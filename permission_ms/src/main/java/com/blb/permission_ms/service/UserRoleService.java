package com.blb.permission_ms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.blb.permission_ms.entity.UserRole;

public interface UserRoleService extends IService<UserRole> {
    Page<UserRole> selectRolePage(Long current,Long size);

    void updateUserRoles(Long userId,String roleIds);
}
