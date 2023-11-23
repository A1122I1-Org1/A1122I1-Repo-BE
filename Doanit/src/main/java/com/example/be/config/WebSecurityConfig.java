package com.example.be.config;

import com.example.be.jwt.JwtAuthenticationFilter;
import com.example.be.security.UserPrincipleService;
import com.example.be.service.IAccountService;
import com.example.be.service.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configurable//đánh dấu đây là class có các phương thức tạo ra Bean component.
@EnableWebSecurity// đây là class cấu hình cho Spring Security
@EnableMethodSecurity//  kích hoạt cả 3 cách phân quyền @PreAuthorize @PostAuthorize//@RolesAllowed vs. @PreAuthorize vs. @Secured
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserPrincipleService userPrincipleService;
    @Bean
    public IAccountService accountServiceService() {
        return new AccountServiceImpl();
    }
    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //Xác thực
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userPrincipleService)//cung cấp userPrincipleService cho spring security
                .passwordEncoder(passwordEncoder());// cung cấp password
    }
    //Phân quyền
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors()// ngăn chặn request từ các domain khác
                .and().csrf().disable()// tắt mặt định tấn công xác thực
                .authorizeRequests()// phân quyền request
                .antMatchers("/api/v1/auth/**").permitAll()//Không cần xác thực
                .anyRequest().authenticated();
        //Thêm 1 lớp Filter kt jwt
        httpSecurity.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}