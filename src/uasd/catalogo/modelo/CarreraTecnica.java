package uasd.catalogo.modelo;

public class CarreraTecnica extends Carrera {

    public CarreraTecnica(int id, String nombre, String escuela, boolean proyectada) {
        super(id, nombre, escuela, proyectada);
    }

    @Override
    public String getNivel() {
        return NivelCarrera.TECNICO;
    }
}
