package com.ddm.admin;

import com.ddm.admin.dao.RoleUserDao;
import com.ddm.admin.dao.UserDao;
import com.ddm.admin.model.SysRoleUser;
import com.ddm.admin.model.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
class AdminApplicationTests {

    @Resource
    UserDao userDao;
    @Resource
    RoleUserDao roleUserDao;

    @Test
    void contextLoads() {
       SysRoleUser sysRoleUser=new SysRoleUser();
       SysUser sysUser=new SysUser();
//       sysRoleUser.setUserId(1);
//       sysRoleUser.setRoleId(2);
//       roleUserDao.save(sysRoleUser);
//        sysUser.setUsername("wxg");
//        sysUser.setStatus(1);
//        userDao.save(sysUser);
        log.warn("hello warn");
        log.info("hello log");





    }

}
