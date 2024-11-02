package Vista;

import Modelo.Carrito;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import static javax.swing.BorderFactory.createEmptyBorder;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

public class ProcederPago extends javax.swing.JFrame {

    ArrayList<Carrito> carrito_compras_tmp = new ArrayList<>();

    JPanel container = new JPanel(new GridBagLayout());

    public ProcederPago() {
        initComponents();

        container.setLayout(new GridBagLayout());

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double widthScreen = screenSize.getWidth();
        double heightScreen = screenSize.getHeight();

        this.setTitle("Proceder Pago");
        this.setLayout(new GridBagLayout());
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        carrito_compras_tmp = Menu.carrito_compras;

        JPanel jPanel = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 10, 0);

        JLabel lblTitulo = new JLabel();
        lblTitulo.setText("Detalle de Venta");
        lblTitulo.setFont(new Font("", Font.BOLD, 24));

        jPanel.add(lblTitulo, gbc);

        gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.insets = new Insets(10, 0, 10, 0);

        container.add(jPanel, gbc);

        JPanel jPanelDetalles = new JPanel(new GridBagLayout());

        gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.HORIZONTAL;

        JLabel label = new JLabel();

        label.setText("Artículo");
        label.setFont(new Font("", Font.BOLD, 16));
        label.setPreferredSize(new Dimension(250, 30));

        jPanelDetalles.add(label, gbc);

        label = new JLabel();
        label.setText("Cantidad");
        label.setFont(new Font("", Font.BOLD, 16));
        label.setPreferredSize(new Dimension(100, 30));

        jPanelDetalles.add(label, gbc);

        label = new JLabel();
        label.setText("Precio");
        label.setFont(new Font("", Font.BOLD, 16));
        label.setPreferredSize(new Dimension(100, 30));

        jPanelDetalles.add(label, gbc);

        label = new JLabel();
        label.setText("Precio + IGV");
        label.setFont(new Font("", Font.BOLD, 16));
        label.setPreferredSize(new Dimension(150, 30));

        jPanelDetalles.add(label, gbc);

        label = new JLabel();
        label.setText("Precio Total");
        label.setFont(new Font("", Font.BOLD, 16));
        label.setPreferredSize(new Dimension(100, 30));

        jPanelDetalles.add(label, gbc);

        gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.insets = new Insets(10, 0, 10, 0);

        container.add(jPanelDetalles, gbc);

        double total_compra = 0;

        NumberFormat myFormatter = new DecimalFormat("#.##");

        for (Carrito carrito : carrito_compras_tmp) {
            if (carrito.getCantidad() > 0) {
                int cantidad = carrito.getCantidad();
                double precio_igv = carrito.getPrecio() + (carrito.getPrecio() * 0.18);
                double total = cantidad * precio_igv;

                total_compra += total;

                gbc = new GridBagConstraints();
                gbc.gridwidth = GridBagConstraints.HORIZONTAL;

                jPanelDetalles = new JPanel(new GridBagLayout());

                label = new JLabel();
                label.setText(carrito.getDescripcion());
                label.setFont(new Font("", Font.BOLD, 16));
                label.setPreferredSize(new Dimension(250, 30));

                jPanelDetalles.add(label, gbc);

                label = new JLabel();
                label.setText("" + carrito.getCantidad());
                label.setFont(new Font("", Font.BOLD, 16));
                label.setPreferredSize(new Dimension(100, 30));

                jPanelDetalles.add(label, gbc);

                label = new JLabel();
                label.setText("$" + carrito.getPrecio());
                label.setFont(new Font("", Font.BOLD, 16));
                label.setPreferredSize(new Dimension(100, 30));

                jPanelDetalles.add(label, gbc);

                label = new JLabel();
                label.setText("$" + myFormatter.format(precio_igv));
                label.setFont(new Font("", Font.BOLD, 16));
                label.setPreferredSize(new Dimension(150, 30));

                jPanelDetalles.add(label, gbc);

                label = new JLabel();
                label.setText("$" + myFormatter.format(total));
                label.setFont(new Font("", Font.BOLD, 16));
                label.setPreferredSize(new Dimension(100, 30));

                jPanelDetalles.add(label, gbc);

                gbc = new GridBagConstraints();
                gbc.gridwidth = GridBagConstraints.REMAINDER;
                gbc.insets = new Insets(10, 0, 10, 0);

                container.add(jPanelDetalles, gbc);
            }
        }

        jPanel = new JPanel(new GridBagLayout());

        gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 10, 0);

        label = new JLabel();
        label.setText("$" + myFormatter.format(total_compra));
        label.setFont(new Font("", Font.BOLD, 16));
        label.setBorder(new EmptyBorder(0, 540, 0, 0));

        jPanel.add(label, gbc);

        gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.insets = new Insets(10, 0, 10, 0);

        container.add(jPanel, gbc);

        jPanel = new JPanel(new GridBagLayout());

        gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 445, 0, 20);

        JButton btnRegresar = new JButton("Regresar");
        btnRegresar.setPreferredSize(new Dimension(100, 40));
        btnRegresar.addActionListener((java.awt.event.ActionEvent evt) -> {
            setVisible(false);
            new Menu(carrito_compras_tmp).setVisible(true);
        });

        JButton btnPagar = new JButton("Pagar");
        btnPagar.setPreferredSize(new Dimension(100, 40));
        btnPagar.addActionListener((java.awt.event.ActionEvent evt) -> {
            setVisible(false);
            new RealizarVenta().setVisible(true);
        });

        jPanel.add(btnRegresar, gbc);
        jPanel.add(btnPagar);

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
            .addGap(0, 300, Short.MAX_VALUE)
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
            java.util.logging.Logger.getLogger(ProcederPago.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProcederPago.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProcederPago.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProcederPago.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ProcederPago().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
