# 1. HTML5 Structure

## What is HTML?

HTML (HyperText Markup Language) defines the **structure of a web page**.

Think of HTML as:

> DTOs for the UI layer

Just like DTOs define *data shape*, HTML defines *page structure*.

---

## HTML5 Semantic Elements

HTML5 introduced **semantic tags** to make pages more readable and meaningful.

| Tag         | Purpose                |
| ----------- | ---------------------- |
| `<header>`  | Page or section header |
| `<main>`    | Main content           |
| `<section>` | Logical grouping       |
| `<form>`    | User input             |
| `<input>`   | Data fields            |
| `<button>`  | Action trigger         |

### Use in Asset Project

* One section for **Add Asset**
* One section for **Asset List**

### Why This Matters

* Accessibility (screen readers)
* SEO
* Maintainable UI code
* Clean separation of responsibilities

---

# 2. CSS3 Essentials

## What is CSS?

CSS (Cascading Style Sheets) controls:

* Layout
* Colors
* Spacing
* Responsiveness

If HTML is *what exists*, CSS is *how it looks*.

---

## Flexbox

### Problem Flexbox Solves

Before Flexbox:

* Aligning elements was painful
* Float hacks everywhere

Flexbox provides **1-dimensional layout** (row or column).

### Core Flexbox Concepts

| Property          | Use                  |
| ----------------- | -------------------- |
| `display: flex`   | Enable flex layout   |
| `flex-direction`  | Row or column        |
| `gap`             | Spacing              |
| `justify-content` | Horizontal alignment |
| `align-items`     | Vertical alignment   |

### Use in Asset Project

* Side-by-side layout:

    * Left: Add Asset Form
    * Right: Asset List

---

## CSS Grid (When to Use)

Grid is **2-dimensional** (rows + columns).

### Use Cases

* Dashboards
* Tables
* Card layouts

### Rule of Thumb

* Flexbox → components
* Grid → page layout

---

# 3. DOM (Document Object Model)

## What is the DOM?

DOM is the **in-memory representation of HTML** as a tree structure.

Browser converts:

```
HTML → DOM Tree
```

JavaScript interacts with this tree.

---

## DOM Manipulation

### Why DOM Manipulation?

To:

* Update UI dynamically
* Show backend data
* React to user actions

### Examples in Your Project

* Read form values
* Insert asset cards dynamically
* Remove asset from UI after delete

### Common DOM Operations

| Operation          | Purpose        |
| ------------------ | -------------- |
| `getElementById()` | Select element |
| `createElement()`  | Create new UI  |
| `innerHTML`        | Inject content |
| `appendChild()`    | Add to page    |

---

# 4. Events

## What are Events?

Events represent **user actions**.

| Event    | Example         |
| -------- | --------------- |
| `click`  | Button press    |
| `submit` | Form submit     |
| `change` | Dropdown change |

---

## Event Handling

### Why Needed?

Without events:

* Page reloads on every action
* No interactivity

### Example Use

* Intercept form submit
* Call POST API instead of reloading page

### Key Concept

```js
event.preventDefault();
```

This gives JavaScript control over the browser.

---

# 5. Fetch API

## What is Fetch?

Fetch is a **modern API to make HTTP requests** from the browser.

Equivalent of:

* `RestTemplate`
* `WebClient`
* `HttpClient`

---

## Why Fetch Exists

* Browsers are clients too
* UI must talk to backend APIs
* REST is frontend-backend contract

---

## Fetch + REST Mapping

| HTTP   | Spring Boot      |
| ------ | ---------------- |
| GET    | `@GetMapping`    |
| POST   | `@PostMapping`   |
| DELETE | `@DeleteMapping` |

---

## Use in Asset Project

* Fetch asset list
* Create new asset
* Delete asset

Frontend consumes:

* `AssetRequestDto`
* `AssetResponseDto`

---

# 6. JSON Handling

## Why JSON?

JSON is the **common language** between frontend and backend.

Reasons:

* Lightweight
* Human-readable
* Language agnostic

---

## Backend → Frontend Flow

```
AssetResponseDto
   ↓
JSON
   ↓
JavaScript Object
   ↓
UI Rendering
```

---

## Frontend → Backend Flow

```
Form Input
   ↓
JavaScript Object
   ↓
JSON.stringify()
   ↓
AssetRequestDto
```

This strict mapping prevents contract mismatch.

---

# 7. Integrating Spring Boot with Frontend

## Option 1: Same Application

Spring Boot serves static files.

### When to Use

* Internal tools
* Simple apps
* POCs

### Benefit

* No CORS
* One deployable unit

---

## Option 2: Separate Frontend & Backend

### When to Use

* React / Angular
* Microservices
* Cloud-native apps

### Requires

* CORS configuration
* API gateway awareness

---

# 8. Why These Concepts Matter 

Even with React/Angular:

* HTML → JSX / Templates
* CSS → Component styles
* DOM → Virtual DOM
* Fetch → Axios / HttpClient

If you understand **vanilla concepts**, frameworks become easy.

---

# 9. Mental Model for Backend Engineers

| Backend Concept | Frontend Equivalent |
| --------------- | ------------------- |
| DTO             | HTML Form / JSON    |
| Controller      | JS Event Handler    |
| Service         | JS Function         |
| REST Client     | Fetch API           |
| Validation      | Client-side checks  |

---


