### 1. Feature Development

#### Generate Dummy Data for H2DB
* **Tool:** ChatGPT
* **Task/Prompt:** Generate dummy data for hotel booking website based on this API specification and DB design [attachments].
* **Decision:** **Modified.**
* **Why:** Because dummy data is needed to have a pre-filled db to test with.

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




## feature/US3-availability
### Gemini 3.5 Flash: 

Used for UI iteration, CSS cleanup, and generating repetitive layout

| Documented Prompt | Implementation Summary |
| --- | --- |
| "Extract the icon feature boxes into a reusable Ionic Vue component" | Unified the design across pages by creating the `IconBoxCard` component. |
| "Improve the overall UX of the Ionic Vue app: center content, adjust primary colors, and fix spacing" | Set bg primary color to black, added content wrappers, and constrained widths. |
| "Clean up old CSS and help convert the layout to Vue entirely" | Deleted global stylesheets and migrated entirely to Vue scoped styles. |
| "Improve styles for the room availability status chips" | Added semantic colors and refined borders for state feedback. |
| "Move all hardcoded UI text into a central configuration file for the Vue components" | Extracted copy across all pages into a single object. |
| "Create a data.sql file to populate the database with rooms and extras and all the relationships on startup. data dropped in between sessions" | Created the SQL script with `INSERT` statements to ensure data availability on launch. |
| "Expand the database seed data with more rooms and extras" | Added mock data for 9 rooms and mapped various hotel extras to them. |
| "How do I make the native HTML5 date picker icon visible on a dark background in Vue?" | Used the AI's CSS snippet (`filter: invert(1)`) to fix the invisible calendar icon in dark mode. |
| "Design a dark-themed booking form with Bootstrap 5 utilities and custom CSS for Vue" | Applied the suggested dark input classes and background colors (`#1e1e1e`, `#2c2c2c`) to the form and checkboxes. |
| "Create a responsive image header for the room details page that doesn't stretch on desktop" | Added the `img-fluid w-100 room-hero-img` classes and constrained the max-height to `50vh`. |
| "What are some CSS ways to make headings look better?" | Implemented the `.tracking-wide` utility class with `letter-spacing: 0.1em` for the section headers. |

### Claude Opus 4.8: 

Used for Spring Boot config, backend setup, and debugging routing/API issues

| Documented Prompt | Implementation Summary |
| --- | --- |
| "Provide a basic set up the build.gradle configuration for a Spring Boot project with JPA, Web, and database dependencies" | Added required plugins and iterated through suggestions to resolve version conflicts. |
| "Provide an outline of the Spring Boot backend based on the er model" | Generated controllers, DTO, entities, and services using Clean Architecture. |
| "Split the Room and Booking entities into smaller relational entities for the database" | Separated the data and manually rewrote mappings to ensure correct foreign keys. |
| "Move all API endpoints under an /api/v1 prefix" | Updated controller routes, adjusted configurations, and fixed tests. |
| "I'm getting a dozen of errors when fetching rooms. How do I fix the mapping loop after splitting the entities" | Mapped the raw JPA entities into clean DTO before sending them to the frontend. |
| "Show Ionic skeleton loaders on the rooms page" | Added skeleton UI states and surfaced Axios network errors to debug a broken picture. |
| "Make the date picker responsive and extract the date logic from input pls" | Improved grid layout for mobile and moved date logic into a shared utility file. |
| "Auto-scroll to the top when navigating between Ionic Vue pages" | Added Vue Router `scrollBehavior` to reset scroll position on route changes. |
| "Write an axios request to check room availability and handle a 409 status code for booked rooms" | Implemented the try/catch block that sets `isAvailable` and updates the UI status message based on HTTP responses. |
| "How do I prevent users from selecting a check-in date in the past? Vue 3 only?" | Created a computed property for today and dynamically bound it to the `:min` attribute of the date inputs. |
| "Validate that the email and confirm email fields match before navigating to the confirmation page" | Added the booking method with form validation and Vue Router query parameter pushing. |
| "Set up the reactive state for a hotel booking form in Vue 3 script setup" | Used `reactive()` to build the form object (first name, last name, email, breakfast boolean) to track user input. |
| "Show an error message if the availability call fails completely" | Added the else block in the catch statement to display a connection error if the network goes down. |

