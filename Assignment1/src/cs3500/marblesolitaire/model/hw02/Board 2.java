package cs3500.marblesolitaire.model.hw02;

public interface Board {
  /**
   * Generates the board and fills it with slots of type: marble, empty, or invalid.
   *
   * @param sRow the row of the empty slot.
   * @param sCol the column of the empty slot.
   */
  public void createBoard(int sRow, int sCol);

  /**
   * Checks if the row and column are in the bounds of the board.
   *
   * @param row    the row number of the position.
   * @param column the column of the position.
   * @return a boolean value of true if the row and column are in the bounds.
   */
  public boolean inBounds(int row, int column);

  /**
   * Checks if the inputs constitute a valid move:
   * the from and to positions are valid.
   * there is a marble at the specified from position.
   * the to position is empty.
   * the to and from positions are exactly two positions away (horizontally or vertically).
   * there is a marble in the slot between the to and from positions.
   *
   * @param fromRow the row number of the position to be moved from
   *                (starts at 0)
   * @param fromCol the column number of the position to be moved from
   *                (starts at 0)
   * @param toRow   the row number of the position to be moved to
   *                (starts at 0)
   * @param toCol   the column number of the position to be moved to
   *                (starts at 0)
   * @return a boolean value of true if all the conditions are met
   *         and the user can make a valid move.
   */
  public boolean canMove(int fromRow, int fromCol, int toRow, int toCol);

  /**
   * Move a single marble from a given position to another given position.
   * A move is valid only if the from and to positions are valid.
   *
   * @param fromRow the row number of the position to be moved from
   *                (starts at 0)
   * @param fromCol the column number of the position to be moved from
   *                (starts at 0)
   * @param toRow   the row number of the position to be moved to
   *                (starts at 0)
   * @param toCol   the column number of the position to be moved to
   *                (starts at 0)
   * @throws IllegalArgumentException if the move is not possible
   */
  public void move(int fromRow, int fromCol, int toRow, int toCol);
}
