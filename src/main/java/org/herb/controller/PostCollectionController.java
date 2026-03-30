package org.herb.controller;

import org.herb.pojo.*;
import org.herb.service.PostCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/postCollect")
public class PostCollectionController {
    @Autowired
    private PostCollectionService postCollectionService;

    //添加到收藏夹
    @PostMapping
    public Result collect(@RequestParam Integer postId){
        postCollectionService.collect(postId);
        return Result.success();
    }

    //帖子收藏数+1
    @PutMapping
    public Result addCollNum(@RequestParam Integer postId){
        postCollectionService.addCollNum(postId);
        return Result.success();
    }

    //取消收藏
    @DeleteMapping
    public Result delete(@RequestParam Integer id){
        postCollectionService.delete(id);
        return Result.success();
    }

    //帖子收藏数-1
    @PatchMapping
    public Result subtractCollNum(@RequestParam Integer postId){
        postCollectionService.subtractCollNum(postId);
        return Result.success();
    }

    //判断是否收藏（查找userId和preId都满足的）
    @GetMapping
    public Result<Post> isCollect(@RequestParam Integer postId, Integer userId){
        Post pc = postCollectionService.isCollect(postId, userId);
        return Result.success(pc);
    }

    //收藏列表
    @GetMapping("/list")
    public Result<PageBean<Post>> list(
            Integer pageNum,
            Integer pageSize,
            Integer userId
    ){
        PageBean<Post> pb = postCollectionService.list(pageNum, pageSize, userId);
        return Result.success(pb);
    }

    //根据postId删除所有记录
    @DeleteMapping("/deleteByPostId")
    public Result deleteByPostId(@RequestParam Integer postId){
        postCollectionService.deleteByPostId(postId);
        return Result.success();
    }

    //根据userId删除所有记录
    @DeleteMapping("/deleteByUserId")
    public Result deleteByUserId(@RequestParam Integer userId){
        postCollectionService.deleteByUserId(userId);
        return Result.success();
    }
}
