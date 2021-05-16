package com.example.api_gateway.filters;

import com.example.api_gateway.config.IgnoreAuthorizationConfig;
import com.example.api_gateway.services.AuthorizationClient1;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @description:
 * @author: zhangyunfei
 * @date: 2021/5/16 20:33
 */
@Slf4j
@Component
public class AuthorizationFilter extends AbstractGatewayFilterFactory<AuthorizationFilter.Config> {
    @Autowired
    private AuthorizationClient1 authorizationClient;

    @Autowired
    private IgnoreAuthorizationConfig ignoreAuthorizationConfig;

    public AuthorizationFilter() {
        super(AuthorizationFilter.Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            log.info("## 触发在 过滤器:AuthorizationFilter2");
            String targetUriPath = exchange.getRequest().getURI().getPath();
            if (isSkipAuth(targetUriPath)) {
                log.info("## 跳过 身份验证, targetUriPath={}", targetUriPath);
                return goNext(exchange, chain);
            }
            String token = exchange.getRequest().getHeaders().getFirst("token");
            if (token == null || token.isEmpty()) {
                log.info("## 无效的token = {}, targetUriPath= {}", token, targetUriPath);
                return responseInvalidToken(exchange, chain);
            }
            if (!verifyToken(token)) {
                log.info("## token 校验失败，参数 token = {}, targetUriPath= {}", token, targetUriPath);
                return responseInvalidToken(exchange, chain);
            }
            log.info("## token 校验通过! 参数 token = {}, targetUriPath= {}", token, targetUriPath);
            return chain.filter(exchange);
        };
    }

    /**
     * 验证 token 的合法性
     *
     * @param token
     * @return
     */
    private boolean verifyToken(String token) {
        try {
            String verifyToken = authorizationClient.verifyToken(token);
            log.info("## verifyToken, 参数token={}, result = {}", token, verifyToken);
            return verifyToken != null && !verifyToken.isEmpty();
        } catch (Exception ex) {
            ex.printStackTrace();
            log.info("## verifyToken,参数token={}, 发生异常 = {}", token, ex.toString());
            return false;
        }
    }

    /**
     * 返回响应：无效的 token
     *
     * @param exchange
     * @param chain
     * @return
     */
    private Mono<Void> responseInvalidToken(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("## responseInvalidToken");
        ServerHttpResponse response = exchange.getResponse();
        DataBuffer bodyDataBuffer = response.bufferFactory().wrap("{ \"code\":401, \"error\":\"token 无效\" }".getBytes());
        return response.writeWith(Mono.just(bodyDataBuffer));
    }

    /**
     * 是否跳过 认证检查
     *
     * @param targetUriPath 请求的资源 URI
     * @return
     */
    private boolean isSkipAuth(String targetUriPath) {
        boolean isSkip = ignoreAuthorizationConfig.getUrlList().contains(targetUriPath);
        log.info("## isSkip={}, ignoreAuthorizationConfig={}, targetUriPath={}", isSkip, ignoreAuthorizationConfig, targetUriPath);
        return isSkip;
    }

    private Mono<Void> goNext(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest req = exchange.getRequest();
        ServerHttpRequest request1 = req.mutate()
                .header("x-check-url", req.getURI().getPath())
                .build();
        return chain.filter(exchange.mutate().request(request1).build());
    }


    static class Config {

    }


}
