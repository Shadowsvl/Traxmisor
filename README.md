# TRAXMISOR

Prueba técnica para Traxión usando [Jetpack Compose](https://developer.android.com/jetpack/compose). Con el objetivo de solucionar el problema propuesto con base en los requerimientos y mostrar un proyecto modular con las capacidades actuales para la creación de UI, y su uso en conjunto con el resto de librerías para navegación, inyección de dependencias, consumo de servicios REST y preferencias locales. Siguiendo las prácticas actuales recomendadas por la [Guía de Arquitectura](https://developer.android.com/topic/architecture).

## Características

El proyecto hace uso de Kotlin DSL para la construcción y configuración de dependencias para los diferentes tipos de módulos. Facilitando la creación de nuevos módulos y el mantenimiento de las dependencias generales.

La aplicación consta de dos pantallas:
1. Inicio de sesión, que implementa un caso de uso para la validación de correo. El flujo implementa un mock para la respuesta de autorización. Igualmente en esta pantalla se solicitan los permisos para locación del usuario.
2. Pantalla principal, que hace uso de un stateFlow para el monitoreo de la locación del usuario, así mismo se envía dicha locación a un servicio que implementa el servidor webhook propuesto. Igualmente muestra los datos del usuario así como una sección con botones para enviar señales de SOS a un servicio igualmente implementado usando el servidor webhook.

Cada pantalla muestra un snackbar cuando el internet no está disponible.

## Instrucciones de compilación

El proyecto utiliza la versión Gradle JDK `Amazon Corretto 18.0.2`

En caso de ser requerido se puede acceder a dicha configuración del proyecto en:
`File -> Settings -> Build, Excecution, Deployment -> Build Tools -> Gradle -> Gradle JDK`

#### Dependencias principales
* **Compose** - UI
* **Material 3** - Sistema de Diseño
* **Hilt** - Inyección de Dependencias
* **DataStore (Preferences)** - Persistencia de datos local
* **Retrofit** - Cliente HTTP
* **Coil** - Carga de imágenes
* **Play Services Location (Fused Location)** - Monitoreo de locación
* **Accompanist Permissions** - Solicitud de permisos explícitos