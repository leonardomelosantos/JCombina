package combina;

/**
 * Classe que modela a exce��o lan��vel quando o n�mero de casas � inferior ao n�mero de elementos a serem combinados. 
 * 
 * @author Leonardo Melo Santos (leonardomelosantos@gmail.com)
 * @version 2.0
 *
 */
public class NumeroCasasMaiorException extends Exception {

	public NumeroCasasMaiorException() {
		super();
	}

	public NumeroCasasMaiorException(String arg0) {
		super(arg0);
	}

	public NumeroCasasMaiorException(Throwable arg0) {
		super(arg0);
	}

	public NumeroCasasMaiorException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

}
