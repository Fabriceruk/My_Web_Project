# My Web Project - Hospital Appointment Booking System

A full-stack web application for managing hospital appointments, built with Spring Boot backend and React frontend. This system allows patients to book appointments with doctors and provides administrative features for managing doctor information and appointments.


## Features

- **Doctor Management**: Add, view, update, and manage doctor profiles including specialization and availability
- **Appointment Booking**: Schedule appointments with available doctors during specified time slots
- **Patient Management**: Track patient information and appointment history
- **RESTful API**: Comprehensive API endpoints for all operations
- **Modern UI**: Responsive React interface with Tailwind CSS styling
- **Database Integration**: PostgreSQL database with JPA for data persistence
- **Validation**: Input validation and error handling
- **CORS Support**: Cross-origin resource sharing for frontend-backend communication

## Technology Stack

### Backend
- **Framework**: Spring Boot 4.0.6
- **Language**: Java 21
- **Database**: PostgreSQL
- **ORM**: Spring Data JPA with Hibernate
- **Validation**: Spring Boot Validation
- **Build Tool**: Maven
- **Testing**: JUnit with Spring Boot Test
- **Development**: Spring Boot DevTools, Lombok

### Frontend
- **Framework**: React 19.2.5
- **Build Tool**: Vite 8.0.10
- **Styling**: Tailwind CSS 4.2.4
- **Routing**: React Router DOM 7.14.2
- **HTTP Client**: Axios 1.15.2
- **Notifications**: React Toastify 11.1.0
- **Linting**: ESLint 10.2.1

## Prerequisites

Before running this application, make sure you have the following installed:

- **Java**: JDK 21 or higher
- **Node.js**: Version 18 or higher (includes npm)
- **PostgreSQL**: Version 12 or higher
- **Maven**: Version 3.6 or higher (usually comes with Spring Boot)
- **Git**: For cloning the repository

## Installation and Setup

### Database Setup

1. Install and start PostgreSQL server
2. Create a new database named `webstack_db`:
   ```sql
   CREATE DATABASE webstack_db;
   ```
3. Update database credentials in `backend/src/main/resources/application.properties` if needed:
   ```
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```

### Backend Setup

1. Navigate to the backend directory:
   ```bash
   cd backend
   ```

2. Install dependencies and build the project:
   ```bash
   ./mvnw clean install
   ```

3. Run the Spring Boot application:
   ```bash
   ./mvnw spring-boot:run
   ```

The backend server will start on `http://localhost:8080`

### Frontend Setup

1. Navigate to the frontend directory:
   ```bash
   cd frontend
   ```

2. Install dependencies:
   ```bash
   npm install
   ```

3. Start the development server:
   ```bash
   npm run dev
   ```

The frontend application will be available at `http://localhost:5173`

## Usage

1. Ensure both backend and frontend servers are running
2. Open your browser and navigate to `http://localhost:5173`
3. Use the application to:
   - View available doctors
   - Book appointments with doctors
   - Manage doctor information (admin features)

## API Documentation

The REST API provides the following endpoints:

### Doctor Endpoints

- `GET /api/doctors` - Retrieve all doctors
- `GET /api/doctors/{id}` - Retrieve a specific doctor by ID
- `POST /api/doctors` - Create a new doctor
- `PUT /api/doctors/{id}` - Update an existing doctor

### Appointment Endpoints

- `GET /api/appointments` - Retrieve all appointments
- `POST /api/appointments` - Create a new appointment
- `DELETE /api/appointments/{id}` - Cancel an appointment

### Request/Response Formats

#### Doctor Model
```json
{
  "id": 1,
  "fullName": "Dr. John Smith",
  "specialization": "Cardiology",
  "department": "Cardiology",
  "available": true
}
```

#### Appointment Model
```json
{
  "id": 1,
  "patientName": "Jane Doe",
  "patientId": "P001",
  "doctorId": 1,
  "appointmentDate": "2024-01-15",
  "timeSlot": "10:00-11:00"
}
```

### Available Time Slots
- 08:00-09:00
- 10:00-11:00
- 14:00-15:00

## Project Structure

```
My_Web_Project/
├── backend/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/webstack/backend/
│   │   │   │   ├── controllers/     # REST controllers
│   │   │   │   ├── entities/        # JPA entities
│   │   │   │   ├── repositories/    # Data repositories
│   │   │   │   ├── services/        # Business logic
│   │   │   │   └── BackendApplication.java
│   │   │   └── resources/
│   │   │       └── application.properties
│   │   └── test/                    # Unit tests
│   └── pom.xml                      # Maven configuration
├── frontend/
│   ├── src/
│   │   ├── components/              # React components
│   │   ├── pages/                   # Page components
│   │   ├── routes/                  # Routing configuration
│   │   ├── api/                     # API client
│   │   └── assets/                  # Static assets
│   ├── public/                      # Public assets
│   ├── package.json                 # npm configuration
│   ├── vite.config.js               # Vite configuration
│   └── eslint.config.js             # ESLint configuration
└── README.md                        # This file
```

## Development

### Running Tests

#### Backend Tests
```bash
cd backend
./mvnw test
```

#### Frontend Linting
```bash
cd frontend
npm run lint
```

### Building for Production

#### Backend
```bash
cd backend
./mvnw clean package
```

#### Frontend
```bash
cd frontend
npm run build
```

Project Link: [https://github.com/Fabriceruk/My_Web_Project](https://github.com/Fabriceruk/My_Web_Project)