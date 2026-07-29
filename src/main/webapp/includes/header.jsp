<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
    String theme = "light";
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if ("theme".equals(cookie.getName())) {
                theme = java.net.URLDecoder.decode(cookie.getValue(), "UTF-8");
                break;
            }
        }
    }
%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>
        ${not empty pageTitle ? pageTitle : 'Cổng Đăng Ký Học Phần'}
    </title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body class="<%= theme %>">
<header>
    <h1>CỔNG ĐĂNG KÝ HỌC PHẦN TRỰC TUYẾN</h1>
    <p>Hệ thống Quản lý Đào tạo Đại học</p>
</header>