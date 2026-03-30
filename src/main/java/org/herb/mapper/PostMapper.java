package org.herb.mapper;

import org.apache.ibatis.annotations.*;
import org.herb.pojo.Post;

import java.util.List;

@Mapper
public interface PostMapper {
    @Insert("insert into post(poster_id,title,content,cover_img,post_time,view_num,coll_num) values(#{posterId},#{title},#{content},#{coverImg},now(),0,0)")
    void post(Post p);

    @Delete("delete from post where id=#{id}")
    void delete(Integer id);

    @Select("select * from post order by post_time desc")
    List<Post> list();

    @Select("select * from post where poster_id=#{posterId} order by post_time desc")
    List<Post> myPost(Integer posterId);

    @Select("select * from post where id=#{id}")
    Post findById(Integer id);

    @Update("update post set view_num=view_num+1 where id=#{id}")
    void addView(Integer id);

    @Select("select * from post order by view_num desc limit 10")
    List<Post> hotPost();

    @Delete("delete from post where poster_id=#{userId}")
    void deleteByUserId(Integer userId);
}
