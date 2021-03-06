package org.ujar.basics.restful.jwtauth.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.ujar.basics.restful.jwtauth.dto.UserDto;
import org.ujar.basics.restful.jwtauth.entity.User;
import org.ujar.basics.restful.jwtauth.service.UserService;

@RestController
@RequestMapping(value = "/api/v1/users/")
@RequiredArgsConstructor
public class UserController {
  private final UserService userService;

  @GetMapping(value = "{id}")
  public ResponseEntity<UserDto> getUserById(@PathVariable(name = "id") final Long id) {
    User user = userService.findById(id);

    if (user == null) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    UserDto result = UserDto.fromUser(user);

    return new ResponseEntity<>(result, HttpStatus.OK);
  }
}
