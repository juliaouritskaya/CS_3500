package operations;

import model.ProcessingModel;

/**
 * This class creates a sharpened image.
 */
public class Sharpen implements ImageOperationCommand {
  protected String imageName;
  protected String destImageName;

  public Sharpen(String imageName, String destImageName) {
    this.imageName = imageName;
    this.destImageName = destImageName;
  }

  /**
   * Takes in an image object and executes the flip vertical operation on it.
   *
   * @param image the image object passed in
   */
  @Override
  public void execute(ProcessingModel image) {
    image.sharpen(imageName, destImageName);
  }
}
