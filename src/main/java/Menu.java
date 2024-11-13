import dao.*;
import modelo.*;
import java.sql.Connection;
import java.util.Scanner;
import java.util.List;
import java.sql.SQLException;
import java.sql.Timestamp;

public class Menu {
    private Scanner scanner;
    private ProductoDAO productoDAO;
    private ClienteDAO clienteDAO;
    private ProveedorDAO proveedorDAO;
    private VentaDAO ventaDAO;

    public Menu(Connection conexion) {
        this.scanner = new Scanner(System.in);
        this.productoDAO = new ProductoDAO(conexion);
        this.clienteDAO = new ClienteDAO(conexion);
        this.proveedorDAO = new ProveedorDAO(conexion);
        this.ventaDAO = new VentaDAO(conexion);
    }

    public void mostrarMenuPrincipal() {
        while (true) {
            System.out.println("\n----------MENU-----------");
            System.out.println("1. Clientes");
            System.out.println("2. Proveedores");
            System.out.println("3. Productos");
            System.out.println("4. Ventas");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                int opcion = scanner.nextInt();
                scanner.nextLine(); 

                switch (opcion) {
                    case 1:
                        menuClientes();
                        break;
                    case 2:
                        menuProveedores();
                        break;
                    case 3:
                        menuProductos();
                        break;
                    case 4:
                        menuVentas();
                        break;
                    case 5:
                        return;
                    default:
                        System.out.println("Opción no válida");
                }
            } catch (Exception e) {
                System.out.println("Error inesperado: " + e.getMessage());
                scanner.nextLine(); 
            }
        }
    }

    private void menuClientes() {
        while (true) {
            System.out.println("\n-----Clientes-----");
            System.out.println("1. Lista de clientes");
            System.out.println("2. Crear nuevo cliente");
            System.out.println("3. Actualizar datos del cliente");
            System.out.println("4. Eliminar Cliente");
            System.out.println("5. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); 

            try {
                switch (opcion) {
                    case 1:
                        mostrarListaClientes();
                        break;
                    case 2:
                        crearNuevoCliente();
                        break;
                    case 3:
                        actualizarCliente();
                        break;
                    case 4:
                        eliminarCliente();
                        break;
                    case 5:
                        return;
                    default:
                        System.out.println("Opción no válida");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void menuProveedores() {
        while (true) {
            System.out.println("\n-----Proveedores-----");
            System.out.println("1. Lista de proveedores");
            System.out.println("2. Crear nuevo proveedor");
            System.out.println("3. Actualizar datos del proveedor");
            System.out.println("4. Eliminar Proveedor");
            System.out.println("5. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            try {
                switch (opcion) {
                    case 1:
                        mostrarListaProveedores();
                        break;
                    case 2:
                        crearNuevoProveedor();
                        break;
                    case 3:
                        actualizarProveedor();
                        break;
                    case 4:
                        eliminarProveedor();
                        break;
                    case 5:
                        return;
                    default:
                        System.out.println("Opción no válida");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void menuProductos() {
        while (true) {
            System.out.println("\n-----Productos-----");
            System.out.println("1. Lista de productos");
            System.out.println("2. Crear nuevo producto");
            System.out.println("3. Actualizar precio");
            System.out.println("4. Actualizar Stock");
            System.out.println("5. Eliminar Producto");
            System.out.println("6. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            try {
                int opcion = scanner.nextInt();
                scanner.nextLine(); 

                switch (opcion) {
                    case 1:
                        mostrarListaProductos();
                        break;
                    case 2:
                        crearNuevoProducto();
                        break;
                    case 3:
                        actualizarPrecioProducto();
                        break;
                    case 4:
                        actualizarStockProducto();
                        break;
                    case 5:
                        eliminarProducto();
                        break;
                    case 6:
                        return;
                    default:
                        System.out.println("Opción no válida");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                scanner.nextLine();
            }
        }
    }

    private void menuVentas() {
        while (true) {
            System.out.println("\n-----Ventas-----");
            System.out.println("1. Registrar nueva venta");
            System.out.println("2. Ver lista de ventas");
            System.out.println("3. Actualizar venta");
            System.out.println("4. Eliminar venta");
            System.out.println("5. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            try {
                String opcionStr = scanner.nextLine();
                if (opcionStr.isEmpty()) continue;

                int opcion = Integer.parseInt(opcionStr);
                switch (opcion) {
                    case 1:
                        registrarVenta();
                        break;
                    case 2:
                        mostrarListaVentas();
                        break;
                    case 3:
                        actualizarVenta();
                        break;
                    case 4:
                        eliminarVenta();
                        break;
                    case 5:
                        return;
                    default:
                        System.out.println("Opción no válida");
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingrese un número válido");
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    // Métodos para Clientes
    private void mostrarListaClientes() throws SQLException {
        List<Cliente> clientes = clienteDAO.obtenerTodos();
        System.out.println("\nLista de Clientes:");
        for (Cliente cliente : clientes) {
            System.out.println(
                    "DNI: " + cliente.getDni() +
                            " | Nombre: " + cliente.getNombre() +
                            " | Teléfono: " + cliente.getTelefono() +
                            " | Dirección: " + cliente.getDireccion() +
                            " | Fecha Registro: " + cliente.getFechaRegistro()
            );
        }
    }

    private void crearNuevoCliente() throws SQLException {
        System.out.println("\nCrear Nuevo Cliente");
        System.out.print("DNI: ");
        String dni = scanner.nextLine();
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Teléfono: ");
        String telefono = scanner.nextLine();
        System.out.print("Dirección: ");
        String direccion = scanner.nextLine();

        Cliente cliente = new Cliente();
        cliente.setDni(dni);
        cliente.setNombre(nombre);
        cliente.setTelefono(telefono);
        cliente.setDireccion(direccion);
        cliente.setFechaRegistro(new Timestamp(System.currentTimeMillis()));

        clienteDAO.insertar(cliente);
        System.out.println("Cliente creado exitosamente!");
    }

    private void actualizarCliente() throws SQLException {
        System.out.print("Ingrese el DNI del cliente a actualizar: ");
        String dni = scanner.nextLine();

        Cliente cliente = clienteDAO.obtenerPorDni(dni);
        if (cliente == null) {
            System.out.println("Cliente no encontrado");
            return;
        }

        System.out.println("Datos actuales:");
        System.out.println(cliente.getNombre() + " | " + cliente.getTelefono());

        System.out.print("Nuevo nombre (Enter para mantener actual): ");
        String nombre = scanner.nextLine();
        if (!nombre.isEmpty()) cliente.setNombre(nombre);

        System.out.print("Nuevo teléfono (Enter para mantener actual): ");
        String telefono = scanner.nextLine();
        if (!telefono.isEmpty()) cliente.setTelefono(telefono);

        clienteDAO.actualizar(cliente);
        System.out.println("Cliente actualizado exitosamente!");
    }

    private void eliminarCliente() throws SQLException {
        System.out.print("Ingrese el DNI del cliente a eliminar: ");
        String dni = scanner.nextLine();
        clienteDAO.eliminar(dni);
        System.out.println("Cliente eliminado exitosamente!");
    }

    // Métodos para Proveedores
    private void mostrarListaProveedores() throws SQLException {
        List<Proveedor> proveedores = proveedorDAO.obtenerTodos();
        System.out.println("\nLista de Proveedores:");
        for (Proveedor proveedor : proveedores) {
            System.out.println("ID: " + proveedor.getIdProveedor() +
                    " | Empresa: " + proveedor.getNombreEmpresa() +
                    " | Contacto: " + proveedor.getContactoNombre() +
                    " | Tel. Soporte: " + proveedor.getTelefonoSoporte() +
                    " | Tel. Comercial: " + proveedor.getTelefonoComercial());
        }
    }

    private void crearNuevoProveedor() throws SQLException {
        System.out.println("\nCrear Nuevo Proveedor");
        System.out.print("Nombre Empresa: ");
        String nombreEmpresa = scanner.nextLine();
        System.out.print("Nombre Contacto: ");
        String contacto = scanner.nextLine();
        System.out.print("Teléfono Soporte Técnico: ");
        String telefonoSoporte = scanner.nextLine();
        System.out.print("Teléfono Comercial: ");
        String telefonoComercial = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();

        Proveedor proveedor = new Proveedor();
        proveedor.setNombreEmpresa(nombreEmpresa);
        proveedor.setContactoNombre(contacto);
        proveedor.setTelefonoSoporte(telefonoSoporte);
        proveedor.setTelefonoComercial(telefonoComercial);
        proveedor.setEmail(email);

        proveedorDAO.insertar(proveedor);
        System.out.println("Proveedor creado exitosamente!");
    }

    private void actualizarProveedor() throws SQLException {
        System.out.print("Ingrese el ID del proveedor a actualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Proveedor proveedor = proveedorDAO.obtenerPorId(id);
        if (proveedor == null) {
            System.out.println("Proveedor no encontrado");
            return;
        }

        System.out.print("Nuevo teléfono soporte (Enter para mantener actual): ");
        String telefonoSoporte = scanner.nextLine();
        if (!telefonoSoporte.isEmpty()) proveedor.setTelefonoSoporte(telefonoSoporte);

        System.out.print("Nuevo teléfono comercial (Enter para mantener actual): ");
        String telefonoComercial = scanner.nextLine();
        if (!telefonoComercial.isEmpty()) proveedor.setTelefonoComercial(telefonoComercial);

        System.out.print("Nuevo email (Enter para mantener actual): ");
        String email = scanner.nextLine();
        if (!email.isEmpty()) proveedor.setEmail(email);

        proveedorDAO.actualizar(proveedor);
        System.out.println("Proveedor actualizado exitosamente!");
    }

    private void eliminarProveedor() throws SQLException {
        System.out.print("Ingrese el ID del proveedor a eliminar: ");
        int id = scanner.nextInt();
        proveedorDAO.eliminar(id);
        System.out.println("Proveedor eliminado exitosamente!");
    }

    // Métodos para Productos
    private void mostrarListaProductos() throws SQLException {
        List<Producto> productos = productoDAO.obtenerTodos();
        System.out.println("\nLista de Productos:");
        for (Producto producto : productos) {
            System.out.println("ID: " + producto.getIdProducto() +
                    " | Marca: " + producto.getMarca() +
                    " | Modelo: " + producto.getModelo() +
                    " | Precio: $" + producto.getPrecioVenta() +
                    " | Stock: " + producto.getStock());
        }
    }

    private void actualizarPrecioProducto() throws SQLException {
        System.out.print("Ingrese el ID del producto: ");
        int id = scanner.nextInt();

        Producto producto = productoDAO.obtenerPorId(id);
        if (producto == null) {
            System.out.println("Producto no encontrado");
            return;
        }

        System.out.println("Precio de compra actual: $" + producto.getPrecioCompra());
        System.out.print("Ingrese el porcentaje de ganancia deseado: ");
        double porcentaje = scanner.nextDouble();

        double nuevoPrecioVenta = producto.getPrecioCompra() * (1 + porcentaje/100);
        producto.setPrecioVenta(nuevoPrecioVenta);

        productoDAO.actualizar(producto);
        System.out.println("Precio actualizado exitosamente!");
    }

    private void actualizarStockProducto() throws SQLException {
        System.out.print("Ingrese el ID del producto: ");
        int id = scanner.nextInt();
        System.out.print("Ingrese el nuevo stock: ");
        int nuevoStock = scanner.nextInt();

        productoDAO.actualizarStock(id, nuevoStock);
        System.out.println("Stock actualizado exitosamente!");
    }

    private void eliminarProducto() throws SQLException {
        System.out.print("Ingrese el ID del producto a eliminar: ");
        int id = scanner.nextInt();
        productoDAO.eliminar(id);
        System.out.println("Producto eliminado exitosamente!");
    }

    // Método para Ventas
    private void mostrarListaVentas() throws SQLException {
        List<Venta> ventas = ventaDAO.obtenerTodos();
        System.out.println("\nLista de Ventas:");
        for (Venta venta : ventas) {
            // Obtener el producto y su proveedor
            Producto producto = productoDAO.obtenerPorId(venta.getIdProducto());
            String nombreProducto = "Producto no encontrado";
            String infoProveedor = "Proveedor no encontrado";

            if (producto != null) {
                nombreProducto = producto.getMarca() + " " + producto.getModelo();
                Proveedor proveedor = proveedorDAO.obtenerPorId(producto.getIdProveedor());
                if (proveedor != null) {
                    infoProveedor = proveedor.getNombreEmpresa() + " - Tel. Soporte: " + proveedor.getTelefonoSoporte();
                }
            }

            System.out.println("ID Venta: " + venta.getIdVenta() +
                    " | DNI Cliente: " + venta.getDniCliente() +
                    " | Producto: " + venta.getIdProducto() + " - " + nombreProducto +
                    " | Cantidad: " + venta.getCantidad() +
                    " | Total: $" + (venta.getCantidad() * venta.getPrecioUnitario()) +
                    " | Proveedor: " + infoProveedor);
        }
    }

    private void registrarVenta() throws SQLException {
        try {
            System.out.println("\nRegistrar Nueva Venta");
            System.out.print("DNI del cliente: ");
            String dni = scanner.nextLine();
            if (dni.isEmpty()) return;

            // Verificar si el cliente existe
            if (!clienteDAO.existeDni(dni)) {
                System.out.println("Error: Cliente no encontrado");
                return;
            }

            System.out.print("ID del producto: ");
            String idProductoStr = scanner.nextLine();
            if (idProductoStr.isEmpty()) return;
            int idProducto = Integer.parseInt(idProductoStr);

            // Obtener el producto para verificar stock y precio
            Producto producto = productoDAO.obtenerPorId(idProducto);
            if (producto == null) {
                System.out.println("Error: Producto no encontrado");
                return;
            }

            System.out.print("Cantidad: ");
            String cantidadStr = scanner.nextLine();
            if (cantidadStr.isEmpty()) return;
            int cantidad = Integer.parseInt(cantidadStr);

            // Verificar stock disponible
            if (producto.getStock() < cantidad) {
                System.out.println("Error: Stock insuficiente");
                return;
            }

            // Crear y registrar la venta
            Venta venta = new Venta();
            venta.setDniCliente(dni);
            venta.setIdProducto(idProducto);
            venta.setCantidad(cantidad);
            venta.setPrecioUnitario(producto.getPrecioVenta());

            ventaDAO.insertar(venta);

            // Detalle de la venta recién creada
            System.out.println("\nDetalle de la venta registrada:");
            Venta ventaRegistrada = ventaDAO.obtenerUltimaVenta();
            Cliente cliente = clienteDAO.obtenerPorDni(ventaRegistrada.getDniCliente());
            System.out.println("ID Venta: " + ventaRegistrada.getIdVenta());
            System.out.println("Cliente: " + cliente.getNombre());
            System.out.println("Producto: " + producto.getMarca() + " " + producto.getModelo());
            System.out.println("Cantidad: " + ventaRegistrada.getCantidad());
            System.out.println("Precio unitario: $" + ventaRegistrada.getPrecioUnitario());
            System.out.println("Total: $" + (ventaRegistrada.getCantidad() * ventaRegistrada.getPrecioUnitario()));
            System.out.println("\nVenta registrada exitosamente!");
        } catch (NumberFormatException e) {
            System.out.println("Error: Ingrese un número válido");
        }
    }

    private void actualizarVenta() throws SQLException {
        System.out.print("Ingrese el ID de la venta a actualizar: ");
        int idVenta = scanner.nextInt();
        scanner.nextLine();

        Venta venta = ventaDAO.obtenerPorId(idVenta);
        if (venta == null) {
            System.out.println("Venta no encontrada");
            return;
        }

        System.out.print("Nueva cantidad (Enter para mantener actual): ");
        String cantidadStr = scanner.nextLine();
        if (!cantidadStr.isEmpty()) {
            int nuevaCantidad = Integer.parseInt(cantidadStr);
            venta.setCantidad(nuevaCantidad);
        }


        ventaDAO.actualizar(venta);
        System.out.println("Venta actualizada exitosamente!");
    }

    private void eliminarVenta() throws SQLException {
        System.out.print("Ingrese el ID de la venta a eliminar: ");
        int idVenta = scanner.nextInt();
        ventaDAO.eliminar(idVenta);
        System.out.println("Venta eliminada exitosamente!");
    }

    private void crearNuevoProducto() throws SQLException {
        System.out.println("\nCrear Nuevo Producto");

        // Mostrar lista de proveedores disponibles
        System.out.println("\nProveedores disponibles:");
        mostrarListaProveedores();

        System.out.print("\nID del Proveedor: ");
        int idProveedor = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        // Verificar si el proveedor existe
        if (proveedorDAO.obtenerPorId(idProveedor) == null) {
            System.out.println("Error: Proveedor no encontrado");
            return;
        }

        System.out.print("Marca: ");
        String marca = scanner.nextLine();

        System.out.print("Modelo: ");
        String modelo = scanner.nextLine();

        System.out.print("Precio de compra: $");
        double precioCompra = scanner.nextDouble();

        System.out.print("Porcentaje de ganancia: %");
        double porcentajeGanancia = scanner.nextDouble();

        double precioVenta = precioCompra * (1 + porcentajeGanancia/100);

        System.out.print("Stock inicial: ");
        int stock = scanner.nextInt();

        Producto producto = new Producto();
        producto.setIdProveedor(idProveedor);
        producto.setMarca(marca);
        producto.setModelo(modelo);
        producto.setPrecioCompra(precioCompra);
        producto.setPrecioVenta(precioVenta);
        producto.setStock(stock);

        productoDAO.insertar(producto);
        System.out.println("Producto creado exitosamente!");
        System.out.println("Precio de venta establecido: $" + precioVenta);
    }
}
