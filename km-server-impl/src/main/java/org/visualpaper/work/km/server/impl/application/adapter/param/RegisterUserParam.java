package org.visualpaper.work.km.server.impl.application.adapter.param;

import jakarta.annotation.Nonnull;
import org.visualpaper.work.km.server.impl.domain.user.UserId;

public record RegisterUserParam(@Nonnull UserId id, @Nonnull String name) {

}
