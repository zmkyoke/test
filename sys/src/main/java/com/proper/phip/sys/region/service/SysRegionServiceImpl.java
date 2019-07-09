package com.proper.phip.sys.region.service;

import com.github.pagehelper.PageHelper;
import com.proper.phip.core.i18n.I18NService;
import com.proper.phip.core.restful.Pagination;
import com.proper.phip.core.restful.ReturnException;
import com.proper.phip.core.utils.PinyinUtils;
import com.proper.phip.sys.company.entity.SysCompanyEntity;
import com.proper.phip.sys.company.mapper.SysCompanyMapper;
import com.proper.phip.sys.dept.entity.SysDeptEntity;
import com.proper.phip.sys.dept.mapper.SysDeptMapper;
import com.proper.phip.sys.region.entity.SysRegionEntity;
import com.proper.phip.sys.region.mapper.SysRegionMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SysRegionServiceImpl implements SysRegionService {

    @Autowired
    private I18NService i18NService;

    @Autowired
    private SysCompanyMapper sysCompanyMapper;

    @Autowired
    private SysRegionMapper sysRegionMapper;

    @Autowired
    private SysDeptMapper sysDeptMapper;

    @Override
    public List<SysRegionEntity> getRegions(Pagination pagination, String regionName, String regionCode, String companyId) {
        if (pagination != null) {
            PageHelper.startPage(pagination.getCurrent(), pagination.getPageSize());
        }
        return sysRegionMapper.getRegions(regionName, regionCode, companyId);
    }

    @Override
    public void addRegion(SysRegionEntity sysRegionEntity, String userId) {
        List<SysRegionEntity> regions = sysRegionMapper.getRegions(null, sysRegionEntity.getRegionCode(), sysRegionEntity.getCompanyId());
        if (regions.size() > 0) {
            throw new ReturnException(i18NService.getMessage("msg.return.error.edit.code-repeat"));
        }

        SysCompanyEntity sysCompanyEnity = sysCompanyMapper.selectByPrimaryKey(sysRegionEntity.getCompanyId());
        sysRegionEntity.setUniqueCode(sysCompanyEnity.getCode() + "." + sysRegionEntity.getRegionCode());
        if (StringUtils.isEmpty(sysRegionEntity.getRegionNamePy())) {
            sysRegionEntity.setRegionNamePy(PinyinUtils.getPinYinHeadChar(sysRegionEntity.getRegionName()));
        }
        sysRegionEntity.setStatus("1");
        sysRegionEntity.initAddInfo(userId, new Date());
        sysRegionMapper.insert(sysRegionEntity);
    }

    @Override
    public void editRegion(SysRegionEntity sysRegionEntity, String userId) {
        SysCompanyEntity sysCompanyEnity = sysCompanyMapper.selectByPrimaryKey(sysRegionEntity.getCompanyId());
        sysRegionEntity.setUniqueCode(sysCompanyEnity.getCode() + "." + sysRegionEntity.getRegionCode());
        if (StringUtils.isEmpty(sysRegionEntity.getRegionNamePy())) {
            sysRegionEntity.setRegionNamePy(PinyinUtils.getPinYinHeadChar(sysRegionEntity.getRegionName()));
        }
        sysRegionEntity.initModifyInfo(userId, new Date());
        sysRegionMapper.updateByPrimaryKeySelective(sysRegionEntity);
    }

    @Override
    public void deleteRegion(String id) {
        // 验证是否已使用
        SysDeptEntity sysDeptEntity = new SysDeptEntity();
        sysDeptEntity.setCompanyRegionId(id);
        sysDeptEntity.setStatus("1");
        List<SysDeptEntity> depts = sysDeptMapper.select(sysDeptEntity);
        if (depts.size() > 0) {
            throw new ReturnException(i18NService.getMessage("msg.return.error.delete.used"));
        }

        // 设置状态为删除
        SysRegionEntity sysRegionEntity = new SysRegionEntity();
        sysRegionEntity.setId(id);
        sysRegionEntity.setStatus("0");
        sysRegionMapper.updateByPrimaryKeySelective(sysRegionEntity);
    }
}
