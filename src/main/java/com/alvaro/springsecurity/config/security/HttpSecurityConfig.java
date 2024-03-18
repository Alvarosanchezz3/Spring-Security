package com.alvaro.springsecurity.config.security;

import com.alvaro.springsecurity.config.security.filter.JwtAuthenticationFilter;
import com.alvaro.springsecurity.persistence.util.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true) // PERMITE LA AUTORIZACIÓN CON MÉTODOS
public class HttpSecurityConfig {

    @Autowired
    private AuthenticationProvider daoAuthProvider;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception {

        SecurityFilterChain filterChain = http
                .cors(Customizer.withDefaults())
                .csrf(csrfConfig -> csrfConfig.disable())
                .sessionManagement( sessionManConfig -> sessionManConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(daoAuthProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                //.authorizeHttpRequests( authRequestConfig -> {buildRequestMatchers2(authRequestConfig);})
                .build();

        return filterChain;
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("https://localhost:4200", "https://localhost:3000"));
        configuration.setAllowedMethods(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    private static void buildRequestMatchers(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry authRequestConfig) {

        // AUTORIZACIÓN de los endpoints protegidos de productos por roles
        authRequestConfig.requestMatchers(HttpMethod.GET, "/products")
                        .hasAnyRole(Role.ADMINISTRATOR.name(), Role.ASSISTANT_ADMINISTRATOR.name());

        authRequestConfig.requestMatchers(HttpMethod.GET, "/products/{productId}")
                .hasAnyRole(Role.ADMINISTRATOR.name(), Role.ASSISTANT_ADMINISTRATOR.name());

        authRequestConfig.requestMatchers(HttpMethod.POST, "/products")
                .hasAnyRole(Role.ADMINISTRATOR.name());

        authRequestConfig.requestMatchers(HttpMethod.PUT, "/products/{productId}")
                .hasAnyRole(Role.ADMINISTRATOR.name(), Role.ASSISTANT_ADMINISTRATOR.name());

        authRequestConfig.requestMatchers(HttpMethod.PUT, "/products/{productId}/disabled")
                .hasAnyRole(Role.ADMINISTRATOR.name());

        authRequestConfig.requestMatchers(HttpMethod.PUT, "/products/{productId}/enabled")
                .hasAnyRole(Role.ADMINISTRATOR.name());

        // AUTORIZACIÓN de los endpoints protegidos de categorias por roles
        authRequestConfig.requestMatchers(HttpMethod.GET, "/categories")
                .hasAnyRole(Role.ADMINISTRATOR.name(), Role.ASSISTANT_ADMINISTRATOR.name());

        authRequestConfig.requestMatchers(HttpMethod.GET, "/categories/{categoryId}")
                .hasAnyRole(Role.ADMINISTRATOR.name(), Role.ASSISTANT_ADMINISTRATOR.name());

        authRequestConfig.requestMatchers(HttpMethod.POST, "/categories")
                .hasAnyRole(Role.ADMINISTRATOR.name());

        authRequestConfig.requestMatchers(HttpMethod.PUT, "/categories/{categoryId}")
                .hasAnyRole(Role.ADMINISTRATOR.name(), Role.ASSISTANT_ADMINISTRATOR.name());

        authRequestConfig.requestMatchers(HttpMethod.PUT, "/categories/{categoryId}/disabled")
                .hasAnyRole(Role.ADMINISTRATOR.name());

        authRequestConfig.requestMatchers(HttpMethod.PUT, "/categories/{categoryId}/enabled")
                .hasAnyRole(Role.ADMINISTRATOR.name());

        // AUTORIZACIÓN del endpoint protegido para leer tu perfil
        authRequestConfig.requestMatchers(HttpMethod.GET, "/auth/profile")
                .hasAnyRole(Role.ADMINISTRATOR.name(), (Role.ASSISTANT_ADMINISTRATOR.name()),
                        Role.CUSTOMER.name());


        // AUTENTICACIÓN Rutas públicas
        authRequestConfig.requestMatchers(HttpMethod.POST,"/customers").permitAll();
        authRequestConfig.requestMatchers(HttpMethod.POST,"/auth/authenticate").permitAll();
        authRequestConfig.requestMatchers(HttpMethod.GET,"/auth/validate-token").permitAll();

        // El resto de rutas son protegidas y necesitas estar AUTENTICADO
        authRequestConfig.anyRequest().authenticated();
    }

    private static void buildRequestMatchers2(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry authRequestConfig) {

        // AUTENTICACIÓN Rutas públicas
        authRequestConfig.requestMatchers(HttpMethod.POST,"/customers").permitAll();
        authRequestConfig.requestMatchers(HttpMethod.POST,"/auth/authenticate").permitAll();
        authRequestConfig.requestMatchers(HttpMethod.GET,"/auth/validate-token").permitAll();

        // El resto de rutas son protegidas y necesitas estar AUTENTICADO
        authRequestConfig.anyRequest().authenticated();
    }
}