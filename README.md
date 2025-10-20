# Kaiburr Task 1

## Project Overview
This project is the solution for **Kaiburr Task 1**. It demonstrates the creation, execution, and management of tasks using a REST API. Users can:

- Create new tasks
- Execute tasks
- View task execution history
- Update and delete tasks

This project simulates a simple **task management system**.

---

## Technologies Used
- **Backend:** Java / Spring Boot (REST API)
- **Frontend / Client:** JavaScript (Axios for API calls)
- **Data Storage:** In-memory storage
- **Tools:** Git, Postman, VS Code / IntelliJ IDEA

---

## Project Structure

kaiburr_task1_full_code/
│
├─ src/ # Java source files for API
│ ├─ Task.java # Task model
│ ├─ TaskExecution.java # TaskExecution model
│ ├─ TaskController.java # API endpoints
│ └─ TaskService.java # Business logic
│
├─ client/ # JavaScript client files
│ └─ api.js # Axios API calls
│
├─ README.md # Project documentation
└─ other config or resource files

> All files are inside the main folder as per your local setup.

---

## Setup & Installation

### Step 1: Clone the repository
```bash
git clone https://github.com/your-username/KAIBURR_TASK-1.git
cd KAIBURR_TASK-1

Step 2: Run the Backend API

Open the project in an IDE (IntelliJ, Eclipse, or VS Code with Java extensions).

Run the Spring Boot application:

./mvnw spring-boot:run   # For Maven
# or
./gradlew bootRun         # For Gradle

Test the API

Use Postman, curl, or the JS client to test.

Get all tasks
GET http://localhost:8080/tasks

Add a task
POST http://localhost:8080/tasks
Headers: Content-Type: application/json
Body:
{
    "id": "101",
    "name": "Print Hello",
    "owner": "Akshay",
    "command": "echo Hello Kaiburr"
}

Update a task
PUT http://localhost:8080/tasks
Headers: Content-Type: application/json
Body:
{
    "id": "101",
    "name": "Print Hello Updated",
    "owner": "Akshay",
    "command": "echo Hello Kaiburr Updated"
}

Delete a task
DELETE http://localhost:8080/tasks/{id}

Execute a task
POST http://localhost:8080/tasks/{id}/execute


Response includes output and timestamps.
Using JavaScript Client

Open client/api.js.

Use functions like getTasks(), addTask(), updateTask(), deleteTask().

Example:

import { getTasks, addTask } from './api.js';

addTask({
    id: "102",
    name: "Print Bye",
    owner: "Akshay",
    command: "echo Bye Kaiburr"
}).then(() => getTasks().then(console.log));

Key Features

REST API for task management

Stores task execution history

CRUD operations supported

Simple and easy to extend

Author:

Akshay

Notes:

Educational project demonstrating API and task execution logic.

For production, consider using database storage instead of in-memory.

Ensure Java 11+ and Maven/Gradle installed.


---

If you want, I can **also make a ready-to-run Git command sequence** that will push **all your local files including this README** to GitHub **without merge errors**, so you don’t have to manually fix the `main -> main (fetch first)` issue.  

Do you want me to do that next?
