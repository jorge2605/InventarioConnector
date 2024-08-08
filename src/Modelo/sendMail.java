package Modelo;

import java.util.Properties;
import java.util.Stack;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class sendMail {
    
    public void sendAlmacen(String destinatario, String copia, String asunto, Stack<String> material, Stack<String> cant,  Stack<String> cantR, JFrame f,
            String requi, String oc){
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp-mail.outlook.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        props.put("mail.smtp.ssl.trust", "*");
        
        System.setProperty("mail.debug", "true");
        
        Authenticator auth = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("mensajeriaconnector@outlook.com", "123456789Aa.");
            }
        };
        
        Session session = Session.getInstance(props, auth);

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("mensajeriaconnector@outlook.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
            message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(copia));
            message.setSubject(asunto);
            
            String mat = "";
            for (int i = 0; i < material.size(); i++) {
                mat += "<tr>\n";
                mat += "<td>"+material.get(i)+"</td>\n";
                mat += "<td style=\"text-align:center; \">"+cant.get(i)+"</td>\n";
                mat += "<td style=\"text-align:center; \">"+cantR.get(i)+"</td>\n";
                mat += "</tr>";
                
            }
            
            String htmlContent = "<body style=\"font-family: Arial, sans-serif;\">\n" +
            "\n" +
            "    <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n" +
            "        <tr>\n" +
            "            <td align=\"center\" style=\"background-color: #f5f5f5; padding: 40px;\">\n" +
            "                <table width=\"90%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n" +
            "                    <tr>\n" +
            "                        <td align=\"center\" style=\"background-color: #ffffff; padding: 40px; color: #666666;\">\n" +
            "                            <h1 style=\"font-size: 24px; margin-bottom: 20px; background-color: #9b2f2f; padding: 20px; color: white;\">Recibo de material</h1>\n" +
            "                            <p style=\"color: #666666; font-size: 16px; line-height: 1.5; margin-bottom: 20px; font-weight:700; \">El siguiente material ha llegado a almacen "
                                         + " de la requisicion No. " + requi + " con orden de compra. " + oc + ":</p>\n" + 
            "                            <table border=\"0\" style=\"width: 100%;\">\n" +
            "                               <tr>" +
            "                                   <th style=\"width: 70%; \">Descripcion</th>" +
            "                                   <th>Cantidad</th>" +
            "                                   <th>Cantidad Faltante</th>" +
            "                               </tr>" +
            "                               " + mat +
            "                            </table>\n" +
            "                            <p style=\"color: #666666; font-size: 14px; margin-top: 40px; font-weight:700;\">Gracias,</p>\n" +
            "                            <p style=\"color: #c93900; font-size: 14px; font-weight: 700;\">Servicio de mensajeria Connector</p>\n" +
            "                        </td>\n" +
            "                    </tr>\n" +
            "                </table>\n" +
            "            </td>\n" +
            "        </tr>\n" +
            "    </table>\n" +
            "\n" +
            "</body>";

            // Asignar contenido HTML al cuerpo del mensaje
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(htmlContent, "text/html");
            
            // Combinar las partes en un multiparte
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            
            message.setContent(multipart);
            Transport.send(message);

            JOptionPane.showMessageDialog(f, "Correo enviado correctamente");

        } catch (MessagingException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERROR AL ENVIAR CORREO: "+e,"ERROR",JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
