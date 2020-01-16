package ua.testing.model;

/**
 * Exception class. Call when user with current login exist
 * @author alexander dolgikh
 * @version 1.0
 */
public class LoginInputException extends Exception {
	private String	loginExist;
	
	/**
	 * Constructor
	 * @param loginExist - user login, which already exist in DataBase
	 */
	LoginInputException( final String loginExist ) {
		this.loginExist = loginExist;
	}
	
	public String toString() {
		return "User with currect login (" + loginExist + ") already exist!!!";
	}
}
