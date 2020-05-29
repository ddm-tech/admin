package com.ddm.admin.service.impl;

import com.ddm.admin.base.result.Results;
import com.ddm.admin.dao.RoleDao;
import com.ddm.admin.model.SysRole;
import com.ddm.admin.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleDao roleDao;

    @Override
    public Results<SysRole> getAllRoles() {
        return Results.success(50,roleDao.getAllRoles());
    }

    @Override
    public Results<SysRole> getAllRolesByPages(Integer offset, Integer limit) {
        return Results.success(roleDao.countAllRoles().intValue(), roleDao.getAllRolesByPage(offset, limit));
    }

    @Override
    public Results<SysRole> getRoleByFuzzyRoleName(String roleName, Integer offset, Integer limit) {
        return Results.success(roleDao.getRoleByFuzzyRoleName(roleName).intValue(),roleDao.getRoleByFuzzyRoleNameByPage(roleName,offset,limit));
    }


}
