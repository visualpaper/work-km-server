package org.visualpaper.work.km.server.impl.domain.user;


import jakarta.annotation.Nonnull;

public record User(@Nonnull UserId id, @Nonnull String name) {

}
