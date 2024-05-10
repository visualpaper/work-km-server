package org.visualpaper.work.km.server.impl.infrastructure.db.dao;

import java.util.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.visualpaper.work.km.server.impl.infrastructure.db.dto.TmUserDto;

/**
 * TM_USER テーブル DAO.
 */
@Repository
public interface TmUserDao extends JpaRepository<TmUserDto, Integer> {

  // * Cursor が必要なら Cusror<TmUserDto> になるのだと思うが、
  //   Cursor 処理は基本複雑になりがちなのでハマりぽいんとがあるかもしれない。
  // * 引数は多すぎなければ以下のように普通に Java の引数で渡すで良いと思う。
  @Procedure(name = "GET_USER_SAMPLE_PROCEDURE_NAME")
  Map<String, ?> getUserSampleProcedure(@Param("target_id") Integer target_id);
}
