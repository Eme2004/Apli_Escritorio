/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author Emesis
 */
public class ConexionBD {
    // Objeto estático para mantener una única conexión a la base de datos
    private static Connection conexion = null;

    // Datos para conectar a la base de datos PostgreSQL
    private static final String URL = "jdbc:postgresql://localhost:5432/Apli_escritorio";
    private static final String USUARIO = "postgres";
    private static final String CONTRASENA = "eme12"; 

    // Método para obtener la conexión con la base de datos
    public static Connection obtenerConexion() {
        try {
            // Verifica si la conexión es nula o está cerrada, para crear una nueva si es necesario
            if (conexion == null || conexion.isClosed()) {
                // Carga el driver JDBC de PostgreSQL
                Class.forName("org.postgresql.Driver");
                // Establece la conexión usando URL, usuario y contraseña
                conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
                System.out.println(" Conexión creada a PostgreSQL");
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(" Error al conectar a la base de datos");
            e.printStackTrace();
        }
        // Retorna la conexión establecida (o la existente)
        return conexion;
    }

    // Método  para cerrar la conexión antes de salir de la aplicación
    public static void cerrarConexion() {
        if (conexion != null) {
            try {
                conexion.close();  // Cierra la conexión abierta
                conexion = null;   // Limpia la referencia para crear una nueva si es necesario
                System.out.println("🔌 Conexión cerrada");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}