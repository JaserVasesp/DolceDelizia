package Vista;

import Modelo.Carrito;
import Modelo.Producto;
import assets.Imagen;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
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
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import Controlador.Conexion;

public final class Menu extends javax.swing.JFrame {

    public static ArrayList<Carrito> carrito_compras = new ArrayList<>();
    public ArrayList<Carrito> carrito_compras_tmp = new ArrayList<>();
    ArrayList<Producto> productos = new ArrayList<>();
    JPanel container = new JPanel(new GridBagLayout());

    final ImageIcon error = new ImageIcon(new Imagen().error());

    public Menu(ArrayList<Carrito> carrito) {
        initComponents();

        container.setLayout(new GridBagLayout());

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double widthScreen = screenSize.getWidth();
        double heightScreen = screenSize.getHeight();

        this.setTitle("Lista de Productos");
        this.setLayout(new GridBagLayout());
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        JPanel jPanel = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 50, 0);

        JLabel lblTitulo = new JLabel();
        lblTitulo.setText("DOLCE DELIZIA");
        lblTitulo.setFont(new Font("Titulo", Font.BOLD, 24));

        jPanel.add(lblTitulo, gbc);

        gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.insets = new Insets(10, 0, 10, 0);

        container.add(jPanel, gbc);

        listaProductos();

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

