## BlackList Services

Proyecto para validar una cadena calculando la distancia de edición entre las cadenas usando el algoritmo de Levenshtein.

### Descripción
El proyecto es un microservicio desarrollado en Spring que valida si una clave está en una blacklist almacenada en una base de datos H2. Además, garantiza que no se puedan ingresar claves con pequeñas variaciones respecto a las ya existentes en la base de datos, por ejemplo si en la base de datos esta **hola** y el servicio recibe cadenas como **hol@**, **H0la** dira que la cadena no es valida debido al parecido entre las cadenas. Para realizar esta comparación, se emplea el algoritmo de Levenshtein.

Este proyecto contiene una lista de cadenas en el archivo **data.sql** 

### Algoritmo de Levenshtein
El algoritmo de Levenshtein es una técnica que calcula la "distancia de edición" entre dos cadenas de texto. En el contexto de este proyecto, se utiliza para medir la similitud entre la nueva clave ingresada y las claves prohibidas ya presentes en la base de datos. La distancia de Levenshtein cuenta el número mínimo de operaciones requeridas para transformar una cadena en la otra, considerando inserciones, eliminaciones y sustituciones de caracteres.

La lógica implementada en el método validatePassword emplea este algoritmo para evaluar si la nueva clave contiene similitudes cercanas con la clave prohibida, y así, asegurar la consistencia en la base de datos y evitar contraseñas que sean variaciones mínimas de las ya existentes.

### Requisitos

Para ejecutar este proyecto, necesitarás:

- Java 11
- Maven
- Git (opcional, para clonar el repositorio)

### Instalación

Sigue estos pasos para ejecutar el proyecto localmente:

1. Clona este repositorio: `git clone https://github.com/micha3lvega/blacklist-services.git` (o descárgalo como archivo ZIP)
2. Accede al directorio del proyecto: `cd blacklist-services`
3. Ejecuta el comando: `mvn clean install`
4. Ejecuta la aplicación: `mvn spring-boot:run`

### Uso

Puedes validar una contraseña enviando una solicitud POST al endpoint validate de la siguiente manera utilizando curl:

```bash
Copy code
curl -X 'POST' \
  'http://localhost:8080/api/v1/dictionary/validate/passw0rd' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json'
```

Este comando curl envía una solicitud POST al servidor local en el puerto 8080, al endpoint /api/v1/dictionary/validate/, con la contraseña "passw0rd" incluida en la URL. Asegúrate de reemplazar http://localhost:8080 con la dirección de tu servidor si es diferente.



### Contribución

¡Todos son bienvenidos a contribuir al proyecto! Si deseas contribuir, simplemente realiza un fork del repositorio, realiza tus cambios y envía un pull request. Asegúrate de que tus contribuciones sigan las pautas de contribución.

### Licencia

Este proyecto está bajo la Licencia Creative Commons.

Copyright (c) 2023 Michael Vega Carrillo

Licencia Creative Commons - NoComercial (CC BY-NC)

Esta licencia permite a otros remixar, modificar y construir a partir del trabajo original con fines no comerciales, tambien si es posible mencionar de alguna manera al autor se los agradeceria

Al hacer uso del software bajo esta licencia, está permitido:

- Compartir: copiar y redistribuir el material en cualquier medio o formato.
- Adaptar: remezclar, transformar y construir a partir del material.

Restricciones:

- Uso Comercial: No se puede utilizar el material con propósitos comerciales sin previa autorización.

Esto es solo un resumen de (y no sustituye) la licencia. Para ver la versión completa del acuerdo legal, consulta el archivo LICENSE en el repositorio.

### Contacto

@Micha3lVega