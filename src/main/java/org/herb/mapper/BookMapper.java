package org.herb.mapper;

import org.apache.ibatis.annotations.*;
import org.herb.pojo.Book;

import java.util.List;

@Mapper
public interface BookMapper {

    @Insert("insert into book(book_name,author,introduction,type,coll_num,cover_img) values(#{bookName},#{author},#{introduction},#{type},0,#{coverImg})")
    void add(Book book);

    @Update("update book set book_name=#{bookName},author=#{author},introduction=#{introduction},type=#{type},cover_img=#{coverImg} where id=#{id}")
    void update(Book book);

    @Delete("delete from book where id=#{id}")
    void delete(Integer id);

    @Select("select * from book where id=#{id}")
    Book findById(Integer id);

    //配置文件
    List<Book> list(String type, String bookName);
}
