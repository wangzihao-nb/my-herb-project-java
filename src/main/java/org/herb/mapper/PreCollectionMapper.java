package org.herb.mapper;

import org.apache.ibatis.annotations.*;
import org.herb.pojo.PreCollection;
import org.herb.pojo.Prescription;

import java.util.List;

@Mapper
public interface PreCollectionMapper {

    //收藏方剂
    @Insert("insert into pre_collection(pre_id,user_id,create_time) values(#{preId},#{userId},now())")
    void collect(Integer preId, Integer userId);

    //方剂收藏数+1
    @Update("update prescription set coll_num=coll_num+1 where id=#{preId}")
    void addCollNum(Integer preId);

    //取消收藏
    @Delete("delete from pre_collection where id=#{id}")
    void delete(Integer id);

    //收藏数-1
    @Update("update prescription set coll_num=coll_num-1 where id=#{preId}")
    void subtractCollNum(Integer preId);

    //根据userId和preId查找收藏表里的项
    @Select("select * from pre_collection where user_id=#{userId} and pre_id=#{preId}")
    PreCollection isCollect(Integer userId, Integer preId);

    //根据收藏表里的preId在方剂表中查询结果
    @Select("select * from prescription inner join pre_collection on pre_collection.pre_id=prescription.id where user_id=#{userId} order by create_time desc")
    List<Prescription> list(Integer userId);

    //根据id查找preId
    @Select("select pre_id from pre_collection where id=#{id}")
    Integer getPreIdById(Integer id);

    @Delete("delete from pre_collection where pre_id=#{preId}")
    void deleteByPreId(Integer preId);

    @Delete("delete from pre_collection where user_id=#{userId}")
    void deleteByUserId(Integer userId);
}
