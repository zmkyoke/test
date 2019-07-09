package com.proper.phip.sys.role.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

@Service
public class SysRoleCacheServiceImpl implements SysRoleCacheService {

    @Override
    @CacheEvict(value = "phip.role.default")
    public void deleteCacheRole(String isDefault) {}
}
