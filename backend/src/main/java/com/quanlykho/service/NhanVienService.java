package com.quanlykho.service;

import com.quanlykho.entity.NhanVien;
import com.quanlykho.repository.NhanVienRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;//dùng để láy danh sách 
import java.util.Optional;//giải quyết đúng sai

@Service
public class NhanVienService {
	private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
// kiểu dữ liệu và tên biến , final để không bị thay đổi sau khi tạo , phải đủ ntn mới dùng đc nhanVienRepository.findAll().
	private final NhanVienRepository nhanVienRepository;

	

	public NhanVienService(NhanVienRepository nhanVienRepository) {
		this.nhanVienRepository = nhanVienRepository;
	}


	// Lấy danh sách tất cả nhân viên.
	public List<NhanVien> getAllNhanVien() {
		return nhanVienRepository.findAll();
	}

	// Tìm nhân viên theo ID.
	public Optional<NhanVien> getNhanVienById(Long id) {
		return nhanVienRepository.findById(id);
	}

	// Thêm nhân viên mới.
	public NhanVien createNhanVien(NhanVien nhanVien) {
		return nhanVienRepository.save(nhanVien);
	}

	public NhanVien register(String hoTen, String tenDangNhap, String email, String matKhau, String xacNhanMatKhau) {
		if (hoTen == null || hoTen.isBlank()
				|| tenDangNhap == null || tenDangNhap.isBlank()
				|| email == null || email.isBlank()
				|| matKhau == null || matKhau.length() < 8
				|| xacNhanMatKhau == null || !matKhau.equals(xacNhanMatKhau)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Vui lòng kiểm tra lại thông tin đăng ký.");
		}

		String normalizedUsername = tenDangNhap.trim();
		String normalizedEmail = email.trim();
		if (nhanVienRepository.existsByTenDangNhap(normalizedUsername)) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "Tên đăng nhập đã được sử dụng.");
		}
		if (nhanVienRepository.existsByEmail(normalizedEmail)) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "Email đã được sử dụng.");
		}

		NhanVien nhanVien = new NhanVien();
		nhanVien.setHoTen(hoTen.trim());
		nhanVien.setTenDangNhap(normalizedUsername);
		nhanVien.setEmail(normalizedEmail);
		nhanVien.setMatKhau(passwordEncoder.encode(matKhau));
		nhanVien.setVaiTro("NHAN_VIEN");
		return nhanVienRepository.save(nhanVien);
	}

	// Cập nhật thông tin nhân viên theo ID.
	public NhanVien updateNhanVien(Long id, NhanVien nhanVien) {
		Optional<NhanVien> nhanVienCanCapNhat = nhanVienRepository.findById(id);

		if (nhanVienCanCapNhat.isEmpty()) {
			return null;
		}

		NhanVien nhanVienHienTai = nhanVienCanCapNhat.get();
		nhanVienHienTai.setTenDangNhap(nhanVien.getTenDangNhap());
		nhanVienHienTai.setMatKhau(nhanVien.getMatKhau());
		nhanVienHienTai.setHoTen(nhanVien.getHoTen());
		nhanVienHienTai.setEmail(nhanVien.getEmail());
		nhanVienHienTai.setSoDienThoai(nhanVien.getSoDienThoai());
		nhanVienHienTai.setVaiTro(nhanVien.getVaiTro());

		return nhanVienRepository.save(nhanVienHienTai);
	}

	// Xóa nhân viên theo ID.
	public void deleteNhanVien(Long id) {
		nhanVienRepository.deleteById(id);
	}

	// Also upgrades legacy plaintext passwords when the employee signs in successfully.
	public Optional<NhanVien> login(String tenDangNhap, String matKhau) {
		Optional<NhanVien> nhanVienTimThay = nhanVienRepository.findByTenDangNhap(tenDangNhap);
		if (nhanVienTimThay.isEmpty()) {
			return Optional.empty();
		}

		NhanVien nhanVien = nhanVienTimThay.get();
		String storedPassword = nhanVien.getMatKhau();
		boolean isLegacyPassword = storedPassword != null && !storedPassword.startsWith("$2");
		boolean passwordMatches = isLegacyPassword
				? storedPassword.equals(matKhau)
				: storedPassword != null && passwordEncoder.matches(matKhau, storedPassword);

		if (!passwordMatches) {
			return Optional.empty();
		}

		if (isLegacyPassword) {
			nhanVien.setMatKhau(passwordEncoder.encode(matKhau));
			nhanVienRepository.save(nhanVien);
		}
		return Optional.of(nhanVien);
	}
}
