# SafetyRoadCOL
DISEÑO Y DESARROLLO DE UNA APLICACIÓN MOVIL PARA LA VINCULACIÓN DE VEHICULOS Y USUARIOS



- Juan Camilo Flores Pana
- Julian David Rodriguez Martinez
- Facultad de ingenieria
- Estructura de Datos

# INTRODRUCCIÓN
El presente proyecto tiene como objetivo abordar el diseño y desarrollo de una aplicación movil para ayudar a las empresas a mejorar la seguridad vial de sus empleados los cuales tengan asignado un vehiculo.
Este aplicativo movil será implementado en Kotlin, contando con herramientas suministradas por firebase, como lo son (autentication, storage y firestore). 
Así mismo, se trabajaran conceptos adquiridos durante el desarrollo de la unidad de estudio "Estructura de datos".

Finalmente, también se incluiran el diseño de la interfaz y herramientas utilizadas para la elaboración de la solución.

# NECESIDAD/JUSTIFICACIÓN

Mantener la seguridad de los empleados a nivel vehicular se a convertido en una necesidad para las compañias colombianas, ya que a nivel nacional en los ultimos años se han prsentado diversos desesos a consecuencia de accidentes viales.
por esta razón se propone SafetyRoadCol como medio de verificación de correcto funcionamiento de los vehiculos de la compañia asociada.

Ahora bien, aunque en el mercado actualmente existen distintos instrumentos tecnologicos y no-tecnologicos que buscan facilitar este proceso, 
ninguno se ha consolidado en tener una aplicación que sea amigable y confiable con el usuario final.

# REQUISITOS FUNCIONALES
<table>
    <tr>
        <td>Número de requerimiento</td>
        <td>RF001</td>
    </tr>
   <tr>
        <td>Nombre de requerimiento</td>
        <td>Autenticación de usuario</td>
    </tr>
    <tr>
        <td>Tipo</td>
        <td>Requisito</td>
    </tr>
    <tr>
        <td>Fuente del rquerimiento</td>
        <td>Usuario</td>
    </tr>
    <tr>
        <td>Proceso</td>
        <td>La aplicación permite validar las credenciales 
          de usuario a través de
          una pantalla de inicio de 
          sesión</td>
    </tr>
    <tr>
        <td>Prioridad del requerimiento</td>
        <td>Escencial</td>
    </tr>
</table>

<table>
    <tr>
        <td>Número de requerimiento</td>
        <td>RF002</td>
    </tr>
   <tr>
        <td>Nombre de requerimiento</td>
        <td>creacion de usuarios con peril</td>
    </tr>
    <tr>
        <td>Tipo</td>
        <td>Requisito</td>
    </tr>
    <tr>
        <td>Fuente del rquerimiento</td>
        <td>Usuario</td>
    </tr>
    <tr>
        <td>Proceso</td>
        <td>la aplicación permite que un usuario administrador pueda crear usuarios con acceso al siistema</td>
    </tr>
    <tr>
        <td>Prioridad del requerimiento</td>
        <td>Escencial</td>
    </tr>
</table>

<table>
    <tr>
        <td>Número de requerimiento</td>
        <td>RF003</td>
    </tr>
   <tr>
        <td>Nombre de requerimiento</td>
        <td>ingresar y asignar vehiculos nuevos</td>
    </tr>
    <tr>
        <td>Tipo</td>
        <td>Requisito</td>
    </tr>
    <tr>
        <td>Fuente del rquerimiento</td>
        <td>Usuario</td>
    </tr>
    <tr>
        <td>Proceso</td>
        <td>La aplicación permite que el usuario administrador cree y asigne vehiculos</td>
    </tr>
    <tr>
        <td>Prioridad del requerimiento</td>
        <td>Escencial</td>
    </tr>
</table>

