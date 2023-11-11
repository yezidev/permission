package com.blb.permission_ms.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blb.permission_ms.entity.ResponseResult;
import com.blb.permission_ms.entity.UserRole;
import com.blb.permission_ms.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserRoleController {

    @Autowired
    private UserRoleService userRoleService;

    @GetMapping("/userRoles")
    public ResponseResult<Page<UserRole>> selectRoleAll(@RequestParam(required = false,defaultValue = "1") Long current,
                                                    @RequestParam(required = false,defaultValue = "12") Long size){
        Page<UserRole> rolePage = userRoleService.selectRolePage(current, size);
        return ResponseResult.ok(rolePage);
    }

    @PostMapping("/userRole")
    public ResponseResult<String> add(@RequestBody UserRole userRole){
        userRoleService.save(userRole);
        return ResponseResult.ok(userRole);
    }

    @DeleteMapping("/userRole/{id}")
    public ResponseResult<String> delete(@PathVariable Long id){
        userRoleService.removeById(id);
        return ResponseResult.ok(id);
    }

    @PutMapping("/userRole")
    public ResponseResult<String> update(@RequestBody UserRole userRole){
        userRoleService.updateById(userRole);
        return ResponseResult.ok(userRole);
    }

    @PutMapping("/user-roles")
    public ResponseResult<String> updateRolePermissions(Long userId, String roleIds){
        userRoleService.updateUserRoles(userId,roleIds);
        return ResponseResult.ok("ok");
    }

}
