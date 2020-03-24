package br.edu.ifsp.manhani.massoterapia.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApplicationProperties {

    @Component
    public static class Urls {

        @Value("${frontend-url:swagger-ui.html}")
        public String frontend;

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
    @Getter
    @Setter
    public static class SecurityOauth2Client {

        @Value("${spring.security.oauth2.client.provider.oidc.issuer-uri}/protocol/openid-connect/auth")
        private String userAuthorizationUri;

        @Value("${spring.security.oauth2.client.registration.oidc.client-id}")
        private String clientId;

    }

    private ApplicationProperties() {
        super();
    }

}
