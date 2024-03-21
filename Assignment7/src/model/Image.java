package model;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;

import static java.lang.Integer.MAX_VALUE;

/**
 * Represents the image class.
 */
public class Image implements IImage {

  private int width;
  private int height;
  private int maxValue;
  private Pixel[][] pixels;


  /**
   * Creates an image using pixels.
   *
   * @param width    represents the width of an image.
   * @param height   represents the height of an image.
   * @param maxValue maximum value of a pixel component.
   * @param pixels   represents a two-dimensional array
   *                 that contains the pixel objects that make up the image.
   */
  public Image(int width, int height, int maxValue, Pixel[][] pixels) {
    this.width = width;
    this.height = height;
    this.maxValue = maxValue;
    this.pixels = pixels;

  }

  /**
   * The purpose of this method is to represent the method that calls on the otherImage.
   *
   * @param otherImage represents the otherImage.
   */
  public Image(Image otherImage) {
    this.width = otherImage.width;
    this.height = otherImage.height;
    this.maxValue = otherImage.maxValue;
    this.pixels = new Pixel[otherImage.pixels.length][otherImage.pixels[0].length];
    for (int i = 0; i < otherImage.pixels.length; i++) {
      for (int k = 0; k < otherImage.pixels[i].length; k++) {
        Pixel otherPixel = otherImage.pixels[i][k];
        int red = otherPixel.getRed();
        int green = otherPixel.getGreen();
        int blue = otherPixel.getBlue();
        Pixel pixel = new Pixel(red, green, blue);
        this.pixels[i][k] = pixel;
      }
    }
  }

  /**
   * Represents the method that saves the image.
   *
   * @param filename represents the file name.
   * @throws IOException represents the exception.
   */
  public void save(String filename) throws IOException {

    if (filename.endsWith(".ppm")) {

      StringBuilder imageStr = new StringBuilder();
      imageStr.append("P3" + System.lineSeparator());
      imageStr.append(this.width + " " + this.height + System.lineSeparator());
      imageStr.append(this.maxValue + System.lineSeparator());
      for (int i = 0; i < pixels.length; i++) {
        for (int j = 0; j < pixels[i].length; j++) {
          Pixel pixel = pixels[i][j];
          int red = pixel.getRed();
          int green = pixel.getGreen();
          int blue = pixel.getBlue();
          imageStr.append(red + System.lineSeparator());
          imageStr.append(green + System.lineSeparator());
          imageStr.append(blue + System.lineSeparator());
        }

      }
      File file = new File(filename);
      FileWriter filewriter = new FileWriter(file);
      BufferedWriter bufferedWriter = new BufferedWriter(filewriter);
      bufferedWriter.write(imageStr.toString());
      bufferedWriter.close();
      filewriter.close();
    } else if (filename.endsWith(".jpg") || filename.endsWith(".jpeg") || filename.endsWith(".png")
            || filename.endsWith(".bmp")) {
      BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
      for (int i = 0; i < pixels.length; i++) {
        for (int j = 0; j < pixels[i].length; j++) {
          Pixel pixel = pixels[i][j];
          int red = pixel.getRed();
          int green = pixel.getGreen();
          int blue = pixel.getBlue();
          Color color = new Color(red, green, blue);
          bufferedImage.setRGB(j, i, color.getRGB());

        }
      }

      String filetype = filename.substring(filename.lastIndexOf(".") + 1);
      ImageIO.write(bufferedImage, filetype, new File(filename));
    }

  }

  /**
   * Represents the method that brightens the image.
   *
   * @param value represents the value of the pixel.
   */
  public void brighten(int value) {
    for (int i = 0; i < pixels.length; i++) {
      for (int k = 0; k < pixels[i].length; k++) {
        pixels[i][k].brighten(value, maxValue);
      }
    }
  }

