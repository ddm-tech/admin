package com.ddm.admin.service.impl;

import com.ddm.admin.base.result.Results;
import com.ddm.admin.dao.RoleUserDao;
import com.ddm.admin.dao.UserDao;
import com.ddm.admin.dto.UserDto;
import com.ddm.admin.model.SysRoleUser;
import com.ddm.admin.model.SysUser;
import com.ddm.admin.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;
    @Resource
    private RoleUserDao roleUserDao;

    @Override
    public SysUser getUser(String username) {
        return userDao.getUser(username);
    }

    @Override
    public Results<SysUser> getAllUsersByPages(Integer offset, Integer limit) {

        return Results.success(userDao.countAllUsers().intValue(), userDao.getAllUsersByPage(offset, limit));
    }

    @Override
    public Results save(SysUser user, Integer roleId) {
        if (roleId != null) {
            userDao.save(user);
            SysRoleUser sysRoleUser = new SysRoleUser();
            sysRoleUser.setRoleId(roleId);
            sysRoleUser.setUserId(user.getId().intValue());
            roleUserDao.save(sysRoleUser);
            return Results.success();
        }
        return Results.failure();
    }

    @Override
    public SysUser getUserByPhone(String telephone) {
        return userDao.getUserByPhone(telephone);
    }

    @Override
    public SysUser getUserById(Long id) {
        return userDao.getUserById(id);
    }

    @Override
    public Results<SysUser> update(UserDto userDto, Integer roleId) {
        if (roleId !=null) {
            userDao.updateUser(userDto);
            SysRoleUser sysRoleUser = new SysRoleUser();
            sysRoleUser.setRoleId(roleId);
            sysRoleUser.setUserId(userDto.getId().intValue());
            if (roleUserDao.getRoleUserByRoleId(userDto.getId().intValue()) != null) {
                roleUserDao.updateSysRoleUser(sysRoleUser);
            } else {
                roleUserDao.save(sysRoleUser);

            }

            return Results.success();
        }else {
            return Results.failure();
        }
    }

    @Override
    public int deleteUser(Long id) {
        roleUserDao.deleteRoleUserById(id.intValue());

        return userDao.deleteUser(id.intValue());
    }

    @Override
    public Results<SysUser> getUserByFuzzyUsername(String username, Integer offset, Integer limit) {
        return Results.success(userDao.getUserByFuzzyUsername(username).intValue(),userDao.getUserByFuzzyUsernameByPage(username,offset,limit));
    }

}
