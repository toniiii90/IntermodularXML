import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<Consulta> consultas = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion = 0;
        do {
            System.out.println("\nMenú de Gestión de Consultas:");
            System.out.println("1. Insertar consulta");
            System.out.println("2. Eliminar consulta");
            System.out.println("3. Actualizar consulta");
            System.out.println("4. Consultar una consulta");
            System.out.println("5. Generar lista de consultas en XML");
            System.out.println("6. Salir");
            System.out.print("Elige una opción: ");

            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
                scanner.nextLine(); // Consumir nueva línea
            } else {
                System.out.println("Error: Debes ingresar un número válido.");
                scanner.nextLine(); // Limpiar buffer
                continue;
            }

            switch (opcion) {
                case 1:
                    insertarConsulta();
                    break;
                case 2:
                    eliminarConsulta();
                    break;
                case 3:
                    actualizarConsulta();
                    break;
                case 4:
                    consultarConsulta();
                    break;
                case 5:
                    generarXML();
                    break;
                case 6:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 6);
    }

    private static void insertarConsulta() {
        try {
            String fecha;
            do {
                System.out.print("Introduce la fecha (YYYY-MM-DD): ");
                fecha = scanner.nextLine();
            } while (!fecha.matches("\\d{4}-\\d{2}-\\d{2}"));

            String diagnostico;
            do {
                System.out.print("Introduce el diagnóstico (no debe ser numérico ni estar vacío): ");
                diagnostico = scanner.nextLine();
            } while (diagnostico.isEmpty() || diagnostico.matches(".*\\d.*"));

            int id;
            while (true) {
                System.out.print("Introduce el ID (número positivo): ");
                if (scanner.hasNextInt()) {
                    id = scanner.nextInt();
                    scanner.nextLine(); // Consumir nueva línea
                    if (id > 0) break;
                    System.out.println("Error: El ID debe ser un número positivo.");
                } else {
                    System.out.println("Error: Debes ingresar un número válido.");
                    scanner.next(); // Limpiar entrada incorrecta
                }
            }

            Consulta nuevaConsulta = new Consulta(fecha, diagnostico, id);
            consultas.add(nuevaConsulta);
            System.out.println("Consulta añadida correctamente.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            scanner.nextLine(); // Limpiar buffer
        }
    }

    private static void eliminarConsulta() {
        int id;
        while (true) {
            System.out.print("Introduce el ID de la consulta a eliminar: ");
            if (scanner.hasNextInt()) {
                id = scanner.nextInt();
                scanner.nextLine(); // Consumir nueva línea
                break;
            } else {
                System.out.println("Error: Debes ingresar un número válido.");
                scanner.next(); // Limpiar entrada incorrecta
            }
        }

        consultas.removeIf(consulta -> consulta.getId_consulta() == id);
        System.out.println("Consulta eliminada si existía.");
    }

    private static void actualizarConsulta() {
        int id;
        while (true) {
            System.out.print("Introduce el ID de la consulta a actualizar: ");
            if (scanner.hasNextInt()) {
                id = scanner.nextInt();
                scanner.nextLine(); // Consumir nueva línea
                break;
            } else {
                System.out.println("Error: Debes ingresar un número válido.");
                scanner.next(); // Limpiar entrada incorrecta
            }
        }

        for (Consulta consulta : consultas) {
            if (consulta.getId_consulta() == id) {
                String fecha;
                do {
                    System.out.print("Nueva fecha (YYYY-MM-DD): ");
                    fecha = scanner.nextLine();
                } while (!fecha.matches("\\d{4}-\\d{2}-\\d{2}"));

                String diagnostico;
                do {
                    System.out.print("Nuevo diagnóstico (no debe ser numérico ni estar vacío): ");
                    diagnostico = scanner.nextLine();
                } while (diagnostico.isEmpty() || diagnostico.matches(".*\\d.*"));

                consulta.setFechaCon(fecha);
                consulta.setDiagnostico(diagnostico);

                System.out.println("Consulta actualizada correctamente.");
                return;
            }
        }
        System.out.println("No se encontró la consulta con ese ID.");
    }

    private static void consultarConsulta() {
        int id;
        while (true) {
            System.out.print("Introduce el ID de la consulta a buscar: ");
            if (scanner.hasNextInt()) {
                id = scanner.nextInt();
                scanner.nextLine(); // Consumir nueva línea
                break;
            } else {
                System.out.println("Error: Debes ingresar un número válido.");
                scanner.next(); // Limpiar entrada incorrecta
            }
        }

        for (Consulta consulta : consultas) {
            if (consulta.getId_consulta() == id) {
                System.out.println("Consulta encontrada:");
                System.out.println(consulta);
                return;
            }
        }
        System.out.println("Consulta no encontrada.");
    }

    private static void generarXML() {
        try (FileWriter writer = new FileWriter("consultas.txt")) {
            writer.write("<Consultas>\n");
            for (Consulta consulta : consultas) {
                writer.write(consulta.toString() + "\n");
            }
            writer.write("</Consultas>");
            System.out.println("Archivo generado correctamente.");
        } catch (IOException e) {
            System.out.println("Error al generar el archivo .txt: " + e.getMessage());
        }
    }
}
