package org.app;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;

public class ImageStatistics {

    public static String getImageStatistics(Image image) {
        if (image == null) {
            return "No image loaded.";
        }

        int width = (int) image.getWidth();
        int height = (int) image.getHeight();
        int pixelCount = width * height;


        int[] colorHistogram = new int[256];
        int uniqueColors = 0;

        PixelReader pixelReader = image.getPixelReader();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int pixel = pixelReader.getArgb(x, y);
                int r = (pixel >> 16) & 0xFF;
                int g = (pixel >> 8) & 0xFF;
                int b = pixel & 0xFF;
                int colorIndex = (r + g + b) / 3;
                if (colorHistogram[colorIndex] == 0) {
                    uniqueColors++;
                }
                colorHistogram[colorIndex]++;
            }
        }


        int availableBits = pixelCount * 3;

        return String.format("Image Dimensions: %dx%d\nPixel Count: %d\nUnique Colors: %d\nAvailable Bits for Embedding: %d",
                width, height, pixelCount, uniqueColors, availableBits);
    }
}
