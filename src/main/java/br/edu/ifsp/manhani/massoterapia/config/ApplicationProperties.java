package br.edu.ifsp.manhani.massoterapia.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApplicationProperties {

	@Component
	public static class Urls {

		@Value("${spring.application.cors-allowed-urls}")
		public String allowedUrls;

	}

	@Component
	public static class Documentation {

		@Value("${app-documentation.api-title}")
		public String title;

		@Value("${app-documentation.description}")
		public String description;

		@Value("${app-documentation.version}")
		public String version;

	}

	@Component
	public static class Ldap {
		@Value("${spring.ldap.urls}")
		public String urls;

		@Value("${spring.ldap.base}")
		public String baseDn;

		@Value("${spring.ldap.username}")
		public String username;

		@Value("${spring.ldap.password}")
		public String password;
	}

	private ApplicationProperties() {
		super();
	}

}
