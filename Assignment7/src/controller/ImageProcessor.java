package controller;

import java.awt.image.BufferedImage;
import java.util.HashMap;

import model.Image;
import utils.ImageUtil;

/**
 * represents the controller of the ImageProcessing program.
 */
public class ImageProcessor implements IProcessor {

  private HashMap<String, model.Image> images;

  /**
   * initializes and constructs ImageProcessor.
   */
  public ImageProcessor() {
    images = new HashMap<>();
  }

  /**
   * represents the method to load the image.
   *
   * @param name     represents the name.
   * @param fileName represents the name of the file.
   */
  public void loadImage(String name, String fileName) {
    try {

      model.Image image = ImageUtil.load(fileName);
      images.put(name, image);
    } catch (Exception e) {
      System.out.println("there was a problem loading the image: " + e.getMessage());
    }
  }

  /**
   * represents the method to save the image.
   *
   * @param name     represents the name.
   * @param fileName represents the filename.
   */
  public void saveImage(String name, String fileName) {
    if (images.containsKey(name)) {
      model.Image image = images.get(name);
      try {
        image.save(fileName);
      } catch (Exception ex) {
        System.out.println("there was a problem saving the image: " + ex.getMessage());
      }
    } else {
      System.out.println("image name not found");
    }
  }

  /**
   * represents the method to brighten the image.
   *
   * @param name          represents the name.
   * @param brightenValue represents the value we are brightening the image.
   * @param destination   represents the destination of the brightening taking place.
   */
  public void brightenImage(String name, int brightenValue, String destination) {
    model.Image originalImage = images.get(name); // get the original
    model.Image brightenImage = new model.Image(originalImage); // duplicate that image
    brightenImage.brighten(brightenValue); // perform brighten
    images.put(destination, brightenImage); // add to hashmap
  }

  /**
   * represents the method to flip an image vertically.
   *
   * @param name        represents the name.
   * @param destination represents the destination of the flipping taking place.
   */
  public void flipVert(String name, String destination) {
    model.Image originalImage = images.get(name);
    model.Image verticalImage = new model.Image(originalImage);
    verticalImage.flipVertically();
    images.put(destination, verticalImage);
  }

  /**
   * represents the method to flip an image horizontally.
   *
   * @param name        represents the name.
   * @param destination represents the destination of the flipping taking place.
   */
  public void flipHor(String name, String destination) {
    model.Image originalImage = images.get(name);
    model.Image horizontalImage = new model.Image(originalImage);
    horizontalImage.flipHorizontally();
    images.put(destination, horizontalImage);
  }

  /**
   * represents the method to make an image greyscale.
   *
   * @param component   represents the component in the method.
   * @param name        represents the name.
   * @param destination represents the destination of the greyscale taking place.
   */
  public void greyscale(String component, String name, String destination) {
    model.Image originalImage = images.get(name);
    model.Image greyscaleImage = new Image(originalImage);
    greyscaleImage.greyscale(component);
    images.put(destination, greyscaleImage);
  }

  /**
   * represents the method with two parameters to make an image greyscale.
   *
   * @param name        represents the name
   * @param destination represents the destination of the greyscale taking place.
   */
  public void greyscale(String name, String destination) {
    model.Image originalImage = images.get(name);
    model.Image greyscaleImage = new Image(originalImage);
    greyscaleImage.greyscale();
    images.put(destination, greyscaleImage);
  }

  /**
   * represents the method to make an image sepia.
   *
   * @param name        represents the name
   * @param destination represents the destination of the sepia taking place.
   */
  public void sepiaImage(String name, String destination) {
    model.Image originalImage = images.get(name);
    model.Image sepiaImage = new Image(originalImage);
    sepiaImage.sepia();
    images.put(destination, sepiaImage);
  }

  /**
   * represents the method to make an image blurred.
   *
   * @param name        represents the name
   * @param destination represents the destination of the blurred taking place.
   */
  public void blurImage(String name, String destination) {
    model.Image originalImage = images.get(name);
    model.Image blurImage = new Image(originalImage);
    blurImage.blur();
    images.put(destination, blurImage);
  }

  /**
   * represents the method to make an image sharpened.
   *
   * @param name        represents the name
   * @param destination represents the destination of the sharpening taking place.
   */
  public void sharpenImage(String name, String destination) {
    model.Image originalImage = images.get(name);
    model.Image sharpenImage = new Image(originalImage);
    sharpenImage.sharpen();
    images.put(destination, sharpenImage);
  }

