Hospital Appointment Booking System

Projects:
- backend (Spring Boot + PostgreSQL)
- frontend (React + Vite)

Backend Setup (Spring Boot):
1) Create PostgreSQL database:
   - Name: webstack_db
2) Update credentials in:
   - backend/src/main/resources/application.properties
3) Run backend:
   - cd backend
   - mvn spring-boot:run
4) API base URL:
   - http://localhost:8080

Frontend Setup (React):
1) Install dependencies:
   - cd frontend
   - npm install
2) Run frontend:
   - npm run dev
3) Frontend URL:
   - http://localhost:5173

Main API Endpoints:
Doctor
- GET    /api/doctors
- GET    /api/doctors/{id}
- POST   /api/doctors
- PUT    /api/doctors/{id}

Appointment
- GET    /api/appointments
- POST   /api/appointments
- DELETE /api/appointments/{id}

Notes:
- CORS is enabled for http://localhost:5173 in backend controllers.
- Appointment booking accepts time slots:
  08:00-09:00, 10:00-11:00, 14:00-15:00
- Only required backend entities are included: Doctor and Appointment.
