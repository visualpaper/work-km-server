package org.visualpaper.work.km.server.impl.resources.schemas;

import lombok.Data;

@Data
public class ErrorSchema {

  private String code;
  private String message;
}
