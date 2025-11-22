package com.selivanov.dto.error;

import java.util.List;

public record ValidationErrorResponse(
        Integer status,
        String description,
        List<FieldError> errors
) {
}