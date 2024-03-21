package model;

import java.awt.*;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * This class represents an image, with corresponding pixels, width, and height.
 */
public class ImageImpl implements Image {
  private Pixel[][] pixels;
  protected int width;
  protected int height;

  /**
   * Creates an instance of an image with specified pixels, width, and height.
   *
   * @param pixels the 2D array representing the pixels that make up the image
   * @throws IllegalArgumentException if the parameters are null or negative
   */
  public ImageImpl(Pixel[][] pixels) throws IllegalArgumentException {
    if (pixels == null || pixels[0].length < 1) {
      throw new IllegalArgumentException("Pixels must be non-null and contain elements.");
    } else {
      for (int col = 0; col < pixels.length; col++) {
        if (pixels[col].length != pixels[0].length) {
          throw new IllegalArgumentException("Number of pixels in each row is not the same.");
        } else {
          this.width = pixels[0].length;
          //this.width = pixels.length;
          this.height = pixels.length;
          //this.height = pixels[0].length;
          this.pixels = pixels;
        }
      }
    }
  }

  /**
   * Flips the image vertically by flipping its pixel composition.
   */
  @Override
  public void flipHorizontal() {
    for (int col = 0; col < this.width; col++) {
      int first = 0;
      int last = this.height - 1;

      while (first < last) {
        Pixel temp = this.pixels[first][col];
        this.pixels[first][col] = this.pixels[last][col];
        this.pixels[last][col] = temp;
        first++;
        last--;
      }
    }
  }

  @Override
  public void flipVertical() {
    for (int row = 0; row < this.height; row++) {
      int first = 0;
      int last = this.width - 1;

      while (first < last) {
        Pixel temp = this.pixels[row][first];
        this.pixels[row][first] = this.pixels[row][last];
        this.pixels[row][last] = temp;
        first++;
        last--;
      }
    }
  }

  /**
   * Method to brighten or darken and image.
   *
   * @param component how much you want the image to be lightened or darkened.
   */
  @Override
  public void brighten(int component) {

    for (int col = 0; col < this.width; col++) {
      for (int row = 0; row < this.height; row++) {
        int r = this.pixels[row][col].red;
        int g = this.pixels[row][col].green;
        int b = this.pixels[row][col].blue;


        this.pixels[row][col].red = Math.max(0, Math.min(r + component, 255));
        this.pixels[row][col].blue = Math.max(0, Math.min(b + component, 255));
        this.pixels[row][col].green = Math.max(0, Math.min(g + component, 255));
      }
    }
  }

  /**
   * Method to turn an image into greyscale using an inputted component.
   *
   * @param type the desired component, either luma, value, intensity red, green or blue.
   */
  public void component(String type) {
    for (int col = 0; col < this.width; col++) {
      for (int row = 0; row < this.height; row++) {
        int comp = 0;
        if (type.equalsIgnoreCase("red")) {
          comp = pixels[row][col].getRed();
        } else if (type.equalsIgnoreCase("green")) {
          comp = pixels[row][col].getGreen();
        } else if (type.equalsIgnoreCase("blue")) {
          comp = pixels[row][col].getBlue();
        } else if (type.equalsIgnoreCase("luma")) {
          comp = (int) (this.pixels[row][col].blue * 0.0722
                  + this.pixels[row][col].red * 0.2126
                  + this.pixels[row][col].green * 0.7152);
        } else if (type.equalsIgnoreCase("intensity")) {
          int average = 0;
          average += this.pixels[row][col].getRed();
          average += this.pixels[row][col].getBlue();
          average += this.pixels[row][col].getGreen();

          comp = Math.round(average / 3);
        } else if (type.equalsIgnoreCase("value")) {
          int temp = Math.max(this.pixels[row][col].blue,
                  this.pixels[row][col].red);
          comp = Math.max(temp, this.pixels[row][col].green);
        }
        this.pixels[row][col].red = comp;
        this.pixels[row][col].green = comp;
        this.pixels[row][col].blue = comp;
      }
    }
  }

