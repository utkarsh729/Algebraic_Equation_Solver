Algebraic_Equation_Solver
A Spring Boot REST API to store, retrieve, and evaluate algebraic equations using a postfix expression tree.

📐 Algebraic Equation Solver (Spring Boot)
This is a RESTful Spring Boot backend for storing, retrieving, and evaluating algebraic equations using a postfix (Reverse Polish Notation) expression tree.

📦 Features
✅ Store algebraic equations (e.g., 3 * x + 2 * y - z)
✅ Parse and save them as postfix trees
✅ Retrieve stored equations in infix form
✅ Evaluate equations by substituting variable values
✅ JSON-based APIs — testable with Postman
✅ In-memory storage — no external DB needed
✅ Robust syntax validation and error handling
✅ 100% JUnit tests for normal and edge cases

📂 Tech Stack
Java 21
Spring Boot 3.5.3
Maven
JUnit 5
Lombok
⚙️ Prerequisites
Java 21 installed (java -version)
Maven installed (mvn -version)
Any REST client (Postman, cURL, etc.)
🚀 How to Run
1️⃣ Clone this repo: git clone https://github.com/utkarsh729/Algebraic-Equation-Solver.git cd algebraic-equation-solver 2️⃣ Build the project mvn clean install 3️⃣ Run the application mvn spring-boot:run The API will be available at: http://localhost:8080

API Documentation 📥 Store an Equation URL: POST /api/equations/store

Request Body json { "equation": "3 * x + 2 * y - z" }

Response: json

{ "message": "Equation stored successfully", "equationId": "1" } ✅ Function:

Parses the infix expression to postfix.

Builds a postfix tree.

Stores it in memory with a unique ID.

📤 Retrieve All Stored Equations URL: GET /api/equations

Response: json

{ "equations": [ { "equationId": "1", "equation": "3 * x + 2 * y - z" }, { "equationId": "2", "equation": "x ^ 2 + y ^ 2 - 4" } ] } ✅ Function:

Traverses the tree for each stored equation.

Returns the infix representation for display.

🧮 Evaluate an Equation URL: POST /api/equations/{equationId}/evaluate

Request Body: json { "variables": { "x": 2, "y": 3, "z": 1 } } Response: json { "equationId": "1", "equation": "3 * x + 2 * y - z", "variables": { "x": 2, "y": 3, "z": 1 }, "result": 10 } ✅ Function:

Substitutes provided variable values.

Evaluates the postfix tree.

Returns the computed result.

⚠️ Error Handling The API will respond with clear error messages for:

Invalid syntax

Mismatched parentheses

Invalid tokens

Missing or undefined variable values

Unknown equation ID

Example error response: json { "error": "Missing value for variable: z" } 🧪 Running Unit Tests Run all unit tests to verify storage, retrieval, evaluation, and edge cases: mvn test
