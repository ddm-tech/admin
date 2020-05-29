package com.ddm.admin.service;

import com.ddm.admin.base.result.Results;
import org.springframework.stereotype.Service;


public interface RoleUserService {
    Results getRoleUserByRoleId(Integer userId);
}
