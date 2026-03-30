package org.herb.pojo;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDateTime;

@Data
public class Post {

    private Integer id;

    @NotNull
    private Integer posterId;

    @NotEmpty
    private String title;

    @NotEmpty
    private String content;

    @NotEmpty
    @URL
    private String coverImg;

    private LocalDateTime postTime;

    private Integer viewNum;

    private Integer collNum;
}
