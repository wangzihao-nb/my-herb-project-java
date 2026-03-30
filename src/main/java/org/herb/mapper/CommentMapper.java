package org.herb.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.herb.pojo.Comment;

import java.util.List;

@Mapper
public interface CommentMapper {
    @Insert("insert into comment(publisher_id,post_id,content,publish_time,reply_comment_id,comment_rank) values(#{publisherId},#{postId},#{content},now(),#{replyCommentId},#{commentRank})")
    void post(Comment comment);

    @Delete("delete from comment where id=#{id}")
    void delete(Integer id);

    @Select("select * from comment where post_id=#{postId} order by publish_time desc")
    List<Comment> list(Integer postId);

    @Select("select * from comment where id=#{id}")
    Comment getCommentById(Integer id);

    @Delete("delete from comment where post_id=#{postId}")
    void deleteByPostId(Integer postId);

    @Delete("delete from comment where publisher_id=#{userId}")
    void deleteByUserId(Integer userId);
}
