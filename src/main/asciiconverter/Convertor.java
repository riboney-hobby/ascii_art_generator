package main.asciiconverter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class Convertor {

    // Loading an image file
    public static BufferedImage loadImage(File imgFile) throws IOException {

        return ImageIO.read(imgFile);
    }

    // Resize image
    // https://stackoverflow.com/questions/4756268/how-to-resize-the-buffered-image-n-graphics-2d-in-java
    public static BufferedImage scaleImage(BufferedImage img, int width, int height,
                                    Color background) {
        int imgWidth = img.getWidth();
        int imgHeight = img.getHeight();
        if (imgWidth*height < imgHeight*width) {
            width = imgWidth*height/imgHeight;
        } else {
            height = imgHeight*width/imgWidth;
        }
        BufferedImage newImage = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        Graphics2D g = newImage.createGraphics();
        try {
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                    RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            // these lines are for creating image background? Not needed, but saving just in case
            //g.setBackground(background);
            //g.clearRect(0, 0, width, height);
            g.drawImage(img, 0, 0, width, height, null);
        } finally {
            g.dispose();
        }
        return newImage;
    }

    public static String getAppropriateString(int greyscaleValue) {
        String out = " ";

        if (greyscaleValue >= 230)
        {
            out = "@";
        }
        else if (greyscaleValue >= 200)
        {
            out = "#";
        }
        else if (greyscaleValue >= 180)
        {
            out = "8";
        }
        else if (greyscaleValue >= 160)
        {
            out = "&";
        }
        else if (greyscaleValue >= 130)
        {
            out = "o";
        }
        else if (greyscaleValue >= 100)
        {
            out = ":";
        }
        else if (greyscaleValue >= 70)
        {
            out = "*";
        }
        else if (greyscaleValue >= 50)
        {
            out = ".";
        }
        else
        {
            out = " ";
        }

        return out;
    }

    public static String getGreyScaleValues(int pixel) {
        // Get the first 16 bits of each pixel and store them as integer values
        int blue = pixel & 0xff;
        int green = (pixel & 0xff00) >> 8;
        int red = (pixel & 0xff0000) >> 16;

        int sumOfCurrentPixelValues = (red / 3)
                + (green / 3) + (blue / 3);

        return getAppropriateString(sumOfCurrentPixelValues);
    }

    public static void printPixel(int pixel) {
        System.out.print(getGreyScaleValues(pixel));
    }

    public static void walkThroughImage(File imgFile) throws IOException {
        BufferedImage img = loadImage(imgFile);
        img = scaleImage(img, 64,64,null);

        int width = img.getWidth();
        int height = img.getHeight();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                printPixel(img.getRGB(x, y));
            }
            System.out.println();
        }
    }

    // Converting image to ascii
    public static void imageToAscii(File imgFile) throws IOException {
        walkThroughImage(imgFile);
    }
}
