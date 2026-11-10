package uasd.catalogo.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    private static final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static final String SERVIDOR = "jdbc:sqlserver://localhost:1433;encrypt=true;trustServerCertificate=true";
    private static final String BASE = "CatalogoUASD";
    private static final String USUARIO = "sa";
    private static final String CLAVE = "Catalogo_UASD_2026";

    public static String getBase() {
        return BASE;
    }

    public static Connection conectarServidor() throws SQLException {
        cargarDriver();
        return DriverManager.getConnection(SERVIDOR, USUARIO, CLAVE);
    }

    public static Connection conectar() throws SQLException {
        cargarDriver();
        return DriverManager.getConnection(SERVIDOR + ";databaseName=" + BASE, USUARIO, CLAVE);
    }

    private static void cargarDriver() throws SQLException {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            throw new SQLException("No se encontró el driver de SQL Server", e);
        }
    }
}
