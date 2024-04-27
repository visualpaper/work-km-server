package org.visualpaper.work.km.server.impl.resources;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.visualpaper.work.km.server.impl.openapi.api.HealthCheckApi;

@RestController
public class HealthCheckResource implements HealthCheckApi {

  /**
   * HealthCheck API.
   */
  @Override
  public ResponseEntity<Void> getHealthCheck() throws Exception {
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
