package com.lamnguyen.stationery_kimi.controller;

import com.lamnguyen.stationery_kimi.dto.UserDTO;
import com.lamnguyen.stationery_kimi.entity.User;
import com.lamnguyen.stationery_kimi.request.LoginRequest;
import com.lamnguyen.stationery_kimi.request.UserRegisterRequest;
import com.lamnguyen.stationery_kimi.request.VerifyUserRequest;
import com.lamnguyen.stationery_kimi.response.ApiResponse;
import com.lamnguyen.stationery_kimi.service.IAuthenticationService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    @Autowired
    private IAuthenticationService authenticationService;

    @PostMapping("/register")
    public ApiResponse<UserDTO> register(@ModelAttribute @Valid UserRegisterRequest user) {
        UserDTO result = authenticationService.register(user);
        return ApiResponse.<UserDTO>builder()
                .message("Vui lòng kiểm tra email để xác thực tài khoản!")
                .data(result)
                .build();
    }

    @PostMapping("/verify-email")
    public ApiResponse<UserDTO> register(@ModelAttribute @Valid VerifyUserRequest verifyUserRequest) {
        UserDTO result = authenticationService.verify(verifyUserRequest);
        return ApiResponse.<UserDTO>builder()
                .message("Xác thực thành công!")
                .data(result)
                .build();
    }

    @PostMapping("/login")
    public ApiResponse<UserDTO> login(@Valid @ModelAttribute LoginRequest request) {
        String email = request.getEmail();
        String password = request.getPassword();
        UserDTO userDTO = authenticationService.login(email, password);
        return ApiResponse.<UserDTO>builder()
                .message("Đăng nhập thành công!")
                .data(userDTO)
                .build();
    }

    @GetMapping("/test")
    public ApiResponse<String> login() {
        return ApiResponse.<String>builder()
                .message("Hello World!")
                .build();
    }
}
