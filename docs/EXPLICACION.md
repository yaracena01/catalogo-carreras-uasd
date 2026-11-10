# Explicación del proyecto

Resumen para la exposición. El detalle completo, con diagramas, código y todas las técnicas explicadas, está en `PASO-A-PASO-Y-TECNICAS.html`, que se abre con doble clic en cualquier navegador.

## 1. Qué hace

Un programa en Java con una ventana de Swing. El usuario elige una **facultad** de la UASD, pulsa **Consultar** y el programa le pide a una base de datos **SQL Server** las **carreras** de esa facultad y las muestra en una tabla, con su escuela, nivel (Técnico o Grado) y estado (Vigente o Proyectada).

El enunciado habla de "Escuela (Ingeniería, negocios, artes)". En la UASD esos nombres son **Facultades**, y cada facultad tiene varias **Escuelas**. Por eso el selector muestra facultades y la tabla muestra la escuela de cada carrera.

## 2. De dónde sale cada decisión

| Decisión | Fuente |
|---|---|
| Formulario de Swing con `initComponents()`, nombres como `btnConsultar` y prefijo `Frm_` | Los videos de formularios de NetBeans |
| El driver de SQL Server como archivo JAR agregado al proyecto (*Add JAR/Folder*) | El blog "Conectar SQL Server con Java" |
| Paquetes `conexion` y `formularios` | El proyecto `tienda` que se ve en el primer video |
| `Class.forName`, `DriverManager`, `Statement` y `ResultSet` | El ejemplo de conexión de la Unidad 7 |
| SQL Server, URL `jdbc:sqlserver://servidor:1433;databaseName=...`, usuario y clave, TCP/IP en el puerto 1433 | El blog "Conectar SQL Server con Java" |
| Herencia, clases abstractas, interfaces, polimorfismo, encapsulamiento, clases internas y paquetes | Las presentaciones XI y XII |
| Ventana con `WindowAdapter` | El ejemplo `HolaMundoSwing` |

El manual de Servlets y JSP no se usó: es para páginas web, y este programa es de escritorio. Es una interpretación del enunciado que conviene confirmar con el profesor.

## 3. Cómo está armado

| Paquete | Qué hay | Para qué |
|---|---|---|
| `modelo` | `EntidadCatalogo`, `Facultad`, `Carrera`, `CarreraTecnica`, `CarreraGrado`, `NivelCarrera` | Los datos como objetos |
| `dao` | `FacultadDAO`, `CarreraDAO` y sus clases con JDBC | Consultar la base de datos |
| `conexion` | `ConexionBD`, `InicializadorBD` | Abrir la conexión y preparar la base la primera vez |
| `formularios` | `Frm_Carreras` | El formulario de Swing, hecho con el diseñador de NetBeans (`Frm_Carreras.java` y `Frm_Carreras.form`) |

```
Frm_Carreras → CarreraDAO (interfaz) → CarreraDAOJdbc → ConexionBD → driver → SQL Server
```

## 4. Las técnicas de la clase y dónde están

| Técnica | Dónde |
|---|---|
| Clase abstracta y método abstracto | `EntidadCatalogo.java:3` y `:28`, `Carrera.java:3` y `:29` |
| Herencia en varios niveles y `super` | `EntidadCatalogo` → `Carrera` → `CarreraTecnica` y `CarreraGrado` (`CarreraTecnica.java:3` y `:6`) |
| Redefinición (`@Override`) y clase `Object` | `toString()` en `EntidadCatalogo.java:35` y `Carrera.java:37`; `equals()` en `EntidadCatalogo.java:40` con `getClass()` |
| `final` y `protected` | `describir()` en `EntidadCatalogo.java:30`; constructor en `EntidadCatalogo.java:7` |
| Polimorfismo y ligadura dinámica | `CarreraDAOJdbc.java:40` a `:43` crea las subclases; `Frm_Carreras.java:127` llama a `getNivel()` |
| Casting | `Frm_Carreras.java:108` |
| Interfaces y constantes en interfaces | `FacultadDAO.java:7`, `CarreraDAO.java:7`, `NivelCarrera.java:3` |
| La interfaz como tipo de variable | `Frm_Carreras.java:11` y `:12` |
| Encapsulamiento y `this` | Atributos `private` en `EntidadCatalogo.java:4` y `Carrera.java:4` |
| Sobrecarga | `mostrarMensaje` en `Frm_Carreras.java:164` y `:168` |
| Clases internas | `ModeloCarreras` en `Frm_Carreras.java:21`; anónimas en `:49` y `:78`, y `Main.java:30` |
| Paquetes e `import` con `*` | `Frm_Carreras.java:7` y `:8` |
| Swing y `WindowAdapter` | `Frm_Carreras.java:49`, como en `HolaMundoSwing` |
| JDBC | `Class.forName` en `ConexionBD.java:30`; `DriverManager.getConnection` en `:25`; `Statement` en `FacultadDAOJdbc.java:19` |

De la clase `Object` no se usan `clone` ni `finalize`: aquí no hay nada que clonar, y `finalize` está en desuso.

## 5. Qué no viene del material de clase

El patrón DAO con interfaces y la creación automática de la base. Todo lo demás sigue los videos, el blog y las presentaciones: el driver como archivo JAR agregado al proyecto, la conexión con `Class.forName` y `DriverManager`, los formularios de Swing y la programación orientada a objetos.

## 6. Qué se instaló

| Qué | Para qué |
|---|---|
| **JDK Eclipse Temurin 21** | Compilar y ejecutar Java. La máquina no tenía Java |
| **Driver `mssql-jdbc` 12.8.1** (un archivo JAR, en la carpeta `lib`) | Conectar Java con SQL Server. Es lo que el blog agrega con *Add JAR/Folder* |
| **SQL Server 2022 Express** (instancia `SQLEXPRESS`, modo mixto, TCP/IP en el puerto 1433) | La base de datos. Es el procedimiento del blog de la clase |

## 7. Qué se probó

- Compilación con `compilar.bat`, sin errores.
- SQL Server 2022 Express instalado en el PC: se crea la base, se cargan los datos y una segunda ejecución no duplica nada.
- Las 9 facultades devuelven 140 carreras: 25 técnicas y 115 de grado, igual que la base. Una facultad inexistente devuelve 0 sin romperse.
- El formulario real con `ejecutar.bat`: el aviso sin facultad, Ingeniería y Arquitectura (9 carreras) y Artes (29 carreras).
- Dentro de Apache NetBeans 31 con JDK 21: se creó el proyecto con el asistente, se agregó el driver y se ejecutó. El proyecto compila para Java 11.
## 8. Limitaciones

Los datos son una foto del catálogo 2024 y no se actualizan solos. No incluye postgrado. Es solo de consulta. Necesita un SQL Server por TCP en el puerto 1433, o el que se configure en `ConexionBD.java`.
