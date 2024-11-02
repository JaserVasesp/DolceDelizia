package Modelo;

public class Cajera {
    private int CodUsuario;
    private String Usuario;
    private String Contrasenia;

    public Cajera() {
    }

    public Cajera(int CodUsuario, String Usuario, String Contrasenia) {
        this.CodUsuario = CodUsuario;
        this.Usuario = Usuario;
        this.Contrasenia = Contrasenia;
    }

    public int getCodUsuario() {
        return CodUsuario;
    }

    public void setCodUsuario(int CodUsuario) {
        this.CodUsuario = CodUsuario;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }

    public String getContrasenia() {
        return Contrasenia;
    }

    public void setContrasenia(String Contrasenia) {
        this.Contrasenia = Contrasenia;
    }
    
    
}
