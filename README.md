# API REST con Autenticación y Autorización 🛡️🍃

Este proyecto implementa una API REST robusta con autenticación basada en **JSON Web Tokens (JWT)** y un sistema de roles y permisos personalizado para cada usuario. La API ofrece endpoints públicos para el registro e inicio de sesión de los clientes, encriptando sus contraseñas en la base de datos. Además, proporciona endpoints protegidos para administradores y asistentes de administradores, quienes pueden realizar operaciones CRUD en productos y categorías. También se manejan relaciones entre entidades, todo almacenado en una base de datos MySQL.

## Características clave 📈

- **Autenticación con JWT**: Los usuarios deben autenticarse mediante tokens JWT para acceder a los recursos protegidos.
- **Roles y Permisos**: Cada usuario tiene un rol específico (cliente, administrador, asistente) con permisos definidos para poder usar o no endpoints.
- **Base de Datos MySQL**: Se utiliza MySQL para almacenar datos.
- **Encriptación de contraseñas**: Las contraseñas de los usuarios se encriptan usando BCrypt

## Instalación 🧑🏼‍💻

1. Clona este repositorio.
2. Configura tu base de datos MySQL y las variables de entorno.
3. Ejecuta el proyecto y prueba los endpoints

## Implementación con Docker 🐳
1. Clona este repositorio
2. Asegurate de tener Docker instalado
3. Crea el contenedor de MySQL con las credenciales del application-docker.properties o cambialas y crealas con las que quieras (igual con la base de datos)
4. Ejecuta el comando --> mvn clean package -DskipTests para conseguir el jar del proyecto
5. Ejecuta el comando de docker --> docker-compose up --build -d
6. Ejecuta el contenedor de la app
7. DISFRUTA DE LOS ENDPOINTS

## Documentación del proyecto 🗒️

He explicado todo el proceso en un documento público en Google Drive (Punto 4): https://docs.google.com/document/d/1vnzZ3QvvSO0E0mLggKKMDuPOd4-gq6dBL89URtORbZk/edit

## Video de pruebas de los endpoints 📹:
https://github.com/Alvarosanchezz3/Spring-Security/assets/99328696/f25bdf07-e645-4977-8d87-902528392dce
