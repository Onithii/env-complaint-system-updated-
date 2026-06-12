function register() {

    const data = {
        fullName: document.getElementById("fullName").value,
        username: document.getElementById("username").value,
        email: document.getElementById("email").value,
        phone: document.getElementById("phone").value,
        password: document.getElementById("password").value
    };

    fetch(BASE_URL + "/auth/register", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(data)
    })
    .then(res => res.text())
    .then(msg => {
        alert(msg);
        window.location.href = "index.html";
    })
    .catch(err => {
        console.log(err);
        alert("Registration failed");
    });
}