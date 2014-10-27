/**
 * 
 */
package org.watchdog.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * A typical user in the system.
 * 
 * @author Vivek Ranjan
 *
 */
@Entity
@ApiModel("User")
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue
	@Column(name = "user_id")
	@ApiModelProperty(dataType = "string", required = false)
	private Long userId;

	@Column(name = "user_name", nullable = false, unique = true)
	@ApiModelProperty(required = true, dataType = "string")
	private String userName;

	@Column(name = "password")
	@ApiModelProperty(required = false, dataType = "string")
	private String password;

	@Enumerated(EnumType.STRING)
	@Column(name = "user_type")
	@ApiModelProperty(required = false, dataType = "string", allowableValues = "STUDENT, SUPERUSER, TEACHER, GUARDIAN")
	private UserType userType;

	@Column(name = "email_address", nullable = true, unique = true)
	@ApiModelProperty(required = true, dataType = "string")
	private String emailAddress;

	@Column(name = "phone_number", nullable = false, unique = true)
	@ApiModelProperty(required = true, dataType = "string")
	private String phoneNumber;

	/**
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the userType
	 */
	public UserType getUserType() {
		return userType;
	}

	/**
	 * @param userType
	 *            the userType to set
	 */
	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	/**
	 * @return the emailAddress
	 */
	public String getEmailAddress() {
		return emailAddress;
	}

	/**
	 * @param emailAddress
	 *            the emailAddress to set
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber
	 *            the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}
