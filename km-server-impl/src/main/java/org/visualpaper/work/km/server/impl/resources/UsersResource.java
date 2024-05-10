package org.visualpaper.work.km.server.impl.resources;

import jakarta.annotation.Nonnull;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.visualpaper.work.km.server.impl.KmLogCode;
import org.visualpaper.work.km.server.impl.application.UserFacade;
import org.visualpaper.work.km.server.impl.application.adapter.UserAdapter;
import org.visualpaper.work.km.server.impl.domain.user.User;
import org.visualpaper.work.km.server.impl.domain.user.UserId;
import org.visualpaper.work.km.server.impl.exceptions.KmException;
import org.visualpaper.work.km.server.impl.resources.schemas.users.EditUserRequestSchema;
import org.visualpaper.work.km.server.impl.resources.schemas.users.GetUserResponseScheme;
import org.visualpaper.work.km.server.impl.resources.schemas.users.RegistUserRequestSchema;
import org.visualpaper.work.km.server.impl.resources.schemas.users.UserSchema;
import org.visualpaper.work.km.server.impl.resources.schemas.users.GetUsersResponseScheme;

@RestController
public class UsersResource {

  @Autowired
  private UserFacade facade;

  @Autowired
  private UserAdapter adapter;

  /**
   * Create User API.
   */
  @Nonnull
  @RequestMapping(
      method = RequestMethod.POST,
      value = "/v1/users",
      produces = { "application/json" },
      consumes = { "application/json" }
  )
  public ResponseEntity<Void> postUser(
      @RequestBody RegistUserRequestSchema request
  ) throws Exception {

    facade.registerUser(
        adapter.registerUserParamFrom(
            request.getId(),
            request.getName()
        )
    );
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  /**
   * Get User API.
   */
  @Nonnull
  @RequestMapping(
      method = RequestMethod.GET,
      value = "/v1/users/{id}",
      produces = { "application/json" }
  )
  public ResponseEntity<GetUserResponseScheme> getUser(
      @PathVariable("id") Integer id
  ) throws Exception {
    User result = facade.getUser(UserId.from(id));

    if (result == null) {
      throw new KmException(
          KmLogCode.NOT_FOUND,
          HttpStatus.NOT_FOUND
      );
    }
    GetUserResponseScheme schema = new GetUserResponseScheme();
    schema.setId(result.id().value());
    schema.setName(result.name());
    return ResponseEntity.status(HttpStatus.OK)
        .body(schema);
  }

  /**
   * Get Users API.
   */
  @Nonnull
  @RequestMapping(
      method = RequestMethod.GET,
      value = "/v1/users",
      produces = { "application/json" }
  )
  public ResponseEntity<GetUsersResponseScheme> getUsers() throws Exception {
    List<User> result = facade.getUsers();

    GetUsersResponseScheme schema = new GetUsersResponseScheme();
    schema.setUsers(
        result.stream()
            .map(this::createUserSchema)
            .collect(Collectors.toList())
    );
    return ResponseEntity.status(HttpStatus.OK)
        .body(schema);
  }

  @Nonnull
  private UserSchema createUserSchema(@Nonnull User user) {
    UserSchema schema = new UserSchema();
    schema.setId(user.id().value());
    schema.setName(user.name());

    return schema;
  }

  /**
   * Update User API.
   */
  @Nonnull
  @RequestMapping(
      method = RequestMethod.PUT,
      value = "/v1/users/{id}",
      produces = { "application/json" },
      consumes = { "application/json" }
  )
  public ResponseEntity<Void> putUser(
      @PathVariable("id") Integer id,
      @RequestBody EditUserRequestSchema request
  ) throws Exception {

    facade.updateUser(
        adapter.updateUserNameParamFrom(
            id,
            request.getName()
        )
    );
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  /**
   * Delete User API.
   */
  @Nonnull
  @RequestMapping(
      method = RequestMethod.DELETE,
      value = "/v1/users/{id}",
      produces = { "application/json" }
  )
  public ResponseEntity<Void> deleteUser(
      @PathVariable("id") Integer id
  ) throws Exception {

    facade.deleteUser(
        UserId.from(id)
    );
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
