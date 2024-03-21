package cs3500.marblesolitaire.model.hw04;

import cs3500.marblesolitaire.model.hw02.Board;

/**
 * This class represents a european board. The corners between the arms of the cross are filled in
 * to produce an octagon shape. The initial hole will again be in the center.
 */
public class EuropeanBoard extends Board {
  /**
   * A european board has an arm thickness and a corresponding empty slot.
   *
   * @param armThickness the number of marbles in the top row
   *                     (or bottom row, or left or right columns).
   * @param sRow         the row of the empty slot.
   * @param sCol         the column of the empty slot.
   */
  public EuropeanBoard(int armThickness, int sRow, int sCol) {
    super(armThickness, sRow, sCol);
  }

  /**
   * Checks if the row and column are in the bounds of the board.
   *
   * @param row    the row number of the position.
   * @param column the column of the position.
   * @return a boolean value of true if the row and column are in the bounds.
   */
  public boolean inBounds(int row, int column) {
    int length = (this.armThickness * 2) + (this.armThickness - 2);

    return ((!((row < this.armThickness - 1 && column < (length / 2)
            - ((this.armThickness + (2 * row)) / 2))

            || (row < this.armThickness - 1 && column > (length / 2)
            + ((this.armThickness + (2 * row)) / 2))

            || (row > (length / 2) + ((this.armThickness + (2 * column)) / 2)
            && column < this.armThickness - 1) // bottom left

            || (row > this.armThickness * 2 - 2
            && column > (length / 2)
            + ((this.armThickness + ((length - row - 1) * 2)) / 2)))) //bottom right

            && !(row < 0
            || row >= length
            || column >= length
            || column < 0));
  }
}