### 1. Feature Development in general

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
### 2. Debugging & Troubleshooting in general

#### Data Deserialization Alignment
* **Tool:** Gemini AI
* **Task/Prompt:** Diagnose why room detail image tags rendered broken links containing trailing `undefined` values (`http://localhost:8080undefined`).
* **Decision:** **Modified.**
* **Why:** While the database entity used `imagePath`, the backend DTO decoupled the name to serialize as `"image"`. The frontend mapping logic was updated to map directly to `room.image` while prepending the server port prefix.
---
### 3. UI/UX Refactoring in general

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

### 4. Feature Development in detail per story in feature branch

## feature/US1-hotel-website

### ChatGPT 5.5

Used for frontend guidance, component structuring, content refinement, and Vue/Ionic troubleshooting.

| Documented Prompt | Implementation Summary |
| --- | --- |
| "How should I structure the static pages for the hotel website?" | Suggested separating the application into dedicated pages such as Home, About, Rooms, and Legal Notice. |
| "Should I use BootstrapVue or standard Bootstrap with Vue 3?" | Recommended standard Bootstrap integration for compatibility with Vue 3 and Ionic. |
| "How can I make my room layout reusable in Vue?" | Suggested extracting repeated room sections into a reusable `RoomCard` component. |
| "Should the navigation bar and footer be separate components?" | Recommended creating shared components to avoid duplication across pages. |
| "How can I navigate to another page when clicking a button?" | Explained Vue Router navigation using `router-link` and route definitions. |
| "What information should be included on an About Us page for a hotel website?" | Provided guidance and example content for presenting the hotel and its services. |
| "How can I improve the structure and readability of the hotel description?" | Suggested text formatting, content organization, and improvements for readability. |
| "How can I make the layout responsive for mobile and desktop devices?" | Recommended Bootstrap grid utilities and responsive layout patterns. |
| "What is still missing to satisfy the user story requirements?" | Reviewed the implementation against the assignment criteria and identified any remaining tasks. |

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

## feature/US4-booking
 
### Claude Opus 4.8:
 
Used for the booking persistence flow (controller, service, DTOs), the breakfast option, the Bootstrap-to-Ionic migration of the room details page, the confirmation page, and frontend/data debugging.
 
| Documented Prompt | Implementation Summary |
| --- | --- |
| "Generate example data.sql INSERT statements for the Booking and Guest entities" | Produced seed inserts aligned to the entity fields and the existing rooms/extras data, with `total_price` set consistently to price per night times number of nights. |
| "Getting H2 error: Column 'ROOMID' not found when seeding bookings" | Identified Spring Boot's default snake_case physical naming strategy overriding the explicit `@JoinColumn` names; corrected the SQL columns to `room_id` / `guest_id`. |
| "Add form validation (especially email) to the room booking form" | Added a per-field `errors` object and a `validateForm()` checking required fields, email format, and email-confirmation match, with inline error messages replacing the blocking alert. |
| "Write a BookingController with a POST /bookings endpoint, using the existing controllers as a guide" | Created `BookingController` (`POST /api/v1/bookings`) plus `CreateBookingDto` (validated input), `BookingDto` (response), and `BookingMapper`, mirroring the existing Room controller/mapper patterns. |
| "Adapt the RoomPage frontend to POST the booking" | Replaced the navigation-only submit with an async axios POST, handling 201/409/400 responses and adding a submitting/loading state. |
| "Adapt the backend to have a breakfastIncluded boolean" | Threaded the flag through the `Booking` entity, `CreateBookingDto`, `BookingDto`, `BookingMapper`, and `createBooking`, plus updated the `data.sql` seed rows with the new column. |

## feature/US5-booking-confirmation
 
### Claude Opus 4.8:

| Documented Prompt | Implementation Summary |
| --- | --- |
| "Make the confirmation page include full room details" | Fetched the full room object (image, title, extras, description) instead of just the title and rendered all required sections. |

### 5. Pre-development phase 

## Checking paper prototype against usability heuristics
 
### ChatGPT:

| Documented Prompt | Implementation Summary |
| --- | --- |
| "Check this paper prototype in terms of usability and UX. The 10 heuristics in https://www.nngroup.com/articles/ten-usability-heuristics/ must be met. Give me a report with an estimation per heuristic on how much it is met. In the report you must also describe improvements that can be made and what heuristic is linked to the improvement. Generate a png with an improved prototype." | Produced report with prioritized improvement ideas and evaluation per heuristik, generated png file of improved prototype. |

## Checking data base design and API definition against best practices
 
### ChatGPT:

| Documented Prompt | Implementation Summary |
| --- | --- |
| "Check these 2 specifications of the APIs and the database for consistency, logic and synergy. Check these 2 speicificatins for guidlines for clean architecture, good data base design and api calls. Give a list of improvement ideas with a priority and rationale." | Produced a list of prioritized improvement ideas with rationale and evaluation with X out of 10 for architecture and API best practice. |
