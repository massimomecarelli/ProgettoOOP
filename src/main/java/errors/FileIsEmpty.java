package errors;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Classe che gestisce l'eccezione nel caso in cui il file weather.json sia vuoto, stampando un messaggio all'utente.
 * @author Massimo Mecarelli
 */
public class FileIsEmpty extends Exception{
	
	/**
	 * Costruttore che gestisce l'eccezione
	 * @param e : messaggio di errore generato.
	 * @param response : parametro che funge da "tunnel" tra il programma e l'utente.
	 */
	public FileIsEmpty(String e,HttpServletResponse response) {
		resolveException(e,response);
	}
	
	/**
	 * Numero seriale che identifica l'eccezione.
	 */
	private static final long serialVersionUID = 2L;
	
	/**
	 * Metodo che mostra il messaggio di errore all'utente.
	 * @param response : parametro che funge da "tunnel" tra il programma e l'utente.
	 * @param ex : messaggio di errore generato.
	 */
	@ExceptionHandler(value=FileNotFound.class)
	public void resolveException(String ex,HttpServletResponse response) {
		try {
			response.getOutputStream().println("Warning: File vuoto\nException message: "+ex);
		} catch (IOException e) {
			
		}
	}

	
}
