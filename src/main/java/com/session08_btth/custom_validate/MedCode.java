package com.session08_btth.custom_validate;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = MedCodeValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MedCode {
    String message() default "Mã khoa khám phải bắt đầu bằng tiền tố MED_";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}