package com.zeta.EmbarkxTuto.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(
                requests -> requests.anyRequest().authenticated()
        );

        http.csrf(
                csrf -> csrf.disable()
        );

        http.sessionManagement(
                session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );

        http.formLogin(Customizer.withDefaults());
        http.httpBasic(Customizer.withDefaults());
        return http.build();
    }


    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {
        //InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);

        if(!manager.userExists("user1")) {
            manager.createUser(
                    User.withUsername("username1")
                            .password("{noop}passer")
                            .roles("USER")
                            .build()
            );
        }

        if(!manager.userExists("admin1")) {
            manager.createUser(
                    User.withUsername("admin1")
                            .password("{noop}passer")
                            .roles("ADMIN")
                            .build()
            );
        }

        return manager;
    }

}
