package com.proper.phip.sys.menu.service;

import com.proper.phip.core.i18n.I18NService;
import com.proper.phip.core.restful.ReturnException;
import com.proper.phip.core.utils.PinyinUtils;
import com.proper.phip.sys.menu.entity.SysMenuEntity;
import com.proper.phip.sys.menu.entity.SysMenuUrlEntity;
import com.proper.phip.sys.menu.mapper.SysMenuMapper;
import com.proper.phip.sys.menu.mapper.SysMenuUrlMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SysMenuServiceImpl implements SysMenuService {

    @Autowired
    private I18NService i18NService;

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Autowired
    private SysMenuUrlMapper sysMenuUrlMapper;

    @Override
    public List<SysMenuEntity> getMenuByRoleIds(List<String> roleIds) {
        return sysMenuMapper.getMenuByRoleIds(roleIds);
    }

    @Override
    public List<SysMenuEntity> getMenus() {
        return sysMenuMapper.getMenus(null);
    }

    @Override
    public void addMenu(SysMenuEntity sysMenuEntity, String userId) {
        List<SysMenuEntity> menus = sysMenuMapper.getMenus(sysMenuEntity.getCode());
        if (menus.size() > 0) {
            throw new ReturnException(i18NService.getMessage("msg.return.error.edit.code-repeat"));
        }
        if (StringUtils.isEmpty(sysMenuEntity.getNamePy())) {
            sysMenuEntity.setNamePy(PinyinUtils.getPinYinHeadChar(sysMenuEntity.getName()));
        }
        sysMenuEntity.initAddInfo(userId, new Date());
        sysMenuMapper.insertSelective(sysMenuEntity);
    }

    @Override
    public void editMenu(SysMenuEntity sysMenuEntity, String userId) {
        if (StringUtils.isEmpty(sysMenuEntity.getNamePy())) {
            sysMenuEntity.setNamePy(PinyinUtils.getPinYinHeadChar(sysMenuEntity.getName()));
        }
        sysMenuEntity.initModifyInfo(userId, new Date());
        sysMenuMapper.updateByPrimaryKeySelective(sysMenuEntity);
    }

    @Override
    public void deleteMenu(String id) {
        List<SysMenuEntity> menus = sysMenuMapper.getMenus(null);
        List<SysMenuEntity> childMenus = menuChild(id, menus);
        List<String> ids = new ArrayList<>();
        ids.add(id);
        for (SysMenuEntity tmpEntity : childMenus) {
            ids.add(tmpEntity.getId());
        }
        sysMenuMapper.deleteByIds(ids);
        sysMenuUrlMapper.deleteByMenuIds(ids);
    }

    @Override
    @Cacheable(value = "phip.menu.url")
    public List<SysMenuUrlEntity> getMenuUrls(String menuId) {
        return sysMenuUrlMapper.getMenuUrls(menuId);
    }

    @Override
    @CacheEvict(value = "phip.role.menu", key = "#menuId")
    public void editMenuUrls(List<SysMenuUrlEntity> menuUrls, String menuId, String userId) {
        Date date = new Date();

        List<String> menuIds = new ArrayList<>();
        menuIds.add(menuId);
        sysMenuUrlMapper.deleteByMenuIds(menuIds);

        for (SysMenuUrlEntity tempEntity : menuUrls) {
            tempEntity.initAddInfo(userId, date);
            sysMenuUrlMapper.insertSelective(tempEntity);
        }
    }

    /**
     * 取得所有下级菜单
     */
    private List<SysMenuEntity> menuChild(String id, List<SysMenuEntity> menus) {
        List<SysMenuEntity> childMenus = new ArrayList<>();
        for (SysMenuEntity tmpEntity : menus) {
            if (tmpEntity.getParentId().equals(id)) {
                childMenus.add(tmpEntity);
                childMenus.addAll(menuChild(tmpEntity.getId(), menus));
            }
        }
        return childMenus;
    }
}
