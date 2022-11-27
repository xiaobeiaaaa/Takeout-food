package com.codermast.takeoutfood.controller;

import com.codermast.takeoutfood.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @Description: 文件上传下载控制器
 * @author: CoderMast
 * @date: 2022/11/27
 * @Blog: <a href="https://www.codermast.com/">codermast</a>
 */
@Slf4j
@RestController
@RequestMapping("/common")
public class CommonController {

    @Value("${takeout-food.images}")
    private String imagesUrl;

    /**
     * @param file 上传的文件
     * @Description: 文件上传
     * @Author: CoderMast <a href="https://www.codermast.com/">...</a>
     */
    @PostMapping("/upload")
    public R<String> upload(MultipartFile file) {
        log.info("已经接收到文件，文件名为：" + imagesUrl + file.getOriginalFilename());
        // 获取文件扩展名
        String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        log.info(extension);

        // 创建指定目录的文件对象
        File dir = new File(imagesUrl);
        // 判断目录是否存在
        if (!dir.exists()) {
            // 不存在时，应该创建对应的目录，这里使用的是mkdirs方法，一次性创建多级目录。
            dir.mkdirs();
        }

        // 生成文件名
        String uuidFileName = UUID.randomUUID().toString();

        // 完整的文件名
        String fileName = imagesUrl + uuidFileName + extension;


        // 构建文件对象
        File image = new File(fileName);

        // 此时目录已经存在，将文件转存到该地址
        try {
            file.transferTo(image);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return R.success(fileName);
    }

    /**
     * @param name
     * @Description: 文件下载
     * @Author: CoderMast <a href="https://www.codermast.com/">...</a>
     */
    @GetMapping("/download")
    public R<String> download(String name) {
        String imgUrl = "/backend/images/" + name;
        return R.success(imgUrl);
    }
}
