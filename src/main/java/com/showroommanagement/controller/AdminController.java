package com.showroommanagement.controller;

import com.showroommanagement.dto.ResponseDTO;
import com.showroommanagement.entity.Admin;
import com.showroommanagement.service.AdminService;
import com.showroommanagement.util.Constant;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class AdminController {
    private final AdminService adminService;

    public AdminController(final AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/admin")
    public ResponseDTO createAdmin(@RequestBody final Admin admin) {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.CREATE, this.adminService.createAdmin(admin));
    }

    @GetMapping("/admin/{id}")
    public ResponseDTO retrieveAdminById(@PathVariable final Integer id) {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.RETRIEVE, this.adminService.retrieveAdminById(id));

    }

    @GetMapping("/admin/retrieve")
    public ResponseDTO retrieveAdmin() {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.RETRIEVE, this.adminService.retrieveAdmin());
    }

    @PutMapping("/admin/{id}")
    public ResponseDTO updateAdmin(@PathVariable final Integer id, @RequestBody final Admin admin) {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.UPDATE, this.adminService.updateAdminById(admin, id));
    }

    @DeleteMapping("/admin/{id}")
    public ResponseDTO removeAdminById(@PathVariable final Integer id) {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.REMOVE, this.adminService.removeAdminById(id));
    }
}
