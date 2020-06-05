package com.weikaiyu.bootactitivi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    //    密码编译器
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return NoOpPasswordEncoder.getInstance();
//    }

    //    密码比较器，一般常规数据库密码不会存储明文/security-springboot
//    NoOpPasswordEncoder.getInstance();为明文直接比较器
//    所以改用如下
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    //    安全拦截机制（重要）
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().//暂时屏蔽csrf
                authorizeRequests()
                .antMatchers("/r/r1").hasAnyAuthority("p1")
                .antMatchers("/r/r2").hasAnyAuthority("p2")
                .antMatchers("/r/**").authenticated()
                .anyRequest().permitAll()//除了r/*外其他资源按都可以访问
                .and()
                .formLogin()//允许表单登录
                .loginPage("/login-view")//登录页面
                .loginProcessingUrl("/login")
                .successForwardUrl("/login-success");
    }
}
