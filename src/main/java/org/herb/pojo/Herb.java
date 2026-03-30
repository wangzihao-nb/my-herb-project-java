package org.herb.pojo;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.Default;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

@Data
public class Herb {


    private Integer id;

    @NotEmpty
    private String cnName;

    @NotEmpty
    private String latinName; //拉丁名

    @NotEmpty
    private String enName;

    private String family; //科

    private String habitat; //产地

    private String part; //药用部位

    private String category; //类型

    private String property; //性

    private String flavor; //味

    private String meridianTropism; //归经

    private String efficacy; //功效

    private String trait; //性状

    private String indications; //规格

    @URL
    private String herbPic; //图片

    public interface Add{

    }

    public interface Update{

    }

    public interface Delete{

    }
}
