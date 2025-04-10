package com.cengiz.natsjava.base.util.annotation;


import com.cengiz.natsjava.base.util.ValidationUtil;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;



public class TcNoValidator implements ConstraintValidator<TcNo, Long> {

  @Override
  public boolean isValid(Long value, ConstraintValidatorContext context) {

    if (value == null) {
      return true;
    }

    return ValidationUtil.tcNoValidation(value);
  }


}
