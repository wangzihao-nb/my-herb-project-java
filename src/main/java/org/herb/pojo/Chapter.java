package org.herb.pojo;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class Chapter {

    private Integer id;

    @NotEmpty
    private String title;

    @NotNull
    private Integer bookId;

    @NotNull
    private Integer contentId;

    public interface Add {
    }
}
