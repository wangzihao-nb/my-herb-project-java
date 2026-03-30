package org.herb.mapper;

import org.apache.ibatis.annotations.*;
import org.herb.pojo.Post;

import java.util.List;

@Mapper
public interface PostCollectionMapper {

    @Insert("insert into post_collection(post_id,user_id,create_time) values(#{postId},#{userId},now())")
    void collect(Integer postId, Integer userId);

    @Update("update post set coll_num=coll_num+1 where id=#{postId}")
    void addCollNum(Integer postId);

    @Delete("delete from post_collection where id=#{id}")
    void delete(Integer id);

    @Update("update post set coll_num=coll_num-1 where id=#{postId}")
    void subtractCollNum(Integer postId);

    @Select("select * from post_collection where post_id=#{postId} and user_id=#{userId}")
    Post isCollect(Integer postId, Integer userId);

    @Select("select * from post inner join post_collection on post_collection.post_id=post.id where user_id=#{userId} order by create_time desc")
    List<Post> list(Integer userId);

    @Delete("delete from post_collection where post_id=#{postId}")
    void deleteByPostId(Integer postId);

    @Delete("delete from post_collection where user_id=#{userId}")
    void deleteByUserId(Integer userId);
}
