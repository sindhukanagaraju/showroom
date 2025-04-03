package com.showroommanagement.controller;

import com.showroommanagement.dto.ResponseDTO;
<<<<<<< HEAD
import com.showroommanagement.entity.User;
import com.showroommanagement.service.UserService;
import com.showroommanagement.util.Constant;
import org.springframework.http.HttpStatus;
=======
import com.showroommanagement.dto.SignInDTO;
import com.showroommanagement.dto.SignUpDTO;
import com.showroommanagement.service.UserService;
import com.showroommanagement.util.Constant;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
>>>>>>> 1bb0e5d (first commit)
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    private final UserService userService;

    public UserController(final UserService userService) {
        this.userService = userService;
    }

<<<<<<< HEAD
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
=======
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/user/admin")
    public ResponseDTO adminCreate(@RequestBody final SignUpDTO signUpDTO) {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.CREATE, this.userService.adminCreate(signUpDTO));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','EMPLOYEE')")
    @PostMapping("/user/employee")
    public ResponseDTO employeeCreate(@RequestBody final SignUpDTO signUpDTO) {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.CREATE, this.userService.employeeCreate(signUpDTO));
    }

    @PreAuthorize("hasAnyAuthority('CUSTOMER')")
    @PostMapping("/user/customer")
    public ResponseDTO customerCreate(@RequestBody final SignUpDTO signUpDTO) {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.CREATE, this.userService.customerCreate(signUpDTO));
    }

    @PostMapping("/user/signIn")
    public ResponseDTO signIn(@RequestBody final SignInDTO signInDTO) {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.SIGN_IN, this.userService.signIn(signInDTO));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("/user/{id}")
    public ResponseDTO retrieveUserById(@PathVariable final int id) {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.RETRIEVE, this.userService.retrieveUserById(id));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("/user")
>>>>>>> 1bb0e5d (first commit)
    public ResponseDTO retrieveUser() {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.RETRIEVE, this.userService.retrieveUser());
    }

<<<<<<< HEAD
    @PutMapping("/user/{id}")
    public ResponseDTO updateUser(@PathVariable final Integer id, @RequestBody final User user) {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.UPDATE, this.userService.updateUserById(user, id));
    }

    @DeleteMapping("/user/{id}")
    public ResponseDTO removeUserById(@PathVariable final Integer id) {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.REMOVE, this.userService.removeUserById(id));
=======
    @PreAuthorize("hasAnyAuthority('ADMIN','EMPLOYEE','CUSTOMER')")
    @DeleteMapping("/user/{id}")
    public ResponseDTO deleteByAdminId(@PathVariable final int id) {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.DELETE, this.userService.deleteById(id));
    }

    @PostMapping("user/refresh-token")
    public ResponseDTO refreshToken(@RequestParam final String refreshToken) {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.TOKEN, this.userService.refreshToken(refreshToken));
>>>>>>> 1bb0e5d (first commit)
    }
}
