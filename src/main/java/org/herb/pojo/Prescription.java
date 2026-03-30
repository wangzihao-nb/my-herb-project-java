package org.herb.pojo;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class Prescription {

    @NotNull
    private Integer id;

    @NotEmpty
    private String preName;

    private String dosageForm; //剂型

    private String disease; //主治疾病

    private String syndromes; //病因病机或主治证候

    private String symptom; //症状

    private String preSource; //处方来源

    private String originalText; //原文

    private String treatment; //功能主治

    private Integer collNum; //收藏数

    public interface Add {
    }

    public interface Update {
    }

    public interface Delete {
    }

    public interface AddColl {
    }
}
