package org.visualpaper.work.km.server.impl.application.impl;

import jakarta.annotation.Nonnull;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.visualpaper.work.km.server.impl.application.UserFacade;
import org.visualpaper.work.km.server.impl.application.adapter.param.RegisterUserParam;
import org.visualpaper.work.km.server.impl.application.adapter.param.UpdateUserParam;
import org.visualpaper.work.km.server.impl.domain.user.User;
import org.visualpaper.work.km.server.impl.domain.user.UserId;
import org.visualpaper.work.km.server.impl.domain.user.UserRepository;
import org.visualpaper.work.km.server.impl.exceptions.KmException;

@Service
public class UserFacadeImpl implements UserFacade {

  @Autowired
  private UserRepository repository;

  @Override
  public User getUser(UserId id) throws KmException {
    return repository.find(id);
  }

  @Nonnull
  @Override
  public List<User> getUsers() throws KmException {
    return repository.findAll();
  }

  @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Throwable.class)
  @Override
  public void registerUser(@Nonnull RegisterUserParam param) throws KmException {
    repository.insert(
        new User(param.id(), param.name())
    );
  }

  @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Throwable.class)
  @Override
  public void updateUser(@Nonnull UpdateUserParam param) throws KmException {
    repository.update(
        new User(param.id(), param.name())
    );
  }

  @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Throwable.class)
  @Override
  public void deleteUser(@Nonnull UserId id) throws KmException {
    repository.delete(id);
  }
}
