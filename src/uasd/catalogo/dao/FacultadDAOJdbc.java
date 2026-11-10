package uasd.catalogo.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import uasd.catalogo.conexion.ConexionBD;
import uasd.catalogo.modelo.Facultad;

public class FacultadDAOJdbc implements FacultadDAO {

    @Override
    public List<Facultad> listarTodas() throws SQLException {
        List<Facultad> facultades = new ArrayList<>();
        Connection conexion = ConexionBD.conectar();
        try {
            Statement sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery("SELECT id, nombre FROM facultad ORDER BY nombre");

            while (resultado.next()) {
                int id = resultado.getInt("id");
                String nombre = resultado.getString("nombre");
                facultades.add(new Facultad(id, nombre));
            }
            resultado.close();
            sentencia.close();
        } finally {
            conexion.close();
        }
        return facultades;
    }
}
