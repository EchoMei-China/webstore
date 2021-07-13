package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;

@Controller
@RequestMapping("/fileupload")
public class FileUpload {
    @RequestMapping("/BookPictureUpload")
    @ResponseBody

    public File uploadFile(@RequestParam("file") MultipartFile file){
        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf('.'));
        String newFileName = new Date().getTime() + suffix;
        String path = "Documents/webstore/";
        File newFile = new File(path +"/" + newFileName);
        try {
            file.transferTo(newFile);
            return newFile;
        }
        catch (Exception e){
            e.printStackTrace();
            return newFile;
        }
    }



}
