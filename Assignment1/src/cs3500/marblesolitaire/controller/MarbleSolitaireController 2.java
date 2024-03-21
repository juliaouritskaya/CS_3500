package cs3500.marblesolitaire.controller;

/**
 * This interface represents a controller for the marble solitaire game.
 */
public interface MarbleSolitaireController {
  /**
   * Plays a new game of marble solitaire.
   *
   * @throws IllegalStateException only if the controller is unable to successfully
   *                               read input or transmit output
   */
  public void playGame() throws IllegalStateException;
}
