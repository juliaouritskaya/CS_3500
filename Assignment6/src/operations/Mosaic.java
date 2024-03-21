package operations;

import model.ProcessingModel;

public class Mosaic implements ImageOperationCommand {
  protected String imageName;
  protected String destImageName;
  protected int seeds;

  /**
   * Creates an instance of a bright object.
   * @param seeds the amount of inputted seeds
   * @param imageName the name of the image
   * @param destImageName the name of the destination image
   */
  public Mosaic(int seeds, String imageName, String destImageName) {
    this.seeds = seeds;
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
    image.brighten(this.seeds, this.imageName, this.destImageName);
  }
}
