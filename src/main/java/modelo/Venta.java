package modelo;

import java.sql.Timestamp;

public class Venta {
    private int idVenta;
    private String dniCliente;
    private int idProducto;
    private int cantidad;
    private double precioUnitario;
    private Timestamp fechaVenta;

    // Constructor vacío
    public Venta() {
    }

    // Constructor sin ID
    public Venta(String dniCliente, int idProducto, int cantidad, double precioUnitario) {
        this.dniCliente = dniCliente;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    // Constructor completo
    public Venta(int idVenta, String dniCliente, int idProducto, int cantidad, double precioUnitario, Timestamp fechaVenta) {
        this.idVenta = idVenta;
        this.dniCliente = dniCliente;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.fechaVenta = fechaVenta;
    }

    // Getters y Setters
    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public String getDniCliente() {
        return dniCliente;
    }

    public void setDniCliente(String dniCliente) {
        this.dniCliente = dniCliente;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Timestamp getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Timestamp fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    @Override
    public String toString() {
        return "Venta{" +
                "idVenta=" + idVenta +
                ", dniCliente='" + dniCliente + '\'' +
                ", idProducto=" + idProducto +
                ", cantidad=" + cantidad +
                ", precioUnitario=" + precioUnitario +
                ", fechaVenta=" + fechaVenta +
                '}';
    }

    // Método para calcular el total de la venta
    public double calcularTotal() {
        return cantidad * precioUnitario;
    }
}