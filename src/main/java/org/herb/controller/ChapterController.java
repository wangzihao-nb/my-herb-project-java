package org.herb.controller;

import org.herb.pojo.*;
import org.herb.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chapter")
public class ChapterController {

    @Autowired
    private ChapterService chapterService;

    //添加章节
    @PostMapping
    public Result add(@RequestBody @Validated(Chapter.Add.class) Chapter chapter){
        chapterService.add(chapter);
        return Result.success();
    }

    //编辑章节
    @PutMapping
    public Result update(@RequestBody @Validated Chapter chapter){
        chapterService.update(chapter);
        return Result.success();
    }

    //删除章节
    @DeleteMapping
    public Result delete(@RequestParam @Validated Integer id){
        chapterService.delete(id);
        return Result.success();
    }

    //章节列表
    @GetMapping
    public Result<List<Chapter>> list(@RequestParam @Validated Integer bookId){
        List<Chapter> cs = chapterService.list(bookId); //返回一个集合，存放多个chapter对象
        return Result.success(cs);
    }

    //根据章节id找到章节（没用）
    @GetMapping("/findChapterById")
    public Result<Chapter> findChapterById(@RequestParam Integer chapterId){
        Chapter c = chapterService.findChapterById(chapterId);
        return Result.success(c);
    }

    //根据bookId删除所有章节
    @DeleteMapping("/deleteByBookId")
    public Result deleteByBookId(@RequestParam Integer bookId){
        chapterService.deleteByBookId(bookId);
        return Result.success();
    }
}
