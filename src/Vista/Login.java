package Vista;

import Modelo.Cajera;
import Modelo.Carrito;
import static Vista.Login.cajera;
import assets.Imagen;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.BorderFactory.createEmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import Controlador.Conexion;

public class Login extends javax.swing.JFrame {

    public static ArrayList<Cajera> cajera;
    
    final ImageIcon success = new ImageIcon(new Imagen().success());
    final ImageIcon error = new ImageIcon(new Imagen().error());
    
    JPanel container = new JPanel(new GridBagLayout());
    
    public Login() {
        initComponents();
        
        container.setLayout(new GridBagLayout());
        
        this.setTitle("Dolce Delizia");
        this.setLayout(new GridBagLayout());
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double widthScreen = screenSize.getWidth();
        double heightScreen = screenSize.getHeight();

        JPanel jPanel = new JPanel(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 10, 15);
        
        JLabel label = new JLabel("DOLCE DELIZIA");
        label.setFont(new Font("Titulo", Font.BOLD, 24));
        
        jPanel.add(label, gbc);
        
        gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.insets = new Insets(10, 0, 50, 0);

        container.add(jPanel, gbc);
        
        jPanel = new JPanel();
        
        jPanel.setBackground(Color.GRAY);
        jPanel.setPreferredSize(new Dimension((int) widthScreen, 50));
        
        gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.insets = new Insets(10, 0, 10, 0);

        container.add(jPanel, gbc);
        
        jPanel = new JPanel(new GridBagLayout());
        
        gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 10, 15);

        label = new JLabel("USUARIO");
        label.setPreferredSize(new Dimension(100, 35));

        jPanel.add(label, gbc);
        
        JTextField txtUsuario = new JTextField();
        txtUsuario.setPreferredSize(new Dimension(200, 35));
        
        jPanel.add(txtUsuario, gbc);
        
        gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.insets = new Insets(10, 0, 10, 0);

        container.add(jPanel, gbc);
        
        jPanel = new JPanel(new GridBagLayout());
        
        gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 10, 15);
        
        label = new JLabel("CONTRASEÑA");
        label.setPreferredSize(new Dimension(100, 35));
        
        jPanel.add(label, gbc);
        
        JPasswordField txtContrasenia = new JPasswordField();
        txtContrasenia.setPreferredSize(new Dimension(200, 35));     
        
        jPanel.add(txtContrasenia, gbc);
        
        gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.insets = new Insets(10, 0, 10, 0);

        container.add(jPanel, gbc);
        
        jPanel = new JPanel(new GridBagLayout());
        
        gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 10, 15);
        
        JButton btnIngresar = new JButton("Ingresar");
        btnIngresar.setPreferredSize(new Dimension(150, 40));
        btnIngresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/login.png")));
        btnIngresar.addActionListener((java.awt.event.ActionEvent evt) -> {
            login(txtUsuario, txtContrasenia);
        });
        
        jPanel.add(btnIngresar, gbc);
        
        gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.insets = new Insets(10, 0, 10, 0);

        container.add(jPanel, gbc);
        
        JScrollPane scrollPane = new JScrollPane(container);
        scrollPane.setBorder(createEmptyBorder());
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(0, 0, (int) widthScreen, (int) heightScreen - 70);
        JPanel contentPane = new JPanel(null);
        contentPane.setPreferredSize(new Dimension(500, 400));
        contentPane.add(scrollPane);
        this.setContentPane(contentPane);
    }
    
    public void login(JTextField txtUsuario, JPasswordField txtContrasenia) {
        try {
            Conexion conexion = new Conexion();

            cajera = conexion.login(txtUsuario.getText().trim(), txtContrasenia.getText().trim());

            if(cajera.size() == 1){
                JOptionPane. showMessageDialog(null, "Datos correctos. Bienvenido.", "Mensaje", JOptionPane.INFORMATION_MESSAGE, success);
                setVisible(false);
                
                ArrayList<Carrito> carrito_compras = new ArrayList<>();
                new Menu(carrito_compras).setVisible(true);
            }else{
                JOptionPane. showMessageDialog(null, "Datos incorrectos.", "Mensaje", JOptionPane.WARNING_MESSAGE, error);
            }
        } catch (Exception ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 277, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
