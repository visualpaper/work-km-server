package org.visualpaper.work.km.server.impl.resources;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckResource {

  /**
   * HealthCheck API.
   */
  @RequestMapping(
      method = RequestMethod.GET,
      value = "/healthCheck",
      produces = { "application/json" }
  )
  public ResponseEntity<Void> getHealthCheck() throws Exception {
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
