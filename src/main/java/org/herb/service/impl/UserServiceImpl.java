package org.herb.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.herb.mapper.UserMapper;
import org.herb.pojo.PageBean;
import org.herb.pojo.Pcm;
import org.herb.pojo.Result;
import org.herb.pojo.User;
import org.herb.service.UserService;
import org.herb.utils.Md5Util;
import org.herb.utils.ThreadLocalUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Override
    public User FindByUserName(String username) {
        User u = userMapper.findByUserName(username);
        return u;
    }

    @Override
    @Transactional
    public void register(String username, String password, String email, String role) {
        logger.info("User registration attempt - username: {}, email: {}", username, email);
        String md5String = Md5Util.getMD5String(password);
        userMapper.add(username, md5String, email, role);
        logger.info("User registered successfully - username: {}", username);
    }

    @Override
    @Transactional
    public void update(User user) {
        logger.info("Updating user info - userId: {}", user.getId());
        user.setUpdateTime(LocalDateTime.now());
        userMapper.update(user);
        logger.info("User info updated successfully - userId: {}", user.getId());
    }

    @Override
    @Transactional
    public void updateAvatar(String avatarUrl) {
        //用户携带的token解析后的数据存放到ThreadLocal中，包括用户id
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        userMapper.updateAvatar(avatarUrl, id);
    }

    @Override
    @Transactional
    public void updatePwd(String newPwd) {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        logger.info("Updating password for userId: {}", id);
        userMapper.updatePwd(Md5Util.getMD5String(newPwd), id);
        logger.info("Password updated successfully for userId: {}", id);
    }

    @Override
    public PageBean<User> list(Integer pageNum, Integer pageSize, String username) {
        //创建pageBean对象封装查询好的对象
        PageBean<User> pb = new PageBean<>();

        //开启分页查询 PageHelper
        PageHelper.startPage(pageNum, pageSize);

        //调用mapper
        List<User> ps = userMapper.list(username);

        //Page中提供了方法，可以获取PageHelper分页查询后得到的总记录条数和当前页数据，如果不强转，多态不允许父类去调用子类特有的方法
        Page<User> p = (Page<User>) ps;

        //把数据填充到PageBean对象中
        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());
        return pb;
    }

    @Override
    public User getUserById(Integer id) {

        return userMapper.getUserById(id);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        logger.warn("Deleting user - userId: {}", id);
        userMapper.delete(id);
        logger.warn("User deleted successfully - userId: {}", id);
    }


}
