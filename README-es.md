## BlackList Services
Proyecto para validar una cadena calculando la distancia de edición entre las cadenas usando el algoritmo de Levenshtein. 

### Descripción 
El proyecto es un microservicio desarrollado en Spring que válida si una clave está en una blacklist almacenada en una base de datos H2. Además, garantiza que no se puedan ingresar claves con pequeñas variaciones respecto a las ya existentes en la base de datos, por ejemplo si en la base de datos esta la cadena **password** al enviar al servicio cadenas como **passw0rd**, **P@ssword** este retornara que la cadena es *inválida* debido al parecido entre ellas. Las cadenas de la blacklist se encuentran en un script de base de datos llamado *data.sql. Para realizar esta comparación, se emplea el algoritmo de *Levenshtein*.  

### Algoritmo de Levenshtein
El algoritmo de Levenshtein es una técnica que calcula la “distancia de edición” entre dos cadenas de texto. En el contexto de este proyecto, se utiliza para medir la similitud entre la nueva clave ingresada y las claves prohibidas ya presentes en la base de datos. La distancia de Levenshtein cuenta el número mínimo de operaciones requeridas para transformar una cadena en la otra, considerando inserciones, eliminaciones y sustituciones de caracteres.

El archivo de **datos.sql** contiene más de 200 palabras que son las más inseguras y usadas por los usuarios al momento de crear una contraseña según **NordPass**. 
Puedes ver más detalles en https://nordpass.com/es/most-common-passwords-list.

La lógica implementada en el método **isValidchain** emplea este algoritmo para evaluar si la nueva clave contiene similitudes con la clave prohibida, y así, asegurar la consistencia en la base de datos y evitar variaciones mínimas en la cadena.  

### Requisitos  
Para ejecutar este proyecto, necesitarás:

-  Java
-  Maven
-  Git (opcional, para clonar el repositorio)
 
### Instalación
Sigue estos pasos para ejecutar el proyecto localmente:

1. Clona este repositorio: `git clone https://github.com/micha3lvega/blacklist-services` (o también puedes descárgalo como un archivo ZIP).
2. Accede al directorio del proyecto: `cd blacklist-services`
3. Ejecuta el comando: `mvn clean install`
4. Ejecuta la aplicación: `mvn spring-boot:run`
  
### Uso
Puedes validar una contraseña enviando una solicitud *POST* al endpoint **validate** de la siguiente manera utilizando curl:

```bash
curl -X 'POST' \
  'http://localhost:8080/api/v1/blacklist/validate' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '"love123"'
```

Este comando curl envía una solicitud POST al servidor local en el puerto 8080, al endpoint: **/api/v1/blacklist/validate/**, con la contraseña "passw0rd" incluida en la URL. Asegúrate de reemplazar http://localhost:8080 con la dirección de tu servidor si es diferente.

## Pruebas
Aquí un ejemplo de las palabras que podría recibir el servicio, cuál está en base de datos y que responderá el servicio:

| Database Word | Palabra recibida | Resultado |
|-------------- |---------------   |--------   |
| "love123"     | "love124"        | Invalida  |
| "love123"     | "l0ve123"        | Invalida  |
| "maria"       | "mari4"          | Invalida  |
| "maria"       | "m4ri4"          | Invalida  |
| "maria"       | "m4r14"          | Invalida  |

### Contribución
¡Todos son bienvenidos a contribuir al proyecto! Si deseas contribuir, simplemente realiza un fork del repositorio, realiza tus cambios y envía un pull request. Asegúrate de que tus contribuciones sigan las pautas de contribución.

### Licencia
Este proyecto está bajo la Licencia Creative Commons.
Copyright (c) 2023 Michael Vega Carrillo
Licencia Creative Commons - NoComercial (CC BY-NC)
Esta licencia permite a otros remixar, modificar y construir a partir del trabajo original con fines no comerciales, también si es posible mencionar de alguna manera al autor se los agradecería.

Al hacer uso del software bajo esta licencia, está permitido:

- Compartir: copiar y redistribuir el material en cualquier medio o formato.
- Adaptar: remezclar, transformar y construir a partir del material. 

### Restricciones:  

Uso Comercial: No se puede utilizar el material con propósitos comerciales sin previa autorización.  
Esto es solo un resumen de (y no sustituye) la licencia. Para ver la versión completa del acuerdo legal, consulta el archivo LICENSE en el repositorio.
  

### Contacto
@Micha3lVega