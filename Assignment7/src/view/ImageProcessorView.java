package view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

import controller.ImageProcessor;

/**
 * The purpose of this class is to represent the ImageProcessorView and use it to practice testing.
 */
public class ImageProcessorView implements IView {
  private ImageProcessor imageProcessor;

  public ImageProcessorView() {
    imageProcessor = new ImageProcessor();
  }

  /**
   * The purpose of this method is to run the actual commands when a user wants to test the Image
   * Processor and see if it runs appropriately.
   */
  public void run() {
    try {

      Scanner scanner = new Scanner(System.in);
      String s = "";
      while (!s.equals("exit")) {
        System.out.print("enter command: ");
        s = scanner.nextLine();
        imageProcessor.parseCommand(s);
      }
      scanner.close();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * The purpose of this method is to be the main method to practice using the parameters in
   * ImageProcessor.
   *
   * @param args represents the arguments in the main method.
   */
  public static void main(String[] args) {
    ImageProcessorView imageProcessorView = new ImageProcessorView();
    if (args.length == 2 && args[0].equals("-file")) {
      String filename = args[1];
      imageProcessorView.readCommands(filename);
    } else {
      imageProcessorView.run();
    }

  }

  /**
   * reads commands from a file.
   *
   * @param filename represents a file path.
   */
  public void readCommands(String filename) {
    try {
      File file = new File(filename);
      FileReader fileReader = new FileReader(file);
      BufferedReader bufferedReader = new BufferedReader(fileReader);
      String command = "";
      while ((command = bufferedReader.readLine()) != null) {
        imageProcessor.parseCommand(command);
      }

    } catch (Exception ex) {
      System.out.println("Error reading File");


    }
  }
}
