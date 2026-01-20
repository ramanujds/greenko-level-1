## Case Study: Asset & Telemetry Management Platform

**(Java • REST APIs • Microservices)**


### Business Story 

A company owns **thousands of assets** (machines, turbines, vehicles, sensors).
Each asset continuously sends **telemetry data** (temperature, load, vibration, usage hours).
The platform must:

* Manage assets
* Ingest high-volume telemetry
* Detect abnormal behavior
* Notify engineers
* Expose clean REST APIs for dashboards and integrations

---

## 1. High-Level Architecture

### Microservices

1. Asset Service
2. Telemetry Service
3. Alert Service
4. User & Auth Service
5. API Gateway

### Supporting Components

* PostgreSQL (relational data)
* MongoDB / TimescaleDB (time-series data)
* Kafka (events)
* Redis (optional caching)
* Docker Compose (local infra)

---

## 2. Asset Service (Foundation Service)

### Responsibility

* Asset lifecycle management
* Asset hierarchy & ownership
* Metadata only (no telemetry)

### Data Model

```text
Asset
- assetId (UUID)
- name
- type (TURBINE, MACHINE, VEHICLE)
- location
- status (ACTIVE, INACTIVE, MAINTENANCE)
- installedAt
- createdAt
```

### REST APIs

```http
POST   /assets
GET    /assets/{id}
GET    /assets?type=&status=&page=
PUT    /assets/{id}
PATCH  /assets/{id}/status
DELETE /assets/{id}
```

### Java Concepts You Teach Here

* REST design
* DTO vs Entity
* Validation
* Pagination & filtering
* Proper HTTP status codes

---

## 3. Telemetry Service (Data-Heavy Service)

### Responsibility

* High-volume telemetry ingestion
* Store raw and aggregated data
* Expose read APIs for analytics

### Telemetry Payload

```json
{
  "assetId": "UUID",
  "timestamp": "2026-01-20T10:15:30Z",
  "metrics": {
    "temperature": 72.4,
    "vibration": 0.018,
    "rpm": 1480
  }
}
```

### APIs

```http
POST /telemetry
POST /telemetry/bulk
GET  /telemetry/{assetId}/latest
GET  /telemetry/{assetId}?from=&to=
```

### Storage Strategy

* Metadata → PostgreSQL
* Telemetry → MongoDB / TimescaleDB
* Aggregates → scheduled jobs

### Java Concepts

* Bulk REST ingestion
* Async processing
* Time-series modeling
* Performance optimization
* Indexing strategy

---

## 4. Alert Service (Event-Driven)

### Responsibility

* Detect anomalies
* Trigger alerts
* Notify engineers

### Rule Example

```text
IF temperature > 80 for 3 consecutive readings
THEN raise alert
```

### Flow

1. Telemetry Service publishes event to Kafka
2. Alert Service consumes event
3. Rule engine evaluates
4. Alert stored + notification sent

### APIs

```http
POST /alerts/rules
GET  /alerts?assetId=&status=
PATCH /alerts/{id}/acknowledge
```

### Java Concepts

* Kafka consumers & producers
* Event-driven design
* Idempotent processing
* Eventually consistent systems

---

## 5. User & Auth Service

### Roles

* ADMIN
* ENGINEER
* VIEWER

### APIs

```http
POST /users
POST /auth/login
GET  /users/me
```

### Security

* JWT tokens
* Role-based access
* Gateway-level auth

### Java Concepts

* Spring Security
* Filters
* Token lifecycle
* Secure API design

---

## 6. API Gateway

### Responsibilities

* Single entry point
* Authentication filter
* Rate limiting
* Routing

### Example Routing

```text
/api/assets/**     → Asset Service
/api/telemetry/**  → Telemetry Service
/api/alerts/**     → Alert Service
```

### Concepts

* Gateway pattern
* Cross-cutting concerns
* API versioning

---

## 7. End-to-End Flow Example

1. Admin registers an asset
2. Asset starts sending telemetry
3. Telemetry stored & published
4. Alert rules evaluated
5. Engineer notified
6. Dashboard fetches latest health

## 8. Implementation Phases


### Phase 1 – Core Backend (Monolith First)

* Asset APIs
* Clean REST design
* Validation & persistence

### Phase 2 – Microservices Split

* Separate services
* Inter-service communication
* Centralized config

### Phase 3 – Streaming & Events

* Kafka integration
* Alert engine
* Async flows

### Phase 4 – Enterprise Concerns

* Security
* Observability
* Failures & retries
* Scaling discussions

---

