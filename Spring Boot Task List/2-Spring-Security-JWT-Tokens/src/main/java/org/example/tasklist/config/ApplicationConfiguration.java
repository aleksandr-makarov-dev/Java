package org.example.tasklist.config;

import lombok.AllArgsConstructor;
import org.example.tasklist.web.security.JwtFilter;
import org.example.tasklist.web.security.JwtProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class ApplicationConfiguration {

    private final ApplicationContext applicationContext;
    private final JwtProvider jwtProvider;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable) // disable cross site request forgery
                .cors(AbstractHttpConfigurer::disable) // enable cors
                .httpBasic(AbstractHttpConfigurer::disable) // disable basic authentication
                .sessionManagement(sessionManager-> sessionManager
                        .sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS
                        )
                )
                .exceptionHandling(configurer -> configurer
                        .authenticationEntryPoint(((request, response, authException) -> {

                            // return unauthorized response if authentication failed
                            response.setStatus(HttpStatus.UNAUTHORIZED.value());
                            response.getWriter().write("401 Unauthorized");
                        }))
                        .accessDeniedHandler(((request, response, accessDeniedException) -> {
                            // return forbidden if access denied
                            response.setStatus(HttpStatus.FORBIDDEN.value());
                            response.getWriter().write("403 Forbidden");
                        })) //
                ) // handle exceptions
                .authorizeHttpRequests(configurer->
                        configurer
                                .requestMatchers("/api/v1/auth/**")
                                .permitAll()
                                .anyRequest()
                                .authenticated()
                )
                .anonymous(AbstractHttpConfigurer::disable)
                .addFilterBefore(new JwtFilter(jwtProvider), UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }
}
