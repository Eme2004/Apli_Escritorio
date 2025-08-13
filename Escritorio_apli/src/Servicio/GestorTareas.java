/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servicio;
import DAO.TareaDAO;
import dominio.Tarea;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * @author Emesis
 */

public class GestorTareas {

    // Objeto para acceso a la base de datos (CRUD de tareas)
    private final TareaDAO tareaDAO;

    // Lista dinámica que almacena las tareas actualmente visibles/activas
    private final List<Tarea> tareas;

    // Pila para almacenar las tareas eliminadas y permitir deshacer (LIFO)
    private final Deque<Tarea> pilaDeshacer;

    // Instancia única (Singleton) de GestorTareas para toda la aplicación
    private static GestorTareas instancia;

    // Constructor privado para impedir instanciación externa (patrón Singleton)
    private GestorTareas() {
        this.tareaDAO = new TareaDAO();
        this.tareas = new ArrayList<>();
        this.pilaDeshacer = new ArrayDeque<>(10); // Límite de 10 tareas para deshacer
        this.tareaDAO.inicializar(); // Crea la tabla en BD si no existe
        cargarDesdeBD();             // Carga las tareas existentes desde la BD
    }

    // Método para obtener la única instancia de GestorTareas (Singleton)
    public static GestorTareas obtenerInstancia() {
        if (instancia == null) {
            instancia = new GestorTareas();
        }
        return instancia;
    }

    // Carga las tareas almacenadas en la base de datos a la lista 'tareas'
    private void cargarDesdeBD() {
        tareas.clear();
        tareas.addAll(tareaDAO.listarTodas());
    }

    // 1. Crear una nueva tarea con validaciones
    public void crearTarea(String titulo, int prioridad, LocalDate fecha, boolean especial) {
        // Validar título no vacío
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new IllegalArgumentException("El título no puede estar vacío.");
        }
        // Validar prioridad entre 1 y 3
        if (prioridad < 1 || prioridad > 3) {
            throw new IllegalArgumentException("La prioridad debe ser 1 (Alta), 2 (Media) o 3 (Baja).");
        }

        // Crear objeto tarea y asignar valores
        Tarea tarea = new Tarea();
        tarea.setTitulo(titulo.trim());
        tarea.setPrioridad(prioridad);
        tarea.setEstado(false); 
        tarea.setEspecial(especial);
        tarea.setFecha(fecha);

        // Guardar tarea en la base de datos
        tareaDAO.guardar(tarea);
        // Añadir la tarea a la lista de tareas activas
        tareas.add(tarea);
    }

    // 2. Listar todas las tareas ordenadas por su ID
    public List<Tarea> listarTodas() {
        return tareas.stream()
                .sorted(Comparator.comparing(Tarea::getId))
                .collect(Collectors.toList());
    }

    // 3. Cambiar el estado de una tarea (pendiente a hecho o viceversa)
    public void alternarEstado(Tarea tarea) {
        boolean nuevoEstado = !tarea.isEstado(); // Cambia el estado actual
        // Actualiza el estado en la base de datos y, si se logró, actualiza el objeto local
        if (tareaDAO.actualizarEstado(tarea.getId(), nuevoEstado)) {
            tarea.setEstado(nuevoEstado);
        }
    }

    // 4. Eliminar una tarea: no la borra de la base de datos, solo la quita de la lista visible
    public void eliminarTarea(Tarea tarea) {
        if (tarea == null) return;

        // Copiar la tarea para poder recuperarla luego (deshacer)
        Tarea copia = copiarTarea(tarea);
        pilaDeshacer.push(copia);  // Apila la tarea eliminada para deshacer

        // Limitar la pila a 10 elementos para no ocupar memoria excesiva
        while (pilaDeshacer.size() > 10) {
            pilaDeshacer.removeLast();
        }

        // Remover tarea de la lista visible
        boolean removido = tareas.remove(tarea);
        if (!removido) {
            System.err.println(" No se encontró la tarea para eliminar: ID=" + tarea.getId());
        }
    }

    // 5. Deshacer la última eliminación (si hay alguna)
    public boolean deshacerEliminacion() {
        if (pilaDeshacer.isEmpty()) {
            return false; // No hay tareas para recuperar
        }

        // Recupera la tarea eliminada y la vuelve a agregar a la lista visible
        Tarea tarea = pilaDeshacer.pop();
        tareas.add(tarea);
        return true;
    }

    // 6. Persistencia de datos ya está manejada por TareaDAO, no se repite aquí

    // Método auxiliar para copiar un objeto Tarea (para guardar en pila de deshacer)
    private Tarea copiarTarea(Tarea original) {
        Tarea copia = new Tarea();
        copia.setId(original.getId());
        copia.setTitulo(original.getTitulo());
        copia.setPrioridad(original.getPrioridad());
        copia.setEstado(original.isEstado());
        copia.setEspecial(original.isEspecial());
        copia.setFecha(original.getFecha());
        return copia;
    }

    // Métodos auxiliares para la interfaz gráfica u otros usos
    public int tamañoPilaDeshacer() {
        return pilaDeshacer.size();
    }

    public boolean hayTareas() {
        return !tareas.isEmpty();
    }
}