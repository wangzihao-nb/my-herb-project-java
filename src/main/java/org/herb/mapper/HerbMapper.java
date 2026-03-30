package org.herb.mapper;

import org.apache.ibatis.annotations.*;
import org.herb.pojo.Herb;

import java.util.List;

@Mapper
public interface HerbMapper {

    //添加中药
    @Insert("insert into herb(cn_name,latin_name,en_name,family,habitat,part,category,property,flavor,meridian_tropism,efficacy,trait,indications,herb_pic)" +
            "values(#{cnName},#{latinName},#{enName},#{family},#{habitat},#{part},#{category},#{property},#{flavor},#{meridianTropism},#{efficacy},#{trait},#{indications},#{herbPic})")
    void add(Herb herb);

    //编辑中药
    @Update("update herb set cn_name=#{cnName},latin_name=#{latinName},en_name=#{enName},family=#{family},habitat=#{habitat},part=#{part},category=#{category}," +
            "property=#{property},flavor=#{flavor},meridian_tropism=#{meridianTropism},efficacy=#{efficacy},trait=#{trait},indications=#{indications},herb_pic=#{herbPic} where id=#{id}")
    void update(Herb herb);

    //删除中药
    @Delete("delete from herb where id=#{id}")
    void delete(Integer id);

    //中药列表
    //写在映射文件中
    List<Herb> list(String family, String category, String property, String flavor, String meridianTropism, String cnName);

    //根据id查询中药（展示详细信息用）
    @Select("select * from herb where id=#{id}")
    Herb findById(Integer id);
}
