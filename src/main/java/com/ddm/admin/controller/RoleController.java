package com.ddm.admin.controller;

import com.ddm.admin.base.result.PageTableRequest;
import com.ddm.admin.base.result.Results;
import com.ddm.admin.dao.UserDao;
import com.ddm.admin.model.SysRole;
import com.ddm.admin.model.SysUser;
import com.ddm.admin.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("role")
@Slf4j
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/all")
    @ResponseBody
    public Results<SysRole> getAll() {

        log.info("RoleController.getAll");
        return roleService.getAllRoles();
    }
    @GetMapping("/list")
    @ResponseBody
    public Results<SysRole> getUser(PageTableRequest request) {
        request.countOffset();

        return roleService.getAllRolesByPages(request.getOffset(), request.getLimit());
    }
    @GetMapping("/findRoleByFuzzyRoleName")
    @ResponseBody
    public Results<SysRole> findRoleByFuzzyRoleName(PageTableRequest request,String roleName) {
        request.countOffset();
        log.info("RoleController.findRoleByFuzzyRoleName():param (request="+ request+",name=" + roleName + ")");
        return roleService.getRoleByFuzzyRoleName(roleName,request.getOffset(), request.getLimit());
    }
}
