package com.tuan.error;

public final class ErrorMessages {

    private ErrorMessages() {}
    public static final String REQUIRED_FIELD = "Vui lòng nhập đầy đủ thông tin.";
    public static final String REQUIRED_MSSV = "Mã số sinh viên không được để trống.";
    public static final String INVALID_MSSV = "Mã số sinh viên chỉ được chứa chữ số.";
    public static final String REQUIRED_FULL_NAME = "Họ và tên không được để trống.";
    public static final String REQUIRED_EMAIL = "Email không được để trống.";
    public static final String INVALID_EMAIL = "Email không đúng định dạng.";
    public static final String REQUIRED_PHONE = "Số điện thoại không được để trống.";
    public static final String INVALID_PHONE = "Số điện thoại không hợp lệ.";
    public static final String REQUIRED_MAJOR = "Vui lòng chọn ngành học.";
    public static final String REQUIRED_ENROLLMENT_PERIOD = "Vui lòng chọn khóa tuyển.";
    public static final String COURSE_NOT_FOUND = "Không tìm thấy học phần.";
    public static final String COURSE_ALREADY_EXISTS = "Học phần đã có trong giỏ đăng ký.";
    public static final String COURSE_OUT_OF_SLOT = "Học phần đã hết chỗ.";
    public static final String CART_EMPTY = "Giỏ đăng ký đang trống.";
    public static final String REMOVE_COURSE_FAILED = "Không thể xóa học phần.";
    public static final String CLEAR_CART_FAILED = "Không thể làm trống giỏ đăng ký.";
    public static final String STUDENT_NOT_FOUND = "Chưa có thông tin sinh viên.";
    public static final String REGISTRATION_FAILED = "Đăng ký học phần không thành công.";
    public static final String EXPORT_FAILED = "Không thể xuất phiếu đăng ký.";
    public static final String INVALID_REQUEST = "Yêu cầu không hợp lệ.";
    public static final String INTERNAL_SERVER_ERROR = "Đã xảy ra lỗi trong quá trình xử lý. Vui lòng thử lại sau.";
}