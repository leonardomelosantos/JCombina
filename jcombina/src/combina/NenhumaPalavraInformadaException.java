package combina;

/**
 * Classe que modela a exceção lançável quando nenhuma palavra é informada para a combinação. 
 * 
 * @author Leonardo Melo Santos (leonardomelosantos@gmail.com)
 * @version 2.0
 *
 */

public class NenhumaPalavraInformadaException extends Exception {

	public NenhumaPalavraInformadaException() {
		super();
	}

	public NenhumaPalavraInformadaException(String arg0) {
		super(arg0);
	}

	public NenhumaPalavraInformadaException(Throwable arg0) {
		super(arg0);
	}

	public NenhumaPalavraInformadaException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}
	
}
