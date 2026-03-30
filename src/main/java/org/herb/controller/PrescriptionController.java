package org.herb.controller;

import org.herb.pojo.PageBean;
import org.herb.pojo.Pcm;
import org.herb.pojo.Prescription;
import org.herb.pojo.Result;
import org.herb.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/prescription")
public class PrescriptionController {

    @Autowired
    private PrescriptionService prescriptionService;

    /**-----管理员使用-----*/

    //1.新增方剂
    @PostMapping
    public Result add(@RequestBody @Validated(Prescription.Add.class)Prescription prescription){
        prescriptionService.add(prescription);
        return Result.success();
    }
    //2.编辑方剂
    @PutMapping
    public Result update(@RequestBody @Validated(Prescription.Update.class)Prescription prescription){
        prescriptionService.update(prescription);
        return Result.success();
    }
    //3.删除方剂
    @DeleteMapping
    public Result delete(@RequestParam @Validated(Prescription.Delete.class) Integer id) {
        prescriptionService.delete(id);
        return Result.success();
    }
    /**-----用户使用-----*/

    //1.展示详细信息
    @GetMapping("/detail")
    public Result<Prescription> detail(Integer id){
        Prescription prescription = prescriptionService.findById(id);
        return Result.success(prescription);
    }

    /**-----共用-----*/

    //方剂列表
    @GetMapping
    public Result<PageBean<Prescription>> list(
            Integer pageNum,
            Integer pageSize,
            @RequestParam(required = false) String dosageForm, //剂型
            @RequestParam(required = false) String preName //根据方剂名查询
    ){
        PageBean<Prescription> pb = prescriptionService.list(pageNum, pageSize, dosageForm, preName);
        return Result.success(pb);
    }
}