  /**
   * Returns the pixels that make up the image.
   *
   * @return the pixels that make up the image
   */
  @Override
  public Pixel[][] getPixels() {
    Pixel[][] copy = new Pixel[this.height][this.width];
    //Pixel[][] copy = new Pixel[this.width][this.height];
    for (int row = 0; row < this.height; row++) {
      for (int col = 0; col < this.width; col++) {
        Pixel temp = this.pixels[row][col];
        //Pixel temp = this.pixels[col][row];
        copy[row][col] = new Pixel(temp.getRed(), temp.getGreen(), temp.getBlue());
        //copy[col][row] = new Pixel(temp.getRed(), temp.getGreen(), temp.getBlue());
      }
    }
    return this.pixels;
  }

  /**
   * Returns the pixel at the specified position.
   *
   * @param row the row position of the pixel
   * @param col the column positon of the pixel
   * @return the pixel at the specified row and column
   */
  @Override
  public Pixel getPixelAt(int row, int col) {
    if (row < 0 || row > this.width || col < 0 || col > this.height) {
      throw new IllegalArgumentException("Invalid pixel.");
    } else {
      return this.pixels[row][col];
    }
  }

  /**
   * Returns the width of the image.
   *
   * @return the width of the image
   */
  @Override
  public int getWidth() {
    return this.width;
  }

  /**
   * Returns the height of the image.
   *
   * @return the height of the image
   */
  @Override
  public int getHeight() {
    return this.height;
  }

  // my attempt at blur

  /**
   * Method to add a blur filter to an image.
   */
  @Override
  public void blur() {
    double [][] values = {
            {1.0 / 16.0, 1.0 / 8.0, 1.0 / 16.0},
            {1.0 / 8.0, 1.0 / 4.0, 1.0 / 8.0},
            {1.0 / 16.0, 1.0 / 8.0, 1.0 / 16.0}
    };
    kernalHelp(values);
  }

  /**
   * Method to add sharpen filter to an image.
   */
  @Override
  public void sharpen() {
    double[][] sharpen = {
            {-0.125, -0.125, -0.125, -0.125, -0.125},
            {-0.125, 0.25, 0.25, 0.25, -0.125},
            {-0.125, 0.25, 1.0, 0.25, -0.125},
            {-0.125, 0.25, 0.25, 0.25, -0.125},
            {-0.125, -0.125, -0.125, -0.125, -0.125}
    };

    kernalHelp(sharpen);
  }

  private void kernalHelp(double[][] values) {
    int kernalSize = values.length;
    for (int row = 0; row < this.height; row++) {
      for (int col = 0; col < this.width; col++) {
        double r = 0;
        double g = 0;
        double b = 0;
        int bounds = (kernalSize / 2);

        for (int j = -bounds; j <= bounds; j++) {
          for (int k = -bounds; k <= bounds; k++) {
            if ((row + j > -1 && row + 1 < this.height - 1
                    && col + k > -1 && col + k < this.width - 1)) {

              r += pixels[row + j][col + k].getRed() * values[j + bounds][k + bounds];
              g += pixels[row + j][col + k].getGreen() * values[j + bounds][k + bounds];
              b += pixels[row + j][col + k].getBlue() * values[j + bounds][k + bounds];
            }
          }
        }

        int red = truncate(r);
        int green = truncate(g);
        int blue = truncate(b);

        this.pixels[row][col].red = red;
        this.pixels[row][col].green = green;
        this.pixels[row][col].blue = blue;
      }
    }
  }

  private int truncate(double x) {
    return (int) (Math.min(255, Math.max(0, x)));
  }

  /**
   * Method to add a sepia filter to an image.
   */
  @Override
  public void sepia() {
    double[][] sepia = {
            {0.393, 0.769, 0.189},
            {0.349, 0.686, 0.168},
            {0.272, 0.534, 0.131}
    };

    for (int row = 0; row < this.height; row++) {
      for (int col = 0; col < this.width; col++) {
        double r = this.pixels[row][col].red;
        double g = this.pixels[row][col].green;
        double b = this.pixels[row][col].blue;
        double red = sepia[0][0] * r + sepia[0][1] * g + sepia[0][2] * b;
        double green = sepia[1][0] * r + sepia[1][1] * g + sepia[1][2] * b;
        double blue = sepia[2][0] * r + sepia[2][1] * g + sepia[2][2] * b;

        if (red > 255 || green > 255 || blue > 255) {
          red = 255;
          green = 255;
          blue = 255;
        } else {
          this.pixels[row][col].red = (int) red;
          this.pixels[row][col].green = (int) green;
          this.pixels[row][col].blue = (int) blue;
        }
      }
    }
  }


