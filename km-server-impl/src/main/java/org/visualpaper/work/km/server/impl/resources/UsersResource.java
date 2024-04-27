package org.visualpaper.work.km.server.impl.resources;

import jakarta.annotation.Nonnull;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.visualpaper.work.km.server.impl.application.UserFacade;
import org.visualpaper.work.km.server.impl.application.adapter.UserAdapter;
import org.visualpaper.work.km.server.impl.domain.user.User;
import org.visualpaper.work.km.server.impl.domain.user.UserId;
import org.visualpaper.work.km.server.impl.openapi.api.UsersApi;
import org.visualpaper.work.km.server.impl.openapi.model.EditUserRequest;
import org.visualpaper.work.km.server.impl.openapi.model.RegistUserRequest;
import org.visualpaper.work.km.server.impl.openapi.model.UserSchema;
import org.visualpaper.work.km.server.impl.openapi.model.UsersResponse;

@RestController
public class UsersResource implements UsersApi {

  @Autowired
  private UserFacade facade;

  @Autowired
  private UserAdapter adapter;

  /**
   * Create User API.
   */
  @Nonnull
  @Override
  public ResponseEntity<Void> postUser(RegistUserRequest request) throws Exception {

    facade.registerUser(
        adapter.registerUserParamFrom(
            request.getId(),
            request.getName()
        )
    );
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  /**
   * Get Users API.
   */
  @Nonnull
  @Override
  public ResponseEntity<UsersResponse> getUsers() throws Exception {
    List<User> result = facade.getUsers();

    return ResponseEntity.status(HttpStatus.OK)
        .body(
            new UsersResponse()
                .users(
                    result.stream()
                        .map(this::createUserSchema)
                        .collect(Collectors.toList())
                )
        );
  }

  @Nonnull
  private UserSchema createUserSchema(@Nonnull User user) {
    return new UserSchema()
        .id(user.id().value())
        .name(user.name());
  }

  /**
   * Update User API.
   */
  @Nonnull
  @Override
  public ResponseEntity<Void> putUser(Integer id, EditUserRequest editUserRequest)
      throws Exception {

    facade.updateUser(
        adapter.updateUserNameParamFrom(
            id,
            editUserRequest.getName()
        )
    );
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  /**
   * Delete User API.
   */
  @Nonnull
  @Override
  public ResponseEntity<Void> deleteUser(Integer id) throws Exception {

    facade.deleteUser(
        UserId.from(id)
    );
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
