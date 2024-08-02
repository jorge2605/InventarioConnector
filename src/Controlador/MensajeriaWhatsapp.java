package Controlador;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;

public class MensajeriaWhatsapp {
    
    public void sendMessage(String celular, String PO, String requi, String token){
        try{
            String url = "https://graph.facebook.com/v20.0/386062914588815/messages";
            String accessToken = token;
            String recipientPhoneNumber = celular;

            Map<String, Object> body = new HashMap<>();
            body.put("messaging_product", "whatsapp");
            body.put("to", recipientPhoneNumber);
            body.put("type", "template");

            Map<String, Object> template = new HashMap<>();
            template.put("name", "recibo_de_material");
            template.put("language", Map.of("code", "en_US"));

            Map<String, Object> components = new HashMap<>();
            components.put("type", "body");
            components.put("parameters", new Object[] {
                Map.of("type", "text", "text", PO),
                Map.of("type", "text", "text", requi)
            });

            template.put("components", new Map[]{components});
            body.put("template", template);

            ObjectMapper objectMapper = new ObjectMapper();
            String requestBody = objectMapper.writeValueAsString(body);

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Authorization", "Bearer " + accessToken)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println("Status code: " + response.statusCode());
            System.out.println("Response body: " + response.body());
            
            if(response.statusCode() != 200){
                JOptionPane.showMessageDialog(null, "El mensaje no se entrego correctamente\n "+response.body(),"Error",JOptionPane.ERROR_MESSAGE);
            }
        }catch(Exception e){
            
        }
    }
}
