package org.example.websocketserver.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Objects;

@Component
@RequiredArgsConstructor
@Slf4j
public class SocketAuthorizationInterceptor implements HandshakeInterceptor {

    @Override
    public boolean beforeHandshake(
            ServerHttpRequest request,
            ServerHttpResponse response,
            WebSocketHandler wsHandler,
            Map<String, Object> attributes) {
        HttpServletRequest origRequest = ((ServletServerHttpRequest) request).getServletRequest();
        String userId = origRequest.getHeader("User-Id");
        boolean success = !Objects.isNull(userId);
        if (!success) {
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
        } else {
            attributes.put("User-Id", userId);
        }
        log.info("beforeHandshake: {}", success);
        int status = ((ServletServerHttpResponse) response).getServletResponse().getStatus();
        log.info("response code: {}", status);
        return success;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request,
                               ServerHttpResponse response,
                               WebSocketHandler wsHandler,
                               Exception exception) {
    }

}
