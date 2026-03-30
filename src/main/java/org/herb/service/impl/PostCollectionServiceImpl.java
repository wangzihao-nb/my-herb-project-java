package org.herb.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.herb.mapper.PostCollectionMapper;
import org.herb.pojo.PageBean;
import org.herb.pojo.Post;
import org.herb.pojo.Prescription;
import org.herb.service.PostCollectionService;
import org.herb.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PostCollectionServiceImpl implements PostCollectionService {
    @Autowired
    private PostCollectionMapper postCollectionMapper;

    @Override
    public void collect(Integer postId) {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        postCollectionMapper.collect(postId, userId);
    }

    @Override
    public void addCollNum(Integer postId) {
        postCollectionMapper.addCollNum(postId);
    }

    @Override
    public void delete(Integer id) {
        postCollectionMapper.delete(id);
    }

    @Override
    public void subtractCollNum(Integer postId) {
        postCollectionMapper.subtractCollNum(postId);
    }

    @Override
    public Post isCollect(Integer postId, Integer userId) {
        return postCollectionMapper.isCollect(postId, userId);
    }

    @Override
    public PageBean<Post> list(Integer pageNum, Integer pageSize, Integer userId) {
        //创建pageBean对象封装查询好的对象
        PageBean<Post> pb = new PageBean<>();

        //开启分页查询 PageHelper
        PageHelper.startPage(pageNum, pageSize);

        //调用mapper
        List<Post> ps = postCollectionMapper.list(userId);

        //Page中提供了方法，可以获取PageHelper分页查询后得到的总记录条数和当前页数据，如果不强转，多态不允许父类去调用子类特有的方法
        Page<Post> p = (Page<Post>) ps;

        //把数据填充到PageBean对象中
        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());
        return pb;
    }

    @Override
    public void deleteByPostId(Integer postId) {
        postCollectionMapper.deleteByPostId(postId);
    }

    @Override
    public void deleteByUserId(Integer userId) {
        postCollectionMapper.deleteByUserId(userId);
    }
}
