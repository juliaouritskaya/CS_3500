
package operations;

import model.ProcessingModel;


/**
 * This class creates an image that is brighter or darker based on the inputted component.
 */

public class Brighten implements ImageOperationCommand {
  protected String imageName;
  protected String destImageName;
  protected int component;

  /**
   * Creates an instance of a bright object.
   * @param component the amount to brighten the image
   * @param imageName the name of the image
   * @param destImageName the name of the destination image
   */
  public Brighten(int component, String imageName, String destImageName) {
    this.component = component;
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
    image.brighten(this.component, this.imageName, this.destImageName);
  }
}

