package org.herb.service;

import org.herb.pojo.Book;
import org.herb.pojo.Chapter;

import java.util.List;

public interface ChapterService {

    //添加章节
    void add(Chapter chapter);

    //编辑章节
    void update(Chapter chapter);

    //删除章节
    void delete(Integer id);

    //章节列表
    List<Chapter> list(Integer bookId);

    //根据章节id找到典籍
    Chapter findChapterById(Integer chapterId);

    void deleteByBookId(Integer bookId);
}
