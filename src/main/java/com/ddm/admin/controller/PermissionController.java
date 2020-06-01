package com.ddm.admin.controller;

import com.alibaba.fastjson.JSONArray;
import com.ddm.admin.base.result.Results;
import com.ddm.admin.dto.RoleDto;
import com.ddm.admin.model.SysPermission;
import com.ddm.admin.service.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("permission")
@Slf4j
public class PermissionController {
    @Resource
    private PermissionService permissionService;
    @RequestMapping(value = "/listAllPermission", method = RequestMethod.GET)
    @ResponseBody
    public Results<JSONArray> listAllPermission() {
        return permissionService.listAllPermission();
    }
    @RequestMapping(value = "/listAllPermissionByRoleId", method = RequestMethod.GET)
    @ResponseBody
    public Results<SysPermission> listAllPermissionByRoleId(RoleDto roleDto) {
        log.info(getClass().getName() + " : param =  " + roleDto);
        return permissionService.listByRoleId(roleDto.getId().intValue());
    }

}
