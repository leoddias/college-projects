/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.pucpcaldas.inf.ori.projeto.core;

/**
 *
 * @author OCTAVIO-NB
 */
import com.lowagie.text.Document;  
import com.lowagie.text.Element;  
import com.lowagie.text.Paragraph;  
import com.lowagie.text.pdf.PdfPCell;  
import com.lowagie.text.pdf.PdfPTable;  
import com.lowagie.text.pdf.PdfWriter;  
import java.awt.Color;  
import java.io.FileOutputStream;  
import javax.swing.JTable;  
  
/** 
* 
* @author wilson 
*/  
public class BaseReport {  
  
    private String titleReport;  
    private String[] nameHeaders;  
    private JTable jTable;    
  
    public BaseReport(String reportTitle, String[] headerNames) {  
        this.titleReport = reportTitle;  
        this.nameHeaders = headerNames;  
    }  
  
    public void runReport(JTable jtable, String pathSaveFile) throws Exception {  
        this.jTable = jtable;  
  
        Document document = new Document();  
        PdfWriter.getInstance(document, new FileOutputStream(pathSaveFile+".pdf"));  
        document.open();  
        document.add(getPdfPTable());  
        document.close();  
  
    }  
  
    private PdfPTable getPdfPTable() throws Exception {  
        PdfPTable tab = new PdfPTable(nameHeaders.length);  
        tab.addCell(getCellTitle());  
  
        for (int i = 0; i < nameHeaders.length; i++) {  
            tab.addCell(nameHeaders[i]);  
        }  
  
        int rowCount = jTable.getRowCount();  
        int collumCount = jTable.getColumnCount();  
        for (int x = 0; x < rowCount; x++) {  
            for (int y = 0; y < collumCount; y++) {  
  
                tab.addCell(GetData(jTable, x, y).toString());  
            }  
  
        }  
        return tab;  
    }  
  
    private Object GetData(JTable table, int row_index, int col_index) throws Exception {  
        return table.getModel().getValueAt(row_index, col_index);  
    }  
  
    private PdfPCell getCellTitle() {  
  
        PdfPCell cell = new PdfPCell(new Paragraph(titleReport));  
        cell.setColspan(4);  
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);  
        cell.setBackgroundColor(Color.LIGHT_GRAY);  
        return cell;  
    }  
}