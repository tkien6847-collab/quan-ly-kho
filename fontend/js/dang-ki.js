const registerForm = document.querySelector(".register-form");
const registerMessage = document.querySelector(".register-message");
const registerButton = document.querySelector(".register-button");

registerForm.addEventListener("submit", async (event) => {
    event.preventDefault();
    registerMessage.textContent = "";
    registerMessage.classList.remove("success");

    const password = registerForm.elements.password.value;
    const confirmPassword = registerForm.elements.confirm_password.value;
    if (password !== confirmPassword) {
        registerMessage.textContent = "Mật khẩu xác nhận không khớp.";
        registerForm.elements.confirm_password.focus();
        return;
    }

    registerButton.disabled = true;
    registerButton.textContent = "Đang tạo tài khoản...";

    try {
        const response = await fetch("http://localhost:8080/api/nhan-vien", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({
                hoTen: registerForm.elements.full_name.value.trim(),
                tenDangNhap: registerForm.elements.username.value.trim(),
                email: registerForm.elements.email.value.trim(),
                soDienThoai: registerForm.elements.so_dien_thoai.value.trim(),
                vaiTro: registerForm.elements.vai_tro.value,
                matKhau: password
            })
        });
        const result = await response.json();

        if (!response.ok) {
            registerMessage.textContent = (typeof result === "string" ? result : result.message)
                || "Không thể tạo tài khoản. Tên đăng nhập hoặc email có thể đã được sử dụng.";
            return;
        }

        window.location.href = "DangNhap.html?registered=1";
    } catch (error) {
        registerMessage.textContent = error instanceof TypeError
            ? "Không thể kết nối máy chủ. Hãy kiểm tra backend đang chạy."
            : "Không đọc được phản hồi từ máy chủ.";
    } finally {
        registerButton.disabled = false;
        registerButton.textContent = "Tạo tài khoản";
    }
});
