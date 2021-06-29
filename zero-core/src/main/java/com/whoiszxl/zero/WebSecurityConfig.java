package com.whoiszxl.zero;


import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Component;

//@Component
class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    public void configure(WebSecurity web) throws Exception {

        // 全局配置：忽略url
        web.ignoring().antMatchers("/getLogin");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    }
}