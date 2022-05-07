/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collegemanagementsystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author Paul-Vance Pierre Nixon Jr.
 */
public class forgotUsername extends javax.swing.JFrame {

    /**
     * Creates new form forgotUsername
     */
    public forgotUsername() {
        initComponents();
        conn = databaseConnection.Connect();
    }

    // Global variables
    Connection conn; // Connection to the MySQL database
    PreparedStatement pst; // To write queries to the database 
    ResultSet rs; // To access tables derived from queries
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        user_type_label = new javax.swing.JLabel();
        user_type_combo_box = new javax.swing.JComboBox<>();
        email_label = new javax.swing.JLabel();
        enterButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();
        clearButton = new javax.swing.JButton();
        email_text_field = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        user_type_label.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        user_type_label.setText("Select user type:");

        user_type_combo_box.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "admin", "staff", "student" }));
        user_type_combo_box.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        email_label.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        email_label.setText("Email:");

        enterButton.setText("Enter");
        enterButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        enterButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                enterButtonMouseClicked(evt);
            }
        });

        backButton.setText("Back");
        backButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        backButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backButtonMouseClicked(evt);
            }
        });

        clearButton.setText("Clear");
        clearButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        clearButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clearButtonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(user_type_label)
                            .addComponent(email_label))
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(user_type_combo_box, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(email_text_field)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(enterButton, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(clearButton)))
                .addContainerGap(97, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(91, 91, 91)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(user_type_label)
                    .addComponent(user_type_combo_box, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(email_label)
                    .addComponent(email_text_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(enterButton)
                    .addComponent(backButton)
                    .addComponent(clearButton))
                .addContainerGap(103, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void enterButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_enterButtonMouseClicked
        /*
        Check if the user filled the text field. If not, display a pop-up
        telling them it's empty & to fill it. If so, find & display the
        user's username.
        */
        if (!(Pattern.matches("^[a-zA-Z0-9]+[@]{1}+[a-zA-Z0-9]+[.]{1}+[a-zA-Z0-9]+$", email_text_field.getText())) || email_text_field.getText().length() > 30)
        {
            JOptionPane.showMessageDialog(this, "The 'Email' text field is either "
                                         + "invalid or exceeds 30 characters.");
        }
        else
        {
            try
            {
                /*
                Verify if the email is in the database. If not, display a pop-up
                telling the user there's an error. If so, display a pop-up showing
                the user's username.
                */
                pst = conn.prepareStatement("select username from "
                                         + user_type_combo_box.getSelectedItem().toString()
                                         + " where email = ?");
                pst.setString(1, email_text_field.getText());
                rs = pst.executeQuery();

                if (rs.next())
                    JOptionPane.showMessageDialog(this, "Your username is " + rs.getString("username"));
                else
                    JOptionPane.showMessageDialog(this, "The email doesn't exist in the database.");
            }
            catch (SQLException ex)
            {
                Logger.getLogger(forgotUsername.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_enterButtonMouseClicked

    private void backButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backButtonMouseClicked
        // Display Login.java.
        this.hide();
        new Login().setVisible(true);
    }//GEN-LAST:event_backButtonMouseClicked

    private void clearButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clearButtonMouseClicked
        // Clear the text field.
        email_text_field.setText("");
    }//GEN-LAST:event_clearButtonMouseClicked

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
            java.util.logging.Logger.getLogger(forgotUsername.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(forgotUsername.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(forgotUsername.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(forgotUsername.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new forgotUsername().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JButton clearButton;
    private javax.swing.JLabel email_label;
    private javax.swing.JTextField email_text_field;
    private javax.swing.JButton enterButton;
    private javax.swing.JComboBox<String> user_type_combo_box;
    private javax.swing.JLabel user_type_label;
    // End of variables declaration//GEN-END:variables
}
