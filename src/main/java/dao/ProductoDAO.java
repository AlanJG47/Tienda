package dao;

import modelo.Producto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {
    private Connection conexion;

    public ProductoDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public void insertar(Producto producto) throws SQLException {
        String sql = "INSERT INTO productos (marca, modelo, precio_compra, precio_venta, stock, id_proveedor) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, producto.getMarca());
            stmt.setString(2, producto.getModelo());
            stmt.setDouble(3, producto.getPrecioCompra());
            stmt.setDouble(4, producto.getPrecioVenta());
            stmt.setInt(5, producto.getStock());
            stmt.setInt(6, producto.getIdProveedor());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                producto.setIdProducto(rs.getInt(1));
            }
        }
    }

    public Producto obtenerPorId(int id) throws SQLException {
        String sql = "SELECT * FROM productos WHERE id_producto = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return extraerProductoDeResultSet(rs);
            }
        }
        return null;
    }

    public List<Producto> obtenerTodos() throws SQLException {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM productos";
        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                productos.add(extraerProductoDeResultSet(rs));
            }
        }
        return productos;
    }

    public void actualizar(Producto producto) throws SQLException {
        String sql = "UPDATE productos SET marca = ?, modelo = ?, precio_compra = ?, precio_venta = ?, stock = ?, id_proveedor = ? WHERE id_producto = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, producto.getMarca());
            stmt.setString(2, producto.getModelo());
            stmt.setDouble(3, producto.getPrecioCompra());
            stmt.setDouble(4, producto.getPrecioVenta());
            stmt.setInt(5, producto.getStock());
            stmt.setInt(6, producto.getIdProveedor());
            stmt.setInt(7, producto.getIdProducto());
            stmt.executeUpdate();
        }
    }

    public void eliminar(int id) throws SQLException {
        String sql = "DELETE FROM productos WHERE id_producto = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    private Producto extraerProductoDeResultSet(ResultSet rs) throws SQLException {
        Producto producto = new Producto();
        producto.setIdProducto(rs.getInt("id_producto"));
        producto.setMarca(rs.getString("marca"));
        producto.setModelo(rs.getString("modelo"));
        producto.setPrecioCompra(rs.getDouble("precio_compra"));
        producto.setPrecioVenta(rs.getDouble("precio_venta"));
        producto.setStock(rs.getInt("stock"));
        producto.setIdProveedor(rs.getInt("id_proveedor"));
        return producto;
    }

    // Métodos adicionales útiles
    public void actualizarStock(int idProducto, int nuevoStock) throws SQLException {
        String sql = "UPDATE productos SET stock = ? WHERE id_producto = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, nuevoStock);
            stmt.setInt(2, idProducto);
            stmt.executeUpdate();
        }
    }

    public List<Producto> buscarPorMarca(String marca) throws SQLException {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM productos WHERE marca LIKE ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, "%" + marca + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                productos.add(extraerProductoDeResultSet(rs));
            }
        }
        return productos;
    }
}