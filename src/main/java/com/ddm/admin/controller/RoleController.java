package com.ddm.admin.controller;

import com.ddm.admin.base.result.PageTableRequest;
import com.ddm.admin.base.result.ResponseCode;
import com.ddm.admin.base.result.Results;
import com.ddm.admin.dao.UserDao;
import com.ddm.admin.dto.RoleDto;
import com.ddm.admin.dto.UserDto;
import com.ddm.admin.model.SysRole;
import com.ddm.admin.model.SysUser;
import com.ddm.admin.service.RoleService;
import com.ddm.admin.util.MD5;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("role")
@Slf4j
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping("/all")
    @ResponseBody
    public Results<SysRole> getAll() {
        return roleService.getAllRoles();
    }

    @GetMapping("/list")
    @ResponseBody
    public Results list(PageTableRequest request) {
        log.info("RoleController.list(): param ( request = " + request +" )");
        request.countOffset();
        return roleService.getAllRolesByPages(request.getOffset(), request.getLimit());
    }

    @GetMapping(value = "/add")
    public String addRole(Model model) {
        model.addAttribute("sysRole",new SysRole());
        return "role/role-add";
    }

    @PostMapping(value = "/add")
    @ResponseBody
    public Results saveRole(@RequestBody RoleDto roleDto) {
        return roleService.save(roleDto);
    }

//    @GetMapping(value = "/edit")
//    public String editRole(Model model, SysRole role) {
//        model.addAttribute("sysRole",roleService.getRoleById(role.getId()));
//        return "role/role-edit";
//    }

//    @PostMapping(value = "/edit")
//    @ResponseBody
//    public Results updateRole(@RequestBody RoleDto roleDto) {
//        return roleService.update(roleDto);
//    }

//    @GetMapping(value = "/delete")
//    @ResponseBody
//    public Results<SysRole> deleteRole(RoleDto roleDto) {
//        return roleService.delete(roleDto.getId());
//    }

    String pattern = "yyyy-MM-dd";
    //只需要加上下面这段即可，注意不能忘记注解
    @InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request) {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat(pattern), true));// CustomDateEditor为自定义日期编辑器
    }

    @GetMapping("/findRoleByFuzzyRoleName")
    @ResponseBody
    public Results findRoleByFuzzyRoleName(PageTableRequest requests, String roleName) {
        requests.countOffset();
        return roleService.getRoleByFuzzyRoleName(roleName,requests.getOffset(),requests.getLimit());
    }

}

