package org.herb.service;

import org.herb.pojo.PageBean;
import org.herb.pojo.Post;

import java.util.List;

public interface PostService {
    void post(Post p);

    void delete(Integer id);

    PageBean<Post> list(Integer pageNum, Integer pageSize);

    PageBean<Post> myPost(Integer pageNum, Integer pageSize, Integer posterId);

    Post findById(Integer id);

    void addView(Integer id);

    List<Post> hotPost();

    void deleteByUserId(Integer userId);
}
