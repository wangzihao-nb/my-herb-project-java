package org.herb.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.herb.mapper.HerbMapper;
import org.herb.pojo.Herb;
import org.herb.pojo.PageBean;
import org.herb.service.HerbCacheService;
import org.herb.service.HerbService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HerbServiceImpl implements HerbService {

    private static final Logger logger = LoggerFactory.getLogger(HerbServiceImpl.class);

    @Autowired
    private HerbMapper herbMapper;

    @Autowired
    private HerbCacheService herbCacheService;

    //管理员使用

    //1.添加中药
    @Override
    @Transactional
    public void add(Herb herb) {
        logger.info("Adding new herb: {}", herb.getCnName());
        herbMapper.add(herb);
        herbCacheService.evictHotHerbsCache();
        logger.info("Herb added successfully: {}", herb.getCnName());
    }

    //2.编辑中药
    @Override
    @Transactional
    public void update(Herb herb) {
        logger.info("Updating herb with id: {}", herb.getId());
        herbMapper.update(herb);
        herbCacheService.evictHerbCache(herb.getId());
        herbCacheService.evictHotHerbsCache();
        logger.info("Herb updated successfully: {}", herb.getId());
    }

    //3.删除中药
    @Override
    @Transactional
    public void delete(Integer id) {
        logger.warn("Deleting herb with id: {}", id);
        herbMapper.delete(id);
        herbCacheService.evictHerbCache(id);
        herbCacheService.evictHotHerbsCache();
        logger.warn("Herb deleted successfully: {}", id);
    }

    //4.中药列表
    @Override
    public PageBean<Herb> list(Integer pageNum, Integer pageSize, String family, String category, String property, String flavor, String meridianTropism, String cnName) {
        //创建pageBean对象封装查询好的对象
        PageBean<Herb> pb = new PageBean<>();

        //开启分页查询 PageHelper
        PageHelper.startPage(pageNum, pageSize);

        //调用mapper
        List<Herb> hs = herbMapper.list(family,category,property,flavor,meridianTropism,cnName);

        //Page中提供了方法，可以获取PageHelper分页查询后得到的总记录条数和当前页数据，如果不强转，多态不允许父类去调用子类特有的方法
        Page<Herb> p = (Page<Herb>) hs;

        //把数据填充到PageBean对象中
        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());
        return pb;
    }

    @Override
    public Herb findById(Integer id) {
        Herb herb = herbCacheService.getHerbById(id);
        if (herb != null) {
            logger.debug("Retrieved herb from cache: {}", id);
            return herb;
        }
        
        logger.debug("Retrieving herb from database: {}", id);
        herb = herbMapper.findById(id);
        if (herb != null) {
            herbCacheService.cacheHerb(herb);
        }
        return herb;
    }

}
