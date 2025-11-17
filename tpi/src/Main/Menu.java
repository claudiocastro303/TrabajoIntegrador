package Main;

import Models.Vehiculo;
import Models.SeguroVehicular;
import Service.SeguroServiceImpl;
import Service.VehiculoServiceImpl;
import Dao.SeguroVehicularDAO;
import Dao.VehiculoDAO;
import java.util.Scanner;
import java.util.List;

public class Menu {
    private static Scanner sc = new Scanner(System.in);
    private static SeguroServiceImpl seguroService = new SeguroServiceImpl(new SeguroVehicularDAO());
    private static VehiculoServiceImpl vehiculoService = new VehiculoServiceImpl(new VehiculoDAO());

    public static void main(String[] args) {
        int opcion = -1;
        do {
            try {
                menu();
                opcion = Integer.parseInt(sc.nextLine());
                
                switch (opcion) {
                    case 1 -> crearVehiculoConSeguro();
                    case 2 -> listarVehiculos();
                    case 3 -> actualizarVehiculo();
                    case 4 -> eliminarVehiculo();
                    case 5 -> listarSeguros();
                    case 0 -> System.out.println("Saliendo...");
                    default -> System.out.println("Opción no válida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número.");
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (opcion != 0);
        sc.close();
    }

    public static void menu() {
        System.out.println("\n======== MENU ========");
        System.out.println("1. Crear vehículo");
        System.out.println("2. Listar vehículos");
        System.out.println("3. Actualizar vehículo");
        System.out.println("4. Eliminar vehículo");
        System.out.println("5. Listar seguros");
        System.out.println("0. Salir");
        System.out.print("Ingrese una opción: ");
    }

    public static void crearVehiculoConSeguro() {
        try {
            System.out.print("Dominio: ");
            String dominio = sc.nextLine();
            System.out.print("Marca: ");
            String marca = sc.nextLine();
            System.out.print("Modelo: ");
            String modelo = sc.nextLine();
            System.out.print("Año: ");
            int anio = Integer.parseInt(sc.nextLine());
            
            SeguroVehicular seguro = null;
            System.out.print("¿Desea agregar un seguro? (s/n): ");
            String tieneSeguro = sc.nextLine();
            
            if (tieneSeguro.equalsIgnoreCase("s")) {
                System.out.print("Aseguradora: ");
                String aseguradora = sc.nextLine();
                System.out.print("Número de Póliza: ");
                String nroPoliza = sc.nextLine();
                
                seguro = new SeguroVehicular(0, aseguradora, nroPoliza);
                seguroService.insertar(seguro);
            }
            
            Vehiculo vehiculo = new Vehiculo(0, dominio, marca, modelo, anio, seguro);
            vehiculoService.insertar(vehiculo);
            System.out.println("Vehículo creado exitosamente.");
            
        } catch (Exception e) {
            System.out.println("Error al crear vehículo: " + e.getMessage());
        }
    }

    public static void listarVehiculos() {
        try {
            System.out.println("¿Desea (1) listar todos o (2) buscar por dominio y marca?");
            int subopcion = Integer.parseInt(sc.nextLine());
            
            List<Vehiculo> vehiculos;
            
            if (subopcion == 1) {
                vehiculos = vehiculoService.getAll();
            } else if (subopcion == 2) {
                System.out.print("Ingrese texto a buscar: ");
                String filtro = sc.nextLine();
                vehiculos = vehiculoService.buscarPorDominioMarca(filtro);
            } else {
                System.out.println("Opción inválida.");
                return;
            }
            
            if (vehiculos.isEmpty()) {
                System.out.println("No se encontraron vehículos.");
                return;
            }
            
            for (Vehiculo v : vehiculos) {
                System.out.println("ID: " + v.getId() + 
                                 ", Dominio: " + v.getDominio() + 
                                 ", Marca: " + v.getMarca() + 
                                 ", Modelo: " + v.getModelo());
                if (v.getSeguro() != null) {
                    System.out.println("  Seguro: " + v.getSeguro().getAseguradora() + 
                                     " - Póliza: " + v.getSeguro().getNroPoliza());
                }
            }
            
        } catch (Exception e) {
            System.out.println("Error al listar vehículos: " + e.getMessage());
        }
    }

    public static void actualizarVehiculo() {
        try {
            System.out.print("ID del vehículo a actualizar: ");
            int id = Integer.parseInt(sc.nextLine());
            
            Vehiculo vehiculo = vehiculoService.getById(id);
            if (vehiculo == null) {
                System.out.println("Vehículo no encontrado.");
                return;
            }
            
            // Actualizar datos básicos del vehículo
            System.out.print("Nuevo dominio (" + vehiculo.getDominio() + "): ");
            String dominio = sc.nextLine();
            if (!dominio.isBlank()) vehiculo.setDominio(dominio);
            
            System.out.print("Nueva marca (" + vehiculo.getMarca() + "): ");
            String marca = sc.nextLine();
            if (!marca.isBlank()) vehiculo.setMarca(marca);
            
            System.out.print("Nuevo modelo (" + vehiculo.getModelo() + "): ");
            String modelo = sc.nextLine();
            if (!modelo.isBlank()) vehiculo.setModelo(modelo);
            
            System.out.print("Nuevo año (" + vehiculo.getAnio() + "): ");
            String anioStr = sc.nextLine();
            if (!anioStr.isBlank()) vehiculo.setAnio(Integer.parseInt(anioStr));
            
            // Manejar seguro
            if (vehiculo.getSeguro() != null) {
                System.out.print("¿Desea actualizar el seguro? (s/n): ");
                String actualizarSeg = sc.nextLine();
                if (actualizarSeg.equalsIgnoreCase("s")) {
                    actualizarSeguro(vehiculo.getSeguro());
                    seguroService.actualizar(vehiculo.getSeguro());
                }
            } else {
                System.out.print("¿Desea agregar un seguro? (s/n): ");
                String agregarSeg = sc.nextLine();
                if (agregarSeg.equalsIgnoreCase("s")) {
                    SeguroVehicular nuevoSeguro = crearSeguro();
                    seguroService.insertar(nuevoSeguro);
                    vehiculo.setSeguro(nuevoSeguro);
                }
            }
            
            vehiculoService.actualizar(vehiculo);
            System.out.println("Vehículo actualizado exitosamente.");
            
        } catch (Exception e) {
            System.out.println("Error al actualizar vehículo: " + e.getMessage());
        }
    }

    public static void eliminarVehiculo() {
        try {
            System.out.print("ID del vehículo a eliminar: ");
            int id = Integer.parseInt(sc.nextLine());
            
            Vehiculo vehiculo = vehiculoService.getById(id);
            if (vehiculo == null) {
                System.out.println("Vehículo no encontrado.");
                return;
            }
            
            // Eliminar seguro si existe
            if (vehiculo.getSeguro() != null) {
                seguroService.eliminar(vehiculo.getSeguro());
            }
            
            // Eliminar vehículo
            vehiculoService.eliminar(vehiculo);
            System.out.println("Vehículo eliminado exitosamente.");
            
        } catch (Exception e) {
            System.out.println("Error al eliminar vehículo: " + e.getMessage());
        }
    }

    public static void listarSeguros() {
        try {
            List<SeguroVehicular> seguros = seguroService.getAll();
            
            if (seguros.isEmpty()) {
                System.out.println("No se encontraron seguros.");
                return;
            }
            
            for (SeguroVehicular seguro : seguros) {
                System.out.println("ID: " + seguro.getId() + 
                                 ", Aseguradora: " + seguro.getAseguradora() + 
                                 ", Póliza: " + seguro.getNroPoliza());
            }
            
        } catch (Exception e) {
            System.out.println("Error al listar seguros: " + e.getMessage());
        }
    }

    public static SeguroVehicular crearSeguro() {
        System.out.print("Aseguradora: ");
        String aseguradora = sc.nextLine();
        System.out.print("Número de Póliza: ");
        String nroPoliza = sc.nextLine();
        
        return new SeguroVehicular(0, aseguradora, nroPoliza);
    }

    public static void actualizarSeguro(SeguroVehicular seguro) {
        System.out.print("Nueva aseguradora (" + seguro.getAseguradora() + "): ");
        String aseguradora = sc.nextLine();
        if (!aseguradora.isBlank()) seguro.setAseguradora(aseguradora);
        
        System.out.print("Nuevo número de póliza (" + seguro.getNroPoliza() + "): ");
        String nroPoliza = sc.nextLine();
        if (!nroPoliza.isBlank()) seguro.setNroPoliza(nroPoliza);
    }
}
