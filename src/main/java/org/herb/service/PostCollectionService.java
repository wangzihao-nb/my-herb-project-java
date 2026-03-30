package org.herb.service;

import org.herb.pojo.PageBean;
import org.herb.pojo.Post;

public interface PostCollectionService {

    void collect(Integer postId);

    void addCollNum(Integer postId);

    void delete(Integer id);

    void subtractCollNum(Integer postId);


    Post isCollect(Integer postId, Integer userId);

    PageBean<Post> list(Integer pageNum, Integer pageSize, Integer userId);

    void deleteByPostId(Integer postId);

    void deleteByUserId(Integer userId);
}
