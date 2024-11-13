
import util.ConexionMysql;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        Connection conexion = null;
        try {
            System.out.println("=== SISTEMA DE GESTIÓN DE TIENDA DE CELULARES ===");
            System.out.println("Conectando a la base de datos...");

            conexion = ConexionMysql.dameConexion();

            if (conexion != null) {
                System.out.println("Conexión exitosa!");
                System.out.println("--------------------------------");

                // Crear e iniciar el menú
                Menu menu = new Menu(conexion);
                menu.mostrarMenuPrincipal();
            }

        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (conexion != null) {
                try {
                    conexion.close();
                    System.out.println("Conexión cerrada correctamente");
                } catch (SQLException e) {
                    System.err.println("Error al cerrar la conexión: " + e.getMessage());
                }
            }
        }
    }
} 