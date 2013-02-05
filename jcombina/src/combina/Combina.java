package combina;

import java.util.ArrayList;
import java.util.Date;

/**
 * Classe que combina caracteres de acordo com o n�mero de casas desejadas.
 * 
 * @author Leonardo Melo Santos (leonardomelosantos@gmail.com)
 * @version 2.0
 *
 */

public class Combina {
	private ArrayList arrCaracteres;
	private int intNumeroDeCasas;
	private long tempoGasto = -1;
	
	/**
	 * Construtor da classe. Os dados informados para o construtor s�o importantes para o processo de combina��o.
	 * 
	 * @param arrCaracteres Cole��o de caracteres n�o-repetidos a serem combinados 
	 * @param intNumeroDeCasas N�mero de casas para a combina��o
	 */
	public Combina(ArrayList arrCaracteres, int intNumeroDeCasas) {
		this.arrCaracteres = arrCaracteres;
		this.intNumeroDeCasas = intNumeroDeCasas;
	}

	/**
	 * M�todo que efetivamente faz a combina��o dos dados informados no construtor.
	 *  
	 * @param arrResultado Cole��o que ser� alimentada com o resultado da combina��o.
	 * @throws NumeroCasasMaiorException Exce��o que ser� lan�ada caso o n�mero de casas informadas pelo usu�rio ultrapasse o n�mero de elmentos a serem combinados.
	 * @throws NenhumaPalavraInformadaException Exce��o que � lan�ada caso nenhuma elemento a ser combinado for informado.
	 */
	public void combinar(ArrayList arrResultado) throws NumeroCasasMaiorException, NenhumaPalavraInformadaException {
		int i;
		long tempoInicio = new Date().getTime();
		long tempoFim = tempoInicio; 
		
		if (this.intNumeroDeCasas > arrCaracteres.size()) {
			throw new NumeroCasasMaiorException("N�mero de casas maior que o n�mero de elementos.");
		} else {
			if (arrCaracteres.size() <= 0) {
				throw new NenhumaPalavraInformadaException("Nenhum elemento foi informado.");
			} else {
				for (i=0 ; i<this.arrCaracteres.size() ; i++ ) {
					MontaVetorResultado(this.arrCaracteres, i, this.intNumeroDeCasas, 
						0, arrResultado, (String)arrCaracteres.get(i));
				}				
			}
		}
		
		tempoFim = new Date().getTime();
		this.tempoGasto = tempoFim - tempoInicio;
	}

	private void MontaVetorResultado(ArrayList arrCaracteres, int intCaractereInicial, 
		int intNumeroDeCasas, int intPosicao, ArrayList arrResultado, String strConteudo) {
		int i;

		if (intPosicao+1 == intNumeroDeCasas) {
			arrResultado.add(new String(strConteudo));
		} else {
			for (i=intCaractereInicial+1 ; i<arrCaracteres.size() ; i++ ) {
				if ((arrCaracteres.size() - (intCaractereInicial-intPosicao)) >= this.intNumeroDeCasas) {
					MontaVetorResultado(this.arrCaracteres, i, this.intNumeroDeCasas, intPosicao + 1, arrResultado, strConteudo + "," + (String)arrCaracteres.get(i));
				} else {
					break;
				}
			}
		}
	}

	public long getTempoGasto() {
		return tempoGasto;
	}
}
