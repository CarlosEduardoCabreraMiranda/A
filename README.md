# Persona API

Este proyecto es una **API** básica construida con **Java** y **Spring Boot** para realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) sobre recursos de tipo `Persona`. A continuación, se describen los métodos **PATCH**, **HEAD**, y **DELETE**, junto con una comparación entre los métodos **PATCH** y **PUT**.

## Tecnologías Utilizadas
- **Java 17**: Lenguaje de programación principal.
- **Spring Boot**: Framework para crear aplicaciones Java de forma rápida y con configuración mínima.
- **Spring Data JPA**: Módulo de Spring para facilitar el acceso a bases de datos utilizando JPA.
- **MySQL**: Base de datos relacional utilizada para almacenar los datos de las personas.
- **Maven**: Herramienta de construcción y gestión de dependencias del proyecto.

## Atributos del recurso Persona

El recurso `Persona` contiene los siguientes atributos:

```json
{
  "nombre": "Juan Jose",
  "apellido": "Urbano Perdomo",
  "edad": 19
}

```
## Endpoints principales

- **PATCH** `http://localhost:8080/prueba/api/persona/{id}`
- **HEAD** `http://localhost:8080/prueba/api/persona/{id}`
- **DELETE** `http://localhost:8080/prueba/api/persona/{id}`

---

## Método PATCH: Actualización Parcial de Recursos

El método **PATCH** permite actualizar uno o varios campos de un recurso existente sin modificar los valores que no se proporcionen en la solicitud. Solo se actualizarán los campos que estén presentes en el cuerpo de la solicitud.

### Ejemplo de solicitud:
```http
URL: http://localhost:8080/prueba/api/persona/{id}
Content-Type: application/json
{
  "edad" : 20
}
```
Esta petición solamente actualizaría el campo que especificamos en el cuerpo de la petición, por ende, ese registro quedaría de la siguiente manera:
```json
{
  "nombre": "Juan Jose",
  "apellido": "Urbano Perdomo",
  "edad": 20
}
```
## Método HEAD: Encabezados
Devuelve solo los encabezados HTTP relacionados con el recurso sin incluir el cuerpo de la respuesta. Útil para verificar si un recurso existe o para obtener metadatos como la última modificación del recurso
### Ejemplo de solicitud:
```http
URL: http://localhost:8080/prueba/api/persona/{id}
```
### Ejemplo de respuesta:
```http
HTTP/1.1 200 OK
Content-Type: application/json
Content-Length: 0
Last-Modified: Mon, 23 Sep 2024 12:45:00 GMT
ETag: "1d4-5f3a74d3b6b80"
Date: Mon, 23 Sep 2024 12:50:00 GMT
Connection: keep-alive
```
## Método DELETE: Eliminar un recurso
Este método permite eliminar un registro de la base de datos utilizando su clave primaria {id}
### Ejemplo de solicitud:
```http
URL: http://localhost:8080/prueba/api/persona/{id}
```
### Ejemplo de respuesta.
Si la solicitud sale bien, obtendriamos lo siguiente:
```http
HTTP/1.1 200 OK
Content-Type: text/plain;charset=UTF-8
Content-Length: 51
Date: Mon, 23 Sep 2024 13:10:00 GMT
Connection: keep-alive
```
Si la solicitud no sale bien, obtendriamos lo siguiente:
```http
HTTP/1.1 404 Not Found
Content-Type: text/plain;charset=UTF-8
Content-Length: 51
Date: Mon, 23 Sep 2024 13:10:00 GMT
Connection: keep-alive
```
