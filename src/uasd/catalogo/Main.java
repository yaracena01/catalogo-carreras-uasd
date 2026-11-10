package uasd.catalogo;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import uasd.catalogo.conexion.InicializadorBD;
import uasd.catalogo.formularios.Frm_Carreras;

public class Main {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println("Se usa el aspecto por defecto de Java");
        }

        try {
            InicializadorBD.preparar();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    "No se pudo preparar la base de datos.\n"
                    + "Revisa que SQL Server esté encendido y que los datos de\n"
                    + "conexión en ConexionBD.java sean correctos (ver README).",
                    "Error de base de datos", JOptionPane.ERROR_MESSAGE);
            return;
        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Frm_Carreras().setVisible(true);
            }
        });
    }
}
