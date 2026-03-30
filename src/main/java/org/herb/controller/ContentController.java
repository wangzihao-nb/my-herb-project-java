package org.herb.controller;

import org.herb.pojo.Chapter;
import org.herb.pojo.Content;
import org.herb.pojo.Result;
import org.herb.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/content")
public class ContentController {

    @Autowired
    private ContentService contentService;

    //新增章节内容
    @PostMapping
    public Result add(@RequestBody @Validated Content content){
        contentService.add(content);
        return Result.success();
    }

    //编辑章节内容
    @PutMapping
    public Result update(@RequestBody @Validated Content content){
        contentService.update(content);
        return Result.success();
    }

    //删除章节内容
    @DeleteMapping
    public Result delete(@RequestParam Integer id){
        contentService.delete(id);
        return Result.success();
    }

    //获取内容详情
    @GetMapping
    public Result<Content> detail(@RequestParam Integer id){
        Content content =  contentService.detail(id);
        return Result.success(content);
    }

    //根据内容id找到章节
    @GetMapping("/findChapter")
    public Result<Chapter> findChapter(@RequestParam Integer contentId){
        Chapter c = contentService.findChapter(contentId);
        return Result.success(c);
    }

    //根据内容找id
    @GetMapping("/findIdByContent")
    public Result<Content> findIdByContent(@RequestParam String content){
        Content c = contentService.findIdByContent(content);
        return Result.success(c);
    }
}
