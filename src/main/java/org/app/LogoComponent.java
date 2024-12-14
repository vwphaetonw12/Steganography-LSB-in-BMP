package org.app;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * A class responsible for creating a logo component, which is displayed as an image within a VBox container.
 */
public class LogoComponent {

    /**
     * Logger instance for logging events related to the LogoComponent.
     */
    private static final Logger logger = LogManager.getLogger(LogoComponent.class);

    /**
     * The container that holds the logo image.
     */
    private VBox logoBox;

    /**
     * Constructs a new {@code LogoComponent} and loads a logo image from the specified resource path.
     * The logo image is displayed inside a {@code VBox} with predefined alignment and padding styles.
     * If the logo cannot be loaded, the VBox is initialized empty.
     *
     * @param resourcePath the path to the logo image resource, relative to the classpath.
     */
    public LogoComponent(String resourcePath) {
        try {

            Image logoImage = new Image(getClass().getResourceAsStream(resourcePath));
            ImageView logoView = new ImageView(logoImage);
            logoView.setFitWidth(200);
            logoView.setPreserveRatio(true);

            logoBox = new VBox(logoView);
            logoBox.setStyle("-fx-alignment: top-left; -fx-padding: 10;");
        } catch (Exception e) {
            logger.warn("Failed to load logo from resource: " + resourcePath, e);
            logoBox = new VBox();
        }
    }

    /**
     * Returns the {@code VBox} containing the logo image.
     *
     * @return a {@code VBox} containing the logo image, or an empty {@code VBox} if the logo failed to load.
     */
    public VBox getLogoBox() {
        return logoBox;
    }
}
