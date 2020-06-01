package com.ddm.admin.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.ddm.admin.base.result.Results;
import com.ddm.admin.dao.PermissionDao;
import com.ddm.admin.model.SysPermission;
import com.ddm.admin.service.PermissionService;
import com.ddm.admin.util.TreeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
@Slf4j
public class PermissionServiceImpl implements PermissionService {
    @Resource
    private PermissionDao permissionDao;

    @Override
    public Results<JSONArray> listAllPermission() {
        log.info(getClass().getName() + ".listAllPermission()");
        List datas = permissionDao.findAll();
        JSONArray array = new JSONArray();
        log.info(getClass().getName() + ".setPermissionsTree(?,?,?)");
        TreeUtils.setPermissionsTree(0, datas, array);
        return Results.success(array);
    }



    @Override

    public Results<SysPermission> listByRoleId(Integer roleId) {
        List<SysPermission> datas = permissionDao.listByRoleId(roleId);
        return Results.success(0, datas);
    }
}
