package com.quanlykho.controller;

import com.quanlykho.entity.NhanVien;
import com.quanlykho.service.NhanVienService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final NhanVienService nhanVienService;

    public AuthController(NhanVienService nhanVienService) {
        this.nhanVienService = nhanVienService;
    }

    // API đăng nhập
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody NhanVien nhanVien) {

        Optional<NhanVien> ketQua = nhanVienService.login(
                nhanVien.getTenDangNhap(),
                nhanVien.getMatKhau()
        );

        if (ketQua.isPresent()) {
            return ResponseEntity.ok(ketQua.get());
        }

        return ResponseEntity.status(401)
                .body("Sai tên đăng nhập hoặc mật khẩu");
    }
}