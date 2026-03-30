package org.herb.pojo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDateTime;

//lombok 在编译阶段为实体类自动生成setter getter toString等
//需要在pom文件中引入依赖，在实体类上添加注解
@Data
public class User {
    @NotNull
    private Integer id;//主键ID

    private String username;//用户名

    @JsonIgnore //让springmvc把当前对象转换成json字符串时忽略password，最终json字符串就没有password属性了
    private String password;//密码

    @NotEmpty
    @Pattern(regexp = "^\\S{1,10}$")
    private String nickname;//昵称

    @NotEmpty
    @Email
    private String email;//邮箱

    @URL
    private String userPic;//用户头像地址

    private String role; //角色类型

    private LocalDateTime createTime;//创建时间
    private LocalDateTime updateTime;//更新时间
}
