package org.herb.service;

import org.herb.pojo.Book;
import org.herb.pojo.PageBean;

public interface BookService {
    void add(Book book);

    void update(Book book);

    void delete(Integer id);

    Book findById(Integer id);

    PageBean<Book> list(Integer pageNum, Integer pageSize, String type, String bookName);
}
