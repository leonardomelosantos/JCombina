package gui;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.UIManager;

import combina.Combina;
import combina.NenhumaPalavraInformadaException;
import combina.NumeroCasasMaiorException;
import java.awt.List;
import java.io.File;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.awt.Font;

public class Janela extends JDialog implements Runnable {

	private JPanel jContentPane = null;
	private JButton btnCombinar = null;
	private JButton btnExportar = null;
	private JTextField txtPalavrasPossiveis = null;
	private JTextField txtCasas = null;
	private JLabel lblPalavras = null;
	private JLabel lblCasas = null;
	private List lstCombinacao = null;
	private JLabel lblTotal = null;
	private JLabel lblAutor = null;
	private JLabel lblDataHora = null;
	private ArrayList resultado = null;
	private JLabel lblWebsite = null;

	public Janela() {
		super();
		initialize();
		new Thread(this).start();
	}

	private void initialize() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			System.out.println("Erro na seleção do visual do sistema");
		}
		this.setSize(622, 185);
		this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		this.setContentPane(getJContentPane());
		this.setTitle("Combinação de símbolos, sem repetição - v1.1");
		this.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent e) {
				System.exit(0);
			}
		});
	}

	private JPanel getJContentPane() {
		if (jContentPane == null) {
			lblWebsite = new JLabel();
			lblWebsite.setBounds(new java.awt.Rectangle(12, 100, 235, 13));
			lblWebsite.setText("http://www.leonardomelosantos.com.br");
			lblWebsite.setFont(new Font("Arial", Font.BOLD, 10));
			lblDataHora = new JLabel();
			lblDataHora.setBounds(new java.awt.Rectangle(12, 115, 104, 17));
			lblDataHora.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 10));
			lblDataHora.setText(this.pegaDataHora());
			lblAutor = new JLabel();
			lblAutor.setBounds(new java.awt.Rectangle(12, 130, 143, 16));
			lblAutor.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 10));
			lblAutor.setText("Leonardo Melo Santos");
			lblTotal = new JLabel();
			lblTotal.setBounds(new java.awt.Rectangle(13, 75, 590, 21));
			lblTotal.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 12));
			lblTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
			lblTotal.setText("");
			lblCasas = new JLabel();
			lblCasas.setBounds(new java.awt.Rectangle(516, 12, 84, 15));
			lblCasas.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 12));
			lblCasas.setText("Nº de casas:");
			lblPalavras = new JLabel();
			lblPalavras.setBounds(new java.awt.Rectangle(12, 12, 395, 14));
			lblPalavras.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 12));
			lblPalavras.setText("Possíveis elementos da combinação (use a vírgula como separador):");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getBtnCombinar(), null);
			jContentPane.add(getBtnExportar(), null);
			jContentPane.add(getTxtPalavrasPossiveis(), null);
			jContentPane.add(getTxtCasas(), null);
			jContentPane.add(lblPalavras, null);
			jContentPane.add(lblCasas, null);
			//jContentPane.add(getLstCombinacao(), null);
			jContentPane.add(lblTotal, null);
			jContentPane.add(lblAutor, null);
			jContentPane.add(lblDataHora, null);
			jContentPane.add(lblWebsite, null);
		}
		return jContentPane;
	}

	private JButton getBtnCombinar() {
		if (btnCombinar == null) {
			btnCombinar = new JButton();
			btnCombinar.setBounds(new java.awt.Rectangle(342, 100, 127, 41));
			btnCombinar.setText("Combinar");
			btnCombinar.setIcon(new ImageIcon(getClass().getResource("/gui/kpackage.png")));
			btnCombinar.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 12));
			btnCombinar.setName("");
			btnCombinar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {

					try {
						int numeroCasas = 0;
						double tempoGastoEmSegundos = 0.0;
						boolean existemSimbolosRepetidos = false;
						ArrayList caracteres = new ArrayList();
						ArrayList arrFinal = new ArrayList();

						try {
							numeroCasas = Integer.parseInt(txtCasas.getText());
						} catch (NumberFormatException e1) {
							JOptionPane.showMessageDialog(null,
									"Número de casas inválido!", "JCombina",
									JOptionPane.WARNING_MESSAGE);
						}

						if (numeroCasas > 0) {

							lblTotal.setText("Combinando...");
							lblTotal.repaint();

							int j = 0;
							String strCaracteresSeparados[];
							strCaracteresSeparados = txtPalavrasPossiveis.getText().split(",");
							for (j = 0; j < strCaracteresSeparados.length; j++) {
								if (caracteres.contains(strCaracteresSeparados[j])) {
									existemSimbolosRepetidos = true;
								} else {
									if (!strCaracteresSeparados[j].equals("")) {
										caracteres.add(strCaracteresSeparados[j]);
									}
								}
							}

							// Executando o método que faz a combinação
							try {
								Combina combina = new Combina(caracteres, numeroCasas);
								combina.combinar(arrFinal = new ArrayList());
								tempoGastoEmSegundos = combina.getTempoGasto() / 1000;
								lblTotal.setText("Total de combinações: " +
										Integer.toString(arrFinal.size()) + " (" +
										tempoGastoEmSegundos + " s)");

								JFileChooser chooser = new JFileChooser();
								chooser.setDialogType(JFileChooser.OPEN_DIALOG);
								chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
								chooser.showDialog(null, null);

								File theFile = chooser.getSelectedFile();
								if (theFile != null) {
									if (theFile.isDirectory()) {
										Exportacao exportacao = new Exportacao(arrFinal);
										exportacao.iniciarExportacao(theFile.getPath());
									}
								}

								// LM 26/01/09
								// Mostrando o resultado para o usuário
								/*
								 * lstCombinacao.removeAll();
								 * btnExportar.setEnabled(false); resultado =
								 * arrFinal; for (int i = 0 ; i <
								 * arrFinal.size() ; i++) {
								 * lstCombinacao.add(arrFinal
								 * .get(i).toString()); }
								 * 
								 * lblTotal.setText("Total de combinações: " +
								 * Integer.toString(arrFinal.size()) + "(" +
								 * tempoGastoEmSegundos + " s)"); if
								 * (arrFinal.size() > 0) {
								 * btnExportar.setEnabled(true); } else {
								 * btnExportar.setEnabled(false); }
								 */

								if (existemSimbolosRepetidos) {
									JOptionPane.showMessageDialog(
													null,
													"Um ou mais símbolos repetidos foram removidos.",
													"Combina",
													JOptionPane.INFORMATION_MESSAGE);
								}

							} catch (NumeroCasasMaiorException ex) {
								JOptionPane.showMessageDialog(null, ex.getMessage(), "Combina", JOptionPane.WARNING_MESSAGE);
							} catch (NenhumaPalavraInformadaException ex) {
								JOptionPane.showMessageDialog(null, ex.getMessage(), "Combina", JOptionPane.WARNING_MESSAGE);
							}
						}

					} catch (Throwable t) {
						JOptionPane.showMessageDialog(null, t.getMessage(),
								"Combina", JOptionPane.WARNING_MESSAGE);
					}
				}
			});
		}
		return btnCombinar;
	}

	private JButton getBtnExportar() {
		if (btnExportar == null) {
			btnExportar = new JButton();
			btnExportar.setBounds(new java.awt.Rectangle(477, 100, 127, 41));
			btnExportar.setIcon(new ImageIcon(getClass().getResource("/gui/acroread.png")));
			btnExportar.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 12));
			btnExportar.setText("Exportar");
			btnExportar.setEnabled(false);
			btnExportar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {

					JFileChooser chooser = new JFileChooser();
					chooser.setDialogType(JFileChooser.OPEN_DIALOG);
					chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					chooser.showDialog(null, null);

					File theFile = chooser.getSelectedFile();
					if (theFile != null) {
						if (theFile.isDirectory()) {
							Exportacao exportacao = new Exportacao(resultado);
							exportacao.iniciarExportacao(theFile.getPath());
						}
					}

				}
			});
		}
		return btnExportar;
	}

	private JTextField getTxtPalavrasPossiveis() {
		if (txtPalavrasPossiveis == null) {
			txtPalavrasPossiveis = new JTextField();
			txtPalavrasPossiveis.setBounds(new java.awt.Rectangle(12, 29, 491,
					24));
			txtPalavrasPossiveis.setFont(new java.awt.Font("Arial",
					java.awt.Font.PLAIN, 12));
		}
		return txtPalavrasPossiveis;
	}

	private JTextField getTxtCasas() {
		if (txtCasas == null) {
			txtCasas = new JTextField();
			txtCasas.setBounds(new java.awt.Rectangle(516, 29, 85, 24));
			txtCasas
					.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 12));
		}
		return txtCasas;
	}

	private List getLstCombinacao() {
		if (lstCombinacao == null) {
			lstCombinacao = new List();
			lstCombinacao.setBounds(new java.awt.Rectangle(12, 60, 589, 185));
		}
		return lstCombinacao;
	}

	private String pegaDataHora() {
		String minhaDataFormatada = "";
		SimpleDateFormat formatadorData;

		Date minhaData = new Date();

		formatadorData = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		minhaDataFormatada = formatadorData.format(minhaData);

		return minhaDataFormatada;
	}

	public void run() {
		while (true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			this.lblDataHora.setText(this.pegaDataHora());
		}
	}
}