package cs3500.marblesolitaire.controller;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

/**
 * This class implements the marble solitaire controller interface.
 */
public class MarbleSolitaireControllerImpl implements MarbleSolitaireController {
  private final Readable myIn;
  private MarbleSolitaireModel model;
  private MarbleSolitaireView view;

  /**
   * The constructor takes one argument of MarbleSolitaireModel type, MarbleSolitaireView type,
   * and Readable type. The constructor creates an instance of MarbleSolitaireControllerImpl.
   *
   * @param model the type of model object provided to the controller.
   * @param view  the type of view object provided to the controller.
   * @param myIn  the type of readable object provided to the controller.
   * @throws IllegalArgumentException if the provided parameters are null.
   */
  public MarbleSolitaireControllerImpl(MarbleSolitaireModel model, MarbleSolitaireView view,
                                       Readable myIn) throws IllegalArgumentException {
    if (model == null || view == null || myIn == null) {
      throw new IllegalArgumentException("Parameters cannot be null.");
    }
    this.model = model;
    this.view = view;
    this.myIn = myIn;
  }

  /**
   * Plays a new game of marble solitaire.
   *
   * @throws IllegalStateException only if the controller is unable to successfully
   *                               read input or transmit output
   */
  @Override
  public void playGame() throws IllegalStateException {
    Scanner scanner = new Scanner(this.myIn);
    int[] values = new int[4];
    int count = 0;
    String input;


    while (!this.model.isGameOver()) {
      while (count < 4) {
        try {
          input = scanner.next();
        } catch (NoSuchElementException e) {
          throw new IllegalStateException("Readable has no more inputs.");
        }

        try {
          if (input.equalsIgnoreCase("q")) {

            this.displayGameQuit();
            return;

          } else if (Integer.parseInt(input) > 0) {
            values[count] = Integer.parseInt(input) - 1;
            count++;
          } else {
            this.displayInvalidInput();
          }
        } catch (NumberFormatException e) {
          this.displayInvalidInput();
        }
      }

      try {
        try {
          this.model.move(values[0], values[1], values[2], values[3]);
          this.view.renderMessage("\n");
        } catch (IOException e) {
          throw new IllegalStateException("Failed to render the output.");
        }
      } catch (IllegalArgumentException e) {
        try {
          this.view.renderMessage("Invalid move. Play again. " + e.getMessage());
        } catch (IOException e2) {
          throw new IllegalStateException("Failed to render the invalid move message.");
        }
      }

      this.displayBoard();
      count = 0;
    }

    if (this.model.isGameOver()) {
      this.displayGameOver();
    }
  }

  /**
   * Tries to render the board and score.
   *
   * @throws IllegalStateException if failed to render the game board.
   */
  private void displayBoard() {
    try {
      this.view.renderBoard();
      this.view.renderMessage("\nScore: " + this.model.getScore() + "\n");
    } catch (IOException e) {
      throw new IllegalStateException("Failed to render the game board");
    }
  }

  /**
   * Tries to render the game quit message.
   *
   * @throws IllegalStateException if failed to render the game quit message.
   */
  private void displayGameQuit() {
    try {
      this.view.renderMessage("Game quit!\n");
      this.view.renderMessage("State of game when quit:\n");
      this.view.renderBoard();
      this.view.renderMessage("\nScore: " + this.model.getScore());
    } catch (IOException e) {
      throw new IllegalStateException("Failed to render the game quit message.");
    }
  }

  /**
   * Tries to render the game over message.
   *
   * @throws IllegalStateException if failed to render the game over message.
   */
  private void displayGameOver() {
    try {
      this.view.renderMessage("Game over!\n");
      this.view.renderBoard();
      this.view.renderMessage("\nScore: " + this.model.getScore());
    } catch (IOException e) {
      throw new IllegalStateException("Failed to render the game over message.");
    }
  }

  /**
   * Tries to render the invalid input message.
   *
   * @throws IllegalStateException if failed to render the invalid inpuit message.
   */
  private void displayInvalidInput() {
    try {
      this.view.renderMessage("Invalid input, try again.\n");
    } catch (IOException e2) {
      throw new IllegalStateException("Failed to render the invalid input message.");
    }
  }
}
