package controller;

import view.View;

/**
 * This interface represents a features interface, whose methods are the
 * various high-level features and abilities of our view.
 */
public interface Features {
  /**
   * Make the view visible. This is usually called
   * after the view is constructed
   * @param view the view to make visible.
   */
  void makeVisible(View view);


  /**
   * Loads the specified image into the GUI view when the "load" button is clicked.
   */
  public void loadImage();

  /**
   * Saves the specified image from the GUI view when the "save" button is clicked.
   * @param imageName the name of the image to save.
   */
  public void saveImage(String imageName);

  /**
   * Displays the specified image in the GUI view when selected from the list.
   * @param imageName the name of the image to be displayed.
   */
  public void displayImage(String imageName);

  /**
   * Applies the specified filter on the specified image.
   * @param imageName the name of the image to be filtered.
   * @param filter the name of the filter to apply on the image.
   */
  public void applyFilter(String imageName, String filter);
}
