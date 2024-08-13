package Modelo;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

    public class CabeceraReportes extends PdfPageEventHelper {
    private String encabezado;
    PdfTemplate total;
    public int img1y, img1x;
    public int img2y, img2x;
    public int y, x;
    
     public void onOpenDocument(PdfWriter writer, Document document) {
        total = writer.getDirectContent().createTemplate(30, 16);
    }
    
    @Override
     public void onEndPage(PdfWriter writer, Document document) {
        PdfPTable table = new PdfPTable(3);
        try {
            // Se determina el ancho y altura de la tabla
            table.setWidths(new int[]{24, 24, 2});
            table.setTotalWidth(527);
            table.setLockedWidth(true);
            table.getDefaultCell().setFixedHeight(20);
            Image img = Image.getInstance("src/Recursos/Imagenes/Hubbell.png");
            img.scaleAbsolute(50,50);
            img.setAbsolutePosition(img1x, img1y);
            
            Image img2 = Image.getInstance("src/Recursos/Imagenes/Hubbell.png");
            img2.scaleAbsolute(50,50);
            img2.setAbsolutePosition(img2x, img2y);
            
            float[] medidaCeldas2 = {25, 150,25}; 
            table.setWidths(medidaCeldas2);
            
            // Borde de la celda
            table.getDefaultCell().setBorder(0);
            
            com.itextpdf.text.Font fuente1 = new com.itextpdf.text.Font();
            com.itextpdf.text.Font fuente2 = new com.itextpdf.text.Font();
            
            fuente1.setSize(20);
            fuente1.setFamily("Roboto");
            fuente1.setColor(200,200,200);
            
            fuente2.setSize(14);
            fuente2.setFamily("Roboto");
            fuente2.setColor(200,200,200);
            
            PdfPCell cel = new PdfPCell(new Phrase("Connector Manufacturing",fuente1));
            cel.setBorder(0);
            cel.setHorizontalAlignment(Element.ALIGN_CENTER);
            
            PdfPCell cel1 = new PdfPCell(new Phrase("Hubbell",fuente2));
            cel1.setBorder(0);
            cel1.setHorizontalAlignment(Element.ALIGN_CENTER);
            
//            table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
            
            table.addCell("");
            table.addCell(cel);
            table.addCell("");
            table.addCell("");
            table.addCell(cel1);
            table.addCell("");
            document.add(img);
            document.add(img2);
            
            
            // Esta linea escribe la tabla como encabezado
            table.writeSelectedRows(0, -1, x, y, writer.getDirectContent());
        }
         catch(DocumentException de) {
             throw new ExceptionConverter(de);
        } catch (IOException ex) {
            Logger.getLogger(CabeceraReportes.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "ERROR: "+ex, "ERROR",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void onCloseDocument(PdfWriter writer, Document document) {
        ColumnText.showTextAligned(total, Element.ALIGN_LEFT, new Phrase(String.valueOf(writer.getPageNumber())), 2, 2, 0);
    }    
    
    public String getEncabezado() {
        return encabezado;
    }
    public void setEncabezado(String encabezado) {
        this.encabezado = encabezado;
    }

    public PdfTemplate getTotal() {
        return total;
    }

    public void setTotal(PdfTemplate total) {
        this.total = total;
    }

    public int getImg1y() {
        return img1y;
    }

    public void setImg1y(int img1y) {
        this.img1y = img1y;
    }

    public int getImg1x() {
        return img1x;
    }

    public void setImg1x(int img1x) {
        this.img1x = img1x;
    }

    public int getImg2y() {
        return img2y;
    }

    public void setImg2y(int img2y) {
        this.img2y = img2y;
    }

    public int getImg2x() {
        return img2x;
    }

    public void setImg2x(int img2x) {
        this.img2x = img2x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }
}
