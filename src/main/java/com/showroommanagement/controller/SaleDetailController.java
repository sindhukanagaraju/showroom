package com.showroommanagement.controller;

import com.showroommanagement.dto.ResponseDTO;
import com.showroommanagement.entity.SaleDetail;
import com.showroommanagement.service.SaleDetailService;
import com.showroommanagement.util.Constant;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class SaleDetailController {

    private final SaleDetailService saleDetailService;

    public SaleDetailController(final SaleDetailService saleDetailService) {
        this.saleDetailService = saleDetailService;
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','EMPLOYEE','CUSTOMER')")
    @PostMapping("/sale")
    public ResponseDTO createSales(@RequestBody final SaleDetail salesDetail) {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.CREATE, this.saleDetailService.createSales(salesDetail));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','EMPLOYEE','CUSTOMER')")
    @GetMapping("/sale/{id}")
    public ResponseDTO retrieveSalesById(@PathVariable final Integer id) {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.RETRIEVE, this.saleDetailService.retrieveSalesById(id));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','EMPLOYEE','CUSTOMER')")
    @GetMapping("/sale/retrieve")
    public ResponseDTO retrieveSales() {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.RETRIEVE, this.saleDetailService.retrieveSales());
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PutMapping("/sale/{id}")
    public ResponseDTO updateSalesById(@PathVariable final Integer id, @RequestBody final SaleDetail saleDetail) {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.UPDATE, this.saleDetailService.updateSalesById(saleDetail, id));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @DeleteMapping("/sale/{id}")
    public ResponseDTO removeSalesById(@PathVariable final Integer id) {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.REMOVE, this.saleDetailService.removeSaleDetailById(id));
    }

    @GetMapping("/sale/details")
    public ResponseDTO retrieveSaleDetail(@RequestParam final String showroomName, @RequestParam final String productModel) {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.RETRIEVE, this.saleDetailService.retrieveSaleDetail(showroomName, productModel));
    }

    @GetMapping("/sale/page")
    public ResponseDTO pagination(@RequestParam(defaultValue = "1") final int pageIndex, @RequestParam(defaultValue = "2") final int pageSize) {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.RETRIEVE, this.saleDetailService.pagination(pageIndex, pageSize));
    }

    @GetMapping("/sale/model/search")
    public ResponseDTO findByModel(@RequestParam final String keyword) {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.RETRIEVE, this.saleDetailService.findByModel(keyword));
    }

    @GetMapping("/sale/detail/sort")
    public ResponseDTO getPaginatedData(@RequestParam(defaultValue = "1") final int pageIndex, @RequestParam(defaultValue = "2") final int pageSize, @RequestParam(defaultValue = "2") final String sorting, @RequestParam(defaultValue = "true") final boolean direction) {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.RETRIEVE, this.saleDetailService.getPaginatedData(pageIndex, pageSize, sorting, direction));
    }

    @GetMapping("/sale/colour/search")
    public ResponseDTO searchByColour(@RequestParam(defaultValue = "1") final int pageIndex, @RequestParam(defaultValue = "2") final int pageSize, @RequestParam final String keyword) {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.RETRIEVE, this.saleDetailService.searchByColour(pageIndex, pageSize, keyword));
    }
}
