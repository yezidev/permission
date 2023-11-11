package com.blb.permission_ms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blb.permission_ms.entity.Role;
import com.blb.permission_ms.mapper.RoleMapper;
import com.blb.permission_ms.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Long> getRoleIdByUserId(Long id) {
        return roleMapper.selectRoleIdByUserId(id);
    }
}
