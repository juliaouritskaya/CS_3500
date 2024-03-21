package model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * This class represents tests for a pixel.
 */
public class PixelTest {

  @Test
  public void getRed() {
    Pixel pixel = new Pixel(3, 4, 5);
    assertEquals(3, pixel.getRed());
  }

  @Test
  public void getGreen() {
    Pixel pixel = new Pixel(3, 4, 5);
    assertEquals(4, pixel.getGreen());
  }

  @Test
  public void getBlue() {
    Pixel pixel = new Pixel(3, 4, 5);
    assertEquals(5, pixel.getBlue());
  }

  @Test
  public void testHashCode() {
    Pixel x = new Pixel(3, 4, 5);
    assertEquals(3273, x.hashCode());

    Pixel y = new Pixel(3, 4, 5);
    assertEquals(3273, y.hashCode());

    assertEquals(x.hashCode(), y.hashCode());
  }

  @Test
  public void testEquals() {
    Pixel x = new Pixel(3, 4, 5);
    Pixel y = new Pixel(3, 4, 5);
    assertEquals(true, x.equals(y));
  }
}