package org.herb.service;

import org.herb.pojo.Herb;
import org.herb.pojo.PageBean;

public interface HerbService {

    //管理员功能

    //1.添加中药
    void add(Herb herb);

    //编辑中药
    void update(Herb herb);

    //删除中药
    void delete(Integer id);

    //中药列表
    PageBean<Herb> list(Integer pageNum, Integer pageSize, String family, String category, String property, String flavor, String meridianTropism, String cnName);

    //展示中药详细信息
    Herb findById(Integer id);
}
