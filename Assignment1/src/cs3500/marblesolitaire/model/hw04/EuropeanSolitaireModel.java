package cs3500.marblesolitaire.model.hw04;

import cs3500.marblesolitaire.model.hw02.Board;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

/**
 * This class represents a european solitaire model that implements marble solitaire model.
 */
public class EuropeanSolitaireModel extends AbstractSolitaireModel {

  /**
   * A european solitaire model has an arm thickness and a corresponding empty slot.
   * The constructor takes no parameters, and creates an octagonal board whose sides
   * have length 3, with the empty slot in the center of the board.
   */
  public EuropeanSolitaireModel() {
    this(3, 3, 3);
  }

  /**
   * A european solitaire model has an arm thickness and a corresponding empty slot.
   * The constructor takes the side length as its only parameter and creates a game with
   * the specified side length, and the empty slot in the center of the board.
   *
   * @param sideLength the number of marbles in the top row
   *                     (or bottom row, or left or right columns).
   * @throws IllegalArgumentException if the arm thickness is not a positive odd number.
   */
  public EuropeanSolitaireModel(int sideLength) {
    this(sideLength,
            (int) Math.floor((sideLength * 2 + sideLength - 2) / 2),
            (int) Math.floor(sideLength * 2 + sideLength - 2) / 2);
  }

  /**
   * A european solitaire model has an arm thickness and a corresponding empty slot.
   * The constructor takes two parameters – row and col – to specify the initial position
   * of the empty slot, in a board of default size 3.
   *
   * @param row the row of the empty slot.
   * @param col the column of the empty slot.
   * @throws IllegalArgumentException if the empty cell position is invalid,
   *                                  with a message "Invalid empty cell position (r,c)" with
   *                                  r and c replaced with the row and column passed to it.
   */
  public EuropeanSolitaireModel(int row, int col) {
    this(3, row, col);
  }

  /**
   * A european solitaire model has an arm thickness and a corresponding empty slot.
   * The constructor takes the side length, row and col to specify the size of the board
   * and the initial position of the empty slot.
   *
   * @param sideLength the number of marbles in the top row
   *                     (or bottom row, or left or right columns).
   * @param row         the row of the empty slot.
   * @param col         the column of the empty slot.
   * @throws IllegalArgumentException if the arm thickness is not a positive odd number,
   *                                  or the empty cell position is invalid.
   */
  public EuropeanSolitaireModel(int sideLength, int row, int col) {
    if (sideLength < 0 || sideLength % 2 == 0) {
      throw new IllegalArgumentException("Arm thickness must be a positive, odd number.");
    } else {
      this.armThickness = sideLength;
    }

    int length = (this.armThickness * 2) + (this.armThickness - 2);

    if  ((row < sideLength - 1  && col < (length / 2)
            - ((sideLength + (2 * row)) / 2))

            || (row < sideLength - 1 && col > ((length / 2)
            + ((sideLength + (2 * row)) / 2)))

            || (row > (length / 2) + ((sideLength + (2 * col)) / 2)
            && col < sideLength - 1) // bottom left

            || (row > sideLength * 2 - 2
            && col > (length / 2)
            + ((sideLength + ((length - row - 1) * 2))  / 2)) //bottom right

            || (row < 0
            || row > length - 1
            || col > length - 1
            || col < 0)) {
      throw new IllegalArgumentException("Invalid empty cell position (" + row + "," + col + ")");
    } else {
      this.sRow = row;
      this.sCol = col;
    }

    this.board = new EuropeanBoard(this.armThickness, this.sRow, this.sCol);
    this.board.createBoard(this.sRow, this.sCol);

    this.score = (int) (7 * Math.pow(sideLength, 2) - 10 * sideLength + 3);
  }
}
