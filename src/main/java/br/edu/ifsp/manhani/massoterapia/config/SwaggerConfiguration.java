package br.edu.ifsp.manhani.massoterapia.config;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.OAuthBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.GrantType;
import springfox.documentation.service.ImplicitGrant;
import springfox.documentation.service.LoginEndpoint;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@Slf4j
@Configuration
@EnableSwagger2WebMvc
public class SwaggerConfiguration {

    private static final String API_VERSION_REGEX = "/.*";

    @Autowired
    ApplicationProperties.SecurityOauth2Client oauthProps;

    @Autowired
    private ApplicationProperties.Documentation properties;

    private ApiInfo apiInfo() {
        StringBuilder version = new StringBuilder();
        version.append(properties.version);
        version.append("-").append(StringUtils.defaultIfBlank(System.getenv("ENV"), Strings.EMPTY));
        version.append(".").append(StringUtils.defaultIfBlank(System.getenv("BUILD_NUMBER"), StringUtils.EMPTY));
        log.info(String.format("Info do Swagger: %s@%s", properties.title, version));
        return new ApiInfoBuilder().title(properties.title).description(properties.description)
                .version(version.toString()).build();
    }

    @Bean
    public Docket customImplementation() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class)).paths(PathSelectors.any()).build()
                .pathMapping("/").securitySchemes(Collections.singletonList(securityScheme()))
                .securityContexts(Collections.singletonList(securityContext()));
    }

    @Bean
    public SecurityConfiguration security() {
        Map<String, Object> params = new HashMap<>();
        params.put("nonce", "123456");
        return SecurityConfigurationBuilder.builder().clientId(oauthProps.getClientId()).scopeSeparator(",")
                .additionalQueryStringParams(params).useBasicAuthenticationWithAccessCodeGrant(false).build();
    }

    private SecurityScheme securityScheme() {
        GrantType grantType = implicitGrant();
        return new OAuthBuilder().name(oauthProps.getClientId()).grantTypes(Collections.singletonList(grantType))
                .scopes(Arrays.asList(scopes())).build();
    }

    private GrantType implicitGrant() {
        LoginEndpoint loginEndpoint = new LoginEndpoint(oauthProps.getUserAuthorizationUri());
        return new ImplicitGrant(loginEndpoint, "access_token");
    }

    private AuthorizationScope[] scopes() {
        return new AuthorizationScope[]{new AuthorizationScope("ADMIN", "")};
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(
                        Collections.singletonList(new SecurityReference(oauthProps.getClientId(), scopes())))
                .forPaths(PathSelectors.regex(API_VERSION_REGEX)).build();
    }
}
