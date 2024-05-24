package org.visualpaper.work.km.server.impl.infrastructure.repository;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.visualpaper.work.km.server.impl.domain.user.UserId;
import org.visualpaper.work.km.server.impl.exceptions.KmException;
import org.visualpaper.work.km.server.impl.infrastructure.db.dao.StoredDao;

@Repository
public class StoredSampleRepositoryImpl {

  @Autowired
  private StoredDao dao;

  @Nullable
  public void storedProcedureSample(@Nonnull UserId id) throws KmException {
    Map<String, Object> params = new HashMap<>();

    params.put("target_id", id.value());
    dao.storedProcedureSample(params);

    System.out.println(params.get("id"));
    System.out.println(params.get("name"));
  }
}
