package com.showroommanagement.controller;

import com.showroommanagement.dto.ResponseDTO;
import com.showroommanagement.entity.Product;
import com.showroommanagement.service.ProductService;
import com.showroommanagement.util.Constant;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class ProductController {
    private final ProductService productService;

    public ProductController(final ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/product")
    public ResponseDTO createProduct(@RequestBody final Product product) {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.CREATE, this.productService.createProduct(product));
    }

    @GetMapping("/product/{id}")
    public ResponseDTO retrieveProductById(@PathVariable final Integer id) {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.RETRIEVE, this.productService.retrieveProductById(id));
    }

    @GetMapping("/product/retrieve")
    public ResponseDTO retrieveProduct() {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.RETRIEVE, this.productService.retrieveProduct());
    }

    @PutMapping("/product/{id}")
    public ResponseDTO updateProductById(@PathVariable final Integer id, @RequestBody final Product product) {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.UPDATE, this.productService.updateProductById(product, id));
    }

    @DeleteMapping("/product/{id}")
    public ResponseDTO removeProductById(@PathVariable final Integer id) {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.REMOVE, this.productService.removeProductById(id));
    }
}
