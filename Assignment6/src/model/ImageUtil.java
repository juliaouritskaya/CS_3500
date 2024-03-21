package model;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import javax.imageio.ImageIO;

/**
 * This class contains utility methods to read a PPM image from file and simply print its contents.
 * Feel free to change this method as required.
 */
public class ImageUtil {

  /**
   * Method that loads conventional file formats (bmp, jpg and png)
   * in addition to ASCII ppm files from before.
   * @param filename the path of the file
   * @return an instance of an image
   */
  public static Image load(String filename) {
    BufferedImage image;
    Pixel[][] pixels;

    if (filename.endsWith(".ppm")) {
      return readPPM(filename);
    }

    try {
      image = ImageIO.read(new FileInputStream(filename));
    } catch (IOException e) {
      throw new IllegalArgumentException("Unable to read the file.");
    }

    int width = image.getWidth();
    int height = image.getHeight();


    pixels = new Pixel[width][height];

    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        Color color = new Color(image.getRGB(col, row));
        int red = color.getRed();
        int green = color.getGreen();
        int blue = color.getBlue();
        pixels[col][row] = new Pixel(red, green, blue);
      }
    }
    return new ImageImpl(pixels);
  }

  /**
   * Read an image file in the PPM format and print the colors.
   *
   * @param filename the path of the file.
   */
  public static Image readPPM(String filename) {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      System.out.println("File " + filename + " not found!");
      return null;
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    System.out.println("Width of image: " + width);
    int height = sc.nextInt();
    System.out.println("Height of image: " + height);
    int maxValue = sc.nextInt();
    System.out.println("Maximum value of a color in this file (usually 255): " + maxValue);

    Pixel[][] pixels = new Pixel[width][height];

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        pixels[j][i] = new Pixel(r, g, b);
        //System.out.println("Color of pixel ("+j+","+i+"): "+ r+","+g+","+b);
      }
    }

    return new ImageImpl(pixels);
  }

  /**
   * Method that saves conventional file formats (bmp, jpg and png)
   * in addition to ASCII ppm files from before.
   * @param filename the path of the file
   */
  public static void save(String filename, Image image) {
    String[] fileTypes = {".ppm", ".jpeg", ".png", ".bmp"};
    String type;
    if (filename.endsWith(".jpeg")) {
      type = filename.substring(filename.length() - 4);
    } else {
      type = filename.substring(filename.length() - 3);
    }

    if (filename.endsWith(".ppm")) {
      writePPM(filename, image);
      return;
    }

    BufferedImage toSave = new BufferedImage(image.getWidth(), image.getHeight(),
            BufferedImage.TYPE_INT_RGB);

    Pixel[][] pixels = image.getPixels();

    for (int row = 0; row < image.getHeight(); row++) {
      for (int col = 0; col < image.getWidth(); col++) {
        // what i had int red = pixels[row][col].getRed();
        int red = pixels[col][row].getRed();
        // int green = pixels[row][col].getGreen();
        int green = pixels[col][row].getGreen();
        // int blue = pixels[row][col].getBlue();
        int blue = pixels[col][row].getBlue();//rec
        int color = (red << 16) + (green << 8) + blue;
        toSave.setRGB(col, row, color);
      }
    }

    try {
      ImageIO.write(toSave, type, new File(filename));
    } catch (IOException e) {
      throw new IllegalArgumentException("Unable to save file.");
    }
  }

  /**
   * Write an image file in the PPM format.
   *
   * @param fileName the path of the file.
   * @param image    the image to write.
   */
  public static void writePPM(String fileName, Image image) {

    try {
      Pixel[][] pixels = image.getPixels();
      int width = pixels.length;
      //int width = image.getWidth();
      int height = pixels[0].length;
      //int height = image.getHeight();

      FileWriter fwriter = new FileWriter(fileName);

      fwriter.write("P3\n");
      fwriter.write("# Created by CS3500 students Julia Ouritskaya and Evelyn Robert\n");
      fwriter.write(String.valueOf(width));
      fwriter.write(' ');
      fwriter.write(String.valueOf(height));
      fwriter.write('\n');
      fwriter.write(String.valueOf(255));
      fwriter.write('\n'); // max val

      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          fwriter.write(String.valueOf(pixels[j][i].getRed()));
          fwriter.write('\n');
          fwriter.write(String.valueOf(pixels[j][i].getGreen()));
          fwriter.write('\n');
          fwriter.write(String.valueOf(pixels[j][i].getBlue()));
          fwriter.write('\n');
        }
      }
      fwriter.close();
    } catch (IOException e) {
      System.out.println("An error occurred");
      throw new IllegalStateException("An file write error occurred");
    }
  }

  /**
   * Creates a new copy of the image.
   * @param image the image to clone
   * @return the cloned image
   */
  public static Image cloneImage(Image image) {
    Pixel[][] pixels = image.getPixels();
    int width = pixels.length;
    int height = pixels[0].length;

    Pixel[][] pixels2 = new Pixel[width][height];

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = pixels[j][i].getRed();
        int g = pixels[j][i].getGreen();
        int b = pixels[j][i].getBlue();
        pixels2[j][i] = new Pixel(r, g, b);
      }
    }

    return new ImageImpl(pixels2);
  }

  public static BufferedImage Image2BufferedImage(Image image){
    Pixel[][] pixels = image.getPixels();
    int width = pixels.length;
    int height =pixels[0].length;

    BufferedImage bImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

    for(int i=0; i<height; i++){
      for(int j=0; j<width; j++){
        bImage.setRGB(j, i,
                (pixels[j][i].getRed()<<16) +
                        (pixels[j][i].getGreen()<<8) +
                        pixels[j][i].getBlue());
      }
    }
    return bImage;
  }

  /**
   * main method of the program.
   *
   * @param args any command line arguments
   */
  public static void main(String[] args) {
    String filename;


    if (args.length > 0) {
      filename = args[0];
    } else {
      filename = "sample.ppm";
    }

    Image image = ImageUtil.readPPM(filename);
    ImageUtil.writePPM("O_" + filename, image);
  }
}

