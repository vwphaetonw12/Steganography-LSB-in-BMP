package org.app;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.nio.file.Files;
import java.io.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Main application class for the Steganography application.
 * <p>
 * This class extends the {@link javafx.application.Application} class and serves
 * as the entry point for launching the graphical user interface (GUI) of the application.
 * </p>
 *
 * <h3>Application Features:</h3>
 * <ul>
 *     <li>Embed text into BMP images using steganographic techniques.</li>
 *     <li>Extract embedded text from BMP images.</li>
 *     <li>Visualize least significant bit (LSB) analysis for steganography detection.</li>
 *     <li>Save and load BMP images for further processing.</li>
 * </ul>
 *
 * <h3>How It Works:</h3>
 * <ol>
 *     <li>Users can load an original or modified BMP image into the application.</li>
 *     <li>The application allows users to embed text into the image or extract text from it.</li>
 *     <li>It also provides a "Visual Attack" feature, highlighting LSBs for analysis.</li>
 *     <li>All features are accessible via a user-friendly graphical interface built with JavaFX.</li>
 * </ol>
 *
 * <h3>Dependencies:</h3>
 * <ul>
 *     <li>JavaFX library for building the graphical interface.</li>
 *     <li>Log4j library for logging important application events.</li>
 * </ul>
 *
 * <h3>Usage:</h3>
 * <pre>
 * To run the application:
 * 1. Compile the project using a JavaFX-compatible build tool (e.g., Maven or Gradle).
 * 2. Ensure the JavaFX runtime libraries are included in the classpath.
 * 3. Run the application using the {@link NewSteganographyApp} launcher class.
 * </pre>
 *
 * @see javafx.application.Application
 * @see org.app.NewSteganographyApp
 * @see org.app.ImageCryptoTools
 * @see org.app.ImageStatistics
 */
public class SteganographyApp extends Application {

    private static final Logger logger = LogManager.getLogger(SteganographyApp.class);
    public String extractedText;

    ImageView originalImageView;
    ImageView modifiedImageView;

    File originalImageFile;
    File modifiedImageFile;

    private Stage stage;

    TextField textToEmbedField;
    Button loadOriginalImageButton;
    Button loadModifiedImageButton;
    Button embedTextButton;
    Button extractTextButton;
    Button saveModifiedImageButton;
    Button resetButton;

    private Button visualAttackModifiedButton;
    private Button visualAttackOriginalButton;

    private VBox rootLayout;
    private HBox imageDisplayLayout;
    private VBox controlsLayout;
    private VBox originalImageBox;
    private VBox modifiedImageBox;
    private VBox logoBox;

    private Label originalImageStatsLabel;
    private Label modifiedImageStatsLabel;

    /**
     * Entry point of the application. Starts the JavaFX application lifecycle.
     *
     * @param args command-line arguments passed to the application.
     */
    public static void main(String[] args) {
        logger.info("Application starting...");
        launch(args);
    }

    /**
     * Initializes the JavaFX application. Sets up the primary stage and main UI elements.
     *
     * @param primaryStage the primary stage for this application, provided by JavaFX.
     */
    @Override
    public void start(Stage primaryStage) {
        this.stage = primaryStage;
        stage.setTitle("Steganography in BMP");

        logger.info("Initializing main UI elements");

        LogoComponent logoComponent = new LogoComponent("/logo/logo.png");
        logoBox = logoComponent.getLogoBox();

        initMainUIElements();
    }

    /**
     * Initializes the main UI components and layout. Sets up buttons, image views, and their styles.
     */
    private void initMainUIElements() {
        originalImageView = new ImageView();
        modifiedImageView = new ImageView();

        textToEmbedField = new TextField();
        textToEmbedField.setPromptText(AppConstants.TEXT_FIELD_PROMPT);
        textToEmbedField.setFont(Font.font(AppConstants.BUTTON_FONT));

        loadOriginalImageButton = new Button(AppConstants.LOAD_ORIGINAL_IMAGE_BUTTON_TEXT);
        loadModifiedImageButton = new Button(AppConstants.LOAD_MODIFIED_IMAGE_BUTTON_TEXT);
        embedTextButton = new Button(AppConstants.EMBED_TEXT_BUTTON_TEXT);
        extractTextButton = new Button(AppConstants.EXTRACT_TEXT_BUTTON_TEXT);
        saveModifiedImageButton = new Button(AppConstants.SAVE_MODIFIED_IMAGE_BUTTON_TEXT);
        resetButton = new Button(AppConstants.RESET_BUTTON_TEXT);
        visualAttackModifiedButton = new Button(AppConstants.VISUAL_ATTACK_MODIFIED_BUTTON_TEXT);
        visualAttackOriginalButton = new Button(AppConstants.VISUAL_ATTACK_ORIGINAL_BUTTON_TEXT);

        applyStylesToButtons();
        layoutSetup();
        addActionListenersForElements();

        logger.info(AppConstants.UI_INITIALIZING_LOG);
        stage.setScene(new Scene(rootLayout, 1400, 800));
        stage.show();
    }

