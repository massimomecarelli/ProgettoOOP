package errors;


import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class FileNotFound extends FileNotFoundException{
	
	public FileNotFound(FileNotFoundException e,HttpServletResponse response) {
		resolveException(e,response);
	}

	private static final long serialVersionUID = 1L;
	
	@ExceptionHandler(value=FileNotFound.class)
	public void resolveException(FileNotFoundException ex,HttpServletResponse response) {
		try {
			response.getOutputStream().println("Errore: File inesistente, effettuare almeno una lettura!\nException message: "+ex.toString());
		} catch (IOException e) {
			
		}
	}

	
}
