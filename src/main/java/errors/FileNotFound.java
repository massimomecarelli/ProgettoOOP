package errors;


import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Classe che gestisce l'eccezione nel caso in cui il file non esista.
 * @author Marco Pasquale Martino
 */
@ControllerAdvice
public class FileNotFound extends FileNotFoundException{
	
	/**
	 * Costruttore che gestisce l'eccezione
	 * @param e : messaggio di errore generato.
	 * @param response : parametro che funge da "tunnel" tra il programma e l'utente.
	 */
	public FileNotFound(FileNotFoundException e,HttpServletResponse response) {
		resolveException(e,response);
	}

	/**
	 * Numero seriale che identifica l'eccezione.
	 */
	private static final long serialVersionUID = 3L;
	
	/**
	 * Metodo che mostra il messaggio di errore all'utente.
	 * @param ex : messaggio di errore generato.
	 * @param response : parametro che funge da "tunnel" tra il programma e l'utente.
	 */
	@ExceptionHandler(value=FileNotFound.class)
	public void resolveException(FileNotFoundException ex,HttpServletResponse response) {
		try {
			response.getOutputStream().println("Errore: File inesistente, effettuare almeno una lettura!\nException message: "+ex.toString());
		} catch (IOException e) {
			
		}
	}

	
}
