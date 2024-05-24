package org.visualpaper.work.km.server.impl.infrastructure.db.dao;

import jakarta.annotation.Nonnull;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StoredDao {

  void storedProcedureSample(@Nonnull Map<String, Object> params);
  void storedFunctionSample(@Nonnull Map<String, Object> params);
}
