package org.herb.pojo;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Comment {

    private Integer id;

    @NotNull
    private Integer publisherId;

    @NotNull
    private Integer postId;

    @NotEmpty
    private String content;

    @NotEmpty
    private LocalDateTime publishTime;

    private Integer replyCommentId;

    private Integer commentRank;
}
