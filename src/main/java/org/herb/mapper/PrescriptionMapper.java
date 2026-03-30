package org.herb.mapper;

import org.apache.ibatis.annotations.*;
import org.herb.pojo.PageBean;
import org.herb.pojo.Prescription;

import java.util.List;

@Mapper
public interface PrescriptionMapper {

    //添加方剂
    @Insert("insert into prescription(pre_name,dosage_form,disease,syndromes,symptom,pre_source,original_text,treatment,coll_num)" +
            " values(#{preName},#{dosageForm},#{disease},#{syndromes},#{symptom},#{preSource},#{originalText},#{treatment},0)")
    void add(Prescription prescription);

    //编辑方剂
    @Update("update prescription set pre_name=#{preName},dosage_form=#{dosageForm},disease=#{disease},syndromes=#{syndromes},symptom=#{symptom}," +
            "pre_source=#{preSource},original_text=#{originalText},treatment=#{treatment},coll_num=#{collNum} where id=#{id}")
    void update(Prescription prescription);

    //删除方剂
    @Delete("delete from prescription where id=#{id}")
    void delete(Integer id);

    //展示方剂详细信息
    @Select("select * from prescription where id=#{id}")
    Prescription findById(Integer id);

    //方剂列表
    //xml文件动态sql
    List<Prescription> list(String dosageForm, String preName);


}
