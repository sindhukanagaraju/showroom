package com.example.showroommanagement.controller;

import com.example.showroommanagement.dto.ResponseDTO;
import com.example.showroommanagement.entity.Department;
import com.example.showroommanagement.service.DepartmentService;
import com.example.showroommanagement.util.Constant;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(final DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping("/department")
    public ResponseDTO createDepartment(@RequestBody final Department department) {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.CREATE, this.departmentService.createDepartment(department));
    }

    @GetMapping("/department/{id}")
    public ResponseDTO retrieveDepartmentById(@PathVariable final Integer id) {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.RETRIEVE, this.departmentService.retrieveDepartmentById(id));
    }

    @GetMapping("/department/retrieve")
    public ResponseDTO retrieveDepartment() {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.RETRIEVE, this.departmentService.retrieveDepartment());
    }

    @PutMapping("/department/{id}")
    public ResponseDTO updateDepartmentById(@PathVariable final Integer id, @RequestBody final Department department) {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.UPDATE, this.departmentService.updateDepartmentById(department, id));
    }

    @DeleteMapping("/department/{id}")
    public ResponseDTO removeDepartmentById(@PathVariable final Integer id) {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.REMOVE, this.departmentService.removeDepartmentById(id));
    }


}
