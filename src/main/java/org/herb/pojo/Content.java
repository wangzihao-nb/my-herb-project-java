package org.herb.pojo;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class Content {

    private Integer id;

    @NotEmpty
    private String bookContent;
}
