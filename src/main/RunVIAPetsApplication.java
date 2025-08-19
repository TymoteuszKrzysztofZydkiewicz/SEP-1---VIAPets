package main;

import javafx.application.Application;
import view.StartGUI;

/**
 * The entry point of the application.
 * Launches the application by calling the launch method with the main application class.
 * @author Vanessa Hututuc, Jakub Baczek, Natalia Muchova, Tymoteusz Żydkiewicz, Yuchen Zhang
 * @version 1.0 - December 2024
 */

public class RunVIAPetsApplication
{
    /**
     * The main method of the application.
     * @param args Command-line arguments .
     */

        public static void main (String[]args)
        {
            Application.launch(StartGUI.class);
        }

}
