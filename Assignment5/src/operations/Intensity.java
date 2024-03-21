
package operations;

import model.ProcessingModel;


/**
 * This class creates a grey scale of an image using the intensity component.
 */

public class Intensity implements ImageOperationCommand {
  protected String imageName;
  protected String destImageName;

  public Intensity(String imageName, String destImageName) {
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
    image.intensity(imageName, destImageName);
  }
}

