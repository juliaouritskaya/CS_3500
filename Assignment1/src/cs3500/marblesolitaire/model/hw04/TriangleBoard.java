package cs3500.marblesolitaire.model.hw04;

import cs3500.marblesolitaire.model.hw02.Board;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState.SlotState;

public class TriangleBoard extends Board {
  /**
   * A board has an arm thickness and a corresponding empty slot.
   *
   * @param armThickness the number of marbles in the top row
   *                     (or bottom row, or left or right columns).
   * @param sRow         the row of the empty slot.
   * @param sCol         the column of the empty slot.
   */
  public TriangleBoard(int armThickness, int sRow, int sCol) {
    super(armThickness, sRow, sCol);
    super.boardSize = armThickness;
    createBoard(sRow, sCol);
  }

  public void createBoard(int sRow, int sCol) {
    for (int i = 0; i < boardSize; i++) {
      for (int j = 0; j < boardSize; j++) {
        if (j <= i) {
          this.slot[i][j] = SlotState.Marble;
        } else {
          this.slot[i][j] = SlotState.Invalid;
        }
      }
    }
    this.slot[sRow][sCol] = SlotState.Empty;
  }

  /**
   * Checks if the row and column are in the bounds of the board.
   *
   * @param row    the row number of the position.
   * @param column the column of the position.
   * @return a boolean value of true if the row and column are in the bounds.
   */
  public boolean inBounds(int row, int column) {
    return (!(row < 0
            || column < 0
            || row > this.boardSize - 1
            || column > this.boardSize - 1
            || column > row));
  }

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
   * and the user can make a valid move.
   */
  public boolean canMove(int fromRow, int fromCol, int toRow, int toCol) {
    if (inBounds(fromRow, fromCol)
            && inBounds(toRow, toCol)) {
      if ((this.slot[fromRow][fromCol].equals(SlotState.Marble))
              && (this.slot[toRow][toCol].equals(SlotState.Empty))) {

        if (((toRow - fromRow) == 0 && Math.abs(toCol - fromCol) == 2)
                || (Math.abs(toRow - fromRow) == 2 && (Math.abs(toCol - fromCol) == 2))
                || (Math.abs(toRow - fromRow) == 2 && (toCol - fromCol) == 0)) {

          if (this.slot[(fromRow + toRow) / 2][(fromCol + toCol) / 2].equals(SlotState.Marble)) {
            return true;
          }
        }
      }
    }
    return false;
  }

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
  public void move(int fromRow, int fromCol, int toRow, int toCol) {
    if (canMove(fromRow, fromCol, toRow, toCol)) {
      this.slot[fromRow][fromCol] = SlotState.Empty;
      this.slot[toRow][toCol] = SlotState.Marble;

      if (toRow < fromRow && toCol == fromCol) {
        this.slot[fromRow - 1][fromCol] = SlotState.Empty;
      } else if (toRow > fromRow && toCol == fromCol) {
        this.slot[fromRow + 1][fromCol] = SlotState.Empty;
      }

      if (toCol < fromCol && toRow < fromRow) {
        this.slot[fromRow - 1][fromCol - 1] = SlotState.Empty;
      } else if (toCol < fromCol && toRow == fromRow) {
        this.slot[fromRow][fromCol - 1] = SlotState.Empty;
      } else if (toCol > fromCol && toRow > fromRow) {
        this.slot[fromRow + 1][fromCol + 1] = SlotState.Empty;
      } else if (toCol > fromCol && toRow == fromRow) {
        this.slot[fromRow][fromCol + 1] = SlotState.Empty;
      }

    } else {
      throw new IllegalArgumentException("Move is invalid.");
    }
  }

  /**
   * Determine and return if the game is over or not. A game is over if no
   * more moves can be made.
   *
   * @return true if the game is over, false otherwise
   */
  public boolean isGameOver() {
    for (int i = 0; i < this.boardSize; i++) {
      for (int j = 0; j < this.boardSize; j++) {
        if ((canMove(i, j, i + 2, j) == true) //go down positive slope diag
                || (canMove(i, j, i - 2, j) == true) //go up positive slope diag
                || (canMove(i, j, i, j + 2) == true) //go across row to right
                || (canMove(i, j, i, j - 2) == true) //go across row to left
                || (canMove(i, j, i + 2, j + 2) == true) //go down negative slope diag
                || (canMove(i, j, i - 2, j - 2) == true)){ //go up negative slope diag
          return false;
        }
      }
    }
    return true;
  }
}
