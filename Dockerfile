# Autor: Bartosz Zmysłowski

# Użycie obrazu bazowego z Java 17 i zainstalowanym Mavenem
FROM maven:3.8.4-openjdk-17-slim AS build

# Ustawienie katalogu roboczego w kontenerze
WORKDIR /app

# Skopiowanie pliku projektu Mavena - pom.xml
COPY pom.xml .

# Pobranie "dependencies" projektu (z pom.xml)
RUN mvn dependency:go-offline -B

# Skopiowanie kodu źródłowego aplikacji
COPY src ./src

# Zbudowanie aplikacji
RUN mvn package -DskipTests

# Utworzenie nowej warstwy dla obrazu wykonawczego
FROM maven:3.8.4-openjdk-17-slim

# Ustawienie katalogu roboczego w kontenerze
WORKDIR /app

# Skopiowanie pliku .jar z etapu budowania
COPY --from=build /app/target/fullstackwchmurze-1.jar ./fullstackwchmurze-1.jar

# Wystawienie portu nasłuchiwania aplikacji
EXPOSE 8080

# Definicja polecenia startu aplikacji
CMD ["java", "-jar", "fullstackwchmurze-1.jar"]