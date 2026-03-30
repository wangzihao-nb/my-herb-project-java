package org.herb.service;

import org.herb.pojo.PageBean;
import org.herb.pojo.Result;
import org.herb.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    //根据用户名查询用户
    User FindByUserName(String username);

    //注册
    void register(String username, String password, String email, String role);

    //更新用户基本信息
    void update(User user);

    //更新头像
    void updateAvatar(String avatarUrl);

    //更新密码
    void updatePwd(String newPwd);

    //用户列表
    PageBean<User> list(Integer pageNum, Integer pageSize, String username);

    User getUserById(Integer id);

    void delete(Integer id);
}
