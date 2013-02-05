package gui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JDialog;
import javax.swing.JProgressBar;


public class Exportacao extends JDialog implements Runnable {

	private JPanel jContentPane = null;
	private JProgressBar prgProgresso = null;
	private ArrayList resultado = null;
	private int resultadoAtual = 0; // Usado para atualização da barra de progresso
	private String pathGravacaoArquivos = "";

	public Exportacao(ArrayList resultado) {
		super();
		this.resultado = resultado;
		initialize();
	}

	private void initialize() {
		this.setSize(551, 93);
		this.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
		this.setResizable(false);
		this.setTitle("Exportando...");
		this.setContentPane(getJContentPane());
	}

	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getPrgProgresso(), null);
		}
		return jContentPane;
	}

	private JProgressBar getPrgProgresso() {
		if (prgProgresso == null) {
			prgProgresso = new JProgressBar();
			prgProgresso.setBounds(new java.awt.Rectangle(17,14,508,31));
			prgProgresso.setStringPainted(true);
			prgProgresso.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 12));
		}
		return prgProgresso;
	}
	
	public void iniciarExportacao(String pathGravacaoArquivos) {
		this.pathGravacaoArquivos = pathGravacaoArquivos + "\\";
		
		new Thread(this).start();
		this.setModal(true);
		this.setVisible(true);
		
	}
	public void run() {
		int totalItens = this.resultado.size();
		String strCaminhoArquivoPdf; 
		String strCaminhoArquivoXml;
		String[] numerosAux;
		StringBuffer str;
		
		try {
			
			if (totalItens > 0) {

				prgProgresso.setMinimum(0);
				prgProgresso.setMaximum(totalItens);
				prgProgresso.setValue(0);
				
				// LM 26/01/09 Cometando trecho de código que exporta para PDF
				/*
				// Abrindo o arquivo PDF
				strCaminhoArquivoPdf = this.pathGravacaoArquivos + "Combinacao.pdf";
				arquivo = new File(strCaminhoArquivoPdf);
				if (arquivo.exists()) {
					arquivo.delete();
				}
				
				// Abrindo o PDF
				DocumentoPDF meuPDF = new DocumentoPDF(strCaminhoArquivoPdf);
			    meuPDF.abreDocumento();

				// Adicionando cabeçalho no arquivo PDF
			    meuPDF.adicionaLinhaCentralizada("Resultado da combinação");

				// Adicionando o cabeçalho no arquivo XML
				escreveNoArquivo("<?xml version=\"1.0\" encoding=\"iso-8859-1\"?>", meuArquivo);
				escreveNoArquivo("<combinacoes>", meuArquivo);
				
			    for (int i=0 ; i<totalItens ; i++) {
			    	this.resultadoAtual++;
			    	
				    // Escrevendo no PDF
					meuPDF.adicionaLinha(resultado.get(i).toString());
					
			    	this.prgProgresso.setValue(this.resultadoAtual);
					
					// Escrevendo no arquivo XML
			    	escreveNoArquivo("\t<combinacao>", meuArquivo);
					numerosAux = resultado.get(i).toString().split(",");
					for (int k=0 ; k<numerosAux.length ; k++) {
						str = new StringBuffer();
						str.append("\t\t<numero>");
						str.append(numerosAux[k]);
						str.append("</numero>");
						escreveNoArquivo(str.toString(), meuArquivo);
						str = null;
					}
					escreveNoArquivo("\t</combinacao>", meuArquivo);
				}
			    
			    // Fechando o arquivo XML
			    escreveNoArquivo("</combinacoes>", meuArquivo);
			    meuArquivo.close();
			    
			    // Fechando o arquivo PDF
			    meuPDF.fechaDocumento();
			    */

				// Preparativos para criar o arquivo
				strCaminhoArquivoXml = this.pathGravacaoArquivos + "Combinacao.txt"; 
				File arquivo = new File(strCaminhoArquivoXml);
				if (arquivo.exists()) {
					arquivo.delete();
				}
				// Abrindo o arquivo TXT
				FileOutputStream meuArquivo = new FileOutputStream(strCaminhoArquivoXml, true);

				// Adicionando o cabeçalho no arquivo XML
			    for (int i=0 ; i<totalItens ; i++) {
			    	this.resultadoAtual++;
			    	this.prgProgresso.setValue(this.resultadoAtual);
				    // Escrevendo no arquivo
					escreveNoArquivo(resultado.get(i).toString(), meuArquivo);
				}
			    meuArquivo.close();
				
				//JOptionPane.showMessageDialog(null,"Arquivos gerado com sucesso!","Arquivos PDF e XML",JOptionPane.INFORMATION_MESSAGE);
				JOptionPane.showMessageDialog(null,"Arquivo gerado com sucesso!","Arquivo PDF e XML",JOptionPane.INFORMATION_MESSAGE);
				
				this.setVisible(false);
			}
			
		} catch (FileNotFoundException e) {
			
		} catch (IOException e) {
			
		}
	}
	
	private void escreveNoArquivo(String linha, FileOutputStream arquivo) {
		char pulaLinha = 13;
		char[] caracteres;
		
		try {
			caracteres = linha.toCharArray();
			for (int j=0 ; j<caracteres.length ; j++) {
				arquivo.write((byte)(caracteres[j]));
			}
			pulaLinha = 13; arquivo.write((byte)(pulaLinha));
			pulaLinha = 10; arquivo.write((byte)(pulaLinha));
		} catch (IOException e) {
			
		}
	}

}