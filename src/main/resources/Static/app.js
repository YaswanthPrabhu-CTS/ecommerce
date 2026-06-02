
let token = "";
let currentRole = "GUEST";
let currentUserName = "";
let currentUserAddress = "";
let allProducts = [];
let activeCategory = "ALL";
let loginTab = "USER";
const BASE_URL = "http://localhost:8081";


async function api(path, method = "GET", body = null, auth = false) {
    const headers = { "Content-Type": "application/json" };
    if (auth && token) headers["Authorization"] = `Bearer ${token}`;
    const res = await fetch(BASE_URL + path, { method, headers, body: body ? JSON.stringify(body) : null });
    return res.json().catch(() => ({}));
}

/* func for Navigation */
function showPage(name) {
//    ["homePage", "loginPage", "registerPage", "cartPage", "adminPage", "ordersPage"].forEach(id => {
      ["homePage", "loginPage", "registerPage"].forEach(id => {
        document.getElementById(id).classList.add("hidden");
        document.getElementById(id).classList.remove("active");
    });
    const target = document.getElementById(name + "Page");
    if (target) {
        target.classList.remove("hidden");
        target.classList.add("active");
    }
    if (name === "cart") loadCart();
    if (name === "admin") { renderAdminProductGrid(); loadAdminOrders(); }
    if (name === "home") loadFeaturedProducts();
    if (name === "orders") loadOrders();
}

function scrollToProducts() {
    document.getElementById("productSectionTitle").scrollIntoView({ behavior: "smooth" });
}

function switchLoginTab(role) {
    loginTab = role;
    document.getElementById("tabUser").classList.toggle("active", role === "USER");
    document.getElementById("tabAdmin").classList.toggle("active", role === "ADMIN");
}

async function submitLogin() {
    const email = document.getElementById("loginEmail").value.trim();
    const password = document.getElementById("loginPassword").value;
    const errEl = document.getElementById("loginError");
    errEl.classList.add("hidden");
    errEl.textContent = "";

    if (!email || !password) {
        errEl.textContent = "Please enter email and password.";
        errEl.classList.remove("hidden");
        return;
    }

    const data = await api("/api/users/login", "POST", { email, password });

    if (!data.token) {
        errEl.textContent = data.error || "Invalid credentials.";
        errEl.classList.remove("hidden");
        return;
    }

    if (data.role !== loginTab) {
        errEl.textContent = `This login is for ${loginTab} only. Please use the correct tab.`;
        errEl.classList.remove("hidden");
        return;
    }

    setSession(data);
    showPage("home");
}

async function registerUser() {
    const firstName = document.getElementById("regFirstName").value.trim();
    const lastName = document.getElementById("regLastName").value.trim();
    const phone = document.getElementById("regPhone").value.trim();
    const email = document.getElementById("regEmail").value.trim();
    const password = document.getElementById("regPassword").value;
    const confirm = document.getElementById("regConfirmPassword").value;
    const address = document.getElementById("regAddress").value.trim(); // CHANGED: read address
    const msgEl = document.getElementById("registerMessage");

    msgEl.className = "msg-box hidden";
    msgEl.textContent = "";

    // CHANGED: added address to required fields check
    if (!firstName || !lastName || !phone || !email || !password || !confirm || !address) {
        showMsg(msgEl, "Please fill in all fields including your delivery address.", "error");
        return;
    }
    if (password !== confirm) {
        showMsg(msgEl, "Passwords do not match.", "error");
        return;
    }

    // CHANGED: include address in registration payload
    const data = await api("/api/users/register", "POST", {
        name: `${firstName} ${lastName}`, phone, email, password, address
    });

    if (data.token) {
        showMsg(msgEl, "Registration successful! Redirecting...", "success");
        setSession(data);
        setTimeout(() => showPage("home"), 1200);
    } else {
        showMsg(msgEl, data.error || "Registration failed. Please try again.", "error");
    }
}
function showMsg(el, text, type) {
    el.textContent = text;
    el.className = `msg-box ${type}`;
}

async function setSession(data) {
    token = data.token;
    currentRole = data.role;
    currentUserName = data.name || "User";
    updateNavbar();
    loadFeaturedProducts();
    if (currentRole === "USER") {
        updateCartBadge();
        // CHANGED: fetch and cache user's delivery address
        const profile = await api("/api/users/profile", "GET", null, true);
        currentUserAddress = profile.address || "";
    }
}