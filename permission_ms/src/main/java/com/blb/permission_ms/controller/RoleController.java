package com.blb.permission_ms.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blb.permission_ms.entity.ResponseResult;
import com.blb.permission_ms.entity.Role;
import com.blb.permission_ms.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/roles")
    public ResponseResult<List<Role>> selectRole(){
        List<Role> list = roleService.list();
        return ResponseResult.ok(list);
    }

    @GetMapping("/rolesPage")
    public ResponseResult<Page<Role>> selectRolePage(@RequestParam(required = false,defaultValue = "1") Long current,
                                                     @RequestParam(required = false,defaultValue = "12") Long size){
        Page<Role> page = roleService.page(new Page<>(current, size));
        return ResponseResult.ok(page);
    }

    @PostMapping("/role")
    public ResponseResult<String> addRole(@RequestBody Role role){
        roleService.save(role);
        return ResponseResult.ok(role);
    }

    @PutMapping("/role")
    public ResponseResult<String> update(@RequestBody Role role){
        roleService.updateById(role);
        return ResponseResult.ok(role);
    }

    @DeleteMapping("/role/{id}")
    public ResponseResult<String> delete(@PathVariable Long id){
        roleService.removeById(id);
        return ResponseResult.ok(id);
    }

    @GetMapping("/role-ids/{id}")
    public ResponseResult<List<Long>> getRoleIdsByUserId(@PathVariable Long id){
        List<Long> ids = roleService.getRoleIdByUserId(id);
        return ResponseResult.ok(ids);
    }
}
