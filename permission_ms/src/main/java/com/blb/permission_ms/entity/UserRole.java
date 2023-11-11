package com.blb.permission_ms.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("user_role")
public class UserRole {

  @TableId(type = IdType.AUTO)
  private long id;
  private long roleId;
  private long userId;

  @TableField(exist = false)
  private User user;
  @TableField(exist = false)
  private Role role;
}
