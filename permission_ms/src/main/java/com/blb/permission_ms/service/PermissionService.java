package com.blb.permission_ms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blb.permission_ms.entity.Permission;

import java.util.List;

public interface PermissionService extends IService<Permission> {

    List<Permission> getPermissionsTree();

    /**
     * 通过角色id查询权限id
     * @param id
     * @return
     */
    List<Long> getPermissionIdsByRoleId(Long id);
}
