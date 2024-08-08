package Controlador;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.swing.JOptionPane;

public class WhatsAppMessageSimple {
    private final String API_URL = "https://graph.facebook.com/v20.0/386062914588815/messages";
    public String ACCESS_TOKEN;

    public void send(String numero, String mensaje) throws Exception {
        String recipientPhoneNumber = numero;
        sendTextMessage(recipientPhoneNumber, mensaje);
    }

    private void sendTextMessage(String recipientPhoneNumber, String messageBody) throws Exception {
        Map<String, Object> body = new HashMap<>();
        body.put("messaging_product", "whatsapp");
        body.put("to", recipientPhoneNumber);
        body.put("type", "text");

        Map<String, String> text = new HashMap<>();
        text.put("body", messageBody);
        body.put("text", text);

        sendHttpRequest(body);
    }

    private void sendHttpRequest(Map<String, Object> body) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(body);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .header("Authorization", "Bearer " + ACCESS_TOKEN)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Status code: " + response.statusCode());
        System.out.println("Response body: " + response.body());
        if(response.statusCode() != 200){
            JOptionPane.showMessageDialog(null, "No se envio mensaje: " + response.body(),"Error",JOptionPane.ERROR_MESSAGE);
        }
    }
}
