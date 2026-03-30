package org.herb.mapper;

import org.apache.ibatis.annotations.*;
import org.herb.pojo.Book;
import org.herb.pojo.Chapter;

import java.util.List;

@Mapper
public interface ChapterMapper {

    //添加章节信息
    @Insert("insert into chapter(title,book_id,content_id) values(#{title},#{bookId},#{contentId})")
    void add(Chapter chapter);

    //编辑章节信息
    @Update("update chapter set title=#{title},book_id=#{bookId},content_id=#{contentId} where id=#{id}")
    void update(Chapter chapter);

    //删除章节信息（应该先删chapter表，再删content表
    @Delete("delete from chapter where id=#{id}")
    void delete(Integer id);

    //章节列表
    @Select("select * from chapter where book_id=#{bookId}")
    List<Chapter> list(Integer bookId);

    //根据章节id找到章节
    @Select("select * from chapter where id=#{chapterId}")
    Chapter findChapterById(Integer chapterId);

    @Delete("delete from chapter where book_id=#{bookId}")
    void deleteByBookId(Integer bookId);
}
