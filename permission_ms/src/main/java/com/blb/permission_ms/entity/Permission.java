package com.blb.permission_ms.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("permission")
public class Permission {

  @TableId(type = IdType.AUTO)
  private long id;
  private String name;
  private String description;
  private String url;
  private long pid;
  private String icon;
  private long sort;

  //子菜单集合
  @TableField(exist = false)
  private List<Permission> childPermissions;
}
