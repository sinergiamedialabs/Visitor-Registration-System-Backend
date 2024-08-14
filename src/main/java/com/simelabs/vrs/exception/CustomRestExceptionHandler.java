package com.simelabs.vrs.exception;

import com.simelabs.vrs.constants.ErrorConstant;
import com.simelabs.vrs.constants.MessageCodes;
import com.simelabs.vrs.response.BaseResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
@Log4j2
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CustomRestExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleAll(final Exception ex, final WebRequest request) {
		String reason = ErrorConstant.GENERAL_CAUSE;
		String message = ex.getMessage();
		log.info(ErrorConstant.EXCEPTION_LOG, ex.getClass().getName());
		log.info(ErrorConstant.REASON_LOG, ex.getMessage());
		final ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, reason, message, LocalDateTime.now());
		return new ResponseEntity<>(apiError, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<BaseResponse> resourceNotFoundException(ResourceNotFoundException ex) {
		BaseResponse message = new BaseResponse();
		message.setMessage(ex.getMessage());
		message.setCode(MessageCodes.API_ERROR_MESSAGE_CODE);
		return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
	}

}
