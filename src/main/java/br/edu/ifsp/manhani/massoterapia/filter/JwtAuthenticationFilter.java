package br.edu.ifsp.manhani.massoterapia.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.edu.ifsp.manhani.massoterapia.config.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private JwtProperties jwtProperties;

	public JwtAuthenticationFilter(JwtProperties jwtProperties) {
		this.jwtProperties = jwtProperties;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {

		String header = request.getHeader(JwtProperties.TOKEN_HEADER);

		if (header == null || !header.startsWith(JwtProperties.TOKEN_PREFIX)) {
			chain.doFilter(request, response); // If not valid, go to the next filter.
			return;
		}

		String token = header.replace(JwtProperties.TOKEN_PREFIX, "");

		try {
			Claims claims = Jwts.parser().setSigningKey(jwtProperties.getJwtSecret().getBytes()).parseClaimsJws(token)
					.getBody();

			String username = claims.getSubject();
			if (username != null) {
				String roles = (String) claims.get("roles");
				List<String> authorities = Arrays.asList(roles.split(", "));

				UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username, null,
						authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));

				SecurityContextHolder.getContext().setAuthentication(auth);
			}
		} catch (ExpiredJwtException exception) {
			log.warn("Expired JWT : {} failed : {}", token, exception.getMessage());
			SecurityContextHolder.clearContext();
		} catch (UnsupportedJwtException exception) {
			log.warn("Unsupported JWT : {} failed : {}", token, exception.getMessage());
			SecurityContextHolder.clearContext();
		} catch (MalformedJwtException exception) {
			log.warn("Invalid JWT : {} failed : {}", token, exception.getMessage());
			SecurityContextHolder.clearContext();
		} catch (SignatureException exception) {
			log.warn("JWT with invalid signature : {} failed : {}", token, exception.getMessage());
			SecurityContextHolder.clearContext();
		} catch (IllegalArgumentException exception) {
			log.warn("Empty or null JWT : {} failed : {}", token, exception.getMessage());
			SecurityContextHolder.clearContext();
		} catch (Exception e) {
			log.warn(e.getMessage(), e);
			SecurityContextHolder.clearContext();
		}
		chain.doFilter(request, response);
	}
}
