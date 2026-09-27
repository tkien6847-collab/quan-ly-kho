package com.quanlykho.controller;

import com.quanlykho.entity.NhanVien;
import com.quanlykho.service.NhanVienService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/nhan-vien")
public class NhanVienController {

	private final NhanVienService nhanVienService;

	public NhanVienController(NhanVienService nhanVienService) {
		this.nhanVienService = nhanVienService;
	}

	// Lấy danh sách tất cả nhân viên.
	@GetMapping
	public ResponseEntity<List<NhanVien>> getAllNhanVien() {
		return ResponseEntity.ok(nhanVienService.getAllNhanVien());
	}

	// Tìm nhân viên theo ID.
	@GetMapping("/{id}")
	public ResponseEntity<NhanVien> getNhanVienById(@PathVariable Long id) {
		return nhanVienService.getNhanVienById(id)
				.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	// Thêm nhân viên mới.
	@PostMapping
	public ResponseEntity<NhanVien> createNhanVien(@RequestBody NhanVien nhanVien) {
		return ResponseEntity.ok(nhanVienService.createNhanVien(nhanVien));
	}

	// Cập nhật nhân viên theo ID.
	@PutMapping("/{id}")
	public ResponseEntity<NhanVien> updateNhanVien(
			@PathVariable Long id,
			@RequestBody NhanVien nhanVien) {
		NhanVien nhanVienDaCapNhat = nhanVienService.updateNhanVien(id, nhanVien);

		if (nhanVienDaCapNhat == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(nhanVienDaCapNhat);
	}

	// Xóa nhân viên theo ID.
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteNhanVien(@PathVariable Long id) {
		nhanVienService.deleteNhanVien(id);
		return ResponseEntity.ok("Xóa nhân viên thành công.");
	}
}
