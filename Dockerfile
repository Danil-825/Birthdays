FROM eclipse-temurin:17-jdk-alpine

# Рабочая директория внутри контейнера
WORKDIR /app

# Копируем собранный JAR-файл в контейнер
COPY target/Birthdays-0.0.1-SNAPSHOT.jar app.jar

# Открываем порт, на котором работает Spring Boot
EXPOSE 8080

# Команда для запуска приложения
ENTRYPOINT ["java", "-jar", "app.jar"]