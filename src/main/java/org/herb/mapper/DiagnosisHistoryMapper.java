package org.herb.mapper;

import org.apache.ibatis.annotations.*;
import org.herb.pojo.DiagnosisHistory;

import java.util.List;

@Mapper
public interface DiagnosisHistoryMapper {
    // 添加问诊记录
    @Insert("insert into diagnosis_history(user_id, type, question, answer, create_time) " +
            "values(#{userId}, #{type}, #{question}, #{answer}, now())")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void add(DiagnosisHistory diagnosisHistory);

    // 查询用户的所有问诊记录
    @Select("select * from diagnosis_history where user_id=#{userId} order by create_time desc")
    List<DiagnosisHistory> findByUserId(Integer userId);

    // 根据类型查询问诊记录
    @Select("select * from diagnosis_history where user_id=#{userId} and type=#{type} order by create_time desc")
    List<DiagnosisHistory> findByUserIdAndType(Integer userId, String type);

    // 删除问诊记录
    @Delete("delete from diagnosis_history where id=#{id}")
    void delete(Integer id);
}
