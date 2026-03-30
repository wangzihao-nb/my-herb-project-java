package org.herb.pojo;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookCollection {

    private Integer id;

    @NotNull
    private Integer bookId;

    @NotNull
    private Integer userId;

    private LocalDateTime createTime;
}
