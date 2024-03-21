package controller;

import java.awt.*;
import java.io.File;
import java.io.StringReader;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.Image;
import model.ImageUtil;
import model.ProcessingModel;
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
import view.View;

public class GUIController implements Features {
  private ProcessingModel model;
  private View view;
  private ImageController controller;

  public GUIController(ProcessingModel model) {
    this.model = model;
    this.controller = new ImageController(new StringReader(""), new StringBuilder());
  }

  /**
   * Make the view visible. This is usually called
   * after the view is constructed
   *
   * @param view the view to make visible.
   */
  @Override
  public void makeVisible(View view) {
    this.view = view;
    view.addFeatures(this);
  }

  /**
   * Loads the specified image into the GUI view when the "load" button is clicked.
   */
  @Override
  public void loadImage() {
    final JFileChooser fileChooser = new JFileChooser();
    FileNameExtensionFilter filter = new FileNameExtensionFilter("Image files",
            "ppm", "png", "jpeg", "bmp");
    fileChooser.setFileFilter(filter);
    int returnValue = fileChooser.showOpenDialog((Component) this.view);

    if (returnValue == JFileChooser.APPROVE_OPTION) {
      File file = fileChooser.getSelectedFile();
      String path = file.getAbsolutePath();
      String imageName = JOptionPane.showInputDialog("Image name: ");
      if (imageName.equals("")) {
        this.view.renderMessage("Please name the file.");
        return;
      }
      this.model.load(path, imageName);
      Image image = this.model.getImage(imageName);
      //Map<Integer, int[]> histogram = this.model.getHistogramTable(imageName);
      this.view.addImage(imageName, image);
      this.view.updateImage(imageName, image);
    }

  }

  /**
   * Saves the specified image from the GUI view when the "save" button is clicked.
   *
   * @param imageName the name of the image to save.
   */
  @Override
  public void saveImage(String imageName) {
    DefaultListModel<String> images = this.view.getImages();
    if (imageName == null || images.size() == 0) {
      this.view.renderMessage("Image does not exist");
    }

    final JFileChooser fileChooser = new JFileChooser();
    FileNameExtensionFilter filter = new FileNameExtensionFilter("Image files",
            "ppm", "png", "jpeg", "bmp");
    fileChooser.setFileFilter(filter);
    int userReply = fileChooser.showSaveDialog((Component) this.view);

    if (userReply == JFileChooser.CANCEL_OPTION) {
      return;
    }

    if (userReply == JFileChooser.APPROVE_OPTION) {
      File file = fileChooser.getSelectedFile();
      if (file == null) {
        this.view.renderMessage("File type must be either .ppm, .png, .bmp, or .jpeg");
        return;
      }

      String imagePath = file.getAbsolutePath();
      Image image = this.model.getImage(imageName);
      ImageUtil.writePPM(imagePath, image);
      this.view.renderMessage("File saved.");
    }
  }

  /**
   * Displays the specified image in the GUI view when selected from the list.
   *
   * @param imageName the name of the image to be displayed.
   */
  @Override
  public void displayImage(String imageName) {
    Image image = this.model.getImage(imageName);
    //Map<Integer, int[]> histogram = this.model.getHistogramTable(imageName);
    this.view.updateImage(imageName, image);
  }

