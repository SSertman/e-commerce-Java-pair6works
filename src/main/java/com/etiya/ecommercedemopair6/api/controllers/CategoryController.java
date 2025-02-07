package com.etiya.ecommercedemopair6.api.controllers;

import com.etiya.ecommercedemopair6.business.abstracts.CategoryService;
import com.etiya.ecommercedemopair6.business.constants.Paths;
import com.etiya.ecommercedemopair6.business.dto.request.concretes.category.CreateCategoryRequest;
import com.etiya.ecommercedemopair6.business.dto.response.concretes.category.CreateCategoryResponse;
import com.etiya.ecommercedemopair6.business.dto.response.concretes.category.GetAllCategoryResponse;
import com.etiya.ecommercedemopair6.business.dto.response.concretes.category.GetCategoryResponse;
import com.etiya.ecommercedemopair6.core.util.result.DataResult;
import com.etiya.ecommercedemopair6.core.util.result.Result;
import com.etiya.ecommercedemopair6.core.util.result.SuccessDataResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Paths.apiPrefix+"categories")
@AllArgsConstructor
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/getAll")
    public ResponseEntity<DataResult<List<GetAllCategoryResponse>>> getAll() {
        return new ResponseEntity<DataResult<List<GetAllCategoryResponse>>>(categoryService.getAll(),HttpStatus.OK);
    }
    @GetMapping("/getById")
    public ResponseEntity<DataResult<GetCategoryResponse>> getById(@RequestParam int id) {
        return new ResponseEntity<DataResult<GetCategoryResponse>>(categoryService.getById(id),HttpStatus.OK);
    }


    @GetMapping("/getAllCategoriesNameDesc")
    public ResponseEntity<DataResult<List<GetAllCategoryResponse>>> getCategoriesNameDesc(String name){
        return new ResponseEntity<DataResult<List<GetAllCategoryResponse>>>(categoryService.getAllCategoriesNameDesc(name),HttpStatus.OK);
    }


    @GetMapping("/getByName")
    public ResponseEntity<DataResult<GetCategoryResponse>> customByName (@RequestParam int id){
    return new ResponseEntity<DataResult<GetCategoryResponse>>(categoryService.getById(id),HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Result> createCategory(CreateCategoryRequest createCategoryRequest){
        return new ResponseEntity<Result>(categoryService.createCategory(createCategoryRequest),HttpStatus.CREATED);
    }
}