    /**
     * Sets up the layout for the application, including image displays, controls, and logo.
     */
    private void layoutSetup() {
        originalImageBox = createImageBox(originalImageView, "Original Image");
        modifiedImageBox = createImageBox(modifiedImageView, "Modified Image");

        originalImageStatsLabel = new Label("Original Image Stats: Not loaded");
        modifiedImageStatsLabel = new Label("Modified Image Stats: Not loaded");

        originalImageStatsLabel.setFont(Font.font(12));
        originalImageStatsLabel.setStyle("-fx-text-fill: #333;");
        modifiedImageStatsLabel.setFont(Font.font(12));
        modifiedImageStatsLabel.setStyle("-fx-text-fill: #333;");

        VBox originalWithStats = new VBox(10, originalImageBox, originalImageStatsLabel);
        VBox modifiedWithStats = new VBox(10, modifiedImageBox, modifiedImageStatsLabel);

        imageDisplayLayout = new HBox(30, originalWithStats, modifiedWithStats);
        imageDisplayLayout.setAlignment(Pos.CENTER);
        imageDisplayLayout.setPadding(new Insets(20));

        controlsLayout = new VBox(15,
                loadOriginalImageButton,
                loadModifiedImageButton,
                embedTextButton,
                extractTextButton,
                saveModifiedImageButton,
                resetButton,
                visualAttackModifiedButton,
                visualAttackOriginalButton,
                textToEmbedField
        );
        controlsLayout.setAlignment(Pos.CENTER);
        controlsLayout.setPadding(new Insets(20));

        rootLayout = new VBox();
        rootLayout.getChildren().addAll(logoBox, new HBox(30, imageDisplayLayout, controlsLayout));
        rootLayout.setAlignment(Pos.TOP_LEFT);
        rootLayout.setPadding(new Insets(20));
        rootLayout.setStyle("-fx-background-color: #ffe7bb;");
    }

    /**
     * Applies styles to all buttons used in the application.
     */
    private void applyStylesToButtons() {
        styleButton(loadOriginalImageButton);
        styleButton(loadModifiedImageButton);
        styleButton(embedTextButton);
        styleButton(extractTextButton);
        styleButton(saveModifiedImageButton);
        styleButton(resetButton);
        styleButton(visualAttackModifiedButton);
        styleButton(visualAttackOriginalButton);
    }

    /**
     * Applies a predefined style to a specific button.
     *
     * @param button the button to style.
     */
    private void styleButton(Button button) {
        button.setStyle(AppConstants.BUTTON_STYLE);
        button.setMinWidth(200);
    }

    /**
     * Creates a VBox containing an ImageView and a label for display purposes.
     *
     * @param imageView the ImageView to be displayed.
     * @param labelText the text label describing the image.
     * @return a VBox containing the image and label.
     */
    private VBox createImageBox(ImageView imageView, String labelText) {
        Label label = new Label(labelText);
        label.setFont(Font.font(16));
        label.setStyle("-fx-text-fill: #333;");
        imageView.setFitWidth(500);
        imageView.setFitHeight(500);
        imageView.setPreserveRatio(true);
        imageView.setStyle("-fx-effect: dropshadow(gaussian, #000, 10, 0.5, 0, 0); -fx-border-color: #ccc; -fx-border-width: 2;");
        VBox box = new VBox(10, label, imageView);
        box.setAlignment(Pos.CENTER);
        return box;
    }

