package Vista;

import Excel.Excel;
import Modelo.Carrito;
import PDF.PDF;
import assets.Imagen;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.BorderFactory.createEmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JFormattedTextField.AbstractFormatterFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.text.InternationalFormatter;
import Controlador.Conexion;

public class RealizarVenta extends javax.swing.JFrame {

    ArrayList<Carrito> carrito_compras_tmp = new ArrayList<>();

    final ImageIcon success = new ImageIcon(new Imagen().success());
    final ImageIcon error = new ImageIcon(new Imagen().error());

    JPanel container = new JPanel(new GridBagLayout());

    double total_compra = 0;
    double recibir = 0;
    double cambio = 0;

    public RealizarVenta() {
        initComponents();

        container.setLayout(new GridBagLayout());

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double widthScreen = screenSize.getWidth();
        double heightScreen = screenSize.getHeight();

        this.setTitle("Realizar Venta");
        this.setLayout(new GridBagLayout());
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        carrito_compras_tmp = Menu.carrito_compras;

        total_compra = 0;

        NumberFormat myFormatter = new DecimalFormat("#.##");

        for (Carrito carrito : carrito_compras_tmp) {
            if (carrito.getCantidad() > 0) {
                int cantidad = carrito.getCantidad();
                double precio_igv = carrito.getPrecio() + (carrito.getPrecio() * 0.18);
                double total = cantidad * precio_igv;

                total_compra += total;
            }
        }

        JPanel jPanel = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 60, 0, 0);

        JLabel label = new JLabel();

        label.setText("Total a Pagar");
        label.setFont(new Font("", Font.BOLD, 16));
        label.setPreferredSize(new Dimension(140, 30));

        jPanel.add(label, gbc);

        gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 0, 0);

        label = new JLabel();
        label.setText("$" + myFormatter.format(total_compra));
        label.setFont(new Font("", Font.BOLD, 16));
        label.setPreferredSize(new Dimension(100, 30));

        jPanel.add(label, gbc);

        gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.insets = new Insets(10, 0, 0, 0);

        container.add(jPanel, gbc);

        jPanel = new JPanel(new GridBagLayout());

        gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 100, 0, 0);

        label = new JLabel();

        label.setText("Recibir");
        label.setFont(new Font("", Font.BOLD, 16));
        label.setPreferredSize(new Dimension(140, 30));

        jPanel.add(label, gbc);

        gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 0, 3);

        label = new JLabel();

        label.setText("$");
        label.setFont(new Font("", Font.BOLD, 16));
        label.setPreferredSize(new Dimension(10, 30));

        jPanel.add(label, gbc);

        JFormattedTextField txtRecibir = new JFormattedTextField();
        txtRecibir.setPreferredSize(new Dimension(120, 30));
        txtRecibir.setFormatterFactory(new AbstractFormatterFactory() {
            @Override
            public AbstractFormatter getFormatter(JFormattedTextField tf) {
                NumberFormat format = DecimalFormat.getInstance();
                format.setMinimumFractionDigits(2);
                format.setMaximumFractionDigits(2);
                format.setRoundingMode(RoundingMode.HALF_UP);
                InternationalFormatter formatter = new InternationalFormatter(format);
                formatter.setAllowsInvalid(false);
                formatter.setMinimum(0.0);
                return formatter;
            }
        });

        jPanel.add(txtRecibir, gbc);

        gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.insets = new Insets(10, 0, 0, 0);

        container.add(jPanel, gbc);

        jPanel = new JPanel(new GridBagLayout());

        gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 70, 0, 0);

        label = new JLabel();

        label.setText("");
        label.setFont(new Font("", Font.BOLD, 16));
        label.setPreferredSize(new Dimension(70, 30));

        jPanel.add(label, gbc);

        JButton btnCalcular = new JButton("Calcular");
        btnCalcular.setPreferredSize(new Dimension(100, 40));

        jPanel.add(btnCalcular, gbc);

        gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.insets = new Insets(10, 0, 0, 0);

        container.add(jPanel, gbc);

        jPanel = new JPanel(new GridBagLayout());

        gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 115, 0, 0);

        label = new JLabel();

        label.setText("Cambio");
        label.setFont(new Font("", Font.BOLD, 16));
        label.setPreferredSize(new Dimension(140, 30));

        jPanel.add(label, gbc);

        gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 0, 0);

        JLabel labelCambio = new JLabel();

        labelCambio.setText("$" + myFormatter.format(total_compra));
        labelCambio.setFont(new Font("", Font.BOLD, 16));
        labelCambio.setPreferredSize(new Dimension(150, 30));

        jPanel.add(labelCambio, gbc);

        btnCalcular.addActionListener((java.awt.event.ActionEvent evt) -> {
            calcular(labelCambio, txtRecibir, myFormatter);
        });

        gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.insets = new Insets(10, 0, 0, 0);

        container.add(jPanel, gbc);

        jPanel = new JPanel(new GridBagLayout());

        gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 0, 0);

        JButton btnBoleta = new JButton("Boleta");
        btnBoleta.setEnabled(false);
        btnBoleta.setPreferredSize(new Dimension(100, 40));
        btnBoleta.addActionListener((var evt) -> {
            try {
                Object[] choices = {"PDF", "Excel"};

                int option = JOptionPane.showOptionDialog(
                        this,
                        "Exportar Boleta a:",
                        "Exportar",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.INFORMATION_MESSAGE,
                        null,
                        choices,
                        null
                );

                if (option == 0) {
                    PDF.boleta();
                } else if (option == 1) {
                    Excel.boleta();
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error al generar boleta.", "Mensaje", JOptionPane.WARNING_MESSAGE, error);
            }
        });

        JButton btnFactura = new JButton("Factura");
        btnFactura.setEnabled(false);
        btnFactura.setPreferredSize(new Dimension(100, 40));
        btnFactura.addActionListener((java.awt.event.ActionEvent evt) -> {
            try {
                Object[] choices = {"PDF", "Excel"};

                int option = JOptionPane.showOptionDialog(
                        this,
                        "Exportar Factura a:",
                        "Exportar",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.INFORMATION_MESSAGE,
                        null,
                        choices,
                        null
                );

                if (option == 0) {
                    PDF.factura();
                } else if (option == 1) {
                    Excel.factura();
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error al generar boleta.", "Mensaje", JOptionPane.WARNING_MESSAGE, error);
            }
        });

        jPanel.add(btnBoleta, gbc);
        jPanel.add(btnFactura, gbc);

        gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.insets = new Insets(10, 15, 0, 0);

        container.add(jPanel, gbc);

        jPanel = new JPanel(new GridBagLayout());

        gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 0, 10);

        JButton btnRealizarVenta = new JButton("Realizar venta");
        btnRealizarVenta.setPreferredSize(new Dimension(200, 50));
        btnRealizarVenta.addActionListener((java.awt.event.ActionEvent evt) -> {
            try {
                calcular(labelCambio, txtRecibir, myFormatter);

                if (!txtRecibir.getText().trim().isEmpty()) {
                    recibir = Double.parseDouble(txtRecibir.getText().trim());

                    double cambio_tmp = recibir - total_compra;

                    if (cambio_tmp > 0) {
                        btnRealizarVenta.setEnabled(false);

                        Conexion conexion = new Conexion();

                        int estado = conexion.realizarVenta(carrito_compras_tmp);

                        if (estado != 0) {
                            txtRecibir.setEnabled(false);
                            btnCalcular.setEnabled(false);
                            btnBoleta.setEnabled(true);
                            btnFactura.setEnabled(true);

                            JOptionPane.showMessageDialog(null, "Venta realizada con exito.", "Mensaje", JOptionPane.INFORMATION_MESSAGE, success);
                        } else {
                            btnRealizarVenta.setEnabled(true);

                            JOptionPane.showMessageDialog(null, "Error en la venta.", "Mensaje", JOptionPane.WARNING_MESSAGE, error);
                        }
                    } else {
                        txtRecibir.requestFocus(true);
                        JOptionPane.showMessageDialog(null, "El cambio no puede ser negativo.", "Mensaje", JOptionPane.WARNING_MESSAGE, error);
                    }
                } else {
                    txtRecibir.requestFocus(true);
                    JOptionPane.showMessageDialog(null, "El campo recibir no puede estar vacio.", "Mensaje", JOptionPane.WARNING_MESSAGE, error);
                }
            } catch (Exception ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        jPanel.add(btnRealizarVenta, gbc);

        gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.insets = new Insets(10, 15, 0, 0);

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

    public void calcular(JLabel labelCambio, JFormattedTextField txtRecibir, NumberFormat myFormatter) {
        recibir = 0;

        if (!txtRecibir.getText().trim().isEmpty()) {
            recibir = Double.parseDouble(txtRecibir.getText().trim());

            cambio = recibir - total_compra;

            labelCambio.setText("$" + myFormatter.format(cambio));
        } else {
            cambio = total_compra;

            labelCambio.setText("$" + myFormatter.format(total_compra));
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
            java.util.logging.Logger.getLogger(RealizarVenta.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RealizarVenta.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RealizarVenta.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RealizarVenta.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RealizarVenta().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
