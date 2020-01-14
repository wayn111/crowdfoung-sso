package com.wayn.ssoserver.manager;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.wayn.ssocore.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Component
public class TokenManager {

    @Value("${server.servlet.session.timeout}")
    private int timeout;

    private Cache<String, Object> cache;

    @PostConstruct
    public void init() {
        cache = CacheBuilder.newBuilder()
                .maximumSize(1000)
                .expireAfterAccess(timeout, TimeUnit.MINUTES)
                .build();
    }

    public boolean validateToken(String token) {
        return Objects.nonNull(cache.getIfPresent(token));
    }

    public void addToken(String token, User user) {
        cache.put(token, user);
    }

    public void removeToken(String token) {
        cache.invalidate(token);
    }

    public User getUserByToken(String token) {
        return (User) cache.getIfPresent(token);
    }

    public String generateToken() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public Map<String, Object> getAllUser() {
        return cache.asMap();
    }
}
