package gui;

import java.io.FileOutputStream;
import java.io.IOException;
/*
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
*/

/**
 * 
 * @author Leonardo Melo Santos (leonardomelosantos@gmail.com)
 * 
 * Esta classe foi desenvolvida a fim de estudar a cria��o de documentos PDF
 * atrav�s do Java. O conte�do da maioria dos m�todos dessa classe, usa os
 * recursos existente na biblioteca iText. Acredito que o c�digo-fonte desta
 * classe � auto-explicativo.
 *
 */
public class DocumentoPDF {
	/*
    private Document documento;
    private String nomeArquivo;
    
    public DocumentoPDF() {
        
    }
    
    public DocumentoPDF(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }
    
    public void abreDocumento() {
        this.documento = new Document();
        try {
            PdfWriter.getInstance(this.documento, new FileOutputStream(this.nomeArquivo));
            documento.open();
        } catch (IOException ioe) {

        } catch (DocumentException de) {
            
        }
    }
    
    public void fechaDocumento() {
        documento.close();
    }
    
    public void adicionaLinha(String frase) {
        try {
            if (documento.isOpen()) {
                documento.add(new Paragraph(frase));
            }
        } catch (DocumentException de) {
            
        }
    }
    
    public void adicionaLinhaCentralizada(String frase) {
        if (documento.isOpen()) {
            Paragraph titulo = new Paragraph(frase);
            titulo.setAlignment(Paragraph.ALIGN_CENTER);
            try {
                documento.add(titulo);    
            } catch (DocumentException de) {

            }            
        }
    }
    */
}