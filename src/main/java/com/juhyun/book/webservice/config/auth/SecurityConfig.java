package com.juhyun.book.webservice.config.auth;

import com.juhyun.book.webservice.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig  {

   @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
       return http
               .csrf(AbstractHttpConfigurer::disable
               )
               .headers((headerConfig) ->
                       headerConfig.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable
                       )
               )
               .authorizeHttpRequests((authorizeRequests) ->
                       authorizeRequests
                           .requestMatchers(PathRequest.toH2Console()).permitAll()
                           .requestMatchers("/", "/css/**", "/images/**", "/js/**", "/profile").permitAll()
                           .requestMatchers("/api/v1/**").hasRole(Role.USER.name())
                           .anyRequest().authenticated()
               )
               .logout((logoutConfig) ->
                       logoutConfig.logoutSuccessUrl("/")
               )
               .oauth2Login(Customizer.withDefaults())
               .formLogin(Customizer.withDefaults())
               .build();
   }
}

