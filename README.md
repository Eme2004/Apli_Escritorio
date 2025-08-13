✨  Apli_escritorio ✨

> *"La organización es la clave del éxito"* 💫

<div align="center">

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Swing](https://img.shields.io/badge/Swing-FF6B6B?style=for-the-badge&logo=java&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)
![Git](https://img.shields.io/badge/GIT-E44C30?style=for-the-badge&logo=git&logoColor=white)

</div>

## 🌸 Descripción del Proyecto

Una hermosa aplicación de escritorio desarrollada con mucho amor 💕 para gestionar tus tareas diarias de manera eficiente. Construida desde cero utilizando los principios de **Programación Orientada a Objetos** y las mejores prácticas de desarrollo.

### 🎀 Características Principales

- ✅ **Crear tareas** con título, prioridad y fecha
- 🌟 **Marcar tareas especiales** con estrellitas
- 📋 **Visualización en tabla** súper organizada
- 🔄 **Alternar estados** entre Hecho/Pendiente
- 🗑️ **Eliminación inteligente** (sin perder datos)
- ↩️ **Función deshacer** para esas veces que te arrepientes
- 💾 **Persistencia total** - tus datos siempre seguros
- ✨ **Validaciones** que cuidan tu información

## 🛠️ Tecnologías Utilizadas

### 💻 Desarrollo
- **Lenguaje:** Java ☕
- **IDE:** NetBeans (con mucho cariño)
- **GUI Framework:** Swing para interfaces preciosas
- **Build Tool:** Maven/Ant

### 🗄️ Base de Datos
- **Motor:** PostgreSQL 🐘
- **Versión:** 15.x
- **Driver:** postgresql-42.7.7.jar ✨
- **Conexión:** JDBC con prepared statements

### 📦 Arquitectura del Proyecto
```
📁 Escritorio_apli [main]/
├── 📁 Source Packages/
│   ├── 💾 DAO/
│   │   └── 📄 TareaDAO.java          # Acceso a datos con JDBC
│   ├── 🖼️ Imagenes/                   # Recursos visuales
│   ├── ⚙️ Servicio/
│   │   └── 📄 GestorTareas.java      # Lógica de negocio
│   ├── 🚀 app/
│   │   └── 📄 ConexionBD.java        # Configuración de BD
│   ├── 🏗️ dominio/
│   │   ├── 📄 CategoriaTarea.java    # Enumeraciones
│   │   ├── 📄 Tarea.java             # Entidad principal
│   │   └── 📄 TareaExcepcion.java    # Excepciones personalizadas
│   └── 🎨 ui/
│       ├── 📄 Inicio.java            # Ventana principal
│       ├── 📄 Registro.java          # Formularios
│       └── 📄 VentanaPrincipal.java  # GUI principal
├── 📁 Test Packages/                  # Pruebas unitarias
├── 📁 Libraries/
│   ├── 📚 postgresql-42.7.7.jar     # Driver PostgreSQL
│   └── ☕ JDK 24 (Default)           # Java Development Kit
└── 📁 Test Libraries/                 # Librerías de testing
```

## 🎯 Configuración de Base de Datos

### 🔗 Formato de URL JDBC
```java
jdbc:postgresql://localhost:5432/escritorio_db
```

### 🏗️ Plantilla de Conexión
```java
// 📁 app/ConexionBD.java
public class ConexionBD {
    private static final String URL = "jdbc:postgresql://localhost:5432/escritorio_db";
    private static final String USER = "tu_usuario";
    private static final String PASSWORD = "tu_password";
    
    public static Connection obtenerConexion() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
```

### 📋 Inicialización
1. Crear base de datos: `CREATE DATABASE escritorio_db;`
2. La aplicación creará automáticamente las tablas necesarias
3. ¡Y listo para usar! 🎉

## 🚀 Cómo Ejecutar

### 📥 Prerrequisitos
- JDK 11 o superior ☕
- PostgreSQL instalado y ejecutándose 🐘
- NetBeans IDE 💻
- Git (para clonar el repo) 🌸

### 🌟 Pasos de Instalación
1. **Clonar el repositorio:**
   ```bash
   git clone https://github.com/Eme2004/Apli_Escritorio.git
   ```

2. **Configurar la base de datos:**
   - Crear la BD en PostgreSQL
   - Actualizar credenciales en `ConexionDB.java`

3. **Ejecutar desde NetBeans:**
   - Abrir el proyecto
   - Resolver dependencias
   - Run! ▶️

## 💖 Paradigmas de Programación

### 🏗️ Programación Estructurada
```java
// Validación simple y directa
public boolean validarTitulo(String titulo) {
    if (titulo == null || titulo.trim().isEmpty()) {
        return false;
    }
    return titulo.length() <= 100;
}
```

### 🎯 Programación Orientada a Objetos
```java
// 📁 dominio/Tarea.java - Encapsulamiento y abstracción beautiful
public class Tarea {
    private Long id;
    private String titulo;
    private CategoriaTarea prioridad; // Usando enum personalizado ✨
    
    // Polimorfismo en acción 💫
    @Override
    public String toString() {
        return String.format("Tarea{id=%d, titulo='%s'}", id, titulo);
    }
}
```

### 🌊 Programación Funcional (Streams)
```java
// Filtros elegantes con Streams 💫
List<Tarea> tareasAlta = tareas.stream()
    .filter(t -> t.getPrioridad() == Prioridad.ALTA)
    .filter(t -> !t.isCompletada())
    .collect(Collectors.toList());
```

## 🏗️ Decisiones de Diseño

### 🎨 Arquitectura en Capas
- **UI/UX:** `Inicio.java`, `Registro.java`, `VentanaPrincipal.java` - Interfaces Swing elegantes
- **Servicio:** `GestorTareas.java` - Lógica de negocio y validaciones
- **DAO:** `TareaDAO.java` - Acceso a datos con JDBC optimizado
- **Dominio:** `Tarea.java`, `CategoriaTarea.java`, `TareaExcepcion.java` - Entidades y reglas

### 📊 Estructuras de Datos Utilizadas
1. **ArrayList<Tarea>** - Para manejo dinámico de tareas
2. **Stack<Tarea>** - Para la función deshacer (LIFO)
3. **HashMap<Long, Tarea>** - Para búsquedas rápidas por ID

## 🔒 Manejo de Excepciones

### 🛡️ Excepciones Personalizadas
- `TareaExcepcion` - Para validaciones de datos y reglas de negocio
- `ConexionBDException` - Para problemas de conectividad
- `OperacionNoPermitidaException` - Para operaciones no válidas

### 💾 Transacciones
Todas las operaciones críticas están envueltas en transacciones para garantizar consistencia de datos.

## 📸 Capturas de Pantalla

### 🌟 Ventana de Inicio
![Ventana Inicio](screenshots/ventana-inicio.png)
*Pantalla de bienvenida con diseño minimalista y profesional. El ícono del clipboard con lápiz representa perfectamente la gestión de tareas ✨*

### ✨ Ventana Principal - Agendar Tarea
![Ventana Principal](screenshots/ventana-principal.png)
*Formulario elegante y funcional para crear tareas. Incluye todos los campos necesarios: título, prioridad (con dropdown), fecha y la opción especial con estrellita ⭐. Los botones "Guardar" y "Ver Tareas" permiten una navegación intuitiva.*

### 📋 Tabla de Tareas
![Ventana Tabla](screenshots/ventana-tabla.png)
*Vista de tabla completa con todas las funcionalidades implementadas. Muestra una tarea "Examen" con prioridad Alta, estado Pendiente, marcada como especial (⭐) y fecha 2025-08-25. Los botones "Alternar", "Deshacer" y "Eliminar" proporcionan control total sobre las tareas.*

### 💾 Persistencia de Datos
<img width="678" height="384" alt="image" src="https://github.com/user-attachments/assets/a836d2cb-b87f-47e4-b640-55e7d2fb4539" />
*Evidencia de que los datos persisten después de reiniciar la aplicación*

## ✅ Checklist de Características

- [x] 🎯 Aplicación abre sin errores
- [x] ➕ Crear tareas válidas
- [x] 🔄 Alternar estados Hecho/Pendiente  
- [x] 🗑️ Eliminación inteligente
- [x] ↩️ Función deshacer
- [x] ✅ Validaciones con mensajes claros
- [x] 📊 Dos estructuras de datos dinámicas
- [x] 🗺️ Diagramas UML completos
- [x] 🌿 Control de versiones con Git
- [x] 📚 Documentación completa

## 🎨 Diagramas UML

### 📊 Diagrama de Clases - Base de Datos
![UML BD](screenshots/uml-database.png)

**🗄️ Estructura de la tabla `tarea`:**
- 🔑 `id bigserial` - Clave primaria auto-incremental
- 📝 `titulo character varying(100)` - Título de la tarea (máx. 100 caracteres)
- 🎯 `prioridad integer` - Nivel de prioridad (1=Alta, 2=Media, 3=Baja)
- ✅ `estado boolean` - Estado de completado (true=Hecho, false=Pendiente)
- ⭐ `especial boolean` - Marca especial para tareas importantes
- 📅 `fecha date` - Fecha de la tarea (opcional)
- 🕐 `creado_en timestamp with time zone` - Auditoría: fecha de creación
- 🔄 `actualizado_en timestamp without time zone` - Auditoría: última actualización

*Diseño normalizado con campos de auditoría para trazabilidad completa* ✨

### 🏗️ Diagrama de Clases - POO
![UML POO](screenshots/uml-classes.png)

## 🌸 Contribuciones

Este proyecto fue desarrollado con mucho amor y dedicación 💕. Si tienes sugerencias o encuentras algún bug, no dudes en crear un issue.

## 📜 Licencia

Proyecto académico desarrollado para curso de Programación Orientada a Objetos.

---

<div align="center">

**✨ Hecho con 💖 y mucho ☕ por [Emesis Mairena Sevilla] ✨**