<table>
    <tr>
        <td>Número de requerimiento</td>
        <td>RF004</td>
    </tr>
   <tr>
        <td>Nombre de requerimiento</td>
        <td>reporte diario</td>
    </tr>
    <tr>
        <td>Tipo</td>
        <td>Requisito</td>
    </tr>
    <tr>
        <td>Fuente del rquerimiento</td>
        <td>Usuario</td>
    </tr>
    <tr>
        <td>Proceso</td>
        <td>La aplicación permite que los usuarios que cuentan con vehiculo asignado diariamente, despues de una revisión realicen un reporte del estado vehicular</td>
    </tr>
    <tr>
        <td>Prioridad del requerimiento</td>
        <td>Escencial</td>
    </tr>
</table>


# REQUISITOS NO FUNCIONALES

<table>
    <tr>
        <td>Número de requerimiento</td>
        <td>RNF001</td>
    </tr>
   <tr>
        <td>Nombre de requerimiento</td>
        <td>Encripción</td>
    </tr>
    <tr>
        <td>Tipo</td>
        <td>Requisito</td>
    </tr>
    <tr>
        <td>Fuente del rquerimiento</td>
        <td>Administrador</td>
    </tr>
    <tr>
        <td>Proceso</td>
        <td>La contraseña se almacena encriptada en el firebase autentication</td>
    </tr>
    <tr>
        <td>Prioridad del requerimiento</td>
        <td>Escencial</td>
    </tr>
</table>

<table>
    <tr>
        <td>Número de requerimiento</td>
        <td>RNF002</td>
    </tr>
   <tr>
        <td>Nombre de requerimiento</td>
        <td>UI/UX</td>
    </tr>
    <tr>
        <td>Tipo</td>
        <td>Requisito</td>
    </tr>
    <tr>
        <td>Fuente del rquerimiento</td>
        <td>Administrador</td>
    </tr>
    <tr>
        <td>Proceso</td>
        <td>La aplicación cuenta con una interfaz de usuario intuitiva y fácil de usar</td>
    </tr>
    <tr>
        <td>Prioridad del requerimiento</td>
        <td>Escencial</td>
    </tr>
</table>

<table>
    <tr>
        <td>Número de requerimiento</td>
        <td>RNF003</td>
    </tr>
   <tr>
        <td>Nombre de requerimiento</td>
        <td>Almacenamiento de información</td>
    </tr>
    <tr>
        <td>Tipo</td>
        <td>Requisito</td>
    </tr>
    <tr>
        <td>Fuente del rquerimiento</td>
        <td>Administrador</td>
    </tr>
    <tr>
        <td>Proceso</td>
        <td>Los datos de la aplicación serán administrados mediante un sistema de gestión de bases de datos de firebase</td>
    </tr>
    <tr>
        <td>Prioridad del requerimiento</td>
        <td>Escencial</td>
    </tr>
</table>

# HERRAMIENTAS UTILIZADAS

1. Kotlin
   Kotlin es un lenguaje de programación de tipado estático que corre sobre la máquina virtual de Java
   y que también puede ser compilado a código fuente de JavaScript.
   
2. Android Studio
   Android Studio es el entorno de desarrollo integrado oficial para la plataforma Android. 
   Fue anunciado el 16 de mayo de 2013 en la conferencia Google I/O, 
   y reemplazó a Eclipse como el IDE oficial para el desarrollo de aplicaciones para Android. 
3. Firebase
   Firebase de Google es una plataforma en la nube para el desarrollo de aplicaciones web y móvil. 
   Está disponible para distintas plataformas (iOS, Android y web), con lo que es más rápido trabajar en el desarrollo.
   
   
# RESULTADOS

Se logró el desarrollo de la aplicación movil para la seguridad vial de empleados que cuentan con un vehiculo empresarial, 
cumpliendo con los requisitos funcionales y no funcionales propuestos.


# CONCLUSIÓN

El desarrollo de la aplicación movil utilizando Kotlin, Android Studio Y Firebase permitió un prototipado veloz y una fácil implementación, 
esto segun los requerimientos solicitados por el usuario final.

Finalmente, es importante mencionar que el desarrollo del presente proyecto permitió aplicar el conocimiento adquirido durante el transcurso del semestre.

