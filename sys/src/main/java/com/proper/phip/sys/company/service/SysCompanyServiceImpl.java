package com.proper.phip.sys.company.service;

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
public class SysCompanyServiceImpl implements SysCompanyService {

    @Autowired
    private I18NService i18NService;

    @Autowired
    private SysCompanyMapper sysCompanyMapper;

    @Autowired
    private SysRegionMapper sysRegionMapper;

    @Autowired
    private SysDeptMapper sysDeptMapper;

    @Override
    public List<SysCompanyEntity> getCompanies(Pagination pagination, String name, String code) {
        if (pagination != null) {
            PageHelper.startPage(pagination.getCurrent(), pagination.getPageSize());
        }
        return sysCompanyMapper.getCompanies(name, code);
    }

    @Override
    public void addCompany(SysCompanyEntity sysCompanyEntity, String userId) {
        List<SysCompanyEntity> companies = sysCompanyMapper.getCompanies(null, sysCompanyEntity.getCode());
        if (companies.size() > 0) {
            throw new ReturnException(i18NService.getMessage("msg.return.error.edit.code-repeat"));
        }

        if (StringUtils.isEmpty(sysCompanyEntity.getNamePy())) {
            sysCompanyEntity.setNamePy(PinyinUtils.getPinYinHeadChar(sysCompanyEntity.getName()));
        }
        sysCompanyEntity.setStatus("1");
        sysCompanyEntity.initAddInfo(userId, new Date());
        sysCompanyMapper.insert(sysCompanyEntity);
    }

    @Override
    public void editCompany(SysCompanyEntity sysCompanyEntity, String userId) {
        if (StringUtils.isEmpty(sysCompanyEntity.getNamePy())) {
            sysCompanyEntity.setNamePy(PinyinUtils.getPinYinHeadChar(sysCompanyEntity.getName()));
        }
        sysCompanyEntity.initModifyInfo(userId, new Date());
        sysCompanyMapper.updateByPrimaryKeySelective(sysCompanyEntity);
    }

    @Override
    public void deleteCompany(String id) {
        // 验证是否已使用
        SysRegionEntity sysRegionEntity = new SysRegionEntity();
        sysRegionEntity.setCompanyId(id);
        sysRegionEntity.setStatus("1");
        List<SysRegionEntity> regions = sysRegionMapper.select(sysRegionEntity);
        if (regions.size() > 0) {
            throw new ReturnException(i18NService.getMessage("msg.return.error.delete.used"));
        }
        SysDeptEntity sysDeptEntity = new SysDeptEntity();
        sysDeptEntity.setCompanyRegionId(id);
        sysRegionEntity.setStatus("1");
        List<SysDeptEntity> depts = sysDeptMapper.select(sysDeptEntity);
        if (depts.size() > 0) {
            throw new ReturnException(i18NService.getMessage("msg.return.error.delete.used"));
        }

        // 设置状态为删除
        SysCompanyEntity sysCompanyEntity = new SysCompanyEntity();
        sysCompanyEntity.setId(id);
        sysCompanyEntity.setStatus("0");
        sysCompanyMapper.updateByPrimaryKeySelective(sysCompanyEntity);
    }
}
