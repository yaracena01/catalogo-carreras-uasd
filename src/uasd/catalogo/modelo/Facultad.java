package uasd.catalogo.modelo;

public class Facultad extends EntidadCatalogo {

    public Facultad(int id, String nombre) {
        super(id, nombre);
    }

    @Override
    public String getTipo() {
        return "Facultad";
    }
}
