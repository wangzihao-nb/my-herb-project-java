package org.herb.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhipu.oapi.ClientV4;
import org.herb.pojo.PageBean;
import org.herb.pojo.Pcm;
import org.herb.pojo.Result;
import org.herb.service.PcmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/pcm")
public class PcmController {

    @Autowired
    private PcmService pcmService;

    /**-----管理员使用-----*/

    //1.新增中成药
    @PostMapping
    public Result add(@RequestBody @Validated(Pcm.Add.class) Pcm pcm){
        pcmService.add(pcm);
        return Result.success();
    }

    //2.修改中成药
    @PutMapping
    public Result update(@RequestBody @Validated(Pcm.Update.class) Pcm pcm) {
        pcmService.update(pcm);
        return Result.success();
    }

    //3.删除中药
    @DeleteMapping
    public Result delete(@RequestParam @Validated(Pcm.Delete.class) Integer id){
        pcmService.delete(id);
        return Result.success();
    }

    /**-----用户使用-----*/

    //1.展示详细信息（没用）
    @GetMapping("/detail")
    public Result<Pcm> detail(@RequestParam Integer id){
        Pcm pcm = pcmService.findById(id);
        return Result.success(pcm);
    }

    /**-----共用-----*/

    //中成药列表（包括根据中文名查询）（共用）
    @GetMapping
    public Result<PageBean<Pcm>> list(
            Integer pageNum,
            Integer pageSize,
            @RequestParam(required = false) String dosageForm, //剂型
            @RequestParam(required = false) String type, //类型
            @RequestParam(required = false) String pcmName
    ){
        PageBean<Pcm> pb = pcmService.list(pageNum,pageSize,dosageForm,type,pcmName);
        return Result.success(pb);
    }
}
