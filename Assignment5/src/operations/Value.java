
package operations;

import model.ProcessingModel;


/**
 * This class creates a grey scale of an image using the value component.
 */

public class Value implements ImageOperationCommand {
  protected String imageName;
  protected String destImageName;

  public Value(String imageName, String destImageName) {
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
    image.value(imageName, destImageName);
  }
}

