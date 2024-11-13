package modelo;

public class Producto {
    private int idProducto;
    private String marca;
    private String modelo;
    private double precioCompra;
    private double precioVenta;
    private int stock;
    private int idProveedor;

    // Constructor vacío
    public Producto() {}

    // Constructor sin ID
    public Producto(String marca, String modelo, double precioCompra, double precioVenta, int stock, int idProveedor) {
        this.marca = marca;
        this.modelo = modelo;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
        this.stock = stock;
        this.idProveedor = idProveedor;
    }

    // Constructor completo
    public Producto(int idProducto, String marca, String modelo, double precioCompra, double precioVenta, int stock, int idProveedor) {
        this.idProducto = idProducto;
        this.marca = marca;
        this.modelo = modelo;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
        this.stock = stock;
        this.idProveedor = idProveedor;
    }

    // Getters y Setters
    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(double precioCompra) {
        this.precioCompra = precioCompra;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "idProducto=" + idProducto +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", precioCompra=" + precioCompra +
                ", precioVenta=" + precioVenta +
                ", stock=" + stock +
                ", idProveedor=" + idProveedor +
                '}';
    }

}