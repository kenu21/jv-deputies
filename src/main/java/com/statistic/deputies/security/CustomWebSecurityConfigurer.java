package com.statistic.deputies.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Component;

@Component
public class CustomWebSecurityConfigurer extends WebSecurityConfigurerAdapter {

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
                .permitAll()
                .and()
                .httpBasic()
                .and()
                .logout();
    }
}
