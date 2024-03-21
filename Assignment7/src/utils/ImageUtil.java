package utils;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileInputStream;

import javax.imageio.ImageIO;
import model.Image;

import model.Pixel;


/**
 * The purpose of this is to represent the ImageUtil class.
 */
public class ImageUtil {

  /**
   * Read an image file in the PPM format and print the colors.
   *
   * @param filename the path of the file.
   */
  public static void readPPM(String filename) {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      System.out.println("File " + filename + " not found!");
      return;
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

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        System.out.println("Color of pixel (" + j + "," + i + "): " + r + "," + g + "," + b);
      }
    }
  }

  /**
   * The purpose of this method is to read an image in PPM format and store the colors
   * in a string that is returned.
   *
   * @param filename represents the filename.
   * @return returns the value.
   */
  public static String ppmString(String filename) {
    Scanner sc;
    if (filename.endsWith(".ppm")) {
      try {
        sc = new Scanner(new FileInputStream(filename));
      } catch (FileNotFoundException e) {
        System.out.println("File " + filename + " not found!");
        return "";
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
      String output = "";
      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          int r = sc.nextInt();
          int g = sc.nextInt();
          int b = sc.nextInt();
          output = ("Color of pixel (" + j + "," + i + "): " + r + "," + g + "," + b);
        }
      }
      return output;
    } else if (filename.endsWith(".jpg") || filename.endsWith(".jpeg") || filename.endsWith(".png")
            || filename.endsWith(".bmp")) {
      try {
        BufferedImage bufferedImage = ImageIO.read(new File(filename));
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();
        int maxValue = 255;
        Pixel[][] pixels = new Pixel[height][width];
        for (int h = 0; h < height; h++) {
          for (int w = 0; w < width; w++) {
            Color color = new Color(bufferedImage.getRGB(w, h));
            int red = color.getRed();
            int green = color.getGreen();
            int blue = color.getBlue();
            Pixel pixel = new Pixel(red, green, blue);
            pixels[h][w] = pixel;
          }
        }
        String i = "width:" + width + "height: " + height + "maxValue: " +
                maxValue;

        return i;
      } catch (IOException ex) {
        System.out.println("not valid file");

      }
    }

    return "";
  }


  /**
   * represents the method being used in the image class that uses the filename.
   *
   * @param filename represents the name of the file.
   * @throws Exception represents throwing an exception.
   */
  public static Image load(String filename) throws Exception {

    filename = filename.toLowerCase();
    if (filename.endsWith(".ppm")) {
      Scanner scanner = new Scanner(new FileInputStream(filename));
      StringBuilder imageStr = new StringBuilder();
      while (scanner.hasNext()) {
        String line = scanner.nextLine();
        if (!line.startsWith("#")) {
          imageStr.append(line + System.lineSeparator());
        }
      }
      scanner = new Scanner(imageStr.toString());
      String imageType = scanner.next();
      if (!imageType.equals("P3")) {
        throw new Exception("invalid PPM file");

      }
      int width = scanner.nextInt();
      int height = scanner.nextInt();
      int maxValue = scanner.nextInt();

      Pixel[][] pixels = new Pixel[height][width];
      for (int r = 0; r < height; r++) {
        for (int c = 0; c < width; c++) {
          int red = scanner.nextInt();
          int green = scanner.nextInt();
          int blue = scanner.nextInt();
          Pixel pixel = new Pixel(red, green, blue);
          pixels[r][c] = pixel;
        }
      }


      scanner.close();

      return new Image(width, height, maxValue, pixels);
    } else if (filename.endsWith(".jpg") || filename.endsWith(".jpeg") || filename.endsWith(".png")
            || filename.endsWith(".bmp")) {

      try {
        BufferedImage bufferedImage = ImageIO.read(new File(filename));
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();
        int maxValue = 255;
        Pixel[][] pixels = new Pixel[height][width];
        for (int h = 0; h < height; h++) {
          for (int w = 0; w < width; w++) {
            Color color = new Color(bufferedImage.getRGB(w, h));
            int red = color.getRed();
            int green = color.getGreen();
            int blue = color.getBlue();
            Pixel pixel = new Pixel(red, green, blue);
            pixels[h][w] = pixel;
          }
        }

        return new Image(width, height, maxValue, pixels);
      } catch (IOException ex) {
        System.out.println("Invalid file");

      }
    }

    return new Image(0,0,0,new Pixel[0][0]);
  }


  //demo main

  /**
   * The purpose of this method is to represent the main method.
   *
   * @param args represents the arguments.
   */
  public static void main(String[] args) {
    String filename;

    if (args.length > 0) {
      filename = args[0];
    } else {
      filename = "koala.ppm";
    }

    ImageUtil.readPPM(filename);
  }
}


