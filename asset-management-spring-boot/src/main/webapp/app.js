const assetForm = document.getElementById("assetForm");
const assetList = document.getElementById("assetList");

window.onload = function() {
    loadAssets();
};

assetForm.addEventListener("submit", function (event) {
    event.preventDefault(); // stop page reload
    addAsset();
});

function addAsset() {
    const asset = {
        assetName: document.getElementById("assetName").value,
        status: document.getElementById("status").value,
        health: document.getElementById("health").value,
        installedDate: document.getElementById("installedDate").value,
        location: document.getElementById("location").value
    };

    fetch("http://localhost:8000/api/v1/assets", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(asset)
    })
    .then(() => {
        assetForm.reset();
        loadAssets();
    });
}

function renderAssets(assets) {
    assetList.innerHTML = "";

    assets.forEach(asset => {
        const div = document.createElement("div");
        div.innerHTML = `
            <strong>${asset.assetName}</strong>
            (${asset.status}, ${asset.health})
            <br/>
            Installed: ${asset.installedDate}
            <br/>
            Location: ${asset.location}
            <button onclick="deleteAsset('${asset.assetId}')">Delete</button>
            <hr/>
        `;
        assetList.appendChild(div);
    });
}

function deleteAsset(assetId) {
    fetch(`http://localhost:8000/api/v1/assets/${assetId}`, {
        method: "DELETE"
    })
    .then(() => loadAssets());
}

function loadAssets() {
    fetch("http://localhost:8000/api/v1/assets")
        .then(response => response.json())
        .then(data => renderAssets(data))
        .catch(error => console.error("Error:", error));
}