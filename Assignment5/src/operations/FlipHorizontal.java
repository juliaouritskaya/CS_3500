
package operations;

import model.ProcessingModel;


/**
 * This class creates an image flipped horizontally.
 */

public class FlipHorizontal implements ImageOperationCommand {
  protected String imageName;
  protected String destImageName;

  public FlipHorizontal(String imageName, String destImageName) {
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
    image.flipHorizontal(imageName, destImageName);
  }
}

