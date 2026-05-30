### Linda Gan
---
### 1. Feature Development

#### Repository & Query Definition
* **Tool:** Gemini AI
* **Task/Prompt:** Evaluate and optimize the repository database query call signature: `Page<Room> findAllByPricePerNight(Pageable pageable);`.
* **Decision:** **Modified.**
* **Why:** The initial query pattern lacked a required method argument matching the filter naming convention, which caused Spring Boot to crash. The implementation was changed to move the sorting logic into the Service layer using native `Sort.by().ascending()` parameters.

#### Client-Side Pagination Optimization
* **Tool:** Gemini AI
* **Task/Prompt:** Design a mobile-friendly pagination navigation bar to replace standard Bootstrap pagination links.
* **Decision:** **Rejected, then Modified.**
* **Why:** An initial design suggesting a backend database DTO modification to fetch a `totalPages` value was rejected to avoid rewriting stable backend code. The component structure was modified to run client-side logic checking page size vs array thresholds (`rooms.length < pageSize`), styled with arrows.
---
### 2. Debugging & Troubleshooting

#### Data Deserialization Alignment
* **Tool:** Gemini AI
* **Task/Prompt:** Diagnose why room detail image tags rendered broken links containing trailing `undefined` values (`http://localhost:8080undefined`).
* **Decision:** **Modified.**
* **Why:** While the database entity used `imagePath`, the backend DTO decoupled the name to serialize as `"image"`. The frontend mapping logic was updated to map directly to `room.image` while prepending the server port prefix.
---
### 3. UI/UX Refactoring

#### Responsive Framework Migration
* **Tool:** Gemini AI
* **Task/Prompt:** Adapt and optimize Bootstrap based hotel website into a mobile-friendly Ionic app with minimal custom css, and ensuring bottom-anchored footers.
* **Decision:** **Rejected, then Modified.**
* **Why:** Bootstrap utilities were replaced by native Ionic components to achieve a more clean and mobile friendly look.

#### Navigation Header Simplification
* **Tool:** Gemini AI
* **Task/Prompt:** Adapt a classic Bootstrap navigation component (`NavbarComponent.vue`) to Ionic, keeping links readable on narrow screens while removing the typical hamburger menu pattern.
* **Decision:** **Modified.**
* **Why:** Hamburger menu was not needed. The design was flattened into a clean `<ion-toolbar>`.
---
