package org.herb.mapper;

import org.apache.ibatis.annotations.*;
import org.herb.pojo.Result;
import org.herb.pojo.User;

import java.util.List;

@Mapper
public interface UserMapper {
    //根据用户名查询用户
    @Select("select * from user where username=#{username}")
    User findByUserName(String username);

    //添加
    @Insert("insert into user(username, password, email, create_time, update_time, role)" +
            " values(#{username}, #{password}, #{email}, now(), now(), #{role})")
    void add(String username, String password, String email, String role);

    //修改个人信息
    @Update("update user set nickname=#{nickname},email=#{email},update_time=#{updateTime} where id=#{id}")
    void update(User user);

    //修改头像
    @Update("update user set user_pic=#{avatarUrl},update_time=now() where id=#{id}")
    void updateAvatar(String avatarUrl, Integer id);

    //修改密码
    @Update("update user set password=#{md5String}, update_time=now() where id=#{id}")
    void updatePwd(String md5String, Integer id);

    //配置文件
    List<User> list(String username);

    @Select("select * from user where id=#{id}")
    User getUserById(Integer id);

    @Delete("delete from user where id=#{id}")
    void delete(Integer id);
}
