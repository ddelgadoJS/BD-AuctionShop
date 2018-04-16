/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Oracle;

import Connections.OracleConnection;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 *
 * @author Kevin MM
 */
public class OracleSubastarItem extends javax.swing.JFrame {
    public static String OraclePhotoPath;    
    
    /**
     * Creates new form SubastarItem
     */
    public OracleSubastarItem() {
        initComponents();
        
        // Agrega al combobox todas las categorías
        ArrayList<String> rowsList = new ArrayList<>(); // List to store the rows from the query.
        
        OracleConnection con_ = new OracleConnection();
        Connection con = con_.CrearConexion();
        rowsList = con_.EjecutarSP("SP_SELECT_CATEGORIAS", con);
        
        ComboBoxSubastarCategoria.removeAllItems();
        // Adds to the combobox all the categories.
        for (String user: rowsList) {
            ComboBoxSubastarCategoria.addItem(user);
        }
        
        rowsList = con_.EjecutarSP("SP_SELECT_SUBCATEGORIAS (IDCATEGORIAv=>" + 1 + ")", con);
        
        ComboBoxSubastarSubcategoria.removeAllItems();
        // Adds to the combobox all the subcategories from first category.
        for (String user: rowsList) {
            ComboBoxSubastarSubcategoria.addItem(user);
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

        LabelSubastar = new javax.swing.JLabel();
        BotonSubastarLogOut = new javax.swing.JButton();
        LabelSubastarPrecio = new javax.swing.JLabel();
        LabelSubastarDescripcion = new javax.swing.JLabel();
        LabelSubastarModoEntrega = new javax.swing.JLabel();
        LabelSubastarFechaLimite = new javax.swing.JLabel();
        LabelSubastarHoraLimite = new javax.swing.JLabel();
        EntrySubastaPrecio = new javax.swing.JTextField();
        EntrySubastaDescripcion = new javax.swing.JTextField();
        EntrySubastaModoDeEntrega = new javax.swing.JTextField();
        EntrySubastaFechaLimite = new javax.swing.JTextField();
        EntrySubastaHoraLimite = new javax.swing.JTextField();
        BotonAgregarImagen = new javax.swing.JButton();
        ComboBoxSubastarSubcategoria = new javax.swing.JComboBox<>();
        ComboBoxSubastarCategoria = new javax.swing.JComboBox<>();
        LabelSubastarCategoria = new javax.swing.JLabel();
        LabelSubastarSubcategoria = new javax.swing.JLabel();
        BotonSubastar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        BotonSubastarCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Oracle");

        LabelSubastar.setFont(new java.awt.Font("Tw Cen MT", 0, 36)); // NOI18N
        LabelSubastar.setText("Subastar Item");

        BotonSubastarLogOut.setFont(new java.awt.Font("Tw Cen MT", 0, 24)); // NOI18N
        BotonSubastarLogOut.setText("Log Out");
        BotonSubastarLogOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonSubastarLogOutActionPerformed(evt);
            }
        });

        LabelSubastarPrecio.setFont(new java.awt.Font("Tw Cen MT", 0, 24)); // NOI18N
        LabelSubastarPrecio.setText("Precio:");

        LabelSubastarDescripcion.setFont(new java.awt.Font("Tw Cen MT", 0, 24)); // NOI18N
        LabelSubastarDescripcion.setText("Descripción:");

        LabelSubastarModoEntrega.setFont(new java.awt.Font("Tw Cen MT", 0, 24)); // NOI18N
        LabelSubastarModoEntrega.setText("Modo de Entrega:");

        LabelSubastarFechaLimite.setFont(new java.awt.Font("Tw Cen MT", 0, 24)); // NOI18N
        LabelSubastarFechaLimite.setText("Fecha Límite:");

        LabelSubastarHoraLimite.setFont(new java.awt.Font("Tw Cen MT", 0, 24)); // NOI18N
        LabelSubastarHoraLimite.setText("Hora Límite:");

        EntrySubastaPrecio.setFont(new java.awt.Font("Tw Cen MT", 0, 20)); // NOI18N

        EntrySubastaDescripcion.setFont(new java.awt.Font("Tw Cen MT", 0, 20)); // NOI18N

        EntrySubastaModoDeEntrega.setFont(new java.awt.Font("Tw Cen MT", 0, 20)); // NOI18N

        EntrySubastaFechaLimite.setFont(new java.awt.Font("Tw Cen MT", 0, 20)); // NOI18N
        EntrySubastaFechaLimite.setToolTipText("Formato: YYYY/MM/DD");

        EntrySubastaHoraLimite.setFont(new java.awt.Font("Tw Cen MT", 0, 20)); // NOI18N
        EntrySubastaHoraLimite.setToolTipText("HH24:MI:SS");

        BotonAgregarImagen.setFont(new java.awt.Font("Tw Cen MT", 0, 24)); // NOI18N
        BotonAgregarImagen.setText("Agregar Imagen");
        BotonAgregarImagen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonAgregarImagenActionPerformed(evt);
            }
        });

        ComboBoxSubastarSubcategoria.setFont(new java.awt.Font("Tw Cen MT", 0, 20)); // NOI18N
        ComboBoxSubastarSubcategoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Subcategoria" }));

        ComboBoxSubastarCategoria.setFont(new java.awt.Font("Tw Cen MT", 0, 20)); // NOI18N
        ComboBoxSubastarCategoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Categoria" }));
        ComboBoxSubastarCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboBoxSubastarCategoriaActionPerformed(evt);
            }
        });

        LabelSubastarCategoria.setFont(new java.awt.Font("Tw Cen MT", 0, 24)); // NOI18N
        LabelSubastarCategoria.setText("Categoría:");

        LabelSubastarSubcategoria.setFont(new java.awt.Font("Tw Cen MT", 0, 24)); // NOI18N
        LabelSubastarSubcategoria.setText("Subcategoría");

        BotonSubastar.setFont(new java.awt.Font("Tw Cen MT", 0, 24)); // NOI18N
        BotonSubastar.setText("Subastar");
        BotonSubastar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonSubastarActionPerformed(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/default.jpg"))); // NOI18N

        BotonSubastarCancelar.setFont(new java.awt.Font("Tw Cen MT", 0, 24)); // NOI18N
        BotonSubastarCancelar.setText("Cancelar");
        BotonSubastarCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonSubastarCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(298, 298, 298)
                .addComponent(LabelSubastar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 232, Short.MAX_VALUE)
                .addComponent(BotonSubastarLogOut)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LabelSubastarHoraLimite)
                            .addComponent(LabelSubastarCategoria)
                            .addComponent(LabelSubastarSubcategoria))
                        .addGap(84, 84, 84)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(ComboBoxSubastarSubcategoria, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ComboBoxSubastarCategoria, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(EntrySubastaHoraLimite, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(BotonSubastar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addComponent(BotonSubastarCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LabelSubastarDescripcion)
                            .addComponent(LabelSubastarPrecio)
                            .addComponent(LabelSubastarModoEntrega)
                            .addComponent(LabelSubastarFechaLimite))
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(EntrySubastaModoDeEntrega, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
                            .addComponent(EntrySubastaFechaLimite)
                            .addComponent(EntrySubastaDescripcion)
                            .addComponent(EntrySubastaPrecio))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(BotonAgregarImagen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2))
                .addGap(31, 31, 31))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LabelSubastar)
                    .addComponent(BotonSubastarLogOut, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(LabelSubastarPrecio)
                                .addGap(23, 23, 23)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(LabelSubastarDescripcion)
                                    .addComponent(EntrySubastaDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(24, 24, 24)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(EntrySubastaModoDeEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(LabelSubastarModoEntrega)))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(EntrySubastaPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(EntrySubastaFechaLimite, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(LabelSubastarFechaLimite)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(EntrySubastaHoraLimite, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LabelSubastarHoraLimite))
                        .addGap(48, 48, 48)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(ComboBoxSubastarCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(ComboBoxSubastarSubcategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(LabelSubastarCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(LabelSubastarSubcategoria)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BotonSubastar)
                            .addComponent(BotonSubastarCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(46, 46, 46))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(BotonAgregarImagen)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BotonSubastarLogOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonSubastarLogOutActionPerformed
        OraclePantallaInicial frame = new OraclePantallaInicial();
        frame.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_BotonSubastarLogOutActionPerformed

    private void BotonSubastarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonSubastarActionPerformed
        ArrayList<String> rowsList = new ArrayList<>(); // List to store the rows from the query.
        
        String ALIASVENDEDORv = OraclePantallaInicial.aliasUsuario;
        String PRECIOINICIALv = EntrySubastaPrecio.getText();
        String DESCRIPCIONITEMv = EntrySubastaDescripcion.getText();
        String MODOENTREGAv = EntrySubastaModoDeEntrega.getText();
        String FECHAFINv = EntrySubastaFechaLimite.getText();
        String HORAFINv = EntrySubastaHoraLimite.getText();
        String IDCATEGORIAv = Integer.toString(ComboBoxSubastarCategoria.getSelectedIndex() + 1);
        String DESCRIPCIONSUBCATEGORIAv = ComboBoxSubastarSubcategoria.getSelectedItem().toString();
        String FOTOv = OraclePhotoPath; 
        
        OracleConnection con_ = new OracleConnection();
        Connection con = con_.CrearConexion();

        // Get subcategory index.
        String query = "SP_GET_IND_SUBCATEGORIA(IDCATEGORIAv=>" + IDCATEGORIAv + ",DESCRIPCIONSUBCATEGORIAv=>'" + DESCRIPCIONSUBCATEGORIAv + "')";
        rowsList = con_.EjecutarSP(query, con);
        String IDSUBCATEGORIAv = rowsList.get(0);
        
        query = "SP_SUBASTAR_ITEM(ALIASVENDEDORv=>'" + ALIASVENDEDORv + "',PRECIOINICIALv=>" + PRECIOINICIALv + 
            ",DESCRIPCIONITEMv=>'" + DESCRIPCIONITEMv + "',MODOENTREGAv=>'" + MODOENTREGAv + 
            "',FECHAFINv=>'" + FECHAFINv + "',HORAFINv=>'" + HORAFINv + "',IDSUBCATEGORIAv=>" + IDSUBCATEGORIAv + ",FOTOv=>'" + FOTOv + "')";
 
        rowsList = con_.EjecutarSP(query, con); // 1 : success.
        
        if (rowsList.get(0).equals("1")) {
            JOptionPane.showMessageDialog(this, "Subasta iniciada con éxito.");
        } else {
            JOptionPane.showMessageDialog(this, "Error: no se ha podido crear la subasta.");
        }
        
        // Close connection.
        try {
            con_.CerrarConexion(con);
        } catch (SQLException ex) {
            Logger.getLogger(OraclePantallaInicial.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        OraclePantallaParticipante frame = new OraclePantallaParticipante();
        frame.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_BotonSubastarActionPerformed

    private void BotonSubastarCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonSubastarCancelarActionPerformed
        OraclePantallaParticipante frame = new OraclePantallaParticipante();
        frame.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_BotonSubastarCancelarActionPerformed

    private void BotonAgregarImagenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonAgregarImagenActionPerformed
        // Opens the file chooser.
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();
        
        // Gets and sets the photo path.
        OraclePhotoPath = f.getAbsolutePath();
        ImageIcon itemPhoto = new ImageIcon(OraclePhotoPath);
        jLabel2.setIcon(itemPhoto);
    }//GEN-LAST:event_BotonAgregarImagenActionPerformed

    private void ComboBoxSubastarCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboBoxSubastarCategoriaActionPerformed
        ArrayList<String> rowsList = new ArrayList<>(); // List to store the rows from the query.
        
        // Gets the selected item index + 1, because it starts at 0 and at the DB starts at 1.
        String indCategoria = Integer.toString(ComboBoxSubastarCategoria.getSelectedIndex() + 1);
        
        // Opens connection to Oracle DB.
        OracleConnection con_ = new OracleConnection();
        Connection con = con_.CrearConexion();
        
        // Gets all the subcategories of the selected category.
        rowsList = con_.EjecutarSP("SP_SELECT_SUBCATEGORIAS (IDCATEGORIAv=>" + indCategoria + ")", con);
        
        ComboBoxSubastarSubcategoria.removeAllItems();
        
        // Adds each element of the rowsList into the combobox.
        for (String subcategoria: rowsList) {
            ComboBoxSubastarSubcategoria.addItem(subcategoria);
        }
        
        // Closes connection to Oracle DB.
        try {
            con_.CerrarConexion(con);
        } catch (SQLException ex) {
            Logger.getLogger(OraclePantallaInicial.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_ComboBoxSubastarCategoriaActionPerformed

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
            java.util.logging.Logger.getLogger(OracleSubastarItem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OracleSubastarItem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OracleSubastarItem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OracleSubastarItem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new OracleSubastarItem().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotonAgregarImagen;
    private javax.swing.JButton BotonSubastar;
    private javax.swing.JButton BotonSubastarCancelar;
    private javax.swing.JButton BotonSubastarLogOut;
    private javax.swing.JComboBox<String> ComboBoxSubastarCategoria;
    private javax.swing.JComboBox<String> ComboBoxSubastarSubcategoria;
    private javax.swing.JTextField EntrySubastaDescripcion;
    private javax.swing.JTextField EntrySubastaFechaLimite;
    private javax.swing.JTextField EntrySubastaHoraLimite;
    private javax.swing.JTextField EntrySubastaModoDeEntrega;
    private javax.swing.JTextField EntrySubastaPrecio;
    private javax.swing.JLabel LabelSubastar;
    private javax.swing.JLabel LabelSubastarCategoria;
    private javax.swing.JLabel LabelSubastarDescripcion;
    private javax.swing.JLabel LabelSubastarFechaLimite;
    private javax.swing.JLabel LabelSubastarHoraLimite;
    private javax.swing.JLabel LabelSubastarModoEntrega;
    private javax.swing.JLabel LabelSubastarPrecio;
    private javax.swing.JLabel LabelSubastarSubcategoria;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
