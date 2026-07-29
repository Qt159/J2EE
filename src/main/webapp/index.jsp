<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<c:set var="pageTitle" value="Trang chủ" scope="request" />
<%@ include file="includes/header.jsp" %>
<%@ include file="includes/menu.jsp" %>
<main>
<div class="container">
    <h2>Trang Chủ Hệ Thống Quản Lý Đăng Ký Học Phần</h2>
    <p><strong>Tên dự án:</strong> Cổng đăng ký học phần trực tuyến (J2EE Final Project)</p>
    <p><strong>Sinh viên thực hiện:</strong> Phạm Quốc Tuấn - 23110056</p>
    <p><strong>Thời gian máy chủ:</strong> <%= new java.util.Date() %></p>

    <hr style="margin: 20px 0; border: 0; border-top: 1px solid var(--border-color);">

    <h3>Giới thiệu hệ thống</h3>
    <p>Hệ thống hỗ trợ sinh viên tra cứu học phần, quản lý danh sách đăng ký tín chỉ, theo dõi khối lượng học tập và xuất phiếu đăng ký học phần chính thức theo đúng chuẩn quy chế đào tạo tín chỉ.</p>

    <div style="margin-top: 24px;">
        <a href="${pageContext.request.contextPath}/student" class="btn btn-primary">Bắt đầu nhập thông tin</a>
    </div>
</div>
</main>
<%@ include file="includes/footer.jsp" %>