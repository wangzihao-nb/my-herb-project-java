package org.herb.controller;

import org.herb.pojo.*;
import org.herb.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.PortUnreachableException;
import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    //发布评论（前端传rank）
    @PostMapping
    public Result publish(@RequestBody Comment comment){
        commentService.publish(comment);
        return Result.success();
    }

    //删除评论（自己和管理员删）
    @DeleteMapping
    public Result delete(@RequestParam Integer id){
        commentService.delete(id);
        return Result.success();
    }

    //评论列表(按照rank显示，帖子下面默认显示的1级评论，点击展开显示2级和3级）
    @GetMapping
    public Result<List<Comment>> list(@RequestParam Integer postId){
        List<Comment> cs = commentService.list(postId); //返回一个集合，存放多个chapter对象
        return Result.success(cs);
    }

    //根据评论id找到评论（为了获取父评论的nickname）
    @GetMapping("/getCommentById")
    public Result<Comment> getCommentById(@RequestParam Integer id){
        Comment c = commentService.getCommentById(id);
        return Result.success(c);
    }

    //根据postId删除所有评论
    @DeleteMapping("/deleteByPostId")
    public Result deleteByPostId(@RequestParam Integer postId){
        commentService.deleteByPostId(postId);
        return Result.success();
    }

    //根据userId删除所有评论
    @DeleteMapping("deleteByUserId")
    public Result deleteByUserId(@RequestParam Integer userId){
        commentService.deleteByUserId(userId);
        return Result.success();
    }
}
