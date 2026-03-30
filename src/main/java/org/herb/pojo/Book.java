package org.herb.pojo;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

@Data
public class Book {

    private Integer id;

    @NotEmpty
    private String bookName;

    @NotEmpty
    private String author;

    private String introduction;

    private String type;

    private Integer collNum;

    @URL
    private String coverImg;

    public interface Add {
    }

    public interface Update {
    }

    public interface Delete {
    }
}
