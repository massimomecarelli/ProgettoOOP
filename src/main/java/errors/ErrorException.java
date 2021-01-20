package errors;


import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Classe che gestisce un eccezione nel caso in cui l'errore percentuale calcolato è maggiore di quello inserito.
 * @author Marco Pasquale Martino
 */
@Service("ErrorException")
public class ErrorException extends RuntimeException{
	
	/**
	 * Costruttore che gestisce l'eccezione
	 * @param response : parametro che funge da "tunnel" tra il programma e l'utente.
	 * @param err : errore percentuale inserito dall'utente.
	 */
	public ErrorException(HttpServletResponse response, double err) {
		resolveException(response,err);
	}

	/**
	 * Numero seriale che identifica l'eccezione.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Metodo che mostra il messaggio di errore all'utente.
	 * @param response : parametro che funge da "tunnel" tra il programma e l'utente.
	 * @param err : errore percentuale inserito dall'utente.
	 */
	@ExceptionHandler(value=ErrorException.class)
	public void resolveException(HttpServletResponse response, double err) {
		try {
			response.getOutputStream().println("Non esistono errori percentuale minore o uguale al "+err+"% inserito. Aumentare margine di errore.");
		} catch (IOException e) {
			
		}
	}
}
