package com.ddm.admin.controller;

import com.ddm.admin.base.result.PageTableRequest;
import com.ddm.admin.base.result.ResponseCode;
import com.ddm.admin.base.result.Results;
import com.ddm.admin.dto.UserDto;
import com.ddm.admin.model.SysUser;
import com.ddm.admin.service.UserService;
import com.ddm.admin.util.MD5;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Controller
@RequestMapping("user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{username}")
    @ResponseBody
    public SysUser user(@PathVariable String username) {
        log.info("UserController.user():param (username=" + username + ")");
        return userService.getUser(username);
    }

    @GetMapping("/list")
    @ResponseBody
    public Results<SysUser> getUser(PageTableRequest request) {
        request.countOffset();

        return userService.getAllUsersByPages(request.getOffset(), request.getLimit());
    }

    @GetMapping("/add")
    public String addUser(Model model) {
        model.addAttribute(new SysUser());
        return "user/user-add";
    }

    @PostMapping("/add")
    @ResponseBody
    public Results<SysUser> saveUser(UserDto userDto, Integer roleId) {
        SysUser sysUser = null;
        sysUser=userService.getUserByPhone(userDto.getTelephone());
        if(sysUser !=null && !(sysUser.getId().equals(userDto.getId()))){
            return Results.failure(ResponseCode.PHONE_REPEAT.getCode(),ResponseCode.PHONE_REPEAT.getMessage());
        }
        userDto.setStatus(1);
        userDto.setPassword(MD5.crypt(userDto.getPassword()));
        return userService.save(userDto, roleId);
    }

    String pattern = "yyyy-MM-dd";
    @InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request){
        binder.registerCustomEditor(Date.class,new CustomDateEditor(new SimpleDateFormat(pattern),true));
    }
    @GetMapping("/edit")
    public String addUser(Model model, SysUser sysUser) {
       model.addAttribute(userService.getUserById(sysUser.getId()));
        return "user/user-edit";

    }
    @PostMapping("/edit")
    @ResponseBody
    public Results<SysUser> updateUser(UserDto userDto, Integer roleId) {
        SysUser sysUser = null;
        sysUser=userService.getUserByPhone(userDto.getTelephone());
        if(sysUser !=null && !(sysUser.getId().equals(userDto.getId()))){
            return Results.failure(ResponseCode.PHONE_REPEAT.getCode(),ResponseCode.PHONE_REPEAT.getMessage());
        }

        return userService.update(userDto, roleId);
    }
    @GetMapping("/delete")
    @ResponseBody
    public Results deleteUser(UserDto userDto) {
        int count=userService.deleteUser(userDto.getId());
        if (count>0) {
            return Results.success();
        }else{
        return Results.failure();
        }
    }

    @GetMapping("/findUserByFuzzyUsername")
    @ResponseBody
    public Results<SysUser> findUserByFuzzyUsername(PageTableRequest request,String username) {
        request.countOffset();
        log.info("UserController.findUserByFuzzyUsername():param (request="+ request+",username=" + username + ")");
        request.countOffset();
        return userService.getUserByFuzzyUsername(username,request.getOffset(), request.getLimit());
    }
}
