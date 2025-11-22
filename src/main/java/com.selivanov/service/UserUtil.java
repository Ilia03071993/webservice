package com.selivanov.service;

import com.selivanov.enums.AgeCategory;
import com.selivanov.exception.NoValidAgeException;
import com.selivanov.exception.NoValidEmailException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;

@Service
public class UserUtil {
    public Integer getAgeFromBirthday(String birthday) {
        LocalDate localDate = LocalDate.parse(birthday);
        Period period = Period.between(localDate, LocalDate.now());
        return period.getYears();
    }

    public String getEmail(String email) {
        if(email.isBlank()) {
            throw new NoValidEmailException("email is blank");
        }

        String regex = "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        if(!email.matches(regex)) {
            throw new NoValidEmailException("email is invalid");
        }

        return email.toLowerCase();
    }

    public String getAgeCategory(Integer age) {
        if(age > 130) {
            throw new NoValidAgeException("Age cannot exceed 130 years");
        }

        if (age <= 12) {
            return AgeCategory.CHILD.name();
        } else if (age <= 20) {
            return AgeCategory.TEEN.name();
        }
        return AgeCategory.ADULT.name();
    }
}