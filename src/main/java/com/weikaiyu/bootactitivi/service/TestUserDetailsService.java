package com.weikaiyu.bootactitivi.service;

import com.weikaiyu.bootactitivi.dao.UserRepository;
import com.weikaiyu.bootactitivi.pojo.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 自定义 UserDetailsService，
 * 1、这个类会拿到前端输入的用户名
 * 2、拿到用户名后，开发者拿到用户名去查询数据库，返回账号密码、
 * 3、将账号密码丢进 User.withUsername(byName.getName()).password(byName.getPassword()).authorities("p1").build();
 * withUsername接收参数为数据库查出的账号 password参数为数据库查出密码  p1为权限
 *
 */
@Service
public class TestUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        user byName = userRepository.findByName(s);
        if(byName==null){//这里如果查不到数据，直接就返回null，因为spring security帮我们做了异常抛出
            return null;
        }
        System.out.println("ssssssss"+s);
        UserDetails build = User.withUsername(byName.getName()).password(byName.getPassword()).authorities("p1").build();
        return build;
    }
}
