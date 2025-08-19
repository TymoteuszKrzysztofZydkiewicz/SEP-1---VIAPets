package view;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Entry point for the VIAPets application GUI.
 * Initializes and starts the JavaFX application, setting up the main window with a title and icon.
 *
 * @author Vanessa Hututuc, Jakub Baczek, Natalia Muchova, Tymoteusz Żydkiewicz, Yuchen Zhang
 * @version 1.0 - December 2024
 */
public class StartGUI extends Application {

    /**
     * Starts the JavaFX application and sets up the primary stage (window).
     *
     * @param window the primary stage for this application
     */
    @Override
    public void start(Stage window) {
        ViewHandler viewHandler = new ViewHandler(window);
        viewHandler.start();

        // Set the application icon
        window.getIcons().add(new Image(getClass().getResourceAsStream("viapets_logo.jpg")));

        // Set the application title
        window.setTitle("VIAPets");
    }
}
