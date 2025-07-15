# Proyecto Aniclips

<h1 align="center">
    <img src="https://github.com/DavidSevillano/AniClips/blob/main/logo.jpg" alt="logo_aniclips"  width="300" height="300">
</h1>

## Descripción del Proyecto

AniClips es una plataforma para compartir y descubrir clips de anime. Permite a los usuarios subir clips, comentar y seguir a otros usuarios. La aplicación está diseñada para ser fácil de usar y accesible para todos los amantes del anime.

## Características

   - Registro y autenticación de usuarios.
   - Subida, búsqueda y gestión de clips de anime.
   - Comentarios, me gustas y valoraciones de clips.
   - Seguimiento de otros usuarios.
     
## Tecnologías Utilizadas

   🖥️ Backend
   
   - **Java**: Lenguaje de programación principal.
   - **Spring Boot**: Framework para construir la aplicación.
   - **PostgreSQL**: Base de datos utilizada para almacenar datos.
   - **Docker**: Contenerización de la aplicación.
   - **Maven**: Gestión de dependencias y construcción del proyecto.

   📱 Frontend (Android)

   - **Kotlin**: Desarrollo de la aplicación móvil nativa.
   - **Jetpack Compose**: Definición de interfaces gráficas (layouts) en Android Studio
   - **Android Studio**: Entorno de desarrollo para la app móvil.
   - **Retrofit**: Cliente HTTP para consumir la API REST desde Android.

## Requisitos

   🖥️ Backend

   - **Java JDK 17** o superior
   - **Maven 3.6.3** o superior
   - **Docker** (versión 20.10.0 o superior)
   - **Docker Compose** (versión 1.27.0 o superior)

   📱 Frontend (Android)

   - **Android Studio Giraffe** o superior
   - **JDK 11 o 17** configurado en Android Studio
   - **SDK** de Android API 26+
   - **Emulador o dispositivo físico** para pruebas

## Configuración del entorno

1. **Clonar el repositorio:**
   ```
   git clone https://github.com/DavidSevillano/AniClips_Kotlin_Jetpack-Compose.git
   cd AniClips
   ```

2. **Construir el proyecto:**
   Asegúrate de tener Maven instalado y ejecuta el siguiente comando para compilar el proyecto:
   ```
   mvn clean install
   ```

3. **Configurar la IP del backend**

   - Asegúrate de estar en la misma red Wi-Fi que el dispositivo.
     
   - Obtén la IP local de tu máquina desde el terminal con:
        ```
     **ipconfig**
        ```
   - Asegúrate de introducir en el archivo import.sql tu ip local en los registros de perfil y clip, justo donde pone "TU IP LOCAL AQUI", así te aparecerán los avatares y clips de ejemplo.
   ```
   ('TU IP LOCAL AQUI:8080/iconos/icono_ejemplo1.jpg', 'Aficionado de los anime de deportes como Blue Lock, Haikyuu, Aoashi etc', '11111111-1111-1111-1111-111111111111'),
   
                    |
                    v
    
   ('http://192.168.1.125:8080/iconos/icono_ejemplo1.jpg', 'Aficionado de los anime de deportes como Blue Lock, Haikyuu, Aoashi etc', '11111111-1111-1111-1111-111111111111')
   ```

4. **Configurar Docker:**
   Asegúrate de que Docker y Docker Compose estén instalados y en funcionamiento. Puedes verificarlo con:
   ```
   docker --version
   docker-compose --version
   ```

5. **Levantar el contenedor:**
   Utiliza Docker Compose para levantar el contenedor de la aplicación. Asegúrate de estar en el directorio raíz del proyecto y ejecuta:
   ```bash
   docker-compose up
   ```

## AVISO: Debido a que la aplicación está actualmente en desarrollo se recomienda hacer uso de un usuario predefinido. La función de activar cuenta está deshabilitada por problemas técnicos (Expiró el periodo de prueba de Mailtrap D,:).

- Nombre de usuario: StrikerDa10
- Contraseña: 1

## Si quieres probarlo con Postman:

1. **Uso de la colección de Postman:**
   Puedes importar la colección de Postman que se encuentra en el archivo `Aniclips.postman_collection.json` para facilitar las pruebas de la API.

2. **Registrarse en la aplicación:**
   Puedes registrarte en la aplicación introduciendo tus datos a través de la siguiente petición en Postman:
   ```
   POST http://localhost:8080/auth/register
   ```

   **Cuerpo de la solicitud:**
   ```
   {
       "username": "tu_usuario",
       "email": "tu_email@example.com",
       "password": "tu_contraseña",
       "verifyPassword": "tu_contraseña"
   }
   ```

3. **Activar tu cuenta:**
   Después de registrarte, recibirás un correo con un código de activación. Debes introducir el código de activación con la siguiente petición:
   ```
   POST http://localhost:8080/activate/account/
   ```

   **Cuerpo de la solicitud:**
   ```json
   {
       "token": "tu_token_de_activacion"
   }
   ```

4. **Iniciar sesión:**
   Ahora solo queda iniciar sesión introduciendo tu nombre de usuario y contraseña a través de esta petición:
   ```
   POST http://localhost:8080/auth/login
   ```

   **Cuerpo de la solicitud:**
   ```json
   {
       "username": "tu_usuario",
       "password": "tu_contraseña"
   }
   ```
   
## Si quieres probarlo con un dispositivo movil físico o virtual solo tienes que:

1. **Abrir el proyecto en Android Studio**

    - Abre Android Studio.

    - Selecciona "Open an existing project".

    - Navega hasta la carpeta AniClips_front y ábrela.
  
2. **Configurar la IP del frontend**

Como el backend está dockerizado, localhost no funcionará desde un dispositivo físico ni desde un emulador. Para solucionarlo:

   - Asegúrate de estar en la misma red Wi-Fi que el dispositivo.
     
   - Obtén la IP local de tu máquina desde el terminal con:
        ```
     **ipconfig**
        ```

   - En el proyecto abre la clase **view/core/NetworkModule** y cambia la ApiBaseUrl.
        ```
     val ApiBaseUrl = "http://<IP_LOCAL>:8080/";
                    |
                    v
     val ApiBaseUrl = "http://192.168.1.125:8080/";
        ```

3. **Ejecutar en un dispositivo o emulador**

   - Conecta tu dispositivo Android por USB (modo desarrollador activado) o usa un emulador
     
   - Presiona el botón de Run (▶️) en Android Studio.
     
   - La app se iniciará y si el backend está levantado correctamente con Docker, podrás consumir todos los endpoints disponibles.
