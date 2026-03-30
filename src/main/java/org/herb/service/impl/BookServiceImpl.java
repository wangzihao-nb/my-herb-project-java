package org.herb.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.herb.mapper.BookMapper;
import org.herb.pojo.Book;
import org.herb.pojo.Herb;
import org.herb.pojo.PageBean;
import org.herb.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper bookMapper;

    @Override
    @Transactional
    public void add(Book book) {
        bookMapper.add(book);
    }

    @Override
    @Transactional
    public void update(Book book) {
        bookMapper.update(book);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        bookMapper.delete(id);
    }

    @Override
    public Book findById(Integer id) {
        return bookMapper.findById(id);
    }

    @Override
    public PageBean<Book> list(Integer pageNum, Integer pageSize, String type, String bookName) {
        //创建pageBean对象封装查询好的对象
        PageBean<Book> pb = new PageBean<>();

        //开启分页查询 PageHelper
        PageHelper.startPage(pageNum, pageSize);

        //调用mapper
        List<Book> bs = bookMapper.list(type, bookName);

        //Page中提供了方法，可以获取PageHelper分页查询后得到的总记录条数和当前页数据，如果不强转，多态不允许父类去调用子类特有的方法
        Page<Book> p = (Page<Book>) bs;

        //把数据填充到PageBean对象中
        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());
        return pb;
    }
}
