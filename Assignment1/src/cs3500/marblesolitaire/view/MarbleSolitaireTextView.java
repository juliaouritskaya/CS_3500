package cs3500.marblesolitaire.view;

import java.io.IOException;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState.SlotState;

import static cs3500.marblesolitaire.view.Utils.slotStateToString;

/**
 * This class implements operations offered by
 * marble solitaire view for the Marble solitaire game.
 */
public class MarbleSolitaireTextView implements MarbleSolitaireView {

  private final MarbleSolitaireModelState model;
  private final Appendable appendable;


  /**
   * The constructor takes exactly one argument of MarbleSolitaireModelState type.
   * This constructor can be called with the model object that provides this view all the methods
   * it needs to query the model and print the board (but not make moves).
   *
   * @param model the type of model object provided to the view.
   * @throws IllegalArgumentException if the provided model is null.
   */
  public MarbleSolitaireTextView(MarbleSolitaireModelState model) throws IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null");
    }
    this.model = model;
    this.appendable = System.out;
  }

  /**
   * The constructor takes one argument of MarbleSolitaireModelState type and
   * one argument of Appendable type. This constructor can be called with the model object that
   * provides this view all the methods it needs to query the model and print the board
   * (but not make moves). The view uses the appendable object as its destination.
   *
   * @param model the type of model object provided to the view.
   * @param appendable object that this view uses as its destination.
   * @throws IllegalArgumentException if the provided model is null.
   */
  public MarbleSolitaireTextView(MarbleSolitaireModelState model,
                                 Appendable appendable) throws IllegalArgumentException {
    if (model == null || appendable == null) {
      throw new IllegalArgumentException("Model and appendable cannot be null.");
    }
    this.model = model;
    this.appendable = appendable;
  }

  /**
   * Return a string that represents the current state of the board. The
   * string should have one line per row of the game board. Each slot on the
   * game board is a single character (O, _ or space for a marble, empty and
   * invalid position respectively). Slots in a row should be separated by a
   * space. Each row has no space before the first slot and after the last slot.
   *
   * @return the game state as a string
   */
  public String toString() {
    Appendable ap = new StringBuilder();

    for (int i = 0; i < this.model.getBoardSize(); i++) {
      boolean flag = false;
      for (int j = 0; j < this.model.getBoardSize(); j++) {
        Utils.write(ap, slotStateToString(this.model.getSlotAt(i, j)));
        if (!flag && !this.model.getSlotAt(i, j).equals(SlotState.Invalid)) {
          flag = true;
        }
        if (flag && j < this.model.getBoardSize() - 1
                && this.model.getSlotAt(i, j + 1).equals(SlotState.Invalid)
                || j == this.model.getBoardSize() - 1) {
          break;
        }
        Utils.write(ap, " ");
      }
      if (i < this.model.getBoardSize() - 1) {
        Utils.write(ap, "\n");
      }
    }
    return ap.toString();
  }

  /**
   * Render the board to the provided data destination. The board should be rendered exactly
   * in the format produced by the toString method above
   *
   * @throws IOException if transmission of the board to the provided data destination fails
   */
  @Override
  public void renderBoard() throws IOException {
    Utils.write(this.appendable, this.toString());
  }

  /**
   * Render a specific message to the provided data destination.
   *
   * @param message the message to be transmitted
   * @throws IOException if transmission of the board to the provided data destination fails
   */
  @Override
  public void renderMessage(String message) throws IOException {
    Utils.write(this.appendable, message);
  }
}
