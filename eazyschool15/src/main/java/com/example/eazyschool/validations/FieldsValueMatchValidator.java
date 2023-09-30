package com.example.eazyschool.validations;

import com.example.eazyschool.annotation.FieldsValueMatch;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

public class FieldsValueMatchValidator implements ConstraintValidator<FieldsValueMatch, Object> {

    private String field;
    private String fieldMatch;

    @Override
    public void initialize(FieldsValueMatch constraintAnnotation){
        this.field = constraintAnnotation.field();
        this.fieldMatch = constraintAnnotation.fieldMatch();
    }

    @Override
    public boolean isValid( Object value, ConstraintValidatorContext context ){
        Object fieldValue = new BeanWrapperImpl(value).getPropertyValue( field );
        Object fieldMatchValue = new BeanWrapperImpl(value).getPropertyValue( fieldMatch );
        if(fieldValue != null){
            return fieldValue.equals( fieldMatchValue );
        }else{
            return fieldMatchValue == null;
        }
    }

}

/*
It is used to validate that the values of two fields are the same.

The FieldsValueMatchValidator class implements the ConstraintValidator interface. This interface specifies the methods that must be implemented by a custom validator class.

The initialize() method is called when the validator is created. This method is used to initialize the validator with the parameters of the constraint annotation. In this case, the parameters are the field and fieldMatch parameters.

The isValid() method is called to validate the data in a field or property. This method returns true if the data is valid and false if the data is invalid. In this case, the method checks if the values of the field and fieldMatch parameters are the same.

The BeanWrapperImpl class is used to get the value of a field from an object.

The code you have provided is a concise and efficient way to implement a custom validator class for Spring Boot.
 */
