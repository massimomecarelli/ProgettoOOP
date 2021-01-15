package errors;


import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Service("ErrorException")
public class ErrorException extends RuntimeException{
	
	public ErrorException(HttpServletResponse response, double err) {
		resolveException(response,err);
	}

	private static final long serialVersionUID = 1L;

	@ExceptionHandler(value=ErrorException.class)
	public void resolveException(HttpServletResponse response, double err) {
		try {
			response.getOutputStream().println("Non esistono errori percentuale minore o uguale al "+err+"% inserito. Aumentare margine di errore.");
		} catch (IOException e) {
			
		}
	}
}
