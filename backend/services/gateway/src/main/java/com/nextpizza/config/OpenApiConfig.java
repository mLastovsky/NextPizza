package com.nextpizza.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                title = "Api Gateway",
                description = "OpenApi documentation for api-gateway",
                version = "1.0"
        ),
        servers = {
                @Server(
                        description = "LOCAL ENV",
                        url = "http://localhost:8222"
                )
        }
)
public class OpenApiConfig {
}
