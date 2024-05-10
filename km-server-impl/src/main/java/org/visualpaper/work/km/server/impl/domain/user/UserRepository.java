package org.visualpaper.work.km.server.impl.domain.user;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.util.List;
import org.visualpaper.work.km.server.impl.exceptions.KmException;

public interface UserRepository {

  @Nullable
  User find(@Nonnull UserId userId) throws KmException;

  @Nonnull
  List<User> findAll() throws KmException;

  void insert(@Nonnull User user) throws KmException;

  void update(@Nonnull User user) throws KmException;

  void delete(@Nonnull UserId userId) throws KmException;
}
