package operations;

import model.ProcessingModel;

/**
 * An interface for choosing which operation to execute on the image.
 */
public interface ImageOperationCommand {
  /**
   * Takes in an image object and executes this operation on it.
   * @param image the image object passed in
   */
  void execute(ProcessingModel image);
}
