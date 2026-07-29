<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" value="Thông tin sinh viên" scope="request" />
<%@ include file="../includes/header.jsp" %>
<%@ include file="../includes/menu.jsp" %>

<div class="container">
    <h2>Thông Tin Cá Nhân Sinh Viên</h2>
    <p class="small-text" style="margin-bottom: 20px;">Vui lòng nhập đầy đủ và chính xác thông tin cá nhân trước khi tiến hành chọn học phần.</p>


    </select>
    <form action="${pageContext.request.contextPath}/student" method="POST">
        <div class="form-group">
            <label>Giao diện</label>
            <select name="theme">
                <option value="light"${theme == 'light' || empty theme ? 'selected' : ''}>Light
                </option>
                <option value="dark"${theme == 'dark' ? 'selected' : ''}>Dark
                </option>
            </select>
            <label for="mssv">Mã số sinh viên (MSSV) *</label>
            <input type="text" id="mssv" name="mssv" value="${not empty student ? student.mssv : mssv}" placeholder="Ví dụ: 2026001">
            <c:if test="${not empty errors.mssv}">
                <span class="error-text">${errors.mssv}</span>
            </c:if>
        </div>

        <div class="form-group">
            <label for="fullName">Họ và tên *</label>
            <input type="text" id="fullName" name="fullName" value="${not empty student ? student.fullName : ''}" placeholder="Nguyễn Văn A">
            <c:if test="${not empty errors.fullName}">
                <span class="error-text">${errors.fullName}</span>
            </c:if>
        </div>

        <div class="form-group">
            <label for="email">Email liên hệ *</label>
            <input type="text" id="email" name="email" value="${not empty student ? student.email : ''}" placeholder="student@university.edu.vn">
            <c:if test="${not empty errors.email}">
                <span class="error-text">${errors.email}</span>
            </c:if>
        </div>

        <div class="form-group">
            <label for="phone">Số điện thoại</label>
            <input type="text" id="phone" name="phone" value="${not empty student ? student.phone : ''}" placeholder="0901234567">
        </div>

        <div class="form-group">
            <label for="major">Ngành học</label>
            <select id="major" name="major">
                <option value="Toán tin" ${major == 'Toán tin' ? 'selected' : ''}>Toán tin</option>
                <option value="Khoa học máy tính" ${major == 'Khoa học máy tính' ? 'selected' : ''}>Khoa học máy tính</option>
                <option value="Hệ thống thông tin" ${major == 'Hệ thống thông tin' ? 'selected' : ''}>Hệ thống thông tin</option>
                <option value="Công nghệ thông tin" ${major == 'Công nghệ thông tin' ? 'selected' : ''}>Công nghệ thông tin</option>
            </select>
        </div>

        <div class="form-group">
            <label for="enrollmentPeriod">Khóa tuyển</label>
            <input type="text" id="enrollmentPeriod" name="enrollmentPeriod" value="${not empty student ? student.enrollmentPeriod : '2023'}" placeholder="2023">
        </div>

        <div class="form-group">
            <label for="note">Ghi chú</label>
            <textarea id="note" name="note" rows="3" placeholder="Ghi chú thêm (nếu có)">${not empty student ? student.note : ''}</textarea>
        </div>

        <div style="margin-top: 24px;">
            <button type="submit" class="btn btn-primary">Lưu và Tiếp tục chọn học phần</button>
        </div>
    </form>
</div>

<%@ include file="../includes/footer.jsp" %>