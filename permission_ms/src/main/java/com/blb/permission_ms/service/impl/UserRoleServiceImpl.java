package com.blb.permission_ms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blb.permission_ms.entity.UserRole;
import com.blb.permission_ms.mapper.UserRoleMapper;
import com.blb.permission_ms.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.w3c.dom.ranges.RangeException;

@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;
    @Override
    public Page<UserRole> selectRolePage(Long current, Long size) {
        Page<UserRole> page = userRoleMapper.selectRolePage(new Page<>(current, size));
        return page;
    }

    @Override
    @Transactional
    public void updateUserRoles(Long userId, String roleIds) {
        if (StringUtils.isEmpty(roleIds)){
            throw  new RuntimeException("角色id为空");
        }

        //把中间表用户的角色清空
        userRoleMapper.delete(new QueryWrapper<UserRole>().lambda().eq(UserRole::getUserId, userId));
        //添加选中的角色
        String[] ids = roleIds.split(",");
        for (String roleId : ids){
            UserRole ur = new UserRole();
            ur.setUserId(userId);
            ur.setRoleId(Long.valueOf(roleId));
            userRoleMapper.insert(ur);
        }
    }
}
