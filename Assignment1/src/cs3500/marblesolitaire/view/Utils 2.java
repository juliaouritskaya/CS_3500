package cs3500.marblesolitaire.view;

import java.io.IOException;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * This class represents a utils class for the marble solitaire text view class.
 */
public class Utils {
  /**
   * Returns a string from the type of slot state that is given.
   *
   * @param slot the slot state: marble, empty, or invalid.
   * @throws IllegalArgumentException if the provided slot state is invalid.
   */
  public static String slotStateToString(MarbleSolitaireModelState.SlotState slot) {
    String toReturn = "";

    switch (slot) {
      case Marble:
        toReturn = "O";
        break;
      case Empty:
        toReturn = "_";
        break;
      case Invalid:
        toReturn = " ";
        break;
      default:
        throw new IllegalArgumentException("Invalid slot type.");
    }
    return toReturn;
  }

  /**
   * Writes the specified string message and appends it.
   *
   * @param appendable appends a string to a given sequence.
   * @param message    the string inputted.
   * @throws IllegalArgumentException if the something went wrong while
   *                                  constructing the string output.
   */
  public static void write(Appendable appendable, String message) throws IllegalStateException {
    try {
      appendable.append(message);
    } catch (IOException e) {
      throw new IllegalStateException("Something went wrong while "
              + "constructing the string output.");
    }
  }
}
