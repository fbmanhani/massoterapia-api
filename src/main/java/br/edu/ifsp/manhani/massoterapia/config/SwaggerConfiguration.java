package br.edu.ifsp.manhani.massoterapia.config;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
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
                .pathMapping("/");
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

}
