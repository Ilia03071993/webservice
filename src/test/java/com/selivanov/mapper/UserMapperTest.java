package com.selivanov.mapper;

import com.selivanov.dto.UserDto;
import com.selivanov.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.List;

class UserMapperTest {
    //system under test
    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    @Test
    void toUserDto() {
        //Given
        User user = new User();
        user.setId(1);
        user.setName("Mike");
        user.setLastname("Killo");

        //When
        UserDto actual = userMapper.toUserDto(user);

        //Then
        Assertions.assertEquals(1, actual.id());
        Assertions.assertEquals("Mike", actual.name());
        Assertions.assertEquals("Killo", actual.lastname());
    }

    @Test
    void toUserDto2() {
        //Given
        User user = new User();
        user.setId(1);
        user.setName("Mike");
        user.setLastname("Killo");
        user.setAge(26);
        user.setEmail("mike@mail.ru");

        UserDto expected = new UserDto(1, "Mike", "Killo", null, "mike@mail.ru");

        //When
        UserDto actual = userMapper.toUserDto(user);

        //Then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void toUserEntity(){
        //Given
        UserDto userDto = new UserDto(1, "Mike", "Killo", "1999-01-01", "mike@mail.ru");

        User user = new User();
        user.setId(1);
        user.setName("Mike");
        user.setLastname("Killo");
        user.setEmail("mike@mail.ru");

        //When
        User actual = userMapper.toEntity(userDto);

        //Then
        Assertions.assertEquals(user, actual);
    }

    @Test
    void toUserDtoList_shouldMapUserList_whenUserListNotEmpty() {
        //Given
        User user = new User();
        user.setId(1);
        user.setName("Mike");
        user.setLastname("Killo");

        List<User> users = List.of(user);

        //When
        List<UserDto> actual = userMapper.toUserDtoList(users);

        //Then
        Assertions.assertEquals(1, actual.size());
        Assertions.assertEquals(1, actual.getFirst().id());
        Assertions.assertEquals("Mike", actual.getFirst().name());
        Assertions.assertEquals("Killo", actual.getFirst().lastname());
    }

    @Test
    void toUserDtoList_shouldMapUserList_whenUserListEmpty() {
        //Given
        List<User> userList = List.of();

        //When
        List<UserDto> actual = userMapper.toUserDtoList(userList);

        //Then
        Assertions.assertEquals(0, actual.size());
    }

    @Test
    void updateUser() {
        //Given
        User updatableUser = new User();
        updatableUser.setId(1);
        updatableUser.setName("Mike");
        updatableUser.setLastname("Killo");
        updatableUser.setEmail("mike@mail.ru");

        UserDto userDto = new UserDto(1, "Bob", "Silton", "1993-01-01", "bob@mail.ru");

        //When
        userMapper.updateUser(updatableUser, userDto);

        //Then
        Assertions.assertEquals("Bob", updatableUser.getName());
        Assertions.assertEquals("Silton", updatableUser.getLastname());
    }
}