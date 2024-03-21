package cs3500.marblesolitaire.model.hw04;

/**
 * This class represents a triangle solitaire model that extends the abstract solitaire model.
 */
public class TriangleSolitaireModel extends AbstractSolitaireModel {

  /**
   * A triangle solitaire model has an arm thickness and a corresponding empty slot.
   * The constructor takes no parameters, and creates a triangular board with
   * the empty slot at (0, 0).
   */
  public TriangleSolitaireModel() {
    this(5, 0, 0);
  }

  /**
   * A triangle solitaire model has an arm thickness and a corresponding empty slot.
   * The constructor takes a single parameter (dimensions) that creates a game with the
   * specified dimension (number of slots in the bottom-most row) and the empty slot at (0,0).
   *
   * @param dimensions the number of slots in the bottom-most row.
   * @throws IllegalArgumentException if the specified dimension is invalid (non-positive).
   */
  public TriangleSolitaireModel(int dimensions) {
    this(dimensions, 0, 0);
  }

  /**
   * A triangle solitaire model has an arm thickness and a corresponding empty slot.
   * The constructor takes two parameters (row,col) that creates a 5-row game with the
   * empty slot at the specified position.
   *
   * @param row the row of the empty slot.
   * @param col the column of the empty slot.
   * @throws IllegalArgumentException if the specified position is invalid.
   */
  public TriangleSolitaireModel(int row, int col) {
    this(5, row, col);
  }

  /**
   * A triangle solitaire model has an arm thickness and a corresponding empty slot.
   * The constructor takes three parameters (dimensions,row,col) that creates a game with
   * the specified dimension and an empty slot at the specified row and column.
   *
   * @param dimensions the number of slots in the bottom-most row.
   * @param row         the row of the empty slot.
   * @param col         the column of the empty slot.
   * @throws IllegalArgumentException if the specified dimension is invalid (non-positive)
   *                                  or the specified position is invalid.
   */
  public TriangleSolitaireModel(int dimensions, int row, int col) {
    if (dimensions <= 0) {
      throw new IllegalArgumentException("Specified dimension is invalid, must be non-positive.");
    } else {
      this.armThickness = dimensions;
    }

    if (row < 0 || col < 0 || row > dimensions - 1 || col > dimensions - 1 || col > row) {
      throw new IllegalArgumentException("Specified position (" + row
              + ", " + col + ") is invalid.");
    } else {
      this.sRow = row;
      this.sCol = col;
    }

    this.board = new TriangleBoard(this.armThickness, this.sRow, this.sCol);
    this.board.createBoard(this.sRow, this.sCol);

    this.score = dimensions * (dimensions + 1) / 2 - 1;
  }
}
