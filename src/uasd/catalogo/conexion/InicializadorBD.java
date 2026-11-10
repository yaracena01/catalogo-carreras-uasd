package uasd.catalogo.conexion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class InicializadorBD {
    private static final String[] SCRIPTS = {"01_esquema.sql", "02_datos.sql"};

    public static void preparar() throws SQLException, IOException {
        crearBaseSiNoExiste();
        crearTablasSiNoExisten();
    }

    private static void crearBaseSiNoExiste() throws SQLException {
        String base = ConexionBD.getBase();
        Connection conexion = ConexionBD.conectarServidor();
        try {
            Statement sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery("SELECT DB_ID('" + base + "')");
            resultado.next();
            resultado.getInt(1);
            boolean existe = !resultado.wasNull();
            resultado.close();

            if (!existe) {
                sentencia.executeUpdate("CREATE DATABASE " + base);
            }
            sentencia.close();
        } finally {
            conexion.close();
        }
    }

    private static void crearTablasSiNoExisten() throws SQLException, IOException {
        Connection conexion = ConexionBD.conectar();
        try {
            Statement sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery("SELECT OBJECT_ID('dbo.carrera', 'U')");
            resultado.next();
            resultado.getInt(1);
            boolean existe = !resultado.wasNull();
            resultado.close();

            if (!existe) {
                for (String script : SCRIPTS) {
                    for (String bloque : leerBloques(script)) {
                        sentencia.execute(bloque);
                    }
                }
            }
            sentencia.close();
        } finally {
            conexion.close();
        }
    }

    private static List<String> leerBloques(String nombreArchivo) throws IOException {
        List<String> bloques = new ArrayList<>();
        StringBuilder actual = new StringBuilder();

        File archivo = new File("sql", nombreArchivo);
        if (!archivo.exists()) {
            throw new IOException("No se encontró " + archivo.getPath() + ". Ejecuta el programa desde la carpeta del proyecto.");
        }

        BufferedReader lector = new BufferedReader(new InputStreamReader(new FileInputStream(archivo), StandardCharsets.UTF_8));
        try {
            String linea;
            while ((linea = lector.readLine()) != null) {
                if (linea.trim().equalsIgnoreCase("GO")) {
                    if (!actual.toString().isBlank()) {
                        bloques.add(actual.toString());
                    }
                    actual = new StringBuilder();
                } else {
                    actual.append(linea).append("\n");
                }
            }
        } finally {
            lector.close();
        }
        if (!actual.toString().isBlank()) {
            bloques.add(actual.toString());
        }
        return bloques;
    }
}
