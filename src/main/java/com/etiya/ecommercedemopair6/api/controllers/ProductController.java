package com.etiya.ecommercedemopair6.api.controllers;

import com.etiya.ecommercedemopair6.business.abstracts.ProductService;
import com.etiya.ecommercedemopair6.business.constants.Paths;
import com.etiya.ecommercedemopair6.business.dto.request.concretes.product.CreateProductRequest;
import com.etiya.ecommercedemopair6.business.dto.response.concretes.product.CreateProductResponse;
import com.etiya.ecommercedemopair6.business.dto.response.concretes.product.GetAllProductsResponse;
import com.etiya.ecommercedemopair6.business.dto.response.concretes.product.GetProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Paths.apiPrefix+"products")

public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/getAll")
    public List<GetAllProductsResponse> getAll() {
        return productService.getAll();
    }

    @GetMapping("/getById")
    public GetProductResponse getById(@RequestParam int id) {
        return productService.getById(id);
    }
    @GetMapping("/getByStockGreaterThan")
    public List<GetAllProductsResponse> getAllByStock(@RequestParam("stock") int stock){
        return productService.getAllByStockGreaterThan(stock);
    }
    @GetMapping("/getByName")
    public GetProductResponse getByName(@RequestParam("name")String name){
      return  productService.findByName(name);
    }

    @PostMapping("/add")
    public ResponseEntity<CreateProductResponse> createProduct(@RequestBody CreateProductRequest createProductRequest){
        return new ResponseEntity<CreateProductResponse>(productService.createProduct(createProductRequest), HttpStatus.CREATED);

    }

}