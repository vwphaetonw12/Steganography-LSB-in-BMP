package org.app;

/**
 * This class contains application-wide constants used for UI, error messages,
 * logging, and image processing configurations.
 * <p>
 * The constants are organized into categories based on their usage, such as button labels,
 * error messages, and UI styles. These constants ensure consistency across the application.
 * </p>
 */
public class AppConstants {

    // ----------------------------------------
    // Button Labels
    // ----------------------------------------

    /** Text for the "Load Original BMP" button. */
    public static final String LOAD_ORIGINAL_IMAGE_BUTTON_TEXT = "Load Original BMP";

    /** Text for the "Load Modified BMP" button. */
    public static final String LOAD_MODIFIED_IMAGE_BUTTON_TEXT = "Load Modified BMP";

    /** Text for the "Embed Text" button. */
    public static final String EMBED_TEXT_BUTTON_TEXT = "Embed Text";

    /** Text for the "Extract Text" button. */
    public static final String EXTRACT_TEXT_BUTTON_TEXT = "Extract Text";

    /** Text for the "Save BMP" button. */
    public static final String SAVE_MODIFIED_IMAGE_BUTTON_TEXT = "Save BMP";

    /** Text for the "Reset" button. */
    public static final String RESET_BUTTON_TEXT = "Reset";

    /** Text for the "Visual Attack (Modified)" button. */
    public static final String VISUAL_ATTACK_MODIFIED_BUTTON_TEXT = "Visual Attack (Modified)";

    /** Text for the "Visual Attack (Original)" button. */
    public static final String VISUAL_ATTACK_ORIGINAL_BUTTON_TEXT = "Visual Attack (Original)";

    // ----------------------------------------
    // Text Field and Prompts
    // ----------------------------------------

    /** Prompt text displayed in the text field for embedding text. */
    public static final String TEXT_FIELD_PROMPT = "Enter text to embed";

    // ----------------------------------------
    // Error and Info Messages
    // ----------------------------------------

    /** Error message displayed when an image fails to load. */
    public static final String ERROR_LOADING_IMAGE = "Error loading image";

    /** Error message displayed when text embedding fails. */
    public static final String ERROR_EMBEDDING_TEXT = "Error embedding text";

    /** Error message displayed when text extraction from an image fails. */
    public static final String ERROR_EXTRACTING_TEXT = "Error extracting text from image";

    /** Error message displayed when saving an image fails. */
    public static final String ERROR_SAVING_IMAGE = "Error saving image";

    /** Error message displayed when applying a visual attack fails. */
    public static final String ERROR_VISUAL_ATTACK = "Error applying visual attack";

    /** Error message displayed when no image is loaded for processing. */
    public static final String NO_IMAGE_LOADED_ERROR = "No image loaded.";

    /** Error message displayed when the image is too small to embed text. */
    public static final String IMAGE_TOO_SMALL_ERROR = "Image is too small to embed text.";

    // ----------------------------------------
    // Image Requirements and Dimensions
    // ----------------------------------------

    /** Minimum required number of pixels (width × height) for embedding or extracting text. */
    public static final int MIN_REQUIRED_PIXELS = 2 * 2;

    /** Default width of an image in the application. */
    public static final double IMAGE_WIDTH = 500;

    /** Default height of an image in the application. */
    public static final double IMAGE_HEIGHT = 500;

    /** Default width for displaying a modified image. */
    public static final double MODIFIED_IMAGE_WIDTH = 800;

    /** Default height for displaying a modified image. */
    public static final double MODIFIED_IMAGE_HEIGHT = 800;

    // ----------------------------------------
    // File Extensions
    // ----------------------------------------

    /** File extension filter for BMP files. */
    public static final String BMP_EXTENSION = "*.bmp";

    /** Description used for the BMP file extension filter in dialogs. */
    public static final String BMP_DESCRIPTION = "BMP Images";

    // ----------------------------------------
    // UI Styles and Fonts
    // ----------------------------------------

    /** Background color for the root layout of the application. */
    public static final String ROOT_LAYOUT_BACKGROUND_COLOR = "#f4f4f9";

    /** Style applied to buttons in the application. */
    public static final String BUTTON_STYLE = "-fx-background-color: #FFA500; " +
            "-fx-text-fill: white; " +
            "-fx-font-size: 14px; " +
            "-fx-font-weight: bold; " +
            "-fx-padding: 10px 20px; " +
            "-fx-border-width: 2px; " +
            "-fx-border-radius: 5px; " +
            "-fx-cursor: hand; " +
            "-fx-effect: dropshadow(gaussian, #000, 10, 0.5, 2, 2);";

    /** Style applied to ImageView components in the application. */
    public static final String IMAGE_VIEW_STYLE = "-fx-effect: dropshadow(gaussian, #000, 10, 0.5, 0, 0); -fx-border-color: #ccc; -fx-border-width: 2;";

    /** Font size for labels in the application. */
    public static final String LABEL_FONT = "16";

    /** Font size for buttons in the application. */
    public static final String BUTTON_FONT = "14";

    // ----------------------------------------
    // Logging Messages
    // ----------------------------------------

    /** Log message indicating that the application is starting. */
    public static final String APPLICATION_STARTING_LOG = "Application starting...";

    /** Log message indicating that the UI is being initialized. */
    public static final String UI_INITIALIZING_LOG = "Initializing main UI elements";

    /** Log message indicating that the UI elements have been initialized. */
    public static final String UI_ELEMENTS_INITIALIZED_LOG = "UI elements initialized";

    /** Log message indicating that the application has been reset. */
    public static final String RESET_LOG = "Application reset";
}