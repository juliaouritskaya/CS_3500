import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

import controller.Features;
import controller.GUIController;
import controller.ImageController;
import model.ProcessingModel;
import model.ProcessingModelImpl;
import view.GUIView;
import view.View;

/**
 * The driver of this application.
 */
public class ImageMain {
  /**
   * main method of the program.
   *
   * @param args any command line arguments
   */
  public static void main(String[] args) {
    StringBuilder builder = new StringBuilder();
    ProcessingModel model = new ProcessingModelImpl();
    View view;
    Features guiController;
    ImageController controller;
    String filename;
    File file;
    BufferedReader in = null;


    StringBuilder sB = new StringBuilder();

    for (String s : args) {
      sB.append(s);
    }

    // when invoked in this manner the program should open the graphical user interface
    if (sB.toString().equals("")) {
      view = new GUIView();
      guiController = new GUIController(model);
      guiController.makeVisible(view);
    // when invoked in this manner the program should open the script file,
    // execute it and then shut down
    } else if (args.length == 2) {
      if (args[0].equals("-file")) {
        filename = args[1];
        file = new File(filename);
        try {
          in = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        } catch (FileNotFoundException e) {
          e.printStackTrace();
        }
        controller = new ImageController(in, System.out);

        controller.control();
      }
    // when invoked in this manner the program should open in an interactive text mode,
    // allowing the user to type the script and execute it one line at a time
    } else if (args.length == 1) {
      if (args[0].equals("-text")) {
        if (in == null) {
          in = new BufferedReader(new InputStreamReader(System.in));
        }

        controller = new ImageController(in, System.out);

        controller.control();
      }
    }
  }
}
