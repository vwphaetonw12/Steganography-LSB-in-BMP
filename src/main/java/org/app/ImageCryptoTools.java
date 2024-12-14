package org.app;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import java.io.*;

/**
 * Utility class for performing cryptographic operations on images,
 * including embedding and extracting text, and applying visual attacks.
 */
public class ImageCryptoTools {

    /**
     * Applies a visual attack (LSB Analysis) to the given image.
     * This method creates a new image where each pixel reflects the least significant bit
     * of the red, green, and blue channels of the original image.
     *
     * @param image the input image to apply the visual attack on.
     * @return a new Image object with the results of the visual attack.
     * @throws IllegalArgumentException if the image dimensions are invalid (width or height <= 0).
     */
    public static Image performVisualAttack(Image image) {
        // Check if the image dimensions are valid
        int width = (int) image.getWidth();
        int height = (int) image.getHeight();

        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Image dimensions must be positive (w,h > 0)");
        }

        PixelReader reader = image.getPixelReader();
        WritableImage attackedImage = new WritableImage(width, height);
        PixelWriter writer = attackedImage.getPixelWriter();

        // Traverse through all pixels of the image
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Color color = reader.getColor(x, y);

                // Extract the least significant bits of each channel (red, green, blue)
                int red = (int) (color.getRed() * 255);
                int green = (int) (color.getGreen() * 255);
                int blue = (int) (color.getBlue() * 255);

                // Extract the least significant bit from each channel (0 or 1)
                int redLsb = red & 1;
                int greenLsb = green & 1;
                int blueLsb = blue & 1;

                // Apply the least significant bits to the new color:
                // Each channel will be either 0 (black) or 255 (white)
                int newRed = redLsb * 255;
                int newGreen = greenLsb * 255;
                int newBlue = blueLsb * 255;

                // Write the modified color to the new image
                writer.setColor(x, y, Color.rgb(newRed, newGreen, newBlue));
            }
        }

        return attackedImage;
    }

    /**
     * Extracts a text message embedded in the least significant bits (LSBs) of an image byte array.
     * The embedded text is expected to be terminated by a null byte.
     *
     * @param imageBytes the byte array representing the image data.
     * @return the extracted string message.
     */
    public static String getString(byte[] imageBytes) {
        int offset = 54; // BMP header size
        ByteArrayOutputStream textBytes = new ByteArrayOutputStream();

        while (offset < imageBytes.length) {
            byte b = 0;
            for (int bit = 0; bit < 8; bit++) {
                b |= (byte) ((imageBytes[offset] & 1) << bit);
                offset++;
            }
            if (b == 0) { // Null byte marks end of text
                break;
            }
            textBytes.write(b);
        }

        return textBytes.toString();
    }

    /**
     * Embeds a byte array (representing text) into the least significant bits (LSBs) of an image byte array.
     * The method modifies the provided imageBytes in place to store the textBytes.
     *
     * @param imageBytes the byte array representing the image data where the text will be embedded.
     * @param textBytes  the byte array representing the text to embed.
     * @throws IOException if there is insufficient space in the image to embed the text.
     */
    static void embedBytesInImageBytes(byte[] imageBytes, byte[] textBytes) throws IOException {
        int offset = 54; // BMP header size
        for (byte b : textBytes) {
            for (int bit = 0; bit < 8; bit++) {
                int lsb = (b >> bit) & 1;
                imageBytes[offset] = (byte) ((imageBytes[offset] & 0xFE) | lsb);
                offset++;
            }
        }

        // Mark end of text with a null byte
        for (int i = 0; i < 8; i++) {
            imageBytes[offset] = (byte) (imageBytes[offset] & 0xFE);
            offset++;
        }
    }
    
}
