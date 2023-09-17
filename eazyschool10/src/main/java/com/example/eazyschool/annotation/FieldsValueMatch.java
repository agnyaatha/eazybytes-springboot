package com.example.eazyschool.annotation;

import com.example.eazyschool.validations.FieldsValueMatchValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint( validatedBy = FieldsValueMatchValidator.class)
@Target( {ElementType.TYPE} )
@Retention( RetentionPolicy.RUNTIME )
public @interface FieldsValueMatch {

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    String message() default "Fields values don't match!";

    String field();

    String fieldMatch();

    @Target( {ElementType.TYPE} )
    @Retention( RetentionPolicy.RUNTIME )
    @interface List {
        FieldsValueMatch[] value();
    }
}

/*
The @Constraint annotation specifies the class that will be used to validate the data. In this case, the class is FieldsValueMatchValidator.

The @Target annotation specifies the kind of elements that can be annotated with this constraint. In this case, the annotation can be applied to a class.

The @Retention annotation specifies the lifespan of the annotation. In this case, the annotation will be retained at runtime.

The groups and payload parameters are used to control how the constraint is applied. The groups parameter specifies the validation groups that the constraint belongs to. The payload parameter specifies the payload that is associated with the constraint.

The message parameter specifies the error message that is displayed if the constraint is violated.

The field parameter specifies the name of the field that the constraint is applied to.

The fieldMatch parameter specifies the name of the field that the value of the field parameter should be matched against.

The List annotation is a nested annotation that can be used to define multiple FieldsValueMatch annotations on a class.

In summary, the code you have provided defines a custom constraint annotation that can be used to validate that the values of two fields are the same.
 */
