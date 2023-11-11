package com.blb.permission_ms;

import com.blb.permission_ms.entity.Permission;
import com.blb.permission_ms.entity.User;
import com.blb.permission_ms.mapper.PermissionMapper;
import com.blb.permission_ms.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class PermissionMsApplicationTests {
    @Autowired
    private UserService userService;

    @Autowired
    private PermissionMapper permissionMapper;

    @Test
    void contextLoads() {
        List<Permission> permissions = permissionMapper.selectPermissionsTree();
        System.out.println(permissions);
    }

}
