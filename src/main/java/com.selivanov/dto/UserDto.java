package com.selivanov.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserDto(
        Integer id,
        String name,
        String lastname,
        @JsonFormat(pattern = "yyyy-MM-dd")
        String birthday,
//        @Email
        @Size(min = 6, max = 100)
        String email
) {
}