package br.edu.ifsp.manhani.massoterapia.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.alperkurtul.firebaseuserauthentication.service.UserAuthenticationServiceImpl;

@Configuration
public class FirebaseConfig {

	@Bean
	public UserAuthenticationServiceImpl userAuthenticationServiceImpl() {
		return new UserAuthenticationServiceImpl();
	}

}
