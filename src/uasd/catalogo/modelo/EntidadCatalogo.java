package uasd.catalogo.modelo;

public abstract class EntidadCatalogo {
    private int id;
    private String nombre;

    protected EntidadCatalogo(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public abstract String getTipo();

    public final String describir() {
        return getTipo() + ": " + nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }

    @Override
    public boolean equals(Object otro) {
        if (this == otro) {
            return true;
        }
        if (otro == null || getClass() != otro.getClass()) {
            return false;
        }
        EntidadCatalogo otraEntidad = (EntidadCatalogo) otro;
        return id == otraEntidad.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}
