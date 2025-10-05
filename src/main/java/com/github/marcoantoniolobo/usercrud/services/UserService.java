package com.github.marcoantoniolobo.usercrud.services;

import java.util.List;
import com.github.marcoantoniolobo.usercrud.dto.*;

public interface UserService {
    UserDTO create(CreateUserRequest req);
    List<UserDTO> getAll();
    UserDTO getById(Long id);
    UserDTO update(Long id, UpdateUserRequest req);
    void delete(Long id);
}
