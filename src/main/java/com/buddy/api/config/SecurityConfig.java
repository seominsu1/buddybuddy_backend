package com.buddy.api.config;

import com.buddy.api.config.jwt.JwtAccessDeniedHandler;
import com.buddy.api.config.jwt.JwtAuthenticationEntryPoint;
import com.buddy.api.config.jwt.JwtSecurityConfig;
import com.buddy.api.config.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtTokenProvider jwtTokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    private static final String[] PERMIT_URL = {
            /*
            swagger
            * */
            "/v3/api-docs/**",
            "/swagger-ui/**",
            /*
            * service
            * */
            "/api/v1/login",
            "/index.html",
            "/app.js",
            "/bootstrap.min.css",
            "jquery.min.js",
            "sockjs.min.js",
            "stomp.min.js",
            "/ws-stomp/**",
            "/posts.html",
            "/post.js",
            "/api/v1/render/**",
            "/auctionDetail.html"
    };

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(request -> request
                        .requestMatchers(
                                PERMIT_URL
                        ).permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/post")
                        .permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/post/paging*")
                        .permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/pool")
                        .permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/member")
                        .permitAll()
               )
                .authorizeHttpRequests(request -> request.anyRequest().authenticated())
                .exceptionHandling(exceptionConfig -> exceptionConfig
                        .accessDeniedHandler(jwtAccessDeniedHandler)
                        .authenticationEntryPoint(jwtAuthenticationEntryPoint))
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .logout(logoutConfig -> logoutConfig
                        .logoutSuccessUrl("/"))
                ;
        http.apply(new JwtSecurityConfig(jwtTokenProvider));
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
