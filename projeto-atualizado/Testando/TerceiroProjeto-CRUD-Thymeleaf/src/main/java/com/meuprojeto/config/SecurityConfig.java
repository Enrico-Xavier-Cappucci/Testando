package com.meuprojeto.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.meuprojeto.service.CustomAdminDetailsService;
import com.meuprojeto.service.CustomEmpregadoDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomAdminDetailsService adminDetailsService;
    
    @Autowired
    @Qualifier("empregadoDetailsService")
    private CustomEmpregadoDetailsService empregadoDetailsService;

    // Configuração para área do empregado
    @Bean
    @Order(1)
    public SecurityFilterChain empregadoSecurityFilterChain(HttpSecurity http) throws Exception {
        http
            .securityMatcher("/empregado/**")
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/empregado/login").permitAll()
                .requestMatchers("/empregado/**").hasRole("EMPREGADO")
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/empregado/login")
                .defaultSuccessUrl("/empregado/dashboard", true)
                .failureUrl("/empregado/login?error=true")
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/empregado/logout")
                .logoutSuccessUrl("/empregado/login?logout=true")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll()
            )
            .authenticationProvider(empregadoAuthenticationProvider());
        return http.build();
    }

    // Configuração para área do admin
    @Bean
    @Order(2)
    public SecurityFilterChain adminSecurityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/registration", "/login", "/css/**", "/js/**", "/images/**").permitAll()
                .anyRequest().hasRole("ADMIN")
            )
            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/", true)
                .failureUrl("/login?error=true")
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout=true")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll()
            )
            .authenticationProvider(adminAuthenticationProvider());
        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider adminAuthenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(adminDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public DaoAuthenticationProvider empregadoAuthenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(empregadoDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}

