function login() {
    const data = {
        username: document.getElementById("username").value,
        password: document.getElementById("password").value
    };

    fetch(BASE_URL + "/auth/login", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(data)
    })
    .then(res => res.json())
    .then(response => {
        // assuming response.token
        saveToken(response.token);
        alert("Login successful!");
        window.location.href = "dashboard.html";
    })
    .catch(err => {
        alert("Login failed");
        console.log(err);
    });
}