package com.mjm.springcloud.security.config;

import com.mjm.springcloud.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic();
        http.authorizeRequests().mvcMatchers(HttpMethod.GET, "/couponapi/coupons/{code:^[A-Z]*$}").hasAnyRole("USER","ADMIN").
                mvcMatchers(HttpMethod.POST, "/couponapi/coupons").hasRole("ADMIN").anyRequest().denyAll().and().csrf().disable();
//        http.authorizeRequests().mvcMatchers(HttpMethod.GET, "/couponapi/coupons/**").hasAnyRole("USER","ADMIN").
//                mvcMatchers(HttpMethod.POST, "/couponapi/coupons").hasRole("ADMIN").anyRequest().denyAll().and().csrf().disable();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
