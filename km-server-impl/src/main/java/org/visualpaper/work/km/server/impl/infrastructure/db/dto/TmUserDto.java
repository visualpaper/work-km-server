package org.visualpaper.work.km.server.impl.infrastructure.db.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
    @Column(name = "ID")
    private Integer id;

    @Column(name = "NAME")
    private String name;
}