    /**
     * Adds action listeners to all UI elements for handling user interactions.
     */
    private void addActionListenersForElements() {
        loadOriginalImageButton.setOnAction(e -> {
            logger.info("Load Original BMP button clicked");
            loadImage(stage, true);
        });
        loadModifiedImageButton.setOnAction(e -> {
            logger.info("Load Modified BMP button clicked");
            loadImage(stage, false);
        });
        embedTextButton.setOnAction(e -> {
            String text = textToEmbedField.getText();
            if (originalImageFile != null && !text.isEmpty()) {
                logger.info("Embedding text into image");
                embedTextInImage(text);
            } else {
                logger.warn("No image or text provided for embedding");
                showErrorMessage("Please load an original image and enter text to embed.");
            }
        });
        extractTextButton.setOnAction(e -> {
            logger.info("Extract Text button clicked");
            extractTextFromImage();
        });
        saveModifiedImageButton.setOnAction(e -> {
            logger.info("Save Modified Image button clicked");
            saveModifiedImage(stage);
        });
        resetButton.setOnAction(e -> {
            logger.info("Reset button clicked");
            resetApplication();
        });
        visualAttackModifiedButton.setOnAction(e -> {
            if (modifiedImageFile != null) {
                logger.info("Performing visual attack on modified image");
                applyVisualAttack(modifiedImageFile, modifiedImageView);
            } else {
                logger.warn("No modified image loaded for visual attack");
                showErrorMessage("Please load the modified image first.");
            }
        });
        visualAttackOriginalButton.setOnAction(e -> {
            if (originalImageFile != null) {
                logger.info("Performing visual attack on original image");
                applyVisualAttack(originalImageFile, originalImageView);
            } else {
                logger.warn("No original image loaded for visual attack");
                showErrorMessage("Please load the original image first.");
            }
        });
        loadOriginalImageButton.setOnAction(e -> {
            logger.info("Load Original BMP button clicked");
            loadImage(stage, true);
            updateOriginalImageStatistics(); // Обновление статистики для оригинального изображения
        });

        loadModifiedImageButton.setOnAction(e -> {
            logger.info("Load Modified BMP button clicked");
            loadImage(stage, false);
            updateModifiedImageStatistics(); // Обновление статистики для модифицированного изображения
        });
    }

