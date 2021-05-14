//package com.example.api_gateway;
//
//import java.util.Set;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.cloud.gateway.filter.GatewayFilter;
//import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
//import org.springframework.core.io.buffer.DataBuffer;
//import org.springframework.http.server.reactive.ServerHttpRequest;
//import org.springframework.http.server.reactive.ServerHttpResponse;
//import org.springframework.stereotype.Component;
//
//import com.google.gson.Gson;
//import com.navinfo.spf.common.result.RestResult;
//import com.navinfo.spf.gateway.config.IgnoreConfig;
//import com.navinfo.spf.gateway.config.UserCenterConfig;
//import com.navinfo.spf.gateway.pre.AuthorityFilter.Config;
//import com.navinfo.spf.gateway.service.AuthService;
//
//import reactor.core.publisher.Mono;
//
///**
// * @author tongshujian
// * @date 2019/3/28
// */
//@Component
//public class AuthorityFilter extends AbstractGatewayFilterFactory<AuthorityFilter.Config> {
//
//    @Autowired
//    private AuthService authService;
//
//    public AuthorityFilter() {
//        super(Config.class);
//    }
//
//    @Override
//    public GatewayFilter apply(Config config) {
//        return (exchange, chain) -> {
//
//            ServerHttpRequest request = exchange.getRequest();
//            String uri = request.getHeaders().get("x-check-url").get(0);
//          //不需要登陆拦截
//            Set<String> urls = ignoreConfig.getLoginUris();
//            for(String url : urls){
//            	if(url.endsWith("/**") && uri.indexOf(url.substring(0, url.length()-3)) >= 0){
//            		return chain.filter(exchange.mutate().request(request).build());
//            	}
//            }
//            //不需要登陆拦截
//            if (ignoreConfig.getLoginUris().contains(uri)
//                    || request.getURI().getPath().indexOf("sock-js") > -1 || uri.indexOf("/auth") > -1
//            ) {
//                return chain.filter(exchange.mutate().request(request).build());
//            } else {
//                //调用用户中心进行权限校验
//                ServerHttpResponse response = exchange.getResponse();
//
//                String userId = request.getHeaders().get("x-user-id").get(0);
//                String token = request.getHeaders().get("x-auth-token").get(0);
//                String method = request.getMethod().name();
//
//                RestResult restResult = authService.authority(userId, uri, method,token);
//                if (RestResult.CODE_SUCCESS.equals(restResult.getCode())) {
//                    return chain.filter(exchange.mutate().request(request).build());
//                } else {
//                    response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
//                    Gson gson = new Gson();
//                    DataBuffer bodyDataBuffer = response.bufferFactory().wrap(gson.toJson(restResult).getBytes());
//                    return response.writeWith(Mono.just(bodyDataBuffer));
//                }
//            }
//        };
//    }
//
//
//    public static class Config {
//
//    }
//
//}
