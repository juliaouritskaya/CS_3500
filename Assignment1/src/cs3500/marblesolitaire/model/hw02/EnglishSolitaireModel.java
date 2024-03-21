package cs3500.marblesolitaire.model.hw02;

import cs3500.marblesolitaire.model.hw04.AbstractSolitaireModel;

/**
 * This class implements operations offered by
 * marble solitaire model and marble solitaire model state for the Marble solitaire game.
 */
public class EnglishSolitaireModel extends AbstractSolitaireModel {

  /**
   * An english solitaire model has an arm thickness and a corresponding empty slot.
   * The constructor takes no parameters, and initialize the game board with
   * an arm thickness of 3 with the empty slot at the center.
   */
  public EnglishSolitaireModel() {
    this(3, 3, 3);
  }

  /**
   * A board has an arm thickness and a corresponding empty slot.
   * The constructor takes two parameters: sRow and sCol.
   * It initializes the game board so that the arm thickness is 3
   * and the empty slot is at the position (sRow, sCol).
   *
   * @param sRow the row of the empty slot.
   * @param sCol the column of the empty slot.
   * @throws IllegalArgumentException if the empty cell position is invalid,
   *                                  with a message "Invalid empty cell position (r,c)" with
   *                                  r and c replaced with the row and column passed to it.
   */
  public EnglishSolitaireModel(int sRow, int sCol) {
    this(3, sRow, sCol);
  }

  /**
   * A board has an arm thickness and a corresponding empty slot.
   * The constructor takes the arm thickness as its only parameter
   * and initialize a game board with the empty slot at the center.
   *
   * @param armThickness the number of marbles in the top row
   *                     (or bottom row, or left or right columns).
   * @throws IllegalArgumentException if the arm thickness is not a positive odd number.
   */
  public EnglishSolitaireModel(int armThickness) {
    this(armThickness,
            (int) Math.floor((armThickness * 2 + armThickness - 2) / 2),
            (int) Math.floor(armThickness * 2 + armThickness - 2) / 2);
  }

  /**
   * A board has an arm thickness and a corresponding empty slot.
   * The constructor takes the arm thickness, row and column of the empty slot in that order.
   *
   * @param armThickness the number of marbles in the top row
   *                     (or bottom row, or left or right columns).
   * @param sRow         the row of the empty slot.
   * @param sCol         the column of the empty slot.
   * @throws IllegalArgumentException if the arm thickness is not a positive odd number,
   *                                  or the empty cell position is invalid.
   */
  public EnglishSolitaireModel(int armThickness, int sRow, int sCol) {
    if (armThickness < 0 || armThickness % 2 == 0) {
      throw new IllegalArgumentException("Arm thickness must be a positive, odd number.");
    } else {
      this.armThickness = armThickness;
    }

    if (((sRow < this.armThickness - 1)
            && (sCol < this.armThickness - 1
            || sCol > this.armThickness * 2 - 2))
            || ((sRow > this.armThickness * 2 - 2)
            && (sCol < this.armThickness - 1
            || sCol > this.armThickness * 2 - 2))) {
      throw new IllegalArgumentException("Invalid empty cell position (" + sRow + "," + sCol + ")");
    } else {
      this.sRow = sRow;
      this.sCol = sCol;
    }

    this.board = new Board(this.armThickness, this.sRow, this.sCol);
    this.board.createBoard(this.sRow, this.sCol);

    this.score = (5 * (this.armThickness * this.armThickness)) - (4 * this.armThickness) - 1;
  }
}

