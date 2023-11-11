package com.blb.permission_ms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blb.permission_ms.entity.RolePermission;
import com.blb.permission_ms.mapper.RolePermissionMapper;
import com.blb.permission_ms.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission> implements RolePermissionService {

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Transactional
    @Override
    public void updateRolePermissions(Long roleId, String permissionIds) {
        if (StringUtils.isEmpty(permissionIds)){
            throw new RuntimeException("权限id为空");
        }
        //把中间表角色的权限清空
        rolePermissionMapper.delete(new QueryWrapper<RolePermission>().lambda().eq(RolePermission::getRoleId,roleId));
        //添加选中的权限
        String[] ids = permissionIds.split(",");
        for (String permissionId : ids){
            RolePermission rp = new RolePermission();
            rp.setRoleId(roleId);
            rp.setPermissionId(Long.valueOf(permissionId));
            rolePermissionMapper.insert(rp);
        }

    }
}
