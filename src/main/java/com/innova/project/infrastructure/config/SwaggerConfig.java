package com.innova.project.infrastructure.config;

import com.innova.project.core.response.ResponseErrorDTO;
import com.innova.project.core.response.ResponseFailDTO;
import com.innova.project.core.swagger.ResultErrors;
import com.innova.project.core.swagger.ResultFails;
import io.swagger.v3.core.converter.ModelConverters;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.responses.ApiResponse;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.ForwardedHeaderFilter;

@Configuration
public class SwaggerConfig {
    @Bean
    public ForwardedHeaderFilter forwardedHeaderFilter() {
        return new ForwardedHeaderFilter();
    }

    @Bean
    public OpenAPI openAPI(
    ) {
        Info info = new Info()
                .title("innova project section")
                .description("RESTful API")
                .version("0.0.1");
        return new OpenAPI()
                .info(info)
                .components(new Components());
    }

    @Bean
    public OpenApiCustomiser customGlobalHeaderOpenApiCustomiser() {
        return openApi -> openApi.getPaths().values().forEach(
                pathItem -> pathItem.readOperations().forEach(
                        operation -> {
                            openApi.getComponents().getSchemas().putAll(ModelConverters.getInstance().read(ResultErrors.class));
                            openApi.getComponents().getSchemas().putAll(ModelConverters.getInstance().read(ResultFails.class));

                            Schema<ResultErrors> errorSchema = new Schema<>();
                            errorSchema.set$ref("ResultErrors");
                            Schema<ResultFails> failsSchema = new Schema<>();

                            operation.getResponses()
                                    .addApiResponse("400", createResponse("Bad Request", errorSchema))
                                    .addApiResponse("401", createResponse("Unauthorized", failsSchema))
                                    .addApiResponse("403", createResponse("Forbidden", failsSchema))
                                    .addApiResponse("500", createResponse("Internal Server Error", failsSchema))
                            ;
                        }
                )
        );
    }

    private ApiResponse createResponse(String description, Schema<?> schema) {
        return new ApiResponse()
                .description(description)
                .content(
                        new Content().addMediaType(
                                org.springframework.http.MediaType.APPLICATION_JSON_VALUE
                                , new MediaType().schema(schema)
                        )
                );
    }

}
