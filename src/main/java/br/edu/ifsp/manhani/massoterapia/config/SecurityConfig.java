package br.edu.ifsp.manhani.massoterapia.config;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.ldap.userdetails.LdapUserDetailsMapper;
import org.springframework.security.ldap.userdetails.UserDetailsContextMapper;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import br.edu.ifsp.manhani.massoterapia.filter.JwtAuthenticationFilter;
import lombok.extern.java.Log;

@EnableWebSecurity
@Log
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JwtProperties jwtProperties;

	@Autowired
	private ApplicationProperties.Urls urls;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable().authorizeRequests()
				.antMatchers("/auth/**", "/v2/api-docs", "/v3/api-docs", "/configuration/ui", "/swagger-resources/**",
						"/configuration/**", "/swagger-ui/**", "/webjars/**")
				.permitAll().anyRequest().authenticated().and()
				.addFilterBefore(new JwtAuthenticationFilter(jwtProperties), UsernamePasswordAuthenticationFilter.class)
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

	// @formatter:off
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.ldapAuthentication()
        		.userDnPatterns("uid={0},ou=people")
        		.groupSearchBase("ou=groups")
        		.userDetailsContextMapper(userDetailsContextMapper())
        		.contextSource()
                	.url("ldap://localhost:12345/dc=springframework,dc=org")
            	.and()
            		.passwordCompare()
            		.passwordEncoder(passwordEncoder())
                		.passwordAttribute("userPassword");

    }
    // @formatter:on

	@Bean
	public UserDetailsContextMapper userDetailsContextMapper() {
		return new LdapUserDetailsMapper() {
			@Override
			public UserDetails mapUserFromContext(DirContextOperations ctx, String username,
					Collection<? extends GrantedAuthority> authorities) {
				String fullNamePlusLogin = String.format("%s (%s)", (String) ctx.getObjectAttribute("cn"), username);
				return super.mapUserFromContext(ctx, fullNamePlusLogin, authorities);

			}
		};
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration().applyPermitDefaultValues();
		config.setAllowedOrigins(Arrays.asList(urls.allowedUrls));
		source.registerCorsConfiguration("/**", config);
		return source;
	}

	@Bean(BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
