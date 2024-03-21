package controller;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;
import model.ProcessingModel;
import model.ProcessingModelImpl;
import operations.BlueComponent;
import operations.Blur;
import operations.Brighten;
import operations.FlipHorizontal;
import operations.FlipVertical;
import operations.GreenComponent;
import operations.Greyscale;
import operations.ImageOperationCommand;
import operations.Intensity;
import operations.Luma;
import operations.Mosaic;
import operations.RedComponent;
import operations.Sepia;
import operations.Sharpen;
import operations.Value;

/**
 * This class represents the controller of an interactive image processing application.
 * This controller offers a simple text interface in which the user can
 * type instructions to manipulate an image.
 *
 * <p>This controller works with any Readable to read its inputs and
 * * any Appendable to transmit output. This controller directly uses
 * * the Appendable object (i.e. there is no official "view")
 */
public class ImageController {
  private final ProcessingModel images;
  private Readable readable;
  private Appendable appendable;

  /**
   * Creates an instance of an image controller.
   *
   * @param readable   the Readable object for inputs
   * @param appendable the Appendable objects to transmit any output
   */
  public ImageController(Readable readable, Appendable appendable) {
    this.readable = Objects.requireNonNull(readable);
    this.appendable = Objects.requireNonNull(appendable);
    this.images = new ProcessingModelImpl();
  }

  /**
   * Writes the specified string message and appends it.
   *
   * @param message the string inputted.
   * @throws IllegalArgumentException if the something went wrong while
   *                                  constructing the string output.
   */
  protected void writeMessage(String message) throws IllegalStateException {
    try {
      appendable.append(message);

    } catch (IOException e) {
      throw new IllegalStateException(e.getMessage());
    }
  }

  /**
   * The main method that relinquishes control of the application to the controller.
   *
   * @throws IllegalStateException if the controller is unable to transmit output
   */
  public void control() throws IllegalStateException {
    Scanner sc = new Scanner(readable);
    boolean quit = false;

    //print the welcome message
    this.welcomeMessage();

    while (!quit && sc.hasNext()) { //continue until the user quits
      writeMessage("Type operation: "); //prompt for the instruction name
      String input = sc.nextLine();
      String[] userInstruction = input.split(" ");
      //take an instruction name
      if (userInstruction[0].equals("quit") || userInstruction[0].equals("q")) {
        quit = true;
      } else if (userInstruction[0].equals("load")) {
        writeMessage("Loading file.\n");
        try {
          this.images.load(userInstruction[1], userInstruction[2]);
          writeMessage("File loaded.\n");
        } catch (IllegalArgumentException e) {
          writeMessage("Error: " + e.getMessage() + System.lineSeparator());
        }
      } else if (userInstruction[0].equals("save")) {
        writeMessage("Saving image.\n");
        try {
          this.images.save(userInstruction[1], userInstruction[2]);
          writeMessage("Image saved.\n");
        } catch (IllegalArgumentException e) {
          writeMessage("Error: " + e.getMessage() + System.lineSeparator());
        }
      } else {
        processCommand(userInstruction);
      }
    }

    //after the user has quit, print farewell message
    this.farewellMessage();

  }

