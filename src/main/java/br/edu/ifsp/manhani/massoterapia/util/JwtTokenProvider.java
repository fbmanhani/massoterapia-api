package br.edu.ifsp.manhani.massoterapia.util;

import java.security.Key;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.ldap.userdetails.LdapUserDetailsImpl;
import org.springframework.stereotype.Component;

import br.edu.ifsp.manhani.massoterapia.config.JwtProperties;
import br.edu.ifsp.manhani.massoterapia.exception.BusinessException;
import br.edu.ifsp.manhani.massoterapia.messages.MessageProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtTokenProvider {

    @Autowired
    private JwtProperties jwtProperties;

    public String generateToken(Authentication authentication, Collection<? extends GrantedAuthority> aditionalRoles) {
        LdapUserDetailsImpl userPrincipal = (LdapUserDetailsImpl) authentication.getPrincipal();
        
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtProperties.getTokenExpTime());

        Key key = Keys.hmacShaKeyFor(jwtProperties.getJwtSecret().getBytes());
        String roles = getRoles(authentication.getAuthorities(), aditionalRoles);

        return Jwts.builder().setSubject(userPrincipal.getUsername()).setIssuedAt(new Date()).setExpiration(expiryDate)
                .claim(JwtProperties.ROLES_CLAIM, roles).signWith(key).compact();
    }

    private String getRoles(Collection<? extends GrantedAuthority> authorities,
            Collection<? extends GrantedAuthority> aditionalRoles) {
        String roles = StringUtils.join(AuthorityUtils.authorityListToSet(authorities), ", ");
        roles += StringUtils.join(AuthorityUtils.authorityListToSet(aditionalRoles), ", ");
        return roles;
    }

    public String getUsernameFromJWT(String token) {
        Claims claims = Jwts.parser().setSigningKey(jwtProperties.getJwtSecret()).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtProperties.getJwtSecret()).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {
            log.error("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty.");
        }
        return false;
    }

    public String generateToken(HttpServletRequest request) {
        String header = request.getHeader(JwtProperties.TOKEN_HEADER);

        if (header == null || !header.startsWith(JwtProperties.TOKEN_PREFIX)) {
            throw new BusinessException(MessageProperties.MSG0002);
        }

        String token = header.replace(JwtProperties.TOKEN_PREFIX, "");
        Claims claims = Jwts.parser().setSigningKey(jwtProperties.getJwtSecret().getBytes()).parseClaimsJws(token)
                .getBody();
        String username = claims.getSubject();
        if (username != null) {
            String roles = (String) claims.get("roles");
            Date now = new Date();
            Date expiryDate = new Date(now.getTime() + jwtProperties.getTokenExpTime());
            Key key = Keys.hmacShaKeyFor(jwtProperties.getJwtSecret().getBytes());
            return Jwts.builder().setSubject(username).setIssuedAt(new Date()).setExpiration(expiryDate)
                    .claim(JwtProperties.ROLES_CLAIM, roles).signWith(key).compact();
        }
        return null;
    }

    public String generateRefreshToken(HttpServletRequest request) {
        String header = request.getHeader(JwtProperties.TOKEN_HEADER);

        if (header == null || !header.startsWith(JwtProperties.TOKEN_PREFIX)) {
            throw new BusinessException(MessageProperties.MSG0002);
        }

        String token = header.replace(JwtProperties.TOKEN_PREFIX, "");
        Claims claims = Jwts.parser().setSigningKey(jwtProperties.getJwtSecret().getBytes()).parseClaimsJws(token)
                .getBody();
        String username = claims.getSubject();
        if (username != null) {
            String roles = (String) claims.get("roles");
            Date now = new Date();
            Date expiryDate = new Date(now.getTime() + jwtProperties.getRefreshTokenExpTime());
            Key key = Keys.hmacShaKeyFor(jwtProperties.getJwtSecret().getBytes());
            return Jwts.builder().setSubject(username).setIssuedAt(new Date()).setExpiration(expiryDate)
                    .claim(JwtProperties.ROLES_CLAIM, roles).signWith(key).compact();
        }
        return null;
    }

    public String generateRefreshToken(Authentication authentication,
            Collection<? extends GrantedAuthority> aditionalRoles) {
        LdapUserDetailsImpl userPrincipal = (LdapUserDetailsImpl) authentication.getPrincipal();

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtProperties.getRefreshTokenExpTime());

        Key key = Keys.hmacShaKeyFor(jwtProperties.getJwtSecret().getBytes());
        String roles = getRoles(authentication.getAuthorities(), aditionalRoles);

        return Jwts.builder().setSubject(userPrincipal.getUsername()).setIssuedAt(new Date()).setExpiration(expiryDate)
                .claim(JwtProperties.ROLES_CLAIM, roles).signWith(key).compact();
    }
}
