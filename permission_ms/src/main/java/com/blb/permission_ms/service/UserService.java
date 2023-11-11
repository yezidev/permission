package com.blb.permission_ms.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.blb.permission_ms.entity.Permission;
import com.blb.permission_ms.entity.Role;
import com.blb.permission_ms.entity.User;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

public interface UserService extends IService<User> {

    List<Role> selectRolesByUsername(String username);

    List<Permission> selectPermissionByUsername(String username);

    List<GrantedAuthority> getAuthoritiesByUserName(String username);

    List<Permission> getUserPermissionMenus(String username);
}
