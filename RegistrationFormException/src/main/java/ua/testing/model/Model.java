package ua.testing.model;

import ua.testing.model.entity.LoginData;
import ua.testing.model.LoginInputException;

/**
 * Class realise busines-logic
 *
 * @author alexander dolgikh
 * @version 1.0
 */
public class Model {
	/**
	 * Add user with firstName and login to DataBase
	 * @param firstName - user first name
	 * @param login	- user login
	 * @throws LoginInputException - exception result of 
	 */
	public void addUserWithFirstNameAndLogin(String firstName, String login )
			throws LoginInputException {		
		for ( LoginData loginData : LoginData.values() ) {
			if ( login.equals( loginData.getLogin() ) )
				throw new LoginInputException( login );
		}
	}
}
