package com.blb.permission_ms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blb.permission_ms.entity.Role;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 通过用户id查询角色id
     * @param id
     * @return
     */
    @Select("select role_id from user_role where user_id=#{id}")
    List<Long> selectRoleIdByUserId(Long id);
}
