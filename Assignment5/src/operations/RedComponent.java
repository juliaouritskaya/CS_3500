package operations;

import model.ProcessingModel;

/**
 * This class creates a grey scale of an image using the red component.
 */
public class RedComponent implements ImageOperationCommand {
  protected String imageName;
  protected String destImageName;

  public RedComponent(String imageName, String destImageName) {
    this.imageName = imageName;
    this.destImageName = destImageName;
  }

  /**
   * Takes in an image object and executes this operation on it.
   *
   * @param image the image object passed in
   */
  @Override
  public void execute(ProcessingModel image) {
    image.redComponent(imageName, destImageName);
  }
}
