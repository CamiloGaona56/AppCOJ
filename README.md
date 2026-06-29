# AppCOJ — Sistema de Gestión para Consultorio Odontológico (JIRETH)

**AppCOJ** (Consultorio Odontológico JIRETH) es una aplicación web empresarial diseñada para la automatización, gestión y control de procesos en consultorios odontológicos. Permite llevar un registro detallado de pacientes, definir procedimientos clínicos dentales, crear proyecciones de tratamientos personalizados, y realizar el seguimiento de pagos (abonos) y saldos asociados a los pacientes.

El sistema cuenta con un diseño responsivo de alta calidad basado en la plantilla de administración **Stisla** (Bootstrap 4, jQuery) y utiliza una arquitectura MVC clásica en Java EE (Servlets y JSP) con persistencia JPA y procedimientos almacenados en base de datos.

## 🚀 Características Principales

### 1. Gestión de Pacientes (CRUD)
* Registro y actualización de información personal de pacientes (nombre, documento, celular, recomendación/referencia y fecha de nacimiento).
* Búsqueda dinámica y control de estado (activo/inactivo).

### 2. Planificación de Tratamientos (Proyecciones)
* Creación de planes de tratamiento u odontogramas proyectados para pacientes.
* Selección de dientes individuales (basado en la nomenclatura dental ISO parametrizada en la base de datos).
* Asociación de procedimientos odontológicos específicos para cada diente.
* Control de presupuestos (presupuesto inicial vs. presupuesto actual) y anotaciones de observaciones clínicas.
* Checklists interactivos de revisión mediante AJAX para marcar tratamientos completados.

### 3. Registro de Abonos y Finanzas
* Seguimiento de abonos/pagos realizados por los pacientes.
* Cálculo en tiempo real de saldos pendientes.
* Selección de múltiples métodos de pago (Efectivo, Nequi, DaviPlata, Tarjeta).
* Registro de observaciones e historial completo de transacciones financieras por paciente.

### 4. Seguridad, Roles y Autenticación
* **Multi-rol**: Control de acceso para administradores y gestores (odontólogos/personal administrativo).
* **Acceso de Pacientes**: Módulo de consulta rápida donde el paciente puede verificar su estado y tratamientos ingresando únicamente su número de documento sin necesidad de contraseña.
* **Seguridad de Contraseñas**: Encriptación de contraseñas mediante hash MD5.
* **Restablecimiento**: Opción para restablecer contraseñas de usuarios.

### 5. Informes y Reportes
* Módulo de estadísticas generales y filtrado rápido de pacientes.
* Historial clínico de procedimientos y flujo de caja (ingresos por abonos).

---

## 🛠️ Tecnologías y Librerías Utilizadas

### Backend
* **Lenguaje**: Java 8 (Java/Jakarta EE).
* **Patrón de Arquitectura**: Modelo-Vista-Controlador (MVC).
* **Controladores**: Servlets nativos mapeados en `web.xml`.
* **Componentes Dinámicos**: JSP Custom Tags (Java Tag Library) implementadas para encapsular la generación de vistas HTML directamente desde el backend.
* **Persistencia (ORM)**: JPA (Java Persistence API) implementado con **EclipseLink**.
* **Base de Datos**: MySQL / MariaDB mediante almacenamiento relacional y ejecución de procedimientos almacenados (Stored Procedures).

### Frontend
* **Diseño y Maquetación**: **Stisla Admin Template** (Bootstrap 4 y CSS3 personalizado).
* **Framework JS**: jQuery.
* **Elementos Interactivos**:
  * **Select2**: Para dropdowns fluidos y buscables.
  * **DataTables**: Para tablas dinámicas con paginación, filtros y ordenamiento rápido.
  * **iziToast**: Para alertas del sistema y mensajes de éxito/error.
  * **FontAwesome**: Para la iconografía del sistema.

---

## 📊 Estructura de la Base de Datos (`BD_APP.sql`)

El sistema utiliza la base de datos relacional `appcoj` y cuenta con las siguientes tablas principales:

* **`paciente`**: Almacena los datos personales del paciente.
* **`procedimiento`**: Catálogo de tratamientos odontológicos disponibles (ej. Implantes Dentales, Extracciones).
* **`proyeccion`**: Registro de tratamientos asignados a un paciente con presupuesto e identificación de dientes.
* **`abono`**: Registro de pagos realizados y saldos pendientes.
* **`usuario`**: Personal clínico y administrativo del consultorio.
* **`rol`**: Roles de acceso al sistema (`Administrador` y `Gestor`).
* **`configuracion`**: Configuración global del sistema (parámetros de tipos de documentos e índices de dientes representados en formato ISO).

