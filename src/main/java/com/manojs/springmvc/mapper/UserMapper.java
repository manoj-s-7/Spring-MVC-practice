package com.manojs.springmvc.mapper;

import com.manojs.springmvc.dto.UserRequestDTO;
import com.manojs.springmvc.dto.UserResponseDTO;
import com.manojs.springmvc.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper{
    UserResponseDTO toDto(User user);
    User toEntity(UserRequestDTO userRequestDTO);
    void updateEntity(@MappingTarget User user, UserRequestDTO dto);
}