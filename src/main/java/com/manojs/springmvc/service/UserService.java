package com.manojs.springmvc.service;

import com.manojs.springmvc.dto.UserRequestDTO;
import com.manojs.springmvc.dto.UserResponseDTO;

import java.util.List;
import java.util.Map;

public interface UserService {

    List<UserResponseDTO> findAllUsers();

    UserResponseDTO findUserById(Long id);

    UserResponseDTO addUser(UserRequestDTO dto);

    UserResponseDTO updateUser(Long id, UserRequestDTO dto);

    void deleteUser(Long id);

    UserResponseDTO partialUpdate(Long id,Map<String,Object> updates);
}
