package com.proper.phip.sys.role.service;

public interface SysRoleCacheService {

    /**
     * 删除缓存默认权限
     * @param isDefault 默认权限
     */
    void deleteCacheRole(String isDefault);
}
