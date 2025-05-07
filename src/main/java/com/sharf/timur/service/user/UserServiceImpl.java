package com.sharf.timur.service.user;

import com.sharf.timur.domain.User;
import com.sharf.timur.dto.user.UserRequest;
import com.sharf.timur.dto.user.UserResponse;
import com.sharf.timur.exception.AlreadyExistException;
import com.sharf.timur.exception.ResourceNotFoundException;
import com.sharf.timur.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper mapper;
    private final UserRepository repository;

    @Override
    public Long addUser(UserRequest request) {
        if (repository.existsByEmail(request.email()))
            throw new AlreadyExistException("email " + request.email() + " is already registered");

        User user = new User();
        user.setLastName(request.lastName());
        user.setFirstName(request.firstName());
        user.setSurName(request.surName());
        user.setEmail(request.email());
        user.setDateOfBirth(request.dateOfBirth());
        return repository.save(user).getId();
    }

    @Override
    public UserResponse getUser(Long id) {
        return repository.findById(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("user with id:" + id + " hasn't found"));
    }

    @Override
    public Long updateUser(UserRequest request, Long id) {
        return repository.findById(id)
                .map(user -> {
                    user.setLastName(request.lastName());
                    user.setFirstName(request.firstName());
                    user.setSurName(request.surName());
                    user.setEmail(request.email());
                    user.setDateOfBirth(request.dateOfBirth());
                    return repository.save(user);
                })
                .map(User::getId)
                .orElseThrow(() -> new ResourceNotFoundException("user with id:" + id + " hasn't found"));
    }

    @Override
    public void deleteUser(Long id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("user with id:" + id + " hasn't found"));

        repository.delete(user);
    }
}
