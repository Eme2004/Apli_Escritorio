/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

/**
 *
 * @author USER
 */
// Clase personalizada para manejar excepciones específicas de Tarea
public class TareaExcepcion extends Exception {

    // Constructor que recibe solo un mensaje de error
    public TareaExcepcion(String mensaje) {
        super(mensaje);  // Llama al constructor de Exception con el mensaje
    }

    // Constructor que recibe un mensaje y una causa (otra excepción que provocó esta)
    public TareaExcepcion(String mensaje, Throwable causa) {
        super(mensaje, causa);  // Llama al constructor de Exception con mensaje y causa
    }
}
