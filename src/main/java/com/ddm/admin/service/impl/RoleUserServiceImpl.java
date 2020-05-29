package com.ddm.admin.service.impl;

import com.ddm.admin.base.result.Results;
import com.ddm.admin.dao.RoleUserDao;
import com.ddm.admin.model.SysRole;
import com.ddm.admin.model.SysRoleUser;
import com.ddm.admin.service.RoleUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service
public class RoleUserServiceImpl implements RoleUserService {

    @Resource
    private RoleUserDao roleUserDao;

    @Override
    public Results getRoleUserByRoleId(Integer userId) {
        SysRoleUser sysRoleUser=roleUserDao.getRoleUserByRoleId(userId);
        if (sysRoleUser !=null){
            return Results.success(sysRoleUser);
        }else{
            return Results.success();
        }
    }
}
