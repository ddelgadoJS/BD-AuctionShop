/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Oracle;

import Connections.OracleConnection;
import Connections.PostgreSQLConnection;
import PostgreSQL.PostgreSQLPantallaInicial;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 *
 * @author Kevin MM
 */
public class OraclePantallaInicial extends javax.swing.JFrame {

    public static String aliasUsuario;
    
    public static String incrementominimo = "5000";
    public static String porcentajemejora = "0.05";
    
    // Set:
    // PantallaInicial.incrementominimo = "X";
    
    public OraclePantallaInicial() {
        initComponents();
        EntryPassword.setEchoChar('•');
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPasswordField1 = new javax.swing.JPasswordField();
        LabelTitulo = new javax.swing.JLabel();
        EntryAlias = new javax.swing.JTextField();
        EntryPassword = new javax.swing.JPasswordField();
        BotonLogin = new javax.swing.JButton();
        LabelAlias = new javax.swing.JLabel();
        LabelPassword = new javax.swing.JLabel();

        jPasswordField1.setText("jPasswordField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Oracle");

        LabelTitulo.setFont(new java.awt.Font("Tw Cen MT", 0, 48)); // NOI18N
        LabelTitulo.setText("Casa de Subastas el Potro Salvaje");

        EntryAlias.setFont(new java.awt.Font("Tw Cen MT", 0, 20)); // NOI18N
        EntryAlias.setToolTipText("");
        EntryAlias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EntryAliasActionPerformed(evt);
            }
        });

        EntryPassword.setFont(new java.awt.Font("Tw Cen MT", 0, 20)); // NOI18N
        EntryPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EntryPasswordActionPerformed(evt);
            }
        });

        BotonLogin.setFont(new java.awt.Font("Tw Cen MT", 0, 24)); // NOI18N
        BotonLogin.setText("Login");
        BotonLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonLoginActionPerformed(evt);
            }
        });

        LabelAlias.setFont(new java.awt.Font("Tw Cen MT", 0, 24)); // NOI18N
        LabelAlias.setText("Alias:");

        LabelPassword.setFont(new java.awt.Font("Tw Cen MT", 0, 24)); // NOI18N
        LabelPassword.setText("Password:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(LabelTitulo))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(160, 160, 160)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(BotonLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(LabelPassword)
                                    .addComponent(LabelAlias))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(EntryAlias, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(EntryPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(88, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(LabelTitulo)
                .addGap(103, 103, 103)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(EntryAlias, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LabelAlias))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelPassword)
                    .addComponent(EntryPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addComponent(BotonLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(248, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void EntryAliasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EntryAliasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EntryAliasActionPerformed

    private void EntryPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EntryPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EntryPasswordActionPerformed

    private void BotonLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonLoginActionPerformed
        ArrayList<String> rowsList = new ArrayList<>(); // List to store the rows from the query.
        
        String USR = EntryAlias.getText(); // User alias.
        String PSS = String.valueOf(EntryPassword.getPassword()); // User password.
        
        // Creating connection.
        OracleConnection con_ = new OracleConnection();
        Connection con = con_.CrearConexion();
        // The SP owner must be wrote before the SP name.
        rowsList = con_.EjecutarSP("SP_LOGIN(ALIASv=>'" + USR + "', PASSWORDv=>'" + PSS + "')", con);
        
        /* Splitting returned string "x, y, z"
            x - 1: alias found, 0: alias not found.
            y - 1: correct password, 0: incorrect password.
            z - User type: "PARTICIPANTE" or "ADMINISTRADOR".
        */
        String[] parts = rowsList.get(0).split(",");
        String usrAlias = parts[0];
        String usrPassword = parts[1];
        String usrType = parts[2];
        
        if (usrAlias.equals("1") && usrPassword.equals("1")) { // Correct alias and password.
            OraclePantallaInicial.aliasUsuario = USR; // Sets the user alias to be used on other screens.
            
            if (usrType.equals("ADMINISTRADOR") || usrType.equals("Administrador") || usrType.equals("administrador")) {
                OraclePantallaAdmin frame = new OraclePantallaAdmin();
                frame.setVisible(true);
                this.setVisible(false);
            } else if (usrType.equals("PARTICIPANTE") || usrType.equals("Participante") || usrType.equals("participante")) {
                OraclePantallaParticipante frame = new OraclePantallaParticipante();
                frame.setVisible(true);
                this.setVisible(false);
            }  
            
        } else if (usrAlias.equals("0")) {
            showMessageDialog(null, "Alias incorrecto");
        } else if (usrPassword.equals("0")) {
            showMessageDialog(null, "Contraseña incorrecta.");
        } else {
            showMessageDialog(null, "Unknown error.");
        }
        
        // Close connection.
        try {
            con_.CerrarConexion(con);
        } catch (SQLException ex) {
            Logger.getLogger(OraclePantallaInicial.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // Update Oracle system parameters
        OracleConnection Oraclecon_ = new OracleConnection();
        Connection Oraclecon_2 = Oraclecon_.CrearConexion();
        rowsList = Oraclecon_.EjecutarSP("SP_SELECT_PARAMETROS(IDv=>1)", Oraclecon_2);
        
        String[] parts2 = rowsList.get(0).split(", ");
        
        OraclePantallaInicial.incrementominimo = parts2[0];
        OraclePantallaInicial.porcentajemejora = (Float.valueOf(parts2[1])).toString();       
        
        // Close connection.
        try {
            Oraclecon_.CerrarConexion(con);
        } catch (SQLException ex) {
            Logger.getLogger(OraclePantallaInicial.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_BotonLoginActionPerformed

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
            java.util.logging.Logger.getLogger(OraclePantallaInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OraclePantallaInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OraclePantallaInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OraclePantallaInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new OraclePantallaInicial().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotonLogin;
    private javax.swing.JTextField EntryAlias;
    private javax.swing.JPasswordField EntryPassword;
    private javax.swing.JLabel LabelAlias;
    private javax.swing.JLabel LabelPassword;
    private javax.swing.JLabel LabelTitulo;
    private javax.swing.JPasswordField jPasswordField1;
    // End of variables declaration//GEN-END:variables
}
