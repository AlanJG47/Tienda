package modelo;

public class Proveedor {
    private int idProveedor;
    private String nombreEmpresa;
    private String contactoNombre;
    private String telefonoSoporte;
    private String telefonoComercial;
    private String email;
    private String direccion;

    // Constructor vacío
    public Proveedor() {}

    // Constructor con parámetros básicos
    public Proveedor(String nombreEmpresa, String contactoNombre, String telefonoSoporte, String telefonoComercial, String email, String direccion) {
        this.nombreEmpresa = nombreEmpresa;
        this.contactoNombre = contactoNombre;
        this.telefonoSoporte = telefonoSoporte;
        this.telefonoComercial = telefonoComercial;
        this.email = email;
        this.direccion = direccion;
    }

    // Constructor completo
    public Proveedor(int idProveedor, String nombreEmpresa, String contactoNombre, String telefonoSoporte, String telefonoComercial, String email, String direccion) {
        this.idProveedor = idProveedor;
        this.nombreEmpresa = nombreEmpresa;
        this.contactoNombre = contactoNombre;
        this.telefonoSoporte = telefonoSoporte;
        this.telefonoComercial = telefonoComercial;
        this.email = email;
        this.direccion = direccion;
    }

    // Getters y Setters
    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getContactoNombre() {
        return contactoNombre;
    }

    public void setContactoNombre(String contactoNombre) {
        this.contactoNombre = contactoNombre;
    }

    public String getTelefonoSoporte() {
        return telefonoSoporte;
    }

    public void setTelefonoSoporte(String telefonoSoporte) {
        this.telefonoSoporte = telefonoSoporte;
    }

    public String getTelefonoComercial() {
        return telefonoComercial;
    }

    public void setTelefonoComercial(String telefonoComercial) {
        this.telefonoComercial = telefonoComercial;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "Proveedor{" +
                "idProveedor=" + idProveedor +
                ", nombreEmpresa='" + nombreEmpresa + '\'' +
                ", contactoNombre='" + contactoNombre + '\'' +
                ", telefonoSoporte='" + telefonoSoporte + '\'' +
                ", telefonoComercial='" + telefonoComercial + '\'' +
                ", email='" + email + '\'' +
                ", direccion='" + direccion + '\'' +
                '}';
    }
}