package org.watchdog.domain;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * The various types of users in the system.
 * 
 * @author Vivek Ranjan
 *
 */
public enum UserType {

	STUDENT(1, "STUDENT", "Student"), GUARDIAN(2, "", ""), TEACHER(3, "", ""), SUPERUSER(
			4, "", "");

	UserType(Integer id, String name, String displayName) {
		this.id = id;
		this.name = name;
		this.displayName = displayName;
	}

	private static Map<Integer, UserType> ID_TYPE_MAP = new LinkedHashMap<Integer, UserType>();
	static {
		ID_TYPE_MAP.put(1, STUDENT);
		ID_TYPE_MAP.put(2, GUARDIAN);
		ID_TYPE_MAP.put(3, TEACHER);
		ID_TYPE_MAP.put(4, SUPERUSER);
	};

	private static Map<String, UserType> NAME_TYPE_MAP = new LinkedHashMap<String, UserType>();
	static {
		NAME_TYPE_MAP.put("STUDENT", STUDENT);
		NAME_TYPE_MAP.put("GUARDIAN", GUARDIAN);
		NAME_TYPE_MAP.put("TEACHER", TEACHER);
		NAME_TYPE_MAP.put("SUPERUSER", SUPERUSER);
	};

	/**
	 * Identifier.
	 */
	private Integer id;

	/**
	 * String based identifier to use internally.
	 */
	private String name;

	/**
	 * User friendly representation.
	 */
	private String displayName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public UserType getTypeById(Integer id) {
		return ID_TYPE_MAP.get(id);
	}

	public UserType getTypeByName(String name) {
		return NAME_TYPE_MAP.get(name);
	}
}
