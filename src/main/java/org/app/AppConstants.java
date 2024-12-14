package org.app;

public class AppConstants {

    // Texts for button labels
    public static final String LOAD_ORIGINAL_IMAGE_BUTTON_TEXT = "Load Original BMP";
    public static final String LOAD_MODIFIED_IMAGE_BUTTON_TEXT = "Load Modified BMP";
    public static final String EMBED_TEXT_BUTTON_TEXT = "Embed Text";
    public static final String EXTRACT_TEXT_BUTTON_TEXT = "Extract Text";
    public static final String SAVE_MODIFIED_IMAGE_BUTTON_TEXT = "Save BMP";
    public static final String RESET_BUTTON_TEXT = "Reset";
    public static final String VISUAL_ATTACK_MODIFIED_BUTTON_TEXT = "Visual Attack (Modified)";
    public static final String VISUAL_ATTACK_ORIGINAL_BUTTON_TEXT = "Visual Attack (Original)";

    // Text for text field and prompts
    public static final String TEXT_FIELD_PROMPT = "Enter text to embed";

    // Error and info messages
    public static final String ERROR_LOADING_IMAGE = "Error loading image";
    public static final String ERROR_EMBEDDING_TEXT = "Error embedding text";
    public static final String ERROR_EXTRACTING_TEXT = "Error extracting text from image";
    public static final String ERROR_SAVING_IMAGE = "Error saving image";
    public static final String ERROR_VISUAL_ATTACK = "Error applying visual attack";
    public static final String NO_IMAGE_LOADED_ERROR = "No image loaded.";
    public static final String IMAGE_TOO_SMALL_ERROR = "Image is too small to embed text.";

    // Minimum image dimensions for embedding/extracting text (in pixels)
    public static final int MIN_REQUIRED_PIXELS = 2 * 2;

    // Image properties
    public static final double IMAGE_WIDTH = 500;
    public static final double IMAGE_HEIGHT = 500;
    public static final double MODIFIED_IMAGE_WIDTH = 800;
    public static final double MODIFIED_IMAGE_HEIGHT = 800;

    // File extensions
    public static final String BMP_EXTENSION = "*.bmp";
    public static final String BMP_DESCRIPTION = "BMP Images";

    // Other UI settings
    public static final String ROOT_LAYOUT_BACKGROUND_COLOR = "#f4f4f9";
    public static final String BUTTON_STYLE = "-fx-background-color: #FFA500; " +
            "-fx-text-fill: white; " +
            "-fx-font-size: 14px; " +
            "-fx-font-weight: bold; " +
            "-fx-padding: 10px 20px; " +
            "-fx-border-width: 2px; " +
            "-fx-border-radius: 5px; " +
            "-fx-cursor: hand; " +
            "-fx-effect: dropshadow(gaussian, #000, 10, 0.5, 2, 2);";
    public static final String IMAGE_VIEW_STYLE = "-fx-effect: dropshadow(gaussian, #000, 10, 0.5, 0, 0); -fx-border-color: #ccc; -fx-border-width: 2;";

    // Fonts
    public static final String LABEL_FONT = "16";
    public static final String BUTTON_FONT = "14";

    // Logging messages
    public static final String APPLICATION_STARTING_LOG = "Application starting...";
    public static final String UI_INITIALIZING_LOG = "Initializing main UI elements";
    public static final String UI_ELEMENTS_INITIALIZED_LOG = "UI elements initialized";
    public static final String RESET_LOG = "Application reset";
}