  /**
   * Method to convert a color image into a greyscale image.
   */
  @Override
  public void greyscale() {
    double[][] values = {
            {0.393, 0.769, 0.189},
            {0.393, 0.769, 0.189},
            {0.393, 0.769, 0.189}
    };

    for (int row = 0; row < this.height; row++) {
      for (int col = 0; col < this.width; col++) {
        int r = this.pixels[row][col].red;
        int g = this.pixels[row][col].green;
        int b = this.pixels[row][col].blue;

        double red = Math.min(255, r * values[0][0] + g * values[0][1] + b * values[0][2]);
        double green = Math.min(255, r * values[1][0] + g * values[1][1] + b * values[1][2]);
        double blue = Math.min(255, r * values[1][0] + g * values[1][1] + b * values[1][2]);

        this.pixels[row][col].red = (int) (red);
        this.pixels[row][col].green = (int) (green);
        this.pixels[row][col].blue = (int) (blue);
      }
    }
  }


  /**
   * Return a string that represents the current state of the image.
   *
   * @return the image state as a string
   */
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(this.width + " ");
    stringBuilder.append(this.height + "\n");
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        if (j < this.height - 1) {
          stringBuilder.append(this.pixels[i][j].toString()).append(" ");
        } else {
          stringBuilder.append(this.pixels[i][j].toString()).append("\n");
        }
      }
    }
    return stringBuilder.toString();
  }

  /**
   * Creates a 2D array of the RGB value components and how frequently they occur.
   * @return a 2D array that represents the histogram.
   */
  public int [][] histogram() {
    int[][] h = new int[4][256];

    for (Pixel[] row : this.pixels) {
      for (Pixel p : row) {
        h[0][p.getRed()] += 1;
        h[1][p.getGreen()] += 1;
        h[2][p.getBlue()] += 1;
        h[3][(int) Math.round( (double) p.getRed() + p.getGreen() + p.getBlue()) / 3] += 1;
      }
    }
    return h;
  }

  /**
   * represents the method to apply a mosaic filter to the image.
   */
  @Override
  public void mosaic (int seeds) {
    Random random = new Random();
    List<Pixel> chosenSeeds = new ArrayList<>();

    // looping through pixels in image to create a list of seeds
    // that correlate to points in the image
    for (int i = 0; i <= seeds; i++) {
      int randomRow = random.nextInt(this.pixels.length);
      int randomCol = random.nextInt(this.pixels[randomRow].length);
      int red = this.pixels[randomRow][randomCol].getRed();
      int green = this.pixels[randomRow][randomCol].getGreen();
      int blue = this.pixels[randomRow][randomCol].getBlue();
      Pixel pixel = new Pixel(randomRow, randomCol, red, green, blue);
      chosenSeeds.add(pixel);
    }

    // each pixel in the image is paired to the seed closest to it (by distance)
    for (int row = 0; row < this.pixels.length; row++) {
      for (int col = 0; col < this.pixels[row].length; col++) {
        Point point = new Point(row, col);
        List<Double> distances = new ArrayList<>();

        for (Pixel seed : chosenSeeds) {
          distances.add(point.distance(seed.getRow(), seed.getCol()));
        }
        Pixel mosaicSeed = chosenSeeds.get(distances.indexOf(Collections.min(distances)));
        this.pixels[row][col] = this.pixels[mosaicSeed.getRow()][mosaicSeed.getCol()];
      }
    }
  }

  private int [] averageColors(List<Point> seeds) {
    int red = 0;
    int green = 0;
    int blue = 0;

    for (Point p : seeds) {
      Pixel pixel = this.pixels[p.x][p.y];
      red += pixel.getRed();
      green += pixel.getGreen();
      blue += pixel.getBlue();
    }

    if (seeds.size() == 0) {
      return new int [] {0, 0, 0};
    }

    return new int[] {red / seeds.size(), green / seeds.size(), blue / seeds.size()};
  }
}


