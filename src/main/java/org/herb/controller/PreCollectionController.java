package org.herb.controller;

import org.herb.pojo.PageBean;
import org.herb.pojo.PreCollection;
import org.herb.pojo.Prescription;
import org.herb.pojo.Result;
import org.herb.service.PreCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/preCollect")
public class PreCollectionController {

    @Autowired
    private PreCollectionService preCollectionService;

    //收藏方剂
    @PostMapping
    public Result collect(@RequestParam @Validated(PreCollection.Collect.class)Integer preId){
        preCollectionService.collect(preId);
        return Result.success();
    }

    //方剂表收藏数+1
    @PutMapping
    public Result addCollNum(@RequestParam @Validated(Prescription.AddColl.class) Integer preId){
        preCollectionService.addCollNum(preId);
        return Result.success();
    }

    //取消收藏
    @DeleteMapping
    public Result delete(@RequestParam Integer id){
        preCollectionService.delete(id);
        return Result.success();
    }

    //收藏数-1
    @PatchMapping
    public Result subtractCollNum(@RequestParam Integer preId){
        preCollectionService.subtractCollNum(preId);
        return Result.success();
    }

    //判断是否收藏（查找userId和preId都满足的）
    @GetMapping
    public Result<PreCollection> isCollect(@RequestParam Integer userId, Integer preId){
        PreCollection pc = preCollectionService.isCollect(userId, preId);
        return Result.success(pc);
    }

    //收藏列表
    @GetMapping("/list")
    public Result<PageBean<Prescription>> list(
            Integer pageNum,
            Integer pageSize,
            Integer userId
    ){
        PageBean<Prescription> pb = preCollectionService.list(pageNum, pageSize, userId);
        return Result.success(pb);
    }

    //根据id获得preId（没用上）
    @GetMapping("/getPreId")
    public Integer getPreIdById(@RequestParam Integer id){

        return preCollectionService.getPreIdById(id);
    }

    //删除收藏表里所有这个方剂的记录
    @DeleteMapping("/deleteByPreId")
    public Result deleteByPreId(@RequestParam Integer preId){
        preCollectionService.deleteByPreId(preId);
        return Result.success();
    }

    //根据userId删除所有记录
    @DeleteMapping("deleteByUserId")
    public Result deleteByUserId(@RequestParam Integer userId){
        preCollectionService.deleteByUserId(userId);
        return Result.success();
    }
}
