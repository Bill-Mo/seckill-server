# Seckill Mall Project

This project is a high-concurrency Seckill Mall application based on Java Spring Boot, aiming to implement functionalities including user authentication, web mall, search, seckill products, and order payment. 

Now the project has been developed to the stage of user authentication, web mall, and seckill product display. Visit the [Seckill Mall](http://167.71.167.241:8080/seckill/index) to see the current progress.

## Key Features

- **User Authentication:** Implement user registration and login functionality.
- **Web Mall:** Display product listings and details.
- **Order Payment:** Implement order creation and payment functionality.
- **User Shopping Cart**: Implement shopping cart functionality. User can add products to the shopping cart and select products to operate.
- **User Acount Management**: User can modify their account name, password, and other information. User can also view their order history.

## Technology Stack

- Java
- Spring Boot
- Redis: Used for caching seckill product information and user login status.
- MySQL: Used for storing user information, product information, and order information.
- Thymeleaf: Used for frontend page rendering.

## Quick Start On Local Machine

0. **Prerequisites:**

    Make sure you have the following software installed and run on your local machine:
    - Java 8
    - Maven 3.9.2
    - Spring Boot 2.1.5
    - MySQL 8.0.33
    - Redis 3.2.100

1. **Clone the project to your local machine:**

    ```bash
    git clone https://github.com/Bill-Mo/seckill-server
    ```

2. **Modify the local paths in the configuration file:**

    In the `application.properties` file, modify the relevant configurations. To my knowledge, the following configurations might need to be modified:

    ```properties
    spring.datasource.url
    spring.datasource.username
    spring.datasource.password
    ```

3. **Run the application:**
    
    Run the `SeckillApplication.java`

    Visit `localhost:8080/seckill/index` to start using the Seckill Mall.

## Contributors

- [Tianwei Mo](https://github.com/Bill-Mo)

## Notes

- This project may have some incomplete or areas for optimization.
- Ensure that your environment configurations are correct, especially the database connection information and the addresses of Redis and MySQL.
