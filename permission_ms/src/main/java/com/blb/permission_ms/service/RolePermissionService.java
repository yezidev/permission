package com.blb.permission_ms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blb.permission_ms.entity.RolePermission;

public interface RolePermissionService extends IService<RolePermission> {

    void updateRolePermissions(Long roleId,String permissionIds);
}
