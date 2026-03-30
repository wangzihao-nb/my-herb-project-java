package org.herb.mapper;

import org.apache.ibatis.annotations.*;
import org.herb.pojo.Book;
import org.herb.pojo.BookCollection;

import java.util.List;

@Mapper
public interface BookCollectionMapper {
    @Insert("insert into book_collection(book_id,user_id,create_time) values(#{bookId},#{userId},now()) ")
    void collect(Integer bookId, Integer userId);

    @Update("update book set coll_num=coll_num+1 where id=#{id}")
    void addCollNum(Integer id);

    @Delete("delete from book_collection where id=#{id}")
    void delete(Integer id);

    @Update("update book set coll_num=coll_num-1 where id=#{id}")
    void subtractCollNum(Integer id);

    @Select("select * from book_collection where book_id=#{bookId} and user_id=#{userId}")
    BookCollection isCollect(Integer bookId, Integer userId);

    @Select("select * from book inner join book_collection on book_collection.book_id=book.id where user_id=#{userId} order by create_time desc")
    List<Book> list(Integer userId);

    @Delete("delete from book_collection where book_id=#{bookId}")
    void deleteByBookId(Integer bookId);

    @Delete("delete from book_collection where user_id=#{userId}")
    void deleteByUserId(Integer userId);
}
