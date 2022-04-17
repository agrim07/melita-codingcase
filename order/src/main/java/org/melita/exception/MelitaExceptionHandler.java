package org.melita.exception;

import org.melita.domain.ModelApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class MelitaExceptionHandler extends ResponseEntityExceptionHandler {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MelitaExceptionHandler.class);

	@ExceptionHandler({BadRequestException.class})
	public ResponseEntity<ModelApiResponse> toResponse(Exception exception, final WebRequest req) {
		ModelApiResponse response = new ModelApiResponse();
		response.setCode(HttpStatus.BAD_REQUEST.value());
		response.setType(HttpStatus.BAD_REQUEST.name());
		response.setMessage(exception.getMessage());
		LOGGER.error("BadRequestException: "+ exception.getMessage());
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
}
