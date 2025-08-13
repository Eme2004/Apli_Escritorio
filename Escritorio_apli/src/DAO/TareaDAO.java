/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import dominio.Tarea;
import app.ConexionBD;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Emesis
 */

public class TareaDAO {
    // Sentencia SQL para crear la tabla "tarea" si no existe
    private static final String CREAR_TABLA = """
        CREATE TABLE IF NOT EXISTS tarea (
            id BIGSERIAL PRIMARY KEY,                       // Identificador único autoincremental
            titulo VARCHAR(100) NOT NULL CHECK (titulo <> ''),  // Título obligatorio y no vacío
            prioridad INT NOT NULL CHECK (prioridad IN (1, 2, 3)), // Prioridad debe ser 1, 2 o 3
            estado BOOLEAN NOT NULL DEFAULT FALSE,          // Estado de la tarea (hecha o no), por defecto falsa
            especial BOOLEAN NOT NULL DEFAULT FALSE,        // Indicador si la tarea es especial, por defecto falsa
            fecha DATE,                                     // Fecha opcional para la tarea
            creado_en TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  // Fecha y hora de creación, asignada automáticamente
            actualizado_en TIMESTAMP DEFAULT CURRENT_TIMESTAMP // Fecha y hora de última actualización
        );
        """;

    // Sentencia SQL para insertar una nueva tarea
    private static final String INSERTAR = 
        "INSERT INTO tarea (titulo, prioridad, estado, especial, fecha) VALUES (?, ?, ?, ?, ?)";

    // Sentencia SQL para listar todas las tareas ordenadas por ID
    private static final String LISTAR = 
        "SELECT id, titulo, prioridad, estado, especial, fecha FROM tarea ORDER BY id";

    // Sentencia SQL para actualizar el estado (hecho/no hecho) y actualizar la fecha de modificación
    private static final String ACTUALIZAR_ESTADO = 
        "UPDATE tarea SET estado = ?, actualizado_en = CURRENT_TIMESTAMP WHERE id = ?";

    // Sentencia SQL para actualizar la fecha de la tarea y actualizar la fecha de modificación
    private static final String ACTUALIZAR_FECHA = 
        "UPDATE tarea SET fecha = ?, actualizado_en = CURRENT_TIMESTAMP WHERE id = ?";

    // Método para inicializar la base de datos creando la tabla si no existe
    public void inicializar() {
        try (Connection conn = ConexionBD.obtenerConexion();
             Statement stmt = conn.createStatement()) {
            stmt.execute(CREAR_TABLA);  // Ejecuta la creación de la tabla
            System.out.println("✅ Tabla 'tarea' lista");
        } catch (SQLException e) {
            e.printStackTrace(); // Muestra error en caso de fallo
        }
    }

    // Método para guardar una nueva tarea en la base de datos
    public void guardar(Tarea tarea) {
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement pstmt = conn.prepareStatement(INSERTAR, Statement.RETURN_GENERATED_KEYS)) {

            // Asigna los valores de la tarea a los parámetros SQL
            pstmt.setString(1, tarea.getTitulo());
            pstmt.setInt(2, tarea.getPrioridad());
            pstmt.setBoolean(3, tarea.isEstado());
            pstmt.setBoolean(4, tarea.isEspecial());
            // Convierte la fecha de LocalDate a java.sql.Date, o asigna null si no hay fecha
            pstmt.setDate(5, tarea.getFecha() != null ? Date.valueOf(tarea.getFecha()) : null);

            pstmt.executeUpdate(); // Ejecuta la inserción

            // Obtiene la clave generada (id) y la asigna al objeto tarea
            try (ResultSet keys = pstmt.getGeneratedKeys()) {
                if (keys.next()) {
                    tarea.setId(keys.getLong(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Muestra error en caso de fallo
        }
    }

    // Método para obtener todas las tareas almacenadas en la base de datos
    public List<Tarea> listarTodas() {
        List<Tarea> tareas = new ArrayList<>();
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement pstmt = conn.prepareStatement(LISTAR);
             ResultSet rs = pstmt.executeQuery()) {

            // Recorre el resultado y crea objetos Tarea con los datos obtenidos
            while (rs.next()) {
                Tarea t = new Tarea();
                t.setId(rs.getLong("id"));
                t.setTitulo(rs.getString("titulo"));
                t.setPrioridad(rs.getInt("prioridad"));
                t.setEstado(rs.getBoolean("estado"));
                t.setEspecial(rs.getBoolean("especial"));
                Date fechaSql = rs.getDate("fecha");
                if (fechaSql != null) t.setFecha(fechaSql.toLocalDate());
                tareas.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Muestra error en caso de fallo
        }
        return tareas; // Retorna la lista completa de tareas
    }

    // Método para actualizar el estado (hecho o no) de una tarea específica
    public boolean actualizarEstado(long id, boolean hecho) {
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement pstmt = conn.prepareStatement(ACTUALIZAR_ESTADO)) {
            pstmt.setBoolean(1, hecho);
            pstmt.setLong(2, id);
            // Retorna true si se actualizó al menos una fila
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Retorna false si hubo error
        }
    }

    // Método para actualizar la fecha asignada a una tarea específica
    public boolean actualizarFecha(long id, LocalDate fecha) {
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement pstmt = conn.prepareStatement(ACTUALIZAR_FECHA)) {
            // Convierte la fecha LocalDate a SQL Date, o asigna null si es null
            pstmt.setDate(1, fecha != null ? Date.valueOf(fecha) : null);
            pstmt.setLong(2, id);
            // Retorna true si se actualizó al menos una fila
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Retorna false si hubo error
        }
    }
}