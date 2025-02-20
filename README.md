# Fullstack - Proyecto Backend y Frontend

Este proyecto incluye una aplicación de backend construida con **Spring Boot** y un frontend desarrollado con **React (Vite)**. El sistema utiliza **PostgreSQL** como base de datos y se despliega con **Docker**. El backend maneja autenticación mediante **JWT** y **Spring Security** con roles.

## Tecnologías utilizadas

- **Base de Datos**: PostgreSQL
- **Backend**: Spring Boot
  - Addons: Spring Security, JWT, roles y servicios
- **Frontend**: React (Vite)
---

## Proceso de Ejecución

### 1. Clonar el repositorio

Primero, debes clonar el repositorio del proyecto en tu máquina local.

```bash
git clone https://github.com/eleangelrivas/fullstakfebrero.git
cd fullstakfebrero

```
---

### 2. Compilación del backend

(Generación del archivo JAR) Recuerda: Es importante tener instalado maven en tu entorno.
Es necesario generar el archivo JAR del backend antes de levantar los contenedores de Docker. Sigue los siguientes pasos:

2.1 Navegar al directorio raíz del repositorio: 
Accede al directorio raíz del proyecto:

```bash
cd fullstackfebrero
```
2.2 Encender el contenedor de PostgreSQL
Para que la aplicación de backend se conecte correctamente a la base de datos PostgreSQL en tiempo de ejecución, primero debemos levantar el contenedor de PostgreSQL. Ejecuta el siguiente comando:

Nota: Eliminar cualquier contenedor e imagen que se pueda haber creado en cualquier versión de este proyecto anteriormente, para evitar cache en docker, si es tu primera vez que usas el proyect, continua sin eliminar ninguna imagen.

Para versiones antiguas de Docker Compose:
```bash
docker-compose up --build postgres
```
Para versiones más recientes:
```bash
docker compose up -d postgres
```
Nota 1: PostgreSQL es el nombre del servicio que se buscará en el archivo docker-compose.yml para encender el servidor de la base de datos.

Nota 2: El backend tiene dos propiedades de configuración (una para desarrollo y otra para Docker). Solo cambia la conexión al contenedor en la red de Docker.

2.3 Compilar el archivo JAR
Ahora, navega al directorio del proyecto de backend (eleangel-fullstack) y compila el proyecto con Maven para generar el archivo JAR que se utilizará en el contenedor Docker:
```bash
cd eleangel-fullstack
mvn clean package -DskipTests
```

Esto limpiará el proyecto y generará el archivo target/fullstack-0.0.1-SNAPSHOT.jar, el cual será copiado a la imagen del contenedor de backend en el siguiente paso.

---

### 3. Detener el contenedor de PostgreSQL

 
Una vez que se haya completado la compilación del archivo JAR, puedes detener el contenedor de PostgreSQL. Para ello, ejecuta el siguiente comando:

```bash
cd eleangel-fullstack
docker compose down
```
Si deseas hacer una instalación limpia y eliminar también los volúmenes asociados, usa el siguiente comando:

```bash
docker compose down -v
```

---

### 4. Levantar los contenedores con Docker Compose

Con el archivo JAR del backend listo, puedes levantar todos los contenedores necesarios (backend, frontend y base de datos) con Docker Compose.

Ejecuta el siguiente comando en el directorio donde se encuentra tu archivo docker-compose.yml:

![contenedor levantado](https://latecnology.com/imagenes-siman/compilacion.png)



```bash
docker compose up --build -d
```
o para versiones mas antiguas
```bash
docker-compose up --build
```
**Esto iniciará los contenedores de PostgreSQL, el backend (Spring Boot) y el frontend (React).**

---

### 5. Acceder al frontend
El frontend estará disponible en el siguiente puerto en tu máquina local:

http://localhost:5173


![contenedor levantado](https://latecnology.com/imagenes-siman/front.png)

---

### 6. Iniciar sesión como usuario administrador
Usa las siguientes credenciales para iniciar sesión como Administrador:

Usuario: ele19901

Contraseña: Root1234.$

---

### 7. Iniciar sesión como usuario operador
Usa las siguientes credenciales para iniciar sesión como Operador:

Usuario: operador

Contraseña: Root1234.$

**Notas Adicionales**

Verificar de que el contenedor de PostgreSQL esté funcionando antes de compilar el backend, ya que el backend depende de la base de datos para su funcionamiento.

Si encuentras problemas con la compilación o ejecución de los contenedores, verificar su entorno de Docker esté configurado correctamente y que todos los puertos necesarios estén disponibles.

Este proyecto fue desplegado en Docker version 27.4.0

---

### Contacto

Si tienes alguna duda o necesitas soporte, no dudes en contactarnos a través del repositorio o directamente a mi correo.

**La programación no es solo un conjunto de instrucciones para una máquina, sino una forma de dar vida a ideas, resolver problemas y transformar el caos en soluciones simples y elegantes.**
