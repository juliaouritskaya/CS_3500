package cs3500.marblesolitaire.model.hw02;

/**
 * This class implements operations offered by
 * marble solitaire model and marble solitaire model state for the Marble solitaire game.
 */
public class EnglishSolitaireModel implements MarbleSolitaireModel {
  private final int armThickness;
  private final int sRow;
  private final int sCol;
  private final Board board;
  private int score;

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

  /**
   * Move a single marble from a given position to another given position.
   * A move is valid only if the from and to positions are valid. Specific
   * implementations may place additional constraints on the validity of a move.
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
  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
    this.board.move(fromRow, fromCol, toRow, toCol);
    this.score = this.score - 1;
  }

  /**
   * Determine and return if the game is over or not. A game is over if no
   * more moves can be made.
   *
   * @return true if the game is over, false otherwise
   */
  @Override
  public boolean isGameOver() {
    for (int i = 0; i < this.getBoardSize(); i++) {
      for (int j = 0; j < this.getBoardSize(); j++) {
        if (score > 1 && this.board.slot[i][j].equals(SlotState.Marble)
                && (i < this.getBoardSize() - 1)
                && this.board.canMove(i, j, i + 2, j)
                || i > 1 && this.board.canMove(i, j, i - 2, j)
                || j < this.getBoardSize() - 1
                && this.board.canMove(i, j, i, j + 2)
                || j > 1
                && this.board.canMove(i, j, i, j - 2)) {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * Return the size of this board. The size is roughly the longest dimension of a board
   *
   * @return the size as an integer
   */
  @Override
  public int getBoardSize() {
    return this.board.boardSize;
  }

  /**
   * Get the state of the slot at a given position on the board.
   *
   * @param row the row of the position sought, starting at 0
   * @param col the column of the position sought, starting at 0
   * @return the state of the slot at the given row and column
   * @throws IllegalArgumentException if the row or the column are beyond
   *                                  the dimensions of the board
   */
  @Override
  public SlotState getSlotAt(int row, int col) throws IllegalArgumentException {
    if (row < 0 || row >= this.getBoardSize() || col < 0 || col >= this.getBoardSize()) {
      throw new IllegalArgumentException("Beyond the dimensions of the board.");
    } else {
      return this.board.slot[row][col];
    }
  }

  /**
   * Return the number of marbles currently on the board.
   *
   * @return the number of marbles currently on the board.
   */
  @Override
  public int getScore() {
    return this.score;
  }
}

