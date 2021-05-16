//package com.example.api_gateway.services.impl;
//
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//
///**
// * @description:
// * @author: zhangyunfei
// * @date: 2021/5/16 22:47
// */
//@FeignClient(value = "auth-service")
//public interface AuthorizationClient2  {
////     "http://127.0.0.1:8082/verifytoken";
//
//    @RequestMapping(method = RequestMethod.POST, value = "/verifytoken")
//    String verifyToken(@RequestParam String token);
//
//}