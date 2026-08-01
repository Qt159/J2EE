# Cổng Đăng Ký Học Phần Trực Tuyến

## 1. Giới thiệu

Cổng Đăng Ký Học Phần Trực Tuyến là hệ thống hỗ trợ sinh viên đăng ký các học phần.

Hệ thống cho phép sinh viên nhập thông tin cá nhân, tìm kiếm học phần, thêm học phần vào giỏ đăng ký, kiểm tra thông tin trước khi xác nhận và xuất phiếu đăng ký sau khi hoàn tất.

Dự án được xây dựng nhằm áp dụng kiến thức lập trình web với J2EE sử dụng mô hình Servlet/JSP, quản lý session và xử lý dữ liệu phía server.

### Demo Online
Application URL:
[http://54.255.197.227:8080/23110056_PhamQuocTuan_J2EE_FinalProject-1.0-SNAPSHOT/](http://54.255.197.227:8080/23110056_PhamQuocTuan_J2EE_FinalProject-1.0-SNAPSHOT/)

## 2. Công nghệ sử dụng

### Backend
- Java Servlet
- JSP (JavaServer Pages)
- Apache Tomcat (8.5+)

### Frontend
- HTML
- CSS

### Quản lý dự án
- Git / GitHub

### Deployment
- AWS EC2 Instance
- VPC
- Public Subnet
- Internet Gateway
- Route Table
- Security Group
- Key Pair

## 3. Chức năng chính

### Quản lý thông tin sinh viên
- Nhập thông tin sinh viên:
    - MSSV
    - Họ tên
    - Email
    - Số điện thoại
    - Ngành học
    - Khóa học
    - Ghi chú (nếu có)
  
### Tra cứu học phần
- Hiển thị danh sách học phần
- Tìm kiếm học phần theo:
    - Mã học phần
    - Tên học phần

### Giỏ đăng ký học phần
- Thêm học phần vào danh sách đăng ký (không cho phép thêm trùng)
- Xóa từng học phần khỏi giỏ
- Làm trống toàn bộ giỏ đăng ký
- Hủy phiên đăng ký (`session.invalidate()`) — xóa toàn bộ dữ liệu phiên
- Tính toán:
    - Tổng số học phần
    - Tổng số tín chỉ
    - Tổng học phí dự kiến
- Hiển thị thông tin kỹ thuật session: Session ID, thời điểm tạo session, số lần truy cập trang giỏ

### Xác nhận đăng ký
- Kiểm tra thông tin sinh viên
- Kiểm tra danh sách học phần
- Tạo mã đăng ký
- Hiển thị phiếu xác nhận

### Xuất phiếu đăng ký
- Xuất thông tin đăng ký ra file `.txt`
- Bao gồm:
    - Mã đăng ký
    - Thời gian xác nhận
    - Thông tin sinh viên
    - Danh sách học phần
    - Tổng tín chỉ
    - Tổng học phí

### Giao diện

- Hỗ trợ Dark Mode
- Sử dụng Session để lưu trạng thái người dùng

## 4. Luồng hoạt động của hệ thống
![SystemFlow](images/SystemFlowDiagram.drawio.png)

## 5. Lý do lựa chọn hình thức JSP Include

Dự án sử dụng **`<%@ include file="..." %>`** (Static Include) cho tất cả các thành phần dùng chung (`header.jsp`, `menu.jsp`, `footer.jsp`).

**Lý do lựa chọn Static Include:**
- `<%@ include %>` được xử lý tại **compile time** — nội dung file được nhúng trực tiếp vào trang JSP thành một Servlet duy nhất, giúp hiệu năng tốt hơn vì không cần thêm request/response cycle.
- Các thành phần `header`, `menu`, `footer` có nội dung **tĩnh, không thay đổi theo từng request**, nên không cần dynamic include.
- Tiêu đề trang (`pageTitle`) được truyền qua `<c:set var="pageTitle" scope="request">` trước khi include, thay thế cho `jsp:param` — cách này hoạt động được với static include vì cùng chung scope.

**Khi nào dùng `<jsp:include>`:**  
`<jsp:include>` phù hợp hơn khi component cần nhận tham số động qua `<jsp:param>` hoặc khi nội dung thay đổi theo từng request. Trong project này không có nhu cầu đó nên Static Include là lựa chọn phù hợp.

## 6. Quản lý Session và Cookie
Hệ thống sử dụng HttpSession để lưu trữ dữ liệu trong quá trình đăng ký.

Các dữ liệu được lưu trong Session:

| Attribute    | Mục đích                               |
|--------------|----------------------------------------|
| student      | Lưu thông tin sinh viên                |
| cart         | Lưu danh sách học phần đang chọn       |
| registration | Lưu thông tin đăng ký sau khi xác nhận |
| cartVisit    | Lưu số lần truy cập giỏ đăng ký        |

Ngoài HttpSession, hệ thống sử dụng Cookie để lưu một số thông tin người dùng nhằm cải thiện trải nghiệm khi quay lại trang.

| Cookie | Mục đích |
|---|---|
| mssv | Lưu mã số sinh viên để điền sẵn vào form |
| major | Lưu ngành học để điền sẵn vào form |
| theme | Lưu chế độ giao diện (sáng/tối) |

Người dùng có thể xóa toàn bộ cookie đã lưu bằng liên kết **"Xóa thông tin đã lưu"** trên trang nhập thông tin sinh viên. Hệ thống không lưu bất kỳ dữ liệu nhạy cảm nào (email, số điện thoại) vào cookie.

## 7. Kiến trúc xử lý
![SystemArchitecture](images/SystemArchitectureDiagram.drawio.png)

### Controller
Xử lý request từ người dùng:

- StudentServlet
- CourseServlet
- CartServlet
- ConfirmServlet
- ExportServlet

### DTO
Đóng gói dữ liệu nhận từ request:
- StudentRequestDTO
- CourseSearchRequestDTO
- CartRequestDTO
### Service
Xử lý nghiệp vụ:
- CourseService
### Mapper
Chuyển đổi dữ liệu:
- StudentMapper

### Model
Các Java Bean đại diện dữ liệu:
- StudentBean
- CourseBean
- RegistrationBean

### View
Các trang JSP:
- Trang nhập sinh viên
- Trang danh sách học phần
- Trang giỏ đăng ký
- Trang xác nhận

---

## 8. Cách chạy dự án ở local (Windows)

### Yêu cầu môi trường

- JDK 17+
- Maven
- Apache Tomcat 8.5+

### 1. Clone project

```bash
git clone https://github.com/Qt159/J2EE
cd J2EE
```

### 2. Build project

```bash
mvn clean package
```

Sau khi build thành công sẽ tạo file:

```text
target/
└── 23110056_PhamQuocTuan_J2EE_FinalProject-1.0-SNAPSHOT.war
```

### 3. Deploy lên Tomcat

Copy file WAR vào thư mục:

```text
<TOMCAT_HOME>\webapps\
```

Ví dụ:

```text
D:\apache-tomcat-8.5.43\apache-tomcat-8.5.43\webapps\
```

### 4. Khởi động Tomcat

Mở Command Prompt hoặc PowerShell:

```powershell
cd D:\apache-tomcat-8.5.43\apache-tomcat-8.5.43\bin
.\startup.bat
```
### 5. Truy cập ứng dụng

```text
http://localhost:8080/23110056_PhamQuocTuan_J2EE_FinalProject-1.0-SNAPSHOT/
```
---

## Chạy bằng IntelliJ IDEA

1. Mở project.
2. Build Project.
3. Chạy Tomcat Configuration.
4. Truy cập:

```text
http://localhost:8080/23110056_PhamQuocTuan_J2EE_FinalProject_war_exploded/
```
## 9. Deployment
![Infra](images/Infrastructure.drawio.png)

### Deployment Flow
```text
User
 |
 v
Internet
 |
 v
Internet Gateway
 |
 v
Route Table
 |
 v
Security Group
 |
 v
EC2 Instance
 |
 v
Apache Tomcat
 |
 v
J2EE Application (.war)
```

### Các thành phần:
- VPC
- Public Subnet
- Internet Gateway
- Route Table
- Security Group
- EC2



## 10. Cấu trúc dự án

```text
23110056_PhamQuocTuan_J2EE_FinalProject
│
├── src
│   └── main
│       ├── java
│       │   └── com.tuan
│       │       ├── controller
│       │       │   ├── StudentServlet.java
│       │       │   ├── CourseServlet.java
│       │       │   ├── CartServlet.java
│       │       │   ├── ConfirmServlet.java
│       │       │   └── ExportServlet.java
│       │       │
│       │       ├── dto
│       │       │   ├── CartRequestDTO.java
│       │       │   ├── CourseSearchRequestDTO.java
│       │       │   └── StudentRequestDTO.java
│       │       ├── model
│       │       │   ├── StudentBean.java
│       │       │   ├── CourseBean.java
│       │       │   └── RegistrationBean.java
│       │       │
│       │       ├── service
│       │       │   └── CourseService.java
│       │       ├── mapper
│       │       │   └── StudentMapper.java
│       │       ├── error
│       │       │   └── ErrorMessages.java
│       │       └── util
│       │       │   ├── HtmlUtils.java
│       │       │   └── ValidationUtils.java
│       │
│       └── webapp
│           ├── views
│           │   ├── cart.jsp
│           │   ├── confirmation.jsp
│           │   ├── course-list.jsp
│           │   └── error.jsp
│           │   ├── search-result.jsp
│           │   └── student-form.jsp
│           │
│           ├── includes
│           │   ├── header.jsp
│           │   ├── menu.jsp
│           │   └── footer.jsp
│           │
│           ├── css
│           │   └── style.css
│
├── pom.xml
├── README.md
└── images
    ├── SystemFlowDiagram.drawio.png
    ├── SystemArchitectureDiagram.drawio.png
    └── Infrastructure.drawio.png
```
## 11. Thành viên thực hiện
Phạm Quốc Tuấn
