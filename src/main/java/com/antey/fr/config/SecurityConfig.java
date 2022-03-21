package com.antey.fr.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers( "/surveys/*/questions" ).hasRole("ADMIN")
                .antMatchers( HttpMethod.POST, "/surveys" ).hasRole("ADMIN")
                .antMatchers( HttpMethod.PUT, "/surveys/*" ).hasRole("ADMIN")
                .antMatchers( HttpMethod.DELETE, "/surveys/*" ).hasRole("ADMIN")
                .anyRequest().permitAll()
                .and().httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("Admin").password("{noop}12345").roles("ADMIN");
    }
}
