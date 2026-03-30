package org.herb.service;

import org.herb.pojo.PageBean;
import org.herb.pojo.PreCollection;
import org.herb.pojo.Prescription;
import org.herb.pojo.Result;

public interface PreCollectionService {

    //收藏方剂
    void collect(Integer preId);

    //方剂表收藏数+1
    void addCollNum(Integer preId);

    //取消收藏
    void delete(Integer id);

    //收藏数-1
    void subtractCollNum(Integer preId);

    //判断是否收藏
    PreCollection isCollect(Integer userId, Integer preId);

    //收藏列表
    PageBean<Prescription> list(Integer pageNum, Integer pageSize, Integer userId);

    //根据id查询preId
    Integer getPreIdById(Integer id);

    void deleteByPreId(Integer preId);

    void deleteByUserId(Integer userId);
}
