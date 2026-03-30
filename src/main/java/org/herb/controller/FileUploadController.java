package org.herb.controller;

import org.herb.pojo.Result;
import org.herb.utils.AliOssUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
public class FileUploadController {

    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file) throws Exception {
        //把文件内容存储到本地磁盘
        String originalFilename = file.getOriginalFilename();
        //要保证名字唯一，防止覆盖，使用uuid，UUID 是由标准化格式生成和表示的唯一标识符。
        String filename = UUID.randomUUID().toString() + originalFilename.substring(originalFilename.lastIndexOf("."));
        //file.transferTo(new File("C:\\Users\\huawei\\Desktop\\files\\" + filename));

        String url = AliOssUtil.uploadFile(filename, file.getInputStream());
        return Result.success(url);
    }
}
