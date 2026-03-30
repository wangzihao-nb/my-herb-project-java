package org.herb.mapper;

import org.apache.ibatis.annotations.*;
import org.herb.pojo.Pcm;

import java.util.List;

@Mapper
public interface PcmMapper {

    //添加中成药
    @Insert("insert into pcm(pcm_name,dosage_form,composition,pcm_usage,type) values(#{pcmName},#{dosageForm},#{composition},#{pcmUsage},#{type})")
    void add(Pcm pcm);

    //编辑中成药
    @Update("update pcm set pcm_name=#{pcmName},dosage_form=#{dosageForm},composition=#{composition},pcm_usage=#{pcmUsage},type=#{type} where id=#{id}")
    void update(Pcm pcm);

    //删除中成药
    @Delete("delete from pcm where id=#{id}")
    void delete(Integer id);

    //展示中成药详细信息
    @Select("select * from pcm where id=#{id}")
    Pcm findById(Integer id);

    //中成药列表
    //配置文件
    List<Pcm> list(String dosageForm, String type, String pcmName);
}
