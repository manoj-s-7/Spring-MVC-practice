package com.manojs.springmvc.service.impl;

import com.manojs.springmvc.dto.UserRequestDTO;
import com.manojs.springmvc.dto.UserResponseDTO;
import com.manojs.springmvc.dto.UserUpdateDTO;
import com.manojs.springmvc.entity.User;
import com.manojs.springmvc.exception.ResourceNotFoundException;
import com.manojs.springmvc.mapper.UserMapper;
import com.manojs.springmvc.repository.UserRepo;
import com.manojs.springmvc.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final UserMapper userMapper;

    @Override
    public List<UserResponseDTO> findAllUsers() {
        List<User> users = userRepo.findAll();
        if (users.isEmpty()) {
            throw new ResourceNotFoundException("No users found");
        }
        return users.stream()
                .map(userMapper::toDto)
                .toList();
    }

    @Override
    public UserResponseDTO findUserById(Long id) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + id));

        return userMapper.toDto(user);
    }

    @Override
    public UserResponseDTO addUser(UserRequestDTO dto) {
        User user = userMapper.toEntity(dto);
        User savedUser = userRepo.save(user);
        return userMapper.toDto(savedUser);
    }

    @Override
    @Transactional
    public UserResponseDTO updateUser(Long id, UserRequestDTO dto) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + id));

        userMapper.updateEntity(user, dto);

        return userMapper.toDto(user);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + id));

        userRepo.delete(user);
    }

    @Override
    @Transactional
    public UserResponseDTO updateUserDetails(final Long id, UserUpdateDTO userUpdateDTO) {
        User userById = userRepo.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("User not found: " + id));

        userMapper.updateUserDetails(userById , userUpdateDTO);
        return userMapper.toDto(userById);
    }
}