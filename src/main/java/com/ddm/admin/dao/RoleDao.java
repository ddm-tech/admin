package com.ddm.admin.dao;

import com.ddm.admin.dto.RoleDto;
import com.ddm.admin.model.SysRole;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RoleDao {

    @Select("select * from sys_role t")
    List<SysRole> getAllRoles();

    @Select("select count(*) from sys_role t")
    Long countAllRoles();

    @Select("select * from sys_role t order by t.id limit #{startPosition},#{limit}")
    List<SysRole> getAllRolesByPage(@Param("startPosition") Integer startPosition, @Param("limit") Integer limit);

    @Select("select count(*) from sys_role t where t.name like '%${roleName}%'")
    Long getRoleByFuzzyRoleName(String roleName);

    @Select("select * from sys_role t where t.name like '%${roleName}%' limit #{startPosition},#{limit}")
    List<SysRole> getRoleByFuzzyRoleNameByPage(@Param("roleName") String name, @Param("startPosition") Integer startPosition, @Param("limit") Integer limit);

    @Insert("insert into sys_role(name, description, createTime, updateTime) values(#{name}, #{description}, now(), now())")
    @Options(useGeneratedKeys=true ,keyProperty="id")
    int saveRole(SysRole role);
}
