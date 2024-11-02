package PDF;

import Modelo.Cliente;
import Modelo.Sistema;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.FontSelector;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import Controlador.Conexion;

public class PDF {

    public static String archivo_boleta = System.getProperty("user.dir") + "/boleta.pdf";
    public static String archivo_factura = System.getProperty("user.dir") + "/factura.pdf";

    public static void boleta() throws Exception {
        Document documento = new Document(PageSize.LETTER, 0, 0, 75, 75);

        PdfWriter writer = null;

        try {
            writer = PdfWriter.getInstance(documento, new FileOutputStream(archivo_boleta));
        } catch (DocumentException ex) {
            ex.getMessage();
        }

        documento.open();

        Paragraph vacio1 = new Paragraph();
        vacio1.add("\n\n");
        documento.add(vacio1);

        Paragraph texto = new Paragraph();
        texto.setAlignment(Paragraph.ALIGN_CENTER);
        texto.setFont(FontFactory.getFont("Times New Roman", 20, Font.BOLD, BaseColor.BLACK));
        texto.add("BOLETA DE VENTA\n\nB001-01");

        try {
            documento.add(texto);
        } catch (DocumentException ex) {
            ex.getMessage();
        }

        texto = new Paragraph();
        texto.add("\n\n");
        documento.add(texto);
        
        texto = new Paragraph();
        texto.setAlignment(Paragraph.ALIGN_LEFT);
        texto.setIndentationLeft(62);
        texto.setFont(FontFactory.getFont("Times New Roman", 10, Font.BOLD, BaseColor.BLACK));
        texto.add("Cliente: " + cliente().get(0).getNombres());
        texto.add("\n");
        texto.add("DNI: " + cliente().get(0).getNumero_Dni());
        texto.add("\n");
        texto.add("Teléfono: " + cliente().get(0).getTelefono());
        texto.add("\n");
        texto.add("Dirección: " + cliente().get(0).getDireccion());

        documento.add(texto);

        texto = new Paragraph();
        texto.add("\n\n");
        documento.add(texto);

        PdfPTable table = new PdfPTable(5);

        ArrayList<String> headers = new ArrayList<>();
        headers.add("Codigo");
        headers.add("Descripcion");
        headers.add("Cantidad");
        headers.add("Precio");
        headers.add("Total");

        estiloHeader(table, headers);

        documento.add(table);

        Conexion conexion = new Conexion();
        ArrayList<Sistema> sistema = conexion.listaSistema();

        double total = 0;

        for (int i = 0; i < sistema.size(); i++) {
            total += sistema.get(i).getTotal();

            table = new PdfPTable(5);
            
            PdfPCell cell = new PdfPCell(new Phrase("" + sistema.get(i).getCodProducto()));
            cell.setPaddingTop(10);
            cell.setPaddingBottom(10);
            cell.setPaddingLeft(10);

            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("" + sistema.get(i).getDescripcion()));
            cell.setPaddingTop(10);
            cell.setPaddingBottom(10);
            cell.setPaddingLeft(10);
            
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("" + sistema.get(i).getCantidad()));
            cell.setPaddingTop(10);
            cell.setPaddingBottom(10);
            cell.setPaddingLeft(10);
            
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("" + sistema.get(i).getPrecio()));
            cell.setPaddingTop(10);
            cell.setPaddingBottom(10);
            cell.setPaddingLeft(10);
            
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("" + sistema.get(i).getTotal()));
            cell.setPaddingTop(10);
            cell.setPaddingBottom(10);
            cell.setPaddingLeft(10);
            
            table.addCell(cell);

            documento.add(table);
        }

        NumberFormat myFormatter = new DecimalFormat("#.##");

        texto = new Paragraph();
        texto.add("\n\n");
        documento.add(texto);

        texto = new Paragraph();
        texto.setAlignment(Paragraph.ALIGN_RIGHT);
        texto.setIndentationRight(62);
        texto.setFont(FontFactory.getFont("Times New Roman", 12, Font.BOLD, BaseColor.BLACK));
        texto.add("Total a pagar: $" + myFormatter.format(total));

        try {
            documento.add(texto);
        } catch (DocumentException ex) {
            ex.getMessage();
        }

        documento.close();
        writer.close();

        Desktop.getDesktop().open(new File(archivo_boleta));
    }

    public static void factura() throws Exception {
        Document documento = new Document(PageSize.LETTER, 0, 0, 75, 75);

        PdfWriter writer = null;

        try {
            writer = PdfWriter.getInstance(documento, new FileOutputStream(archivo_factura));
        } catch (DocumentException ex) {
            ex.getMessage();
        }

        documento.open();

        Paragraph vacio1 = new Paragraph();
        vacio1.add("\n\n");
        documento.add(vacio1);

        Paragraph texto = new Paragraph();
        texto.setAlignment(Paragraph.ALIGN_CENTER);
        texto.setFont(FontFactory.getFont("Times New Roman", 20, Font.BOLD, BaseColor.BLACK));
        texto.add("FACTURA DE VENTA\n\nF001-01");

        try {
            documento.add(texto);
        } catch (DocumentException ex) {
            ex.getMessage();
        }

        texto = new Paragraph();
        texto.add("\n\n");
        documento.add(texto);

        texto = new Paragraph();
        texto.setAlignment(Paragraph.ALIGN_LEFT);
        texto.setIndentationLeft(62);
        texto.setFont(FontFactory.getFont("Times New Roman", 10, Font.BOLD, BaseColor.BLACK));
        texto.add("Cliente: " + cliente().get(0).getNombres());
        texto.add("\n");
        texto.add("RUC: " + cliente().get(0).getNumero_Ruc());
        texto.add("\n");
        texto.add("Teléfono: " + cliente().get(0).getTelefono());
        texto.add("\n");
        texto.add("Dirección: " + cliente().get(0).getDireccion());

        documento.add(texto);

        texto = new Paragraph();
        texto.add("\n\n");
        documento.add(texto);

        PdfPTable table = new PdfPTable(5);

        ArrayList<String> headers = new ArrayList<>();
        headers.add("Codigo");
        headers.add("Descripcion");
        headers.add("Cantidad");
        headers.add("Precio");
        headers.add("Total");

        estiloHeader(table, headers);

        documento.add(table);

        Conexion conexion = new Conexion();
        ArrayList<Sistema> sistema = conexion.listaSistema();

        double total = 0;

        for (int i = 0; i < sistema.size(); i++) {
            total += sistema.get(i).getTotal();

            table = new PdfPTable(5);
            
            PdfPCell cell = new PdfPCell(new Phrase("" + sistema.get(i).getCodProducto()));
            cell.setPaddingTop(10);
            cell.setPaddingBottom(10);
            cell.setPaddingLeft(10);

            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("" + sistema.get(i).getDescripcion()));
            cell.setPaddingTop(10);
            cell.setPaddingBottom(10);
            cell.setPaddingLeft(10);
            
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("" + sistema.get(i).getCantidad()));
            cell.setPaddingTop(10);
            cell.setPaddingBottom(10);
            cell.setPaddingLeft(10);
            
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("" + sistema.get(i).getPrecio()));
            cell.setPaddingTop(10);
            cell.setPaddingBottom(10);
            cell.setPaddingLeft(10);
            
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("" + sistema.get(i).getTotal()));
            cell.setPaddingTop(10);
            cell.setPaddingBottom(10);
            cell.setPaddingLeft(10);
            
            table.addCell(cell);

            documento.add(table);
        }

        NumberFormat myFormatter = new DecimalFormat("#.##");

        texto = new Paragraph();
        texto.add("\n\n");
        documento.add(texto);

        texto = new Paragraph();
        texto.setAlignment(Paragraph.ALIGN_RIGHT);
        texto.setIndentationRight(62);
        texto.setFont(FontFactory.getFont("Times New Roman", 12, Font.BOLD, BaseColor.BLACK));
        texto.add("Total a pagar: $" + myFormatter.format(total));

        try {
            documento.add(texto);
        } catch (DocumentException ex) {
            ex.getMessage();
        }

        documento.close();
        writer.close();

        Desktop.getDesktop().open(new File(archivo_factura));
    }

    public static void estiloHeader(PdfPTable table, ArrayList<String> array) {
        for (String string : array) {
            FontSelector selector = new FontSelector();
            Font font = FontFactory.getFont(FontFactory.defaultEncoding, 14);
            font.setColor(BaseColor.WHITE);

            selector.addFont(font);
            Phrase phrase = selector.process(string);

            PdfPCell header = new PdfPCell(phrase);
            header.setBackgroundColor(BaseColor.GRAY);
            header.setPaddingTop(10);
            header.setPaddingBottom(10);
            header.setPaddingLeft(10);

            table.addCell(header);
        }
    }

    public static ArrayList<Cliente> cliente() {
        ArrayList<Cliente> lista = new ArrayList<>();

        try {
            Conexion conexion = new Conexion();

            lista = conexion.listaCliente();
        } catch (Exception ex) {
            
        }

        return lista;
    }
}
