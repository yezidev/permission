package com.blb.permission_ms.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blb.permission_ms.entity.Permission;
import com.blb.permission_ms.entity.ResponseResult;
import com.blb.permission_ms.service.PermissionService;
import com.blb.permission_ms.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private RolePermissionService rolePermissionService;

    @GetMapping("/permission-tree")
    public ResponseResult<List<Permission>> getPermissionsTree(){
        List<Permission> permissionsTree = permissionService.getPermissionsTree();
        return ResponseResult.ok(permissionsTree);
    }

    @GetMapping("/permissionPage")
    public ResponseResult<Page<Permission>> getPermissionsPage(@RequestParam(required = false,defaultValue = "1") Long current,
                                                               @RequestParam(required = false,defaultValue = "12") Long size){
        Page<Permission> page = permissionService.page(new Page<>(current, size));
        return ResponseResult.ok(page);
    }

    @PostMapping("/permission")
    public ResponseResult<String> add(Permission permission){
        permissionService.save(permission);
        return ResponseResult.ok(permission);
    }

    @DeleteMapping("/permission")
    public ResponseResult<Permission> delete(@PathVariable Long id){
        permissionService.removeById(id);
        return ResponseResult.ok(id);
    }

    @PutMapping("/permission")
    public ResponseResult<Permission> update(@RequestBody Permission permission){
        permissionService.updateById(permission);
        return ResponseResult.ok(permission);
    }

    @GetMapping("/permission-ids/{id}")
    public ResponseResult<List<Long>> getPermissionIdsByRoleId(@PathVariable Long id){
        List<Long> ids = permissionService.getPermissionIdsByRoleId(id);
        return ResponseResult.ok(ids);
    }

    @PutMapping("/role-permissions")
    public ResponseResult<String> updateRolePermissions(Long roleId, String permissionIds){
        rolePermissionService.updateRolePermissions(roleId,permissionIds);
        return ResponseResult.ok("ok");
    }
}
