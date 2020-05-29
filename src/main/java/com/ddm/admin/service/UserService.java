package com.ddm.admin.service;

import com.ddm.admin.base.result.Results;
import com.ddm.admin.dto.UserDto;
import com.ddm.admin.model.SysUser;

public interface UserService {
    SysUser getUser(String username);

    Results<SysUser> getAllUsersByPages(Integer offset, Integer limit);

    Results save(SysUser userDto, Integer roleId);


    SysUser getUserByPhone(String telephone);

    SysUser getUserById(Long id);

    Results<SysUser> update(UserDto userDto, Integer roleId);

    int deleteUser(Long id);

    Results<SysUser> getUserByFuzzyUsername(String username, Integer offset, Integer limit);
}