    /**
     * Loads an image file and sets it to the specified ImageView.
     *
     * @param stage      the stage for displaying the file chooser.
     * @param isOriginal true if the image is the original image; false otherwise.
     */
    void loadImage(Stage stage, boolean isOriginal) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(AppConstants.BMP_DESCRIPTION, AppConstants.BMP_EXTENSION));
        File selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null) {
            try {
                Image image = new Image(new FileInputStream(selectedFile), AppConstants.IMAGE_WIDTH, AppConstants.IMAGE_HEIGHT, true, true);
                if (isOriginal) {
                    originalImageFile = selectedFile;
                    originalImageView.setImage(image);
                    updateOriginalImageStatistics(); // Обновление статистики
                    logger.info("Original image loaded: " + selectedFile.getAbsolutePath());
                } else {
                    modifiedImageFile = selectedFile;
                    modifiedImageView.setImage(image);
                    updateModifiedImageStatistics(); // Обновление статистики
                    logger.info("Modified image loaded: " + selectedFile.getAbsolutePath());
                }
            } catch (IOException e) {
                logger.error(AppConstants.ERROR_LOADING_IMAGE, e);
                showErrorMessage(AppConstants.ERROR_LOADING_IMAGE);
            }
        }
    }

    /**
     * Embeds the provided text into the loaded original image.
     *
     * @param text the text to embed into the image.
     */
    void embedTextInImage(String text) {
        try {
            // Checking the minimum image sizes
            if (originalImageFile == null || originalImageFile.length() == 0) {
                showErrorMessage(AppConstants.ERROR_LOADING_IMAGE);
                return;
            }

            Image image = new Image(new FileInputStream(originalImageFile));
            int width = (int) image.getWidth();
            int height = (int) image.getHeight();

            if (width * height < AppConstants.MIN_REQUIRED_PIXELS) {
                showErrorMessage(AppConstants.IMAGE_TOO_SMALL_ERROR);
                return;
            }

            byte[] imageData = Files.readAllBytes(originalImageFile.toPath());
            byte[] textBytes = text.getBytes();

            // Ensure the image is large enough to embed the text
            int availableCapacity = imageData.length / 8; 
            if (textBytes.length > availableCapacity) {
                showErrorMessage("The message is too large to embed in this image. Available capacity: "
                        + availableCapacity + " bytes, Message size: " + textBytes.length + " bytes.");
                return;
            }

            ImageCryptoTools.embedBytesInImageBytes(imageData, textBytes);

            modifiedImageFile = new File(originalImageFile.getParent(), "modified.bmp");
            try (FileOutputStream fos = new FileOutputStream(modifiedImageFile)) {
                fos.write(imageData);
            }

            Image modifiedImage = new Image(new FileInputStream(modifiedImageFile), AppConstants.MODIFIED_IMAGE_WIDTH, AppConstants.MODIFIED_IMAGE_HEIGHT, true, true);
            modifiedImageView.setImage(modifiedImage);
            updateModifiedImageStatistics();
            logger.info("Text embedded successfully and saved to modified.bmp");

        } catch (IOException e) {
            logger.error(AppConstants.ERROR_EMBEDDING_TEXT, e);
            showErrorMessage(AppConstants.ERROR_EMBEDDING_TEXT);
        }
    }

    /**
     * Extracts embedded text from the currently loaded modified image.
     */
    void extractTextFromImage() {
        if (modifiedImageFile == null || !modifiedImageFile.exists()) {
            logger.warn("No modified image loaded for extraction");
            showErrorMessage(AppConstants.NO_IMAGE_LOADED_ERROR);
            return;
        }

        try {
            Image image = new Image(new FileInputStream(modifiedImageFile));
            int width = (int) image.getWidth();
            int height = (int) image.getHeight();

            if (width * height < AppConstants.MIN_REQUIRED_PIXELS) {
                showErrorMessage(AppConstants.IMAGE_TOO_SMALL_ERROR);
                return;
            }

            byte[] imageData = Files.readAllBytes(modifiedImageFile.toPath());

            String extractedText = ImageCryptoTools.getString(imageData);
            logger.info("Text extracted successfully: " + extractedText);
            showInfoMessage("Extracted Text", "The embedded text is:", extractedText);
        } catch (IOException e) {
            logger.error(AppConstants.ERROR_EXTRACTING_TEXT, e);
            showErrorMessage(AppConstants.ERROR_EXTRACTING_TEXT);
        }
    }

    /**
     * Applies a visual attack to the specified image file and displays the result in the provided ImageView.
     *
     * @param imageFile the image file to which the visual attack will be applied.
     * @param imageView the ImageView to display the attacked image.
     */
    void applyVisualAttack(File imageFile, ImageView imageView) {
        try {
            Image image = new Image(new FileInputStream(imageFile));
            if (image.getWidth() <= 0 || image.getHeight() <= 0) {
                showErrorMessage("Image dimensions must be positive.");
                return;
            }
            imageView.setImage(ImageCryptoTools.performVisualAttack(image));
            logger.info("Visual attack applied successfully");
        } catch (IOException e) {
            logger.error(AppConstants.ERROR_VISUAL_ATTACK, e);
            showErrorMessage(AppConstants.ERROR_VISUAL_ATTACK);
        }
    }

    /**
     * Saves the currently modified image to a location specified by the user.
     *
     * @param stage the JavaFX Stage used to display the save dialog.
     */
    void saveModifiedImage(Stage stage) {
        if (modifiedImageFile == null) {
            logger.warn("No modified image to save");
            showErrorMessage("No modified image to save.");
            return;
        }

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(AppConstants.BMP_DESCRIPTION, AppConstants.BMP_EXTENSION));
        fileChooser.setInitialFileName("saved_image.bmp");
        File saveFile = fileChooser.showSaveDialog(stage);

        if (saveFile != null) {
            try {
                Files.copy(modifiedImageFile.toPath(), saveFile.toPath());
                logger.info("Modified image saved to: " + saveFile.getAbsolutePath());
            } catch (IOException e) {
                logger.error(AppConstants.ERROR_SAVING_IMAGE, e);
                showErrorMessage(AppConstants.ERROR_SAVING_IMAGE);
            }
        }
    }

    /**
     * Updates the statistics label for the original image using data derived from the loaded image.
     */
    private void updateOriginalImageStatistics() {
        if (originalImageView.getImage() != null) {
            String stats = ImageStatistics.getImageStatistics(originalImageView.getImage());
            originalImageStatsLabel.setText("Original Image Stats: \n" + stats);
        }
    }

    /**
     * Updates the statistics label for the modified image using data derived from the loaded image.
     */
    private void updateModifiedImageStatistics() {
        if (modifiedImageView.getImage() != null) {
            String stats = ImageStatistics.getImageStatistics(modifiedImageView.getImage());
            modifiedImageStatsLabel.setText("Modified Image Stats: \n" + stats);
        }
    }

    /**
     * Resets the application to its initial state. Clears loaded images, statistics, and related variables.
     */
    void resetApplication() {
        originalImageFile = null;
        modifiedImageFile = null;

        originalImageView.setImage(null);
        modifiedImageView.setImage(null);

        if (originalImageStatsLabel != null) {
            originalImageStatsLabel.setText("Statistics reset");
        }
        if (modifiedImageStatsLabel != null) {
            modifiedImageStatsLabel.setText("Statistics reset");
        }

        logger.info(AppConstants.RESET_LOG);
    }

    /**
     * Displays an error message in a pop-up dialog.
     *
     * @param message the error message to display.
     */
    private void showErrorMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Displays an informational message in a pop-up dialog.
     *
     * @param title   the title of the dialog.
     * @param header  the header text of the dialog.
     * @param message the informational message to display.
     */
    private void showInfoMessage(String title, String header, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait();
    }
}