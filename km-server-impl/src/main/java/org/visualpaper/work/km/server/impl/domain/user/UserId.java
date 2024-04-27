package org.visualpaper.work.km.server.impl.domain.user;

import jakarta.annotation.Nonnull;

public record UserId(int value) {

  @Nonnull
  public static UserId from(int id) {
    return new UserId(id);
  }
}
