package com.example.bootstrapspring.config;


import com.example.bootstrapspring.service.UserService;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserService userService;
    private final SuccessUserHandler userHandler;
    private final PasswordEncoder passwordEncoder;

    public SecurityConfig(UserService userService, SuccessUserHandler userHandler, PasswordEncoder passwordEncoder ) {
        this.userService = userService;
        this.userHandler = userHandler;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/api/admin/**", "/api/users/**").hasRole("ADMIN")
                .antMatchers("/api/user/**").hasAnyRole("ADMIN", "USER")
                .anyRequest().authenticated()
                .and()
                .formLogin().successHandler(userHandler)
                .loginPage("/")
                .loginProcessingUrl("/process_login")
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService((UserDetailsService) userService).passwordEncoder(passwordEncoder);
    }


}
