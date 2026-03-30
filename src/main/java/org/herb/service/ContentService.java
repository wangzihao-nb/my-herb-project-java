package org.herb.service;

import org.herb.pojo.Chapter;
import org.herb.pojo.Content;

public interface ContentService {

    //添加文章
    void add(Content content);

    //编辑文章
    void update(Content content);


    //删除章节内容
    void delete(Integer id);

    //获取内容详情
    Content detail(Integer id);

    //根据内容id找到章节
    Chapter findChapter(Integer contentId);

    //根据内容找到内容id
    Content findIdByContent(String content);
}
