package com.quanlykho.repository;// thuộc file này

import com.quanlykho.entity.NhanVien;// cho phép sử dụng entity NhanVien trong repository
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

// Repository cung cấp các thao tác cơ bản với dữ liệu nhân viên.NhanVienRepository kế thừa các chức năng có sẵn của JpaRepository.
public interface NhanVienRepository extends JpaRepository<NhanVien, Long> {
    Optional<NhanVien> findByTenDangNhapAndMatKhau(String tenDangNhap, String matKhau);
    //Tìm trong bảng nhan_vien nhân viên có ten_dang_nhap và mat_khau giống dữ liệu truyền vào.
}
//tối xem lại ở trên