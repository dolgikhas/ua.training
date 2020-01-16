package ua.testing.controller;

import java.util.Scanner;


import ua.testing.model.Model;
import ua.testing.model.LoginInputException;
import ua.testing.view.View;

import static ua.testing.controller.RegexContainer.*;
import static ua.testing.controller.RegexContainer.REGEX_LOGIN;
import static ua.testing.view.TextConstant.*;
import static ua.testing.view.TextConstant.LOGIN_DATA;

/**
 * Created by student on 26.09.2017.
 */
public class InputNoteNoteBook {
    private View 	view;
    private Scanner scanner;
    private Model	model;

    private String 	firstName;
    private String 	login;

    public InputNoteNoteBook( View view, Scanner scanner, Model model ) {
        this.view 	 = view;
        this.scanner = scanner;
        this.model 	 = model;
    }

    public void inputNote() {
        UtilityController utilityController = new UtilityController(scanner, view);
        String str = (String.valueOf(View.bundle.getLocale()).equals("ua"))
                ? REGEX_NAME_UKR : REGEX_NAME_LAT;
        
        firstName 	= utilityController.inputStringValueWithScanner
    			(FIRST_NAME, str);;  
        
        String userInputLogin;
        while ( true ) {
        	userInputLogin 	   = utilityController.inputStringValueWithScanner
                            		(LOGIN_DATA, REGEX_LOGIN);
        	try {
        		model.addUserWithFirstNameAndLogin( firstName,
        				userInputLogin );
        		break;
        		
        	} catch ( LoginInputException exc ) {
        		view.printMessage( exc.toString() );
        	}
        }
        
         this.login 		= userInputLogin;
    }
}
