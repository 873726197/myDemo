package com.own.common.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author liuChang
 * @date 2022-12-01 17:24
 * @describe
 **/
@Data
@Accessors(chain = true)
public class FileInfoDto {

    private String fileId;

    private String filePath;
}
