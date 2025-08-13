/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;
import java.time.LocalDate;
/**
 *
 * @author Emesis
 */
public class Tarea {
    // Atributos privados que representan las propiedades de una tarea
    private long id;              // Identificador único de la tarea
    private String titulo;        // Título descriptivo de la tarea
    private int prioridad;        // Prioridad de la tarea: 1 (alta), 2 (media), 3 (baja)
    private boolean estado;       // Estado de la tarea: hecha o pendiente
    private boolean especial;     // Indicador de si la tarea es especial o no
    private LocalDate fecha;      // Fecha asociada a la tarea (opcional)

    // Constructor vacío por defecto
    public Tarea() {}

    // Constructor con todos los parámetros para crear una tarea completa
    public Tarea(long id, String titulo, int prioridad, boolean estado, boolean especial, LocalDate fecha) {
        this.id = id;
        setTitulo(titulo);        // Usa el método set para validar el título
        setPrioridad(prioridad);  // Usa el método set para validar la prioridad
        this.estado = estado;
        this.especial = especial;
        this.fecha = fecha;
    }

    // Getters y setters para cada atributo

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) {
        // Valida que el título no sea nulo ni vacío (tras eliminar espacios)
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new IllegalArgumentException("Título no puede estar vacío");
        }
        this.titulo = titulo.trim();
    }

    public int getPrioridad() { return prioridad; }
    public void setPrioridad(int prioridad) {
        // Valida que la prioridad esté entre 1 y 3 (inclusive)
        if (prioridad < 1 || prioridad > 3) {
            throw new IllegalArgumentException("Prioridad debe ser 1, 2 o 3");
        }
        this.prioridad = prioridad;
    }

    public boolean isEstado() { return estado; }
    public void setEstado(boolean estado) { this.estado = estado; }

    public boolean isEspecial() { return especial; }
    public void setEspecial(boolean especial) { this.especial = especial; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    // Método que devuelve la prioridad como texto según su valor numérico
    public String getPrioridadTexto() {
        return switch (prioridad) {
            case 1 -> "Alta";
            case 2 -> "Media";
            case 3 -> "Baja";
            default -> "Desconocida";
        };
    }

    // Método que devuelve el estado en texto ("Hecho" o "Pendiente")
    public String getEstadoTexto() {
        return estado ? "Hecho" : "Pendiente";
    }

    // Método toString para representar la tarea en texto resumido
    @Override
    public String toString() {
        return String.format("Tarea[id=%d, título='%s']", id, titulo);
    }
}
