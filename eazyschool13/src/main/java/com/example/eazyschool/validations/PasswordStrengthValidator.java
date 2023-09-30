package com.example.eazyschool.validations;

import com.example.eazyschool.annotation.FieldsValueMatch;
import com.example.eazyschool.annotation.PasswordValidator;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;

public class PasswordStrengthValidator implements ConstraintValidator<PasswordValidator, String> {

    List<String> weakPasswords;

    @Override
    public void initialize( PasswordValidator constraintAnnotation ) {
        this.weakPasswords = Arrays.asList("12345", "password", "qwerty");
    }

    @Override
    public boolean isValid( String passwordField, ConstraintValidatorContext constraintValidatorContext ) {
        return passwordField != null && (!weakPasswords.contains( passwordField ));
    }
}
