package org.herb.pojo;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class Pcm {

    @NotNull
    private Integer id;

    @NotEmpty
    private String pcmName;

    private String dosageForm; //剂型

    private String composition; //处方组成

    private String pcmUsage; //用法用量

    private String type; //类型

    public interface Add {
    }

    public interface Update {
    }

    public interface Delete {
    }
}
