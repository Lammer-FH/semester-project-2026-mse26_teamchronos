# Hotel Booking App

Browse rooms, check real-time availability, add breakfast, and book with form validation.

**Stack:** Ionic Vue 3 (Composition API, Bootstrap 5 + scoped CSS, Axios), Spring Boot with a REST API under `/api/v1`.

## Getting Started

**Frontend**

run from ../frontend

```bash
npm install
npm run dev
```

**Backend**
```bash
./gradlew bootRun
```
Or run the Spring Boot build configuration from your IDE (IntelliJ, etc.).

The database seeds from `data.sql` on each startup (state drops between sessions). View it via the H2 console at `http://localhost:8080/h2-console`
