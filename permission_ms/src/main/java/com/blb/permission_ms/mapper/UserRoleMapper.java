package com.blb.permission_ms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blb.permission_ms.entity.Role;
import com.blb.permission_ms.entity.UserRole;

public interface UserRoleMapper extends BaseMapper<UserRole> {

    Page<UserRole> selectRolePage(Page<UserRole> page);
}
