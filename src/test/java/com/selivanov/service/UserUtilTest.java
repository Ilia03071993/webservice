package com.selivanov.service;

import com.selivanov.exception.NoValidAgeException;
import com.selivanov.exception.NoValidEmailException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UserUtilTest {
    //system under test
    private final UserUtil userUtil = new UserUtil();

    @Test
    void getAgeFromBirthday_whenBirthdayIsCorrect() {
        //Given
        String birthday = "1997-08-09";

        //When
        Integer age = userUtil.getAgeFromBirthday(birthday);

        //Then
        Assertions.assertEquals(28, age);
    }

    @Test
    void getAgeFromBirthday_whenBirthdayIsNotCorrect() {
        //Given
        String birthday = "1997-08-09";

        //When
        Integer age = userUtil.getAgeFromBirthday(birthday);

        //Then
        Assertions.assertNotEquals(29, age);
    }

    @Test
    void getEmail_whenEmailIsCorrect() {
        //Given
        String email = "lui@mail.ru";

        //When
        String utilEmail = userUtil.getEmail(email);

        //Then
        Assertions.assertEquals(email, utilEmail);
    }

    @Test
    void getEmail_whenEmailIsBlank() {
        //Given
        String email = "";
        //Then
        Assertions.assertThrows(NoValidEmailException.class,
                () -> {
                    userUtil.getEmail(email);
                });
    }

    @Test
    void getEmail_whenEmailIsNotCorrect() {
        //Given
        String email = "liu@1.1.1";
        //Then
        Assertions.assertThrows(NoValidEmailException.class,
                () -> {
                    userUtil.getEmail(email);
                });
    }

    @Test
    void getAgeCategory_whenAgeExceedThreshold() {
        //Given
        int age = 131;
        //Then
        Assertions.assertThrows(NoValidAgeException.class,
                () -> userUtil.getAgeCategory(age));
    }

    @Test
    void getAgeCategory_WhenAgeCategoryIsChild(){
        //Given
        int age = 10;
        String ageCategory = "CHILD";

        //When
        String userUtilAgeCategory = userUtil.getAgeCategory(age);

        //Then
        Assertions.assertEquals(ageCategory, userUtilAgeCategory);
    }

    @Test
    void getAgeCategory_WhenAgeCategoryIsTeen(){
        //Given
        int age = 19;
        String ageCategory = "TEEN";

        //When
        String userUtilAgeCategory = userUtil.getAgeCategory(age);

        //Then
        Assertions.assertEquals(ageCategory, userUtilAgeCategory);
    }

    @Test
    void getAgeCategory_WhenAgeCategoryIsAdult(){
        //Given
        int age = 21;
        String ageCategory = "ADULT";

        //When
        String userUtilAgeCategory = userUtil.getAgeCategory(age);

        //Then
        Assertions.assertEquals(ageCategory, userUtilAgeCategory);
    }
}