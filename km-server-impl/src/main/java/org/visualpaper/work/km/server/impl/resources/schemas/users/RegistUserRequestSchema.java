package org.visualpaper.work.km.server.impl.resources.schemas.users;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RegistUserRequestSchema {

  private Integer id;
  private String name;
}
