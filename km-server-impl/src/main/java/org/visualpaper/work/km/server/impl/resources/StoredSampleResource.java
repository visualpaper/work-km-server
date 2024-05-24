package org.visualpaper.work.km.server.impl.resources;

import jakarta.annotation.Nonnull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.visualpaper.work.km.server.impl.domain.user.UserId;
import org.visualpaper.work.km.server.impl.infrastructure.repository.StoredSampleRepositoryImpl;

@RestController
public class StoredSampleResource {

  @Autowired
  private StoredSampleRepositoryImpl repository;

  /**
   * Stored Procedure Sample.
   */
  @Nonnull
  @RequestMapping(
      method = RequestMethod.GET,
      value = "/v1/storedProcedureSample/{id}"
  )
  public ResponseEntity<Void> storedProcedureSample(
      @PathVariable("id") Integer id
  ) throws Exception {
    repository.storedProcedureSample(UserId.from(id));

    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  /**
   * Stored Function Sample.
   */
  @Nonnull
  @RequestMapping(
      method = RequestMethod.GET,
      value = "/v1/storedFunctionSample/{id}"
  )
  public ResponseEntity<Void> storedFunctionSample(
      @PathVariable("id") Integer id
  ) throws Exception {
    repository.storedFunctionSample(UserId.from(id));

    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
