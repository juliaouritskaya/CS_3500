package view;

/**
 * represents the view interface.
 */
public interface IView {
  /**
   * runs the commands from the user.
   */
  public void run();

  /**
   * reads the commands from a fileName.
   *
   * @param fileName represents a filepath.
   */
  public void readCommands(String fileName);


}
