package uasd.catalogo.modelo;

public class CarreraGrado extends Carrera {

    public CarreraGrado(int id, String nombre, String escuela, boolean proyectada) {
        super(id, nombre, escuela, proyectada);
    }

    @Override
    public String getNivel() {
        return NivelCarrera.GRADO;
    }
}
