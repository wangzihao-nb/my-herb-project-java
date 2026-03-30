package org.herb.controller;

import org.herb.pojo.*;
import org.herb.service.HerbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/herb")
public class HerbController {
    @Autowired
    private HerbService herbService;

    /**-----管理员使用-----*/

    //1.新增中药
    @PostMapping
    public Result add(@RequestBody @Validated(Herb.Add.class) Herb herb){
        herbService.add(herb);
        return Result.success();
    }

    //2.修改中药
    @PutMapping
    public Result update(@RequestBody @Validated(Herb.Update.class) Herb herb) {
        herbService.update(herb);
        return Result.success();
    }

    //3.删除中药
    @DeleteMapping
    public Result delete(@RequestParam @Validated(Herb.Delete.class) Integer id){
        herbService.delete(id);
        return Result.success();
    }

    /**-----用户使用-----*/

    //1.展示详细信息
    @GetMapping("/detail")
    public Result<Herb> detail(Integer id){
        Herb herb = herbService.findById(id);
        return Result.success(herb);
    }

    /**-----共用-----*/

    //中药列表（包括根据中文名查询）（共用）
    @GetMapping
    public Result<PageBean<Herb>> list(
            Integer pageNum,
            Integer pageSize,
            @RequestParam(required = false) String family,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String property,
            @RequestParam(required = false) String flavor,
            @RequestParam(required = false) String meridianTropism,
            @RequestParam(required = false) String cnName
    ){
        PageBean<Herb> pb = herbService.list(pageNum,pageSize,family,category,property,flavor,meridianTropism,cnName);
        return Result.success(pb);
    }
}
