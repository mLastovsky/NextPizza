package com.nextpizza.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        var source = new UrlBasedCorsConfigurationSource();
        var config = new CorsConfiguration();

        // Разрешаем запросы с фронтенда
        config.addAllowedOrigin("http://localhost:3000");

        // Разрешаем все основные методы
        config.addAllowedMethod("GET");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("DELETE");

        // Разрешаем все заголовки
        config.addAllowedHeader("*");

        // Разрешаем передачу куки и авторизационных заголовков
        config.setAllowCredentials(true);

        // Применяем настройки ко всем путям
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }

}
