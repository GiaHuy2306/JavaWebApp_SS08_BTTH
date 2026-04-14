package com.session08_btth.custom_validate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class MedCodeValidator implements ConstraintValidator<MedCode, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // VƯỢT BẪY 3: Xử lý an toàn khi departmentCode là null để tránh sập Server (Lỗi 500 NPE).
        // Trách nhiệm check null thuộc về @NotNull, validator này chỉ check tiền tố.
        if (value == null) {
            return true;
        }

        // Kiểm tra tiền tố MED_
        return value.startsWith("MED_");
    }
}
