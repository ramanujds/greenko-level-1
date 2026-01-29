# Simple Frontend for Asset Management

## 1. HTML5 Structure (Semantic & Clean)

HTML defines **structure**, not logic.

### Basic HTML5 Page for Asset Management

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Asset Management</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>

<header>
    <h1>Asset Management System</h1>
</header>

<main>
    <!-- Asset Form -->
    <section id="asset-form-section">
        <h2>Add Asset</h2>
        <form id="assetForm">
            <input type="text" id="assetName" placeholder="Asset Name" required />
            <input type="date" id="installedDate" required />
            <input type="text" id="location" placeholder="Location" required />

            <select id="status">
                <option value="ACTIVE">ACTIVE</option>
                <option value="INACTIVE">INACTIVE</option>
            </select>

            <select id="health">
                <option value="GOOD">GOOD</option>
                <option value="AVERAGE">AVERAGE</option>
                <option value="CRITICAL">CRITICAL</option>
            </select>

            <button type="submit">Add Asset</button>
        </form>
    </section>

    <!-- Asset List -->
    <section id="asset-list-section">
        <h2>Assets</h2>
        <div id="assetList"></div>
    </section>
</main>

<script src="app.js"></script>
</body>
</html>
```

### Concepts Introduced

* Semantic tags: `header`, `main`, `section`
* Forms and inputs
* Separation of concerns:

    * HTML → structure
    * CSS → style
    * JS → behavior

---

## 2. CSS3 Essentials (Flexbox & Grid)

CSS controls **layout + responsiveness**.

### Simple CSS Using Flexbox

```css
body {
    font-family: Arial, sans-serif;
    margin: 0;
    padding: 0;
}

header {
    background: #1e293b;
    color: white;
    padding: 1rem;
    text-align: center;
}

main {
    display: flex;
    gap: 2rem;
    padding: 2rem;
}

section {
    flex: 1;
    background: #f8fafc;
    padding: 1rem;
    border-radius: 8px;
}

form {
    display: flex;
    flex-direction: column;
    gap: 0.5rem;
}

button {
    background: #2563eb;
    color: white;
    border: none;
    padding: 0.5rem;
    cursor: pointer;
}
```

### Concepts Introduced

* **Flexbox**

    * `display: flex`
    * `flex-direction`
    * `gap`
* Responsive layout without media queries
* Styling forms and buttons

(You can later introduce **CSS Grid** for dashboards.)

---

## 3. DOM Manipulation & Events

JavaScript interacts with **HTML via DOM**.

### Accessing DOM Elements

```javascript
const assetForm = document.getElementById("assetForm");
const assetList = document.getElementById("assetList");
```

### Handling Form Submit Event

```javascript
assetForm.addEventListener("submit", function (event) {
    event.preventDefault(); // stop page reload
    addAsset();
});
```

### Concepts Introduced

* DOM selection (`getElementById`)
* Event handling (`addEventListener`)
* Preventing default browser behavior

---

## 4. Using Fetch API to Call Backend

This is where frontend meets your **Spring Boot APIs**.

### GET Assets

```javascript
function loadAssets() {
    fetch("http://localhost:8000/api/v1/assets")
        .then(response => response.json())
        .then(data => renderAssets(data))
        .catch(error => console.error("Error:", error));
}
```

### POST Asset

```javascript
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
```

### Mapping to Backend DTO

This JSON maps **directly** to:

```java
AssetRequestDto
```

So the frontend is already aligned with your backend contract.

---

## 5. Handling JSON Responses

Your backend returns:

```java
AssetResponseDto
```

Which becomes:

```json
{
  "assetId": "A101",
  "assetName": "Laptop",
  "status": "ACTIVE",
  "health": "GOOD",
  "installedDate": "2024-01-10",
  "location": "Bangalore"
}
```

### Rendering Assets Dynamically

```javascript
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
```

### Concepts Introduced

* JSON parsing
* Dynamic DOM creation
* Template literals
* Rendering backend data on UI

---

## 6. DELETE API Integration

```javascript
function deleteAsset(assetId) {
    fetch(`http://localhost:8000/api/v1/assets/${assetId}`, {
        method: "DELETE"
    })
    .then(() => loadAssets());
}
```

---

## 7. Integrating Spring Boot with Frontend

### Option 1: Serve HTML from Spring Boot

```
src/main/resources/static/
 ├── index.html
 ├── styles.css
 └── app.js
```

Spring Boot will automatically serve:

```
http://localhost:8000/index.html
```

### Option 2: Frontend as Separate App

* Useful when moving to React/Angular later
* Requires CORS configuration

```java
@CrossOrigin(origins = "http://localhost:5500")
@RestController
@RequestMapping("/api/v1/assets")
public class AssetController {
}
```

---

## 8. Next Steps
* Add **form validation** (HTML5 + JS)
* Implement **edit/update** functionality
* Explore **frontend frameworks** (React, Angular)


