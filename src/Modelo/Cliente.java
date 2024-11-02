package Modelo;

public class Cliente {
    private int CodCliente;
    private String Nombres;
    private String Numero_Dni;
    private String Numero_Ruc;
    private String Telefono;
    private String Direccion;

    public Cliente() {
    }

    public Cliente(int CodCliente, String Nombres, String Numero_Dni, String Numero_Ruc, String Telefono, String Direccion) {
        this.CodCliente = CodCliente;
        this.Nombres = Nombres;
        this.Numero_Dni = Numero_Dni;
        this.Numero_Ruc = Numero_Ruc;
        this.Telefono = Telefono;
        this.Direccion = Direccion;
    }

    public int getCodCliente() {
        return CodCliente;
    }

    public void setCodCliente(int CodCliente) {
        this.CodCliente = CodCliente;
    }

    public String getNombres() {
        return Nombres;
    }

    public void setNombres(String Nombres) {
        this.Nombres = Nombres;
    }

    public String getNumero_Dni() {
        return Numero_Dni;
    }

    public void setNumero_Dni(String Numero_Dni) {
        this.Numero_Dni = Numero_Dni;
    }

    public String getNumero_Ruc() {
        return Numero_Ruc;
    }

    public void setNumero_Ruc(String Numero_Ruc) {
        this.Numero_Ruc = Numero_Ruc;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }
}
