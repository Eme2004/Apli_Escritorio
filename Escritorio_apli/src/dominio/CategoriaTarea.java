/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;
import java.time.LocalDate;
import javax.swing.JOptionPane;
/**
 *
 * @author Emesis
 */
public class CategoriaTarea extends Tarea {
    // Atributo adicional para clasificar la tarea por categoría
    private String categoria;

    // Constructor que llama al constructor de la superclase Tarea
    // y asigna la categoría, si es null asigna "General" por defecto
    public CategoriaTarea(long id, String titulo, int prioridad, boolean estado, LocalDate fecha, String categoria) {
        // El 'true' indica que esta tarea es especial (heredado de Tarea)
        super(id, titulo, prioridad, estado, true, fecha);
        this.categoria = categoria != null ? categoria : "General";
    }

    // Getter y setter para la categoría
    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    // Método toString que incluye la información heredada y la categoría adicional
    @Override
    public String toString() {
        return String.format("CategoriaTarea{super=%s, categoría='%s'}", super.toString(), categoria);
    }

    // Método para mostrar un diálogo con los detalles completos de la tarea especial
    public void mostrarDetalles() {
        JOptionPane.showMessageDialog(
            null,
            "[TAREA ESPECIAL]\n" +
            "Título: " + getTitulo() + "\n" +
            "Categoría: " + getCategoria() + "\n" +
            "Prioridad: " + getPrioridadTexto() + "\n" +
            "Estado: " + getEstadoTexto(),
            "Detalles",
            JOptionPane.INFORMATION_MESSAGE
        );
    }
}