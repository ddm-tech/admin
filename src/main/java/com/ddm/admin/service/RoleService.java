package com.ddm.admin.service;

import com.ddm.admin.base.result.Results;
import com.ddm.admin.model.SysRole;
import com.ddm.admin.model.SysUser;

public interface RoleService {

    Results<SysRole> getAllRoles();

    Results<SysRole> getAllRolesByPages(Integer offset, Integer limit);

    Results<SysRole> getRoleByFuzzyRoleName(String roleName, Integer offset, Integer limit);
}
