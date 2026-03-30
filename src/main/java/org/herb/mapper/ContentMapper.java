package org.herb.mapper;

import org.apache.ibatis.annotations.*;
import org.herb.pojo.Chapter;
import org.herb.pojo.Content;

@Mapper
public interface ContentMapper {

    @Insert("insert into content(book_content) values(#{bookContent})")
    void add(Content content);

    @Update("update content set book_content=#{bookContent} where id=#{id}")
    void update(Content content);

    @Delete("delete from content where id=#{id}")
    void delete(Integer id);

    @Select("select * from content where id=#{id}")
    Content detail(Integer id);

    @Select("select * from chapter where content_id=#{contentId}")
    Chapter findChapter(Integer contentId);

    @Select("select * from content where book_content=#{content}")
    Content findIdByContent(String content);
}
