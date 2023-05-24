package com.own.web.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author liuChang
 * @date 2023/5/19 15:17
 * @describe
 */

@Configuration
@ConfigurationProperties(prefix = "web")
@Data
public class WebConfig {

    private String name;
}
