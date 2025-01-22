import java.text.DecimalFormat;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Crear una instancia de la clase ConversorDeMoneda
        ConversorDeMoneda conversor = new ConversorDeMoneda();

        // Crear un objeto Scanner para leer entradas desde la consola
        Scanner scanner = new Scanner(System.in);
        int opcion;

        // Crear un objeto DecimalFormat para redondear a 2 decimales
        DecimalFormat df = new DecimalFormat("#.##");

        do {
            // Mostrar el menú de opciones
            System.out.println("Seleccione una opción:");
            System.out.println("1) Dólar a Peso Argentino");
            System.out.println("2) Peso Argentino a Dólar");
            System.out.println("3) Dólar a Real Brasileño");
            System.out.println("4) Real Brasileño a Dólar");
            System.out.println("5) Dólar a Peso Colombiano");
            System.out.println("6) Peso Colombiano a Dólar");
            System.out.println("7) Salir");

            // Leer la opción seleccionada
            System.out.print("Elija una opcion valida: ");
            opcion = scanner.nextInt();

            if (opcion == 7) {
                System.out.println("¡Hasta luego!");
                break;  // Salir del bucle
            }

            // Solicitar el monto a convertir
            System.out.print("Ingrese el monto: ");
            double monto = scanner.nextDouble();

            // Llamar al método de conversión según la opción seleccionada
            double resultado = 0;
            String monedaDestino = "";
            String monedaOrigen = "";

            switch (opcion) {
                case 1:
                    monedaOrigen = "USD";  // Dólar
                    monedaDestino = "ARS";  // Peso Argentino
                    resultado = conversor.convertir(monto, monedaOrigen, monedaDestino);
                    break;
                case 2:
                    monedaOrigen = "ARS";  // Peso Argentino
                    monedaDestino = "USD";  // Dólar
                    resultado = conversor.convertir(monto, monedaOrigen, monedaDestino);
                    break;
                case 3:
                    monedaOrigen = "USD";  // Dólar
                    monedaDestino = "BRL";  // Real Brasileño
                    resultado = conversor.convertir(monto, monedaOrigen, monedaDestino);
                    break;
                case 4:
                    monedaOrigen = "BRL";  // Real Brasileño
                    monedaDestino = "USD";  // Dólar
                    resultado = conversor.convertir(monto, monedaOrigen, monedaDestino);
                    break;
                case 5:
                    monedaOrigen = "USD";  // Dólar
                    monedaDestino = "COP";  // Peso Colombiano
                    resultado = conversor.convertir(monto, monedaOrigen, monedaDestino);
                    break;
                case 6:
                    monedaOrigen = "COP";  // Peso Colombiano
                    monedaDestino = "USD";  // Dólar
                    resultado = conversor.convertir(monto, monedaOrigen, monedaDestino);
                    break;
                default:
                    System.out.println("Opción no válida. Por favor intente de nuevo.");
                    continue;  // Volver a mostrar el menú si la opción es inválida
            }

            // Mostrar el resultado de la conversión, redondeado a 2 decimales
            if (resultado != -1) {
                System.out.println("El resultado de la conversión es: " + df.format(resultado) + " " + monedaDestino);
            } else {
                System.out.println("Hubo un error al realizar la conversión.");
            }

        } while (opcion != 7);  // Continuar hasta que se elija la opción de salir

        // Cerrar el escáner
        scanner.close();
    }
}
