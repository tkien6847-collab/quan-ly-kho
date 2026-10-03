package com.quanlykho.controller;

import com.quanlykho.entity.NhanVien;
import com.quanlykho.service.NhanVienService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final NhanVienService nhanVienService;

    public AuthController(NhanVienService nhanVienService) {
        this.nhanVienService = nhanVienService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {

        Optional<NhanVien> ketQua = nhanVienService.login(
                request.tenDangNhap(),
                request.matKhau()
        );

        if (ketQua.isPresent()) {
            NhanVien nhanVien = ketQua.get();
            return ResponseEntity.ok(new AuthResponse(
                    nhanVien.getMaNV(),
                    nhanVien.getTenDangNhap(),
                    nhanVien.getHoTen(),
                    nhanVien.getVaiTro()
            ));
        }

        return ResponseEntity.status(401)
                .body("Sai tên đăng nhập hoặc mật khẩu");
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegistrationRequest request) {
        NhanVien nhanVien = nhanVienService.register(
                request.hoTen(),
                request.tenDangNhap(),
                request.email(),
                request.matKhau(),
                request.xacNhanMatKhau()
        );
        return ResponseEntity.status(201).body(new AuthResponse(
                nhanVien.getMaNV(),
                nhanVien.getTenDangNhap(),
                nhanVien.getHoTen(),
                nhanVien.getVaiTro()
        ));
    }

    public record LoginRequest(String tenDangNhap, String matKhau) {}

    public record RegistrationRequest(
            String hoTen,
            String tenDangNhap,
            String email,
            String matKhau,
            String xacNhanMatKhau
    ) {}

    public record AuthResponse(Long maNV, String tenDangNhap, String hoTen, String vaiTro) {}
}