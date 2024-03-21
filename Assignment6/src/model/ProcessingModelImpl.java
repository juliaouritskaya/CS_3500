package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * This class implements the processing model for images. It stores all the images.
 */
public class ProcessingModelImpl implements ProcessingModel {
  protected final Map<String, Image> images;

  public ProcessingModelImpl() {
    this.images = new HashMap<>();
  }

  /**
   * Returns the image based on the name of the image the user inputs.
   *
   * @param imageName the name of the image
   * @return the image
   */
  @Override
  public Image getImage(String imageName) {
    Image image = this.images.get(imageName);
    Image deepCopy = new ImageImpl(image.getPixels());
    return deepCopy;
  }

  /**
   * Adds an image to the processing model.
   *
   * @param image     the image to add
   * @param imageName the name of the image
   */
  @Override
  public void addImage(Image image, String imageName) {
    this.images.put(imageName, image);
  }

  /**
   * Flips the image horizontally by flipping its pixel composition.
   */
  @Override
  public void flipHorizontal(String imageName, String destImageName) {
    Image image = this.images.get(imageName);
    Image deepCopy = ImageUtil.cloneImage(image);
    deepCopy.flipHorizontal();
    this.images.put(destImageName, deepCopy);
  }

  /**
   * Flips the image vertically by flipping its pixel composition.
   */
  @Override
  public void flipVertical(String imageName, String destImageName) {
    Image image = this.images.get(imageName);
    Image deepCopy = ImageUtil.cloneImage(image);
    deepCopy.flipVertical();
    this.images.put(destImageName, deepCopy);
  }

  /**
   * Method to brighten or darken and image.
   *
   * @param component how much you want the image to be lightened or darkened.
   */
  @Override
  public void brighten(int component, String imageName, String destImageName) {
    Image image = this.images.get(imageName);
    Image deepCopy = ImageUtil.cloneImage(image);
    deepCopy.brighten(component);
    this.images.put(destImageName, deepCopy);
  }

  /**
   * Method to turn an image into greyscale using intensity.
   */
  @Override
  public void intensity(String imageName, String destImageName) {
    Image image = this.images.get(imageName);
    Image deepCopy = ImageUtil.cloneImage(image);
    deepCopy.component("intensity");
    this.images.put(destImageName, deepCopy);
  }

  /**
   * Method to turn an image into greyscale using the red component.
   */
  @Override
  public void redComponent(String imageName, String destImageName) {
    Image image = this.images.get(imageName);
    Image deepCopy = ImageUtil.cloneImage(image);
    deepCopy.component("red");
    this.images.put(destImageName, deepCopy);
  }

  /**
   * Method to turn an image into greyscale using the green component.
   */
  @Override
  public void greenComponent(String imageName, String destImageName) {
    Image image = this.images.get(imageName);
    Image deepCopy = ImageUtil.cloneImage(image);
    deepCopy.component("green");
    this.images.put(destImageName, deepCopy);
  }

  /**
   * Method to turn an image into greyscale using the blue component.
   */
  @Override
  public void blueComponent(String imageName, String destImageName) {
    Image image = this.images.get(imageName);
    Image deepCopy = ImageUtil.cloneImage(image);
    deepCopy.component("blue");
    this.images.put(destImageName, deepCopy);
  }

  /**
   * Method to turn an image into greyscale using luma.
   */
  @Override
  public void luma(String imageName, String destImageName) {
    Image image = this.images.get(imageName);
    Image deepCopy = ImageUtil.cloneImage(image);
    deepCopy.component("luma");
    this.images.put(destImageName, deepCopy);
  }

  /**
   * Method to turn an image into greyscale using the highest value component.
   */
  @Override
  public void value(String imageName, String destImageName) {
    Image image = this.images.get(imageName);
    Image deepCopy = ImageUtil.cloneImage(image);
    deepCopy.component("value");
    this.images.put(destImageName, deepCopy);
  }

  /**
   * Method to turn an image into greyscale using sepia.
   */
  @Override
  public void sepia(String imageName, String destImageName) {
    Image image = this.images.get(imageName);
    Image deepCopy = ImageUtil.cloneImage(image);
    deepCopy.sepia();
    this.images.put(destImageName, deepCopy);
  }

  /**
   * Method to turn an image into greyscale.
   */
  @Override
  public void greyscale(String imageName, String destImageName) {
    Image image = this.images.get(imageName);
    Image deepCopy = ImageUtil.cloneImage(image);
    deepCopy.greyscale();
    this.images.put(destImageName, deepCopy);
  }

  /**
   * Method to blur an image.
   */
  @Override
  public void blur(String imageName, String destImageName) {
    Image image = this.images.get(imageName);
    Image deepCopy = ImageUtil.cloneImage(image);
    deepCopy.blur();
    this.images.put(destImageName, deepCopy);
  }

  /**
   * Method to sharpen an image.
   */
  @Override
  public void sharpen(String imageName, String destImageName) {
    Image image = this.images.get(imageName);
    Image deepCopy = ImageUtil.cloneImage(image);
    deepCopy.sharpen();
    this.images.put(destImageName, deepCopy);
  }

  @Override
  public void mosaic(int seeds, String imageName, String destImageName) {
    Image image = this.images.get(imageName);
    Image deepCopy = ImageUtil.cloneImage(image);
    deepCopy.mosaic(seeds);
    this.images.put(destImageName, deepCopy);
  }

  /**
   * Loads a file to an image for the user to implement operations on.
   *
   * @param imagePath the path to the image file
   * @param imageName the name of the image
   * @throws IllegalArgumentException if the image path is null.
   */
  @Override
  public void load(String imagePath, String imageName) {
    this.images.put(imageName, ImageUtil.load(imagePath));
  }

  /**
   * Saves an image to a file.
   *
   * @param imagePath the path to the image file
   * @param imageName the name of the image
   * @throws IllegalArgumentException if the image path is null.
   */
  @Override
  public void save(String imagePath, String imageName) {
    Image image = this.images.get(imageName);
    ImageUtil.writePPM(imagePath, image);
    //ImageUtil.save(imagePath, image);
  }
}