### Procedimientos Almacenados (Stored Procedures)
El archivo `BD_APP.sql` contiene más de 30 stored procedures optimizados para agilizar la comunicación entre Java JPA y la base de datos MySQL, entre ellos:
* `Paciente_c_ConsultarPacienteDocumento`, `Paciente_r_RegistrarPaciente`
* `Proyeccion_c_ConsultaProyeccionxPaciente`, `Proyeccion_r_RegistrarProyeccion`
* `Abono_r_RegistrarAbono`, `Abono_c_ConsultarAbtonoIdPaciente`
* `Usu_c_ConsultaUsuarioSesion`, `Usu_m_RestablecerContrasena`

---

## 📁 Estructura del Proyecto

El código está estructurado bajo las convenciones estándar de un proyecto Java Web de NetBeans:

```text
AppCOJ/
├── build/                 # Archivos compilados temporalmente
├── dist/                  # Empaquetado final (archivo .war)
├── lib/                   # Librerías y dependencias externas (.jar)
├── nbproject/             # Configuración interna del IDE NetBeans
├── src/
│   ├── conf/
│   │   ├── MANIFEST.MF
│   │   └── persistence.xml # Configuración de persistencia JPA y conexión a base de datos
│   └── java/              # Código fuente Java (Backend)
│       ├── Controlador/   # Controladores JPA generados por NetBeans (JPA Controllers)
│       ├── Entidad/       # Clases de entidad JPA (Mapeo de base de datos)
│       ├── Metodo/        # Clases de utilidad y métodos generales (ej. encriptación)
│       ├── Servelt/       # Servlets controladores de flujo web (Sesion, Paciente, etc.)
│       └── Tag/           # Lógica Java para generación de Custom Tags de JSP
├── web/                   # Recursos del cliente (Frontend)
│   ├── Contenido/         # Recursos estáticos (CSS, JS, Imágenes y Validaciones)
│   ├── WEB-INF/           # Configuraciones web privadas
│   │   ├── tlds/          # Tag Library Descriptors (.tld) para los componentes dinámicos
│   │   └── web.xml        # Descriptor de despliegue web de Java EE
│   ├── index.jsp          # Pantalla de login principal (usuarios y consulta rápida de pacientes)
│   ├── Inicio.jsp         # Dashboard del sistema
│   ├── Menu.jsp           # Barra lateral y navegación principal
│   └── (Módulos).jsp      # Vistas individuales (Paciente, Proyeccion, Rol, Usuario, etc.)
├── BD_APP.sql             # Script SQL de inicialización de la base de datos
└── build.xml              # Script Ant de construcción
```

---

## ⚙️ Configuración e Instalación

### Requisitos Previos
* **Java JDK 8** o superior.
* **Servidor de Aplicaciones**: Apache Tomcat 8.5/9.0, GlassFish o Payara Server.
* **Base de Datos**: MySQL 5.7+ o MariaDB 10.x.
* **IDE Recomendado**: NetBeans IDE 11+.

### Pasos de Despliegue

1. **Importar la Base de Datos**:
   * Abra su gestor de bases de datos (HeidiSQL, phpMyAdmin, MySQL Workbench).
   * Cree la base de datos `appcoj`:
     ```sql
     CREATE DATABASE appcoj CHARACTER SET utf8mb4;
     ```
   * Ejecute las sentencias del script [BD_APP.sql](file:///c:/Proyect/AppCOJ/BD_APP.sql) para inicializar las tablas, stored procedures e insertar los roles y usuario por defecto.

2. **Configurar la Conexión en Java**:
   * El archivo de conexión [persistence.xml](file:///c:/Proyect/AppCOJ/src/conf/persistence.xml) está preconfigurado para conectarse localmente:
     * **URL**: `jdbc:mysql://localhost:3306/appcoj`
     * **Usuario**: `root`
     * **Contraseña**: (vacía)
   * Modifique estas credenciales de ser necesario.

3. **Abrir y Desplegar el Proyecto**:
   * Abra NetBeans y cargue la carpeta del proyecto `AppCOJ`.
   * Agregue el servidor de aplicaciones de su preferencia al IDE.
   * Haga clic derecho en el proyecto -> **Clean & Build**.
   * Haga clic derecho -> **Run** para desplegarlo en el servidor local.

### Credenciales por Defecto
* **Usuario (Documento)**: `1012462411`
* **Contraseña**: `2025`
* **Rol**: Administrador

---
*Desarrollado para el control clínico del Consultorio Odontológico JIRETH.*
