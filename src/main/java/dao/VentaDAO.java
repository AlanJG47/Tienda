package dao;

import modelo.Venta;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VentaDAO {
    private Connection conexion;

    public VentaDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public void insertar(Venta venta) throws SQLException {
        conexion.setAutoCommit(false);
        try {
            String sql = "INSERT INTO ventas (dni_cliente, id_producto, cantidad, precio_unitario) VALUES (?, ?, ?, ?)";
            try (PreparedStatement stmt = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, venta.getDniCliente());
                stmt.setInt(2, venta.getIdProducto());
                stmt.setInt(3, venta.getCantidad());
                stmt.setDouble(4, venta.getPrecioUnitario());
                stmt.executeUpdate();

                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    venta.setIdVenta(rs.getInt(1));
                }
            }

            actualizarStockProducto(venta.getIdProducto(), venta.getCantidad());
            conexion.commit();
        } catch (SQLException e) {
            conexion.rollback();
            throw e;
        } finally {
            conexion.setAutoCommit(true);
        }
    }

    public Venta obtenerPorId(int id) throws SQLException {
        String sql = "SELECT * FROM ventas WHERE id_venta = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return extraerVentaDeResultSet(rs);
            }
        }
        return null;
    }

    public List<Venta> obtenerTodos() throws SQLException {
        List<Venta> ventas = new ArrayList<>();
        String sql = "SELECT * FROM ventas";
        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                ventas.add(extraerVentaDeResultSet(rs));
            }
        }
        return ventas;
    }

    public List<Venta> obtenerVentasPorCliente(String dni) throws SQLException {
        List<Venta> ventas = new ArrayList<>();
        String sql = "SELECT * FROM ventas WHERE dni_cliente = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, dni);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ventas.add(extraerVentaDeResultSet(rs));
            }
        }
        return ventas;
    }

    public List<Venta> obtenerVentasPorFecha(Date fecha) throws SQLException {
        List<Venta> ventas = new ArrayList<>();
        String sql = "SELECT * FROM ventas WHERE DATE(fecha_venta) = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setDate(1, fecha);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ventas.add(extraerVentaDeResultSet(rs));
            }
        }
        return ventas;
    }

    public double obtenerTotalVentasPorFecha(Date fecha) throws SQLException {
        String sql = "SELECT SUM(cantidad * precio_unitario) as total FROM ventas WHERE DATE(fecha_venta) = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setDate(1, fecha);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("total");
            }
        }
        return 0.0;
    }

    private void actualizarStockProducto(int idProducto, int cantidadVendida) throws SQLException {
        String sql = "UPDATE productos SET stock = stock - ? WHERE id_producto = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, cantidadVendida);
            stmt.setInt(2, idProducto);
            stmt.executeUpdate();
        }
    }

    private Venta extraerVentaDeResultSet(ResultSet rs) throws SQLException {
        Venta venta = new Venta();
        venta.setIdVenta(rs.getInt("id_venta"));
        venta.setDniCliente(rs.getString("dni_cliente"));
        venta.setIdProducto(rs.getInt("id_producto"));
        venta.setCantidad(rs.getInt("cantidad"));
        venta.setPrecioUnitario(rs.getDouble("precio_unitario"));
        venta.setFechaVenta(rs.getTimestamp("fecha_venta"));
        return venta;
    }

    public void actualizar(Venta venta) throws SQLException {
        String sql = "UPDATE ventas SET cantidad = ? WHERE id_venta = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, venta.getCantidad());
            stmt.setInt(2, venta.getIdVenta());
            stmt.executeUpdate();
        }
    }

    public void eliminar(int idVenta) throws SQLException {
        String sql = "DELETE FROM ventas WHERE id_venta = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, idVenta);
            stmt.executeUpdate();
        }
    }

    public Venta obtenerUltimaVenta() throws SQLException {
        String sql = "SELECT * FROM ventas WHERE id_venta = (SELECT MAX(id_venta) FROM ventas)";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Venta venta = new Venta();
                venta.setIdVenta(rs.getInt("id_venta"));
                venta.setDniCliente(rs.getString("dni_cliente"));
                venta.setIdProducto(rs.getInt("id_producto"));
                venta.setCantidad(rs.getInt("cantidad"));
                venta.setPrecioUnitario(rs.getDouble("precio_unitario"));
                venta.setFechaVenta(rs.getTimestamp("fecha_venta"));
                return venta;
            }
        }
        return null;
    }
}
