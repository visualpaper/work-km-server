package org.visualpaper.work.km.server.impl.infrastructure.repository;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.StoredProcedureQuery;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
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

  /*
   * StoredProcedure の例
  @Nullable
  @Override
  public User find(@Nonnull UserId id) throws KmException {
    StoredProcedureQuery query = this.em.createStoredProcedureQuery("GET_USER_SAMPLE_PROCEDURE");
    query.registerStoredProcedureParameter("target_id", Integer.class, ParameterMode.IN);
    query.registerStoredProcedureParameter("id", Integer.class, ParameterMode.OUT);
    query.registerStoredProcedureParameter("name", String.class, ParameterMode.OUT);
    query.setParameter("target_id", id.value());
    query.execute();

    Integer resultId = (Integer) query.getOutputParameterValue("id");
    String resultName = (String) query.getOutputParameterValue("name");
    if (resultId == null) {
      return null;
    }
    return new User(UserId.from(resultId), resultName);
  }
  */

  /*
   * StoredFunction の例
   */
  @Nullable
  @Override
  public User find(@Nonnull UserId id) throws KmException {
    Query query = this.em.createNativeQuery(
        "SELECT GET_USER_SAMPLE_FUNCTION(:target_id) from dual"
    );
    query.setParameter("target_id", id.value());
    Object out = query.getSingleResult();

    return new User(UserId.from(1), "aaa");
  }

  @Nonnull
  @Override
  public List<User> findAll() throws KmException {
    StoredProcedureQuery query = this.em.createStoredProcedureQuery("GET_USERS_SAMPLE_CURSOR_PROCEDURE");
    query.registerStoredProcedureParameter("target_id", Integer.class, ParameterMode.IN);
    query.registerStoredProcedureParameter("out_cur", Class.class, ParameterMode.REF_CURSOR);
    query.setParameter("target_id", 41);
    query.execute();

    List<Object[]> results = query.getResultList();

    return results.stream()
        .map(this::toUser)
        .collect(Collectors.toList());
  }

  @Nonnull
  public User toUser(Object[] result) {
    return new User(UserId.from(((BigDecimal) result[0]).intValue()), (String)result[1]);
  }

  /*
   * とりあえず退避
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
  */

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
