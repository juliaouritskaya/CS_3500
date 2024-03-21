package model;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * This class represents tests for an image.
 */
public class ImageImplTest {
  private Pixel a = new Pixel(1, 2, 3);
  private Pixel b = new Pixel(4, 5, 6);
  private Pixel c = new Pixel(7, 8, 9);
  private Pixel d = new Pixel(11, 12, 13);
  private Pixel e = new Pixel(14, 15, 16);
  private Pixel f = new Pixel(17, 18, 19);
  private Pixel g = new Pixel(21, 22, 23);
  private Pixel h = new Pixel(24, 25, 26);
  private Pixel i = new Pixel(27, 28, 29);

  private Pixel [][] pixels = {
          {a, b, c},
          {d, e, f},
          {g, h, i}
  };

  Image image = new ImageImpl(pixels);

  private Pixel [][] pixelsNull = null;

  private Pixel [][] pixelsZero = {
          {},
          {d, e, f},
          {g, h, i}
  };

  private Pixel [][] pixelsInconsistent = {
          {a},
          {d, e},
          {g, h, i}
  };

  @Test
  public void testImageImplConstructor() {
    Image image1 = new ImageImpl(pixels);
    assertEquals(3, image1.getWidth());
    assertEquals(3, image1.getHeight());
    assertEquals(pixels, image1.getPixels());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testImageImplConstructorNullPixelsException() {
    try {
      Image image1 = new ImageImpl(pixelsNull);
    } catch (IllegalArgumentException i) {
      String output = "Pixels must be non-null and contain elements.";
      Assert.assertEquals(output, i.getMessage());
      throw i;
    }
    Assert.fail();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testImageImplConstructorZeroPixelsException() {
    try {
      Image image1 = new ImageImpl(pixelsZero);
    } catch (IllegalArgumentException i) {
      String output = "Pixels must be non-null and contain elements.";
      Assert.assertEquals(output, i.getMessage());
      throw i;
    }
    Assert.fail();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testImageImplConstructorInconsistentPixelsException() {
    try {
      Image image1 = new ImageImpl(pixelsInconsistent);
    } catch (IllegalArgumentException i) {
      String output = "Number of pixels in each row is not the same.";
      Assert.assertEquals(output, i.getMessage());
      throw i;
    }
    Assert.fail();
  }


  @Test
  public void testToString() {
    assertEquals("3 3\n" +
            "(R:1, G:2, B:3) (R:4, G:5, B:6) (R:7, G:8, B:9)\n" +
            "(R:11, G:12, B:13) (R:14, G:15, B:16) (R:17, G:18, B:19)\n" +
            "(R:21, G:22, B:23) (R:24, G:25, B:26) (R:27, G:28, B:29)\n", image.toString());
  }

  @Test
  public void testBrighten() {
    image.brighten(10);
    assertEquals("3 3\n" +
            "(R:11, G:12, B:13) (R:14, G:15, B:16) (R:17, G:18, B:19)\n" +
            "(R:21, G:22, B:23) (R:24, G:25, B:26) (R:27, G:28, B:29)\n" +
            "(R:31, G:32, B:33) (R:34, G:35, B:36) (R:37, G:38, B:39)\n", image.toString());
  }

  @Test
  public void testFlipHorizontal() {
    image.flipHorizontal();
    assertEquals("3 3\n" +
            "(R:21, G:22, B:23) (R:24, G:25, B:26) (R:27, G:28, B:29)\n" +
            "(R:11, G:12, B:13) (R:14, G:15, B:16) (R:17, G:18, B:19)\n" +
            "(R:1, G:2, B:3) (R:4, G:5, B:6) (R:7, G:8, B:9)\n", image.toString());

  }

  @Test
  public void testFlipVertical() {
    image.flipVertical();
    assertEquals("3 3\n" +
            "(R:7, G:8, B:9) (R:4, G:5, B:6) (R:1, G:2, B:3)\n" +
            "(R:17, G:18, B:19) (R:14, G:15, B:16) (R:11, G:12, B:13)\n" +
            "(R:27, G:28, B:29) (R:24, G:25, B:26) (R:21, G:22, B:23)\n", image.toString());
  }

  @Test
  public void testBlueComponent() {
    image.component("blue");
    assertEquals("3 3\n" +
            "(R:3, G:3, B:3) (R:6, G:6, B:6) (R:9, G:9, B:9)\n" +
            "(R:13, G:13, B:13) (R:16, G:16, B:16) (R:19, G:19, B:19)\n" +
            "(R:23, G:23, B:23) (R:26, G:26, B:26) (R:29, G:29, B:29)\n", image.toString());
  }

  @Test
  public void testRedComponent() {
    image.component("red");
    assertEquals("3 3\n" +
            "(R:1, G:1, B:1) (R:4, G:4, B:4) (R:7, G:7, B:7)\n" +
            "(R:11, G:11, B:11) (R:14, G:14, B:14) (R:17, G:17, B:17)\n" +
            "(R:21, G:21, B:21) (R:24, G:24, B:24) (R:27, G:27, B:27)\n", image.toString());
  }

  @Test
  public void testGreenComponent() {
    image.component("green");
    assertEquals("3 3\n" +
            "(R:2, G:2, B:2) (R:5, G:5, B:5) (R:8, G:8, B:8)\n" +
            "(R:12, G:12, B:12) (R:15, G:15, B:15) (R:18, G:18, B:18)\n" +
            "(R:22, G:22, B:22) (R:25, G:25, B:25) (R:28, G:28, B:28)\n", image.toString());
  }

  @Test
  public void testIntensity() {
    image.component("intensity");
    assertEquals("3 3\n" +
            "(R:2, G:2, B:2) (R:5, G:5, B:5) (R:8, G:8, B:8)\n" +
            "(R:12, G:12, B:12) (R:15, G:15, B:15) (R:18, G:18, B:18)\n" +
            "(R:22, G:22, B:22) (R:25, G:25, B:25) (R:28, G:28, B:28)\n", image.toString());
  }

  @Test
  public void testLuma() {
    image.component("luma");
    assertEquals("3 3\n" +
            "(R:1, G:1, B:1) (R:4, G:4, B:4) (R:7, G:7, B:7)\n" +
            "(R:11, G:11, B:11) (R:14, G:14, B:14) (R:17, G:17, B:17)\n" +
            "(R:21, G:21, B:21) (R:24, G:24, B:24) (R:27, G:27, B:27)\n", image.toString());
  }

  @Test
  public void testValue() {
    image.component("value");
    assertEquals("3 3\n" +
            "(R:3, G:3, B:3) (R:6, G:6, B:6) (R:9, G:9, B:9)\n" +
            "(R:13, G:13, B:13) (R:16, G:16, B:16) (R:19, G:19, B:19)\n" +
            "(R:23, G:23, B:23) (R:26, G:26, B:26) (R:29, G:29, B:29)\n", image.toString());
  }

  @Test
  public void testLoad() {
    System.out.println("load");
    String imagePath = "ny.ppm";
    String imageName = "ny";
    ProcessingModelImpl instance = new ProcessingModelImpl();
    instance.load(imagePath, imageName);
    Image image = instance.getImage(imageName);
    int red = image.getPixels()[100][100].getRed();
    int green = image.getPixels()[100][100].getGreen();
    int blue = image.getPixels()[100][100].getBlue();
    assertEquals(red, 46);
    assertEquals(green, 114);
    assertEquals(blue, 163);
  }

  @Test
  public void testSave() {
    System.out.println("save");
    String imagePath = "ny.ppm";
    String imagePaths = "ny2.ppm";
    String imageName = "ny";
    ProcessingModelImpl instance = new ProcessingModelImpl();
    instance.load(imagePath, imageName);
    File f = new File(imagePaths);
    if (f.exists()) {
      f.delete();
    }
    instance.save(imagePaths, imageName);
    assertTrue(f.exists());
  }

  @Test
  public void testSaveJPEG() {
    System.out.println("save");
    String imagePath = "whale.jpeg";
    String imagePaths = "whale2.jpg";
    String imageName = "whale";
    ProcessingModelImpl instance = new ProcessingModelImpl();
    instance.load(imagePath, imageName);
    File f = new File(imagePaths);
    if (f.exists()) {
      f.delete();
    }
    instance.save(imagePaths, imageName);
    assertTrue(f.exists());
  }

  @Test
  public void testGetPixelAt() {
    assertEquals(a, image.getPixelAt(0, 0));
    assertEquals(i, image.getPixelAt(2, 2));
  }

  @Test
  public void testGetWidth() {
    assertEquals(3, image.getWidth());
  }

  @Test
  public void testGetHeight() {
    assertEquals(3, image.getHeight());
  }

  @Test
  public void testBlur() {
    image.blur();
    assertEquals("3 3\n" +
            "(R:3, G:3, B:4) (R:3, G:4, B:4) (R:1, G:1, B:1)\n" +
            "(R:0, G:0, B:0) (R:0, G:0, B:0) (R:0, G:0, B:0)\n" +
            "(R:0, G:0, B:0) (R:0, G:0, B:0) (R:0, G:0, B:0)\n", image.toString());
  }

  @Test
  public void testSharpen() {
    image.sharpen();
    assertEquals("3 3\n" +
            "(R:2, G:4, B:5) (R:5, G:6, B:8) (R:0, G:0, B:0)\n" +
            "(R:0, G:0, B:0) (R:0, G:0, B:0) (R:0, G:0, B:0)\n" +
            "(R:0, G:0, B:0) (R:0, G:0, B:0) (R:0, G:0, B:0)\n", image.toString());
  }

  @Test
  public void testSepia() {
    image.sepia();
    assertEquals("3 3\n" +
            "(R:2, G:2, B:1) (R:6, G:5, B:4) (R:10, G:9, B:7)\n" +
            "(R:16, G:14, B:11) (R:20, G:17, B:13) (R:24, G:21, B:16)\n" +
            "(R:29, G:26, B:20) (R:33, G:29, B:23) (R:37, G:33, B:26)\n", image.toString());
  }

  @Test
  public void testGreyscale() {
    image.greyscale();
    assertEquals("3 3\n" +
            "(R:2, G:2, B:2) (R:6, G:6, B:6) (R:10, G:10, B:10)\n" +
            "(R:16, G:16, B:16) (R:20, G:20, B:20) (R:24, G:24, B:24)\n" +
            "(R:29, G:29, B:29) (R:33, G:33, B:33) (R:37, G:37, B:37)\n", image.toString());
  }
}