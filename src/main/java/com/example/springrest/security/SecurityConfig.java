package com.example.springrest.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final CustomAuthProvider customAuthProvider;
    public SecurityConfig(CustomAuthProvider customAuthProvider) {
        this.customAuthProvider = customAuthProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .cors().and().csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.POST,
                        "/restapi/v1/user/adduser").permitAll()
                .requestMatchers(HttpMethod.GET,
                        "/restapi/v1/story/getvipstories",
                        "/restapi/v1/story/getbywriter").hasAnyRole("VIP", "ADMIN")
                .requestMatchers(HttpMethod.GET,
                        "restapi/v1/user/getuser/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST,
                        "restapi/v1/user/addrole",
                                "restapi/v1/user/giveroletouser",
                                "/restapi/v1/story/**").hasRole("ADMIN")
                .anyRequest().hasAnyRole("USER", "VIP", "ADMIN")
                .and()
                .authenticationProvider(customAuthProvider)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .httpBasic(Customizer.withDefaults());
            return httpSecurity.build();
    }
}
