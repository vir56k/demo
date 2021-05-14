//package com.example.api_gateway;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import com.google.gson.Gson;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.core.ParameterizedTypeReference;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//import org.springframework.util.LinkedMultiValueMap;
//import org.springframework.util.MultiValueMap;
//import org.springframework.web.client.RestTemplate;
//
//
///**
// * @author tongshujian
// * @date 2019/2/20
// */
//@Service
//public class AuthService {
//
//    @Autowired
//    private RestTemplate restTemplate;
//
//
//    public RestResult authority(String token) {
//        Gson gson = new Gson();
//        Map<String, String> reqMap = new HashMap<>();
//
//        MultiValueMap<String, String> header = new LinkedMultiValueMap<>();
//        header.add("x-user-id", userId);
//        header.add("x-app-code", userCenterConfig.getAppCode());
//        header.set("x-auth-token", token);
//        header.add("Content-Type", "application/json;charset=UTF-8");
//
//        String url = userCenterConfig.getHost() + "/authority";
//        HttpEntity entity = new HttpEntity(gson.toJson(reqMap), header);
//
//        ResponseEntity<RestResult> forEntity = restTemplate
//                .exchange(url, HttpMethod.POST, entity, new ParameterizedTypeReference<RestResult>() {
//                });
//        return forEntity.getBody();
//    }
//
//    public RestResult fistLoing(String userId, String uri, String method,String token) {
//        Gson gson = new Gson();
//        Map<String, String> reqMap = new HashMap<>();
//        reqMap.put("checkUrl", uri);
//        reqMap.put("method", method);
//
//        MultiValueMap<String, String> header = new LinkedMultiValueMap<>();
//        header.add("x-user-id", userId);
//        header.add("x-app-code", userCenterConfig.getAppCode());
//        header.set("x-auth-token", token);
//        header.add("Content-Type", "application/json;charset=UTF-8");
//
//        String url = userCenterConfig.getHost() + "/firstLogin";
//        HttpEntity entity = new HttpEntity(gson.toJson(reqMap), header);
//
//        ResponseEntity<RestResult> forEntity = restTemplate
//                .exchange(url, HttpMethod.POST, entity, new ParameterizedTypeReference<RestResult>() {
//                });
//        return forEntity.getBody();
//    }
//
//
//}
