/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PostgreSQL;

import Oracle.*;
import Connections.OracleConnection;
import Connections.PostgreSQLConnection;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 *
 * @author Kevin MM
 */
public class PostgreSQLSubastasRealizadas extends javax.swing.JFrame {

    /**
     * Creates new form SubastasGanadoras
     */
    public PostgreSQLSubastasRealizadas() {
        initComponents();
        
        DefaultListModel model = new DefaultListModel();
        //model.addElement("hola");
        
        // Agrega al combobox todos los alias de los usuarios.
        ArrayList<String> rowsList = new ArrayList<>(); // List to store the rows from the query.
        
        String ALIASv = PostgreSQLPantallaInicial.aliasUsuario;
        
        ALIASv = "jrhyme1";
        
        PostgreSQLConnection con_ = new PostgreSQLConnection();
        String columnName = "ft_get_subastas_realizadas_usuario"; // Name of the returned column.
        
        // Obtener subastas de usuario.
        String query = "SELECT public.FT_GET_SUBASTAS_REALIZADAS_USUARIO(ALIASv:='" + ALIASv + "'::TEXT)";
        rowsList = con_.FunctionReturningString(query, columnName);
        
        for (String subasta: rowsList) {
            model.addElement(subasta);
        }
        
        ListSubastasFinalizadas.setModel(model);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        ListSubastasFinalizadas = new javax.swing.JList<>();
        BotonSubastasFinalizadasAgregarComentario = new javax.swing.JButton();
        BotonSubastasFinalizadasVolver = new javax.swing.JButton();
        LabelSubastasFinalizadasComentario = new javax.swing.JLabel();
        LabelSubastarSubcategoria = new javax.swing.JLabel();
        LabelSubastasFinalizadas = new javax.swing.JLabel();
        BotonSubastasFinalizadasLogOut = new javax.swing.JButton();
        EntrySubastasFinalizadasComentario = new javax.swing.JTextField();
        BotonSubastasFinalizadasMarcarCompletada = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        ListSubastasFinalizadas.setFont(new java.awt.Font("Tw Cen MT", 0, 20)); // NOI18N
        jScrollPane1.setViewportView(ListSubastasFinalizadas);

        BotonSubastasFinalizadasAgregarComentario.setFont(new java.awt.Font("Tw Cen MT", 0, 24)); // NOI18N
        BotonSubastasFinalizadasAgregarComentario.setText("Agregar Comentario");
        BotonSubastasFinalizadasAgregarComentario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonSubastasFinalizadasAgregarComentarioActionPerformed(evt);
            }
        });

        BotonSubastasFinalizadasVolver.setFont(new java.awt.Font("Tw Cen MT", 0, 24)); // NOI18N
        BotonSubastasFinalizadasVolver.setText("Volver");
        BotonSubastasFinalizadasVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonSubastasFinalizadasVolverActionPerformed(evt);
            }
        });

        LabelSubastasFinalizadasComentario.setFont(new java.awt.Font("Tw Cen MT", 0, 24)); // NOI18N
        LabelSubastasFinalizadasComentario.setText("Comentario:");

        LabelSubastarSubcategoria.setFont(new java.awt.Font("Tw Cen MT", 0, 24)); // NOI18N

        LabelSubastasFinalizadas.setFont(new java.awt.Font("Tw Cen MT", 0, 36)); // NOI18N
        LabelSubastasFinalizadas.setText("Subastas Realizadas");

        BotonSubastasFinalizadasLogOut.setFont(new java.awt.Font("Tw Cen MT", 0, 24)); // NOI18N
        BotonSubastasFinalizadasLogOut.setText("Log Out");
        BotonSubastasFinalizadasLogOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonSubastasFinalizadasLogOutActionPerformed(evt);
            }
        });

        EntrySubastasFinalizadasComentario.setFont(new java.awt.Font("Tw Cen MT", 0, 20)); // NOI18N

        BotonSubastasFinalizadasMarcarCompletada.setFont(new java.awt.Font("Tw Cen MT", 0, 24)); // NOI18N
        BotonSubastasFinalizadasMarcarCompletada.setText("Marcar Completada");
        BotonSubastasFinalizadasMarcarCompletada.setToolTipText("Marcar la compra como conretada y completa");
        BotonSubastasFinalizadasMarcarCompletada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonSubastasFinalizadasMarcarCompletadaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(LabelSubastasFinalizadas)
                .addGap(134, 134, 134)
                .addComponent(BotonSubastasFinalizadasLogOut)
                .addGap(20, 20, 20))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(BotonSubastasFinalizadasVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BotonSubastasFinalizadasMarcarCompletada, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(LabelSubastarSubcategoria)
                            .addGap(411, 411, 411)
                            .addComponent(BotonSubastasFinalizadasAgregarComentario))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(96, 96, 96)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(LabelSubastasFinalizadasComentario)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 560, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(EntrySubastasFinalizadasComentario, javax.swing.GroupLayout.PREFERRED_SIZE, 433, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(144, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelSubastasFinalizadas)
                    .addComponent(BotonSubastasFinalizadasLogOut))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelSubastasFinalizadasComentario)
                    .addComponent(EntrySubastasFinalizadasComentario, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(LabelSubastarSubcategoria))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(BotonSubastasFinalizadasAgregarComentario, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(BotonSubastasFinalizadasMarcarCompletada, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(BotonSubastasFinalizadasVolver)
                .addContainerGap(49, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BotonSubastasFinalizadasVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonSubastasFinalizadasVolverActionPerformed
        OraclePantallaParticipante frame = new OraclePantallaParticipante();
        frame.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_BotonSubastasFinalizadasVolverActionPerformed

    private void BotonSubastasFinalizadasLogOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonSubastasFinalizadasLogOutActionPerformed
        OraclePantallaInicial frame = new OraclePantallaInicial();
        frame.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_BotonSubastasFinalizadasLogOutActionPerformed

    private void BotonSubastasFinalizadasAgregarComentarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonSubastasFinalizadasAgregarComentarioActionPerformed
        ArrayList<String> rowsList = new ArrayList<>(); // List to store the rows from the query.
        
        String[] parts = ListSubastasFinalizadas.getSelectedValue().split(",");
        
        String IDSUBASTAv = parts[0]; // Gets index of auction.
        String COMENTARIOVENDEDORv = EntrySubastasFinalizadasComentario.getText(); // User alias.
        
        // Creating connection.
        PostgreSQLConnection con_ = new PostgreSQLConnection();
        String columnName = "ft_agregar_comentario_estado_vendedor"; // Name of the returned column.

        String query = "SELECT public.FT_AGREGAR_COMENTARIO_ESTADO_VENDEDOR(IDSUBASTAv:=" + IDSUBASTAv + "::INTEGER,COMENTARIOVENDEDORv:='" + COMENTARIOVENDEDORv + "'::TEXT)";
        rowsList = con_.FunctionReturningString(query, columnName);
        
        if (rowsList.get(0).toString().equals("1")) {
            JOptionPane.showMessageDialog(this, "Comentario Agregado");
        } else {
            JOptionPane.showMessageDialog(this, "Error");
        }
    }//GEN-LAST:event_BotonSubastasFinalizadasAgregarComentarioActionPerformed

    private void BotonSubastasFinalizadasMarcarCompletadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonSubastasFinalizadasMarcarCompletadaActionPerformed
        ArrayList<String> rowsList = new ArrayList<>(); // List to store the rows from the query.
        
        String[] parts = ListSubastasFinalizadas.getSelectedValue().split(",");
        
        String IDSUBASTAv = parts[0]; // Gets index of auction.
        
        // Creating connection.
        PostgreSQLConnection con_ = new PostgreSQLConnection();
        String columnName = "ft_agregar_comentario_estado_vendedor"; // Name of the returned column.

        String query = "SELECT public.FT_AGREGAR_COMENTARIO_ESTADO_VENDEDOR(IDSUBASTAv:=" + IDSUBASTAv + "::INTEGER,ESTADOSUBASTAv:=3::SMALLINT)";
        rowsList = con_.FunctionReturningString(query, columnName);
        
        if (rowsList.get(0).toString().equals("1")) {
            JOptionPane.showMessageDialog(this, "Marcada como completada.");
        } else {
            JOptionPane.showMessageDialog(this, "Error");
        }
    }//GEN-LAST:event_BotonSubastasFinalizadasMarcarCompletadaActionPerformed

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
            java.util.logging.Logger.getLogger(PostgreSQLSubastasRealizadas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PostgreSQLSubastasRealizadas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PostgreSQLSubastasRealizadas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PostgreSQLSubastasRealizadas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PostgreSQLSubastasRealizadas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotonSubastasFinalizadasAgregarComentario;
    private javax.swing.JButton BotonSubastasFinalizadasLogOut;
    private javax.swing.JButton BotonSubastasFinalizadasMarcarCompletada;
    private javax.swing.JButton BotonSubastasFinalizadasVolver;
    private javax.swing.JTextField EntrySubastasFinalizadasComentario;
    private javax.swing.JLabel LabelSubastarSubcategoria;
    private javax.swing.JLabel LabelSubastasFinalizadas;
    private javax.swing.JLabel LabelSubastasFinalizadasComentario;
    private javax.swing.JList<String> ListSubastasFinalizadas;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
