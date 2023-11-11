package com.blb.permission_ms.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

  private long id;
  private String username;
  private String password;
  private String realname;
  private String telephone;
  private String icon;
  private long state;
}
