package br.edu.ifsp.manhani.massoterapia.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;

@Getter
@Component
public final class JwtProperties {

    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String ROLES_CLAIM = "roles";
    public static final String CITY_CLAIM = "city";

    @Value("${jwt.jwtSecret}")
    private String jwtSecret;

    @Value("${jwt.tokenExpTime}")
    private int tokenExpTime;
    
    @Value("${jwt.refreshTokenExpTime}")
    private int refreshTokenExpTime;

}
