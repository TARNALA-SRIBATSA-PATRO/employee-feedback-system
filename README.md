# Employee Feedback System

A Spring Boot + MySQL backend project to manage employee feedback with automated email reminders. Project managers can rate employees and view all feedback history. Email reminders are sent on a schedule using Spring Scheduler.

---

## 🎓 Technologies Used

* Java 17
* Spring Boot 3.x
* Spring Data JPA
* MySQL
* Spring Scheduler
* JavaMailSender (Gmail)
* Postman for API Testing

---

## 📂 Folder Structure

```
feedback/
├── src/
│   └── main/
│       ├── java/com/rumango/feedback/
│       │   ├── controller/
│       │   │   └── EmployeeController.java
│       │   ├── entity/
│       │   │   ├── EmployeeRating.java
│       │   │   └── EmployeeFeedback.java
│       │   ├── repository/
│       │   │   ├── EmployeeRepository.java
│       │   │   └── FeedbackRepository.java
│       │   ├── scheduler/
│       │   │   └── AutoEmailScheduler.java
│       │   ├── service/
│       │   │   └── EmailSenderService.java
│       │   └── FeedbackApplication.java
│       └── resources/
│           └── application.properties
└── pom.xml
```

---

## 🔧 How to Set Up the Database

### Step 1: Create Database

```sql
CREATE DATABASE employee_feedback;
```

### Step 2: Create Tables

#### `employee_rating`

```sql
CREATE TABLE employee_rating (
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(255),
  email VARCHAR(255),
  project_working_on VARCHAR(255),
  start_date DATETIME(6),
  end_date DATETIME(6),
  manager_name VARCHAR(255),
  manager_email VARCHAR(255)
);
```

#### `employee_feedback`

```sql
CREATE TABLE employee_feedback (
  id INT PRIMARY KEY AUTO_INCREMENT,
  employee_id INT NOT NULL,
  rating INT,
  comment VARCHAR(500),
  rated_by VARCHAR(255),
  rated_on DATETIME DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (employee_id) REFERENCES employee_rating(id)
);
```

---

## 🚫 Where to Configure Email Sending

### Step 1: Go to `application.properties`

```properties
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=tsribatsapatro@gmail.com    # ✉️ Change this to your email
spring.mail.password=YOUR_APP_PASSWORD            # 🔐 Add App Password here
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
```

### Step 2: Create App Password (for Gmail)

* Visit: [https://myaccount.google.com/apppasswords](https://myaccount.google.com/apppasswords)
* Generate an App Password under "Mail" section
* Paste it into `spring.mail.password`

---

## ⏰ Email Scheduler Configuration

**Location:** `AutoEmailScheduler.java`

```java
@Scheduled(fixedRate = 3600000) // runs every 1 hour
public void sendRatingReminderToManagers() {
    // logic to send email to manager_email for each employee
}
```

You can change it to daily:

```java
@Scheduled(fixedRate = 86400000)
```

---

## 🚀 How to Run the Project

```bash
# 1. Open in IntelliJ or VS Code
# 2. Make sure MySQL is running
# 3. Update DB & mail credentials in application.properties
# 4. Run main class
```

> **Main class:** `FeedbackApplication.java`

### Run with:

```bash
mvn spring-boot:run
```

---

## 🔍 Test with Postman

| Action              | Method | URL Example                                                    |
| ------------------- | ------ | -------------------------------------------------------------- |
| Save employee       | POST   | `http://localhost:8080/employee/save`                          |
| View all employees  | GET    | `http://localhost:8080/employee/all`                           |
| View employee by ID | GET    | `http://localhost:8080/employee/1`                             |
| Update employee     | PUT    | `http://localhost:8080/employee/update`                        |
| Delete employee     | DELETE | `http://localhost:8080/employee/delete/1`                      |
| Submit rating       | PUT    | `http://localhost:8080/employee/rate/1?rating=5`               |
| With comment        | PUT    | `http://localhost:8080/employee/rate/1?rating=5&comment=Great` |
| All feedbacks       | GET    | `http://localhost:8080/employee/feedback/1`                    |

---

## 📃 Screenshot Suggestions

* 📊 MySQL Table Data (after rating)
![image](https://github.com/user-attachments/assets/e5ae57c4-1131-4d2a-80fb-27c9b7f50e39)

* 🚌 Postman API test (for `feedback`)
![image](https://github.com/user-attachments/assets/42a2b5e3-feac-415d-a3f1-8bf5c8147b67)

* 📧 Received Email in Inbox
![WhatsApp Image 2025-06-13 at 13 39 20_6c53720b](https://github.com/user-attachments/assets/ae46f809-5197-466a-821c-5ccbf6d3befb)

---

## 🚀 Future Improvements

* Add JWT Auth
* Manager login for submitting ratings
* Angular UI to view dashboards
* Average rating display

---

## 💼 Author

Tarnala Sribatsa Patro
Email: [tsribatsapatro@gmail.com](mailto:tsribatsapatro@gmail.com)
LinkedIn: [T. Sribatsa Patro](https://www.linkedin.com/in/t-sribatsa-patro/)
GitHub: [@TARNALA-SRIBATSA-PATRO](https://github.com/TARNALA-SRIBATSA-PATRO)

---

