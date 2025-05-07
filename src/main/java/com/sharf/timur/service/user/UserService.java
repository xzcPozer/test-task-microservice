package com.sharf.timur.service.user;

import com.sharf.timur.dto.user.UserRequest;
import com.sharf.timur.dto.user.UserResponse;

public interface UserService {
    Long addUser(UserRequest request);
    UserResponse getUser(Long id);
    Long updateUser(UserRequest request, Long id);
    void deleteUser(Long id);
}
