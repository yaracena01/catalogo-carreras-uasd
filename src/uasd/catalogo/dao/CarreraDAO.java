package uasd.catalogo.dao;

import java.sql.SQLException;
import java.util.List;
import uasd.catalogo.modelo.Carrera;

public interface CarreraDAO {
    List<Carrera> listarPorFacultad(int facultadId) throws SQLException;
}
