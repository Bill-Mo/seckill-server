# Seckill Mall Project

This project is a high-concurrency Seckill Mall application based on Java Spring Boot, aiming to implement functionalities including user authentication, web mall, search, seckill products, and order payment.

## Key Features

- **User Authentication:** Implement user registration and login functionality.
- **Web Mall:** Display seckill product listings and details.
- **Search:** Allow users to search for products using keywords.
- **Seckill Products:** Support high-concurrency seckill product functionality.
- **Order Payment:** Implement order creation and payment functionality.

## Technology Stack

- Java
- Spring Boot
- Redis: Used for caching seckill product information and user login status.
- RabbitMQ: Used for handling order payment message queues.
- JMeter: Used for performance testing and stress testing.
- Thymeleaf: Used for frontend page rendering.

## Quick Start

1. **Clone the project to your local machine:**

    ```bash
    git clone https://github.com/Bill-Mo/seckill-server
    ```

2. **Modify the local paths in the configuration file:**

    In the `application.properties` file, modify the relevant configurations such as database connection information and Redis address.

3. **Run the application:**

    ```bash
    mvn spring-boot:run
    ```

    Visit `http://localhost:8080` to start using the Seckill Mall.

## Contributors

- [Tianwei Mo](https://github.com/Bill-Mo)

## Notes

- This project is currently in development and may have some incomplete or areas for optimization.
- Ensure that your environment configurations are correct, especially the database connection information and the addresses of Redis and RabbitMQ.
