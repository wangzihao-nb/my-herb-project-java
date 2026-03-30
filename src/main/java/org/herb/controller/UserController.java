package org.herb.controller;

import jakarta.validation.constraints.Pattern;
import org.herb.mapper.UserMapper;
import org.herb.pojo.*;
import org.herb.service.UserService;
import org.herb.utils.JwtUtil;
import org.herb.utils.Md5Util;
import org.herb.utils.ThreadLocalUtil;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate; //用于把token存储到redis中


    //注册
    @PostMapping("/register") //注册接口的请求方式是post
    public Result register(@RequestBody Map<String, String> params){
        String username = params.get("username");
        String password = params.get("password");
        String email = params.get("email");
        String role = params.get("role");
        //下面这样写太繁琐了，spring validation可以使用注解完成参数校验
//        if(username != null && username.length() >= 5 && username.length() <= 16 &&
//        password != null && password.length() >= 5 && password.length() <= 16
//        ){
//            //查询用户
//            User u = userService.FindByUserName(username);
//            if(u == null){
//                //没有被占用
//                //注册
//                userService.register(username,password);
//                return Result.success();
//            }else{
//                //被占用
//                return Result.error("用户名已被占用");
//            }
//        }else{
//            return Result.error("参数不合法");
//        }

        role = "ROLE_USER";
        if(userService.FindByUserName(username) == null){
            userService.register(username, password, email, role);
            return Result.success();
        }else{
            return Result.error("用户名已被占用");
        }
    }

    //登录
    @PostMapping("/login")
    public Result<String> login(@RequestBody Map<String, String> params){
        String username = params.get("username");
        String password = params.get("password");
        //根据用户名查询用户
        User loginUser = userService.FindByUserName(username);

        //判断用户是否存在
        if(loginUser == null){
            return Result.error("用户名错误");
        }

        //判断密码是否正确，loginUser对象中的password是密文
        if(Md5Util.getMD5String(password).equals(loginUser.getPassword())){
            //登录成功
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", loginUser.getId());
            claims.put("username", loginUser.getUsername());
            String token = JwtUtil.genToken(claims);

            //把token存储到redis中
            ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
            operations.set(token, token, 1, TimeUnit.HOURS);

            return Result.success(token);
        }else{
            return Result.error("登录失败");
        }
    }

    //获取用户详细信息
    @GetMapping("/userInfo")
    public Result<User> userInfo(@RequestHeader(name = "Authorization") String token){
        /*//根据用户名获取详细信息
        //解析token
        Map<String, Object> map = JwtUtil.parseToken(token);
        String username = (String)map.get("username");*/

        Map<String, Object> map = ThreadLocalUtil.get();
        String username = (String)map.get("username");
        User user = userService.FindByUserName(username);
        return Result.success(user);
    }

    //更新用户基本信息
    @PutMapping("/update")
    //RequestBody将json数据转换为实体类对象
    //@Validated让实体类里添加的校验注解生效
    public Result update(@RequestBody @Validated User user){
        userService.update(user);
        return Result.success();
    }

    //更新用户头像
    @PatchMapping("/updateAvatar")
    public Result updateAvatar(@RequestParam @URL String avatarUrl){
         userService.updateAvatar(avatarUrl);
         return Result.success();
    }

    //更新用户密码
    @PatchMapping("/updatePwd")
    public Result updatePwd(@RequestBody Map<String, String> params, @RequestHeader("Authorization") String token) {
        //手动校验参数，因为validation提供的注解不能完成我们的需求
        String oldPwd = params.get("old_pwd");
        String newPwd = params.get("new_pwd");
        String rePwd = params.get("re_pwd");

        if(!StringUtils.hasLength(oldPwd) || !StringUtils.hasLength(newPwd) || !StringUtils.hasLength(rePwd)){
            return Result.error("缺少必要的参数");
        }

        //原密码是否正确
        //调用userService根据用户名拿到原密码，与old_pwd进行比对
        Map<String, Object> map = ThreadLocalUtil.get();
        String username = (String)map.get("username");
        User loginUser = userService.FindByUserName(username);
        if(!loginUser.getPassword().equals(Md5Util.getMD5String(oldPwd))){
            return Result.error("原密码填写不正确");
        }

        //新密码是否符合要求
        if(!newPwd.matches("^\\S{5,16}$")){
            return Result.error("新密码长度不合规");
        }

        //newPwd和rePwd是否一致
        if(!newPwd.equals(rePwd)){
            return Result.error("两次填写密码不一致");
        }

        //调用service完成密码更新
        userService.updatePwd(newPwd);
        //删除redis中对应的token
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        operations.getOperations().delete(token);

        return Result.success();
    }

    //获取用户列表
    @GetMapping("/list")
    public Result<PageBean<User>> list(
            Integer pageNum,
            Integer pageSize,
            @RequestParam(required = false) String username
    ){
            PageBean<User> pb = userService.list(pageNum,pageSize,username);
            return Result.success(pb);
    }

    //根据用户id查找用户
    @GetMapping("/getUserById")
    public Result<User> getUserById(@RequestParam Integer id){
        User user = userService.getUserById(id);
        return Result.success(user);
    }

    //删除用户
    @DeleteMapping
    public Result delete(@RequestParam Integer id){
        userService.delete(id);
        return Result.success();
    }
}

