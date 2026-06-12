function submitComplaint() {

    const data = {
        complaintType: document.getElementById("type").value,
        description: document.getElementById("description").value,
        latitude: parseFloat(document.getElementById("latitude").value),
        longitude: parseFloat(document.getElementById("longitude").value)
    };

    fetch(BASE_URL + "/complaints/submit", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Authorization": "Bearer " + getToken()
        },
        body: JSON.stringify(data)
    })
    .then(res => res.text())
    .then(msg => {
        alert(msg);
        window.location.href = "dashboard.html";
    })
    .catch(err => {
        console.log(err);
        alert("Failed to submit complaint");
    });
}

function goBack() {
    window.location.href = "dashboard.html";
}