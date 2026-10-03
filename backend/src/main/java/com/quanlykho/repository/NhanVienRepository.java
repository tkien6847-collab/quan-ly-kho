package com.quanlykho.repository;// thuộc file này

import com.quanlykho.entity.NhanVien;// cho phép sử dụng entity NhanVien trong repository
import org.springframework.data.jpa.repository.JpaRepository;//dùng để sử dụng jpa repository, cung cấp các phương thức để thao tác với csdl , tìm  sửa ....
import java.util.Optional;//class có sẵn của java trả về kq có hoặc không có dữ liệu, tránh lỗi null pointer exception khi tìm kiếm dữ liệu trong csdl

// tạo 1 repository để làm việc với nv , extend là kế thừa từ JpaRepository để sử dụng các phương thức có sẵn của jpa repository, NhanVien là entity, Long là kiểu dữ liệu của khóa chính
public interface NhanVienRepository extends JpaRepository<NhanVien, Long> {
    Optional<NhanVien> findByTenDangNhap(String tenDangNhap);

    boolean existsByTenDangNhap(String tenDangNhap);

    boolean existsByEmail(String email);

    Optional<NhanVien> findByTenDangNhapAndMatKhau(String tenDangNhap, String matKhau);
    //Tìm trong bảng nhan_vien nhân viên có ten_dang_nhap và mat_khau giống dữ liệu truyền vào.
}
//repo dùng để thao tác với cơ sở dữ liệu, nó cung cấp các phương thức để truy vấn, lưu trữ, cập nhật và xóa dữ liệu từ bảng nhan_vien.