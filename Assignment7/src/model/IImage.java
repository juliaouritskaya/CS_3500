package model;

import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * represents the image interface.
 */
public interface IImage {

  /**
   * represents the method that saves the image.
   *
   * @param fileName represents the file name.
   * @throws IOException represents the exception.
   */
  public void save(String fileName) throws IOException;

  /**
   * represents the method that brightens the image.
   *
   * @param value represents the value of the pixel.
   */
  public void brighten(int value);

  /**
   * represents the method that flips the image vertically.
   */
  public void flipVertically();

  /**
   * represents the method that flips the image horizontally.
   */
  public void flipHorizontally();

  /**
   * represents the method that makes the image greyscale.
   *
   * @param component represents the component in the method of the greyscale.
   */
  public void greyscale(String component);

  /**
   * represents the method that makes the image greyscale.
   */
  public void greyscale();

  /**
   * represents the method that makes the image sepia.
   */
  public void sepia();


  /**
   * represents the method to make the image blurred.
   */
  public void blur();

  /**
   * represents the method to sharpen the image.
   */
  public void sharpen();

  /**
   * represents the method to apply a mosaic filter to the image.
   */
  public void mosaic(int seeds);

  /**
   * returns the bufferedImage from calculating pixelValues.
   *
   * @return bufferedImage.
   */
  public BufferedImage getBufferedImage();

  /**
   * returns the component values.
   *
   * @param component represents component parameter in method.
   * @return an integer with component values.
   */
  public int[] getComponentCount(String component);

}
