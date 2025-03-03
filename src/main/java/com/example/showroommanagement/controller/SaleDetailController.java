package com.example.showroommanagement.controller;

import com.example.showroommanagement.dto.ResponseDTO;
import com.example.showroommanagement.entity.SaleDetail;
import com.example.showroommanagement.service.SaleDetailService;
import com.example.showroommanagement.util.Constant;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class SaleDetailController {


    private final SaleDetailService saleDetailService;

    public SaleDetailController(final SaleDetailService saleDetailService) {
        this.saleDetailService = saleDetailService;
    }

    @PostMapping("/sale")
    public ResponseDTO createSales(@RequestBody final SaleDetail salesDetail) {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.CREATE, this.saleDetailService.createSales(salesDetail));

    }

    @GetMapping("/sale/{id}")
    public ResponseDTO retrieveSalesById(@PathVariable final Integer id) {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.RETRIEVE, this.saleDetailService.retrieveSalesById(id));

    }

    @GetMapping("/sale/retrieve")
    public ResponseDTO retrieveSales() {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.RETRIEVE, this.saleDetailService.retrieveSales());
    }

    @PutMapping("/sale/{id}")
    public ResponseDTO updateSalesById(@PathVariable final Integer id, @RequestBody final SaleDetail saleDetail) {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.UPDATE, this.saleDetailService.updateSalesById(saleDetail, id));
    }

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
