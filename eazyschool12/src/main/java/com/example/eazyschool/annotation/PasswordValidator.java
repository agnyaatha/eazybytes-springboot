package com.example.eazyschool.annotation;

import com.example.eazyschool.validations.PasswordStrengthValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint( validatedBy = PasswordStrengthValidator.class)
@Target( {ElementType.METHOD, ElementType.FIELD} )
@Retention( RetentionPolicy.RUNTIME )
public @interface PasswordValidator {

    String message() default "Please choose a strong password";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default{};
}

/*
The @Documented annotation specifies that the annotation should be documented by the JavaDoc generator.

The @Constraint annotation specifies the class that will be used to validate the data. In this case, the class is PasswordStrengthValidator.

The @Target annotation specifies the kind of elements that can be annotated with this constraint. In this case, the annotation can be applied to a method or field.

The @Retention annotation specifies the lifespan of the annotation. In this case, the annotation will be retained at runtime.

The message parameter specifies the error message that is displayed if the constraint is violated.

The groups and payload parameters are used to control how the constraint is applied. The groups parameter specifies the validation groups that the constraint belongs to. The payload parameter specifies the payload that is associated with the constraint.

In summary, the code you have provided defines a custom constraint annotation that can be used to validate the strength of a password.
 */
