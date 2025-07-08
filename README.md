# 🏥 Hospital Management System

A full-stack hospital management system that simplifies operations like doctor profiles, patient registration, appointment scheduling, and billing. This system is designed for administrative staff, medical personnel, and patients to interact with hospital data efficiently.

---

## ✅ Features

- 👨‍⚕️ **Admin & Doctor Dashboards** – manage users, view records, access patient data  
- 🧾 **Appointment Scheduling** – create, update, and track doctor–patient appointments  
- 🏥 **Department & Staff Management** – assign doctors/admins to departments  
- 🩺 **Medical History & Records** – manage diagnosis and treatment info per patient  
- 💳 **Invoice Management** – create, view, and update payment status  
- 🔐 **Authentication** – secure login for users (JWT integration recommended)

---

## 🧰 Tech Stack

### Backend
- **Java 17**  
- **Javalin (Web Framework)**  
- **MariaDB (Relational Database)**  
- **JDBC (DAO Pattern)**

### Frontend (Planned)
- **React.js**  
- **TypeScript**  
- **TailwindCSS / ShadCN UI**

---

## 🧱 Architecture

- DAO Layer → Service Layer → Controller Layer (Javalin routes)  
- Uses RESTful API design with clean separation of concerns  
- Modular service implementation for easier testing and maintenance

---

## 🚀 Getting Started

### Prerequisites

- Java 17+  
- Maven or Gradle  
- MariaDB installed  
- Node.js (if frontend is used)

### Backend Setup

```bash
# Clone repo and navigate
git clone https://github.com/yourusername/hospital-system.git
cd hospital-system

# Set up DB schema
mysql -u root -p < schema.sql

# Run app
mvn clean compile exec:java -Dexec.mainClass="com.hospital.Main"
