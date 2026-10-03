const userName = sessionStorage.getItem("userName");
const userRole = sessionStorage.getItem("userRole");
const userNameElement = document.querySelector("#user-name");
const userRoleElement = document.querySelector("#user-role");
const userAvatar = document.querySelector("#user-avatar");
const welcomeName = document.querySelector("#welcome-name");

if (userName && userRole) {
    const roleLabels = {
        NHAN_VIEN: "Nhân viên",
        QUAN_LY: "Quản lý"
    };
    const displayName = userName.trim();
    const firstNameInitial = displayName.split(/\s+/).at(-1).charAt(0).toLocaleUpperCase("vi-VN");

    userNameElement.textContent = displayName;
    userRoleElement.textContent = roleLabels[userRole] || userRole;
    userAvatar.textContent = firstNameInitial;
    welcomeName.textContent = displayName;
}

document.querySelector(".logout").addEventListener("click", () => {
    sessionStorage.removeItem("userName");
    sessionStorage.removeItem("userRole");
});
