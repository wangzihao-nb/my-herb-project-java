package org.herb.pojo;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PreCollection {

    private Integer id;

    @NotNull
    private Integer preId;

    @NotNull
    private Integer userId;

    private LocalDateTime createTime;
    public interface Collect {
    }
}
