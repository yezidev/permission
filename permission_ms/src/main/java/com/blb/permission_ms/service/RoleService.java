package com.blb.permission_ms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blb.permission_ms.entity.Role;

import java.util.List;

public interface RoleService extends IService<Role> {
    List<Long> getRoleIdByUserId(Long id);
}
