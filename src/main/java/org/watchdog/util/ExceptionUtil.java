/**
 * 
 */
package org.watchdog.util;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Utility class for storing custom exceptions.
 * 
 * @author Vivek Ranjan
 *
 */
@Component
public class ExceptionUtil {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public static class PhoneNumberAlreadyExistsException extends Exception {

		private static final long serialVersionUID = 3195293410786010807L;

		public PhoneNumberAlreadyExistsException(String phoneNumber) {
			super("User with phone number " + phoneNumber + " already exists.");
		}

	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	public static class PhoneNumberNotFoundException extends Exception {

		private static final long serialVersionUID = 3195293410786010807L;

		public PhoneNumberNotFoundException(String phoneNumber) {
			super("User with phone number " + phoneNumber + " does not exist.");
		}

	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public static class UserNameAlreadyExistsException extends Exception {

		private static final long serialVersionUID = 3195293410786010807L;

		public UserNameAlreadyExistsException(String userName) {
			super("User name: " + userName + ", already exists.");
		}

	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	public static class UserNameNotFoundException extends Exception {

		private static final long serialVersionUID = 3195293410786010807L;

		public UserNameNotFoundException(String userName) {
			super("User name " + userName + ", not found.");
		}

	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	public static class UserIDNotFoundException extends Exception {

		private static final long serialVersionUID = -1383508695151294578L;

		public UserIDNotFoundException(Long Id) {
			super("User ID " + Id + ", not found.");
		}

	}
}
