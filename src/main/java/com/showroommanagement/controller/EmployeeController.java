package com.showroommanagement.controller;

import com.showroommanagement.dto.ResponseDTO;
import com.showroommanagement.entity.Employee;
import com.showroommanagement.service.EmployeeService;
import com.showroommanagement.util.Constant;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(final EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/employee")
    public ResponseDTO createEmployee(@RequestBody final Employee employee) {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.CREATE, this.employeeService.createEmployee(employee));

    }

    @GetMapping("/employee/{id}")
    public ResponseDTO retrieveEmployeeById(@PathVariable final Integer id) {
        return new ResponseDTO(HttpStatus.OK.value(),Constant.RETRIEVE, this.employeeService.retrieveEmployeeById(id));
    }

    @GetMapping("/employee/retrieve")
    public ResponseDTO retrieveEmployee() {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.RETRIEVE, this.employeeService.retrieveEmployee());
    }

    @PutMapping("/employee/{id}")
    public ResponseDTO updateEmployeeById(@PathVariable final Integer id, @RequestBody final Employee employee) {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.UPDATE, this.employeeService.updateEmployeeById(employee, id));
    }

    @DeleteMapping("/employee/{id}")
    public ResponseDTO removeEmployeeById(@PathVariable final Integer id) {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.REMOVE, this.employeeService.removeEmployeeById(id));
    }

    @GetMapping("/employee/detail")
    public ResponseDTO retrieveEmployeeDetail() {
        return new ResponseDTO(HttpStatus.OK.value(),Constant.RETRIEVE, this.employeeService.retrieveEmployeeDetail());
    }

    @GetMapping("/employee/name")
    public ResponseDTO countOfName() {
        return new ResponseDTO(HttpStatus.OK.value(),Constant.RETRIEVE,this.employeeService.countOfName());
    }
}
