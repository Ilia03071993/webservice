package com.selivanov.service;

import com.selivanov.dto.UserDto;
import com.selivanov.entity.User;
import com.selivanov.exception.NoSuchEntityException;
import com.selivanov.mapper.UserMapper;
import com.selivanov.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserUtil userUtil;

    @Transactional(readOnly = true)
    public List<UserDto> getAllUsers() {
        List<User> userList = userRepository.findAll();
        return userMapper.toUserDtoList(userList);
    }

    @Transactional(readOnly = true)
    public UserDto getUserById(int id) {
        User user = userRepository
                .findById(id)
                .orElseThrow(() -> new NoSuchEntityException("User with id " + id + " not found"));
        return userMapper.toUserDto(user);
    }

    @Transactional
    public void saveUser(UserDto userDto) {
        Integer age = userUtil.getAgeFromBirthday(userDto.birthday());

        User user = userMapper.toEntity(userDto);
        user.setAge(age);
        user.setEmail(userUtil.getEmail(userDto.email()));
        user.setAgeCategory(userUtil.getAgeCategory(age));

        userRepository.save(user);
    }

    @Transactional
    public void updateUser(Integer id, UserDto userDto) {
        User updatableUser = userRepository
                .findById(id)
                .orElseThrow(() -> new NoSuchEntityException("User with id " + id + " not found"));
        userMapper.updateUser(updatableUser, userDto);
        userRepository.save(updatableUser);
    }

    @Transactional
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }
}