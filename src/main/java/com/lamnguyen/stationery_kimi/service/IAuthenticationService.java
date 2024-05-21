package com.lamnguyen.stationery_kimi.service;

import com.lamnguyen.stationery_kimi.dto.UserDTO;
import com.lamnguyen.stationery_kimi.entity.User;
import com.lamnguyen.stationery_kimi.request.UserRegisterRequest;
import com.lamnguyen.stationery_kimi.request.VerifyUserRequest;
import jakarta.validation.Valid;

public interface IAuthenticationService {
    UserDTO login(@Valid String userName, @Valid String password);

    String forgotPassword(@Valid String email);

    UserDTO register(UserRegisterRequest user);

    UserDTO changePassword(User user,@Valid String newPassword);

    UserDTO changeProfile(User user);

    User checkUserExist(@Valid String email);

    UserDTO verify(VerifyUserRequest verifyUserRequest);
}