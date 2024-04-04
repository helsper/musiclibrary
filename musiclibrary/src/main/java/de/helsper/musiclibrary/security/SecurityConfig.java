package de.helsper.musiclibrary.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Value("${cors.allowedHeaders}")
    private List<String> allowedHeaders;

    @Value("${cors.allowedExposedHeaders}")
    private List<String> allowedExposedHeaders;

    @Value("${cors.allowedMethods}")
    private List<String> allowedMethods;

    @Value("${cors.allowedOrigins}")
    private List<String> allowedOrigins;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.cors(httpCors -> httpCors.configurationSource(configurationSource()));
        http.csrf(AbstractHttpConfigurer::disable);
        http.authorizeHttpRequests(requests -> requests
                .requestMatchers("/albums/**")
                .permitAll()
                .anyRequest().authenticated()
        );

        return http.build();
    }

    private CorsConfigurationSource configurationSource() {
        final CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.setAllowedHeaders(allowedHeaders);
        corsConfig.setExposedHeaders(allowedExposedHeaders);
        corsConfig.setAllowedMethods(allowedMethods);
        corsConfig.setAllowedOrigins(allowedOrigins);

        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);

        return source;
    }
}
