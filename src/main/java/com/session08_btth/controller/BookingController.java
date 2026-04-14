package com.session08_btth.controller;

import com.session08_btth.model.dto.BookingDto;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/booking")
public class BookingController {

    @GetMapping("/create")
    public String showForm(Model model) {
        model.addAttribute("booking", new BookingDto());
        return "booking-form";
    }

    @PostMapping("/create")
    public String submitForm(
            @Valid @ModelAttribute("booking") BookingDto bookingDto,
            BindingResult bindingResult, // VƯỢT BẪY 2: BindingResult BẮT BUỘC phải nằm ngay sát sau đối tượng @Valid
            Model model) {               // Model hoặc các tham số khác phải đẩy ra phía sau BindingResult

        // Nếu có lỗi, không ném ra 400 mà trả lại trang form để Thymeleaf hiển thị lỗi đỏ
        if (bindingResult.hasErrors()) {
            return "booking-form";
        }

        return "redirect:/booking/success";
    }
}