    public void listaProductos() {
        try {
            Conexion conexion = new Conexion();

            productos = conexion.listaProductos();

            carrito_compras_tmp.clear();

            if (!productos.isEmpty()) {
                JPanel jPanelParent = new JPanel(new GridBagLayout());
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridwidth = GridBagConstraints.REMAINDER;
                gbc.insets = new Insets(10, 20, 20, 20);

                int i = 0;

                for (Producto producto : productos) {
                    i += 1;

                    int cantidad_producto = 0;

                    if (!carrito_compras.isEmpty()) {
                        for (Carrito c : carrito_compras) {
                            if (producto.getCodProducto() == c.getCodProducto()) {
                                cantidad_producto = c.getCantidad();
                            }
                        }
                    }

                    Carrito carrito = new Carrito(producto.getCodProducto(), cantidad_producto, producto.getDescripcion(), producto.getPrecio(), producto.getImagen());
                    carrito_compras_tmp.add(carrito);

                    JPanel jPanel = new JPanel(new GridBagLayout());
                    GridBagConstraints gbcTmp;

                    ImageIcon imagen = new ImageIcon(this.getClass().getResource("/assets/" + producto.getImagen()));
                    Image image = imagen.getImage();
                    Image newimg = image.getScaledInstance(150, 150, Image.SCALE_SMOOTH);

                    ImageIcon newImageIcon = new ImageIcon(newimg);

                    JLabel lblDescricpion = new JLabel();
                    lblDescricpion.setText(producto.getDescripcion());
                    lblDescricpion.setFont(new Font("Titulo", Font.BOLD, 16));

                    JLabel lblPrecio = new JLabel();
                    lblPrecio.setText("$" + producto.getPrecio());
                    lblPrecio.setFont(new Font("Titulo", Font.BOLD, 16));

                    JPanel jPanelBotones = new JPanel(new GridBagLayout());

                    gbcTmp = new GridBagConstraints();
                    gbcTmp.gridwidth = GridBagConstraints.HORIZONTAL;
                    gbcTmp.insets = new Insets(0, 0, 0, 0);

                    JTextField txtCantidad = new JTextField("" + cantidad_producto, 5);
                    txtCantidad.setHorizontalAlignment(SwingConstants.CENTER);
                    txtCantidad.setEditable(false);

                    JButton btnResta = new JButton();
                    btnResta.setText("-");
                    btnResta.addActionListener((java.awt.event.ActionEvent evt) -> {
                        int cantidad = Integer.parseInt(txtCantidad.getText().trim());
                        cantidad -= 1;

                        if (producto.getCantidad() == 0) {
                            
                        } else {
                            if (cantidad >= 0) {
                                txtCantidad.setText("" + cantidad);

                                for (Carrito c : carrito_compras_tmp) {
                                    if (producto.getCodProducto() == c.getCodProducto()) {
                                        c.setCantidad(cantidad);
                                    }
                                }
                            }
                        }
                    });

                    JButton btnSuma = new JButton();
                    btnSuma.setText("+");
                    btnSuma.addActionListener((java.awt.event.ActionEvent evt) -> {
                        int cantidad = Integer.parseInt(txtCantidad.getText().trim());
                        cantidad += 1;

                        if (producto.getCantidad() == 0) {
                            JOptionPane.showMessageDialog(this, "No hay stock en el almacen", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            if (cantidad <= producto.getCantidad()) {
                                txtCantidad.setText("" + cantidad);

                                for (Carrito c : carrito_compras_tmp) {
                                    if (producto.getCodProducto() == c.getCodProducto()) {
                                        c.setCantidad(cantidad);
                                    }
                                }
                            }
                        }

                    });

                    jPanelBotones.add(btnResta, gbcTmp);
                    jPanelBotones.add(txtCantidad, gbcTmp);
                    jPanelBotones.add(btnSuma, gbcTmp);

                    if (i < 3) {
                        gbcTmp = new GridBagConstraints();
                        gbcTmp.gridwidth = GridBagConstraints.HORIZONTAL;
                        gbcTmp.insets = new Insets(50, 0, 10, 0);
                    } else {
                        gbcTmp = new GridBagConstraints();
                        gbcTmp.gridwidth = GridBagConstraints.REMAINDER;
                        gbcTmp.insets = new Insets(50, 0, 10, 0);

                        i = 0;
                    }

                    jPanel.add(new JLabel(newImageIcon), gbc);
                    jPanel.add(lblDescricpion, gbc);
                    jPanel.add(lblPrecio, gbc);
                    jPanel.add(jPanelBotones, gbc);
                    jPanelParent.add(jPanel, gbcTmp);
                }

                carrito_compras = carrito_compras_tmp;

                gbc = new GridBagConstraints();
                gbc.gridwidth = GridBagConstraints.REMAINDER;
                gbc.insets = new Insets(10, 0, 10, 0);

                container.add(jPanelParent, gbc);

                gbc = new GridBagConstraints();
                gbc.gridwidth = GridBagConstraints.HORIZONTAL;
                gbc.insets = new Insets(20, 20, 20, 20);

                JPanel jPanel = new JPanel(new GridBagLayout());

                JButton btnCerrarSesion = new JButton("Cerrar sesión");
                btnCerrarSesion.setPreferredSize(new Dimension(150, 40));
                btnCerrarSesion.addActionListener((java.awt.event.ActionEvent evt) -> {
                    carrito_compras.clear();
                    setVisible(false);
                    new Login().setVisible(true);
                });

                JButton btnCancelarCompra = new JButton("Cancelar compra");
                btnCancelarCompra.setPreferredSize(new Dimension(150, 40));
                btnCancelarCompra.addActionListener((java.awt.event.ActionEvent evt) -> {
                    System.exit(0);
                });

                JButton btnProcederPago = new JButton("Proceder al pago");
                btnProcederPago.setPreferredSize(new Dimension(150, 40));
                btnProcederPago.addActionListener((java.awt.event.ActionEvent evt) -> {
                    boolean estado = false;

                    for (Carrito c : carrito_compras) {
                        if (c.getCantidad() > 0) {
                            estado = true;
                        }
                    }

                    if (estado) {
                        setVisible(false);
                        new ProcederPago().setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "El carrito de compras esta vacio", "Mensaje", JOptionPane.WARNING_MESSAGE, error);
                    }
                });

                jPanel.add(btnCerrarSesion, gbc);
                jPanel.add(btnCancelarCompra, gbc);
                jPanel.add(btnProcederPago, gbc);

                gbc = new GridBagConstraints();
                gbc.gridwidth = GridBagConstraints.REMAINDER;
                gbc.insets = new Insets(10, 0, 10, 0);

                container.add(jPanel, gbc);
            } else {
                JLabel lblMensaje = new JLabel();
                lblMensaje.setText("No hay productos.");
                lblMensaje.setFont(new Font("Titulo", Font.BOLD, 18));

                GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridwidth = GridBagConstraints.REMAINDER;
                gbc.insets = new Insets(10, 0, 50, 0);

                gbc = new GridBagConstraints();
                gbc.gridwidth = GridBagConstraints.REMAINDER;
                gbc.insets = new Insets(10, 0, 10, 0);

                container.add(lblMensaje, gbc);
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
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu(carrito_compras).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
