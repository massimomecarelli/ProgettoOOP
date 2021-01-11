package errors;


import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ExceptionHandler;

public class FileIsEmpty extends Exception{
	
	public FileIsEmpty(Exception e,HttpServletResponse response) {
		resolveException(e,response);
	}

	private static final long serialVersionUID = 1L;

	@ExceptionHandler(value=FileNotFound.class)
	public void resolveException(Exception ex,HttpServletResponse response) {
		try {
			response.getOutputStream().println("Warning: File vuoto\nException message: "+ex.toString());
		} catch (IOException e) {
			
		}
	}

	
}
