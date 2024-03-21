package controller;

import java.awt.image.BufferedImage;

/**
 * represents the controller of the Image Processing.
 */
public interface IProcessor {
  /**
   * represents the method to load the image.
   *
   * @param name     represents the name.
   * @param fileName represents the name of the file.
   */
  void loadImage(String name, String fileName);

  /**
   * represents the method to save the image.
   *
   * @param name     represents the name.
   * @param fileName represents the filename.
   */
  void saveImage(String name, String fileName);

  /**
   * represents the method to brighten the image.
   *
   * @param name          represents the name.
   * @param brightenValue represents the value we are brightening the image.
   * @param destination   represents the destination of the brightening taking place.
   */
  void brightenImage(String name, int brightenValue, String destination);

  /**
   * represents the method to flip an image vertically.
   *
   * @param name        represents the name.
   * @param destination represents the destination of the flipping taking place.
   */
  void flipVert(String name, String destination);

  /**
   * represents the method to flip an image horizontally.
   *
   * @param name        represents the name.
   * @param destination represents the destination of the flipping taking place.
   */
  void flipHor(String name, String destination);

  /**
   * represents the method to make an image greyscale.
   *
   * @param component   represents the component in the method.
   * @param name        represents the name.
   * @param destination represents the destination of the greyscale taking place.
   */
  void greyscale(String component, String name, String destination);

  /**
   * represents the method with two parameters to make an image greyscale.
   *
   * @param name        represents the name
   * @param destination represents the destination of the greyscale taking place.
   */
  void greyscale(String name, String destination);

  /**
   * represents the method to make an image sepia.
   *
   * @param name        represents the name
   * @param destination represents the destination of the sepia taking place.
   */
  void sepiaImage(String name, String destination);

  /**
   * represents the method to make an image blurred.
   *
   * @param name        represents the name
   * @param destination represents the destination of the blurred taking place.
   */
  void blurImage(String name, String destination);

  /**
   * represents the method to make an image sharpened.
   *
   * @param name        represents the name
   * @param destination represents the destination of the sharpening taking place.
   */
  void sharpenImage(String name, String destination);

  /**
   * Represents the method to make an image a mosaic image.
   *
   * @param name        represents the name
   * @param seed        represents the amount of seeds desired
   * @param destination represents the destination of the mosaic taking place.
   */
  void mosaicImage(String name, int seed, String destination);

  /**
   * represents the parseCommand method.
   *
   * @param command represents the command.
   */
  void parseCommand(String command);

  /**
   * returns the Image if key is found.
   *
   * @param name of Image.
   * @return BufferedImage.
   */
  BufferedImage getBufferedImage(String name);

  /**
   * returns the component values.
   *
   * @param component represent the component for method.
   * @param name      of file.
   * @return the component values.
   */
  int[] getComponentCount(String component, String name);


}
