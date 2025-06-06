# Traceability System

A web-based application for managing and visualizing trace links between artifacts in software development. This system allows users to create, search, and visualize artifacts (e.g., requirements, code, test cases, documents) and their relationships, ensuring traceability across the development lifecycle.

## Features

- Create and search artifacts with unique names (e.g., `req-01`).
- Establish trace links with customizable types via jsTree.
- Visualize trace links as interactive graphs using Cytoscape.js.
- Responsive UI with Thymeleaf and custom CSS.

## Technologies

- **Backend**: Spring Boot, MyBatis, MySQL
- **Frontend**: Thymeleaf, jQuery, jsTree, Cytoscape.js, Font Awesome
- **Build**: Maven

## Getting Started

1. Clone the repository: `git clone https://github.com/your-username/traceability-system.git`  
2. Configure MySQL database in `application.yml`.  
3. Run the application: `mvn spring-boot:run`  
4. Access at `http://localhost:8080`.

   
