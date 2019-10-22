package com.jiahao.controller;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
public class UploadController {

    @RequestMapping("upload")
    public String upload(HttpServletRequest request, @RequestParam CommonsMultipartFile photo) throws IOException {
        //原文件名

        String oname = photo.getOriginalFilename();
        String substring = oname.substring(oname.lastIndexOf("."));
        //新文件名
        String fileName = UUID.randomUUID().toString().replace("-", "") + substring;

        //工程相对路径
        String path = request.getServletContext().getRealPath("/upload/") + fileName;

        System.out.println(path);

        photo.transferTo(new File(path));
        System.out.println("上传成功...");
        return "upload";
    }

    /**
     * 多文件上传
     * @param request
     * @param photo
     * @return
     * @throws IOException
     */
    @RequestMapping("upload2")
    public String upload2(HttpServletRequest request, @RequestParam CommonsMultipartFile[] photo) throws IOException {
        //原文件名

        if(photo.length > 0){
            for (int i = 0 ; i < photo.length ; i++){
                String oname = photo[i].getOriginalFilename();
                String substring = oname.substring(oname.lastIndexOf("."));
                //新文件名
                String fileName = UUID.randomUUID().toString().replace("-", "") + substring;

                //工程相对路径
                String path = request.getServletContext().getRealPath("/upload/") + fileName;

                System.out.println(path);

                photo[i].transferTo(new File(path));
                System.out.println("上传成功...");
            }
        }
        return "upload";
    }
}
