package org.visualpaper.work.km.server.impl.application.adapter;

import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;
import org.visualpaper.work.km.server.impl.application.adapter.param.RegisterUserParam;
import org.visualpaper.work.km.server.impl.application.adapter.param.UpdateUserParam;
import org.visualpaper.work.km.server.impl.domain.user.UserId;

@Component
public class UserAdapter {

  @Nonnull
  public RegisterUserParam registerUserParamFrom(
      @Nonnull int id,
      @Nonnull String name
  ) {
    return new RegisterUserParam(
        UserId.from(id),
        name
    );
  }

  @Nonnull
  public UpdateUserParam updateUserNameParamFrom(
      @Nonnull int id,
      @Nonnull String name
  ) {
    return new UpdateUserParam(
        UserId.from(id),
        name
    );
  }
}