  /**
   * Represents the method that flips the image vertically.
   */
  public void flipVertically() {
    for (int i = 0; i < pixels.length / 2; i++) {
      for (int k = 0; k < pixels[i].length; k++) {
        Pixel temp = pixels[i][k];
        pixels[i][k] = pixels[pixels.length - 1 - i][k];
        pixels[pixels.length - 1 - i][k] = temp;
      }
    }
  }

  /**
   * Represents the method that flips the image horizontally.
   */
  public void flipHorizontally() {
    for (int i = 0; i < pixels.length; i++) {
      for (int k = 0; k < pixels[i].length / 2; k++) {
        Pixel temp = pixels[i][k];
        pixels[i][k] = pixels[i][pixels[i].length - 1 - k];
        pixels[i][pixels[i].length - 1 - k] = temp;
      }
    }
  }

  /**
   * Represents the method that makes the image greyscale.
   *
   * @param component represents the component in the method of the greyscale.
   */
  public void greyscale(String component) {
    for (int i = 0; i < pixels.length; i++) {
      for (int k = 0; k < pixels[i].length; k++) {
        pixels[i][k].greyscale(component);
      }
    }

  }

  /**
   * Represents the method that makes the image greyscale.
   */
  public void greyscale() {
    Pixel[][] greyscalePixels = new Pixel[height][width];
    for (int i = 0; i < pixels.length; i++) {
      for (int k = 0; k < pixels[i].length; k++) {
        Pixel pixel = pixels[i][k];
        double greyscaleValue =
                .2126 * pixel.getRed() + .7152 * pixel.getGreen() + .0722 * pixel.getBlue();
        if (greyscaleValue > maxValue) {
          greyscaleValue = maxValue;
        }
        Pixel greyscalePixel = new Pixel((int) greyscaleValue, (int) greyscaleValue,
                (int) greyscaleValue);
        greyscalePixels[i][k] = greyscalePixel;
      }
    }
    this.pixels = greyscalePixels;
  }

  /**
   * Represents the method that makes the image sepia.
   */
  public void sepia() {
    Pixel[][] sepiaPixels = new Pixel[height][width];
    for (int i = 0; i < pixels.length; i++) {
      for (int k = 0; k < pixels[i].length; k++) {
        Pixel pixel = pixels[i][k];
        double sepiaRed = .393 * pixel.getRed() + .769 * pixel.getGreen() + .189 * pixel.getBlue();
        double sepiaGreen = .349 * pixel.getRed() + .686 * pixel.getGreen()
                + .168 * pixel.getBlue();
        double sepiaBlue = .272 * pixel.getRed() + .534 * pixel.getGreen() + .131 * pixel.getBlue();
        if (sepiaRed > maxValue) {
          sepiaRed = maxValue;
        }
        if (sepiaGreen > maxValue) {
          sepiaGreen = maxValue;
        }
        if (sepiaBlue > maxValue) {
          sepiaBlue = maxValue;
        }
        Pixel sepiaPixel = new Pixel((int) sepiaRed, (int) sepiaGreen, (int) sepiaBlue);
        sepiaPixels[i][k] = sepiaPixel;
      }
    }
    this.pixels = sepiaPixels;
  }


