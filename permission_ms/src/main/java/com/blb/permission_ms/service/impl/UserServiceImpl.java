package com.blb.permission_ms.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blb.permission_ms.entity.Permission;
import com.blb.permission_ms.entity.Role;
import com.blb.permission_ms.entity.User;
import com.blb.permission_ms.mapper.UserMapper;
import com.blb.permission_ms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<Role> selectRolesByUsername(String username) {
        return userMapper.selectRolesByUsername(username);
    }

    @Override
    public List<Permission> selectPermissionByUsername(String username) {
        return userMapper.selectPermissionByUsername(username);
    }

    @Override
    public List<GrantedAuthority> getAuthoritiesByUserName(String username) {
        StringBuilder authorities = new StringBuilder();

        //查询所有角色权限
        List<Role> roles = userMapper.selectRolesByUsername(username);
        List<Permission> permissions = userMapper.selectPermissionByUsername(username);

        //拼接角色到字符串中
        roles.forEach(role -> authorities.append("ROLE_" + role.getName() + ","));

        //拼接权限名称
        permissions.forEach(permission -> authorities.append(permission.getName() + ","));
        authorities.deleteCharAt(authorities.length() - 1);

        //将用户名密码角色权限包装到userdetails.User对象中
        List<GrantedAuthority> authorities1 = AuthorityUtils.commaSeparatedStringToAuthorityList(authorities.toString());
        return authorities1;
    }

    @Override
    public List<Permission> getUserPermissionMenus(String username) {
        List<Permission> permissions = userMapper.selectPermissionMenuByUsername(username);
        return permissions;
    }
}
