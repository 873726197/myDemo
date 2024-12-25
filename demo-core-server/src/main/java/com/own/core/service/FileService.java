package com.own.core.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author liuChang
 * @date 2022-12-02 11:33
 * @describe
 **/
public interface FileService {
    MultipartFile createMultipartFileByPath(String path);

}
