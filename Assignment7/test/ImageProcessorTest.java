//package cs3500.ImageView;

import org.junit.Test;

import java.awt.event.ActionEvent;

import javax.swing.JButton;

import controller.ImageProcessor;
import model.Pixel;
import utils.ImageUtil;
import view.ImageProcessorGUI;

import static org.junit.Assert.assertEquals;

/**
 * The purpose of this class is to test the imageProcessor entirely.
 */
public class ImageProcessorTest {
  @Test
  public void testPixel() {
    Pixel pixel = new Pixel(40, 45, 48);
    pixel.brighten(33, 255);
    assertEquals(pixel.getRed(), 73);
  }

  @Test
  public void testPixel2() {
    Pixel pixel = new Pixel(0, 0, 0);
    pixel.brighten(0, 255);
    assertEquals(pixel.getRed(), 0);
  }

  @Test
  public void testPixel3() {
    Pixel pixel = new Pixel(0, 0, 25);
    pixel.brighten(7, 255);
    assertEquals(pixel.getBlue(), 32);
  }

  @Test
  public void testPixel4() {
    Pixel pixel = new Pixel(0, 0, 0);
    pixel.brighten(255, 255);
    assertEquals(pixel.getBlue(), 255);
  }

  @Test
  public void testPixel5() {
    Pixel pixel = new Pixel(0, 0, 100);
    pixel.brighten(255, 255);
    assertEquals(pixel.getBlue(), 255);
  }

  @Test
  public void testPixel6() {
    Pixel pixel = new Pixel(150, 150, 150);
    pixel.brighten(10, 255);
    assertEquals(pixel.getBlue(), 160);
  }

  @Test
  public void testPixel7() {
    Pixel pixel = new Pixel(255, 255, 255);
    pixel.brighten(-10, 255);
    assertEquals(pixel.getBlue(), 245);
  }

