package model;

import java.util.Map;

/**
 * This interface represents the processing model for images. It stores all the images.
 */
public interface ProcessingModel {
  /**
   * Returns the image based on the name of the image the user inputs.
   * @param imageName the name of the image
   * @return the image
   */
  public Image getImage(String imageName);

  /**
   * Adds an image to the processing model.
   * @param image the image to add
   * @param imageName the name of the image
   */
  public void addImage(Image image, String imageName);

  void flipHorizontal(String imageName, String destImageName);

  void flipVertical(String imageName, String destImageName);


  void brighten(int component, String imageName, String destImageName);


  void intensity(String imageName, String destImageName);

  void redComponent(String imageName, String destImageName);

  void greenComponent(String imageName, String destImageName);


  void blueComponent(String imageName, String destImageName);

  void luma(String imageName, String destImageName);

  void value(String imageName, String destImageName);

  void sepia(String imageName, String destImageName);

  void greyscale(String imageName, String destImageName);

  void blur(String imageName, String destImageName);

  void sharpen(String imageName, String destImageName);

  void mosaic(int seeds, String imageName, String destImageName);

  /**
   * Loads a file to an image for the user to implement operations on.
   * @param imagePath the path to the image file
   * @throws IllegalArgumentException if the image path is null.
   */
  public void load(String imagePath, String imageName);

  /**
   * Saves an image to a file.
   * @param imagePath the path to the image file
   * @throws IllegalArgumentException if the image path is null.
   */
  public void save(String imagePath, String imageName);
}
