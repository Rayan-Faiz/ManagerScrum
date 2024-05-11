package com.iir4.managerscrum4iir.Config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;
import java.util.Collection;

import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        http
                .cors(withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorize)->{
                    authorize.requestMatchers("/ManagerScrum/users/**").permitAll();
                    authorize.requestMatchers("/dashboard/scrum-master-dashboard").hasAuthority("ROLE_Master");
                    authorize.requestMatchers("/dashboard/member-dashboard").hasAuthority("ROLE_Member");
                    authorize.requestMatchers("/notifications/**").permitAll();
                    authorize.anyRequest().authenticated();
                })
                .formLogin(form -> form
                        .loginPage("/ManagerScrum/users/login")
                        .loginProcessingUrl("/ManagerScrum/users/login")
                        .successHandler(new AuthenticationSuccessHandler() {
                            @Override
                            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                                Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>) authentication.getAuthorities();
                                if (authorities.stream().anyMatch(authority -> authority.getAuthority().equals("ROLE_Member"))) {
                                    response.sendRedirect("/dashboard/member-dashboard");
                                } else if (authorities.stream().anyMatch(authority -> authority.getAuthority().equals("ROLE_Master"))) {
                                    response.sendRedirect("/dashboard/scrum-master-dashboard");
                                } else {
                                    response.sendRedirect("/error");
                                }
                            }
                        })
                        .permitAll()
                )
                .sessionManagement(AbstractHttpConfigurer::disable);
        return http.build();
    }
}