  @Test
  public void testLoadIllegalImage() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.parseCommand("fake.ppm");
      String s = ImageUtil.ppmString("fake.ppm");
      String s1 = ImageUtil.ppmString("fake.ppm");
      assertEquals(s, s1);
    } catch (Exception e) {
      throw new IllegalArgumentException(e.getMessage());
    }
  }

  @Test
  public void testLoadIllegalImage2() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.parseCommand("123.ppm");
      String s = ImageUtil.ppmString("54.ppm");
      String s1 = ImageUtil.ppmString("f1ake.ppm");
      assertEquals(s, s1);
    } catch (Exception e) {
      throw new IllegalArgumentException(e.getMessage());
    }
  }

  @Test
  public void testLoadIllegalImage3() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.parseCommand(" ");
      String s = ImageUtil.ppmString("");
      String s1 = ImageUtil.ppmString("");
      assertEquals(s1, s);
    } catch (Exception e) {
      throw new IllegalArgumentException(e.getMessage());
    }
  }

  @Test
  public void testSave() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.parseCommand("load color.ppm color-hello");
      processor.parseCommand("save color-hello.ppm color-hello");
      String s = ImageUtil.ppmString("color-hello.ppm");
      String s1 = ImageUtil.ppmString("color.ppm");
      assertEquals(s, s1);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }


  @Test
  public void testSaveIllegal() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.parseCommand("load koala.ppm color-hello");
      processor.parseCommand("save color-hello.ppm color-hello");
      String s = ImageUtil.ppmString("color-hello.ppm");
      String s1 = ImageUtil.ppmString("color.ppm");
      assertEquals(s, s1);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Test
  public void testLoadImages() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.parseCommand("load colorbb.ppm color");
      String s = ImageUtil.ppmString("colorbb.ppm");
      String s1 = ImageUtil.ppmString("colorbb.ppm");
      assertEquals(s, s1);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  /*
    @Test
    public void testLoadImages() {
      try {
        ImageProcessor processor = new ImageProcessor();
        processor.parseCommand("load koala.ppm koala");
        String s = ImageUtil.PPMString("koala.ppm");
        String s1 = ImageUtil.PPMString("koala.ppm");
        assertEquals(s, s1);
      } catch (Exception e) {
        throw new IllegalArgumentException(e);
      }
    }

    @Test
    public void testLoadImage() {
      try {
        ImageProcessor processor = new ImageProcessor();
        processor.loadImage("Koala", "koala.ppm");
        ImageUtil i = new ImageUtil();
        i.readPPM("koala.ppm");
        String s = ImageUtil.PPMString("koala.ppm");
        String s1 = ImageUtil.PPMString("koala-expected.ppm");
        assertEquals(s, s1);
      } catch (Exception e) {
        throw new IllegalArgumentException(e);
      }
    }



    @Test
    public void testLoadImage2() {
      try {
        ImageProcessor processor = new ImageProcessor();
        processor.loadImage("Koala-horizontal", "koala-horizontal.ppm");
        ImageUtil i = new ImageUtil();
        i.readPPM("koala-horizontal.ppm");
        String s = ImageUtil.PPMString("koala-horizontal.ppm");
        String s1 = ImageUtil.PPMString("koala-horizontal.ppm");
        assertEquals(s, s1);
      } catch (Exception e) {
        throw new IllegalArgumentException(e);
      }
    }

    @Test
    public void testLoadImage3() {
      try {
        ImageProcessor processor = new ImageProcessor();
        processor.loadImage("Koala", "koala-vertical.ppm");
        ImageUtil i = new ImageUtil();
        i.readPPM("koala-vertical.ppm");
        String s = ImageUtil.PPMString("koala-vertical.ppm");
        String s1 = ImageUtil.PPMString("koala-vertical.ppm");
        assertEquals(s, s1);
      } catch (Exception e) {
        throw new IllegalArgumentException(e);
      }
    }

    @Test
    public void testSaveImage() {
      try {
        String input = "koala.ppm";
        ImageProcessor processor = new ImageProcessor();
        processor.loadImage("Koala", "koala.ppm");
        processor.saveImage("Koala", "koalal.ppm");
        String s = ImageUtil.PPMString("koala.ppm");
        String s1 = ImageUtil.PPMString("koalal.ppm");
        assertEquals(s, s1);
      } catch (Exception e) {
        throw new IllegalArgumentException(e);
      }
    }

    @Test
    public void testSave() {
      try {
        ImageProcessor processor = new ImageProcessor();
        processor.parseCommand("load koala.ppm koala-hello");
        processor.parseCommand("save koala-hello.ppm koala-hello");
        String s = ImageUtil.PPMString("koala-hello.ppm");
        String s1 = ImageUtil.PPMString("koala.ppm");
        assertEquals(s, s1);
      } catch (Exception e) {
        throw new IllegalArgumentException(e);
      }
    }



    @Test
    public void testSave2() {
      try {
        ImageProcessor processor = new ImageProcessor();
        processor.parseCommand("load Koala-brighten.ppm koala-save2");
        processor.parseCommand("save koala-save2.ppm koala-save2");
        String s = ImageUtil.PPMString("koala-save2.ppm");
        String s1 = ImageUtil.PPMString("koala-brighten.ppm");
        assertEquals(s, s1);
      } catch (Exception e) {
        throw new IllegalArgumentException(e);
      }
    }

    @Test
    public void testSaveImage2() {
      try {
        ImageProcessor processor = new ImageProcessor();
        processor.loadImage("Koala-vertical", "koala-vertical.ppm");
        processor.saveImage("Koala-vertical", "koala-v.ppm");
        String s = ImageUtil.PPMString("koala-vertical.ppm");
        String s1 = ImageUtil.PPMString("koala-v.ppm");
        assertEquals(s, s1);
      } catch (Exception e) {
        throw new IllegalArgumentException(e);
      }
    }

    @Test
    public void testSaveIllegalImage() {
      try {
        ImageProcessor processor = new ImageProcessor();
        processor.loadImage("Koala-vertical", "koala-vertical.ppm");
        processor.saveImage("Koala-vertical", "");
        String s = ImageUtil.PPMString("koala-vertica.ppm");
        String s1 = ImageUtil.PPMString("koala-verti");
        assertEquals(s, s1);
      } catch (Exception e) {
        throw new IllegalArgumentException(e);
      }
    }

    @Test
    public void testSaveImage3() {
      try {
        ImageProcessor processor = new ImageProcessor();
        processor.loadImage("Koala-brighten", "koala-brighten.ppm");
        processor.saveImage("Koala-brighten", "koala-b.ppm");
        String s = ImageUtil.PPMString("koala-brighten.ppm");
        String s1 = ImageUtil.PPMString("koala-b.ppm");
        assertEquals(s, s1);
      } catch (Exception e) {
        throw new IllegalArgumentException(e);
      }
    }

    @Test
    public void testBrightenImage() {
      try {
        ImageProcessor processor = new ImageProcessor();
        processor.parseCommand("load res/koala.ppm koala");
        processor.parseCommand("brighten 50 koala koala-bb");
        processor.parseCommand("save res/koala-bb.ppm koala-bb");
        String s = ImageUtil.PPMString("res/koala-bb.ppm");
        String s1 = ImageUtil.PPMString("res/koala-brighten.ppm");
        assertEquals(s, s1);
      } catch (Exception e) {
        throw new IllegalArgumentException(e);
      }
    }
  */

  @Test
  public void testSaveImage() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.loadImage("Color", "color.ppm");
      processor.saveImage("Color", "color1.ppm");
      String s = ImageUtil.ppmString("color.ppm");
      String s1 = ImageUtil.ppmString("color1.ppm");
      assertEquals(s, s1);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Test
  public void testBrightenImage1() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.parseCommand("load res/color.ppm color");
      processor.parseCommand("brighten 100 color color-high");
      processor.parseCommand("save res/color-high.ppm color-high");
      String s = ImageUtil.ppmString("res/color-high.ppm");
      String s1 = ImageUtil.ppmString("res/color-high.ppm");
      assertEquals(s, s1);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Test
  public void testLoadSunImage() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.loadImage("Koala", "koala.ppm");
      ImageUtil i = new ImageUtil();
      i.readPPM("koala.ppm");
      String s = ImageUtil.ppmString("koala.ppm");
      String s1 = ImageUtil.ppmString("koala-expected.ppm");
      assertEquals(s, s1);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Test
  public void testBrightenImage() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.parseCommand("load res/color.ppm color");
      processor.parseCommand("brighten 50 color color-bb");
      processor.parseCommand("save res/color-bb.ppm color-bb");
      String s = ImageUtil.ppmString("res/color-bb.ppm");
      String s1 = ImageUtil.ppmString("res/color-bb-expected.ppm");
      assertEquals(s, s1);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Test
  public void testSunBrightenImage() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.parseCommand("load res/sun.ppm sun");
      processor.parseCommand("brighten 50 sun sun-bb");
      processor.parseCommand("save res/sun-bb.ppm sun-bb");
      String s = ImageUtil.ppmString("res/sun-bb.ppm");
      String s1 = ImageUtil.ppmString("res/sun-bb-expected.ppm");
      assertEquals(s, s1);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Test
  public void testSunBrightenImage2() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.parseCommand("load res/sun.ppm sun");
      processor.parseCommand("brighten 300 sun sun-bb2");
      processor.parseCommand("save res/sun-bb2.ppm sun-bb2");
      String s = ImageUtil.ppmString("res/sun-bb2.ppm");
      String s1 = ImageUtil.ppmString("res/sun-bb2-expected.ppm");
      assertEquals(s, s1);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSunBrightenImageInvalid() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.parseCommand("load res/sun.ppm sun");
      processor.parseCommand("brighten x sun sun-bb2");
      processor.parseCommand("save res/sun-bb2.ppm sun-bb2");
      String s = ImageUtil.ppmString("res/sun-bb2.ppm");
      String s1 = ImageUtil.ppmString("res/sun-bb2-expected.ppm");
      assertEquals(s, s1);
    } catch (Exception e) {
      throw new IllegalArgumentException(e.getMessage());
    }
  }

  // tests if darkening the brightened koala image by 50, makes it revert back to the original
  // image
  @Test
  public void testDarkenImage() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.parseCommand("load res/color-bb.ppm color-brighten");
      processor.parseCommand("brighten -50 color-brighten color-db");
      processor.parseCommand("save res/color-db.ppm color-db");
      String s = ImageUtil.ppmString("res/color-db.ppm");
      String s1 = ImageUtil.ppmString("res/color.ppm");
      assertEquals(s, s1);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Test
  public void testSunDarkenImage() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.parseCommand("load res/sun.ppm sun-brighten");
      processor.parseCommand("brighten -50 sun-brighten sun-db");
      processor.parseCommand("save res/sun-db.ppm sun-db");
      String s = ImageUtil.ppmString("res/sun-db.ppm");
      String s1 = ImageUtil.ppmString("res/sun-db-expected.ppm");
      assertEquals(s, s1);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Test
  public void testSunDarken() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.parseCommand("load res/sun.ppm sun-brighten");
      processor.parseCommand("brighten -50 sun-brighten sun-db");
      processor.parseCommand("save res/sun-db.ppm sun-db");
      String s = ImageUtil.ppmString("res/sun-db.ppm");
      String s1 = ImageUtil.ppmString("res/sun-db-expected.ppm");
      assertEquals(s, s1);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }


  @Test
  public void testVertFlip() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.parseCommand("load res/color.ppm color");
      processor.parseCommand("vertical-flip color color-vv");
      processor.parseCommand("save res/color-vv.ppm color-vv");
      String s = ImageUtil.ppmString("res/color-vv.ppm");
      String s1 = ImageUtil.ppmString("res/color-vv expected.ppm");
      assertEquals(s, s1);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  //flips vertically, horizontally, and brightens image.
  @Test
  public void testVertHorizFlip() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.parseCommand("load res/color.ppm color");
      processor.parseCommand("vertical-flip color color-1");
      processor.parseCommand("horizontal-flip color color-1");
      processor.parseCommand("brighten 50 color color-1");
      processor.parseCommand("save res/color-1.ppm color-1");
      processor.parseCommand("save res/color-1.ppm color-1");
      String s = ImageUtil.ppmString("res/color-vv.ppm");
      String s1 = ImageUtil.ppmString("res/color-vv expected.ppm");
      assertEquals(s, s1);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  /*
    // tests vertical flip on a horizontal image
    @Test
    public void testVertFlip2() {
      try {
        ImageProcessor processor = new ImageProcessor();
        processor.parseCommand("load res/color-horizontal.ppm koala-horizontal");
        processor.parseCommand("vertical-flip koala-horizontal koala-horizontal-flip2");
        processor.parseCommand("save res/koala-horizontal-flip2.ppm koala-horizontal-flip2");
        String s = ImageUtil.PPMString("res/koala-horizontal-flip2.ppm");
        String s1 = ImageUtil.PPMString("res/koala-horizontal-flip2-expected.ppm");
        assertEquals(s, s1);
      } catch (Exception e) {
        throw new IllegalArgumentException(e);
      }
    }

    // tests horizontal flip on a horizontal image
    @Test
    public void testHorizFlip2() {
      try {
        ImageProcessor processor = new ImageProcessor();
        processor.parseCommand("load res/koala-vertical.ppm koala-vertical");
        processor.parseCommand("horizontal-flip koala-vertical koala-vertical-flip2");
        processor.parseCommand("save res/koala-vertical-flip2.ppm koala-vertical-flip2");
        String s = ImageUtil.PPMString("res/koala-vertical-flip2.ppm");
        String s1 = ImageUtil.PPMString("res/koala-vertical-flip2-expected.ppm");
        assertEquals(s, s1);
      } catch (Exception e) {
        throw new IllegalArgumentException(e);
      }
    }
  */
  @Test
  public void testSunVertFlip() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.parseCommand("load res/sun.ppm sun");
      processor.parseCommand("vertical-flip sun sun-vv");
      processor.parseCommand("save res/sun-vv.ppm sun-vv");
      String s = ImageUtil.ppmString("res/sun-vv.ppm");
      String s1 = ImageUtil.ppmString("res/sun-vv-expected.ppm");
      assertEquals(s, s1);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Test
  public void testSunHorizFlip() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.parseCommand("load res/sun.ppm sun");
      processor.parseCommand("horizontal-flip sun sun-hv");
      processor.parseCommand("save res/sun-hv.ppm sun-hv");
      String s = ImageUtil.ppmString("res/sun-hv.ppm");
      String s1 = ImageUtil.ppmString("res/sun-hv-expected.ppm");
      assertEquals(s, s1);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Test
  public void testSunHorizFlip2() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.parseCommand("load res/sun.ppm sun");
      processor.parseCommand("horizontal-flip sun sun-hv");
      processor.parseCommand("vertical-flip sun sun-hv");
      processor.parseCommand("save res/sun-hv.ppm sun-hv");
      String s = ImageUtil.ppmString("res/sun-hv.ppm");
      String s1 = ImageUtil.ppmString("res/sun-hv-expected.ppm");
      assertEquals(s, s1);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Test
  public void testGreyScale() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.parseCommand("load res/color.ppm color");
      processor.parseCommand("value-component color color-greyscale");
      processor.parseCommand("save res/color-greyscale.ppm color-greyscale");
      String s = ImageUtil.ppmString("res/color-greyscale.ppm");
      String s1 = ImageUtil.ppmString("res/color-greyscale-expected.ppm");
      assertEquals(s, s1);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Test
  public void testSunGreyScale() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.parseCommand("load res/sun.ppm sun");
      processor.parseCommand("value-component sun sun-greyscale");
      processor.parseCommand("save res.sun-greyscale.ppm sun-greyscale");
      String s = ImageUtil.ppmString("res/sun-greyscale.ppm");
      String s1 = ImageUtil.ppmString("res/sun-greyscale expected.ppm");
      assertEquals(s, s1);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Test
  public void testColorGreyScale() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.parseCommand("load res/color.ppm color");
      processor.parseCommand("value-component color color-greyscale");
      processor.parseCommand("save res.color-greyscale.ppm color-greyscale");
      String s = ImageUtil.ppmString("res/color-greyscale.ppm");
      String s1 = ImageUtil.ppmString("res/color-greyscale-expected.ppm");
      assertEquals(s, s1);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Test
  public void testSunBasicBlur() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.parseCommand("load res/sun.ppm sun");
      processor.parseCommand("blur sun sun-blur-try");
      processor.parseCommand("save res/sun-blur-try.ppm sun-blur-try");
      String s = ImageUtil.ppmString("res/sun-blur.ppm");
      String s1 = ImageUtil.ppmString("res/sun-blur-try.ppm");
      assertEquals(s, s1);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Test
  public void testSunDoubleBlur() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.parseCommand("load res/sun.ppm sun");
      processor.parseCommand("blur sun sun-blur-double");
      processor.parseCommand("blur sun sun-blur-double");
      processor.parseCommand("save res/sun-blur-double.ppm sun-blur-double");
      String s = ImageUtil.ppmString("res/sun-blur-double-expected.ppm");
      String s1 = ImageUtil.ppmString("res/sun-blur-double.ppm");
      assertEquals(s, s1);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Test
  public void testSunTripleBlur() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.parseCommand("load res/sun.ppm sun");
      processor.parseCommand("blur sun sun-blur-double");
      processor.parseCommand("blur sun sun-blur-double");
      processor.parseCommand("blur sun sun-blur-double");
      processor.parseCommand("save res/sun-blur-double.ppm sun-blur-double");
      String s = ImageUtil.ppmString("res/sun-blur-double-expected.ppm");
      String s1 = ImageUtil.ppmString("res/sun-blur-double.ppm");
      assertEquals(s, s1);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Test
  public void testSunBlurSharpen() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.parseCommand("load res/sun.ppm sun");
      processor.parseCommand("blur sun sun-blur-double");
      processor.parseCommand("sharpen sun sun-blur-double");
      processor.parseCommand("save res/sun-blur-double.ppm sun-blur-double");
      String s = ImageUtil.ppmString("res/sun.ppm");
      String s1 = ImageUtil.ppmString("res/sun-blur-double.ppm");
      assertEquals(s, s1);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Test
  public void testSunSharpenBlur() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.parseCommand("load res/sun.ppm sun");
      processor.parseCommand("sharpen sun sun-blur-double");
      processor.parseCommand("blur sun sun-blur-double");
      processor.parseCommand("save res/sun-blur-double.ppm sun-blur-double");
      String s = ImageUtil.ppmString("res/sun-blur-double-expected.ppm");
      String s1 = ImageUtil.ppmString("res/sun-blur-double.ppm");
      assertEquals(s, s1);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Test
  public void testColorBasicBlur() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.parseCommand("load res/color.ppm color");
      processor.parseCommand("blur color color-blur-try");
      processor.parseCommand("save res/color-blur-try.ppm color-blur-try");
      String s = ImageUtil.ppmString("res/color-blur-try-expected.ppm");
      String s1 = ImageUtil.ppmString("res/color-blur-try.ppm");
      assertEquals(s, s1);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Test
  public void testColorDoubleBlur() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.parseCommand("load res/color.ppm color");
      processor.parseCommand("blur color color-blur-double");
      processor.parseCommand("blur color color-blur-double");
      processor.parseCommand("save res/color-blur-double.ppm color-blur-double");
      String s = ImageUtil.ppmString("res/color-blur-double-expected.ppm");
      String s1 = ImageUtil.ppmString("res/color-blur-double.ppm");
      assertEquals(s, s1);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Test
  public void testColorTripleBlur() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.parseCommand("load res/color.ppm color");
      processor.parseCommand("blur color color-blur-double");
      processor.parseCommand("blur color color-blur-double");
      processor.parseCommand("blur color color-blur-double");
      processor.parseCommand("save res/color-blur-double.ppm color-blur-double");
      String s = ImageUtil.ppmString("res/color-blur-double-expected.ppm");
      String s1 = ImageUtil.ppmString("res/color-blur-double.ppm");
      assertEquals(s, s1);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Test
  public void testColorBlurSharpen() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.parseCommand("load res/color.ppm color");
      processor.parseCommand("blur color color-blur-double");
      processor.parseCommand("sharpen color color-blur-double");
      processor.parseCommand("save res/color-blur-double.ppm color-blur-double");
      String s = ImageUtil.ppmString("res/color-blur-double.ppm");
      String s1 = ImageUtil.ppmString("res/color-blur-double.ppm");
      assertEquals(s, s1);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Test
  public void testColorSharpenBlur() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.parseCommand("load res/color.ppm color");
      processor.parseCommand("sharpen color color-blur-double");
      processor.parseCommand("blur color color-blur-double");
      processor.parseCommand("save res/color-blur-double.ppm color-blur-double");
      String s = ImageUtil.ppmString("res/color-blur-double.ppm");
      String s1 = ImageUtil.ppmString("res/color-blur-double.ppm");
      assertEquals(s, s1);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Test
  public void testSunBasicSharpen() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.parseCommand("load res/sun.ppm sun");
      processor.parseCommand("sharpen sun sun-sharpen-try");
      processor.parseCommand("save res/sun-sharpen-try.ppm sun-sharpen-try");
      String s = ImageUtil.ppmString("res/sun-sharpen-try.ppm");
      String s1 = ImageUtil.ppmString("res/sun-sharpen-try.ppm");
      assertEquals(s, s1);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Test
  public void testSunDoubleSharpen() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.parseCommand("load res/sun.ppm sun");
      processor.parseCommand("sharpen sun sun-sharpen-double");
      processor.parseCommand("sharpen sun sun-sharpen-double");
      processor.parseCommand("save res/sun-sharpen-double.ppm sun-sharpen-double");
      String s = ImageUtil.ppmString("res/sun-sharpen-double-expected.ppm");
      String s1 = ImageUtil.ppmString("res/sun-sharpen-double.ppm");
      assertEquals(s, s1);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Test
  public void testSunTripleSharpen() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.parseCommand("load res/sun.ppm sun");
      processor.parseCommand("sharpen sun sun-sharpen-double");
      processor.parseCommand("sharpen sun sun-sharpen-double");
      processor.parseCommand("sharpen sun sun-sharpen-double");
      processor.parseCommand("save res/sun-sharpen-double.ppm sun-sharpen-double");
      String s = ImageUtil.ppmString("res/sun-sharpen-double-expected.ppm");
      String s1 = ImageUtil.ppmString("res/sun-sharpen-double.ppm");
      assertEquals(s, s1);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Test
  public void testColorBasicSharpen() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.parseCommand("load res/color.ppm color");
      processor.parseCommand("sharpen color color-sharpen-try");
      processor.parseCommand("save res/color-sharpen-try.ppm color-sharpen-try");
      String s = ImageUtil.ppmString("res/color-sharpen-try.ppm");
      String s1 = ImageUtil.ppmString("res/color-sharpen-try.ppm");
      assertEquals(s, s1);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Test
  public void testColorDoubleSharpen() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.parseCommand("load res/color.ppm color");
      processor.parseCommand("sharpen color color-sharpen-double");
      processor.parseCommand("sharpen color color-sharpen-double");
      processor.parseCommand("save res/color-sharpen-double.ppm color-sharpen-double");
      String s = ImageUtil.ppmString("res/color-sharpen-double-expected.ppm");
      String s1 = ImageUtil.ppmString("res/color-sharpen-double.ppm");
      assertEquals(s, s1);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Test
  public void testColorTripleSharpen() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.parseCommand("load res/color.ppm color");
      processor.parseCommand("sharpen color color-sharpen-double");
      processor.parseCommand("sharpen color color-sharpen-double");
      processor.parseCommand("sharpen color color-sharpen-double");
      processor.parseCommand("save res/color-sharpen-double.ppm color-sharpen-double");
      String s = ImageUtil.ppmString("res/color-sharpen-double-expected.ppm");
      String s1 = ImageUtil.ppmString("res/color-sharpen-double.ppm");
      assertEquals(s, s1);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Test
  public void testGreyScaleBasic() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.parseCommand("load res/color.ppm color");
      processor.parseCommand("greyscale color color-greyscale-double");
      processor.parseCommand("save res/color-greyscale-double.ppm color-greyscale-double");
      String s = ImageUtil.ppmString("res/color-greyscale-double-expected.ppm");
      String s1 = ImageUtil.ppmString("res/color-greyscale-double.ppm");
      assertEquals(s, s1);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Test
  public void testGreyScaleDouble() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.parseCommand("load res/color.ppm color");
      processor.parseCommand("greyscale color color-greyscale-double");
      processor.parseCommand("greyscale color color-greyscale-double");
      processor.parseCommand("save res/color-greyscale-double.ppm color-greyscale-double");
      String s = ImageUtil.ppmString("res/color-greyscale-double-expected.ppm");
      String s1 = ImageUtil.ppmString("res/color-greyscale-double.ppm");
      assertEquals(s, s1);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }


  @Test
  public void testColorGreyScaleDouble() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.parseCommand("load res/color.ppm color");
      processor.parseCommand("greyscale color color-greyscale-double");
      processor.parseCommand("greyscale color color-greyscale-double");
      processor.parseCommand("save res/color-greyscale-double.ppm color-greyscale-double");
      String s = ImageUtil.ppmString("res/color-greyscale-double-expected.ppm");
      String s1 = ImageUtil.ppmString("res/color-greyscale-double.ppm");
      assertEquals(s, s1);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Test
  public void testSunGreyScaleTriple() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.parseCommand("load res/color.ppm color");
      processor.parseCommand("greyscale color color-greyscale-double");
      processor.parseCommand("greyscale color color-greyscale-double");
      processor.parseCommand("greyscale color color-greyscale-double");
      processor.parseCommand("save res/color-greyscale-double.ppm color-greyscale-double");
      String s = ImageUtil.ppmString("res/color-greyscale-double-expected.ppm");
      String s1 = ImageUtil.ppmString("res/color-greyscale-double.ppm");
      assertEquals(s, s1);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Test
  public void testSunGrey() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.parseCommand("load res/color.ppm color");
      processor.parseCommand("greyscale color color-greyscale-double");
      processor.parseCommand("greyscale color color-greyscale-double");
      processor.parseCommand("greyscale color color-greyscale-double");
      processor.parseCommand("save res/color-greyscale-double.ppm color-greyscale-double");
      String s = ImageUtil.ppmString("res/color-greyscale-double-expected.ppm");
      String s1 = ImageUtil.ppmString("res/color-greyscale-double.ppm");
      assertEquals(s, s1);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Test
  public void testSunDoubleGrey() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.parseCommand("load res/color.ppm color");
      processor.parseCommand("greyscale color color-greyscale-double");
      processor.parseCommand("greyscale color color-greyscale-double");
      processor.parseCommand("greyscale color color-greyscale-double");
      processor.parseCommand("save res/color-greyscale-double.ppm color-greyscale-double");
      String s = ImageUtil.ppmString("res/color-greyscale-double-expected.ppm");
      String s1 = ImageUtil.ppmString("res/color-greyscale-double.ppm");
      assertEquals(s, s1);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Test
  public void testColorSepia() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.parseCommand("load res/color.ppm color");
      processor.parseCommand("sepia color color-sepia");
      processor.parseCommand("save res/color-sepia.ppm color-sepia");
      String s = ImageUtil.ppmString("res/color-sepia-expected.ppm");
      String s1 = ImageUtil.ppmString("res/color-sepia.ppm");
      assertEquals(s, s1);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Test
  public void testSunSepia() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.parseCommand("load res/sun.ppm sun");
      processor.parseCommand("sepia sun sun-sepia");
      processor.parseCommand("save res/sun-sepia.ppm sun-sepia");
      String s = ImageUtil.ppmString("res/sun-sepia-expected.ppm");
      String s1 = ImageUtil.ppmString("res/sun-sepia.ppm");
      assertEquals(s, s1);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Test
  public void testColorDoubleSepia() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.parseCommand("load res/color.ppm color");
      processor.parseCommand("sepia color color-sepia");
      processor.parseCommand("sepia color color-sepia");
      processor.parseCommand("save res/color-sepia.ppm color-sepia");
      String s = ImageUtil.ppmString("res/color-sepia-expected.ppm");
      String s1 = ImageUtil.ppmString("res/color-sepia.ppm");
      assertEquals(s, s1);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Test
  public void testSunDoubleSepia() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.parseCommand("load res/sun.ppm sun");
      processor.parseCommand("sepia sun sun-sepia");
      processor.parseCommand("sepia sun sun-sepia");
      processor.parseCommand("save res/sun-sepia.ppm sun-sepia");
      String s = ImageUtil.ppmString("res/sun-sepia-expected.ppm");
      String s1 = ImageUtil.ppmString("res/sun-sepia.ppm");
      assertEquals(s, s1);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Test
  public void testColorTripleSepia() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.parseCommand("load res/color.ppm color");
      processor.parseCommand("sepia color color-sepia");
      processor.parseCommand("sepia color color-sepia");
      processor.parseCommand("sepia color color-sepia");
      processor.parseCommand("save res/color-sepia.ppm color-sepia");
      String s = ImageUtil.ppmString("res/color-sepia-expected.ppm");
      String s1 = ImageUtil.ppmString("res/color-sepia.ppm");
      assertEquals(s, s1);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Test
  public void testSunTripleSepia() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.parseCommand("load res/sun.ppm sun");
      processor.parseCommand("sepia sun sun-sepia");
      processor.parseCommand("sepia sun sun-sepia");
      processor.parseCommand("sepia sun sun-sepia");
      processor.parseCommand("save res/sun-sepia.ppm sun-sepia");
      String s = ImageUtil.ppmString("res/sun-sepia-expected.ppm");
      String s1 = ImageUtil.ppmString("res/sun-sepia.ppm");
      assertEquals(s, s1);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Test
  public void testColorBlurGrey() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.parseCommand("load res/color.ppm color");
      processor.parseCommand("blur color color-blur-grey");
      processor.parseCommand("greyscale color color-blur-grey");
      processor.parseCommand("save res/color-blur-grey.ppm color-blur-grey");
      String s = ImageUtil.ppmString("res/color-blur-color.ppm");
      String s1 = ImageUtil.ppmString("res/color-blur-color-expected.ppm");
      assertEquals(s, s1);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Test
  public void testColorDoubleBlurGrey() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.parseCommand("load res/color.ppm color");
      processor.parseCommand("blur color color-blur-grey");
      processor.parseCommand("blur color color-blur-grey");
      processor.parseCommand("greyscale color color-blur-grey");
      processor.parseCommand("save res/color-blur-grey.ppm color-blur-grey");
      String s = ImageUtil.ppmString("res/color-blur-color.ppm");
      String s1 = ImageUtil.ppmString("res/color-blur-color-expected.ppm");
      assertEquals(s, s1);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Test
  public void testColorTripleBlurGrey() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.parseCommand("load res/color.ppm color");
      processor.parseCommand("blur color color-blur-grey");
      processor.parseCommand("blur color color-blur-grey");
      processor.parseCommand("blur color color-blur-grey");
      processor.parseCommand("greyscale color color-blur-grey");
      processor.parseCommand("save res/color-blur-grey.ppm color-blur-grey");
      String s = ImageUtil.ppmString("res/color-blur-color.ppm");
      String s1 = ImageUtil.ppmString("res/color-blur-color-expected.ppm");
      assertEquals(s, s1);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Test
  public void testColorGreyBlur() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.parseCommand("load res/color.ppm color");
      processor.parseCommand("greyscale color color-blur-grey");
      processor.parseCommand("blur color color-blur-grey");
      processor.parseCommand("save res/color-blur-grey.ppm color-blur-grey");
      String s = ImageUtil.ppmString("res/color-blur-color.ppm");
      String s1 = ImageUtil.ppmString("res/color-blur-color-expected.ppm");
      assertEquals(s, s1);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Test
  public void testColorDoubleGreyBlur() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.parseCommand("load res/color.ppm color");
      processor.parseCommand("greyscale color color-blur-grey");
      processor.parseCommand("greyscale color color-blur-grey");
      processor.parseCommand("blur color color-blur-grey");
      processor.parseCommand("save res/color-blur-grey.ppm color-blur-grey");
      String s = ImageUtil.ppmString("res/color-blur-color.ppm");
      String s1 = ImageUtil.ppmString("res/color-blur-color-expected.ppm");
      assertEquals(s, s1);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Test
  public void testColorTripleGreyBlur() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.parseCommand("load res/color.ppm color");
      processor.parseCommand("greyscale color color-blur-grey");
      processor.parseCommand("greyscale color color-blur-grey");
      processor.parseCommand("greyscale color color-blur-grey");
      processor.parseCommand("blur color color-blur-grey");
      processor.parseCommand("save res/color-blur-grey.ppm color-blur-grey");
      String s = ImageUtil.ppmString("res/color-blur-color.ppm");
      String s1 = ImageUtil.ppmString("res/color-blur-color-expected.ppm");
      assertEquals(s, s1);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Test
  public void testColorTripleGreyDoubleBlur() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.parseCommand("load res/color.ppm color");
      processor.parseCommand("greyscale color color-blur-grey");
      processor.parseCommand("greyscale color color-blur-grey");
      processor.parseCommand("greyscale color color-blur-grey");
      processor.parseCommand("blur color color-blur-grey");
      processor.parseCommand("blur color color-blur-grey");
      processor.parseCommand("save res/color-blur-grey.ppm color-blur-grey");
      String s = ImageUtil.ppmString("res/color-blur-color.ppm");
      String s1 = ImageUtil.ppmString("res/color-blur-color-expected.ppm");
      assertEquals(s, s1);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Test
  public void testColorTripleGreyTripleBlur() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.parseCommand("load res/color.ppm color");
      processor.parseCommand("greyscale color color-blur-grey");
      processor.parseCommand("greyscale color color-blur-grey");
      processor.parseCommand("greyscale color color-blur-grey");
      processor.parseCommand("blur color color-blur-grey");
      processor.parseCommand("blur color color-blur-grey");
      processor.parseCommand("blur color color-blur-grey");
      processor.parseCommand("save res/color-blur-grey.ppm color-blur-grey");
      String s = ImageUtil.ppmString("res/color-blur-color.ppm");
      String s1 = ImageUtil.ppmString("res/color-blur-color-expected.ppm");
      assertEquals(s, s1);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Test
  public void testSunBlurGrey() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.parseCommand("load res/sun.ppm sun");
      processor.parseCommand("blur sun sun-blur-grey");
      processor.parseCommand("greyscale sun-blur-grey sun-blur-grey");
      processor.parseCommand("save res/sun-blur-grey.ppm sun-blur-grey");
      String s = ImageUtil.ppmString("res/sun-blur-grey.ppm");
      String s1 = ImageUtil.ppmString("res/sun-blur-grey-expected.ppm");
      assertEquals(s, s1);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Test
  public void testSunDoubleBlurGrey() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.parseCommand("load res/sun.ppm sun");
      processor.parseCommand("blur sun sun-blur-double-grey");
      processor.parseCommand("blur sun-blur-double-grey sun-blur-double-grey");
      processor.parseCommand("greyscale sun-blur-double-grey sun-blur-double-grey");
      processor.parseCommand("save res/sun-blur-double-grey.ppm sun-blur-double-grey");
      String s = ImageUtil.ppmString("res/sun-blur-double-grey.ppm");
      String s1 = ImageUtil.ppmString("res/sun-blur-double-grey-expected.ppm");
      assertEquals(s, s1);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Test
  public void testSunTripleBlurGrey() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.parseCommand("load res/sun.ppm sun");
      processor.parseCommand("blur sun sun-blur-double-grey");
      processor.parseCommand("blur sun-blur-double-grey sun-blur-double-grey");
      processor.parseCommand("greyscale sun-blur-double-grey sun-blur-double-grey");
      processor.parseCommand("save res/sun-blur-double-grey.ppm sun-blur-double-grey");
      String s = ImageUtil.ppmString("res/sun-blur-double-grey.ppm");
      String s1 = ImageUtil.ppmString("res/sun-blur-double-grey-expected.ppm");
      assertEquals(s, s1);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Test
  public void testSunGreyBlur() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.parseCommand("load res/sun.ppm sun");
      processor.parseCommand("greyscale sun sun-blur-grey");
      processor.parseCommand("blur sun sun-blur-grey");
      processor.parseCommand("save res/sun-blur-grey.ppm sun-blur-grey");
      String s = ImageUtil.ppmString("res/sun-blur-grey.ppm");
      String s1 = ImageUtil.ppmString("res/sun-blur-grey-expected.ppm");
      assertEquals(s, s1);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Test
  public void testSunDoubleGreyBlur() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.parseCommand("load res/sun.ppm sun");
      processor.parseCommand("greyscale sun sun-blur-grey");
      processor.parseCommand("greyscale sun sun-blur-grey");
      processor.parseCommand("blur sun sun-blur-grey");
      processor.parseCommand("save res/sun-blur-grey.ppm sun-blur-grey");
      String s = ImageUtil.ppmString("res/sun-blur-grey.ppm");
      String s1 = ImageUtil.ppmString("res/sun-blur-grey-expected.ppm");
      assertEquals(s, s1);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Test
  public void testSunTripleGreyBlur() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.parseCommand("load res/sun.ppm sun");
      processor.parseCommand("greyscale sun sun-blur-grey");
      processor.parseCommand("greyscale sun sun-blur-grey");
      processor.parseCommand("greyscale sun sun-blur-grey");
      processor.parseCommand("blur sun sun-blur-grey");
      processor.parseCommand("save res/sun-blur-grey.ppm sun-blur-grey");
      String s = ImageUtil.ppmString("res/sun-blur-grey.ppm");
      String s1 = ImageUtil.ppmString("res/sun-blur-grey-expected.ppm");
      assertEquals(s, s1);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Test
  public void testSunTripleGreyDoubleBlur() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.parseCommand("load res/sun.ppm sun");
      processor.parseCommand("greyscale sun sun-blur-grey");
      processor.parseCommand("greyscale sun sun-blur-grey");
      processor.parseCommand("greyscale sun sun-blur-grey");
      processor.parseCommand("blur sun sun-blur-grey");
      processor.parseCommand("blur sun sun-blur-grey");
      processor.parseCommand("save res/sun-blur-grey.ppm sun-blur-grey");
      String s = ImageUtil.ppmString("res/sun-blur-grey.ppm");
      String s1 = ImageUtil.ppmString("res/sun-blur-grey-expected.ppm");
      assertEquals(s, s1);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Test
  public void testSunTripleGreyTripleBlur() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.parseCommand("load res/sun.ppm sun");
      processor.parseCommand("greyscale sun sun-blur-grey");
      processor.parseCommand("greyscale sun sun-blur-grey");
      processor.parseCommand("greyscale sun sun-blur-grey");
      processor.parseCommand("blur sun sun-blur-grey");
      processor.parseCommand("blur sun sun-blur-grey");
      processor.parseCommand("blur sun sun-blur-grey");
      processor.parseCommand("save res/sun-blur-grey.ppm sun-blur-grey");
      String s = ImageUtil.ppmString("res/sun-blur-grey.ppm");
      String s1 = ImageUtil.ppmString("res/sun-blur-grey-expected.ppm");
      assertEquals(s, s1);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Test
  public void testSunSharpenSepia() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.parseCommand("load res/sun.ppm sun");
      processor.parseCommand("sharpen sun sun-sharpen-sepia");
      processor.parseCommand("sepia sun-sharpen-sepia sun-sharpen-sepia");
      processor.parseCommand("save res/sun-sharpen-sepia.ppm sun-sharpen-sepia");
      String s = ImageUtil.ppmString("res/sun-sharpen-sepia.ppm");
      String s1 = ImageUtil.ppmString("res/sun-sharpen-sepia-expected.ppm");
      assertEquals(s, s1);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Test
  public void testSunDoubleSharpenSepia() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.parseCommand("load res/sun.ppm sun");
      processor.parseCommand("sharpen sun sun-sharpen-sepia");
      processor.parseCommand("sharpen sun sun-sharpen-sepia");
      processor.parseCommand("sepia sun-sharpen-sepia sun-sharpen-sepia");
      processor.parseCommand("save res/sun-sharpen-sepia.ppm sun-sharpen-sepia");
      String s = ImageUtil.ppmString("res/sun-sharpen-sepia.ppm");
      String s1 = ImageUtil.ppmString("res/sun-sharpen-sepia-expected.ppm");
      assertEquals(s, s1);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Test
  public void testSunTripleSharpenSepia() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.parseCommand("load res/sun.ppm sun");
      processor.parseCommand("sharpen sun sun-sharpen-sepia");
      processor.parseCommand("sharpen sun sun-sharpen-sepia");
      processor.parseCommand("sepia sun-sharpen-sepia sun-sharpen-sepia");
      processor.parseCommand("save res/sun-sharpen-sepia.ppm sun-sharpen-sepia");
      String s = ImageUtil.ppmString("res/sun-sharpen-sepia.ppm");
      String s1 = ImageUtil.ppmString("res/sun-sharpen-sepia-expected.ppm");
      assertEquals(s, s1);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Test
  public void testSunSharpenDoubleSepia() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.parseCommand("load res/sun.ppm sun");
      processor.parseCommand("sharpen sun sun-sharpen-sepia");
      processor.parseCommand("sharpen sun sun-sharpen-sepia");
      processor.parseCommand("sepia sun-sharpen-sepia sun-sharpen-sepia");
      processor.parseCommand("save res/sun-sharpen-sepia.ppm sun-sharpen-sepia");
      String s = ImageUtil.ppmString("res/sun-sharpen-sepia.ppm");
      String s1 = ImageUtil.ppmString("res/sun-sharpen-sepia-expected.ppm");
      assertEquals(s, s1);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Test
  public void testSunSharpenTripleSepia() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.parseCommand("load res/sun.ppm sun");
      processor.parseCommand("sharpen sun sun-sharpen-sepia");
      processor.parseCommand("sharpen sun sun-sharpen-sepia");
      processor.parseCommand("sepia sun-sharpen-sepia sun-sharpen-sepia");
      processor.parseCommand("save res/sun-sharpen-sepia.ppm sun-sharpen-sepia");
      String s = ImageUtil.ppmString("res/sun-sharpen-sepia.ppm");
      String s1 = ImageUtil.ppmString("res/sun-sharpen-sepia-expected.ppm");
      assertEquals(s, s1);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Test
  public void testColorSharpenSepia() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.parseCommand("load res/color.ppm color");
      processor.parseCommand("sharpen color color-sharpen-sepia");
      processor.parseCommand("sepia color-sharpen-sepia color-sharpen-sepia");
      processor.parseCommand("save res/color-sharpen-sepia.ppm color-sharpen-sepia");
      String s = ImageUtil.ppmString("res/color-sharpen-sepia.ppm");
      String s1 = ImageUtil.ppmString("res/color-sharpen-sepia-expected.ppm");
      assertEquals(s, s1);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Test
  public void testColorDoubleSharpenSepia() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.parseCommand("load res/color.ppm color");
      processor.parseCommand("sharpen color color-sharpen-sepia");
      processor.parseCommand("sharpen color color-sharpen-sepia");
      processor.parseCommand("sepia color-sharpen-sepia color-sharpen-sepia");
      processor.parseCommand("save res/color-sharpen-sepia.ppm color-sharpen-sepia");
      String s = ImageUtil.ppmString("res/color-sharpen-sepia.ppm");
      String s1 = ImageUtil.ppmString("res/color-sharpen-sepia-expected.ppm");
      assertEquals(s, s1);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Test
  public void testColorTripleSharpenSepia() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.parseCommand("load res/color.ppm color");
      processor.parseCommand("sharpen color color-sharpen-sepia");
      processor.parseCommand("sharpen color color-sharpen-sepia");
      processor.parseCommand("sepia color-sharpen-sepia color-sharpen-sepia");
      processor.parseCommand("save res/color-sharpen-sepia.ppm color-sharpen-sepia");
      String s = ImageUtil.ppmString("res/color-sharpen-sepia.ppm");
      String s1 = ImageUtil.ppmString("res/color-sharpen-sepia-expected.ppm");
      assertEquals(s, s1);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Test
  public void testColorSharpenDoubleSepia() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.parseCommand("load res/color.ppm color");
      processor.parseCommand("sharpen color color-sharpen-sepia");
      processor.parseCommand("sharpen color color-sharpen-sepia");
      processor.parseCommand("sepia color-sharpen-sepia color-sharpen-sepia");
      processor.parseCommand("save res/color-sharpen-sepia.ppm color-sharpen-sepia");
      String s = ImageUtil.ppmString("res/color-sharpen-sepia.ppm");
      String s1 = ImageUtil.ppmString("res/color-sharpen-sepia-expected.ppm");
      assertEquals(s, s1);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Test
  public void testColorSharpenTripleSepia() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.parseCommand("load res/color.ppm color");
      processor.parseCommand("sharpen color color-sharpen-sepia");
      processor.parseCommand("sharpen color color-sharpen-sepia");
      processor.parseCommand("sepia color-sharpen-sepia color-sharpen-sepia");
      processor.parseCommand("save res/sun-sharpen-sepia.ppm sun-sharpen-sepia");
      String s = ImageUtil.ppmString("res/color-sharpen-sepia.ppm");
      String s1 = ImageUtil.ppmString("res/color-sharpen-sepia-expected.ppm");
      assertEquals(s, s1);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Test
  public void testColorGreyScaleSepia() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.parseCommand("load res/color.ppm color");
      processor.parseCommand("greyscale color color-grey-sepia");
      processor.parseCommand("sepia color-grey-sepia color-grey-sepia");
      processor.parseCommand("save res/color-grey-sepia.ppm color-grey-sepia");
      String s = ImageUtil.ppmString("res/color-grey-sepia.ppm");
      String s1 = ImageUtil.ppmString("res/color-grey-sepia-expected.ppm");
      assertEquals(s, s1);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  //TEST MORE
  @Test
  public void testColorDoubleGreyScaleSepia() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.parseCommand("load res/color.ppm color");
      processor.parseCommand("greyscale color color-grey-sepia");
      processor.parseCommand("sepia color-grey-sepia color-grey-sepia");
      //change save file for new test and create new expected for every new variation
      processor.parseCommand("save res/color-grey-double-sepia.ppm color-grey-sepia");
      String s = ImageUtil.ppmString("res/color-grey-double-sepia.ppm");
      String s1 = ImageUtil.ppmString("res/color-grey-double-sepia-expected.ppm");
      assertEquals(s, s1);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Test
  public void testColorBlurSepia() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.parseCommand("load res/color.ppm color");
      processor.parseCommand("blur color color-blur-sepia");
      processor.parseCommand("sepia color-blur-sepia color-blur-sepia");
      processor.parseCommand("save res/color-blur-sepia.ppm color-blur-sepia");
      String s = ImageUtil.ppmString("res/color-blur-sepia.ppm");
      String s1 = ImageUtil.ppmString("res/color-blur-sepia-expected.ppm");
      assertEquals(s, s1);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Test
  public void testColorDoubleBlurSepia() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.parseCommand("load res/color.ppm color");
      processor.parseCommand("blur color color-blur-sepia");
      processor.parseCommand("blur color color-blur-sepia");
      processor.parseCommand("sepia color-blur-sepia color-blur-sepia");
      processor.parseCommand("save res/color-blur-sepia.ppm color-blur-sepia");
      String s = ImageUtil.ppmString("res/color-blur-sepia.ppm");
      String s1 = ImageUtil.ppmString("res/color-blur-sepia-expected.ppm");
      assertEquals(s, s1);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Test
  public void testColorTripleBlurSepia() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.parseCommand("load res/color.ppm color");
      processor.parseCommand("blur color color-blur-sepia");
      processor.parseCommand("blur color color-blur-sepia");
      processor.parseCommand("blur color color-blur-sepia");
      processor.parseCommand("sepia color-blur-sepia color-blur-sepia");
      processor.parseCommand("save res/color-blur-sepia.ppm color-blur-sepia");
      String s = ImageUtil.ppmString("res/color-blur-sepia.ppm");
      String s1 = ImageUtil.ppmString("res/color-blur-sepia-expected.ppm");
      assertEquals(s, s1);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Test
  public void testColorBlurDoubleSepia() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.parseCommand("load res/color.ppm color");
      processor.parseCommand("blur color color-blur-double-sepia");
      processor.parseCommand("sepia color color-blur-double-sepia");
      processor.parseCommand("sepia color-blur-double-sepia color-blur-double-sepia");
      processor.parseCommand("save res/color-blur-double-sepia.ppm color-blur-double-sepia");
      String s = ImageUtil.ppmString("res/color-blur-double-sepia.ppm");
      String s1 = ImageUtil.ppmString("res/color-blur-double-sepia-expected.ppm");
      assertEquals(s, s1);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Test
  public void testColorBlurTripleSepia() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.parseCommand("load res/color.ppm color");
      processor.parseCommand("blur color color-blur-double-sepia");
      processor.parseCommand("sepia color color-blur-double-sepia");
      processor.parseCommand("sepia color color-blur-double-sepia");
      processor.parseCommand("sepia color-blur-double-sepia color-blur-double-sepia");
      processor.parseCommand("save res/color-blur-double-sepia.ppm color-blur-double-sepia");
      String s = ImageUtil.ppmString("res/color-blur-double-sepia.ppm");
      String s1 = ImageUtil.ppmString("res/color-blur-double-sepia-expected.ppm");
      assertEquals(s, s1);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Test
  public void testColorDoubleBlurDoubleSepia() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.parseCommand("load res/color.ppm color");
      processor.parseCommand("blur color color-blur-double-sepia");
      processor.parseCommand("blur color color-blur-double-sepia");
      processor.parseCommand("sepia color color-blur-double-sepia");
      processor.parseCommand("sepia color color-blur-double-sepia");
      processor.parseCommand("sepia color-blur-double-sepia color-blur-double-sepia");
      processor.parseCommand("save res/color-blur-double-sepia.ppm color-blur-double-sepia");
      String s = ImageUtil.ppmString("res/color-blur-double-sepia.ppm");
      String s1 = ImageUtil.ppmString("res/color-blur-double-sepia-expected.ppm");
      assertEquals(s, s1);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Test
  public void testColorDoubleBlurTripleSepia() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.parseCommand("load res/color.ppm color");
      processor.parseCommand("blur color color-blur-double-sepia");
      processor.parseCommand("blur color color-blur-double-sepia");
      processor.parseCommand("sepia color color-blur-double-sepia");
      processor.parseCommand("sepia color color-blur-double-sepia");
      processor.parseCommand("sepia color color-blur-double-sepia");
      processor.parseCommand("sepia color-blur-double-sepia color-blur-double-sepia");
      processor.parseCommand("save res/color-blur-double-sepia.ppm color-blur-double-sepia");
      String s = ImageUtil.ppmString("res/color-blur-double-sepia.ppm");
      String s1 = ImageUtil.ppmString("res/color-blur-double-sepia-expected.ppm");
      assertEquals(s, s1);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Test
  public void testColorTripleBlurTripleSepia() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.parseCommand("load res/color.ppm color");
      processor.parseCommand("blur color color-blur-double-sepia");
      processor.parseCommand("blur color color-blur-double-sepia");
      processor.parseCommand("blur color color-blur-double-sepia");
      processor.parseCommand("sepia color color-blur-double-sepia");
      processor.parseCommand("sepia color color-blur-double-sepia");
      processor.parseCommand("sepia color color-blur-double-sepia");
      processor.parseCommand("sepia color-blur-double-sepia color-blur-double-sepia");
      processor.parseCommand("save res/color-blur-double-sepia.ppm color-blur-double-sepia");
      String s = ImageUtil.ppmString("res/color-blur-double-sepia.ppm");
      String s1 = ImageUtil.ppmString("res/color-blur-double-sepia-expected.ppm");
      assertEquals(s, s1);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Test
  public void testSunBlurSepia() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.parseCommand("load res/sun.ppm sun");
      processor.parseCommand("blur sun sun-blur-sepia");
      processor.parseCommand("sepia sun-blur-sepia sun-blur-sepia");
      processor.parseCommand("save res/sun-blur-sepia.ppm sun-blur-sepia");
      String s = ImageUtil.ppmString("res/sun-blur-sepia.ppm");
      String s1 = ImageUtil.ppmString("res/sun-blur-sepia-expected.ppm");
      assertEquals(s, s1);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Test
  public void testSunDoubleBlurSepia() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.parseCommand("load res/sun.ppm sun");
      processor.parseCommand("blur sun sun-blur-sepia");
      processor.parseCommand("blur sun sun-blur-sepia");
      processor.parseCommand("sepia sun-blur-sepia sun-blur-sepia");
      processor.parseCommand("save res/sun-blur-sepia.ppm sun-blur-sepia");
      String s = ImageUtil.ppmString("res/sun-blur-sepia.ppm");
      String s1 = ImageUtil.ppmString("res/sun-blur-sepia-expected.ppm");
      assertEquals(s, s1);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Test
  public void testSunTripleBlurSepia() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.parseCommand("load res/sun.ppm sun");
      processor.parseCommand("blur sun sun-blur-sepia");
      processor.parseCommand("blur sun sun-blur-sepia");
      processor.parseCommand("blur sun sun-blur-sepia");
      processor.parseCommand("sepia sun-blur-sepia sun-blur-sepia");
      processor.parseCommand("save res/sun-blur-sepia.ppm sun-blur-sepia");
      String s = ImageUtil.ppmString("res/sun-blur-sepia.ppm");
      String s1 = ImageUtil.ppmString("res/sun-blur-sepia-expected.ppm");
      assertEquals(s, s1);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Test
  public void testSunBlurDoubleSepia() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.parseCommand("load res/sun.ppm sun");
      processor.parseCommand("blur sun sun-blur-double-sepia");
      processor.parseCommand("sepia sun sun-blur-double-sepia");
      processor.parseCommand("sepia sun-blur-double-sepia sun-blur-double-sepia");
      processor.parseCommand("save res/sun-blur-double-sepia.ppm sun-blur-double-sepia");
      String s = ImageUtil.ppmString("res/sun-blur-double-sepia.ppm");
      String s1 = ImageUtil.ppmString("res/sun-blur-double-sepia-expected.ppm");
      assertEquals(s, s1);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Test
  public void testSunBlurTripleSepia() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.parseCommand("load res/sun.ppm sun");
      processor.parseCommand("blur sun sun-blur-double-sepia");
      processor.parseCommand("sepia sun sun-blur-double-sepia");
      processor.parseCommand("sepia sun sun-blur-double-sepia");
      processor.parseCommand("sepia sun-blur-double-sepia sun-blur-double-sepia");
      processor.parseCommand("save res/sun-blur-double-sepia.ppm sun-blur-double-sepia");
      String s = ImageUtil.ppmString("res/sun-blur-double-sepia.ppm");
      String s1 = ImageUtil.ppmString("res/sun-blur-double-sepia-expected.ppm");
      assertEquals(s, s1);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Test
  public void testSunDoubleBlurDoubleSepia() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.parseCommand("load res/sun.ppm sun");
      processor.parseCommand("blur sun sun-blur-double-sepia");
      processor.parseCommand("blur sun sun-blur-double-sepia");
      processor.parseCommand("sepia sun sun-blur-double-sepia");
      processor.parseCommand("sepia sun sun-blur-double-sepia");
      processor.parseCommand("sepia sun-blur-double-sepia sun-blur-double-sepia");
      processor.parseCommand("save res/sun-blur-double-sepia.ppm sun-blur-double-sepia");
      String s = ImageUtil.ppmString("res/sun-blur-double-sepia.ppm");
      String s1 = ImageUtil.ppmString("res/sun-blur-double-sepia-expected.ppm");
      assertEquals(s, s1);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Test
  public void testSunDoubleBlurTripleSepia() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.parseCommand("load res/sun.ppm sun");
      processor.parseCommand("blur sun sun-blur-double-sepia");
      processor.parseCommand("blur sun sun-blur-double-sepia");
      processor.parseCommand("sepia sun sun-blur-double-sepia");
      processor.parseCommand("sepia sun sun-blur-double-sepia");
      processor.parseCommand("sepia sun sun-blur-double-sepia");
      processor.parseCommand("sepia sun-blur-double-sepia sun-blur-double-sepia");
      processor.parseCommand("save res/sun-blur-double-sepia.ppm sun-blur-double-sepia");
      String s = ImageUtil.ppmString("res/sun-blur-double-sepia.ppm");
      String s1 = ImageUtil.ppmString("res/sun-blur-double-sepia-expected.ppm");
      assertEquals(s, s1);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Test
  public void testSunTripleBlurTripleSepia() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.parseCommand("load res/sun.ppm sun");
      processor.parseCommand("blur sun sun-blur-double-sepia");
      processor.parseCommand("blur sun sun-blur-double-sepia");
      processor.parseCommand("blur sun sun-blur-double-sepia");
      processor.parseCommand("sepia sun sun-blur-double-sepia");
      processor.parseCommand("sepia sun sun-blur-double-sepia");
      processor.parseCommand("sepia sun sun-blur-double-sepia");
      processor.parseCommand("sepia sun-blur-double-sepia sun-blur-double-sepia");
      processor.parseCommand("save res/sun-blur-double-sepia.ppm sun-blur-double-sepia");
      String s = ImageUtil.ppmString("res/sun-blur-double-sepia.ppm");
      String s1 = ImageUtil.ppmString("res/sun-blur-double-sepia-expected.ppm");
      assertEquals(s, s1);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Test
  public void testColorBlurSharpenSepia() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.parseCommand("load res/color.ppm color");
      processor.parseCommand("blur color color-blur-sharpen-sepia");
      processor.parseCommand("sharpen color-blur-sharpen-sepia color-blur-sharpen-sepia");
      processor.parseCommand("sepia color-blur-sharpen-sepia color-blur-sharpen-sepia");
      processor.parseCommand("save res/color-blur-sharpen-sepia.ppm color-blur-sharpen-sepia");
      String s = ImageUtil.ppmString("res/color-blur-sharpen-sepia.ppm");
      String s1 = ImageUtil.ppmString("res/color-blur-sharpen-sepia-expected.ppm");
      assertEquals(s, s1);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Test
  public void testColorDoubleBlurSharpenSepia() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.parseCommand("load res/color.ppm color");
      processor.parseCommand("blur color color-blur-sharpen-sepia");
      processor.parseCommand("blur color color-blur-sharpen-sepia");
      processor.parseCommand("sharpen color-blur-sharpen-sepia color-blur-sharpen-sepia");
      processor.parseCommand("sepia color-blur-sharpen-sepia color-blur-sharpen-sepia");
      processor.parseCommand("save res/color-blur-sharpen-sepia.ppm color-blur-sharpen-sepia");
      String s = ImageUtil.ppmString("res/color-blur-sharpen-sepia.ppm");
      String s1 = ImageUtil.ppmString("res/color-blur-sharpen-sepia-expected.ppm");
      assertEquals(s, s1);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Test
  public void testColorTripleBlurSharpenSepia() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.parseCommand("load res/color.ppm color");
      processor.parseCommand("blur color color-blur-sharpen-sepia");
      processor.parseCommand("blur color color-blur-sharpen-sepia");
      processor.parseCommand("blur color color-blur-sharpen-sepia");
      processor.parseCommand("sharpen color-blur-sharpen-sepia color-blur-sharpen-sepia");
      processor.parseCommand("sepia color-blur-sharpen-sepia color-blur-sharpen-sepia");
      processor.parseCommand("save res/color-blur-sharpen-sepia.ppm color-blur-sharpen-sepia");
      String s = ImageUtil.ppmString("res/color-blur-sharpen-sepia.ppm");
      String s1 = ImageUtil.ppmString("res/color-blur-sharpen-sepia-expected.ppm");
      assertEquals(s, s1);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Test
  public void testColorBlurDoubleSharpenSepia() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.parseCommand("load res/color.ppm color");
      processor.parseCommand("blur color color-blur-sharpen-sepia");
      processor.parseCommand("sharpen color color-blur-sharpen-sepia");
      processor.parseCommand("sharpen color-blur-sharpen-sepia color-blur-sharpen-sepia");
      processor.parseCommand("sepia color-blur-sharpen-sepia color-blur-sharpen-sepia");
      processor.parseCommand("save " +
              "res/color-blur-double-sharpen-sepia.ppm color-blur-sharpen-sepia");
      String s = ImageUtil.ppmString("res/color-blur-double-sharpen-sepia.ppm");
      String s1 = ImageUtil.ppmString("res/color-blur-double-sharpen-sepia-expected.ppm");
      assertEquals(s, s1);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Test
  public void testColorBlurTripleSharpenSepia() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.parseCommand("load res/color.ppm color");
      processor.parseCommand("blur color color-blur-sharpen-sepia");
      processor.parseCommand("sharpen color color-blur-sharpen-sepia");
      processor.parseCommand("sharpen color color-blur-sharpen-sepia");
      processor.parseCommand("sharpen color-blur-sharpen-sepia color-blur-sharpen-sepia");
      processor.parseCommand("sepia color-blur-sharpen-sepia color-blur-sharpen-sepia");
      processor.parseCommand("save res/color-blur-double-sharpen-sepia.ppm " +
              "color-blur-sharpen-sepia");
      String s = ImageUtil.ppmString("res/color-blur-double-sharpen-sepia.ppm");
      String s1 = ImageUtil.ppmString("res/color-blur-double-sharpen-sepia-expected.ppm");
      assertEquals(s, s1);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Test
  public void testColorDoubleBlurTripleSharpenSepia() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.parseCommand("load res/color.ppm color");
      processor.parseCommand("blur color color-blur-sharpen-sepia");
      processor.parseCommand("blur color color-blur-sharpen-sepia");
      processor.parseCommand("sharpen color color-blur-sharpen-sepia");
      processor.parseCommand("sharpen color color-blur-sharpen-sepia");
      processor.parseCommand("sharpen color-blur-sharpen-sepia color-blur-sharpen-sepia");
      processor.parseCommand("sepia color-blur-sharpen-sepia color-blur-sharpen-sepia");
      processor.parseCommand("save res/color-blur-double-sharpen-sepia.ppm " +
              "color-blur-sharpen-sepia");
      String s = ImageUtil.ppmString("res/color-blur-double-sharpen-sepia.ppm");
      String s1 = ImageUtil.ppmString("res/color-blur-double-sharpen-sepia-expected.ppm");
      assertEquals(s, s1);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Test
  public void testColorTripleBlurTripleSharpenSepia() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.parseCommand("load res/color.ppm color");
      processor.parseCommand("blur color color-blur-sharpen-sepia");
      processor.parseCommand("blur color color-blur-sharpen-sepia");
      processor.parseCommand("blur color color-blur-sharpen-sepia");
      processor.parseCommand("sharpen color color-blur-sharpen-sepia");
      processor.parseCommand("sharpen color color-blur-sharpen-sepia");
      processor.parseCommand("sharpen color-blur-sharpen-sepia color-blur-sharpen-sepia");
      processor.parseCommand("sepia color-blur-sharpen-sepia color-blur-sharpen-sepia");
      processor.parseCommand("save res/color-blur-double-sharpen-sepia.ppm" +
              " color-blur-sharpen-sepia");
      String s = ImageUtil.ppmString("res/color-blur-double-sharpen-sepia.ppm");
      String s1 = ImageUtil.ppmString("res/color-blur-double-sharpen-sepia-expected.ppm");
      assertEquals(s, s1);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Test
  public void testColorTripleBlurTripleSharpenDoubleSepia() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.parseCommand("load res/color.ppm color");
      processor.parseCommand("blur color color-blur-sharpen-sepia");
      processor.parseCommand("blur color color-blur-sharpen-sepia");
      processor.parseCommand("blur color color-blur-sharpen-sepia");
      processor.parseCommand("sharpen color color-blur-sharpen-sepia");
      processor.parseCommand("sharpen color color-blur-sharpen-sepia");
      processor.parseCommand("sharpen color-blur-sharpen-sepia color-blur-sharpen-sepia");
      processor.parseCommand("sepia color-blur-sharpen-sepia color-blur-sharpen-sepia");
      processor.parseCommand("sepia color-blur-sharpen-sepia color-blur-sharpen-sepia");
      processor.parseCommand("save res/color-blur-double-sharpen-double-sepia.ppm" +
              " color-blur-sharpen-sepia");
      String s = ImageUtil.ppmString("res/color-blur-double-sharpen-double-sepia.ppm");
      String s1 = ImageUtil.ppmString(
              "res/color-blur-double-sharpen-double-sepia-expected.ppm");
      assertEquals(s, s1);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Test
  public void testColorTripleBlurTripleSharpenTripleSepia() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.parseCommand("load res/color.ppm color");
      processor.parseCommand("blur color color-blur-sharpen-sepia");
      processor.parseCommand("blur color color-blur-sharpen-sepia");
      processor.parseCommand("blur color color-blur-sharpen-sepia");
      processor.parseCommand("sharpen color color-blur-sharpen-sepia");
      processor.parseCommand("sharpen color color-blur-sharpen-sepia");
      processor.parseCommand("sharpen color-blur-sharpen-sepia color-blur-sharpen-sepia");
      processor.parseCommand("sepia color-blur-sharpen-sepia color-blur-sharpen-sepia");
      processor.parseCommand("sepia color-blur-sharpen-sepia color-blur-sharpen-sepia");
      processor.parseCommand("sepia color-blur-sharpen-sepia color-blur-sharpen-sepia");
      processor.parseCommand("save res/color-blur-triple-sharpen-triple-sepia.ppm " +
              "color-blur-sharpen-sepia");
      String s = ImageUtil.ppmString("res/color-blur-triple-sharpen-triple-sepia.ppm");
      String s1 = ImageUtil.ppmString(
              "res/color-blur-triple-sharpen-triple-sepia-expected.ppm");
      assertEquals(s, s1);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Test
  public void testSunBlurSharpenSepia() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.parseCommand("load res/sun.ppm sun");
      processor.parseCommand("blur sun sun-blur-sharpen-sepia");
      processor.parseCommand("sharpen sun-blur-sharpen-sepia sun-blur-sharpen-sepia");
      processor.parseCommand("sepia sun-blur-sharpen-sepia sun-blur-sharpen-sepia");
      processor.parseCommand("save res/sun-blur-sharpen-sepia.ppm sun-blur-sharpen-sepia");
      String s = ImageUtil.ppmString("res/sun-blur-sharpen-sepia.ppm");
      String s1 = ImageUtil.ppmString("res/sun-blur-sharpen-sepia-expected.ppm");
      assertEquals(s, s1);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Test
  public void testSunDoubleBlurSharpenSepia() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.parseCommand("load res/sun.ppm sun");
      processor.parseCommand("blur sun sun-blur-sharpen-sepia");
      processor.parseCommand("blur sun sun-blur-sharpen-sepia");
      processor.parseCommand("sharpen sun-blur-sharpen-sepia sun-blur-sharpen-sepia");
      processor.parseCommand("sepia sun-blur-sharpen-sepia sun-blur-sharpen-sepia");
      processor.parseCommand("save res/sun-blur-sharpen-sepia.ppm sun-blur-sharpen-sepia");
      String s = ImageUtil.ppmString("res/sun-blur-sharpen-sepia.ppm");
      String s1 = ImageUtil.ppmString("res/sun-blur-sharpen-sepia-expected.ppm");
      assertEquals(s, s1);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Test
  public void testSunTripleBlurSharpenSepia() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.parseCommand("load res/sun.ppm sun");
      processor.parseCommand("blur sun sun-blur-sharpen-sepia");
      processor.parseCommand("blur sun sun-blur-sharpen-sepia");
      processor.parseCommand("blur sun sun-blur-sharpen-sepia");
      processor.parseCommand("sharpen sun-blur-sharpen-sepia sun-blur-sharpen-sepia");
      processor.parseCommand("sepia sun-blur-sharpen-sepia sun-blur-sharpen-sepia");
      processor.parseCommand("save res/sun-blur-sharpen-sepia.ppm sun-blur-sharpen-sepia");
      String s = ImageUtil.ppmString("res/sun-blur-sharpen-sepia.ppm");
      String s1 = ImageUtil.ppmString("res/sun-blur-sharpen-sepia-expected.ppm");
      assertEquals(s, s1);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Test
  public void testSunBlurDoubleSharpenSepia() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.parseCommand("load res/sun.ppm sun");
      processor.parseCommand("blur sun sun-blur-sharpen-sepia");
      processor.parseCommand("sharpen sun sun-blur-sharpen-sepia");
      processor.parseCommand("sharpen sun-blur-sharpen-sepia sun-blur-sharpen-sepia");
      processor.parseCommand("sepia sun-blur-sharpen-sepia sun-blur-sharpen-sepia");
      processor.parseCommand("save res/sun-blur-double-sharpen-sepia.ppm sun-blur-sharpen-sepia");
      String s = ImageUtil.ppmString("res/sun-blur-double-sharpen-sepia.ppm");
      String s1 = ImageUtil.ppmString("res/sun-blur-double-sharpen-sepia-expected.ppm");
      assertEquals(s, s1);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Test
  public void testSunBlurTripleSharpenSepia() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.parseCommand("load res/sun.ppm sun");
      processor.parseCommand("blur sun sun-blur-sharpen-sepia");
      processor.parseCommand("sharpen sun sun-blur-sharpen-sepia");
      processor.parseCommand("sharpen sun sun-blur-sharpen-sepia");
      processor.parseCommand("sharpen sun-blur-sharpen-sepia sun-blur-sharpen-sepia");
      processor.parseCommand("sepia sun-blur-sharpen-sepia sun-blur-sharpen-sepia");
      processor.parseCommand("save res/sun-blur-double-sharpen-sepia.ppm sun-blur-sharpen-sepia");
      String s = ImageUtil.ppmString("res/sun-blur-double-sharpen-sepia.ppm");
      String s1 = ImageUtil.ppmString("res/sun-blur-double-sharpen-sepia-expected.ppm");
      assertEquals(s, s1);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Test
  public void testSunDoubleBlurTripleSharpenSepia() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.parseCommand("load res/sun.ppm sun");
      processor.parseCommand("blur sun sun-blur-sharpen-sepia");
      processor.parseCommand("blur sun sun-blur-sharpen-sepia");
      processor.parseCommand("sharpen sun sun-blur-sharpen-sepia");
      processor.parseCommand("sharpen sun sun-blur-sharpen-sepia");
      processor.parseCommand("sharpen sun-blur-sharpen-sepia sun-blur-sharpen-sepia");
      processor.parseCommand("sepia sun-blur-sharpen-sepia sun-blur-sharpen-sepia");
      processor.parseCommand("save res/sun-blur-double-sharpen-sepia.ppm sun-blur-sharpen-sepia");
      String s = ImageUtil.ppmString("res/sun-blur-double-sharpen-sepia.ppm");
      String s1 = ImageUtil.ppmString("res/sun-blur-double-sharpen-sepia-expected.ppm");
      assertEquals(s, s1);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Test
  public void testSunTripleBlurTripleSharpenSepia() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.parseCommand("load res/sun.ppm sun");
      processor.parseCommand("blur sun sun-blur-sharpen-sepia");
      processor.parseCommand("blur sun sun-blur-sharpen-sepia");
      processor.parseCommand("blur sun sun-blur-sharpen-sepia");
      processor.parseCommand("sharpen sun sun-blur-sharpen-sepia");
      processor.parseCommand("sharpen sun sun-blur-sharpen-sepia");
      processor.parseCommand("sharpen sun-blur-sharpen-sepia sun-blur-sharpen-sepia");
      processor.parseCommand("sepia sun-blur-sharpen-sepia sun-blur-sharpen-sepia");
      processor.parseCommand("save res/sun-blur-double-sharpen-sepia.ppm sun-blur-sharpen-sepia");
      String s = ImageUtil.ppmString("res/sun-blur-double-sharpen-sepia.ppm");
      String s1 = ImageUtil.ppmString("res/sun-blur-double-sharpen-sepia-expected.ppm");
      assertEquals(s, s1);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Test
  public void testSunTripleBlurTripleSharpenDoubleSepia() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.parseCommand("load res/sun.ppm sun");
      processor.parseCommand("blur sun sun-blur-sharpen-sepia");
      processor.parseCommand("blur sun sun-blur-sharpen-sepia");
      processor.parseCommand("blur sun sun-blur-sharpen-sepia");
      processor.parseCommand("sharpen sun sun-blur-sharpen-sepia");
      processor.parseCommand("sharpen sun sun-blur-sharpen-sepia");
      processor.parseCommand("sharpen sun-blur-sharpen-sepia sun-blur-sharpen-sepia");
      processor.parseCommand("sepia sun-blur-sharpen-sepia sun-blur-sharpen-sepia");
      processor.parseCommand("sepia sun-blur-sharpen-sepia sun-blur-sharpen-sepia");
      processor.parseCommand("save res/sun-blur-double-sharpen-double-sepia.ppm " +
              "sun-blur-sharpen-sepia");
      String s = ImageUtil.ppmString("res/sun-blur-double-sharpen-double-sepia.ppm");
      String s1 = ImageUtil.ppmString(
              "res/sun-blur-double-sharpen-double-sepia-expected.ppm");
      assertEquals(s, s1);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Test
  public void testSunTripleBlurTripleSharpenTripleSepia() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.parseCommand("load res/sun.ppm sun");
      processor.parseCommand("blur sun sun-blur-sharpen-sepia");
      processor.parseCommand("blur sun sun-blur-sharpen-sepia");
      processor.parseCommand("blur sun sun-blur-sharpen-sepia");
      processor.parseCommand("sharpen sun sun-blur-sharpen-sepia");
      processor.parseCommand("sharpen sun sun-blur-sharpen-sepia");
      processor.parseCommand("sharpen sun-blur-sharpen-sepia sun-blur-sharpen-sepia");
      processor.parseCommand("sepia sun-blur-sharpen-sepia sun-blur-sharpen-sepia");
      processor.parseCommand("sepia sun-blur-sharpen-sepia sun-blur-sharpen-sepia");
      processor.parseCommand("sepia sun-blur-sharpen-sepia sun-blur-sharpen-sepia");
      processor.parseCommand("save res/sun-blur-triple-sharpen-triple-sepia.ppm " +
              "sun-blur-sharpen-sepia");
      String s = ImageUtil.ppmString("res/sun-blur-triple-sharpen-triple-sepia.ppm");
      String s1 = ImageUtil.ppmString(
              "res/sun-blur-triple-sharpen-triple-sepia-expected.ppm");
      assertEquals(s, s1);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Test
  public void testJPEGSAVE() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.parseCommand("load res/plane.jpeg plane");
      processor.parseCommand("blur plane plane-new");
      processor.parseCommand("save res/plane-new.jpeg plane-new");
      String s = ImageUtil.ppmString("res/plane-new.jpeg");
      String s1 = ImageUtil.ppmString("res/plane.jpeg");
      assertEquals(s, s1);

    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }


  @Test
  public void testGUI() {
    try {
      JButton j = new JButton("save");
      //create new GUI
      ImageProcessorGUI i = new ImageProcessorGUI();
      //create an Action Event
      ActionEvent a = new ActionEvent(j, 12, "save");
      // passes actionEvent through GUI methods
      i.actionPerformed(a);
    } catch (Exception e) {
      throw new IllegalArgumentException(e.getMessage());
    }
    try {
      // creates new controller and saves image and compares
      ImageProcessor processor = new ImageProcessor();
      processor.loadImage("sun", "res/sun.ppm");
      processor.saveImage("UserTest", "res/UserTest");
      String s = ImageUtil.ppmString("res/UserTest");
      String s1 = ImageUtil.ppmString("res/sun.ppm");
      System.out.println("try2");
      assertEquals(s, s1);

    } catch (Exception e) {
      throw new IllegalArgumentException(e.getMessage());
    }

  }

  @Test
  public void testGUI2() {
    try {
      JButton j = new JButton("load");
      ImageProcessorGUI i = new ImageProcessorGUI();
      ActionEvent a = new ActionEvent(j, 12, "load");
      i.actionPerformed(a);
    } catch (Exception e) {
      throw new IllegalArgumentException(e.getMessage());
    }
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.loadImage("sun", "res/sun.ppm");
      processor.saveImage("UserTest", "res/UserTest");
      String s = ImageUtil.ppmString("res/UserTest");
      String s1 = ImageUtil.ppmString("res/sun.ppm");
      System.out.println("try2");
      assertEquals(s, s1);

    } catch (Exception e) {
      throw new IllegalArgumentException(e.getMessage());
    }

  }

  @Test
  public void testJPEGSAVE2() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.parseCommand("load res/plane.jpeg plane");
      processor.parseCommand("blur plane plane-new");
      processor.parseCommand("sharpen plane plane-new");
      processor.parseCommand("save res/plane-new.jpeg plane-new");
      String s = ImageUtil.ppmString("res/plane-new.jpeg");
      String s1 = ImageUtil.ppmString("res/plane.jpeg");
      assertEquals(s, s1);

    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Test
  public void testJPEGSAVE3() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.parseCommand("load res/plane.jpeg plane");
      processor.parseCommand("sepia plane plane-new");
      processor.parseCommand("save res/plane-new.jpeg plane-new");
      String s = ImageUtil.ppmString("res/plane-new.jpeg");
      String s1 = ImageUtil.ppmString("res/plane.jpeg");
      assertEquals(s, s1);

    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Test
  public void testParseCommand() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.parseCommand("load res/plane.jpeg plane");

      processor.parseCommand("sepia plane plane-new");
      processor.parseCommand("save res/plane-new.jpeg plane-new");
      String s = ImageUtil.ppmString("res/plane-new.jpeg");
      String s1 = ImageUtil.ppmString("res/plane.jpeg");
      assertEquals(s, s1);

    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

  /**
   * Testing image mosaic.
   */
  @Test
  public void testAppleMosaic() {
    try {
      ImageProcessor processor = new ImageProcessor();
      processor.parseCommand("load apple.jpg apple");
      processor.parseCommand("mosaic 200 apple apple-mosaic");
      processor.parseCommand("save apple-mosaic.jpg apple-mosaic");
      String s = ImageUtil.ppmString("apple.jpg");
      String s1 = ImageUtil.ppmString("apple-mosaic.jpg");
      assertEquals(s, s1);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

}



