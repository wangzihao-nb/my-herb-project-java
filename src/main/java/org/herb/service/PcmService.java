package org.herb.service;

import org.herb.pojo.PageBean;
import org.herb.pojo.Pcm;

public interface PcmService {
    void add(Pcm pcm);

    void update(Pcm pcm);

    void delete(Integer id);

    Pcm findById(Integer id);

    PageBean<Pcm> list(Integer pageNum, Integer pageSize, String dosageForm, String type, String pcmName);

}
