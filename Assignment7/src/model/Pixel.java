package model;

/**
 * the purpose of this class is to represent the pixel class.
 */
public class Pixel {
  private int red;
  private int green;
  private int blue;

  /**
   * The purpose of this class is to represent the Pixel class.
   *
   * @param red   represents the red value.
   * @param green represents the green value.
   * @param blue  represents the blue value.
   */
  public Pixel(int red, int green, int blue) {
    this.red = red;
    this.green = green;
    this.blue = blue;
  }

  /**
   * The purpose of this method is to represent the method that brightens the image.
   *
   * @param value    represents the value of the parameter.
   * @param maxValue represents the maximum value of a pixel component.
   */
  public void brighten(int value, int maxValue) {
    this.red += value;
    if (this.red > maxValue) {
      this.red = maxValue;

    } else if (this.red < 0) {
      this.red = 0;
    }
    this.green += value;
    if (this.green > maxValue) {
      this.green = maxValue;

    } else if (this.green < 0) {
      this.green = 0;
    }
    this.blue += value;
    if (this.blue > maxValue) {
      this.blue = maxValue;

    } else if (this.blue < 0) {
      this.blue = 0;
    }
  }

  /**
   * The purpose of greyscale is to loops through every pixel and calls the greyscale method on
   * every pixel.
   *
   * @param component represents the component being called on in the method.
   */
  public void greyscale(String component) {
    if (component.equals("red")) {
      green = red;
      blue = red;
    } else if (component.equals("green")) {
      red = green;
      blue = green;
    } else if (component.equals("blue")) {
      red = blue;
      green = blue;
    } else if (component.equals("value")) {
      int value = red;
      if (blue > value) {
        value = blue;
      }
      if (green > value) {
        value = green;
      }
      red = value;
      green = value;
      blue = value;
    } else if (component.equals("intensity")) {
      int intensity = (red + blue + green) / 3;
      red = intensity;
      green = intensity;
      blue = intensity;
    } else if (component.equals("luma")) {
      double luma = (0.2126 * red + 0.7152 * green + 0.0722 * blue);
      red = (int) luma;
      green = (int) luma;
      blue = (int) luma;

    }

  }

  /**
   * gets red Color.
   *
   * @return red.
   */
  public int getRed() {
    return red;
  }

  /**
   * gets green color.
   *
   * @return green
   */
  public int getGreen() {
    return green;
  }

  /**
   * gets blue color.
   *
   * @return blue
   */
  public int getBlue() {
    return blue;
  }

  /**
   * sets the red color to new value.
   *
   * @param newRedValue value to replace old red value.
   */
  public void setRed(int newRedValue) {
    red = newRedValue;
  }

  /**
   * sets the green color to new value.
   *
   * @param newGreenValue value to replace old green value.
   */
  public void setGreen(int newGreenValue) {
    green = newGreenValue;
  }

  /**
   * sets the blue color to new value.
   *
   * @param newBlueValue value to replace old blue value.
   */
  public void setBlue(int newBlueValue) {
    blue = newBlueValue;
  }


}
