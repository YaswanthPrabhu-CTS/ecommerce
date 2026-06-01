/* Navigation */
function showPage(name) {
    ["homePage", "loginPage", "registerPage", "cartPage", "adminPage", "ordersPage"].forEach(id => {
        document.getElementById(id).classList.add("hidden");
        document.getElementById(id).classList.remove("active");
    });
    const target = document.getElementById(name + "Page");
    if (target) {
        target.classList.remove("hidden");
        target.classList.add("active");
    }
    if (name === "cart") loadCart();
    if (name === "admin") { renderAdminProductGrid(); loadAdminOrders(); } // CHANGED: also load admin orders
    if (name === "home") loadFeaturedProducts();
    if (name === "orders") loadOrders();
}

function scrollToProducts() {
    document.getElementById("productSectionTitle").scrollIntoView({ behavior: "smooth" });
}