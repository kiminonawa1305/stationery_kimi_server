package com.lamnguyen.stationery_kimi.controller.admin;

import com.lamnguyen.stationery_kimi.dto.*;
import com.lamnguyen.stationery_kimi.entity.Product;
import com.lamnguyen.stationery_kimi.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/api/products")
public class ProductManagerController {
    @Autowired
    private IProductService productService;

    @GetMapping("/get")
    public DatatableApiResponse<List<ProductDetailDTO>> get(@RequestParam(required = false) Map<String, Object> query) {
        DatatableApiRequest request = DatatableApiRequest.newInstance(query);
        List<ProductDetailDTO> categories = productService.findAll(request);

        return DatatableApiResponse.<List<ProductDetailDTO>>builder()
                .data(categories)
                .draw(request.getDraw())
                .recordsTotal(categories.size())
                .recordsFiltered(categories.size())
                .build();
    }

    @PostMapping("/add")
    public ApiResponse<ProductDTO> addProduct(@ModelAttribute Product product) {
        ProductDTO productDTO = productService.addProduct(product);
        return ApiResponse.<ProductDTO>builder()
                .message("Thêm sản phẩm thành công!")
                .data(productDTO)
                .build();
    }

    @PutMapping("/update")
    public ApiResponse<ProductDTO> updateProduct(@ModelAttribute Product product) {
        ProductDTO productDTO = productService.updateProduct(product);
        return ApiResponse.<ProductDTO>builder()
                .message("Cập nhật sản phẩm thành công!")
                .data(productDTO)
                .build();
    }

    @PutMapping("/lock")
    public ApiResponse<ProductDTO> lockProduct(@ModelAttribute Product product) {
        ProductDTO result = productService.lockProductById(product);
        return ApiResponse.<ProductDTO>builder()
                .message("Khóa sản phẩm thành công!")
                .data(result)
                .build();
    }
}
