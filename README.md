# Ocean Prime E-Commerce

Ocean Prime is a fully-featured e-commerce application designed to provide a seamless online shopping experience. It includes features like product browsing, user authentication, cart management, and secure payment integration.

## Features

- **User Authentication**: Secure registration, login, and profile management.
- **Product Management**:
  - Browse products by category.
  - Detailed product descriptions.
  - Search and filter functionality.
- **Shopping Cart**:
  - Add, update, or remove items.
  - View cart summary and total pricing.
- **Order Management**:
  - Place orders.
  - View order history.
- **Payment Integration**: Secure payment gateway for completing purchases.
- **Admin Panel**:
  - Manage users, products, and orders.
  - View sales reports.

## Tech Stack

### Backend
- **Java Spring Boot**: For developing robust APIs and business logic.
- **Spring Security**: For user authentication and authorization.
- **MySQL**: For database management.
- **Hibernate**: For Object-Relational Mapping (ORM).

### Frontend
- **Thymeleaf**: For server-side rendering and dynamic UI.
- **HTML/CSS**: For responsive design.
- **JavaScript**: For interactive functionality.

### DevOps
- **Docker**: For containerization.
- **Jenkins**: For continuous integration.
- **Kubernetes**: For orchestration.

## Getting Started

### Prerequisites
- Java 17 or higher
- Maven 3.8+
- MySQL 8+
- Docker (optional for containerized setup)

### Installation
1. **Clone the repository**:
   ```bash
   git clone https://github.com/DinelyWellehewage/ocean-prime-ecommerce.git
   cd ocean-prime-ecommerce
   ```

2. **Configure the database**:
   - Create a MySQL database named `ocean_prime`.
   - Update the `application.properties` file with your database credentials:
     ```properties
     spring.datasource.url=jdbc:mysql://localhost:3306/ocean_prime
     spring.datasource.username=YOUR_USERNAME
     spring.datasource.password=YOUR_PASSWORD
     spring.jpa.hibernate.ddl-auto=update
     ```

3. **Build and run the application**:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

4. **Access the application**:
   - Frontend: [http://localhost:8080](http://localhost:8080)
   - Admin Panel: [http://localhost:8080/admin](http://localhost:8080/admin)

### Running with Docker
1. **Build the Docker image**:
   ```bash
   docker build -t ocean-prime .
   ```

2. **Run the Docker container**:
   ```bash
   docker run -p 8080:8080 ocean-prime
   ```

3. Access the application as above.

## Usage

### User Roles
- **Customer**:
  - Register and log in to browse products and place orders.
- **Admin**:
  - Log in to manage products, users, and orders.


## Folder Structure
```
Ocean Prime E-Commerce
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com.oceanprime
│   │   ├── resources
│   │   │   ├── static
│   │   │   ├── templates
│   │   │   └── application.properties
│   └── test
│       └── java
├── Dockerfile
├── README.md
├── pom.xml
└── .gitignore
```

Ocean Prime 

![screen-capture1-ezgif com-video-to-gif-converter](https://github.com/user-attachments/assets/90d173ef-558a-40d1-b890-45c5b1ba8bb1)



Admin Dashboard
![Screenshot 2025-01-19 224308](https://github.com/user-attachments/assets/82690f08-5435-4475-bdb8-958172d6f154)

## Contact

For any inquiries, feel free to reach out:
- **Name**: Dinely Shanuka
- **Email**: dinelywh78@gmail.com

