package org.herb.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.herb.mapper.PrescriptionMapper;
import org.herb.pojo.PageBean;
import org.herb.pojo.Pcm;
import org.herb.pojo.Prescription;
import org.herb.service.PrescriptionService;
import org.herb.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class PrescriptionServiceImpl implements PrescriptionService {
    @Autowired
    private PrescriptionMapper prescriptionMapper;

    @Override
    @Transactional
    public void add(Prescription prescription) {
        prescriptionMapper.add(prescription);
    }

    @Override
    @Transactional
    public void update(Prescription prescription) {
        prescriptionMapper.update(prescription);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        prescriptionMapper.delete(id);
    }

    @Override
    public Prescription findById(Integer id) {
        return prescriptionMapper.findById(id);
    }

    @Override
    public PageBean<Prescription> list(Integer pageNum, Integer pageSize, String dosageForm, String preName) {
        //创建pageBean对象封装查询好的对象
        PageBean<Prescription> pb = new PageBean<>();

        //开启分页查询 PageHelper
        PageHelper.startPage(pageNum, pageSize);

        //调用mapper
        List<Prescription> ps = prescriptionMapper.list(dosageForm, preName);

        //Page中提供了方法，可以获取PageHelper分页查询后得到的总记录条数和当前页数据，如果不强转，多态不允许父类去调用子类特有的方法
        Page<Prescription> p = (Page<Prescription>) ps;

        //把数据填充到PageBean对象中
        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());
        return pb;
    }
}
