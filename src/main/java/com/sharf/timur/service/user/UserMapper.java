package com.sharf.timur.service.user;

import com.sharf.timur.domain.User;
import com.sharf.timur.dto.user.UserResponse;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {

    public UserResponse toResponse(User r){
        return UserResponse.builder()
                .lastName(r.getLastName())
                .firstName(r.getFirstName())
                .surName(r.getSurName())
                .dateOfBirth(r.getDateOfBirth())
                .email(r.getEmail())
                .build();
    }
}
