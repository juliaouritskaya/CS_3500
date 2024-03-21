import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

import controller.ImageController;

/**
 * The driver of this application.
 */
public class ImageMain {
  /**
   * main method of the program.
   *
   * @param args any command line arguments
   */
  public static void main(String []args) {
    String filename;
    File file;
    BufferedReader in = null;

    if (args.length == 2) {
      if (args[0].equals("-file")) {
        filename = args[1];
        file = new File(filename);
        try {
          in = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        } catch (FileNotFoundException e) {
          e.printStackTrace();
        }
      }
    }
    if (in == null) {
      in = new BufferedReader(new InputStreamReader(System.in));
    }

    ImageController controller = new ImageController(in, System.out);

    controller.control();
  }
}
