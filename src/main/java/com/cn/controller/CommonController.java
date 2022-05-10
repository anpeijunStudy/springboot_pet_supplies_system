package com.cn.controller;

import com.cn.code.Code;
import com.cn.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

/**
 * @author 安珮军
 * @version 1.0
 * @Description:
 */
@Slf4j
@RestController
@RequestMapping("/common")
public class CommonController {


    @Value("${reggie.path}")
    private String basePath;

    /**
     * 文件上传
     *
     * @param file MultipartFile参数名必须和前端的一致
     * @return
     */
    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws IOException {
        log.info("图片信息{}" + file.toString());
        // 获得原始文件名-可以使用但是会用重名的概率-文件覆盖
        String originalFilename = file.getOriginalFilename();
        // 获得后缀名
        String substring = originalFilename.substring(originalFilename.lastIndexOf("."));
        // file是一个临时文件-需要转存到指定位置

        // 文件是否存在-不存在创建
        File fileExits = new File(basePath);
        if (!fileExits.exists()) {
            fileExits.mkdirs();
        }
        // UUID随机生成文件名
        String fileName = UUID.randomUUID().toString();
        file.transferTo(new File(basePath + fileName + substring));
        return new Result(Code.POST_OK, fileName + substring, "添加成功");
    }

    /**
     * 文件下载
     *
     * @param name     文件名
     * @param response 直接通过流写出
     */
    @GetMapping("/download")
    public void download(String name, HttpServletResponse response) {
        FileInputStream fileInputStream = null;
        ServletOutputStream outputStream = null;
        try {
            // 读取文件
            fileInputStream = new FileInputStream(new File(basePath + name));
            // 输出文件
            outputStream = response.getOutputStream();

            response.setContentType("image/jpeg");
            int len = 0;
            byte[] bytes = new byte[1024];
            while ((len = fileInputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, len);
                outputStream.flush();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally { // 关闭流
            try {
                fileInputStream.close();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
