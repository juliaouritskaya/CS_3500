package cs3500.marblesolitaire.view;

import org.junit.Test;
import java.io.IOException;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState.SlotState;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class TriangleSolitaireTextViewTest {
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorViewException() {
    try {
      MarbleSolitaireModelState model = null;
      MarbleSolitaireView view = new TriangleSolitaireTextView(model);
    } catch (IllegalArgumentException i) {
      String output = "Model cannot be null";
      assertEquals(output, i.getMessage());
      throw i;
    }
    fail();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorView2Exception() {
    try {
      MarbleSolitaireModelState model = null;
      Appendable appendable = new StringBuilder();
      MarbleSolitaireView view = new TriangleSolitaireTextView(model, appendable);
    } catch (IllegalArgumentException i) {
      String output = "Model and appendable cannot be null.";
      assertEquals(output, i.getMessage());
      throw i;
    }
    fail();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorView2Exception2() {
    try {
      MarbleSolitaireModelState model = new TriangleSolitaireModel();
      Appendable appendable = null;
      MarbleSolitaireView view = new TriangleSolitaireTextView(model, appendable);
    } catch (IllegalArgumentException i) {
      String output = "Model and appendable cannot be null.";
      assertEquals(output, i.getMessage());
      throw i;
    }
    fail();
  }

  @Test
  public void testSlotStateToString() {
    MarbleSolitaireModelState model = new TriangleSolitaireModel();
    MarbleSolitaireView view = new TriangleSolitaireTextView(model);
    Utils utils = new Utils();

    assertEquals("O", utils.slotStateToString(SlotState.Marble));
    assertEquals("_", utils.slotStateToString(SlotState.Empty));
    assertEquals(" ", utils.slotStateToString(SlotState.Invalid));
  }

  @Test
  public void testToString() {
    MarbleSolitaireModelState model = new TriangleSolitaireModel();

    MarbleSolitaireView view = new TriangleSolitaireTextView(model);

    String expected = "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O";

    assertEquals(expected, view.toString());
  }

  @Test
  public void testToStringAfterOneValidMove() {
    MarbleSolitaireModel model = new TriangleSolitaireModel();

    MarbleSolitaireView view = new TriangleSolitaireTextView(model);

    String expected = "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O";

    assertEquals(expected, view.toString());

    model.move(2, 0, 0, 0);

    String expectedAfterOneMove = "    O\n" +
            "   _ O\n" +
            "  _ O O\n" +
            " O O O O\n" +
            "O O O O O";

    assertEquals(expectedAfterOneMove, view.toString());
  }

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

  @Test
  public void testRenderBoardException() throws IOException {
    Appendable appendable = new StringBuilder();
    MarbleSolitaireModel model = new TriangleSolitaireModel();
    MarbleSolitaireView view = new TriangleSolitaireTextView(model, appendable);

    try {
      view.renderBoard();
    } catch (IOException e) {
      if (!e.getMessage().equals("Failed to render the game board")) {
        fail("Incorrect message");
      }
    }
  }

  @Test
  public void testRenderMessage() throws IOException {
    StringBuilder appendable = new StringBuilder();
    MarbleSolitaireModel model = new TriangleSolitaireModel();
    MarbleSolitaireView view = new TriangleSolitaireTextView(model, appendable);

    view.renderMessage("State of game when quit:");
    assertEquals("State of game when quit:", appendable.toString());
  }

  @Test
  public void testRenderMessageException() throws IOException {
    StringBuilder appendable = new StringBuilder();
    MarbleSolitaireModel model = new TriangleSolitaireModel();
    MarbleSolitaireView view = new TriangleSolitaireTextView(model, appendable);

    try {
      view.renderMessage("Game quit!");
      view.renderMessage("State of game when quit:");
    } catch (IOException e) {
      if (!e.getMessage().equals("Failed to render the game quit message.")) {
        fail("Incorrect message");
      }
    }
  }
}