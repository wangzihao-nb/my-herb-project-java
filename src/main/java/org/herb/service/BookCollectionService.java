package org.herb.service;

import org.herb.pojo.Book;
import org.herb.pojo.BookCollection;
import org.herb.pojo.PageBean;

public interface BookCollectionService  {

    void collect(Integer bookId);

    void addCollNum(Integer id);

    void delete(Integer id);

    void subtractCollNum(Integer id);

    BookCollection isCollect(Integer bookId, Integer userId);

    PageBean<Book> list(Integer pageNum, Integer pageSize, Integer userId);

    void deleteByBookId(Integer bookId);

    void deleteByUserId(Integer userId);
}
