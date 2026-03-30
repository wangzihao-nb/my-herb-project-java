package org.herb.service.impl;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zhipu.oapi.ClientV4;
import com.zhipu.oapi.Constants;
import com.zhipu.oapi.service.v4.model.*;
import io.reactivex.Flowable;
import org.herb.mapper.PcmMapper;
import org.herb.pojo.PageBean;
import org.herb.pojo.Pcm;
import org.herb.service.PcmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;



@Service
public class PcmServiceImpl implements PcmService {

    @Autowired
    private PcmMapper pcmMapper;

    @Override
    @Transactional
    public void add(Pcm pcm) {
        pcmMapper.add(pcm);
    }

    @Override
    @Transactional
    public void update(Pcm pcm) {
        pcmMapper.update(pcm);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        pcmMapper.delete(id);
    }

    @Override
    public Pcm findById(Integer id) {
        return pcmMapper.findById(id);
    }

    @Override
    public PageBean<Pcm> list(Integer pageNum, Integer pageSize, String dosageForm, String type, String pcmName) {
        //创建pageBean对象封装查询好的对象
        PageBean<Pcm> pb = new PageBean<>();

        //开启分页查询 PageHelper
        PageHelper.startPage(pageNum, pageSize);

        //调用mapper
        List<Pcm> ps = pcmMapper.list(dosageForm, type, pcmName);

        //Page中提供了方法，可以获取PageHelper分页查询后得到的总记录条数和当前页数据，如果不强转，多态不允许父类去调用子类特有的方法
        Page<Pcm> p = (Page<Pcm>) ps;

        //把数据填充到PageBean对象中
        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());
        return pb;
    }
}
