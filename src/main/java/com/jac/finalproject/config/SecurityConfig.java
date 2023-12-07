package com.jac.finalproject.config;

import com.jac.finalproject.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig {


    @Bean
    public DaoAuthenticationProvider authenticationProvider(UserService userService) {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer -> configurer
                        .requestMatchers("/register").permitAll()
                        .requestMatchers("/users/delete/**").hasAnyAuthority("ROLE_ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(form ->
                        form.loginPage("/showMyLoginPage")
                                .loginProcessingUrl("/authenticateTheUser")
                                .defaultSuccessUrl("/users",true)
                                .permitAll()
                )
                .exceptionHandling(configurer -> configurer.accessDeniedPage("/user/access-denied"))
                .logout(LogoutConfigurer::permitAll)
        ;

        return http.build();
    }
}
