package com.ddm.admin.service;

import com.alibaba.fastjson.JSONArray;
import com.ddm.admin.base.result.Results;
import com.ddm.admin.model.SysPermission;

public interface PermissionService {
    Results<JSONArray> listAllPermission();

    Results<SysPermission> listByRoleId(Integer intValue);
}
