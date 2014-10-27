/**
 * 
 */
package org.watchdog.controller;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.watchdog.domain.User;
import org.watchdog.service.UserRepository;
import org.watchdog.util.ExceptionUtil.PhoneNumberAlreadyExistsException;
import org.watchdog.util.ExceptionUtil.PhoneNumberNotFoundException;
import org.watchdog.util.ExceptionUtil.UserIDNotFoundException;
import org.watchdog.util.ExceptionUtil.UserNameAlreadyExistsException;
import org.watchdog.util.ExceptionUtil.UserNameNotFoundException;

import ch.qos.logback.classic.Logger;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

/**
 * 
 * @author Vivek Ranjan
 *
 */
@RestController
@RequestMapping("/user")
@Api(value = "user", description = "User API")
public class UserController {

	private static final Logger logger = (Logger) LoggerFactory
			.getLogger(UserController.class);

	@Autowired
	UserRepository repo;

	/**
	 * 
	 * @param user
	 *            The user to create.
	 * 
	 * @return Returns CREATED status if creation is successful along with a
	 *         link to access the user in the header location and the user
	 *         details as the body.
	 * 
	 * @throws PhoneNumberAlreadyExistsException
	 *             If the specified phone number already exists. Phone number
	 *             needs to be unique.
	 * @throws UserNameAlreadyExistsException
	 *             If the specified user name already exists. User name needs to
	 *             be unique.
	 * @throws IllegalArgumentException
	 *             If user id is supplied, or user name/phone number is not
	 *             supplied.
	 */
	@RequestMapping(consumes = { "application/json" }, method = RequestMethod.POST, value = "")
	@ApiOperation(httpMethod = "POST", value = "Creates a new user.", consumes = "application/json", response = User.class, produces = "application/json", notes = "Creates a new user.")
	ResponseEntity<?> create(@RequestBody User user)
			throws PhoneNumberAlreadyExistsException,
			UserNameAlreadyExistsException, IllegalArgumentException {
		if (null != user.getUserId()) {
			throw new IllegalArgumentException(
					"Cannot accept the specified User ID.");
		}
		try {
			validateUser(user, true);
		} catch (UserNameNotFoundException | PhoneNumberNotFoundException
				| UserIDNotFoundException e) {
			logger.info("User not found, safe to create.");
		}
		User savedUser = repo.save(user);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedUser.getUserId()).toUri());
		return new ResponseEntity<>(savedUser, httpHeaders, HttpStatus.CREATED);
	}

	/**
	 * Updates the user with the supplied user information.
	 * 
	 * @param user
	 *            The user to update.
	 * 
	 * @return Returns OK status if update is successful along with a link to
	 *         access the user in the header location and the user details as
	 *         the body.
	 * 
	 * @throws UserIDNotFoundException
	 *             If supplied user ID is not found. Can only update the user if
	 *             the user exists.
	 */
	@RequestMapping(consumes = { "application/json" }, method = RequestMethod.POST, value = "/update")
	@ApiOperation(httpMethod = "POST", value = "Updates user.", consumes = "application/json", response = User.class, produces = "application/json", notes = "Updates user.")
	ResponseEntity<?> update(@RequestBody User user)
			throws UserIDNotFoundException, IllegalArgumentException {

		try {
			validateUser(user, false);
		} catch (PhoneNumberAlreadyExistsException | UserNameNotFoundException
				| UserNameAlreadyExistsException | PhoneNumberNotFoundException e) {
			logger.error("Validation threw some issues.", e);
		}
		logger.info(user.getUserName());
		User savedUser = repo.save(user);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedUser.getUserId()).toUri());
		return new ResponseEntity<>(savedUser, httpHeaders, HttpStatus.OK);
	}

	/**
	 * Get user by phone number.
	 * 
	 * @param pn
	 *            The phone number to search for.
	 * 
	 * @return User found with the phone number.
	 * 
	 * @throws IllegalArgumentException
	 *             If incorrect phone number syntax is supplied.
	 * @throws PhoneNumberNotFoundException
	 *             If no user is found with the given phone number.
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/pn/{pn}")
	@ApiOperation(httpMethod = "GET", value = "Get user by phone number.", consumes = "application/json", response = User.class, produces = "application/json", notes = "Get user by phone number.")
	User fetchByPhoneNumber(@PathVariable String pn)
			throws IllegalArgumentException, PhoneNumberNotFoundException {
		try {
			validatePhoneNumber(pn);
		} catch (PhoneNumberAlreadyExistsException e) {
			logger.info("User with number exists. Huzzah!");
		}

		return (User) repo.findByPhoneNumber(pn).get(0);
	}

	/**
	 * Get user by user name.
	 * 
	 * @param un
	 *            User name to search for.
	 * 
	 * @return User found with the user name.
	 * 
	 * @throws IllegalArgumentException
	 *             If incorrect user name syntax is supplied.
	 * 
	 * @throws UserNameNotFoundException
	 *             If no user with the user name is found.
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/un/{un}")
	@ApiOperation(httpMethod = "GET", value = "Get user by user name.", consumes = "application/json", response = User.class, produces = "application/json", notes = "Get user by user name.")
	User fetchByUserName(@PathVariable String un)
			throws IllegalArgumentException, UserNameNotFoundException {
		try {
			validateUserName(un);
		} catch (UserNameAlreadyExistsException e) {
			logger.info("User name exists. Huzzah!");
		}

		return (User) repo.findByUserName(un).get(0);
	}

	/**
	 * Get user by ID.
	 * 
	 * @param id
	 *            The user ID to search for.
	 * 
	 * @return The User with the ID.
	 * 
	 * @throws IllegalArgumentException
	 *             If no Id is given.
	 * 
	 * @throws UserIDNotFoundException
	 *             If user id is not found.
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	@ApiOperation(httpMethod = "GET", value = "Get user by ID.", consumes = "application/json", response = User.class, produces = "application/json", notes = "Get user by ID.")
	User fetchByUserID(@PathVariable Long id) throws IllegalArgumentException,
			UserIDNotFoundException {
		validateUserID(id);
		return (User) repo.findOne(id);
	}

	/**
	 * Validates the user on the ID, user name and phone number.
	 * 
	 * @param user
	 *            The user to be validated.
	 * 
	 * @throws PhoneNumberAlreadyExistsException
	 *             If the specified phone number already exists.
	 * @throws UserNameNotFoundException
	 *             No user with the user name was found.
	 * @throws UserNameAlreadyExistsException
	 *             If the specified user name already exists.
	 * @throws PhoneNumberNotFoundException
	 *             No user with phone number was found.
	 * @throws UserIDNotFoundException
	 *             If supplied user ID is not found.
	 */
	private void validateUser(User user, boolean isNewUser)
			throws PhoneNumberAlreadyExistsException,
			UserNameNotFoundException, UserNameAlreadyExistsException,
			PhoneNumberNotFoundException, UserIDNotFoundException {
		if (!isNewUser) {
			validateUserID(user.getUserId());
		}
		validatePhoneNumber(user.getPhoneNumber());
		validateUserName(user.getUserName());
	}

	private void validateUserID(Long userId) throws UserIDNotFoundException,
			IllegalArgumentException {
		if (null == userId) {
			throw new IllegalArgumentException("Invalid User ID.");
		} else if (!repo.exists(userId)) {
			throw new UserIDNotFoundException(userId);
		}
	}

	private void validateUserName(String userName)
			throws UserNameNotFoundException, UserNameAlreadyExistsException,
			IllegalArgumentException {
		List<User> foundUser = null;
		if (null == userName) {
			throw new IllegalArgumentException("Invalid user name.");
		} else if (userName.length() == 0) {
			throw new IllegalArgumentException("Invalid user name.");
		} else if (null != (foundUser = repo.findByUserName(userName))) {
			if (foundUser.size() != 0) {
				throw new UserNameAlreadyExistsException(userName);
			} else {
				throw new UserNameNotFoundException(userName);
			}
		} else {
			throw new UserNameNotFoundException(userName);
		}
	}

	private void validatePhoneNumber(String phoneNumber)
			throws PhoneNumberAlreadyExistsException,
			PhoneNumberNotFoundException, IllegalArgumentException {
		List<User> foundUser = null;
		if (null == phoneNumber) {
			throw new IllegalArgumentException("Invalid Phone number");
		} else if (phoneNumber.length() == 0) {
			throw new IllegalArgumentException("Invalid Phone number");
		} else if (null != (foundUser = repo.findByPhoneNumber(phoneNumber))) {
			if (foundUser.size() != 0) {
				throw new PhoneNumberAlreadyExistsException(phoneNumber);
			}
		} else {
			throw new PhoneNumberNotFoundException(phoneNumber);
		}
	}

}
