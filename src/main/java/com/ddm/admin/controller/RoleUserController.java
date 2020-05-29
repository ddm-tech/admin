package com.ddm.admin.controller;

import com.ddm.admin.base.result.Results;
import com.ddm.admin.service.RoleUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.juli.logging.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.xml.transform.Result;

@Controller
@RequestMapping("roleuser")
@Slf4j
public class RoleUserController {

    @Resource
    RoleUserService roleUserService;

    @PostMapping("/getRoleUserByRoleId")
    @ResponseBody
    public Results getRoleUserByRoleId(Integer userId){
        log.info("RoleUserController.getRoleUserByRoleId: param =" +userId);
        return roleUserService.getRoleUserByRoleId(userId);
    }

}
