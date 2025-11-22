package com.selivanov.mapper;

import com.selivanov.dto.UserDto;
import com.selivanov.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toEntity(UserDto userDto);

    UserDto toUserDto(User user);

    List<UserDto> toUserDtoList(List<User> userList);

    @Mapping(target = "id", ignore = true)
    void updateUser(@MappingTarget User updatableUser, UserDto userDto);
}