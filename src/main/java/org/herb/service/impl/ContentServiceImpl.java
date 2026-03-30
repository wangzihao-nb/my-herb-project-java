package org.herb.service.impl;

import org.herb.mapper.ContentMapper;
import org.herb.pojo.Chapter;
import org.herb.pojo.Content;
import org.herb.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private ContentMapper contentMapper;

    @Override
    public void add(Content content) {
        contentMapper.add(content);
    }

    @Override
    public void update(Content content) {
        contentMapper.update(content);
    }

    @Override
    public void delete(Integer id) {
        contentMapper.delete(id);
    }

    @Override
    public Content detail(Integer id) {
        return contentMapper.detail(id);
    }

    @Override
    public Chapter findChapter(Integer contentId) {
        return contentMapper.findChapter(contentId);
    }

    @Override
    public Content findIdByContent(String content) {
        return contentMapper.findIdByContent(content);
    }
}
