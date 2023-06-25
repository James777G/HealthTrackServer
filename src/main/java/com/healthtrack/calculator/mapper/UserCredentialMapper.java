package com.healthtrack.calculator.mapper;

import com.healthtrack.calculator.pojo.UserCredential;
import org.apache.ibatis.annotations.*;

import java.util.UUID;

@Mapper
public interface UserCredentialMapper {

    @Select("select id, username, password FROM users_credentials WHERE id = #{uuid}")
    UserCredential getUserCredentialById(@Param("uuid") UUID uuid);

    @Select("select id, username, password FROM users_credentials WHERE username = #{username}")
    UserCredential getUserCredentialByUsername(@Param("username") String username);


    @Insert("insert into users_credentials (id, username, password) values (#{id}, #{username}, #{password})")
    void insertUserCredential(UserCredential userCredential);

    @Delete("delete from users_credentials where username = #{username}")
    void deleteUserCredentialByUsername(@Param("username") String username);
}
