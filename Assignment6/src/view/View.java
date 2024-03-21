package view;

import java.util.Map;

import javax.swing.*;

import controller.Features;
import model.Image;

/**
 * The interface for our view class
 */
public interface View {
  /**
   * Provides the view with all the callbacks.
   * @param features the callbacks.
   */
  public void addFeatures(Features features);

  /**
   * Adds an image to the GUI view to be displayed.
   * @param imageName the name of the image to be displayed.
   * @param image the image to be displayed.
   */
  public void addImage(String imageName, Image image);

  /**
   * Updates the image displayed in the GUI view.
   * @param imageName the name of the image being displayed.
   * @param image the image being displayed.
   */
  public void updateImage(String imageName, Image image);

  /**
   * Returns a list of images that have been loaded into the program.
   *
   * @return a list of images as strings.
   */
  public DefaultListModel<String> getImages();

  /**
   * Render a specific message to the provided data destination.
   *
   * @param message the message to be transmitted.
   * @throws IllegalStateException if transmission of the board to the provided data
   *                               destination fails.
   */
  public void renderMessage(String message) throws IllegalStateException;
}