  /**
   * Processes the user input and executes the correct command on the image.
   *
   * @param userInstruction the script command the user enters
   */
  protected void processCommand(String [] userInstruction) {
    ImageOperationCommand cmd;
    String input = userInstruction[0];

    switch (input) {
      case "red-component":
        try {
          cmd = new RedComponent(userInstruction[1], userInstruction[2]);
          cmd.execute(images);
        } catch (IllegalArgumentException e) {
          writeMessage("Error: " + e.getMessage() + System.lineSeparator());
        }
        break;
      case "green-component":
        try {
          cmd = new GreenComponent(userInstruction[1], userInstruction[2]);
          cmd.execute(images);
        } catch (IllegalArgumentException e) {
          writeMessage("Error: " + e.getMessage() + System.lineSeparator());
        }
        break;
      case "blue-component":
        try {
          cmd = new BlueComponent(userInstruction[1], userInstruction[2]);
          cmd.execute(images);
        } catch (IllegalArgumentException e) {
          writeMessage("Error: " + e.getMessage() + System.lineSeparator());
        }
        break;
      case "intensity":
        try {
          cmd = new Intensity(userInstruction[1], userInstruction[2]);
          cmd.execute(images);
        } catch (IllegalArgumentException e) {
          writeMessage("Error: " + e.getMessage() + System.lineSeparator());
        }
        break;
      case "luma":
        try {
          cmd = new Luma(userInstruction[1], userInstruction[2]);
          cmd.execute(images);
        } catch (IllegalArgumentException e) {
          writeMessage("Error: " + e.getMessage() + System.lineSeparator());
        }
        break;
      case "value":
        try {
          cmd = new Value(userInstruction[1], userInstruction[2]);
          cmd.execute(images);
        } catch (IllegalArgumentException e) {
          writeMessage("Error: " + e.getMessage() + System.lineSeparator());
        }
        break;
      case "horizontal-flip":
        try {
          cmd = new FlipHorizontal(userInstruction[1], userInstruction[2]);
          cmd.execute(images);
        } catch (IllegalArgumentException e) {
          writeMessage("Error: " + e.getMessage() + System.lineSeparator());
        }
        break;
      case "vertical-flip":
        try {
          cmd = new FlipVertical(userInstruction[1], userInstruction[2]);
          cmd.execute(images);
        } catch (IllegalArgumentException e) {
          writeMessage("Error: " + e.getMessage() + System.lineSeparator());
        }
        break;
      case "brighten":
        try {
          cmd = new Brighten(Integer.parseInt(userInstruction[1]),
                  userInstruction[2], userInstruction[3]);
          cmd.execute(images);
        } catch (IllegalArgumentException e) {
          writeMessage("Error: " + e.getMessage() + System.lineSeparator());
        }
        break;
      case "mosaic":
        try {
          cmd = new Mosaic(Integer.parseInt(userInstruction[1]),
                  userInstruction[2], userInstruction[3]);
          cmd.execute(images);
        } catch (IllegalArgumentException e) {
          writeMessage("Error: " + e.getMessage() + System.lineSeparator());
        }
        break;
      case "sepia":
        try {
          cmd = new Sepia(userInstruction[1], userInstruction[2]);
          cmd.execute(images);
        } catch (IllegalArgumentException e) {
          writeMessage("Error: " + e.getMessage() + System.lineSeparator());
        }
        break;
      case "greyscale":
        try {
          cmd = new Greyscale(userInstruction[1], userInstruction[2]);
          cmd.execute(images);
        } catch (IllegalArgumentException e) {
          writeMessage("Error: " + e.getMessage() + System.lineSeparator());
        }
        break;
      case "sharpen":
        try {
          cmd = new Sharpen(userInstruction[1], userInstruction[2]);
          cmd.execute(images);
        } catch (IllegalArgumentException e) {
          writeMessage("Error: " + e.getMessage() + System.lineSeparator());
        }
        break;
      case "blur":
        try {
          cmd = new Blur(userInstruction[1], userInstruction[2]);
          cmd.execute(images);
        } catch (IllegalArgumentException e) {
          writeMessage("Error: " + e.getMessage() + System.lineSeparator());
        }
        break;
      default: //error due to unrecognized instruction
        writeMessage("Undefined instruction: " + userInstruction + System.lineSeparator());

    }
  }

  /**
   * Writes the welcome message of the program.
   *
   * @throws IllegalStateException if it fails to render the message
   */
  protected void welcomeMessage() throws IllegalStateException {
    writeMessage("Welcome to the image processing program!" + System.lineSeparator());
    printMenu();
  }

  /**
   * Writes the menu message of the program.
   *
   * @throws IllegalStateException if it fails to render the message
   */
  protected void printMenu() throws IllegalStateException {
    writeMessage("Supported user operations are: " + System.lineSeparator());
    writeMessage("load image-path image-name (load an image)"
            + System.lineSeparator());
    writeMessage("save image-path image-name (save an image)"
            + System.lineSeparator());
    writeMessage("red-component image-name dest-image-name " +
            "(create greyscale image with the red component)"
            + System.lineSeparator());
    writeMessage("green-component image-name dest-image-name " +
            "(create greyscale image with the green component)"
            + System.lineSeparator());
    writeMessage("blue-component image-name dest-image-name " +
            "(create greyscale image with the blue component)"
            + System.lineSeparator());
    writeMessage("intensity image-name dest-image-name " +
            "(create greyscale image with the intensity component)"
            + System.lineSeparator());
    writeMessage("luma image-name dest-image-name " +
            "(create greyscale image with the luma component)"
            + System.lineSeparator());
    writeMessage("value image-name dest-image-name " +
            "(create greyscale image with the value component)"
            + System.lineSeparator());
    writeMessage("horizontal-flip image-name dest-image-name " +
            "(flips image horizontally)"
            + System.lineSeparator());
    writeMessage("vertical-flip image-name dest-image-name " +
            "(flips image vertically)"
            + System.lineSeparator());
    writeMessage("brighten increment image-name dest-image-name " +
            "(brighten or darken an image)"
            + System.lineSeparator());
    writeMessage("mosaic seeds image-name dest-image-name " +
            "(creates a mosaic image)"
            + System.lineSeparator());
    writeMessage("sepia image-name dest-image-name " +
            "(create sepia image)"
            + System.lineSeparator());
    writeMessage("greyscale image-name dest-image-name " +
            "(create greyscale image with the luma component)"
            + System.lineSeparator());
    writeMessage("sharpen image-name dest-image-name " +
            "(sharpens an image)"
            + System.lineSeparator());
    writeMessage("blur image-name dest-image-name " +
            "(blurs an image)"
            + System.lineSeparator());
    writeMessage("menu (Print supported instruction list)" + System.lineSeparator());
    writeMessage("q or quit (quit the program) " + System.lineSeparator());
  }

  /**
   * Writes the farewell message of the program.
   *
   * @throws IllegalStateException if it fails to render the message
   */
  protected void farewellMessage() throws IllegalStateException {
    writeMessage("Thank you for using this program!");
  }
}
