package com.selivanov.mapper;

import com.selivanov.dto.UserDto;
import com.selivanov.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toUserDto(User user);

    List<UserDto> toUserDtoList(List<User> userList);

    void updateUser(@MappingTarget User updatableUse, UserDto userDto);
}