package Modelo;

public class Sistema {

    private int CodSistema;
    private int CodProducto;
    private String Descripcion;
    private int Cantidad;
    private double Precio;
    private double Total;

    public Sistema() {
    }

    public Sistema(int CodSistema, int CodProducto, String Descripcion, int Cantidad, double Precio, double Total) {
        this.CodSistema = CodSistema;
        this.CodProducto = CodProducto;
        this.Descripcion = Descripcion;
        this.Cantidad = Cantidad;
        this.Precio = Precio;
        this.Total = Total;
    }

    public int getCodSistema() {
        return CodSistema;
    }

    public void setCodSistema(int CodSistema) {
        this.CodSistema = CodSistema;
    }

    public int getCodProducto() {
        return CodProducto;
    }

    public void setCodProducto(int CodProducto) {
        this.CodProducto = CodProducto;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int Cantidad) {
        this.Cantidad = Cantidad;
    }

    public double getPrecio() {
        return Precio;
    }

    public void setPrecio(double Precio) {
        this.Precio = Precio;
    }

    public double getTotal() {
        return Total;
    }

    public void setTotal(double Total) {
        this.Total = Total;
    }
}
