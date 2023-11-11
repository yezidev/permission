package com.blb.permission_ms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blb.permission_ms.entity.Permission;
import com.blb.permission_ms.mapper.PermissionMapper;
import com.blb.permission_ms.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;
    @Override
    public List<Permission> getPermissionsTree() {
        List<Permission> permissions = permissionMapper.selectPermissionsTree();
        return permissions;
    }

    @Override
    public List<Long> getPermissionIdsByRoleId(Long id) {
        List<Long> ids = permissionMapper.selectPermissionIdsByRoleId(id);
        return ids;
    }
}
