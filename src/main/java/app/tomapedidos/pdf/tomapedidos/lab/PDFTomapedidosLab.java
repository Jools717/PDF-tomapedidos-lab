package app.tomapedidos.pdf.tomapedidos.lab;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.FileOutputStream;

import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PDFTomapedidosLab {

   public static void main(String[] args) {
        String htmlFile = "C:/Users/julianorjuela/Documents/toma-pedidos/Encargos empresa española/pdf-españoles/invoice-template-mussa-3.html";
        String pdfFile = "C:/Users/julianorjuela/Documents/toma-pedidos/Encargos empresa española/pdf-españoles/pedido2.pdf";

        try {
            String htmlcontent = new String(Files.readAllBytes(Paths.get(htmlFile)));
            System.out.println("htmlcontent"+htmlcontent);
            // Crear el renderizador de HTML a PDF
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(htmlcontent);

            // Renderizar el PDF
            renderer.layout();
            try (FileOutputStream fos = new FileOutputStream(pdfFile)) {
                renderer.createPDF(fos);
            }

            System.out.println("PDF generado con éxito en: " + pdfFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }    
    
    
//    public static void main(String[] args) {
//        String nombreArchivo = "pedido.pdf";
//
//        try {
//            // Crear el documento
//            Document document = new Document();
//            PdfWriter.getInstance(document, new FileOutputStream(nombreArchivo));
//
//            // Abrir el documento para escribir
//            document.open();
//
//            // Agregar contenido al PDF
//            agregarContenido(document);
//
//            // Cerrar el documento
//            document.close();
//
//            System.out.println("PDF generado con éxito en: " + nombreArchivo);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    private static void agregarContenido(Document document) throws DocumentException {
        // Agregar título
        Paragraph titulo = new Paragraph("Factura de Pedido");
        titulo.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(titulo);

        // Agregar información del pedido
        document.add(new Paragraph("\nInformación del Pedido:"));
        document.add(new Paragraph("Número de Pedido: 123456"));
        document.add(new Paragraph("Fecha: 2024-01-23"));
        document.add(new Paragraph("Cliente: Juan Pérez"));

        // Agregar detalles del pedido (puedes personalizar esto según tus necesidades)
        document.add(new Paragraph("\nDetalles del Pedido:"));
        document.add(new Paragraph("1. Producto A - Cantidad: 2 - Precio Unitario: $10"));
        document.add(new Paragraph("2. Producto B - Cantidad: 1 - Precio Unitario: $20"));

        // Calcular el total del pedido y agregarlo
        double total = 2 * 10 + 1 * 20; // Ejemplo simple, deberías calcularlo según tu lógica
        document.add(new Paragraph("\nTotal: $" + total));
    }
}
