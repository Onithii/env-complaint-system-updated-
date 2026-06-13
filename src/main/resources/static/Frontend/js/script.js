document.getElementById("complaintForm")
    .addEventListener("submit", async function (e) {

        e.preventDefault();

        const complaint = {
            userId: 1,
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

        } catch (error) {
            console.error("Error:", error);

            document.getElementById("message").innerText =
                "Failed to submit complaint";
        }
    });