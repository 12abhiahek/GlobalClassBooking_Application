# Course Booking System

A Class booking backend application built using Spring Boot and MySQL that allows teachers to create course offerings and students to book them while ensuring timezone-aware scheduling, conflict detection, and concurrent booking safety.

---

# Problem Statement

Teachers can create courses and offerings consisting of multiple sessions.

Students can browse available offerings and book an entire offering.

The system must:

* Handle teacher and student timezones correctly.
* Prevent students from booking overlapping offerings.
* Support concurrent booking requests safely.
* Store all session times in UTC.
* Convert session timings to the student's local timezone when viewing.

---

# Features

## Teacher Features

* Create Teacher
* Create Course
* Create Offering
* Add Sessions to Offering
* View Teacher Offerings
* View Offering Sessions

## Student Features

* Create Student
* View Available Offerings
* View Sessions in Local Timezone
* Book Offering
* View Booked Offerings

## Booking Rules

### Rule 1

Booking happens at Offering level.

Example:

Python Coding (8 sessions)

Student books once and automatically gets access to all sessions.

### Rule 2

Prevent Time Conflicts.

Student cannot book:

Course A:
June 10
5 PM - 6 PM

and

Course B:
June 10
5:30 PM - 6:30 PM

### Rule 3

Handle Concurrent Bookings.

Multiple requests from the same student must not create invalid bookings.

Uses:

* Transaction Management
* Pessimistic Locking

---

# Technology Stack

* Java 17
* Spring Boot 4
* Spring Data JPA
* MySQL 8
* Maven
* Swagger / OpenAPI
* Lombok
* JUnit 5
* Mockito

---

# Project Structure

src/main/java/com/Booking/BookingApplication

├── config
│   ├── OpenApiConfig
│   ├── SwaggerConfig
│   └── TimeZoneConfig
│
├── controller
│   ├── TeacherController
│   ├── StudentController
│   └── BookingController
│
├── dto
│   ├── request
│   └── response
│
├── entity
│   ├── Teacher
│   ├── Student
│   ├── Course
│   ├── Offering
│   ├── Session
│   └── Booking
│
├── repository
│
├── service
│
├── service/impl
│
├── exception
│
├── util
│
└── BookingApplication

---

# Database Schema

## teacher

| Column     | Type    |
| ---------- | ------- |
| teacher_id | BIGINT  |
| name       | VARCHAR |
| email      | VARCHAR |
| timezone   | VARCHAR |

---

## student

| Column     | Type    |
| ---------- | ------- |
| student_id | BIGINT  |
| name       | VARCHAR |
| email      | VARCHAR |
| timezone   | VARCHAR |

---

## course

| Column      | Type    |
| ----------- | ------- |
| course_id   | BIGINT  |
| name        | VARCHAR |
| description | VARCHAR |

---

## offering

| Column      | Type    |
| ----------- | ------- |
| offering_id | BIGINT  |
| course_id   | BIGINT  |
| teacher_id  | BIGINT  |
| name        | VARCHAR |
| status      | VARCHAR |

---

## session

| Column         | Type      |
| -------------- | --------- |
| session_id     | BIGINT    |
| offering_id    | BIGINT    |
| teacher_id     | BIGINT    |
| start_time_utc | TIMESTAMP |
| end_time_utc   | TIMESTAMP |

---

## booking

| Column      | Type    |
| ----------- | ------- |
| booking_id  | BIGINT  |
| student_id  | BIGINT  |
| offering_id | BIGINT  |
| status      | VARCHAR |

---

# Database ER Diagram

Teacher (1) ---- (*) Offering

Course (1) ---- (*) Offering

Offering (1) ---- (*) Session

Student (1) ---- (*) Booking

Offering (1) ---- (*) Booking

---

# Timezone Handling

All session times are stored in UTC.

Example:

Teacher Timezone:

America/New_York

Teacher Creates:

2026-06-01 18:00

Stored:

2026-06-01T22:00:00Z

Student Timezone:

Asia/Kolkata

Displayed:

2026-06-02 03:30

---

# Booking Conflict Detection

Overlap Formula:

start1 < end2 && start2 < end1

Example:

Session A:
5 PM - 6 PM

Session B:
5:30 PM - 6:30 PM

Result:

Conflict Detected

Booking Rejected

---

# Concurrency Handling

Uses:

@Transactional

and

PESSIMISTIC_WRITE

Repository:

@Lock(LockModeType.PESSIMISTIC_WRITE)

Result:

Request A acquires lock.

Request B waits.

Request A commits.

Request B re-checks booking conflict.

Invalid booking prevented.

---

# Setup Instructions

## Clone Repository

git clone https://github.com/12abhiahek/GlobalClassBooking_Application.git

cd course-booking-system

---

## Configure Database

Create Database:

CREATE DATABASE Bookingdb;

---

## application.yml

spring:
datasource:
url: jdbc:mysql://localhost:3306/Bookingdb?useSSL=false&serverTimezone=UTC
username: root
password: your_password

jpa:
hibernate:
ddl-auto: update

```
show-sql: true
```

---

## Install Dependencies

mvn clean install

---

## Run Application

mvn spring-boot:run

Application starts on:

http://localhost:8080

---

# Swagger Configuration

Dependency:

<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.8.5</version>
</dependency>

Swagger URL:

http://localhost:8080/swagger-ui/index.html

OpenAPI JSON:

http://localhost:8080/v3/api-docs

---

# API Documentation

## Teacher APIs

### Create Teacher

POST /api/teachers

Request:

{
"name":"John",
"email":"[john@gmail.com](mailto:john@gmail.com)",
"timezone":"America/New_York"
}

---

### Create Course

POST /api/teachers/courses

Request:

{
"name":"Python Coding",
"description":"Learn Python"
}

---

### Create Offering

POST /api/teachers/offerings

Request:

{
"teacherId":1,
"courseId":1,
"name":"Saturday Batch"
}

---

### Add Session

POST /api/teachers/offerings/{offeringId}/sessions

Request:

{
"startTime":"2026-06-01T18:00",
"endTime":"2026-06-01T19:00",
"timezone":"Asia/Kolkata"
}

---

## Student APIs

### Create Student

POST /api/students

Request:

{
"name":"Abhishek",
"email":"[abhishek@gmail.com](mailto:abhishek@gmail.com)",
"timezone":"Asia/Kolkata"
}

---

### View Offerings

GET /api/students/offerings

---

### View Sessions

GET /api/students/offerings/{offeringId}/sessions?studentId=1

---

## Booking APIs

### Book Offering

POST /api/bookings

Request:

{
"studentId":1,
"offeringId":1
}

---

### View Student Bookings

GET /api/bookings/student/{studentId}

---

# Testing

Run All Tests

mvn test

Run Single Test

mvn test -Dtest=BookingServiceTest

---

# Author

Abhishek Singh

Mobile No:9696481593

Email:abhishek184april@gmail.com
