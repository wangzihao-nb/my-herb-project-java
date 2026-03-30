package org.herb.pojo;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostCollection {

    @NotNull
    private Integer postId;

    @NotNull
    private Integer userId;

    private LocalDateTime createTime;
}
