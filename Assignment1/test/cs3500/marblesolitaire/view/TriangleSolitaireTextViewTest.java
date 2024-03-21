package cs3500.marblesolitaire.view;

import org.junit.Test;

import java.io.IOException;

import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;

import static org.junit.Assert.assertEquals;

public class TriangleSolitaireTextViewTest {
  @Test
  public void testRenderBoard() {
    StringBuilder appendable = new StringBuilder();
    MarbleSolitaireModel model = new TriangleSolitaireModel();
    MarbleSolitaireView view = new TriangleSolitaireTextView(model, appendable);

    try {
      view.renderBoard();
    } catch (IOException e) {
      throw new IllegalStateException(e);
    }

    String expected =
            "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O";

    assertEquals(expected, appendable.toString());
  }
}