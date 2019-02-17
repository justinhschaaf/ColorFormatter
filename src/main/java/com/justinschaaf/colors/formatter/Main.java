package com.justinschaaf.colors.formatter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    static String[] weights = {
            "C50",
            "C100",
            "C200",
            "C300",
            "C400",
            "C500",
            "C600",
            "C700",
            "C800",
            "C900",
            "A100",
            "A200",
            "A400",
            "A700"
    };

    public static void main(String[] args) throws IOException {

        BufferedImage image = ImageIO.read(new File("colors.png"));

        ArrayList<String> pixels = convertTo2DUsingGetRGB(image);

        for (String pixel: pixels) {
            System.out.println(pixel);
        }

    }

    // https://stackoverflow.com/questions/6524196/java-get-pixel-array-from-image
    private static ArrayList<String> convertTo2DUsingGetRGB(BufferedImage image) {

        int width = image.getWidth();
        int height = image.getHeight();
        ArrayList<String> result = new ArrayList<String>();

        for (int row = 0; row < height; row++) {

            result.add("{ \n" +
                    "    metadata: {\n" +
                    "        title: \"PLACEHOLDER\"\n" +
                    "    },");

            for (int col = 0; col < width; col++) {

                Color color = new Color(image.getRGB(col, row));

                // https://stackoverflow.com/questions/3607858/convert-a-rgb-color-value-to-a-hexadecimal
                result.add("    " + weights[col] + ": " + '"' + String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue()) + '"' + ",");

            }

            result.add("    contrast: contrast_default\n" +
                    "},");

        }

        return result;

    }

}
