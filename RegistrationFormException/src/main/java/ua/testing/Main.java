package ua.testing;

import ua.testing.controller.Controller;
import ua.testing.model.Model;
import ua.testing.view.View;

public class Main {

    public static void main(String[] args) {
    	// write your code here
        Controller controller = new Controller( new Model(), new View() );
        // Run
        controller.processUser();
    }
}
