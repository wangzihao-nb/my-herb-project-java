package org.herb.controller;

import org.herb.pojo.PageBean;
import org.herb.pojo.Post;
import org.herb.pojo.Result;
import org.herb.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    private PostService postService;

    //发帖
    @PostMapping
    public Result post(@RequestBody Post p){
        postService.post(p);
        return Result.success();
    }

    //删帖（自己和管理员删）
    @DeleteMapping
    public Result delete(@RequestParam Integer id){
        postService.delete(id);
        return Result.success();
    }

    //帖子列表（讨论区首页）（按照时间倒序排列）
    @GetMapping
    public Result<PageBean<Post>> list(@RequestParam Integer pageNum, Integer pageSize){
        PageBean<Post> pb = postService.list(pageNum, pageSize);
        return Result.success(pb);
    }

    //根据posterId和id查找帖子（我的发帖）
    @GetMapping("/myPost")
    public Result<PageBean<Post>> myPost(@RequestParam Integer pageNum, Integer pageSize, Integer posterId){
        PageBean<Post> pb = postService.myPost(pageNum, pageSize, posterId);
        return Result.success(pb);
    }

    //帖子详情
    @GetMapping("/detail")
    public Result<Post> detail(@RequestParam Integer id){
        Post post = postService.findById(id);
        return Result.success(post);
    }

    //浏览量+1
    @PutMapping
    public Result addView(@RequestParam Integer id){
        postService.addView(id);
        return Result.success();
    }

    //热门帖子列表，按浏览量排序，取前十条数据
    @GetMapping("hot")
    public Result<List<Post>> hotPost(){
        List<Post> ps = postService.hotPost();
        return Result.success(ps);
    }

    //根据userId删除所有帖子
    @DeleteMapping("deleteByUserId")
    public Result deleteByUserId(@RequestParam Integer userId){
        postService.deleteByUserId(userId);
        return Result.success();
    }

}
