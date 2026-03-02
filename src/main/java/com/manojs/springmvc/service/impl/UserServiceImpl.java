package com.manojs.springmvc.service.impl;

import com.manojs.springmvc.dto.UserRequestDTO;
import com.manojs.springmvc.dto.UserResponseDTO;
import com.manojs.springmvc.entity.User;
import com.manojs.springmvc.exception.ResourceNotFoundException;
import com.manojs.springmvc.mapper.UserMapper;
import com.manojs.springmvc.repository.UserRepo;
import com.manojs.springmvc.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    @Override
    public List<UserResponseDTO> findAllUsers(){
        List<User> users = userRepo.findAll();
        if (users.isEmpty()) {
            throw new ResourceNotFoundException("No users found");
        }
        return users.stream()
                .map(UserMapper::toDTO)
                .toList();
    }

    @Override
    public UserResponseDTO findUserById(Long id) {

        User user = userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + id));

        return UserMapper.toDTO(user);
    }

    @Override
    public UserResponseDTO addUser(UserRequestDTO dto) {

        User user = UserMapper.toEntity(dto);

        User savedUser = userRepo.save(user);

        return UserMapper.toDTO(savedUser);
    }

    @Override
    @Transactional
    public UserResponseDTO updateUser(Long id, UserRequestDTO dto) {

        User user = userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + id));

        UserMapper.updateEntity(user, dto);

        return UserMapper.toDTO(user);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {

        User user = userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + id));

        userRepo.delete(user);
    }
}