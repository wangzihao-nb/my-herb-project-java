package org.herb.service;

import org.herb.pojo.Comment;
import org.herb.pojo.PageBean;

import java.util.List;

public interface CommentService {
    void publish(Comment comment);

    void delete(Integer id);

    List<Comment> list(Integer postId);

    Comment getCommentById(Integer id);

    void deleteByPostId(Integer postId);

    void deleteByUserId(Integer userId);
}