  /**
   * Represents the method to make the image blurred.
   */
  public void blur() {
    Pixel[][] blurredPixels = new Pixel[height][width];
    for (int i = 0; i < pixels.length; i++) {
      for (int k = 0; k < pixels[i].length; k++) {
        double blurredRed = 0;
        double blurredGreen = 0;
        double blurredBlue = 0;

        // first kernel row
        if (i > 0) {

          // northwest neighbor
          if (k > 0) {
            blurredRed += pixels[i - 1][k - 1].getRed() / 16.0;
            blurredGreen += pixels[i - 1][k - 1].getGreen() / 16.0;
            blurredBlue += pixels[i - 1][k - 1].getBlue() / 16.0;
          }

          // north neighbor
          blurredRed += pixels[i - 1][k].getRed() / 8.0;
          blurredGreen += pixels[i - 1][k].getGreen() / 8.0;
          blurredBlue += pixels[i - 1][k].getBlue() / 8.0;

          // northeast neighbor
          if (k < width - 1) {
            blurredRed += pixels[i - 1][k + 1].getRed() / 16.0;
            blurredGreen += pixels[i - 1][k + 1].getGreen() / 16.0;
            blurredBlue += pixels[i - 1][k + 1].getBlue() / 16.0;
          }

        }

        // second row of kernel

        // west neighbor
        if (k > 0) {
          blurredRed += pixels[i][k - 1].getRed() / 8.0;
          blurredGreen += pixels[i][k - 1].getGreen() / 8.0;
          blurredBlue += pixels[i][k - 1].getBlue() / 8.0;
        }

        // center
        blurredRed += pixels[i][k].getRed() / 4.0;
        blurredGreen += pixels[i][k].getGreen() / 4.0;
        blurredBlue += pixels[i][k].getBlue() / 4.0;

        //east neighbor
        if (k < width - 1) {
          blurredRed += pixels[i][k + 1].getRed() / 8.0;
          blurredGreen += pixels[i][k + 1].getGreen() / 8.0;
          blurredBlue += pixels[i][k + 1].getBlue() / 8.0;
        }

        // third row of kernel
        if (i < height - 1) {
          // southwest neighbor
          if (k > 0) {
            blurredRed += pixels[i + 1][k - 1].getRed() / 16.0;
            blurredGreen += pixels[i + 1][k - 1].getGreen() / 16.0;
            blurredBlue += pixels[i + 1][k - 1].getBlue() / 16.0;
          }

          // south neighbor
          blurredRed += pixels[i + 1][k].getRed() / 8.0;
          blurredGreen += pixels[i + 1][k].getGreen() / 8.0;
          blurredBlue += pixels[i + 1][k].getBlue() / 8.0;

          //southeast neighbor
          if (k < width - 1) {
            blurredRed += pixels[i][k + 1].getRed() / 16.0;
            blurredGreen += pixels[i][k + 1].getGreen() / 16.0;
            blurredBlue += pixels[i][k + 1].getBlue() / 16.0;
          }

        }

        if (blurredRed > maxValue) {
          blurredRed = maxValue;
        }

        if (blurredGreen > maxValue) {
          blurredGreen = maxValue;
        }

        if (blurredBlue > maxValue) {
          blurredBlue = maxValue;
        }
        Pixel blurredPixel = new Pixel((int) blurredRed, (int) blurredGreen, (int) blurredBlue);
        blurredPixels[i][k] = blurredPixel;

      }
    }
    this.pixels = blurredPixels;
  }

