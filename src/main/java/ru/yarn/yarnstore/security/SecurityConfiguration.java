package ru.yarn.yarnstore.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity.authorizeHttpRequests(configurer -> configurer
                        .anyRequest().denyAll()) //todo: Добавить пути авторизации
                .csrf(AbstractHttpConfigurer::disable) //todo: Постараться избежать этого параметра
                .formLogin(Customizer.withDefaults())
                .build();
    }
}
