package com.lamnguyen.stationery_kimi.controller;

import com.lamnguyen.stationery_kimi.dto.ProductDisplayDTO;
import com.lamnguyen.stationery_kimi.exception.ApplicationException;
import com.lamnguyen.stationery_kimi.exception.ErrorCode;
import com.lamnguyen.stationery_kimi.response.ApiResponse;
import com.lamnguyen.stationery_kimi.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductRestController {
    @Autowired
    private IProductService productService;

    @GetMapping("/page-{page}")
    public ApiResponse<List<ProductDisplayDTO>> findAllByLockFalse(@PathVariable("page") Integer page) {
        List<ProductDisplayDTO> result = productService.findAllByLockFalse(20, page);
        if (result.isEmpty()) throw new ApplicationException(ErrorCode.PRODUCT_NOT_FOUND);
        return ApiResponse.<List<ProductDisplayDTO>>builder()
                .message("Thành công!")
                .data(result)
                .build();
    }


}