  /**
   * Represents the method to sharpen the image.
   */
  public void sharpen() {
    Pixel[][] sharpenedPixels = new Pixel[height][width];
    for (int i = 0; i < pixels.length; i++) {
      for (int k = 0; k < pixels[i].length; k++) {
        double sharpenedRed = 0;
        double sharpenedGreen = 0;
        double sharpenedBlue = 0;

        // kernel first row
        if (i > 1) {

          // kernel first col
          if (k > 1) {
            sharpenedRed += pixels[i - 2][k - 2].getRed() * (-.125);
            sharpenedGreen += pixels[i - 2][k - 2].getGreen() * (-.125);
            sharpenedBlue += pixels[i - 2][k - 2].getBlue() * (-.125);

          }
          // kernel second col
          if (k > 0) {
            sharpenedRed += pixels[i - 2][k - 1].getRed() * (-.125);
            sharpenedGreen += pixels[i - 2][k - 1].getGreen() * (-.125);
            sharpenedBlue += pixels[i - 2][k - 1].getBlue() * (-.125);
          }
          // kernel third col
          sharpenedRed += pixels[i - 2][k].getRed() * (-.125);
          sharpenedGreen += pixels[i - 2][k].getGreen() * (-.125);
          sharpenedBlue += pixels[i - 2][k].getBlue() * (-.125);

          // kernel fourth col
          if (k < width - 1) {
            sharpenedRed += pixels[i - 2][k + 1].getRed() * (-.125);
            sharpenedGreen += pixels[i - 2][k + 1].getGreen() * (-.125);
            sharpenedBlue += pixels[i - 2][k + 1].getBlue() * (-.125);
          }
          // kernel fifth col
          if (k < width - 2) {
            sharpenedRed += pixels[i - 2][k + 2].getRed() * (-.125);
            sharpenedGreen += pixels[i - 2][k + 2].getGreen() * (-.125);
            sharpenedBlue += pixels[i - 2][k + 2].getBlue() * (-.125);
          }

        }

        // kernel second row
        if (i > 0) {

          // kernel first col
          if (k > 1) {
            sharpenedRed += pixels[i - 1][k - 2].getRed() * (-.125);
            sharpenedGreen += pixels[i - 1][k - 2].getGreen() * (-.125);
            sharpenedBlue += pixels[i - 1][k - 2].getBlue() * (-.125);

          }
          // kernel second col
          if (k > 0) {
            sharpenedRed += pixels[i - 1][k - 1].getRed() * .25;
            sharpenedGreen += pixels[i - 1][k - 1].getGreen() * .25;
            sharpenedBlue += pixels[i - 1][k - 1].getBlue() * .25;
          }
          // kernel third col
          sharpenedRed += pixels[i - 1][k].getRed() * .25;
          sharpenedGreen += pixels[i - 1][k].getGreen() * .25;
          sharpenedBlue += pixels[i - 1][k].getBlue() * .25;

          // kernel fourth col
          if (k < width - 1) {
            sharpenedRed += pixels[i - 1][k + 1].getRed() * .25;
            sharpenedGreen += pixels[i - 1][k + 1].getGreen() * .25;
            sharpenedBlue += pixels[i - 1][k + 1].getBlue() * .25;
          }
          // kernel fifth col
          if (k < width - 2) {
            sharpenedRed += pixels[i - 1][k + 2].getRed() * (-.125);
            sharpenedGreen += pixels[i - 1][k + 2].getGreen() * (-.125);
            sharpenedBlue += pixels[i - 1][k + 2].getBlue() * (-.125);
          }
        }
        // third row

        // kernel first col
        if (k > 1) {
          sharpenedRed += pixels[i][k - 2].getRed() * (-.125);
          sharpenedGreen += pixels[i][k - 2].getGreen() * (-.125);
          sharpenedBlue += pixels[i][k - 2].getBlue() * (-.125);

        }
        // kernel second col
        if (k > 0) {
          sharpenedRed += pixels[i][k - 1].getRed() * .25;
          sharpenedGreen += pixels[i][k - 1].getGreen() * .25;
          sharpenedBlue += pixels[i][k - 1].getBlue() * .25;
        }
        // kernel third col
        sharpenedRed += pixels[i][k].getRed();
        sharpenedGreen += pixels[i][k].getGreen();
        sharpenedBlue += pixels[i][k].getBlue();

        // kernel fourth col
        if (k < width - 1) {
          sharpenedRed += pixels[i][k + 1].getRed() * .25;
          sharpenedGreen += pixels[i][k + 1].getGreen() * .25;
          sharpenedBlue += pixels[i][k + 1].getBlue() * .25;
        }
        // kernel fifth col
        if (k < width - 2) {
          sharpenedRed += pixels[i][k + 2].getRed() * (-.125);
          sharpenedGreen += pixels[i][k + 2].getGreen() * (-.125);
          sharpenedBlue += pixels[i][k + 2].getBlue() * (-.125);
        }

        // kernel fourth row
        if (i < height - 1) {

          // kernel first col
          if (k > 1) {
            sharpenedRed += pixels[i + 1][k - 2].getRed() * (-.125);
            sharpenedGreen += pixels[i + 1][k - 2].getGreen() * (-.125);
            sharpenedBlue += pixels[i + 1][k - 2].getBlue() * (-.125);

          }
          // kernel second col
          if (k > 0) {
            sharpenedRed += pixels[i + 1][k - 1].getRed() * .25;
            sharpenedGreen += pixels[i + 1][k - 1].getGreen() * .25;
            sharpenedBlue += pixels[i + 1][k - 1].getBlue() * .25;
          }
          // kernel third col
          sharpenedRed += pixels[i + 1][k].getRed() * .25;
          sharpenedGreen += pixels[i + 1][k].getGreen() * .25;
          sharpenedBlue += pixels[i + 1][k].getBlue() * .25;

          // kernel fourth col
          if (k < width - 1) {
            sharpenedRed += pixels[i + 1][k + 1].getRed() * .25;
            sharpenedGreen += pixels[i + 1][k + 1].getGreen() * .25;
            sharpenedBlue += pixels[i + 1][k + 1].getBlue() * .25;
          }
          // kernel fifth col
          if (k < width - 2) {
            sharpenedRed += pixels[i + 1][k + 2].getRed() * (-.125);
            sharpenedGreen += pixels[i + 1][k + 2].getGreen() * (-.125);
            sharpenedBlue += pixels[i + 1][k + 2].getBlue() * (-.125);
          }

        }

        // kernel fifth row
        if (i < height - 2) {

          // kernel first col
          if (k > 1) {
            sharpenedRed += pixels[i + 2][k - 2].getRed() * (-.125);
            sharpenedGreen += pixels[i + 2][k - 2].getGreen() * (-.125);
            sharpenedBlue += pixels[i + 2][k - 2].getBlue() * (-.125);

          }
          // kernel second col
          if (k > 0) {
            sharpenedRed += pixels[i + 2][k - 1].getRed() * (-.125);
            sharpenedGreen += pixels[i + 2][k - 1].getGreen() * (-.125);
            sharpenedBlue += pixels[i + 2][k - 1].getBlue() * (-.125);
          }
          // kernel third col
          sharpenedRed += pixels[i + 2][k].getRed() * (-.125);
          sharpenedGreen += pixels[i + 2][k].getGreen() * (-.125);
          sharpenedBlue += pixels[i + 2][k].getBlue() * (-.125);

          // kernel fourth col
          if (k < width - 1) {
            sharpenedRed += pixels[i + 2][k + 1].getRed() * (-.125);
            sharpenedGreen += pixels[i + 2][k + 1].getGreen() * (-.125);
            sharpenedBlue += pixels[i + 2][k + 1].getBlue() * (-.125);
          }
          // kernel fifth col
          if (k < width - 2) {
            sharpenedRed += pixels[i + 2][k + 2].getRed() * (-.125);
            sharpenedGreen += pixels[i + 2][k + 2].getGreen() * (-.125);
            sharpenedBlue += pixels[i + 2][k + 2].getBlue() * (-.125);
          }

        }

        if (sharpenedRed > maxValue) {
          sharpenedRed = maxValue;
        }
        if (sharpenedGreen > maxValue) {
          sharpenedGreen = maxValue;
        }
        if (sharpenedBlue > maxValue) {
          sharpenedBlue = maxValue;
        }
        if (sharpenedRed < 0) {
          sharpenedRed = 0;
        }
        if (sharpenedGreen < 0) {
          sharpenedGreen = 0;
        }
        if (sharpenedBlue < 0) {
          sharpenedBlue = 0;
        }
        Pixel sharpenedPixel = new Pixel((int) sharpenedRed, (int) sharpenedGreen,
                (int) sharpenedBlue);
        sharpenedPixels[i][k] = sharpenedPixel;
      }
    }
    this.pixels = sharpenedPixels;
  }


