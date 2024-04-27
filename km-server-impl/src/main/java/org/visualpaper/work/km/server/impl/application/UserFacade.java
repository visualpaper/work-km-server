package org.visualpaper.work.km.server.impl.application;

import jakarta.annotation.Nonnull;
import java.util.List;
import org.visualpaper.work.km.server.impl.application.adapter.param.RegisterUserParam;
import org.visualpaper.work.km.server.impl.application.adapter.param.UpdateUserParam;
import org.visualpaper.work.km.server.impl.domain.user.User;
import org.visualpaper.work.km.server.impl.domain.user.UserId;
import org.visualpaper.work.km.server.impl.exceptions.KmException;

public interface UserFacade {

  @Nonnull
  List<User> getUsers() throws KmException;

  void registerUser(
      @Nonnull RegisterUserParam param
  ) throws KmException;

  void updateUser(
      @Nonnull UpdateUserParam param
  ) throws KmException;

  void deleteUser(
      @Nonnull UserId id
  ) throws KmException;
}
