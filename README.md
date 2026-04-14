# Phần 1: Tư duy giải pháp (Phân tích I/O & Kiến trúc) - MedBooking

## 1. Lỗ hổng bảo mật khi chỉ kiểm duyệt tại Frontend
**Câu hỏi:** Vì sao đã có JavaScript chặn ở Frontend rồi mà hệ thống vẫn lọt tuổi âm (-5) khi dùng Postman?

**Phân tích nguyên nhân:**
JavaScript hoạt động ở phía Client (trình duyệt của người dùng). Nó đóng vai trò như một lớp "lọc ảo" giúp tăng trải nghiệm người dùng (UX) bằng cách báo lỗi nhanh chóng mà không cần đợi Server phản hồi. Tuy nhiên, lớp bảo vệ này hoàn toàn vô tác dụng trước các cuộc tấn công trực tiếp.

Các công cụ như Postman, cURL, hoặc các đoạn script tự động cho phép người dùng đóng gói dữ liệu (HTTP Request) và bắn thẳng vào địa chỉ API của Server (Backend). Lúc này, dữ liệu **bỏ qua hoàn toàn giao diện HTML và các hàm chặn của JavaScript**. 

**Kết luận:** Nếu Backend không có cơ chế tự kiểm duyệt (Server-side Validation) mà mù quáng tin tưởng dữ liệu truyền tới, rác dữ liệu như `age = -5` sẽ dễ dàng lọt qua và được lưu thẳng vào cơ sở dữ liệu. Backend Validation là chốt chặn bảo mật bắt buộc đối với mọi hệ thống.

---

## 2. Phân tích bẫy dữ liệu (Edge Cases)
**Câu hỏi:** Để giải quyết triệt để Bẫy 1 (`patientName = "   "`), chọn annotation nào trong 3 loại: `@NotNull`, `@NotEmpty`, hay `@NotBlank`?

**Giải quyết:** Để trị triệt để lỗi người dùng chỉ nhập khoảng trắng, giải pháp duy nhất và chính xác nhất là sử dụng **`@NotBlank`**.

**Lý do loại trừ và lựa chọn:**
* **@NotNull:** Chỉ kiểm tra dữ liệu có tồn tại hay không (tức là khác `null`). Chuỗi chứa 3 dấu cách `"   "` là một chuỗi có tồn tại trong bộ nhớ, nên `@NotNull` sẽ **cho qua**.
* **@NotEmpty:** Tiêu chuẩn cao hơn một chút, yêu cầu dữ liệu khác `null` VÀ có độ dài (length) > 0. Tuy nhiên, chuỗi `"   "` có độ dài bằng 3, do đó `@NotEmpty` vẫn bị đánh lừa và **cho qua**.
* **@NotBlank (Lựa chọn tối ưu):** Cơ chế hoạt động của annotation này là tự động cắt bỏ toàn bộ khoảng trắng ở hai đầu chuỗi (hành động `trim()`), sau đó mới kiểm tra độ dài. Khi chuỗi `"   "` bị cắt hết dấu cách, nó trở thành chuỗi rỗng `""` (có độ dài = 0). Ngay lập tức, `@NotBlank` sẽ phát hiện ra sự bất thường và **chặn lại thành công**.
