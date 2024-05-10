package org.visualpaper.work.km.server.impl.resources.schemas.users;

import java.util.List;
import lombok.Data;

@Data
public class GetUsersResponseScheme {
  private List<UserSchema> users;
}
