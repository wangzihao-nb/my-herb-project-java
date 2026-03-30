package org.herb.service;

import org.herb.pojo.PageBean;
import org.herb.pojo.Prescription;
import org.springframework.stereotype.Service;

public interface PrescriptionService {
    //添加方剂
    void add(Prescription prescription);

    //编辑方剂
    void update(Prescription prescription);

    void delete(Integer id);

    Prescription findById(Integer id);

    PageBean<Prescription> list(Integer pageNum, Integer pageSize, String dosageForm, String preName);

}
