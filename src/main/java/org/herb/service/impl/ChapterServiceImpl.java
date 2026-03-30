package org.herb.service.impl;

import org.herb.mapper.ChapterMapper;
import org.herb.pojo.Book;
import org.herb.pojo.Chapter;
import org.herb.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ChapterServiceImpl implements ChapterService {

    @Autowired
    private ChapterMapper chapterMapper;

    @Override
    @Transactional
    public void add(Chapter chapter) {
        chapterMapper.add(chapter);
    }

    @Override
    @Transactional
    public void update(Chapter chapter) {
        chapterMapper.update(chapter);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        chapterMapper.delete(id);
    }

    @Override
    public List<Chapter> list(Integer bookId) {
        return chapterMapper.list(bookId);
    }

    @Override
    public Chapter findChapterById(Integer chapterId) {
        return chapterMapper.findChapterById(chapterId);
    }

    @Override
    @Transactional
    public void deleteByBookId(Integer bookId) {
        chapterMapper.deleteByBookId(bookId);
    }
}
