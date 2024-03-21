package cs3500.marblesolitaire.model.hw04;

import cs3500.marblesolitaire.model.hw02.Board;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

public class TriangleSolitaireModel extends AbstractSolitaireModel {

  public TriangleSolitaireModel() {
    this(5, 0, 0);
    //this.score = ((armThickness + ((armThickness) * (armThickness))) / 2);
  }

  public TriangleSolitaireModel(int dimensions) {
    this(dimensions, 0, 0);
  }

  public TriangleSolitaireModel(int row, int col) {
    this(5, row, col);
  }

  public TriangleSolitaireModel(int dimensions, int row, int col) {
    if (dimensions <= 0) {
      throw new IllegalArgumentException("Specified dimension is invalid, must be non-positive.");
    } else {
      this.armThickness = dimensions;
    }

    if (row < 0 || col < 0 || row > dimensions - 1 || col > dimensions - 1 || col > row) {
      throw new IllegalArgumentException("Specified position (" + row + ", " + col + ") is invalid.");
    } else {
      this.sRow = row;
      this.sCol = col;
    }

    this.board = new TriangleBoard(this.armThickness, this.sRow, this.sCol);
    this.board.createBoard(this.sRow, this.sCol);

    this.score = dimensions * (dimensions + 1) / 2 - 1;
  }
}
