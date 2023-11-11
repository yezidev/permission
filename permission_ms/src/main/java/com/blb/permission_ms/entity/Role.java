package com.blb.permission_ms.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {

  private long id;
  private String name;
  private String description;
  private long siteId;

}
