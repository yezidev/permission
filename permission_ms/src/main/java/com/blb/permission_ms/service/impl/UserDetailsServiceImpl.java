package com.blb.permission_ms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.blb.permission_ms.entity.User;
import com.blb.permission_ms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //通过用户名查找用户
        User user = userService.getOne(new QueryWrapper<User>().lambda().eq(User::getUsername, username));
        if (user == null){
            throw new UsernameNotFoundException("用户名不存在");
        }

        List<GrantedAuthority> authorities = userService.getAuthoritiesByUserName(username);
        org.springframework.security.core.userdetails.User user1 =
                new org.springframework.security.core.userdetails.User(username,
                        user.getPassword(),authorities);

        return user1;
    }
}
