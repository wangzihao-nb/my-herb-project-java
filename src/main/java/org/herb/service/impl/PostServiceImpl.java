package org.herb.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.herb.mapper.PostMapper;
import org.herb.pojo.Book;
import org.herb.pojo.PageBean;
import org.herb.pojo.Post;
import org.herb.service.PostService;
import org.herb.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostMapper postMapper;
    @Override
    @Transactional
    public void post(Post p) {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer posterId = (Integer)map.get("id");
        p.setPosterId(posterId);
        postMapper.post(p);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        postMapper.delete(id);
    }

    @Override
    public PageBean<Post> list(Integer pageNum, Integer pageSize) {
        //创建pageBean对象封装查询好的对象
        PageBean<Post> pb = new PageBean<>();

        //开启分页查询 PageHelper
        PageHelper.startPage(pageNum, pageSize);

        //调用mapper
        List<Post> ps = postMapper.list();

        //Page中提供了方法，可以获取PageHelper分页查询后得到的总记录条数和当前页数据，如果不强转，多态不允许父类去调用子类特有的方法
        Page<Post> p = (Page<Post>) ps;

        //把数据填充到PageBean对象中
        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());
        return pb;
    }

    @Override
    public PageBean<Post> myPost(Integer pageNum, Integer pageSize, Integer posterId) {
        //创建pageBean对象封装查询好的对象
        PageBean<Post> pb = new PageBean<>();

        //开启分页查询 PageHelper
        PageHelper.startPage(pageNum, pageSize);

        //调用mapper
        List<Post> ps = postMapper.myPost(posterId);

        //Page中提供了方法，可以获取PageHelper分页查询后得到的总记录条数和当前页数据，如果不强转，多态不允许父类去调用子类特有的方法
        Page<Post> p = (Page<Post>) ps;

        //把数据填充到PageBean对象中
        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());
        return pb;
    }

    @Override
    public Post findById(Integer id) {
        return postMapper.findById(id);
    }

    @Override
    @Transactional
    public void addView(Integer id) {
        postMapper.addView(id);
    }

    @Override
    public List<Post> hotPost() {
        return postMapper.hotPost();
    }

    @Override
    @Transactional
    public void deleteByUserId(Integer userId) {
        postMapper.deleteByUserId(userId);
    }
}
