package com.blb.permission_ms.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RolePermission {

  private long id;
  private long roleId;
  private long permissionId;

}
