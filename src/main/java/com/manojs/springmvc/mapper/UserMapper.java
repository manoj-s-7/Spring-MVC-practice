package com.manojs.springmvc.mapper;

import com.manojs.springmvc.dto.UserRequestDTO;
import com.manojs.springmvc.dto.UserResponseDTO;
import com.manojs.springmvc.entity.User;

public final class UserMapper {

    private UserMapper() {
        // utility class
    }

    public static User toEntity(UserRequestDTO dto) {

        if (dto == null) {
            return null;
        }

        User user = new User();
        user.setName(dto.name());
        user.setEmail(dto.email());
        user.setPassword(dto.password());

        return user;
    }

    public static UserResponseDTO toDTO(User user) {

        if (user == null) {
            return null;
        }

        return new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }

    public static void updateEntity(User user, UserRequestDTO dto) {

        if (dto == null || user == null) {
            return;
        }

        if (dto.name() != null) {
            user.setName(dto.name());
        }

        if (dto.email() != null) {
            user.setEmail(dto.email());
        }

        if (dto.password() != null) {
            user.setPassword(dto.password());
        }
    }
}