  /**
   * Applies the specified filter on the specified image.
   *
   * @param imageName the name of the image to be filtered.
   * @param filter    the name of the filter to apply on the image.
   */
  @Override
  public void applyFilter(String imageName, String filter) {
    ImageOperationCommand cmd;

    if (imageName == null || filter == null) {
      this.view.renderMessage("Must select image and filter.");
    }

    String renamedImage = JOptionPane.showInputDialog("Rename new image as: ");

    if (renamedImage == null) {
      this.view.renderMessage("Must provide image name.");
    }

    switch (filter) {
      case "red-component":
        try {
          cmd = new RedComponent(imageName, renamedImage);
          cmd.execute(this.model);
        } catch (IllegalArgumentException e) {
          this.view.renderMessage("Error: " + e.getMessage());
        }
        break;
      case "green-component":
        try {
          cmd = new GreenComponent(imageName, renamedImage);
          cmd.execute(this.model);
        } catch (IllegalArgumentException e) {
          this.view.renderMessage("Error: " + e.getMessage());
        }
        break;
      case "blue-component":
        try {
          cmd = new BlueComponent(imageName, renamedImage);
          cmd.execute(this.model);
        } catch (IllegalArgumentException e) {
          this.view.renderMessage("Error: " + e.getMessage());
        }
        break;
      case "intensity":
        try {
          cmd = new Intensity(imageName, renamedImage);
          cmd.execute(this.model);
        } catch (IllegalArgumentException e) {
          this.view.renderMessage("Error: " + e.getMessage());
        }
        break;
      case "luma":
        try {
          cmd = new Luma(imageName, renamedImage);
          cmd.execute(this.model);
        } catch (IllegalArgumentException e) {
          this.view.renderMessage("Error: " + e.getMessage());
        }
        break;
      case "value":
        try {
          cmd = new Value(imageName, renamedImage);
          cmd.execute(this.model);
        } catch (IllegalArgumentException e) {
          this.view.renderMessage("Error: " + e.getMessage());
        }
        break;
      case "horizontal-flip":
        try {
          cmd = new FlipHorizontal(imageName, renamedImage);
          cmd.execute(this.model);
        } catch (IllegalArgumentException e) {
          this.view.renderMessage("Error: " + e.getMessage());
        }
        break;
      case "vertical-flip":
        try {
          cmd = new FlipVertical(imageName, renamedImage);
          cmd.execute(this.model);
        } catch (IllegalArgumentException e) {
          this.view.renderMessage("Error: " + e.getMessage());
        }
        break;
      case "brighten":
        int increment = 0;
        String userInput = JOptionPane.showInputDialog("Increment: ");
        try {
          increment = Integer.valueOf(userInput);
        } catch (IllegalArgumentException e) {
          this.view.renderMessage("Increment entered is invalid.");
        }
        try {
          cmd = new Brighten(increment, imageName, renamedImage);
          cmd.execute(this.model);
        } catch (IllegalArgumentException e) {
          this.view.renderMessage("Error: " + e.getMessage());
        }
        break;
      case "mosaic":
        int seeds = 0;
        String userInput1 = JOptionPane.showInputDialog("Seeds: ");
        try {
          seeds = Integer.valueOf(userInput1);
        } catch (IllegalArgumentException e) {
          this.view.renderMessage("Seeds entered are invalid.");
        }
        try {
          cmd = new Mosaic(seeds, imageName, renamedImage);
          cmd.execute(this.model);
        } catch (IllegalArgumentException e) {
          this.view.renderMessage("Error: " + e.getMessage());
        }
        break;
      case "sepia":
        try {
          cmd = new Sepia(imageName, renamedImage);
          cmd.execute(this.model);
        } catch (IllegalArgumentException e) {
          this.view.renderMessage("Error: " + e.getMessage());
        }
        break;
      case "greyscale":
        try {
          cmd = new Greyscale(imageName, renamedImage);
          cmd.execute(this.model);
        } catch (IllegalArgumentException e) {
          this.view.renderMessage("Error: " + e.getMessage());
        }
        break;
      case "sharpen":
        try {
          cmd = new Sharpen(imageName, renamedImage);
          cmd.execute(this.model);
        } catch (IllegalArgumentException e) {
          this.view.renderMessage("Error: " + e.getMessage());
        }
        break;
      case "blur":
        try {
          cmd = new Blur(imageName, renamedImage);
          cmd.execute(this.model);
        } catch (IllegalArgumentException e) {
          this.view.renderMessage("Error: " + e.getMessage());
        }
        break;
      default: //error due to unrecognized instruction
        this.view.renderMessage("Undefined instruction.");
    }
    Image toSave = this.model.getImage(renamedImage);
    this.view.addImage(renamedImage, toSave);
  }
}
