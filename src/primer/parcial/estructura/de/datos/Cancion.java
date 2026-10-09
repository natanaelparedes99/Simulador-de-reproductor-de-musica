/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package primer.parcial.estructura.de.datos;

/**
 *
 * @author kevin
 */
public class Cancion {
    private static int contadorId = 1;

    private final int id;
    private String titulo;
    private String artista;
    private int duracionSegundos;
    private String rutaArchivo; // Opcional, para enlazar con audio real

    public Cancion(String titulo, String artista, int duracionSegundos, String rutaArchivo) {
        if (duracionSegundos <= 0) {
            throw new IllegalArgumentException("La duración debe ser estrictamente mayor a 0 segundos.");
        }
        this.id = contadorId++;
        this.titulo = (titulo == null || titulo.trim().isEmpty()) ? "Sin título" : titulo.trim();
        this.artista = (artista == null || artista.trim().isEmpty()) ? "Artista desconocido" : artista.trim();
        this.duracionSegundos = duracionSegundos;
        this.rutaArchivo = (rutaArchivo == null) ? "" : rutaArchivo;
    }

    public Cancion(String titulo, String artista, int duracionSegundos) {
        this(titulo, artista, duracionSegundos, "");
    }

    public int getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getArtista() { return artista; }
    public int getDuracionSegundos() { return duracionSegundos; }
    public String getRutaArchivo() { return rutaArchivo; }

    public String getDuracionFormateada() {
        int min = duracionSegundos / 60;
        int seg = duracionSegundos % 60;
        return String.format("%02d:%02d", min, seg);
    }

    @Override
    public String toString() {
        return String.format("[ID: %d] \"%s\" - %s (%s)", id, titulo, artista, getDuracionFormateada());
    }
}