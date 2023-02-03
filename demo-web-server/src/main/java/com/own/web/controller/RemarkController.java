package com.own.web.controller;

import cn.hutool.core.io.file.FileReader;
import com.own.web.annotation.RemarkAnnotation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author liuChang
 * @date 2023/2/3 15:47
 * @describe
 */

@RestController
@RequestMapping("/remark")
public class RemarkController {

    @RemarkAnnotation(remark = "remark1")
    @RequestMapping("/remark1")
    public String remark1() {
        FileReader fileReader = new FileReader("d:/desktop/2.txt");
        List<String> strings = fileReader.readLines();
        StringBuilder sb = new StringBuilder();
        strings.forEach(s->{
            sb.append(s);
            sb.append("'\n");
        });
        return sb.toString();
    }

    @RemarkAnnotation(remark = "remark2")
    @RequestMapping("/remark2")
    public String remark2() {
        return "11111 \n 22222";
    }
}
