package com.lamnguyen.stationery_kimi.controller.admin;

import com.lamnguyen.stationery_kimi.dto.*;
import com.lamnguyen.stationery_kimi.entity.Category;
import com.lamnguyen.stationery_kimi.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/admin/api/categories")
public class CategoryManagerRestController {
    @Autowired
    private ICategoryService iCategoryService;

    @GetMapping("/get")
    public DatatableApiResponse<List<CategoryManager>> get(@RequestParam(required = false) Map<String, Object> query) {
        DatatableApiRequest request = DatatableApiRequest.newInstance(query);
        List<CategoryManager> categories = iCategoryService.findAll(request);

        return DatatableApiResponse.<List<CategoryManager>>builder()
                .data(categories.stream().skip(request.getStart()).limit(request.getLength()).toList())
                .draw(request.getDraw())
                .recordsTotal(categories.size())
                .recordsFiltered(categories.size())
                .build();
    }

    @PostMapping("/create")
    public EditDataTableResponse create(@RequestParam Map<String, Object> request) {
        String name = (String) request.get("name");
        CategoryManager categoryManager = iCategoryService.addCategory(Category.builder()
                .name(name)
                .build());
        return EditDataTableResponse.builder()
                .data(categoryManager)
                .build();
    }

    @PutMapping(value = "/edit/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public EditDataTableResponse update(@RequestBody(required = false) Map<String, Object> request, @PathVariable("id") Long id) {
        Map<String, String> data = (Map<String, String>) request.get(id.toString());
        String name = data.get("name");
        Category category = Category.builder()
                .id(id)
                .name(name)
                .build();
        CategoryManager categoryDTO = iCategoryService.updateCategory(category);
        return EditDataTableResponse.builder()
                .data(categoryDTO)
                .build();
    }

    @PostMapping("/lock/{id}")
    public ApiResponse<CategoryManager> lockProduct(@PathVariable("id") Long id) {
        CategoryManager result = iCategoryService.lock(id);
        return ApiResponse.<CategoryManager>builder()
                .message("Thành công!")
                .data(result)
                .build();
    }
}
