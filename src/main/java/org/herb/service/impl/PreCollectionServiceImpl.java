package org.herb.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.herb.mapper.PreCollectionMapper;
import org.herb.pojo.PageBean;
import org.herb.pojo.PreCollection;
import org.herb.pojo.Prescription;
import org.herb.pojo.Result;
import org.herb.service.PreCollectionService;
import org.herb.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PreCollectionServiceImpl implements PreCollectionService {

    @Autowired
    private PreCollectionMapper preCollectionMapper;

    @Override
    public void collect(Integer preId) {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        preCollectionMapper.collect(preId, userId);
    }

    @Override
    public void addCollNum(Integer preId) {
        preCollectionMapper.addCollNum(preId);
    }

    @Override
    public void delete(Integer id) {
        preCollectionMapper.delete(id);
    }

    @Override
    public void subtractCollNum(Integer preId) {
        preCollectionMapper.subtractCollNum(preId);
    }

    @Override
    public PreCollection isCollect(Integer userId, Integer preId) {
        PreCollection pc = preCollectionMapper.isCollect(userId, preId);
        return pc;
    }

    @Override
    public PageBean<Prescription> list(Integer pageNum, Integer pageSize, Integer userId) {
        //创建pageBean对象封装查询好的对象
        PageBean<Prescription> pb = new PageBean<>();

        //开启分页查询 PageHelper
        PageHelper.startPage(pageNum, pageSize);

        //调用mapper
        List<Prescription> ps = preCollectionMapper.list(userId);

        //Page中提供了方法，可以获取PageHelper分页查询后得到的总记录条数和当前页数据，如果不强转，多态不允许父类去调用子类特有的方法
        Page<Prescription> p = (Page<Prescription>) ps;

        //把数据填充到PageBean对象中
        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());
        return pb;
    }

    @Override
    public Integer getPreIdById(Integer id) {
        return preCollectionMapper.getPreIdById(id);
    }

    @Override
    public void deleteByPreId(Integer preId) {
        preCollectionMapper.deleteByPreId(preId);
    }

    @Override
    public void deleteByUserId(Integer userId) {
        preCollectionMapper.deleteByUserId(userId);
    }

}
