# Mobile Attendance System with Integrated Fingerprint Sensor

This repository contains a complete solution for a mobile-based attendance system utilizing fingerprint authentication. The system consists of:
- An **Android mobile application** for students (with fingerprint integration)
- A **web-based admin panel** for managing students and attendance records
- **Server-side code** (likely Java) for backend processing and communication
- Comprehensive **documentation** including workflow diagrams and a project report

---

## Table of Contents

- [Project Structure](#project-structure)
- [Detailed Folder Overview](#detailed-folder-overview)
  - [Student_Management](#student_management)
  - [appcode](#appcode)
  - [serversidecodeapp](#serversidecodeapp)
  - [documentation](#documentation)
- [How to Run](#how-to-run)
  - [1. Web Admin Panel](#1-web-admin-panel)
  - [2. Android App](#2-android-app)
  - [3. Server-Side Backend](#3-server-side-backend)
- [How to Test](#how-to-test)
- [License](#license)

---

## Project Structure

```
/
├── Student_Management/      # Web admin panel (PHP)
├── appcode/                # Android application (Kotlin/Gradle)
├── serversidecodeapp/      # Backend server code (Java)
├── documentation/          # Documentation (PDFs, workflow diagrams)
├── LICENSE
└── README.md
```

---

## Detailed Folder Overview

### Student_Management

> [View all files in Student_Management/](https://github.com/print-ramcharan/mobile-attendance-sytem-with-integrated-fingerprint-sensor/tree/main/Student_Management)

This directory contains the **web-based administration panel** built in PHP. (Only the first 10 files are listed below due to listing limits; follow the link above for the full set.)

- **index.php**: Main dashboard for viewing/managing students and attendance.
- **AttendanceIndex.php**: Interface/page for managing attendance records.
- **addStudent.php**: Form and logic for adding a new student.
- **updateStudent.php**: Edit student details.
- **db.php**: Database connection logic.
- **getStudents.php**: Fetches student list from the database (likely returns JSON).
- **getStudentAttendance.php**: Fetches attendance data for a specific student.
- **updateAttendance.php**: Endpoint to update attendance records.
- **uploads/**: Directory for uploaded files (photos, documents, etc).
- **.settings/**, **.buildpath**, **.project**: Eclipse/IDE configuration files.

> **Note:** [There may be more files in this folder. Click here for the complete listing.](https://github.com/print-ramcharan/mobile-attendance-sytem-with-integrated-fingerprint-sensor/tree/main/Student_Management)

---

### appcode

> [View all files in appcode/](https://github.com/print-ramcharan/mobile-attendance-sytem-with-integrated-fingerprint-sensor/tree/main/appcode)

This folder contains the **Android mobile app** source code:

- **app/**: Main application source code (Kotlin/Java, resources, layouts).
- **build.gradle.kts / settings.gradle.kts**: Gradle build scripts.
- **gradlew / gradlew.bat / gradle/**: Gradle wrapper for building the app.
- **gradle.properties**: Gradle configuration.
- **.idea/**: Android Studio/IntelliJ project settings.

---

### serversidecodeapp

> [View all files in serversidecodeapp/](https://github.com/print-ramcharan/mobile-attendance-sytem-with-integrated-fingerprint-sensor/tree/main/serversidecodeapp)

This folder holds the **backend server code**, likely in Java:

- **src/**: Source code for backend logic (API endpoints, fingerprint validation, communication with PHP and app).
- **build/**: Compiled backend files.
- **.classpath / .project / .settings/**: Eclipse/IDE configuration files.

---

### documentation

> [View all files in documentation/](https://github.com/print-ramcharan/mobile-attendance-sytem-with-integrated-fingerprint-sensor/tree/main/documentation)

- **PROJECT REPORT.pdf**: Full project report with architecture, requirements, and implementation details.
- **Workflow.png**: System workflow diagram.
- **lecturer workflow.png**: Workflow from the lecturer/admin perspective.
- **student workflow.png**: Workflow from the student’s perspective.

---

## How to Run

### 1. Web Admin Panel

1. **Requirements:** PHP 7+, MySQL, a web server (XAMPP, WAMP, or LAMP recommended).
2. **Setup:**
   - Copy the `Student_Management` directory to your server's web root.
   - Create a MySQL database and import the required tables (see `db.php` for connection details).
   - Update `db.php` with your database credentials.
   - Open `http://localhost/Student_Management/` in your browser.

### 2. Android App

1. **Requirements:** Android Studio (latest), Android SDK, a physical device or emulator with fingerprint sensor support.
2. **Setup:**
   - Open the `appcode` directory in Android Studio.
   - Build the project using Gradle.
   - Configure the backend/server IP in app settings if required.
   - Install the APK on your device.
   - Register fingerprints on the device for authentication.

### 3. Server-Side Backend

1. **Requirements:** Java JDK 8+, an IDE (Eclipse recommended), and any required libraries.
2. **Setup:**
   - Import `serversidecodeapp` as a Java project in Eclipse.
   - Configure database and PHP endpoints as per your local environment.
   - Build and run the backend server.

---

## How to Test

- **End-to-end Test:** 
  1. Add students in the web admin panel.
  2. Register students in the Android app with fingerprint.
  3. Mark attendance using the app (fingerprint verification).
  4. Check attendance records in the admin panel.
- **Unit Testing:**
  - For PHP: Test API endpoints using Postman or browser.
  - For Android: Use Android Studio's test framework.
  - For Java backend: JUnit or similar frameworks.

---

## License

This project is licensed under the terms of the [LICENSE](LICENSE) file in this repository.

---

## Additional Resources

- [Complete Student_Management Directory Listing](https://github.com/print-ramcharan/mobile-attendance-sytem-with-integrated-fingerprint-sensor/tree/main/Student_Management)
- [Android App Source](https://github.com/print-ramcharan/mobile-attendance-sytem-with-integrated-fingerprint-sensor/tree/main/appcode)
- [Server Backend Source](https://github.com/print-ramcharan/mobile-attendance-sytem-with-integrated-fingerprint-sensor/tree/main/serversidecodeapp)
- [Documentation Folder](https://github.com/print-ramcharan/mobile-attendance-sytem-with-integrated-fingerprint-sensor/tree/main/documentation)

---

> **Note:** Due to GitHub API limits, only a subset of files and folders are shown above. Please refer to the provided links for the complete file structure and latest updates.
