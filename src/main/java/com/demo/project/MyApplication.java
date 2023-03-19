package com.demo.project;

import com.demo.project.controller.CommonController;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;

@SpringBootApplication
@MapperScan("com.demo.project.mapper")
public class MyApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class, args);
        CommonController controller = new CommonController();
        System.out.println("coming...");
    }

}
