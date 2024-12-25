package com.own.core.service.impl;

import com.own.core.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;

import static javax.swing.text.DefaultStyledDocument.ElementSpec.ContentType;

/**
 * @author liuChang
 * @date 2022-12-02 11:35
 * @describe
 **/
@Service
@Slf4j
public class FileServiceImpl implements FileService {

    @Override
    public MultipartFile createMultipartFileByPath(String path) {
        MultipartFile mFile = null;
        try {
            File file = new File(path);
            FileInputStream fileInputStream = new FileInputStream(file);

            String fileName = file.getName();
            fileName = fileName.substring((fileName.lastIndexOf("/") + 1));
            mFile =  new MockMultipartFile(fileName, fileInputStream);
        } catch (Exception e) {
            log.error("封装文件出现错误：{}", e);
            //e.printStackTrace();
        }
        return mFile;
    }

}
