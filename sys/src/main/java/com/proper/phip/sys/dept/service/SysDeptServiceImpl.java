package com.proper.phip.sys.dept.service;

import com.proper.phip.core.i18n.I18NService;
import com.proper.phip.core.restful.ReturnException;
import com.proper.phip.core.utils.PinyinUtils;
import com.proper.phip.sys.company.entity.SysCompanyEntity;
import com.proper.phip.sys.company.mapper.SysCompanyMapper;
import com.proper.phip.sys.dept.entity.SysDeptEntity;
import com.proper.phip.sys.dept.mapper.SysDeptMapper;
import com.proper.phip.sys.region.entity.SysRegionEntity;
import com.proper.phip.sys.region.mapper.SysRegionMapper;
import com.proper.phip.sys.user.entity.SysUserDeptEntity;
import com.proper.phip.sys.user.mapper.SysUserDeptMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SysDeptServiceImpl implements SysDeptService {

    @Autowired
    private I18NService i18NService;

    @Autowired
    private SysDeptMapper sysDeptMapper;

    @Autowired
    private SysCompanyMapper sysCompanyMapper;

    @Autowired
    private SysRegionMapper sysRegionMapper;

    @Autowired
    private SysUserDeptMapper sysUserDeptMapper;

    @Override
    public List<SysDeptEntity> getDepts(String companyRegionId) {
        return sysDeptMapper.getDepts(companyRegionId, null);
    }

    @Override
    public void addDept(SysDeptEntity sysDeptEntity, String userId) {
        List<SysDeptEntity> regions = sysDeptMapper.getDepts(sysDeptEntity.getCompanyRegionId(), sysDeptEntity.getDeptCode());
        if (regions.size() > 0) {
            throw new ReturnException(i18NService.getMessage("msg.return.error.edit.code-repeat"));
        }
        String uniqueCode = "";
        if ("COM".equals(sysDeptEntity.getParentType())) {
            SysCompanyEntity sysCompanyEntity = sysCompanyMapper.selectByPrimaryKey(sysDeptEntity.getCompanyRegionId());
            uniqueCode = sysCompanyEntity.getCode();
        } else {
            SysRegionEntity sysRegionEntity = sysRegionMapper.selectByPrimaryKey(sysDeptEntity.getCompanyRegionId());
            SysCompanyEntity sysCompanyEntity = sysCompanyMapper.selectByPrimaryKey(sysRegionEntity.getCompanyId());
            uniqueCode = sysCompanyEntity.getCode() + "." + sysRegionEntity.getRegionCode();
        }
        sysDeptEntity.setUniqueCode(uniqueCode + "." + sysDeptEntity.getDeptCode());
        if (StringUtils.isEmpty(sysDeptEntity.getDeptNamePy())) {
            sysDeptEntity.setDeptNamePy(PinyinUtils.getPinYinHeadChar(sysDeptEntity.getDeptName()));
        }
        sysDeptEntity.setStatus("1");
        sysDeptEntity.initAddInfo(userId, new Date());
        sysDeptMapper.insertSelective(sysDeptEntity);
    }

    @Override
    public void editDept(SysDeptEntity sysDeptEntity, String userId) {
        if (StringUtils.isEmpty(sysDeptEntity.getDeptNamePy())) {
            sysDeptEntity.setDeptNamePy(PinyinUtils.getPinYinHeadChar(sysDeptEntity.getDeptName()));
        }
        sysDeptEntity.initModifyInfo(userId, new Date());
        sysDeptMapper.updateByPrimaryKeySelective(sysDeptEntity);
    }

    @Override
    public void deleteDept(String id) {
        SysDeptEntity sysDeptEntity = sysDeptMapper.selectByPrimaryKey(id);
        List<SysDeptEntity> depts = sysDeptMapper.getDepts(sysDeptEntity.getCompanyRegionId(), null);
        List<SysDeptEntity> childDepts = deptChild(sysDeptEntity, depts);
        childDepts.add(sysDeptEntity);

        // 验证是否已使用
        List<String> deptIds = new ArrayList<>();
        for (SysDeptEntity tmpEntity: childDepts) {
            deptIds.add(tmpEntity.getId());
        }
        List<SysUserDeptEntity> userDepts = sysUserDeptMapper.getUserDeptByDeptId(deptIds);
        if (userDepts.size() > 0) {
            throw new ReturnException(i18NService.getMessage("msg.return.error.delete.used"));
        }

        // 设置状态为删除
        for (SysDeptEntity tmpEntity: childDepts) {
            SysDeptEntity sysDeptNew = new SysDeptEntity();
            sysDeptNew.setId(tmpEntity.getId());
            sysDeptNew.setStatus("0");
            sysDeptMapper.updateByPrimaryKeySelective(sysDeptNew);
        }
    }

    /**
     * 取得所有下级科室
     */
    private List<SysDeptEntity> deptChild(SysDeptEntity sysDeptEntity, List<SysDeptEntity> depts) {
        List<SysDeptEntity> childDepts = new ArrayList<>();
        for (SysDeptEntity tmpEntity : depts) {
            if (tmpEntity.getParentId().equals(sysDeptEntity.getId())) {
                childDepts.add(tmpEntity);
                childDepts.addAll(deptChild(tmpEntity, depts));
            }
        }
        return childDepts;
    }
}
