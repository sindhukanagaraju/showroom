package com.showroommanagement.controller;

import com.showroommanagement.dto.ResponseDTO;
import com.showroommanagement.entity.User;
import com.showroommanagement.service.UserService;
import com.showroommanagement.util.Constant;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    private final UserService userService;

    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user/sign/up")
    public ResponseDTO createSignUp(@RequestBody final User user) {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.SIGN_UP, this.userService.createSignUp(user));
    }

    @PostMapping("/user/sign/in")
    public ResponseDTO createSignIn(@RequestBody final User user) {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.SIGN_IN, this.userService.createSignIn(user));
    }

    @GetMapping("/user/{id}")
    public ResponseDTO retrieveUserById(@PathVariable final Integer id) {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.RETRIEVE, this.userService.retrieveUserById(id));
    }

    @GetMapping("/user/retrieve")
    public ResponseDTO retrieveUser() {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.RETRIEVE, this.userService.retrieveUser());
    }

    @PutMapping("/user/{id}")
    public ResponseDTO updateUser(@PathVariable final Integer id, @RequestBody final User user) {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.UPDATE, this.userService.updateUserById(user, id));
    }

    @DeleteMapping("/user/{id}")
    public ResponseDTO removeUserById(@PathVariable final Integer id) {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.REMOVE, this.userService.removeUserById(id));
    }
}
