package org.visualpaper.work.km.server.impl.infrastructure.db.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.visualpaper.work.km.server.impl.infrastructure.db.dto.TmUserDto;

/**
 * TM_USER テーブル DAO.
 */
@Repository
public interface TmUserDao extends JpaRepository<TmUserDto, Integer> {

}
