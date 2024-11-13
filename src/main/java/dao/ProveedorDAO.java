package dao;

import modelo.Proveedor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProveedorDAO {
    private Connection conexion;

    public ProveedorDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public void insertar(Proveedor proveedor) throws SQLException {
        String sql = "INSERT INTO proveedores (nombre_empresa, contacto_nombre, telefono_soporte, telefono_comercial, email) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, proveedor.getNombreEmpresa());
            stmt.setString(2, proveedor.getContactoNombre());
            stmt.setString(3, proveedor.getTelefonoSoporte());
            stmt.setString(4, proveedor.getTelefonoComercial());
            stmt.setString(5, proveedor.getEmail());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                proveedor.setIdProveedor(rs.getInt(1));
            }
        }
    }

    public Proveedor obtenerPorId(int id) throws SQLException {
        String sql = "SELECT * FROM proveedores WHERE id_proveedor = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return extraerProveedorDeResultSet(rs);
            }
        }
        return null;
    }

    public List<Proveedor> obtenerTodos() throws SQLException {
        List<Proveedor> proveedores = new ArrayList<>();
        String sql = "SELECT * FROM proveedores";
        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                proveedores.add(extraerProveedorDeResultSet(rs));
            }
        }
        return proveedores;
    }

    public void actualizar(Proveedor proveedor) throws SQLException {
        String sql = "UPDATE proveedores SET nombre_empresa = ?, contacto_nombre = ?, telefono_soporte = ?, telefono_comercial = ?, email = ? WHERE id_proveedor = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, proveedor.getNombreEmpresa());
            stmt.setString(2, proveedor.getContactoNombre());
            stmt.setString(3, proveedor.getTelefonoSoporte());
            stmt.setString(4, proveedor.getTelefonoComercial());
            stmt.setString(5, proveedor.getEmail());
            stmt.setInt(6, proveedor.getIdProveedor());
            stmt.executeUpdate();
        }
    }

    public void eliminar(int id) throws SQLException {
        String sql = "DELETE FROM proveedores WHERE id_proveedor = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    private Proveedor extraerProveedorDeResultSet(ResultSet rs) throws SQLException {
        Proveedor proveedor = new Proveedor();
        proveedor.setIdProveedor(rs.getInt("id_proveedor"));
        proveedor.setNombreEmpresa(rs.getString("nombre_empresa"));
        proveedor.setContactoNombre(rs.getString("contacto_nombre"));
        proveedor.setTelefonoSoporte(rs.getString("telefono_soporte"));
        proveedor.setTelefonoComercial(rs.getString("telefono_comercial"));
        proveedor.setEmail(rs.getString("email"));
        return proveedor;
    }
}