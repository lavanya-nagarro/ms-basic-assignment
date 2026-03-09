# ---------- Build stage ----------
FROM maven:3.9.6-eclipse-temurin-17-alpine AS build
WORKDIR /app

# 1. Cache Maven dependencies separately from source code
# Only re-downloads if pom.xml changes
COPY pom.xml .
RUN mvn dependency:go-offline -B

# 2. Copy source and build
COPY src ./src
RUN mvn clean package -DskipTests

# ---------- Run stage ----------
# 3. Use Alpine-based JRE for the smallest footprint (~180MB vs ~600MB+)
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# 4. Use a non-root user for better security
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

# Copy artifact from build stage
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
