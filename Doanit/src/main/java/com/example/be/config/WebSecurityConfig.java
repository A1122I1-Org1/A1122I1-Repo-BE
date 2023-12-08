package com.example.be.config;

import com.example.be.jwt.JwtAuthenticationFilter;
import com.example.be.security.UserPrincipleService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserPrincipleService userPrincipleService;

    public WebSecurityConfig(UserPrincipleService userPrincipleService) {
        this.userPrincipleService = userPrincipleService;
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Authentication
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userPrincipleService)
                .passwordEncoder(passwordEncoder());
    }

    // Authorization
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/v1/auth/sign-in").permitAll()
                .antMatchers("/api/v1/users/**").hasRole("USER")
                .antMatchers("/api/v1/admin/**").hasRole("ADMIN")
                .antMatchers("/api/public/topic-manager/topic").permitAll() // Đường dẫn cho phương thức pageTopic
                .antMatchers("/api/public/topic-manager/topic-search").permitAll() // Đường dẫn cho phương thức pageTopicFind
                .antMatchers("/api/public/topic-manager/findById/{id}").permitAll() // Đường dẫn cho phương thức findTopicById
                .antMatchers("/notification/{id}").permitAll() // Đường dẫn cho phương thức getListNotification
                .antMatchers("/seen-notification/{id}").permitAll() // Đường dẫn cho phương thức seenNotification
                .anyRequest().authenticated();

        httpSecurity.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

}
