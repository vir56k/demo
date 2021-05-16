package com.example.api_gateway.filters;

import com.fasterxml.jackson.core.filter.TokenFilter;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @description: 用户鉴权：某个人能否访问某个 URI资源
 * @author: zhangyunfei
 * @date: 2021/5/14 18:37
 */
@Slf4j
public class AuthorityFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String token = exchange.getRequest().getHeaders().getFirst("token");
        if (token == null || token.isEmpty()) {
            log.info("token is empty...");
            ServerHttpResponse response = exchange.getResponse();
            DataBuffer bodyDataBuffer = response.bufferFactory().wrap("{ \"code\":401, \"error\":\"token is empty\" }".getBytes());
            return response.writeWith(Mono.just(bodyDataBuffer));
//            return exchange.getResponse().setComplete();
        }
        log.info("# 触发在 全局过滤器");
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -1;
    }
}