package com.statistic.deputies.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomWebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public AuthenticationSuccessHandler getCustomSuccessHandler() {
        return new CustomAuthenticationSuccessHandler();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/deputy/byConvocation/**", "/deputy/notPoliticians",
                        "/deputy/awarded", "/deputy/getGroupedByParty", "/deputy/notUkrainian",
                        "/deputy/partiesByConvocation/**", "/deputy/leastActiveTerms",
                        "/deputy/byUniversity", "/deputy/partySwitchers")
                .permitAll()
                .antMatchers("/deputy/add")
                .hasRole("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginProcessingUrl("/index")
                .successHandler(getCustomSuccessHandler())
                .permitAll()
                .and()
                .httpBasic()
                .and()
                .logout();
    }
}
