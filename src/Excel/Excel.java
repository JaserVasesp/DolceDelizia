package Excel;

import Modelo.Cliente;
import Modelo.Sistema;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import Controlador.Conexion;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel {

    public static String archivo_boleta = System.getProperty("user.dir") + "/boleta.xlsx";
    public static String archivo_factura = System.getProperty("user.dir") + "/factura.xlsx";

    public static void boleta() {
        XSSFWorkbook workbook = new XSSFWorkbook();

        XSSFSheet sheet = workbook.createSheet("BOLETA DE VENTA - B001-01");

        Row row = sheet.createRow(0);
        Cell cell = row.createCell(2);
        cell.setCellValue("BOLETA DE VENTA");

        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints(Short.parseShort("14"));
        CellStyle headerCellStyle = sheet.getWorkbook().createCellStyle();
        headerCellStyle.setFont(headerFont);
        headerCellStyle.setAlignment(HorizontalAlignment.CENTER);
        cell.setCellStyle(headerCellStyle);

        row = sheet.createRow(1);
        cell = row.createCell(2);
        cell.setCellValue("B001-01");

        headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints(Short.parseShort("14"));
        headerCellStyle = sheet.getWorkbook().createCellStyle();
        headerCellStyle.setFont(headerFont);
        headerCellStyle.setAlignment(HorizontalAlignment.CENTER);
        cell.setCellStyle(headerCellStyle);

        row = sheet.createRow(3);
        cell = row.createCell(0);
        cell.setCellValue("Cliente: ");
        headerFont = workbook.createFont();

        headerFont.setBold(true);
        headerCellStyle = sheet.getWorkbook().createCellStyle();
        headerCellStyle.setFont(headerFont);
        cell.setCellStyle(headerCellStyle);

        cell = row.createCell(1);
        cell.setCellValue(cliente().get(0).getNombres());

        row = sheet.createRow(4);
        cell = row.createCell(0);
        cell.setCellValue("RUC: ");

        headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerCellStyle = sheet.getWorkbook().createCellStyle();
        headerCellStyle.setFont(headerFont);
        cell.setCellStyle(headerCellStyle);

        cell = row.createCell(1);
        cell.setCellValue(cliente().get(0).getNumero_Ruc());

        row = sheet.createRow(5);
        cell = row.createCell(0);
        cell.setCellValue("Teléfono: ");

        headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerCellStyle = sheet.getWorkbook().createCellStyle();
        headerCellStyle.setFont(headerFont);
        cell.setCellStyle(headerCellStyle);

        cell = row.createCell(1);
        cell.setCellValue(cliente().get(0).getTelefono());

        row = sheet.createRow(6);
        cell = row.createCell(0);
        cell.setCellValue("Dirección: ");

        headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerCellStyle = sheet.getWorkbook().createCellStyle();
        headerCellStyle.setFont(headerFont);
        cell.setCellStyle(headerCellStyle);

        cell = row.createCell(1);
        cell.setCellValue(cliente().get(0).getDireccion());

        headerCellStyle = estiloCelda(workbook, sheet);

        Map<String, Object[]> data = new TreeMap<>();
        data.put("", new Object[]{"Codigo", "Descripcion", "Cantidad", "Precio", "Total"});

        Conexion conexion = new Conexion();
        ArrayList<Sistema> sistema;

        double total = 0;

        try {
            sistema = conexion.listaSistema();

            for (int i = 0; i < sistema.size(); i++) {
                total += sistema.get(i).getTotal();

                data.put("" + (i + 2), new Object[]{
                    sistema.get(i).getCodProducto(),
                    sistema.get(i).getDescripcion(),
                    "" + sistema.get(i).getCantidad(),
                    "" + sistema.get(i).getPrecio(),
                    "" + sistema.get(i).getTotal()
                });
            }
        } catch (Exception ex) {
            Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
        }

        Set<String> keyset = data.keySet();
        int rownum = 8;
        for (String key : keyset) {
            row = sheet.createRow(rownum++);
            Object[] objArr = data.get(key);
            int cellnum = 0;
            for (Object obj : objArr) {
                cell = row.createCell(cellnum++);

                if (rownum == 9) {
                    cell.setCellStyle(headerCellStyle);
                }

                if (obj instanceof String) {
                    cell.setCellValue((String) obj);
                } else if (obj instanceof Integer) {
                    cell.setCellValue((Integer) obj);
                }
            }
        }

        rownum += 1;

        NumberFormat myFormatter = new DecimalFormat("#.##");

        row = sheet.createRow(rownum);
        cell = row.createCell(3);
        cell.setCellValue("Total a pagar:");

        headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerCellStyle = sheet.getWorkbook().createCellStyle();
        headerCellStyle.setFont(headerFont);
        cell.setCellStyle(headerCellStyle);

        cell = row.createCell(4);
        cell.setCellValue("$" + myFormatter.format(total));

        headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerCellStyle = sheet.getWorkbook().createCellStyle();
        headerCellStyle.setFont(headerFont);
        cell.setCellStyle(headerCellStyle);

        workbook.getSheetAt(0).autoSizeColumn(3);
        workbook.getSheetAt(0).autoSizeColumn(4);

        row = workbook.getSheetAt(0).getRow(0);

        for (int column = 0; column < row.getLastCellNum(); column++) {
            workbook.getSheetAt(0).autoSizeColumn(column);
        }

        try {
            try (FileOutputStream out = new FileOutputStream(new File(archivo_boleta))) {
                workbook.write(out);
            }

            Desktop.getDesktop().open(new File(archivo_boleta));
        } catch (IOException e) {
        }
    }

    public static void factura() {
        XSSFWorkbook workbook = new XSSFWorkbook();

        XSSFSheet sheet = workbook.createSheet("FACTURA DE VENTA - F001-01");

        Row row = sheet.createRow(0);
        Cell cell = row.createCell(2);
        cell.setCellValue("FACTURA DE VENTA");

        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints(Short.parseShort("14"));
        CellStyle headerCellStyle = sheet.getWorkbook().createCellStyle();
        headerCellStyle.setFont(headerFont);
        headerCellStyle.setAlignment(HorizontalAlignment.CENTER);
        cell.setCellStyle(headerCellStyle);

        row = sheet.createRow(1);
        cell = row.createCell(2);
        cell.setCellValue("F001-01");

        headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints(Short.parseShort("14"));
        headerCellStyle = sheet.getWorkbook().createCellStyle();
        headerCellStyle.setFont(headerFont);
        headerCellStyle.setAlignment(HorizontalAlignment.CENTER);
        cell.setCellStyle(headerCellStyle);

        row = sheet.createRow(3);
        cell = row.createCell(0);
        cell.setCellValue("Cliente: ");
        headerFont = workbook.createFont();

        headerFont.setBold(true);
        headerCellStyle = sheet.getWorkbook().createCellStyle();
        headerCellStyle.setFont(headerFont);
        cell.setCellStyle(headerCellStyle);

        cell = row.createCell(1);
        cell.setCellValue(cliente().get(0).getNombres());

        row = sheet.createRow(4);
        cell = row.createCell(0);
        cell.setCellValue("RUC: ");

        headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerCellStyle = sheet.getWorkbook().createCellStyle();
        headerCellStyle.setFont(headerFont);
        cell.setCellStyle(headerCellStyle);

        cell = row.createCell(1);
        cell.setCellValue(cliente().get(0).getNumero_Ruc());

        row = sheet.createRow(5);
        cell = row.createCell(0);
        cell.setCellValue("Teléfono: ");

        headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerCellStyle = sheet.getWorkbook().createCellStyle();
        headerCellStyle.setFont(headerFont);
        cell.setCellStyle(headerCellStyle);

        cell = row.createCell(1);
        cell.setCellValue(cliente().get(0).getTelefono());

        row = sheet.createRow(6);
        cell = row.createCell(0);
        cell.setCellValue("Dirección: ");

        headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerCellStyle = sheet.getWorkbook().createCellStyle();
        headerCellStyle.setFont(headerFont);
        cell.setCellStyle(headerCellStyle);

        cell = row.createCell(1);
        cell.setCellValue(cliente().get(0).getDireccion());

        headerCellStyle = estiloCelda(workbook, sheet);

        Map<String, Object[]> data = new TreeMap<>();
        data.put("", new Object[]{"Codigo", "Descripcion", "Cantidad", "Precio", "Total"});

        Conexion conexion = new Conexion();
        ArrayList<Sistema> sistema;

        double total = 0;

        try {
            sistema = conexion.listaSistema();

            for (int i = 0; i < sistema.size(); i++) {
                total += sistema.get(i).getTotal();

                data.put("" + (i + 2), new Object[]{
                    sistema.get(i).getCodProducto(),
                    sistema.get(i).getDescripcion(),
                    "" + sistema.get(i).getCantidad(),
                    "" + sistema.get(i).getPrecio(),
                    "" + sistema.get(i).getTotal()
                });
            }
        } catch (Exception ex) {
            Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
        }

        Set<String> keyset = data.keySet();
        int rownum = 8;
        for (String key : keyset) {
            row = sheet.createRow(rownum++);
            Object[] objArr = data.get(key);
            int cellnum = 0;
            for (Object obj : objArr) {
                cell = row.createCell(cellnum++);

                if (rownum == 9) {
                    cell.setCellStyle(headerCellStyle);
                }

                if (obj instanceof String) {
                    cell.setCellValue((String) obj);
                } else if (obj instanceof Integer) {
                    cell.setCellValue((Integer) obj);
                }
            }
        }

        rownum += 1;

        NumberFormat myFormatter = new DecimalFormat("#.##");

        row = sheet.createRow(rownum);
        cell = row.createCell(3);
        cell.setCellValue("Total a pagar:");

        headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerCellStyle = sheet.getWorkbook().createCellStyle();
        headerCellStyle.setFont(headerFont);
        cell.setCellStyle(headerCellStyle);

        cell = row.createCell(4);
        cell.setCellValue("$" + myFormatter.format(total));

        headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerCellStyle = sheet.getWorkbook().createCellStyle();
        headerCellStyle.setFont(headerFont);
        cell.setCellStyle(headerCellStyle);

        workbook.getSheetAt(0).autoSizeColumn(3);
        workbook.getSheetAt(0).autoSizeColumn(4);

        row = workbook.getSheetAt(0).getRow(0);

        for (int column = 0; column < row.getLastCellNum(); column++) {
            workbook.getSheetAt(0).autoSizeColumn(column);
        }

        try {
            try (FileOutputStream out = new FileOutputStream(new File(archivo_factura))) {
                workbook.write(out);
            }

            Desktop.getDesktop().open(new File(archivo_factura));
        } catch (IOException e) {
        }
    }

    public static CellStyle estiloCelda(XSSFWorkbook workbook, XSSFSheet sheet) {
        Font headerFont = workbook.createFont();
        headerFont.setColor(IndexedColors.WHITE.index);
        CellStyle headerCellStyle = sheet.getWorkbook().createCellStyle();
        headerCellStyle.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.index);
        headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerCellStyle.setFont(headerFont);

        return headerCellStyle;
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
