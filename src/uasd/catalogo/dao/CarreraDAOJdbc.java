package uasd.catalogo.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import uasd.catalogo.conexion.ConexionBD;
import uasd.catalogo.modelo.Carrera;
import uasd.catalogo.modelo.CarreraGrado;
import uasd.catalogo.modelo.CarreraTecnica;
import uasd.catalogo.modelo.NivelCarrera;

public class CarreraDAOJdbc implements CarreraDAO {

    @Override
    public List<Carrera> listarPorFacultad(int facultadId) throws SQLException {
        List<Carrera> carreras = new ArrayList<>();

        String sql = "SELECT c.id, c.nombre, c.nivel, c.proyectada, e.nombre AS escuela "
                + "FROM carrera c "
                + "JOIN escuela e ON e.id = c.escuela_id "
                + "WHERE e.facultad_id = " + facultadId + " "
                + "ORDER BY e.nombre, c.nivel DESC, c.nombre";

        Connection conexion = ConexionBD.conectar();
        try {
            Statement sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery(sql);

            while (resultado.next()) {
                int id = resultado.getInt("id");
                String nombre = resultado.getString("nombre");
                String escuela = resultado.getString("escuela");
                boolean proyectada = resultado.getBoolean("proyectada");
                String nivel = resultado.getString("nivel");

                Carrera carrera;
                if (nivel.equals(NivelCarrera.TECNICO)) {
                    carrera = new CarreraTecnica(id, nombre, escuela, proyectada);
                } else {
                    carrera = new CarreraGrado(id, nombre, escuela, proyectada);
                }
                carreras.add(carrera);
            }
            resultado.close();
            sentencia.close();
        } finally {
            conexion.close();
        }
        return carreras;
    }
}
