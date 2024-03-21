package model;

/**
 * This interface represents an image, which is a sequence of pixels.
 */
public interface Image {
  /**
   * Flips the image horizontally by flipping its pixel composition.
   */
  public void flipHorizontal();

  /**
   * Flips the image vertically by flipping its pixel composition.
   *
   * @return
   */
  public void flipVertical();

  /**
   * Method to brighten or darken and image.
   * @param component how much you want the image to be lightened or darkened.
   */
  public void brighten(int component);

  /**
   * Method to turn an image into greyscale using an inputted component.
   * @param color the desired component, either red, green or blue.
   */
  public void component(String color);


  /**
   * Returns the pixels that make up the image.
   * @return the pixels that make up the image
   */
  public Pixel[][] getPixels();

  /**
   * Returns the pixel at the specified position.
   * @param row the row position of the pixel
   * @param col the column positon of the pixel
   * @return the pixel at the specified row and column
   */
  public Pixel getPixelAt(int row, int col);

  /**
   * Returns the width of the image.
   * @return the width of the image
   */
  public int getWidth();

  /**
   * Returns the height of the image.
   * @return the height of the image
   */
  public int getHeight();

  /**
   * Method to add a blur filter to an image.
   */
  public void blur();

  /**
   * Method to add a sepia filter to an image.
   */
  public void sepia();

  /**
   * Method to add sharpen filter to an image.
   */
  public void sharpen();

  /**
   * Method to convert a color image into a greyscale image.
   */
  public void greyscale();
}
