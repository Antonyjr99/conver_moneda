import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConversorDeMoneda {
    private static final String API_KEY = "5a60444f5f0788e20dda1fc9";
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/";

    /**
     * Convierte una cantidad de una moneda a otra utilizando las tasas de cambio.
     *
     * @param cantidad      La cantidad que se desea convertir.
     * @param monedaOrigen  El código de la moneda de origen (por ejemplo, USD).
     * @param monedaDestino El código de la moneda de destino (por ejemplo, EUR).
     * @return El valor convertido o -1 en caso de error.
     */
    public double convertir(double cantidad, String monedaOrigen, String monedaDestino) {
        try {
            // Construir la URL de la API
            String apiUrl = BASE_URL + API_KEY + "/latest/" + monedaOrigen;
            URL url = new URL(apiUrl);

            // Hacer la solicitud HTTP
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                // Leer la respuesta de la API
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // Extraer la tasa de cambio de la respuesta
                return calcularConversion(response.toString(), cantidad, monedaDestino);
            } else {
                System.out.println("Error: Código de respuesta " + responseCode);
                return -1;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * Calcula la conversión utilizando la respuesta JSON de la API.
     *
     * @param jsonResponse  La respuesta JSON de la API.
     * @param cantidad      La cantidad a convertir.
     * @param monedaDestino El código de la moneda de destino.
     * @return El valor convertido o -1 si no se encuentra la moneda.
     */
    private double calcularConversion(String jsonResponse, double cantidad, String monedaDestino) {
        try {
            // Utilizar Gson para analizar la respuesta
            JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();
            JsonObject conversionRates = jsonObject.getAsJsonObject("conversion_rates");

            if (conversionRates.has(monedaDestino)) {
                double tasaDeCambio = conversionRates.get(monedaDestino).getAsDouble();
                return cantidad * tasaDeCambio;
            } else {
                System.out.println("Error: Moneda destino no encontrada.");
                return -1;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}
