/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package primer.parcial.estructura.de.datos;

/**
 *
 * @author kevin
 */
import java.util.Scanner;

public class MenuConsola {
    private static final ListaCircularDoble lista = new ListaCircularDoble();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        lista.agregarFinal(new Cancion("Comfortably Numb", "Pink Floyd", 382));
        lista.agregarFinal(new Cancion("Bohemian Rhapsody", "Queen", 354));
        lista.agregarFinal(new Cancion("Starless", "King Crimson", 738));

        int opcion = -1;
        do {
            mostrarOpciones();
            opcion = leerEntero("Seleccione una opción: ");
            procesarOpcion(opcion);
            System.out.println();
        } while (opcion != 13);
    }

    private static void mostrarOpciones() {
        System.out.println("==================================================");
        System.out.println("       GESTOR DE REPRODUCCIÓN (TAD CIRCULAR)      ");
        System.out.println("==================================================");
        System.out.println("1.  Agregar canción al inicio");
        System.out.println("2.  Agregar canción al final");
        System.out.println("3.  Buscar canción por ID");
        System.out.println("4.  Seleccionar canción por ID como actual");
        System.out.println("5.  Eliminar canción por ID");
        System.out.println("6.  Eliminar directamente la canción actual");
        System.out.println("7.  Consultar canción actual");
        System.out.println("8.  Avanzar a la siguiente canción");
        System.out.println("9.  Retroceder a la canción anterior");
        System.out.println("10. Mostrar lista completa (ambos sentidos)");
        System.out.println("11. Consultar cantidad de canciones");
        System.out.println("12. Simular reproducción de k canciones");
        System.out.println("13. Salir del programa");
        System.out.println("--------------------------------------------------");
    }

    private static void procesarOpcion(int op) {
        switch (op) {
            case 1 -> {
                Cancion c = capturarDatosCancion();
                if (c != null) {
                    lista.agregarInicio(c);
                    System.out.println("-> Canción agregada al inicio con éxito.");
                }
            }
            case 2 -> {
                Cancion c = capturarDatosCancion();
                if (c != null) {
                    lista.agregarFinal(c);
                    System.out.println("-> Canción agregada al final con éxito.");
                }
            }
            case 3 -> {
                int id = leerEntero("Ingrese el ID a buscar: ");
                Cancion c = lista.buscarPorId(id);
                if (c != null) {
                    System.out.println("-> Canción encontrada: " + c);
                } else {
                    System.out.println("-> No existe ninguna canción con el ID " + id);
                }
            }
            case 4 -> {
                int id = leerEntero("Ingrese el ID de la canción que desea como actual: ");
                if (lista.seleccionarPorId(id)) {
                    System.out.println("-> Canción actual actualizada: " + lista.consultarActual());
                } else {
                    System.out.println("-> No se pudo seleccionar. ID " + id + " no encontrado.");
                }
            }
            case 5 -> {
                int id = leerEntero("Ingrese el ID de la canción a eliminar: ");
                Cancion eliminada = lista.eliminarPorId(id);
                if (eliminada != null) {
                    System.out.println("-> Canción eliminada: " + eliminada);
                } else {
                    System.out.println("-> Operación fallida: ID " + id + " inexistente o lista vacía.");
                }
            }
            case 6 -> {
                Cancion eliminada = lista.eliminarActual();
                if (eliminada != null) {
                    System.out.println("-> Se eliminó la canción actual: " + eliminada);
                    if (!lista.estaVacia()) {
                        System.out.println("-> Nueva canción actual: " + lista.consultarActual());
                    } else {
                        System.out.println("-> La lista quedó vacía.");
                    }
                } else {
                    System.out.println("-> La lista ya se encuentra vacía.");
                }
            }
            case 7 -> {
                Cancion actual = lista.consultarActual();
                if (actual != null) {
                    System.out.println("-> Canción actual en turno: " + actual);
                } else {
                    System.out.println("-> La lista está vacía.");
                }
            }
            case 8 -> {
                Cancion sig = lista.avanzar();
                if (sig != null) {
                    System.out.println("-> Avanzado a: " + sig);
                } else {
                    System.out.println("-> La lista está vacía.");
                }
            }
            case 9 -> {
                Cancion ant = lista.retroceder();
                if (ant != null) {
                    System.out.println("-> Retrocedido a: " + ant);
                } else {
                    System.out.println("-> La lista está vacía.");
                }
            }
            case 10 -> {
                System.out.println("\n--- Recorrido Hacia Adelante (Inicio -> Fin) ---");
                lista.mostrarAdelante();
                System.out.println("\n--- Recorrido Hacia Atrás (Fin -> Inicio) ---");
                lista.mostrarAtras();
            }
            case 11 -> System.out.println("-> Total de canciones en la lista: " + lista.getTamanio());
            case 12 -> {
                int k = leerEntero("Ingrese la cantidad de reproducciones (k): ");
                lista.simularReproduccion(k);
            }
            case 13 -> System.out.println("Finalizando gestor de reproducción.");
            default -> System.out.println("Opción no válida.");
        }
    }

    private static Cancion capturarDatosCancion() {
        System.out.print("Título de la canción: ");
        String titulo = scanner.nextLine();
        System.out.print("Artista: ");
        String artista = scanner.nextLine();
        int duracion = leerEntero("Duración en segundos (> 0): ");
        if (duracion <= 0) {
            System.out.println("-> Error: La duración debe ser mayor a 0.");
            return null;
        }
        return new Cancion(titulo, artista, duracion);
    }

    private static int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                String linea = scanner.nextLine();
                return Integer.parseInt(linea.trim());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Debe ingresar un número entero.");
            }
        }
    }
}