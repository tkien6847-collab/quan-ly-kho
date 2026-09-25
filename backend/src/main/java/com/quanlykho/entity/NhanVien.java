package com.quanlykho.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "nhan_vien")
public class NhanVien {

    // Mã nhân viên - khóa chính
    @Id
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

    // Constructor rỗng
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