  /**
   * Represents the method to make a mosaic image.
   *
   * @param name        represents the name
   * @param seed        represents the amount of seeds desired
   * @param destination represents the destination of the mosaic taking place.
   */
  public void mosaicImage(String name, int seed, String destination) {
    model.Image originalImage = images.get(name);
    model.Image mosaicImage = new Image(originalImage);
    mosaicImage.mosaic(seed);
    images.put(destination, mosaicImage);

  }

  /**
   * represents the parseCommand method.
   *
   * @param command represents the command.
   */
  public void parseCommand(String command) {
    String[] tokens = command.split(" ");

    // load command
    if (tokens.length == 3 && tokens[0].equals("load")) {


      String filename = tokens[1];
      String name = tokens[2];

      loadImage(name, filename);
    }

    // brighten command
    else if (tokens.length == 4 && tokens[0].equals("brighten")) {
      int brighten = Integer.parseInt(tokens[1]);
      String name = tokens[2];
      String destination = tokens[3];

      brightenImage(name, brighten, destination);

    }

    //flip vertically command
    else if (tokens.length == 3 && tokens[0].equals("vertical-flip")) {
      String name = tokens[1];
      String destination = tokens[2];
      flipVert(name, destination);

    }

    //flip horizontally command
    else if (tokens.length == 3 && tokens[0].equals("horizontal-flip")) {
      String name = tokens[1];
      String destination = tokens[2];
      flipHor(name, destination);
    }

    // save command
    else if (tokens.length == 3 && tokens[0].equals("save")) {
      String name = tokens[2];
      String filename = tokens[1];
      saveImage(name, filename);
    }

    //red greyscale command
    else if (tokens.length == 3 && tokens[0].equals("red-component")) {
      String name = tokens[1];
      String destination = tokens[2];
      greyscale("red", name, destination);
    }

    //green greyscale command
    else if (tokens.length == 3 && tokens[0].equals("green-component")) {
      String name = tokens[1];
      String destination = tokens[2];
      greyscale("green", name, destination);
    }

    //blue greyscale command
    else if (tokens.length == 3 && tokens[0].equals("blue-component")) {
      String name = tokens[1];
      String destination = tokens[2];
      greyscale("blue", name, destination);
    }

    //value greyscale command
    else if (tokens.length == 3 && tokens[0].equals("value-component")) {
      String name = tokens[1];
      String destination = tokens[2];
      greyscale("value", name, destination);
    }

    //luma greyscale command
    else if (tokens.length == 3 && tokens[0].equals("luma-component")) {
      String name = tokens[1];
      String destination = tokens[2];
      greyscale("luma", name, destination);
    }

    //intensity greyscale command
    else if (tokens.length == 3 && tokens[0].equals("intensity-component")) {
      String name = tokens[1];
      String destination = tokens[2];
      greyscale("intensity", name, destination);
    }
    //sepia command
    else if (tokens.length == 3 && tokens[0].equals("sepia")) {
      String name = tokens[1];
      String destination = tokens[2];
      sepiaImage(name, destination);
    }
    //greyscale command
    else if (tokens.length == 3 && tokens[0].equals("greyscale")) {
      String name = tokens[1];
      String destination = tokens[2];
      greyscale(name, destination);
    }
    // blur command
    else if (tokens.length == 3 && tokens[0].equals("blur")) {
      String name = tokens[1];
      String destination = tokens[2];
      blurImage(name, destination);
    }
    // sharpen command
    else if (tokens.length == 3 && tokens[0].equals("sharpen")) {
      String name = tokens[1];
      String destination = tokens[2];
      sharpenImage(name, destination);
    }
  }

  /**
   * returns the Image if key is found.
   *
   * @param name of Image.
   * @return BufferedImage.
   */
  public BufferedImage getBufferedImage(String name) {
    if (images.containsKey(name)) {
      Image image = images.get(name);
      return image.getBufferedImage();
    } else {
      return null;
    }

  }

  /**
   * returns the component values.
   *
   * @param component represent the component for method.
   * @param name      of file.
   * @return the component values.
   */
  public int[] getComponentCount(String component, String name) {
    if (images.containsKey(name)) {
      Image image = images.get(name);
      return image.getComponentCount(component);
    } else {
      return null;
    }
  }
}