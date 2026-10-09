/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package primer.parcial.estructura.de.datos;

/**
 *
 * @author kevin
 */
public class Nodo {
    private Cancion dato;
    private Nodo siguiente;
    private Nodo anterior;

    public Nodo(Cancion cancion) {
        this.dato = cancion;
        this.siguiente = null;
        this.anterior = null;
    }

    public Cancion getDato() { return dato; }
    public void setDato(Cancion dato) { this.dato = dato; }

    public Nodo getSiguiente() { return siguiente; }
    public void setSiguiente(Nodo siguiente) { this.siguiente = siguiente; }

    public Nodo getAnterior() { return anterior; }
    public void setAnterior(Nodo anterior) { this.anterior = anterior; }
}