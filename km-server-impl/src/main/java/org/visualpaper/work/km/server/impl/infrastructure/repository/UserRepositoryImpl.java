package org.visualpaper.work.km.server.impl.infrastructure.repository;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.visualpaper.work.km.server.impl.domain.user.User;
import org.visualpaper.work.km.server.impl.domain.user.UserId;
import org.visualpaper.work.km.server.impl.domain.user.UserRepository;
import org.visualpaper.work.km.server.impl.exceptions.KmException;
import org.visualpaper.work.km.server.impl.infrastructure.db.dao.TmUserDao;
import org.visualpaper.work.km.server.impl.infrastructure.db.dto.TmUserDto;

@Repository
public class UserRepositoryImpl implements UserRepository {

  @PersistenceContext
  private EntityManager em;

  @Autowired
  private TmUserDao dao;

  @Nullable
  @Override
  public User find(@Nonnull UserId id) throws KmException {
    StoredProcedureQuery query = this.em.createNamedStoredProcedureQuery("GET_USER_SAMPLE_PROCEDURE_NAME");
    query.setParameter("target_id", id.value());
    query.execute();
    String name = (String) query.getOutputParameterValue("name");
//    Map<String, ?> map = dao.getUserSampleProcedure(id.value());
//
    if (name == null) {
      return null;
    }
    return new User(id, name);
  }

  @Nonnull
  @Override
  public List<User> findAll() throws KmException {
    return dao.findAll().stream()
        .map(this::toUser)
        .collect(Collectors.toList());
  }

  @Nonnull
  private User toUser(@Nonnull TmUserDto dto) {
    return new User(UserId.from(dto.getId()), dto.getName());
  }

  @Override
  public void insert(@Nonnull User user) throws KmException {
    TmUserDto dto = new TmUserDto();
    dto.setName(user.name());

    dao.save(dto);
  }

  @Override
  public void update(@Nonnull User user) throws KmException {
    TmUserDto dto = new TmUserDto();
    dto.setId(user.id().value());
    dto.setName(user.name());

    dao.save(dto);
  }

  @Override
  public void delete(@Nonnull UserId userId) throws KmException {
    dao.deleteById(userId.value());
  }
}
