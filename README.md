# 🚀 POS System Backend

## 📚 Overview

Welcome to the **POS System Backend**, a powerful and flexible backend solution built with **Spring** and **Hibernate** for managing retail operations. This project enables efficient handling of customer data, orders, and inventory through a robust RESTful API, making it a perfect companion for any POS frontend application.

Check the frontend - [POS System Frontend](https://github.com/sawaniThiwandika/pos-system-with-html.git)

## 🔧 Technologies Used

- **Java 21**: The latest features and performance improvements.
- **Spring**: Rapid development with convention over configuration.
- **Hibernate**: Simplified database interactions with ORM.
- **MySQL**: Reliable data storage and retrieval.
- **Maven**: Streamlined dependency management and project building.
- **RESTful API**: Efficient communication between frontend and backend systems.

## 🌟 Key Features

- **Dynamic Customer Management**: Effortlessly create, retrieve, update, and delete customer profiles.
- **Order Management System**: Streamlined order processing with item tracking and total calculation.
- **Inventory Control**: Manage stock levels, product details, and categories with ease.
- **Data Persistence Layer**: Reliable and structured data storage using MySQL.
- **Client-Side Testing**: Ensure seamless integration with comprehensive client testing strategies.

## 🚀 Getting Started

### ⚙️ Prerequisites

Before you begin, ensure you have the following installed:

- **Java 21** or higher
- **Maven**
- **MySQL Server**
- An API testing tool (e.g., Postman)

### 📥 Installation Steps

1. **Clone the Repository**:
    ```bash
    git clone https://github.com/yourusername/pos-system-backend.git
    cd pos-system-backend
    ```

2. **Set Up MySQL Database**:
   - Create a new database (e.g., `pos_system_db`) in your MySQL server.

3. **Build the Project**:
   ```bash
   mvn clean install
   

## 🔄 API Endpoints

### Customer Management

- **Create Customer**: `POST /api/v1/customer`
- **Retrieve Customer**: `GET /api/v1/customer/{id}`
- **Update Customer**: `PUT /api/v1/customer/{id}`
- **Delete Customer**: `DELETE /api/v1/customer/{id}`

### Order Management

- **Create Order**: `POST /api/v1/orders`
- **Retrieve Order**: `GET /api/v1/orders/{id}`

### Inventory Management

- **Add Item**: `POST /api/v1/items`
- **Retrieve Item**: `GET /api/v1/items/{id}`
- **Update Item**: `PUT /api/v1/items/{id}`
- **Delete Item**: `DELETE /api/v1/items/{id}`

## 🛠 Client Testing

- Use **Postman** or a similar tool to test API endpoints.
- Validate the functionality of each endpoint with various test cases.
- Ensure seamless integration with any frontend applications.

## 📜 License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

## 🙌 Acknowledgments

Thanks to the following technologies for enabling this project:

- [Spring](https://spring.io/) for its extensive capabilities in building applications.
- [Hibernate](https://hibernate.org/) for simplifying database interactions.
- [MySQL](https://www.mysql.com/) for its reliability as a database solution.
- [Maven](https://maven.apache.org/) for managing project dependencies and builds.

---
Feel free to contribute or reach out with any questions about the project!
