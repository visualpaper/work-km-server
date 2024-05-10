package org.visualpaper.work.km.server.impl.infrastructure.db.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedStoredProcedureQueries;
import jakarta.persistence.NamedStoredProcedureQuery;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureParameter;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

/**
 * TM_USER テーブル Entity.
 */
@NamedStoredProcedureQueries({ //
    @NamedStoredProcedureQuery(
        name = "GET_USER_SAMPLE_PROCEDURE_NAME",
        procedureName = "GET_USER_SAMPLE_PROCEDURE",
        parameters = {
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "target_id", type = Integer.class),
            @StoredProcedureParameter(mode = ParameterMode.OUT, name = "id", type = Integer.class),
            @StoredProcedureParameter(mode = ParameterMode.OUT, name = "name", type = String.class),
        })
})
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TM_USER")
public class TmUserDto {

  // 必要であれば BigInteger でやること。
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private Integer id;

  @Column(name = "NAME")
  private String name;
}