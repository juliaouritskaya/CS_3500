package model;

/**
 * This class represents a pixel in an image. It has an RGB component.
 */
public class Pixel {
  protected int red;
  protected int green;
  protected int blue;

  /**
   * Creates an instance of a pixel.
   * @param red the red value in RGB
   * @param green the green value in RGB
   * @param blue the blue value in RGB
   * @throws IllegalArgumentException if any parameter is negative
   */
  public Pixel(int red, int green, int blue) throws IllegalArgumentException {
    if (red < 0 || green < 0 || blue < 0) {
      throw new IllegalArgumentException("RBG values of pixel must be non-negative.");
    } else {
      this.red = red;
      this.green = green;
      this.blue = blue;
    }
  }

  /**
   * Returns the instance variable of the red value.
   * @return the integer value of the red component.
   */
  public int getRed() {
    return this.red;
  }

  /**
   * Returns the instance variable of the green value.
   * @return the integer value of the green component.
   */
  public int getGreen() {
    return this.green;
  }

  /**
   * Returns the instance variable of the blye value.
   * @return the integer value of the blue component.
   */
  public int getBlue() {
    return this.blue;
  }

  /**
   * Computes the hash value of the instance fields.
   * @return the hash value of the instance fields.
   */
  @Override
  public int hashCode() {
    int result = 9;
    result = 7 * result + this.red;
    result = 7 * result + this.blue;
    result = 7 * result + this.green;
    return result;
  }

  /**
   * Checks if the given object is equal to this object.
   * @param o the other object passed in
   * @return true if the two objects are equal
   */
  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }

    if (!(o instanceof Pixel)) {
      return false;
    }

    Pixel pixel = (Pixel) o;

    return pixel.red == this.red
            && pixel.green == this.green
            && pixel.blue == this.blue;
  }

  @Override
  public String toString() {
    return "(R:" + this.red + ", G:" + this.green + ", B:" + this.blue + ")";
  }

}

