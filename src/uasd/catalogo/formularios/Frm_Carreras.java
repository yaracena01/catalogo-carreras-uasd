package uasd.catalogo.formularios;

import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import uasd.catalogo.dao.*;
import uasd.catalogo.modelo.*;

public class Frm_Carreras extends javax.swing.JFrame {
    private FacultadDAO facultadDAO = new FacultadDAOJdbc();
    private CarreraDAO carreraDAO = new CarreraDAOJdbc();
    private DefaultTableModel modeloTabla = new ModeloCarreras();

    public Frm_Carreras() {
        initComponents();
        configurarColumnas();
        cargarFacultades();
    }

    private class ModeloCarreras extends DefaultTableModel {
        public ModeloCarreras() {
            super(new String[]{"Escuela", "Carrera", "Nivel", "Estado"}, 0);
        }

        @Override
        public boolean isCellEditable(int fila, int columna) {
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlNorte = new javax.swing.JPanel();
        pnlTitulo = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        pnlDatos = new javax.swing.JPanel();
        lblFacultad = new javax.swing.JLabel();
        cmbFacultad = new javax.swing.JComboBox<>();
        btnConsultar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCarreras = new javax.swing.JTable();
        lblResultado = new javax.swing.JLabel();

        setTitle("Catálogo de Carreras - UASD");
        setPreferredSize(new java.awt.Dimension(900, 560));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        pnlNorte.setLayout(new java.awt.BorderLayout());

        pnlTitulo.setBackground(new java.awt.Color(31, 58, 122));
        pnlTitulo.setLayout(new java.awt.BorderLayout());

        lblTitulo.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(255, 255, 255));
        lblTitulo.setText("Catálogo de Carreras - Universidad Autónoma de Santo Domingo");
        lblTitulo.setBorder(javax.swing.BorderFactory.createEmptyBorder(14, 16, 14, 16));
        pnlTitulo.add(lblTitulo, java.awt.BorderLayout.CENTER);

        pnlNorte.add(pnlTitulo, java.awt.BorderLayout.NORTH);

        pnlDatos.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        lblFacultad.setText("Facultad:");
        pnlDatos.add(lblFacultad);

        cmbFacultad.setPreferredSize(new java.awt.Dimension(380, 28));
        pnlDatos.add(cmbFacultad);

        btnConsultar.setText("Consultar");
        btnConsultar.setPreferredSize(new java.awt.Dimension(120, 28));
        btnConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarActionPerformed(evt);
            }
        });
        pnlDatos.add(btnConsultar);

        pnlNorte.add(pnlDatos, java.awt.BorderLayout.SOUTH);

        getContentPane().add(pnlNorte, java.awt.BorderLayout.NORTH);

        tblCarreras.setAutoCreateRowSorter(true);
        tblCarreras.setModel(modeloTabla);
        tblCarreras.setRowHeight(24);
        jScrollPane1.setViewportView(tblCarreras);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        lblResultado.setText("Elige una facultad y pulsa Consultar.");
        lblResultado.setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 14, 8, 14));
        getContentPane().add(lblResultado, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        System.exit(0);
    }//GEN-LAST:event_formWindowClosing

    private void btnConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarActionPerformed
        Facultad elegida = (Facultad) cmbFacultad.getSelectedItem();

        if (elegida == null) {
            mostrarMensaje("Primero elige una facultad.");
            return;
        }

        try {
            List<Carrera> carreras = carreraDAO.listarPorFacultad(elegida.getId());

            modeloTabla.setRowCount(0);
            for (Carrera carrera : carreras) {
                String estado = "Vigente";
                if (carrera.isProyectada()) {
                    estado = "Proyectada";
                }
                modeloTabla.addRow(new Object[]{
                        carrera.getEscuela(),
                        carrera.getNombre(),
                        carrera.getNivel(),
                        estado});
            }

            if (carreras.isEmpty()) {
                lblResultado.setText("Sin resultados para " + elegida.getNombre() + ".");
            } else {
                lblResultado.setText(carreras.size() + " carreras en la Facultad de " + elegida.getNombre());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            mostrarMensaje("No se pudo consultar la base de datos.\nRevisa que SQL Server esté encendido.",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnConsultarActionPerformed

    private void configurarColumnas() {
        tblCarreras.getColumnModel().getColumn(0).setPreferredWidth(260);
        tblCarreras.getColumnModel().getColumn(1).setPreferredWidth(420);
        tblCarreras.getColumnModel().getColumn(2).setPreferredWidth(70);
        tblCarreras.getColumnModel().getColumn(3).setPreferredWidth(90);
    }

    private void cargarFacultades() {
        try {
            List<Facultad> facultades = facultadDAO.listarTodas();
            for (Facultad facultad : facultades) {
                cmbFacultad.addItem(facultad);
            }
            cmbFacultad.setSelectedIndex(-1);
        } catch (SQLException e) {
            e.printStackTrace();
            mostrarMensaje("No se pudo leer la base de datos.\nRevisa que SQL Server esté encendido.",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void mostrarMensaje(String texto) {
        mostrarMensaje(texto, JOptionPane.INFORMATION_MESSAGE);
    }

    private void mostrarMensaje(String texto, int tipo) {
        String titulo = "Aviso";
        if (tipo == JOptionPane.ERROR_MESSAGE) {
            titulo = "Error";
        }
        JOptionPane.showMessageDialog(this, texto, titulo, tipo);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConsultar;
    private javax.swing.JComboBox<Facultad> cmbFacultad;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblFacultad;
    private javax.swing.JLabel lblResultado;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel pnlDatos;
    private javax.swing.JPanel pnlNorte;
    private javax.swing.JPanel pnlTitulo;
    private javax.swing.JTable tblCarreras;
    // End of variables declaration//GEN-END:variables
}
