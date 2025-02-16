package com.zeta.SecureNotes.security;

import com.zeta.SecureNotes.enumerations.ROLE;
import com.zeta.SecureNotes.models.Role;
import com.zeta.SecureNotes.models.User;
import com.zeta.SecureNotes.repositories.RoleRepository;
import com.zeta.SecureNotes.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import java.time.LocalDate;
import java.util.Optional;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true
)
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                requests -> requests
                        .anyRequest().authenticated()

        );
        http.csrf(
                csrf -> csrf.disable()
        );
        http.sessionManagement(
                session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );
        //http.formLogin(Customizer.withDefaults());
        http.httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public CommandLineRunner initData( // class initData implements CommandLineRunner.run
            RoleRepository roleRepository,
            UserRepository userRepository
    ){
        return args -> {
            Role userRole = roleRepository.findByRoleName(ROLE.ROLE_USER)
                    .orElseGet(() -> new Role(ROLE.ROLE_USER));

            Role adminRole = roleRepository.findByRoleName(ROLE.ROLE_ADMIN)
                    .orElseGet(() -> new Role(ROLE.ROLE_ADMIN));

            if(!userRepository.existsByUsername("user1")){
                User user1 = new User(
                        "user1",
                        "user1@email.com",
                        "{noop}passeruser1" //not encoded
                );
                user1.setRole(userRole);
                user1.setSignUpMethod("email");
                user1.setEnabled(true);
                user1.setAccountExpiryDate(LocalDate.now().plusYears(1));
                user1.setAccountNonExpired(true);
                user1.setAccountNonLocked(true);
                user1.setCredentialsNonExpired(true);
                user1.setCredentialsExpiryDate(LocalDate.now().plusYears(1));
                user1.setTwoFactorEnabled(false);

                userRepository.save(user1);
            }

            if(!userRepository.existsByUsername("admin1")){
                User admin1 = new User(
                        "admin1",
                        "admin1@email.com",
                        "{noop}passeradmin1" //not encoded
                );
                admin1.setRole(adminRole);
                admin1.setSignUpMethod("email");
                admin1.setEnabled(true);
                admin1.setAccountExpiryDate(LocalDate.now().plusYears(1));
                admin1.setAccountNonExpired(true);
                admin1.setAccountNonLocked(true);
                admin1.setCredentialsNonExpired(true);
                admin1.setCredentialsExpiryDate(LocalDate.now().plusYears(1));
                admin1.setTwoFactorEnabled(false);

                userRepository.save(admin1);
            }
        };
    }

}
