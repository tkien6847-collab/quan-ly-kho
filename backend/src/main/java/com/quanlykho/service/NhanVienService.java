package com.quanlykho.service;

import com.quanlykho.entity.NhanVien;
import com.quanlykho.repository.NhanVienRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NhanVienService {

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
}
