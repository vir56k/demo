//package eureka_client.demo;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.ParameterizedTypeReference;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//import org.springframework.util.LinkedMultiValueMap;
//import org.springframework.web.client.RestTemplate;
//
//import java.lang.reflect.MalformedParameterizedTypeException;
//
//
///**
// * @author tongshujian
// * @date 2019/2/20
// */
//@Slf4j
//@Service
//public class AuthorizationClient {
//
//    @Autowired
//    private RestTemplate restTemplate;
//
//    //    private static final String URL_VERIFY_TOKEN = "http://auth-service/verifytoken";
//    private static final String URL_VERIFY_TOKEN = "http://127.0.0.1:8082/verifytoken";
//
//    public String verifyToken(String token) {
//        log.info("## 准备执行：verifyToken");
//
//        HttpHeaders headers = new HttpHeaders();
//        LinkedMultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<>();
//        HttpEntity entity = new HttpEntity<>(paramMap, headers);
//        paramMap.add("token", token);
//        String url = URL_VERIFY_TOKEN;
//        ResponseEntity<String> forEntity = restTemplate
//                .exchange(url, HttpMethod.POST, entity, new ParameterizedTypeReference<String>() {
//                });
//        return forEntity.getBody();
//
////        Map<String, String> paras = new LinkedHashMap<>();
////        paras.put("token", token);
////        RequestEntity requestEntity = RequestEntity
////                .post(URI.create(URL_VERIFY_TOKEN))
////                .accept(MediaType.APPLICATION_JSON)
////                .body(paras);
////        ResponseEntity<String> exchange = restTemplate.exchange(requestEntity, String.class);
////        String result = exchange.getBody();
////
////        log.info("## 执行结束：verifyToken，结果=" + result);
////        return result;
//    }
//
//}
