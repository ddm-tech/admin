package com.ddm.admin.dao;

import com.ddm.admin.model.SysRoleUser;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface RoleUserDao {

    @Insert("insert into sys_role_user(userId,roleId) values(#{userId},#{roleId})")
    void save(SysRoleUser sysRoleUser);

    @Select("select * from sys_role_user t where t.userId=#{userId}")
    SysRoleUser getRoleUserByRoleId(Integer userId);


    int updateSysRoleUser(SysRoleUser sysRoleUser);

    @Delete("delete from sys_role_user where userId=#{userId}")
    int deleteRoleUserById(int userId);
}
