# Используем образ с JDK
FROM openjdk:17-jdk-slim

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]
# Устанавливаем рабочую директорию

#
# # Копируем файл сборки (например, jar) в контейнер
# COPY out/artifacts/demo_jar/demo.jar app.jar
# COPY resources/META-INF META-INF
# # Запускаем приложение
# ENTRYPOINT ["java", "-jar", "app.jar"]