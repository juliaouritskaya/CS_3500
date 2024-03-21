package operations;

import model.ProcessingModel;

/**
 * This class creates a sepia of an image.
 */
public class Sepia implements ImageOperationCommand {
  protected String imageName;
  protected String destImageName;

  public Sepia(String imageName, String destImageName) {
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
    image.sepia(imageName, destImageName);
  }
}
