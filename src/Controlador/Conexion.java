package Controlador;

import Modelo.Cajera;
import Modelo.Carrito;
import Modelo.Cliente;
import Modelo.Producto;
import Modelo.Sistema;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Conexion {

    private Connection conectar = null;

    public Connection conectar() throws Exception {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conectar = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_dolcedelizia", "root", "");

            if (conectar != null) {

            }
        } catch (SQLException e) {
            System.err.println("Error de conexion a BD" + e.getMessage());
        }

        return conectar;
    }

    public void cerrarConexion() {
        try {
            if (conectar != null) {
                conectar.close();
            }
        } catch (SQLException ex) {
            System.out.println("Error al cerrar conexion:" + ex.getMessage());
        }
    }

    public ArrayList<Cajera> login(String usuario, String password) throws Exception {
        ArrayList<Cajera> lista = new ArrayList<>();

        conectar = conectar();

        Statement s = conectar.createStatement();

        ResultSet rs = s.executeQuery("SELECT * FROM cajera WHERE LOWER(Usuario) = '" + usuario.toLowerCase() + "' AND Contrasenia = '" + password + "'");

        while (rs.next()) {
            lista.add(new Cajera(
                rs.getInt("CodUsuario"),
                rs.getString("Usuario"),
                rs.getString("Contrasenia")
            ));
        }

        return lista;
    }

    public ArrayList<Producto> listaProductos() throws Exception {
        ArrayList<Producto> lista = new ArrayList<>();

        conectar = conectar();

        Statement s = conectar.createStatement();

        ResultSet rs = s.executeQuery("SELECT * FROM producto");

        while (rs.next()) {
            lista.add(new Producto(
                rs.getInt("CodProducto"),
                rs.getInt("Cantidad"),
                rs.getString("Descripcion"),
                rs.getDouble("Precio"),
                rs.getString("Imagen")
            ));
        }

        return lista;
    }

    public int realizarVenta(ArrayList<Carrito> carrito_compras) throws Exception {
        conectar = conectar();

        int estado = 0;

        eliminarVenta();

        for (Carrito carrito : carrito_compras) {
            if (carrito.getCantidad() > 0) {
                int cantidad = carrito.getCantidad();
                double precio_igv = carrito.getPrecio() + (carrito.getPrecio() * 0.18);
                double total = cantidad * precio_igv;

                String sql = "INSERT INTO sistema (CodProducto,Descripcion,Cantidad,Precio,Total) VALUES (?,?,?,?,?)";
                PreparedStatement preparedStatement = conectar.prepareStatement(sql);
                preparedStatement.setInt(1, carrito.getCodProducto());
                preparedStatement.setString(2, carrito.getDescripcion());
                preparedStatement.setInt(3, cantidad);
                preparedStatement.setDouble(4, precio_igv);
                preparedStatement.setDouble(5, total);
                estado = preparedStatement.executeUpdate();
                
                sql = "UPDATE producto SET cantidad = cantidad - ? WHERE CodProducto = ?";
                preparedStatement = conectar.prepareStatement(sql);
                preparedStatement.setInt(1, carrito.getCantidad());
                preparedStatement.setInt(2, carrito.getCodProducto());
                preparedStatement.executeUpdate();
            }
        }
        
        return estado;
    }

    public void eliminarVenta() throws Exception {
        conectar = conectar();

        String sql = "DELETE FROM sistema";
        PreparedStatement preparedStatement = conectar.prepareStatement(sql);
        preparedStatement.executeUpdate();
    }
    
    public ArrayList<Sistema> listaSistema() throws Exception {
        ArrayList<Sistema> lista = new ArrayList<>();

        conectar = conectar();

        Statement s = conectar.createStatement();

        ResultSet rs = s.executeQuery("SELECT * FROM sistema");

        while (rs.next()) {
            lista.add(new Sistema(
                rs.getInt("CodSistema"),
                rs.getInt("CodProducto"),
                rs.getString("Descripcion"),
                rs.getInt("Cantidad"),
                rs.getDouble("Precio"),
                rs.getDouble("Total")
            ));
        }

        return lista;
    }
    
    public ArrayList<Cliente> listaCliente() throws Exception {
        ArrayList<Cliente> lista = new ArrayList<>();

        conectar = conectar();

        Statement s = conectar.createStatement();

        ResultSet rs = s.executeQuery("SELECT * FROM cliente ORDER BY CodCliente DESC LIMIT 1");

        while (rs.next()) {
            lista.add(new Cliente(
                rs.getInt("CodCliente"),
                rs.getString("Nombres"),
                rs.getString("Numero_Dni"),
                rs.getString("Numero_Ruc"),
                rs.getString("Telefono"),
                rs.getString("Direccion")
            ));
        }
        
        if(lista.isEmpty()) {
            lista.clear();
            
            lista.add(new Cliente(
                0,
                "",
                "",
                "",
                "",
                ""
            ));
        }

        return lista;
    }
}
