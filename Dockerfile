# 1. Base image Java 17 (phù hợp Spring Boot 3)
FROM eclipse-temurin:21-jdk-jammy

# 2. Thư mục làm việc trong container
WORKDIR /app

# 3. Copy file jar vào container
COPY target/*.jar app.jar

# 4. Expose port (Render dùng biến PORT)
EXPOSE 8080

# 5. Chạy Spring Boot
ENTRYPOINT ["java", "-jar", "app.jar"]