  /**
   * Represents the method to apply a mosaic filter to the image.
   *
   * @param seeds represents the desired amount of seeds.
   */
  @Override
  public void mosaic(int seeds) {
    Map<Point, List<Point>> clusters = new HashMap<>();
    Pixel[][] mosaicPixels = new Pixel[this.height][this.width];
    Random random = new Random();

    // randomly creating seeds based on dimenions of image and
    // putting them into a hashmap with correlation pixels
    for (int i = 0; i < seeds; i++) {
      int randomX = random.nextInt(this.width);
      int randomY = random.nextInt(this.height);
      Point seed = new Point(randomX, randomY);
      clusters.put(seed, new ArrayList<>());
    }

    // each pixel in the image is paired to the seed closest to it (by distance)
    // and then added to the hashmap
    for (int row = 0; row < this.height; row++) {
      for (int col = 0; col < this.width; col++) {
        Point closestSeed = new Point(MAX_VALUE, MAX_VALUE);
        Point imagePixel = new Point(col, row);
        for (Point seed : clusters.keySet()) {
          closestSeed = distance(row, col, closestSeed, seed);
        }
        clusters.get(closestSeed).add(imagePixel);
      }
    }

    // sets the pixels to the average color of each cluster
    for (Point seed : clusters.keySet()) {
      int[] averageColor = averageColor(clusters.get(seed));
      for (Point pixel : clusters.get(seed)) {
        mosaicPixels[pixel.x][pixel.y] = new Pixel(averageColor[0], averageColor[1],
                averageColor[2]);
      }
    }

    this.pixels = mosaicPixels;
  }

