package org.app;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogoComponent {

    private static final Logger logger = LogManager.getLogger(LogoComponent.class);
    private VBox logoBox;

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

    public VBox getLogoBox() {
        return logoBox;
    }
}
