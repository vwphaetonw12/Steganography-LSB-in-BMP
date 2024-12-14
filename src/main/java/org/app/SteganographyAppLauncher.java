package org.app;

import javafx.application.Application;

/**
 * Entry point for launching the Steganography application.
 * <p>
 * This class serves as a bootstrap to start the JavaFX application
 * by invoking the {@link Application#launch(Class, String...)} method.
 * </p>
 */
public class SteganographyAppLauncher {

    /**
     * Main method to launch the Steganography application.
     * <p>
     * This method calls the {@link Application#launch(Class, String...)} method,
     * passing {@link SteganographyApp} as the main JavaFX application class.
     * </p>
     *
     * @param args command-line arguments passed to the application.
     */
    public static void main(String[] args) {
        Application.launch(SteganographyApp.class, args);
    }
}
