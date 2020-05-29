package com.ddm.admin.dao;

import com.ddm.admin.model.SysUser;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserDao {

    @Insert("insert into sys_user(username,password,nickname,headImgUrl,phone,telephone,email,birthday,sex,status,createTime,updateTime) values(#{username},#{password},#{nickname},#{headImgUrl},#{phone},#{telephone},#{email},#{birthday},#{sex},#{status},now(),now())")
    @Options(useGeneratedKeys = true,keyProperty = "id")
        //@SelectKey(statement = "SELECT LAST_KEY_ID()",keyProperty = "id",resultType = Long.class,before = false)
    int save(SysUser user);

    @Delete("delete from sys_user where id=#{userId}")
    int deleteUser(int userId);


    int updateUser(SysUser user);

    @Select("select * from sys_user t where t.username=#{username}")
    SysUser getUser(String username);

    @Select("select * from sys_user t order by t.id limit #{startPosition},#{limit}")
    List<SysUser> getAllUsersByPage(@Param("startPosition")Integer startPosition,@Param("limit")Integer limit);

    @Select("select count(*) from sys_user t")
    Long countAllUsers();

    @Select("select * from sys_user t where t.telephone=#{telephone}")
    SysUser getUserByPhone(String telephone);

    @Select("select * from sys_user t where t.id=#{id}")
    SysUser getUserById(Long id);

    @Select("select count(*) from sys_user t where t.username like '%${username}%'")
    Long getUserByFuzzyUsername(@Param("username") String username);

    @Select("select * from sys_user t where t.username like '%${username}%' limit #{startPosition},#{limit}")
    List<SysUser> getUserByFuzzyUsernameByPage(@Param("username") String username, @Param("startPosition") Integer startPosition, @Param("limit")Integer limit);
}
