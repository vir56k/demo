package com.example.api_gateway.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;


/**
 * @author zhangyunfei
 * @date 2019/2/20
 */
@Slf4j
@Service
public class AuthorizationClient1 {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 备注：
     * 1、如果使用 RestTemplate LoadBalanced, 则触发异常: blockLast() are blocking, which is not supported in thread reactor-http-nio-3
     * 2、so，只能 停止 LoadBalanced，写死一个 ip
     */

//        private static final String URL_VERIFY_TOKEN = "http://auth-service/verifytoken";
    private static final String URL_VERIFY_TOKEN = "http://127.0.0.1:8082/verifytoken";

    public String verifyToken(String token) {
        log.info("## verifyToken 准备执行：verifyToken");

        HttpHeaders headers = new HttpHeaders();
        LinkedMultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<>();
        HttpEntity entity = new HttpEntity<>(paramMap, headers);
        paramMap.add("token", token);
        String url = URL_VERIFY_TOKEN;
        ResponseEntity<String> forEntity = restTemplate
                .exchange(url, HttpMethod.POST, entity, new ParameterizedTypeReference<String>() {
                });
        HttpStatus statusCode = forEntity.getStatusCode();
        String res = forEntity.getBody();
        log.info("## verifyToken 执行结束：verifyToken，statusCode={}, 结果={}", statusCode, res);
        return res;
    }

}
