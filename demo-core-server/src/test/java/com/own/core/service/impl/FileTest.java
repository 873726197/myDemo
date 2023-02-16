package com.own.core.service.impl;

import com.own.core.service.FileService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @author liuChang
 * @date 2022-12-02 14:40
 * @describe
 **/
@SpringBootTest
public class FileTest {

    @Resource
    private FileService fileService;

    private RestTemplate restTemplate = new RestTemplate();

    @Test
    public void fileTest() throws Exception {
        MultipartFile multipartFile = fileService.createMultipartFileByPath("E:\\Desktop\\1.txt");

        String url = "http://127.0.0.1:8881/user/uploadFile";
        HttpHeaders header = new HttpHeaders();
        header.set("Content-Type", MediaType.MULTIPART_FORM_DATA_VALUE);
        MultiValueMap<Object, Object> body = new LinkedMultiValueMap<>();
        body.add("file", new ByteArrayResource(multipartFile.getBytes()));
//        body.add("fileId", "1");
        HttpEntity<?> entity = new HttpEntity<>(body, header);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody());
    }
}
