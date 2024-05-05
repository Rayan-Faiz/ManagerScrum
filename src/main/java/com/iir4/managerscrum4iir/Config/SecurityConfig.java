package com.iir4.managerscrum4iir.Config;

import com.iir4.managerscrum4iir.Roles.Roles;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.*;

import java.io.IOException;
import java.util.Collection;

import static org.springframework.security.config.Customizer.withDefaults;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;


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
                    authorize.requestMatchers("/api/users/**").permitAll();
                    authorize.requestMatchers("/dashboard/scrum-master-dashboard").hasAuthority("ROLE_Master");
                    authorize.requestMatchers("/dashboard/member-dashboard").hasAuthority("ROLE_Member");
                    authorize.anyRequest().authenticated();
                })
                .formLogin(form -> form
                        .loginPage("/api/users/login")
                        .loginProcessingUrl("/api/users/login")
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
                .sessionManagement(AbstractHttpConfigurer::disable);//session -> session.sessionCreationPolicy(STATELESS));
        return http.build();
    }
}
