# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/3.4.6/maven-plugin)
* [Create an OCI image](https://docs.spring.io/spring-boot/3.4.6/maven-plugin/build-image.html)
* [Spring Data JPA](https://docs.spring.io/spring-boot/3.4.6/reference/data/sql.html#data.sql.jpa-and-spring-data)
* [Validation](https://docs.spring.io/spring-boot/3.4.6/reference/io/validation.html)
* [Spring Web](https://docs.spring.io/spring-boot/3.4.6/reference/web/servlet.html)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/3.4.6/reference/using/devtools.html)

### Guides
The following guides illustrate how to use some features concretely:

* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Validation](https://spring.io/guides/gs/validating-form-input/)
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)

### Maven Parent overrides

Due to Maven's design, elements are inherited from the parent POM to the project POM.
While most of the inheritance is fine, it also inherits unwanted elements like `<license>` and `<developers>` from the parent.
To prevent this, the project POM contains empty overrides for these elements.
If you manually switch to a different parent and actually want the inheritance, you need to remove those overrides.


# 📚 Proyecto: [Nombre del Proyecto]

Bienvenido/a al repositorio de [Nombre del Proyecto]. Este proyecto está desarrollado con Java Spring Boot, utiliza PostgreSQL como base de datos y está diseñado para ejecutarse en IntelliJ IDEA Ultimate.

---

## 🛠 Instalación

Sigue estos pasos para instalar y ejecutar el proyecto correctamente en tu entorno local.

### 1. Clonar el repositorio

Primero, necesitas clonar el repositorio desde GitHub:

```bash
git clone https://github.com/Finanzas-1ASI0642-2510-3429/BackEnd.git
```

> Asegúrate de tener `Git` instalado en tu sistema.

### 2. Requisitos previos

Antes de ejecutar el proyecto, necesitas instalar lo siguiente:

- [IntelliJ IDEA Ultimate](https://www.jetbrains.com/idea/download/)  
  _Necesario para soporte completo con Spring Boot y desarrollo Java avanzado._
- [PostgreSQL](https://www.postgresql.org/download/)  
  _Sistema de gestión de base de datos relacional._
- [pgAdmin](https://www.pgadmin.org/download/)  
  _Cliente visual para administrar bases de datos PostgreSQL. Se descarga automaticamente cuando descargas PostgreSQL_

### 3. Configurar base de datos PostgreSQL

1. Instala PostgreSQL y crea una base de datos llamada:

```
finance
```

2. Usa el usuario `postgres` y la contraseña `12345678` (o cambia estos valores en el archivo `application.properties` si usas otros).
3. Puedes usar `pgAdmin` para gestionar la base de datos de forma visual y sencilla.

### 4. Configurar el archivo `application.properties`

Revisa y configura el archivo ubicado en `src/main/resources/application.properties` con los siguientes valores:

```properties
# Spring Application Name
spring.application.name=UPC French Amortization Platform

# Spring DataSource Configuration
###    JDBC : SGDB :// HOST : PORT / DB
spring.datasource.url= jdbc:postgresql://localhost:5432/finance
spring.datasource.username= postgres
spring.datasource.password= 12345678
spring.datasource.driver-class-name= org.postgresql.Driver

# Spring Data JPA Configuration
spring.jpa.database= postgresql
spring.jpa.show-sql= true
```

> Modifica los valores según tu configuración local si es necesario.

### 5. Ejecutar el proyecto

1. Abre el proyecto con **IntelliJ IDEA Ultimate**.
2. Espera a que se resuelvan las dependencias (`Maven` o `Gradle`, según el proyecto).
3. Ejecuta la clase principal que contiene la anotación `@SpringBootApplication`.
4. Ingresa a la documentación mediante el link http://localhost:8090/swagger-ui/index.html#/

---
