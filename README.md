## Escuela Colombiana de Ingeniería
# APLICACIONES DISTRIBUIDAS (HTTP, SOCKETS, HTML, JS, MAVEN, GIT)

Aplicación para consultar la información de películas de cine. La aplicación recibe una frase de búsqueda del título de la película y muestra los datos de la película correspondiente en una tabla haciendo uso del API gratuito de https://www.omdbapi.com/.

## Clonación del proyecto

Para descargar este proyecto, debe ejecutar el siguiente comando para descargar el proyecto:

```
git clone https://github.com/JuanPablo70/AREP-TALLER01.git
```

### Prerrequisitos

Para hacer uso de esta aplicación debe tener conocimientos de:
+ Java - Lenguaje de programación orientado a objetos.
+ Maven - Herramienta para automatizar la gestión y construcción de proyectos Java. 

### Ejecución de la aplicación

Para ejecutar la aplicación, debera en la línea de comandos (cmd) ubicarse en la carpeta donde se clonó el proyecto y ejecutar el siguiente comando:

```
mvn clean package exec:java -D "exec.mainClass"="edu.eci.arep.HttpServer"
```

Cuando se muestre en el mensaje "Listo para recibir ...", ingrese al link http://localhost:35000 y podrá realizar consultas.

Si ya no va a hacer uno de la aplicación, cierre la línea de comandos.

## Tests unitarios

Desde la línea de comandos ejecutar el siguiente comando:

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

El diseño de esta aplicación se basó en el diseño propuesto para este taller.

![](img/diseno-proyecto.png)

### Client

El cliente puede probar la aplicación de dos formas:

+ Ejecutando la aplicación como se mencionó anteriormente desde un browser donde se invocan servicios rest de forma asíncrona desde un cliente JS.
+ Desde un entorno de desarrollo corriendo la clase ```Client```.

### Java Test Client

Dado que hasta este momento no tengo el conocimiento para hacer pruebas concurrentes, se realizaron dos pruebas para verificar el funcionamiento de la aplicación:

+ Buscar la película deseada por el usuario.
+ Comprobar que el json de una película que fue buscada anteriormente se encuentre en el caché.

### Web Server

El servidor web actúa como un servidor de fachada permitiendo conectarse a una API externa mediante servicios REST (REpresentational State Transfer) para recibir peticiones por el protocolo HTTP y asi acceder a información mediante URLs.
[Servicios REST](https://abi.gitbook.io/net-core/3.-servicios-rest/3.1-servicios-rest)