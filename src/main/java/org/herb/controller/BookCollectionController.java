package org.herb.controller;

import org.herb.pojo.*;
import org.herb.service.BookCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookCollect")
public class BookCollectionController {
    @Autowired
    private BookCollectionService bookCollectionService;

    //添加到收藏表
    @PostMapping
    public Result collect(@RequestParam Integer bookId){
        bookCollectionService.collect(bookId);
        return Result.success();
    }

    //收藏数+1
    @PutMapping
    public Result addCollNum(@RequestParam Integer id){
        bookCollectionService.addCollNum(id);
        return Result.success();
    }

    //从收藏夹中删除
    @DeleteMapping
    public Result delete(@RequestParam Integer id){
        bookCollectionService.delete(id);
        return Result.success();
    }

    //收藏数-1
    @PatchMapping
    public Result subtractCollNum(@RequestParam Integer id){
        bookCollectionService.subtractCollNum(id);
        return Result.success();
    }

    //判断是否收藏
    @GetMapping
    public Result<BookCollection> isCollect(@RequestParam Integer bookId, Integer userId){
        BookCollection bc = bookCollectionService.isCollect(bookId, userId);
        return Result.success(bc);
    }

    //收藏列表
    @GetMapping("/list")
    public Result<PageBean<Book>> list(
            Integer pageNum,
            Integer pageSize,
            Integer userId
    ){
        PageBean<Book> bs = bookCollectionService.list(pageNum, pageSize, userId);
        return Result.success(bs);
    }

    //根据bookId删所有记录
    @DeleteMapping("/deleteByBookId")
    public Result deleteByBookId(@RequestParam Integer bookId){
        bookCollectionService.deleteByBookId(bookId);
        return Result.success();
    }

    //根据userId删所有记录
    @DeleteMapping("/deleteByUserId")
    public Result deleteByUserId(@RequestParam Integer userId){
        bookCollectionService.deleteByUserId(userId);
        return Result.success();
    }
}
