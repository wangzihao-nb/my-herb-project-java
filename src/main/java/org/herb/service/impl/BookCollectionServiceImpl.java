package org.herb.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.herb.mapper.BookCollectionMapper;
import org.herb.pojo.Book;
import org.herb.pojo.BookCollection;
import org.herb.pojo.PageBean;
import org.herb.pojo.Prescription;
import org.herb.service.BookCollectionService;
import org.herb.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class BookCollectionServiceImpl implements BookCollectionService {
    @Autowired
    private BookCollectionMapper bookCollectionMapper;

    @Override
    @Transactional
    public void collect(Integer bookId) {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        bookCollectionMapper.collect(bookId, userId);
    }

    @Override
    @Transactional
    public void addCollNum(Integer id) {
        bookCollectionMapper.addCollNum(id);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        bookCollectionMapper.delete(id);
    }

    @Override
    @Transactional
    public void subtractCollNum(Integer id) {
        bookCollectionMapper.subtractCollNum(id);
    }

    @Override
    public BookCollection isCollect(Integer bookId, Integer userId) {
        return bookCollectionMapper.isCollect(bookId, userId);
    }

    @Override
    public PageBean<Book> list(Integer pageNum, Integer pageSize, Integer userId) {
        //创建pageBean对象封装查询好的对象
        PageBean<Book> pb = new PageBean<>();

        //开启分页查询 PageHelper
        PageHelper.startPage(pageNum, pageSize);

        //调用mapper
        List<Book> bs = bookCollectionMapper.list(userId);

        //Page中提供了方法，可以获取PageHelper分页查询后得到的总记录条数和当前页数据，如果不强转，多态不允许父类去调用子类特有的方法
        Page<Book> b = (Page<Book>) bs;

        //把数据填充到PageBean对象中
        pb.setTotal(b.getTotal());
        pb.setItems(b.getResult());
        return pb;
    }

    @Override
    @Transactional
    public void deleteByBookId(Integer bookId) {
        bookCollectionMapper.deleteByBookId(bookId);
    }

    @Override
    @Transactional
    public void deleteByUserId(Integer userId) {
        bookCollectionMapper.deleteByUserId(userId);
    }
}
