/**
 * 
 */
package org.watchdog.service;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.watchdog.domain.User;
import org.watchdog.domain.UserType;

/**
 * Repository for {@link User}.
 * 
 * @author Vivek Ranjan
 *
 */
@RepositoryRestResource(collectionResourceRel = "user", path = "user")
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

	/**
	 * Find user by username.
	 * <p>
	 * Path: /user/search/findByUserName?un={userName}
	 * 
	 * @param un
	 *            The username to search for.
	 * 
	 * @return All users with the username. Ideally there should be only one
	 *         such user.
	 */
	List<User> findByUserName(@Param("un") String un);

	/**
	 * Find user by their phone number.
	 * <p>
	 * Path: /user/search/findByPhoneNumber?pn={phoneNumber}
	 * 
	 * @param pn
	 *            The phone number to search for.
	 * 
	 * @return All users with the provided phone number. Ideally there should be
	 *         only one such user.
	 */
	List<User> findByPhoneNumber(@Param("pn") String pn);

	/**
	 * Find users by their email. Not all users will have an email.
	 * <p>
	 * Path: /user/search/findByEmailAddress?email={emailAddress}
	 * 
	 * @param email
	 *            The email to search for.
	 * 
	 * @return The users with the given email address. Ideally there should be
	 *         only one such user.
	 */
	List<User> findByEmailAddress(@Param("email") String email);

	/**
	 * Find all users by a particular type.
	 * <p>
	 * Path: /user/search/findByUserType?type={userType}
	 * 
	 * @param type
	 *            The {@link UserType} to search for.
	 * 
	 * @return All users that are the specified type.
	 */
	List<User> findByUserType(@Param("type") UserType type);

}
