package com.glearning.employeeservice.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class ApplicationSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserDetailsService domainUserDetailsService;
    // authentication
    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(this.domainUserDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    //authorization
    @Override
    protected void configure(HttpSecurity http) throws Exception {
            http
                .csrf()
                    .disable()
                .cors()
                    .disable()
                 .headers()
                    .frameOptions().disable();
            http
                .authorizeRequests()
                    .antMatchers("/login**", "/logout**", "/contact-us**", "/h2-console/**", "/login/**")
                        .permitAll()
                    .antMatchers(HttpMethod.GET, "/api/employees**")
                        .hasAnyRole("USER", "ADMIN")
                    .antMatchers(HttpMethod.POST, "/api/employees**")
                        .hasRole("ADMIN")
                    .antMatchers(HttpMethod.DELETE, "/api/employees/**")
                        .hasRole("ADMIN")
                    .anyRequest()
                        .authenticated()
                .and()
                    .formLogin()
                    .and()
                    .httpBasic();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
