/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package primer.parcial.estructura.de.datos;

/**
 *
 * @author kevin
 */
public class ListaCircularDoble {
    private Nodo cabeza;
    private Nodo cola;
    private Nodo actual;
    private int tamanio;

    public ListaCircularDoble() {
        this.cabeza = null;
        this.cola = null;
        this.actual = null;
        this.tamanio = 0;
    }

    public boolean estaVacia() {
        return tamanio == 0;
    }

    public int getTamanio() {
        return tamanio;
    }

    public void agregarInicio(Cancion cancion) {
        Nodo nuevo = new Nodo(cancion);
        if (estaVacia()) {
            nuevo.setSiguiente(nuevo);
            nuevo.setAnterior(nuevo);
            cabeza = nuevo;
            cola = nuevo;
            actual = nuevo;
        } else {
            nuevo.setSiguiente(cabeza);
            nuevo.setAnterior(cola);
            cabeza.setAnterior(nuevo);
            cola.setSiguiente(nuevo);
            cabeza = nuevo;
        }
        tamanio++;
    }

    public void agregarFinal(Cancion cancion) {
        if (estaVacia()) {
            agregarInicio(cancion);
            return;
        }
        Nodo nuevo = new Nodo(cancion);
        nuevo.setAnterior(cola);
        nuevo.setSiguiente(cabeza);
        cola.setSiguiente(nuevo);
        cabeza.setAnterior(nuevo);
        cola = nuevo;
        tamanio++;
    }

    public Cancion consultarActual() {
        if (estaVacia()) return null;
        return actual.getDato();
    }

    public Cancion avanzar() {
        if (estaVacia()) return null;
        actual = actual.getSiguiente();
        return actual.getDato();
    }

    public Cancion retroceder() {
        if (estaVacia()) return null;
        actual = actual.getAnterior();
        return actual.getDato();
    }

    public Cancion buscarPorId(int id) {
        Nodo nodo = buscarNodoPorId(id);
        return (nodo != null) ? nodo.getDato() : null;
    }

    public boolean seleccionarPorId(int id) {
        Nodo nodo = buscarNodoPorId(id);
        if (nodo != null) {
            actual = nodo;
            return true;
        }
        return false;
    }

    private Nodo buscarNodoPorId(int id) {
        if (estaVacia()) return null;
        Nodo temp = cabeza;
        for (int i = 0; i < tamanio; i++) {
            if (temp.getDato().getId() == id) {
                return temp;
            }
            temp = temp.getSiguiente();
        }
        return null;
    }

    public Cancion eliminarActual() {
        if (estaVacia()) return null;
        Cancion eliminada = actual.getDato();
        desenlazarNodo(actual);
        return eliminada;
    }

    public Cancion eliminarPorId(int id) {
        if (estaVacia()) return null;
        Nodo objetivo = buscarNodoPorId(id);
        if (objetivo == null) return null;

        Cancion eliminada = objetivo.getDato();
        desenlazarNodo(objetivo);
        return eliminada;
    }

    private void desenlazarNodo(Nodo nodo) {
        if (tamanio == 1) {
            cabeza = null;
            cola = null;
            actual = null;
        } else {
            Nodo ant = nodo.getAnterior();
            Nodo sig = nodo.getSiguiente();

            ant.setSiguiente(sig);
            sig.setAnterior(ant);

            if (nodo == cabeza) {
                cabeza = sig;
            }
            if (nodo == cola) {
                cola = ant;
            }
            if (nodo == actual) {
                actual = sig;
            }
        }

        nodo.setSiguiente(null);
        nodo.setAnterior(null);
        tamanio--;
    }

    public void mostrarAdelante() {
        if (estaVacia()) {
            System.out.println("-> La lista está vacía.");
            return;
        }
        Nodo temp = cabeza;
        for (int i = 0; i < tamanio; i++) {
            String indicador = (temp == actual) ? " [ACTUAL] -> " : "           -> ";
            System.out.println(indicador + temp.getDato());
            temp = temp.getSiguiente();
        }
    }

    public void mostrarAtras() {
        if (estaVacia()) {
            System.out.println("-> La lista está vacía.");
            return;
        }
        Nodo temp = cola;
        for (int i = 0; i < tamanio; i++) {
            String indicador = (temp == actual) ? " [ACTUAL] -> " : "           -> ";
            System.out.println(indicador + temp.getDato());
            temp = temp.getAnterior();
        }
    }

    public void simularReproduccion(int k) {
        if (estaVacia()) {
            System.out.println("-> No hay canciones para reproducir.");
            return;
        }
        if (k <= 0) {
            System.out.println("-> El número de canciones a reproducir debe ser mayor que cero.");
            return;
        }

        System.out.println("\n--- SIMULANDO REPRODUCCIÓN (" + k + " CANCIONES) ---");
        for (int i = 1; i <= k; i++) {
            System.out.println("Reproduciendo (" + i + "/" + k + "): " + actual.getDato());
            actual = actual.getSiguiente();
        }
        System.out.println("Siguiente canción en turno para reproducción: " + actual.getDato());
    }
}