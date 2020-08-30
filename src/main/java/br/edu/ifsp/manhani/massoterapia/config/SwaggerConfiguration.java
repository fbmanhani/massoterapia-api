package br.edu.ifsp.manhani.massoterapia.config;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.DocExpansion;
import springfox.documentation.swagger.web.ModelRendering;
import springfox.documentation.swagger.web.OperationsSorter;
import springfox.documentation.swagger.web.TagsSorter;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;

@Slf4j
@Configuration
public class SwaggerConfiguration {

    @Autowired
    private ApplicationProperties.Documentation properties;

    private ApiInfo apiInfo() {
        StringBuilder version = new StringBuilder();
        version.append(properties.version);
        version.append("-").append(StringUtils.defaultIfBlank(System.getenv("ENV"), Strings.EMPTY));
        version.append(".").append(StringUtils.defaultIfBlank(System.getenv("BUILD_NUMBER"), StringUtils.EMPTY));
        log.info(String.format("Info do Swagger: %s@%s", properties.title, version));
        return new ApiInfoBuilder().title(properties.title).description(properties.description).version(version.toString()).build();
    }

    @Bean
    public Docket apiDocket() {
        return new Docket(DocumentationType.OAS_30).apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class)).paths(PathSelectors.any()).build()
                .pathMapping("/")
                .securitySchemes(Collections.singletonList(apiKey()))
                .securityContexts(Arrays.asList(securityContext()));
    }
    
    @Bean
    UiConfiguration uiConfig() {
      return UiConfigurationBuilder.builder() 
          .deepLinking(true)
          .displayOperationId(false)
          .defaultModelsExpandDepth(1)
          .defaultModelExpandDepth(1)
          .defaultModelRendering(ModelRendering.EXAMPLE)
          .displayRequestDuration(false)
          .docExpansion(DocExpansion.NONE)
          .filter(false)
          .maxDisplayedTags(null)
          .operationsSorter(OperationsSorter.ALPHA)
          .showExtensions(false)
          .showCommonExtensions(false)
          .tagsSorter(TagsSorter.ALPHA)
          .supportedSubmitMethods(UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS)
          .validatorUrl(null)
          .build();
    }
    
    private ApiKey apiKey() {
        return new ApiKey(HttpHeaders.AUTHORIZATION, "api_key", "header"); 
      }
    
    private SecurityContext securityContext() {
        return SecurityContext.builder()
            .securityReferences(defaultAuth())
            .forPaths(PathSelectors.any()) 
            .build();
      }

    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope
            = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Collections.singletonList(
            new SecurityReference(HttpHeaders.AUTHORIZATION, authorizationScopes)); 
      }

}
