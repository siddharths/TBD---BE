/**
 * 
 */
package org.watchdog.util;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Utility to handle exception display.
 * 
 * @author Vivek Ranjan
 *
 */
@ControllerAdvice
public class ExceptionHandlerUtil {

	private static final Logger logger = LoggerFactory
			.getLogger(ExceptionHandlerUtil.class);

	@ExceptionHandler(value = IllegalArgumentException.class)
	public String illegalArgumentExceptionHandler(HttpServletRequest req,
			IllegalArgumentException e) throws IllegalArgumentException {
		// If the exception is annotated with @ResponseStatus re-throw it and
		// let
		// the framework handle it -
		// AnnotationUtils is a Spring Framework utility class.
		if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null)
			throw e;

		// Otherwise let's show the error.
		logger.error(
				"Turns out, you gave me shit I do not like or want. Better fix it.",
				e);
		return e.getMessage();
	}
}
