package com.ccll.controller;

import com.ccll.common.result.Result;
import com.ccll.common.utils.AliOssUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
public class UploadConteoller {

    @Autowired
    private AliOssUtil aliOssUtil;

    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws IOException {
        log.info("文件上传{}", file);

        try {
            //获取文件原始名
            String originalFilename = file.getOriginalFilename();
            //截取文件后缀
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            //构建新文件名
            String fileName = UUID.randomUUID().toString() + extension;
            //调用工具类上传文件到OSS，并返回请求路径
            String fileUrl = aliOssUtil.upload(file.getBytes(), fileName);
            log.info("<UNK>{}", fileUrl);
            return Result.success(fileUrl);
        } catch (IOException e) {
            log.error("文件上传失败：{}", e.getMessage());
        }
        return Result.success();
    }
}
