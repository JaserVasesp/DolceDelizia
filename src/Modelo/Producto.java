package Modelo;

public class Producto {
    private int CodProducto;
    private int Cantidad;
    private String Descripcion;
    private double Precio;
    private String Imagen;

    public Producto(int CodProducto, int Cantidad, String Descripcion, double Precio, String Imagen) {
        this.CodProducto = CodProducto;
        this.Cantidad = Cantidad;
        this.Descripcion = Descripcion;
        this.Precio = Precio;
        this.Imagen = Imagen;
    }

    public int getCodProducto() {
        return CodProducto;
    }

    public void setCodProducto(int CodProducto) {
        this.CodProducto = CodProducto;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int Cantidad) {
        this.Cantidad = Cantidad;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public double getPrecio() {
        return Precio;
    }

    public void setPrecio(double Precio) {
        this.Precio = Precio;
    }

    public String getImagen() {
        return Imagen;
    }

    public void setImagen(String Imagen) {
        this.Imagen = Imagen;
    }
}
