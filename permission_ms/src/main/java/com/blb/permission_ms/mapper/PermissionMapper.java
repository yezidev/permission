package com.blb.permission_ms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blb.permission_ms.entity.Permission;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PermissionMapper extends BaseMapper<Permission> {

    /**
     * 查询所有权限树
     * @return
     */
    List<Permission> selectPermissionsTree();

    /**
     * 根据角色id查询权限id
     * @param id
     * @return
     */
    @Select("select permission_id from role_permission where role_id=#{id}")
    List<Long> selectPermissionIdsByRoleId(Long id);
}
