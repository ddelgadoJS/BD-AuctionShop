/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Oracle;

import Connections.OracleConnection;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;

/**
 *
 * @author Kevin MM
 */
public class OracleConsultarAdminPujasPorParticipante extends javax.swing.JFrame {

    /**
     * Creates new form ConsultarAdminPujasPorParticipante
     */
    public OracleConsultarAdminPujasPorParticipante() {
        initComponents();
        
        // Agrega al combobox todos los alias de los usuarios.
        ArrayList<String> rowsList = new ArrayList<>(); // List to store the rows from the query.
        
        OracleConnection con_ = new OracleConnection();
        Connection con = con_.CrearConexion();
        rowsList = con_.EjecutarSP("SP_SELECT_USERS", con);
        
        for (String user: rowsList) {
            ComboBoxPGPUAliasAdmin.addItem(user);
            System.out.println(user);
        }
        
        // Close connection.
        try {
            con_.CerrarConexion(con);
        } catch (SQLException ex) {
            Logger.getLogger(OraclePantallaInicial.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BotonListarPGPUVolverAdmin = new javax.swing.JButton();
        LabelPujasGanadorasAdmin = new javax.swing.JLabel();
        BotonListarPGPULogOutAdmin = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        ListListarPGPUAdmin = new javax.swing.JList<>();
        LabelPGPUAliasAdmin = new javax.swing.JLabel();
        ComboBoxPGPUAliasAdmin = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Oracle");

        BotonListarPGPUVolverAdmin.setFont(new java.awt.Font("Tw Cen MT", 0, 24)); // NOI18N
        BotonListarPGPUVolverAdmin.setText("Volver");
        BotonListarPGPUVolverAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonListarPGPUVolverAdminActionPerformed(evt);
            }
        });

        LabelPujasGanadorasAdmin.setFont(new java.awt.Font("Tw Cen MT", 0, 36)); // NOI18N
        LabelPujasGanadorasAdmin.setText("Pujas Ganadoras");

        BotonListarPGPULogOutAdmin.setFont(new java.awt.Font("Tw Cen MT", 0, 24)); // NOI18N
        BotonListarPGPULogOutAdmin.setText("Log Out");
        BotonListarPGPULogOutAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonListarPGPULogOutAdminActionPerformed(evt);
            }
        });

        ListListarPGPUAdmin.setFont(new java.awt.Font("Tw Cen MT", 0, 20)); // NOI18N
        jScrollPane1.setViewportView(ListListarPGPUAdmin);

        LabelPGPUAliasAdmin.setFont(new java.awt.Font("Tw Cen MT", 0, 24)); // NOI18N
        LabelPGPUAliasAdmin.setText("Alias:");

        ComboBoxPGPUAliasAdmin.setFont(new java.awt.Font("Tw Cen MT", 0, 20)); // NOI18N
        ComboBoxPGPUAliasAdmin.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Usuario" }));
        ComboBoxPGPUAliasAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboBoxPGPUAliasAdminActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(LabelPGPUAliasAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23)
                        .addComponent(ComboBoxPGPUAliasAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BotonListarPGPUVolverAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(120, 120, 120))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(LabelPujasGanadorasAdmin)
                        .addGap(145, 145, 145)
                        .addComponent(BotonListarPGPULogOutAdmin)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 654, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BotonListarPGPULogOutAdmin)
                    .addComponent(LabelPujasGanadorasAdmin))
                .addGap(29, 29, 29)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BotonListarPGPUVolverAdmin)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(LabelPGPUAliasAdmin)
                        .addComponent(ComboBoxPGPUAliasAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(112, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BotonListarPGPUVolverAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonListarPGPUVolverAdminActionPerformed
        OracleConsultarUsuario frame = new OracleConsultarUsuario();
        frame.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_BotonListarPGPUVolverAdminActionPerformed

    private void BotonListarPGPULogOutAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonListarPGPULogOutAdminActionPerformed
        OraclePantallaInicial frame = new OraclePantallaInicial();
        frame.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_BotonListarPGPULogOutAdminActionPerformed

    private void ComboBoxPGPUAliasAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboBoxPGPUAliasAdminActionPerformed
        DefaultListModel model = new DefaultListModel();

        // Agrega al combobox todas las categorías
        ArrayList<String> rowsList = new ArrayList<>(); // List to store the rows from the query.

        String ALIASCOMPRADOR = ComboBoxPGPUAliasAdmin.getSelectedItem().toString();

        OracleConnection con_ = new OracleConnection();
        Connection con = con_.CrearConexion();
        rowsList = con_.EjecutarSP("PUJASGANADORAS (ALIASCOMPRADORv=>'" + ALIASCOMPRADOR + "')", con);

        model.removeAllElements();

        for (String puja: rowsList) {
            String[] parts = puja.split(", ");
            if (parts.length > 3) {
                model.addElement(parts[0] + ", Precio inicial: $" + parts[1] + ", Precio Final: $" + parts[2] + ", Comentario vendedor: " + parts[3]);
            } else {
                model.addElement(parts[0] + ", Precio inicial: $" + parts[1] + ", Precio Final: $" + parts[2]);
            }
        }

        // Close connection.
        try {
            con_.CerrarConexion(con);
        } catch (SQLException ex) {
            Logger.getLogger(OraclePantallaInicial.class.getName()).log(Level.SEVERE, null, ex);
        }

        ListListarPGPUAdmin.removeAll();
        ListListarPGPUAdmin.setModel(model);
    }//GEN-LAST:event_ComboBoxPGPUAliasAdminActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(OracleConsultarAdminPujasPorParticipante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OracleConsultarAdminPujasPorParticipante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OracleConsultarAdminPujasPorParticipante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OracleConsultarAdminPujasPorParticipante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new OracleConsultarAdminPujasPorParticipante().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotonListarPGPULogOutAdmin;
    private javax.swing.JButton BotonListarPGPUVolverAdmin;
    private javax.swing.JComboBox<String> ComboBoxPGPUAliasAdmin;
    private javax.swing.JLabel LabelPGPUAliasAdmin;
    private javax.swing.JLabel LabelPujasGanadorasAdmin;
    private javax.swing.JList<String> ListListarPGPUAdmin;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
