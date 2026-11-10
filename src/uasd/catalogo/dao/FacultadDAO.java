package uasd.catalogo.dao;

import java.sql.SQLException;
import java.util.List;
import uasd.catalogo.modelo.Facultad;

public interface FacultadDAO {
    List<Facultad> listarTodas() throws SQLException;
}
