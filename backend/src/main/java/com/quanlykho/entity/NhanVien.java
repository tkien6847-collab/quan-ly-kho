package com.quanlykho.entity;
//cho biết class này nằm trong com.quanlykho.entity

import jakarta.persistence.Entity;//phải có nó  @Entity hoạt động các import khác cũng như vậy
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;// dùng để đặt khóa chính cho id
import jakarta.persistence.Table;

@Entity//báo với jpa rằng đây là một thực thể (entity) và sẽ được ánh xạ tới một bảng trong cơ sở dữ liệu.
@Table(name = "nhan_vien")//ứng bảng nhân viên
public class NhanVien {
//private tính đóng gói bên ngoài không thể sửa mà phải qua setter
        
    @Id// Mã nhân viên - khóa chính
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maNV;

    // Tên đăng nhập
    private String tenDangNhap;

    // Mật khẩu
    private String matKhau;

    // Họ và tên
    private String hoTen;

    // Email
    private String email;

    // Số điện thoại
    private String soDienThoai;

    // Vai trò: QUAN_LY hoặc NHAN_VIEN
    private String vaiTro;

    // Constructor rỗng tạo nhân viên chưa có dữ liệu , jpa và hibernate lấy duex liệu từ db điền vào object
    public NhanVien() {
    }

    // Getter và Setter
    public Long getMaNV() {
        return maNV;
    }

    public void setMaNV(Long maNV) {
        this.maNV = maNV;
    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getVaiTro() {
        return vaiTro;
    }

    public void setVaiTro(String vaiTro) {
        this.vaiTro = vaiTro;
    }
}
// khai báo entity,khai báo bảng , khai báo khóa chính và các thành phần bên trong
//sau đó contructor rỗng và getter setter để jpa hibernate lấy dữ liệu từ db điền vào object