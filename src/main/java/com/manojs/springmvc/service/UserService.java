package com.manojs.springmvc.service;

import com.manojs.springmvc.dto.UserRequestDTO;
import com.manojs.springmvc.dto.UserResponseDTO;
import com.manojs.springmvc.entity.User;
import com.manojs.springmvc.exception.ResourceNotFoundException;
import com.manojs.springmvc.mapper.UserMapper;
import com.manojs.springmvc.repository.UserRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;

    public UserResponseDTO findUserById(Long id) {

        User user = userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + id));

        return UserMapper.toDTO(user);
    }

    public UserResponseDTO addUser(UserRequestDTO dto) {

        User user = UserMapper.toEntity(dto);

        User savedUser = userRepo.save(user);

        return UserMapper.toDTO(savedUser);
    }

    @Transactional
    public UserResponseDTO updateUser(Long id, UserRequestDTO dto) {

        User user = userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + id));

        UserMapper.updateEntity(user, dto);

        return UserMapper.toDTO(user);
    }

    @Transactional
    public void deleteUser(Long id) {

        User user = userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + id));

        userRepo.delete(user);
    }
}