package com.example.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import static com.example.security.ApplicationUserPermission.*;
import static com.example.security.ApplicationUserRole.*;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
//                .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/","index")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .defaultSuccessUrl("/courses",true).and()
                .rememberMe();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails user1 = User.builder().username("annasmith").password(passwordEncoder.encode("password"))
                .authorities(STUDENT.getGrantedAuthorities())
                .build();
//                .roles(STUDENT.name()).build();  //ROLE_STUDENT
        UserDetails user2 = User.builder().username("linda").password(passwordEncoder.encode("password"))
                .authorities(ADMIN.getGrantedAuthorities())
                .build();
//                .roles(ADMIN.name()).build();    //ROLE_ADMIN
        UserDetails user3 = User.builder().username("tom").password(passwordEncoder.encode("password"))
                .authorities(ADMINTRAINEE.getGrantedAuthorities())
                .build();
//                .roles(ADMINTRAINEE.name()).build(); //ROLE_ADMINTRAINEE

        return new InMemoryUserDetailsManager(user1, user2, user3);
    }


}
