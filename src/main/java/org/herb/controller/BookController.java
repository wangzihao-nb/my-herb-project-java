package org.herb.controller;

import org.herb.pojo.Book;
import org.herb.pojo.Herb;
import org.herb.pojo.PageBean;
import org.herb.pojo.Result;
import org.herb.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    //添加书籍
    @PostMapping
    public Result add(@RequestBody @Validated(Book.Add.class) Book book){
        bookService.add(book);
        return Result.success();
    }

    //修改书籍信息
    @PutMapping
    public Result update(@RequestBody @Validated(Book.Update.class) Book book) {
        bookService.update(book);
        return Result.success();
    }

    //删除书籍
    @DeleteMapping
    public Result delete(@RequestParam @Validated(Book.Delete.class) Integer id){
        bookService.delete(id);
        return Result.success();
    }

   //展示详细信息
    @GetMapping("/detail")
    public Result<Book> detail(Integer id){
        Book book = bookService.findById(id);
        return Result.success(book);
    }

    //书籍列表
    @GetMapping
    public Result<PageBean<Book>> list(
            Integer pageNum,
            Integer pageSize,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String bookName
    ){
        PageBean<Book> pb = bookService.list(pageNum,pageSize,type,bookName);
        return Result.success(pb);
    }

}
