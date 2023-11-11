package com.blb.permission_ms.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blb.permission_ms.entity.Permission;
import com.blb.permission_ms.entity.ResponseResult;
import com.blb.permission_ms.entity.User;
import com.blb.permission_ms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ResponseResult<String> selectUser() {
        List<User> list = userService.list();
        return ResponseResult.ok(list);
    }

    @GetMapping("/usersPage")
    public ResponseResult<Page<User>> selectUserPage(@RequestParam(required = false, defaultValue = "1") Long current,
                                                     @RequestParam(required = false, defaultValue = "12") Long size) {
        Page<User> page = userService.page(new Page<>(current, size));
        return ResponseResult.ok(page);
    }


    @GetMapping("/user-permission-menus/{username}")
    public ResponseResult<List<Permission>> getUserPermissionMenus(@PathVariable String username) {
        List<Permission> permissionMenus = userService.getUserPermissionMenus(username);
        return ResponseResult.ok(permissionMenus);
    }


    @PostMapping("/user")
    public ResponseResult<String> add(@RequestBody User user) {
        userService.save(user);
        return ResponseResult.ok(user);
    }

    @PutMapping("/user")
    public ResponseResult<String> update(@RequestBody User user) {
        userService.updateById(user);
        return ResponseResult.ok(user);
    }

    @DeleteMapping("/user/{id}")
    public ResponseResult<String> delete(@PathVariable Long id) {
        userService.removeById(id);
        return ResponseResult.ok(id);
    }
}
