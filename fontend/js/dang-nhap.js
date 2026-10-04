const loginForm = document.querySelector(".login-form");
const loginMessage = document.querySelector(".login-message");
const loginButton = document.querySelector(".login-button");

loginForm.addEventListener("submit", async (event) => {
    event.preventDefault();
    loginMessage.textContent = "";
    loginMessage.classList.remove("success");
    loginButton.disabled = true;
    loginButton.textContent = "Đang đăng nhập...";

    try {
        const response = await fetch("http://localhost:8080/api/auth/login", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({
                tenDangNhap: loginForm.elements.username.value.trim(),
                matKhau: loginForm.elements.password.value
            })
        });
        const contentType = response.headers.get("content-type") || "";
        const result = contentType.includes("application/json")
            ? await response.json()
            : await response.text();

        if (!response.ok) {
            loginMessage.textContent = (typeof result === "string" ? result : result?.message)
                || "Sai tên đăng nhập hoặc mật khẩu.";
            return;
        }

        if (typeof result.hoTen !== "string" || !result.hoTen.trim()
                || !["NHAN_VIEN", "QUAN_LY"].includes(result.vaiTro)) {
            loginMessage.textContent = "Máy chủ không trả về thông tin tên hoặc vai trò hợp lệ.";
            return;
        }

        sessionStorage.setItem("userName", result.hoTen.trim());
        sessionStorage.setItem("userRole", result.vaiTro);
        window.location.href = result.vaiTro === "QUAN_LY" ? "index.html" : "index2.html";
    } catch (error) {
        loginMessage.textContent = error instanceof TypeError
            ? "Không thể kết nối máy chủ. Hãy kiểm tra backend đang chạy."
            : "Không đọc được phản hồi từ máy chủ.";
    } finally {
        loginButton.disabled = false;
        loginButton.textContent = "Đăng nhập";
    }
});

const registrationStatus = new URLSearchParams(window.location.search).get("registered");
if (registrationStatus === "1") {
    loginMessage.textContent = "Tạo tài khoản thành công. Hãy đăng nhập để tiếp tục.";
    loginMessage.classList.add("success");
}
