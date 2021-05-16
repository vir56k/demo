package com.example.api_gateway.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Set;

@Data
@Component
@ConfigurationProperties(prefix = "ignore.authorization")

public class IgnoreAuthorizationConfig {

    /**
     * 忽略 身份认证的 url列表
     */
    private Set<String> urlList;
}
