import java.util.Scanner;

public class Main { // ANTONIO GARCÍA CLAVEL
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String fecha;
        String diagnostico;
        int id = 0; // Inicializo con un valor positivo

        try {
            System.out.println("Introduce la fecha de la consulta con el formato correcto (YYYY-MM-DD): ");
            fecha = scanner.nextLine();

            System.out.println("Introduce el diagnóstico: ");
            diagnostico = scanner.nextLine();

            System.out.println("Introduce el ID de la consulta (que sea un número positivo): ");
            String idInput = scanner.nextLine();

            try {
                id = Integer.parseInt(idInput);
                if (id <= 0) {
                    throw new IllegalArgumentException("El ID debe ser un número positivo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: El ID debe ser un número válido.");
                return; // Aquí termino el programa si hay algún error.
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
                return; // Vuelvo a terminar el programa si hay error
            }

            // Creamos el objeto
            Consulta consulta = new Consulta(fecha, diagnostico, id);

            // Mostramos el objeto
            System.out.println("Consulta agregada correctamente:");
            System.out.println(consulta);

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }


        scanner.close();
    }
}
