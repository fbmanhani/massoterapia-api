package br.edu.ifsp.manhani.massoterapia.exception;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.edu.ifsp.manhani.massoterapia.messages.IMessageProperty;
import br.edu.ifsp.manhani.massoterapia.messages.MessageProperties;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(AccessDeniedException.class)
	protected ResponseEntity<Object> handleAccessDenied(AccessDeniedException ex, WebRequest request) {
		List<ErrorMessage> erros = new ArrayList<>();

		erros.add(new ErrorMessage(MessageProperties.ACCESS_DENIED));

		log.debug(ex.getMessage(), ex);
		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.FORBIDDEN, request);
	}

	@ExceptionHandler(BadCredentialsException.class)
	protected ResponseEntity<Object> handleBadCredentials(BadCredentialsException ex, WebRequest request) {
		ErrorMessage message = new ErrorMessage(MessageProperties.MSG0001);
		log.debug(ex.getMessage(), ex);
		return handleExceptionInternal(ex, message, new HttpHeaders(), HttpStatus.FORBIDDEN, request);
	}

	@ExceptionHandler(BusinessException.class)
	protected ResponseEntity<Object> handleSecurity(BusinessException ex, WebRequest request) {
		ErrorMessage erro = null;
		log.debug(ex.getMessage(), ex);
		if (!ex.getMessages().isEmpty()) {
			for (IMessageProperty msg : ex.getMessages()) {
				erro = new ErrorMessage(msg);
			}
			if (erro == null) {
				erro = new ErrorMessage(MessageProperties.UNDENTIFIED_ERROR);
			}
		} else {
			erro = new ErrorMessage(ex.getCause().getClass().getName(), ex.getMessage());
		}
		return handleExceptionInternal(ex, erro, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<ErrorMessage> erros = new ArrayList<>();
		erros.add(new ErrorMessage(ex.getParameter().getParameterName(), ex.getBindingResult().toString()));
		log.debug(ex.getMessage(), ex);
		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}

}
