CREATE DATABASE IF NOT EXISTS quan_ly_kho;
USE quan_ly_kho;
-- dán câu lệnh trên vào MySQL Workbench và chạy để tạo cơ sở dữ liệu 



INSERT INTO nhan_vien(maNV,ten_dang_nhap, mat_khau, ho_ten, email, so_dien_thoai, vai_tro)
VALUES
('20246026','keen19072006', 'Keendz1977@', 'Nguyen Van Test', 'test01@gmail.com', '0900000001', 'NHAN_VIEN');

-- nhóm mình dùng Hibernate để tạo bảng , xem mẫu ở NhanVien.java nằm ở package entity 
-- tạo xong  thì tiếp tục tạo repository  service và controller để thao tác với bảng nhân viên nhé
-- mai sau khi làm về các bảng khác thì mọi người có thể làm tương tự như vậy 
-- bước cuối tải postman về test API 
-- file 01_tao_database.sql này chỉ tạo database thôi nhé , các bảng như nhân viên , sản phẩm  sẽ được tạo bằng Hibernate
-- đồng thời ở file này mọi người có thể insert thêm giá trị, insert xong nó sẽ hiện cả ở workbench
-- ví dụ ở dưới
-- Là framework dùng Java để xây dựng backend/API nhanh hơn.
-- ở project này dùng spring boot để tạo Controller Service Repository API
-- JPA là bộ quy tắc/tiêu chuẩn giúp Java làm việc với database
-- @ Entity là của JPA. Class NhanVien trong Java tương ứng với dữ liệu trong database.
-- còn Hibernate chạy phía sau để giao tiếp với MySQL. ví dụ  repository.findAll();

