package org.softli.utl.mongonube;

public class Anime {
    int idAnime;
    String nombre;
    String capitulos;
    String temporadas;
    String anio;
    String genero;

    public Anime() {
    }

    public Anime(int idAnime, String nombre, String capitulos, String temporadas, String anio, String genero) {
        this.idAnime = idAnime;
        this.nombre = nombre;
        this.capitulos = capitulos;
        this.temporadas = temporadas;
        this.anio = anio;
        this.genero = genero;
    }

    public int getIdAnime() {
        return idAnime;
    }

    public void setIdAnime(int idAnime) {
        this.idAnime = idAnime;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCapitulos() {
        return capitulos;
    }

    public void setCapitulos(String capitulos) {
        this.capitulos = capitulos;
    }

    public String getTemporadas() {
        return temporadas;
    }

    public void setTemporadas(String temporadas) {
        this.temporadas = temporadas;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
}
