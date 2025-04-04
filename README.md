# API de Gestión de clientes

Este proyecto es una API REST desarrollada con Spring Boot 3.4.3 y Java 17, que proporciona operaciones CRUD para la gestión de clientes.
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

git clone https://github.com/wmarmolejo/devsu-client-api.git

2️⃣ Configurar la Base de Datos

El proyecto usa H2 en memoria. El archivo application.yml ya está configurado:

**Nota:** en caso de querer ver los datos acceder al link al desplegar la api:
http://localhost:8081/h2-console

3️⃣ Ejecutar la Aplicación

Compilar y ejecutar con Gradle:
mvn spring-boot:run

La API estará disponible en: http://localhost:8081/api/clientes

**Nota:** Link de la imagen:
<li>https://hub.docker.com/r/wmarmolejo29/people-api</li> 
