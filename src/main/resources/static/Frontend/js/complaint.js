// Run when page loads
document.addEventListener("DOMContentLoaded", function () {
    loadComplaints();
});


// =======================
// LOAD ALL COMPLAINTS (GET)
// =======================
async function loadComplaints() {
    try {
        const response = await fetch("http://localhost:8080/api/complaints");

        if (!response.ok) {
            throw new Error("Failed to fetch complaints");
        }

        const complaints = await response.json();

        renderTable(complaints);

    } catch (error) {
        console.error("Error loading complaints:", error);
    }
}


// =======================
// RENDER TABLE
// =======================
function renderTable(complaints) {
    const tableBody = document.getElementById("complaintsTableBody");

    // clear old data
    tableBody.innerHTML = "";

    complaints.forEach(c => {
        const row = document.createElement("tr");

        row.innerHTML = `
            <td>${c.id}</td>
            <td>${c.complaintType}</td>
            <td>${c.description}</td>
            <td>${c.latitude}</td>
            <td>${c.longitude}</td>
        `;

        tableBody.appendChild(row);
    });
}


// =======================
// SUBMIT FORM (POST)
// =======================
document.getElementById("complaintForm")
    .addEventListener("submit", async function (e) {

        e.preventDefault();

        const complaint = {
            userId: 1, // temporary
            complaintType: document.getElementById("complaintType").value,
            description: document.getElementById("description").value,
            latitude: parseFloat(document.getElementById("latitude").value),
            longitude: parseFloat(document.getElementById("longitude").value)
        };

        console.log("Sending:", complaint);

        try {
            const response = await fetch("http://localhost:8080/api/complaints", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(complaint)
            });

            if (!response.ok) {
                throw new Error("HTTP error! Status: " + response.status);
            }

            const data = await response.json();
            console.log("Response:", data);

            document.getElementById("message").innerText =
                "Complaint submitted successfully!";

            // clear form
            document.getElementById("complaintForm").reset();

            // refresh table
            loadComplaints();

        } catch (error) {
            console.error("Error:", error);

            document.getElementById("message").innerText =
                "Failed to submit complaint";
        }
    });