  private static Point distance(int y, int x, Point closestSeed, Point seed) {
    double x1 = Math.pow(x - seed.x, 2);
    double y1 = Math.pow(y - seed.y, 2);
    double distance1 = Math.sqrt(x1 + y1);

    double x2 = Math.pow(x - closestSeed.x, 2);
    double y2 = Math.pow(y - closestSeed.y, 2);
    double distance2 = Math.sqrt(x2 + y2);

    if (distance1 < distance2) {
      closestSeed = seed;
    }
    return closestSeed;
  }

  private int[] averageColor(List<Point> clusterPixels) {
    int red = 0;
    int green = 0;
    int blue = 0;

    for (Point p : clusterPixels) {
      Pixel pixel = this.pixels[p.x][p.y];
      red += pixel.getRed();
      green += pixel.getGreen();
      blue += pixel.getBlue();
    }

    return new int[]{red / clusterPixels.size(), green / clusterPixels.size(),
                     blue / clusterPixels.size()
    };
  }


  /**
   * Returns the bufferedImage from calculating pixelValues.
   *
   * @return bufferedImage.
   */
  public BufferedImage getBufferedImage() {
    BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    for (int i = 0; i < pixels.length; i++) {
      for (int j = 0; j < pixels[i].length; j++) {
        Pixel pixel = pixels[i][j];
        int red = pixel.getRed();
        int green = pixel.getGreen();
        int blue = pixel.getBlue();
        Color color = new Color(red, green, blue);
        bufferedImage.setRGB(j, i, color.getRGB());

      }
    }
    return bufferedImage;

  }

  /**
   * Returns the component values.
   *
   * @param component represents component parameter in method.
   * @return an integer with component values.
   */
  public int[] getComponentCount(String component) {
    int[] componentCount = new int[maxValue + 1];
    for (int i = 0; i < componentCount.length; i++) {
      componentCount[i] = 0;
    }
    for (int i = 0; i < pixels.length; i++) {
      for (int j = 0; j < pixels[i].length; j++) {
        Pixel pixel = pixels[i][j];
        if (component.equals("red")) {
          int redValue = pixel.getRed();
          componentCount[redValue] += 1;
        }
        if (component.equals("green")) {
          int greenValue = pixel.getGreen();
          componentCount[greenValue] += 1;
        }
        if (component.equals("blue")) {
          int blueValue = pixel.getBlue();
          componentCount[blueValue] += 1;
        }
        if (component.equals("intensity")) {
          int intensityValue = (pixel.getRed() + pixel.getGreen() + pixel.getBlue()) / 3;
          componentCount[intensityValue] += 1;
        }
      }
    }
    return componentCount;
  }

}



