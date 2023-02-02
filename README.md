# APLICACIONES DISTRIBUIDAS (HTTP, SOCKETS, HTML, JS,MAVEN, GIT)

Aplicación para consultar la información de películas de cine. La aplicación recibe una frase de búsqueda del título de la pelicula y muestra los datos de la película correspondiente en una tabla haciendo uso del API gratuito de https://www.omdbapi.com/.

## Clonación del proyecto

Para descargar este proyecto, debe ejecutar el siguiente comando para descargar el proyecto:

```
git clone https://github.com/JuanPablo70/AREP-TALLER01.git
```

### Prerrequisitos

Para hacer uso de esta aplicación se debe tener conociemiento de:
+ Java - Lenguaje de programación orientado a objetos.
+ Maven - Herramienta para automatizar la gestión y construcción de proyectos Java. 

### Ejecución de la apliación

Para ejecutar la aplicación, debera en la linea de comandos (cmd) ubicarse en la carpeta donde se clonó el proyecto y ejecutar el siguiente comando:

```
mvn clean package exec:java -D "exec.mainClass"="edu.eci.arep.HttpServer"
```

Cuando se muestre en el mensaje "Listo para recibir ...", ingrese al link http://localhost:35000 y podrá realizar consultas.

Si ya no va a hacer uno de la aplicación, cierre la linea de comandos.

## Tests unitarios

Desde la linea de comandos ejecutar el siguiente comando:

```
mvn test
```

## Construido con

+ [Maven](https://maven.apache.org/) - Dependency Management

## Versión

1.0

## Autor

Juan Pablo Sánchez Bermúdez

## Diseño del proyecto