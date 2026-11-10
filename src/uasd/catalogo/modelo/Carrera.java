package uasd.catalogo.modelo;

public abstract class Carrera extends EntidadCatalogo {
    private String escuela;
    private boolean proyectada;

    protected Carrera(int id, String nombre, String escuela, boolean proyectada) {
        super(id, nombre);
        this.escuela = escuela;
        this.proyectada = proyectada;
    }

    public String getEscuela() {
        return escuela;
    }

    public void setEscuela(String escuela) {
        this.escuela = escuela;
    }

    public boolean isProyectada() {
        return proyectada;
    }

    public void setProyectada(boolean proyectada) {
        this.proyectada = proyectada;
    }

    public abstract String getNivel();

    @Override
    public String getTipo() {
        return "Carrera";
    }

    @Override
    public String toString() {
        if (proyectada) {
            return getNombre() + " (proyectada)";
        }
        return getNombre();
    }
}
