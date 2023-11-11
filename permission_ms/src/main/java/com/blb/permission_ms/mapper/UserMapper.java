package com.blb.permission_ms.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blb.permission_ms.entity.Permission;
import com.blb.permission_ms.entity.Role;
import com.blb.permission_ms.entity.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {

    @Select("select r.* from role r join user_role ur on r.id=ur.role_id\n" +
            "join user u on u.id=ur.user_id where u.username=#{username}")
    List<Role> selectRolesByUsername(String username);

    @Select("select p.* from permission p join role_permission rp\n" +
            "on p.id=rp.permission_id\n" +
            "join role r on r.id=rp.role_id\n" +
            "join user_role ur on r.id=ur.role_id\n" +
            "join user u on ur.user_id=u.id\n" +
            "where u.username=#{username}")
    List<Permission> selectPermissionByUsername(String username);

    List<Permission> selectPermissionMenuByUsername(String username);


}
