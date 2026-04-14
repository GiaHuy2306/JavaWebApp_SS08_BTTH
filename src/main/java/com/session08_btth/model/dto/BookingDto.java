package com.session08_btth.model.dto;

import com.session08_btth.custom_validate.MedCode;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class BookingDto {

    // VƯỢT BẪY 1: Dùng @NotBlank để chặn chuỗi chỉ chứa toàn dấu cách
    @NotBlank(message = "Họ tên không được để trống hoặc chỉ chứa khoảng trắng")
    private String patientName;

    @NotNull(message = "Vui lòng nhập tuổi")
    @Min(value = 0, message = "Tuổi không được nhỏ hơn 0")
    private Integer age;

    @NotBlank(message = "Mã khoa khám không được để trống")
    @MedCode // Sử dụng Custom Validator vừa chế tạo
    private String departmentCode;

    public BookingDto() {
    }

    public BookingDto(String patientName, Integer age, String departmentCode) {
        this.patientName = patientName;
        this.age = age;
        this.departmentCode = departmentCode;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }
}
