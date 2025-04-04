# API de Gestión de cuentas y transferencias

Este proyecto es una API REST desarrollada con Spring Boot 3.4.3 y Java 17, que proporciona operaciones CRUD para la gestión de cuentas y creación de transferencias/reporte.
La base de datos utilizada es H2 en memoria, lo que facilita las pruebas y el desarrollo.

🚀 Tecnologías Utilizadas

Lenguaje: Java 17

Framework: Spring Boot 3.4.3

Base de Datos: H2 (en memoria)

Dependencias clave:

Spring Web

Spring Data JPA

H2 Database

Modelmapper

Spring Boot Starter Test

⚙️ Configuración y Ejecución

1️⃣ Clonar el Repositorio

git clone https://github.com/wmarmolejo/devsu-transfer-api.git

2️⃣ Configurar la Base de Datos

El proyecto usa H2 en memoria. El archivo application.yml ya está configurado:

**Nota:** en caso de querer ver los datos acceder al link al desplegar la api:
http://localhost:8082/h2-console

3️⃣ Ejecutar la Aplicación

Compilar y ejecutar con Gradle:
mvn spring-boot:run

La API estará disponible en:
 http://localhost:8082/api/cuentas
 http://localhost:8082/api/movimientos

**Nota:** Link de la imagen:
<li>https://hub.docker.com/r/wmarmolejo29/transfer-api</li> 
