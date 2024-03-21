package cs3500.marblesolitaire.controller;

import org.junit.Assert;
import org.junit.Test;
import java.io.StringReader;

import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * This class represents tests for the marble solitaire controller impl class.
 */
public class MarbleSolitaireControllerImplTest {
  @Test
  public void testConstructor() {
    Appendable appendable = new StringBuilder();
    Readable readable = new StringReader("2 4 4 4 q");
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireView view = new MarbleSolitaireTextView(model, appendable);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view, readable);

    controller.playGame();

    String expectedAfterMove = "\n    O O O\n"
            + "    O _ O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 31";

    String expectedQuit = "\n"
            + "Game quit!\n"
            + "State of game when quit:"
            + expectedAfterMove;

    assertEquals(expectedAfterMove + expectedQuit, appendable.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorInvalidNullView() {
    try {
      StringBuilder appendable = new StringBuilder();
      Readable readable = new StringReader("2 4 4 4 q");
      MarbleSolitaireModel model = new EnglishSolitaireModel();
      MarbleSolitaireView view = null;
      MarbleSolitaireController controller =
              new MarbleSolitaireControllerImpl(model, view, readable);
    } catch (IllegalArgumentException i) {
      String output = "Parameters cannot be null.";
      Assert.assertEquals(output, i.getMessage());
      throw i;
    }
    fail();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorInvalidNullModel() {
    try {
      StringBuilder appendable = new StringBuilder();
      Readable readable = new StringReader("2 4 4 4 q");
      MarbleSolitaireModel model = null;
      MarbleSolitaireView view = new MarbleSolitaireTextView(model, appendable);
      MarbleSolitaireController controller =
              new MarbleSolitaireControllerImpl(model, view, readable);
    } catch (IllegalArgumentException i) {
      String output = "Model and appendable cannot be null.";
      Assert.assertEquals(output, i.getMessage());
      throw i;
    }
    fail();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorInvalidNullReadable() {
    try {
      StringBuilder appendable = new StringBuilder();
      Readable readable = null;
      MarbleSolitaireModel model = new EnglishSolitaireModel();
      MarbleSolitaireView view = new MarbleSolitaireTextView(model, appendable);
      MarbleSolitaireController controller =
              new MarbleSolitaireControllerImpl(model, view, readable);
    } catch (IllegalArgumentException i) {
      String output = "Parameters cannot be null.";
      Assert.assertEquals(output, i.getMessage());
      throw i;
    }
    fail();
  }

  @Test
  public void testPlayGame() {
    System.out.println("***playGame with exhausted integer input");
    MarbleSolitaireModel model = new EnglishSolitaireModel(3, 3, 3);
    MarbleSolitaireView view = new MarbleSolitaireTextView(model);

    Readable myIn = new StringReader("4 6 4 4\n"
            + "4 3 4 5\n"
            + "6 4 4 a "
            + "a a a a a a a a a a "
            + "a a a a a a a a a a ");
    MarbleSolitaireControllerImpl instance = new MarbleSolitaireControllerImpl(model, view, myIn);
    int oldscore = model.getScore();
    try {
      instance.playGame();
      int newscore = model.getScore();
      assertEquals(oldscore - 2, newscore);
    } catch (IllegalStateException e) {
      System.out.println("\n*** Readable has no more inputs.");
      String msg = e.getMessage();
      assertEquals("Readable has no more inputs.", msg);
    }
  }

  @Test
  public void testPlayGame2() {
    System.out.println("***playGame2 with exhausted input");
    MarbleSolitaireModel model = new EnglishSolitaireModel(3, 3, 3);
    MarbleSolitaireView view = new MarbleSolitaireTextView(model);

    Readable myIn = new StringReader("4 6 4 4\n"
            + "4 3 4 5\n"
            + "6 4 4 4");
    MarbleSolitaireControllerImpl instance = new MarbleSolitaireControllerImpl(model, view, myIn);
    int oldscore = model.getScore();
    try {
      instance.playGame();
      int newscore = model.getScore();
      assertEquals(oldscore - 3, newscore);
    } catch (IllegalStateException e) {
      System.out.println("\n*** Readable has no more inputs.");
      String msg = e.getMessage();
      assertEquals("Readable has no more inputs.", msg);
    }
  }

  @Test
  public void testPlayGameNegativeInput() {
    System.out.println("***playGame2 with negative input");
    MarbleSolitaireModel model = new EnglishSolitaireModel(3, 3, 3);
    MarbleSolitaireView view = new MarbleSolitaireTextView(model);

    Readable myIn = new StringReader("-1 6 4 4 q");
    MarbleSolitaireControllerImpl instance = new MarbleSolitaireControllerImpl(model, view, myIn);
    int oldscore = model.getScore();
    try {
      instance.playGame();
      int newscore = model.getScore();
      assertEquals(oldscore, newscore);
    } catch (IllegalStateException e) {
      System.out.println("\n*** Negative input, try again.\n");
      String msg = e.getMessage();
      assertEquals("Negative input, try again.\n", msg);
    }
  }

  @Test
  public void testPlayGameNegativeInputException() {
    MarbleSolitaireModel model = new EnglishSolitaireModel(3, 3, 3);
    MarbleSolitaireView view = new MarbleSolitaireTextView(model);
    Readable myIn = new StringReader("-1 6 4 4 q");
    MarbleSolitaireControllerImpl instance = new MarbleSolitaireControllerImpl(model, view, myIn);

    try {
      instance.playGame();
    } catch (Exception e) {
      if (!e.getMessage().equals("Failed to render the negative input message.")) {
        fail("Incorrect message");
      }
    }
  }

  @Test
  public void testPlayGameMove() {
    MarbleSolitaireModel model = new EnglishSolitaireModel(3, 3, 3);
    MarbleSolitaireView view = new MarbleSolitaireTextView(model);

    Readable myIn = new StringReader("3 3 4 4 q");
    MarbleSolitaireControllerImpl instance = new MarbleSolitaireControllerImpl(model, view, myIn);
    int oldscore = model.getScore();
    try {
      instance.playGame();
      int newscore = model.getScore();
      assertEquals(oldscore, newscore);
    } catch (IllegalStateException e) {
      System.out.println("\n*** Failed to render the output.\n");
      String msg = e.getMessage();
      assertEquals("Failed to render the output.\n", msg);
    }
  }

  @Test
  public void testPlayGameInvalidMove() {
    Appendable appendable = new StringBuilder();
    Readable readable = new StringReader("3 3 4 4 q");
    MarbleSolitaireModel model = new EnglishSolitaireModel(3, 3, 3);
    MarbleSolitaireView view = new MarbleSolitaireTextView(model, appendable);
    MarbleSolitaireControllerImpl instance =
            new MarbleSolitaireControllerImpl(model, view, readable);

    instance.playGame();

    String expectedAfterMove = "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32";

    String expectedInvalid = "Invalid move. Play again. Move is invalid.";

    String expectedQuit = "\n"
            + "Game quit!\n"
            + "State of game when quit:\n"
            + expectedAfterMove;

    assertEquals(expectedInvalid + expectedAfterMove + expectedQuit,
            appendable.toString());
  }

  @Test
  public void testPlayGameInvalidException() {
    MarbleSolitaireModel model = new EnglishSolitaireModel(3, 3, 3);
    MarbleSolitaireView view = new MarbleSolitaireTextView(model);

    Readable myIn = new StringReader("3 3 4 4 q");
    MarbleSolitaireControllerImpl instance = new MarbleSolitaireControllerImpl(model, view, myIn);
    int oldscore = model.getScore();
    try {
      instance.playGame();
      int newscore = model.getScore();
      assertEquals(oldscore, newscore);
    } catch (IllegalStateException e) {
      System.out.println("\n*** \"Failed to render the invalid move message.\"\n");
      String msg = e.getMessage();
      assertEquals("\"Failed to render the invalid move message.\"\n", msg);
    }
  }

  @Test
  public void testPlayGameGameQuitException() {
    MarbleSolitaireModel model = new EnglishSolitaireModel(3, 3, 3);
    MarbleSolitaireView view = new MarbleSolitaireTextView(model);

    Readable myIn = new StringReader("3 3 q 4");
    MarbleSolitaireControllerImpl instance = new MarbleSolitaireControllerImpl(model, view, myIn);
    int oldscore = model.getScore();
    try {
      instance.playGame();
      int newscore = model.getScore();
      assertEquals(oldscore, newscore);
    } catch (IllegalStateException e) {
      System.out.println("\n*** \"Failed to render the invalid move message.\"\n");
      String msg = e.getMessage();
      assertEquals("\"Failed to render the invalid move message.\"\n", msg);
    }
  }

  @Test
  public void testPlayGameGameOverException() {
    MarbleSolitaireModel model = new EnglishSolitaireModel(3, 3, 3);
    MarbleSolitaireView view = new MarbleSolitaireTextView(model);

    Readable readable = new StringReader("6 4 4 4\n"
            + "5 6 5 4\n"
            + "7 5 5 5\n"
            + "7 3 7 5\n"
            + "4 5 6 5\n"
            + "7 5 5 5\n"
            + "2 5 4 5\n"
            + "3 7 3 5\n"
            + "5 7 3 7\n"
            + "3 4 3 6\n"
            + "3 7 3 5\n"
            + "3 2 3 4\n"
            + "1 3 3 3\n"
            + "1 5 1 3\n"
            + "4 3 2 3\n"
            + "1 3 3 3\n"
            + "6 3 4 3\n"
            + "5 1 5 3\n"
            + "3 1 5 1\n"
            + "5 4 5 2\n"
            + "5 1 5 3\n"
            + "3 4 3 2\n"
            + "3 2 5 2\n"
            + "5 2 5 4\n"
            + "5 4 5 6\n"
            + "5 6 3 6\n"
            + "3 6 3 4\n"
            + "4 4 4 2\n"
            + "2 4 4 4\n"
            + "4 5 4 3\n"
            + "4 2 4 4 q\n");
    MarbleSolitaireControllerImpl instance =
            new MarbleSolitaireControllerImpl(model, view, readable);
    int oldscore = model.getScore();
    try {
      instance.playGame();
      int newscore = model.getScore();
      assertEquals(oldscore - 31, newscore);
    } catch (IllegalStateException e) {
      System.out.println("\n*** \"Failed to render the game over message.\"\"\n");
      String msg = e.getMessage();
      assertEquals("\"Failed to render the game over message.\"\"\n", msg);
    }
  }

  @Test
  public void testPlayGameTooManyInputs() {
    Appendable appendable = new StringBuilder();
    Readable readable = new StringReader("a 2 b c 4 4 4 q");
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireView view = new MarbleSolitaireTextView(model, appendable);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view, readable);

    controller.playGame();

    String expectedInvalid = "Invalid input, try again.\n"
            + "Invalid input, try again.\n"
            + "Invalid input, try again.\n";

    String expectedAfterMove = "\n    O O O\n"
            + "    O _ O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 31";

    String expectedQuit = "\n"
            + "Game quit!\n"
            + "State of game when quit:"
            + expectedAfterMove;

    assertEquals(expectedInvalid + expectedAfterMove + expectedQuit, appendable.toString());
  }

  @Test
  public void testPlayGameNotEnoughInputs() {
    Appendable appendable = new StringBuilder();
    Readable readable = new StringReader("4 4 4 q");
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireView view = new MarbleSolitaireTextView(model, appendable);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view, readable);

    controller.playGame();

    String expectedAfterMove = "\n    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32";

    String expectedQuit = "Game quit!\n"
            + "State of game when quit:"
            + expectedAfterMove;

    assertEquals(expectedQuit, appendable.toString());
  }

  @Test
  public void testPlayGameTwoSetsOfValidInputs() {
    Appendable appendable = new StringBuilder();
    Readable readable = new StringReader("4 2 4 4 4 5 4 3 q");
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireView view = new MarbleSolitaireTextView(model, appendable);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view, readable);

    controller.playGame();

    String expectedAfterMove1 = "\n    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O _ _ O O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 31\n";

    String expectedAfterMove2 = "\n    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O _ O _ _ O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 30";

    String expectedQuit = "\nGame quit!\n"
            + "State of game when quit:"
            + expectedAfterMove2;

    assertEquals(expectedAfterMove1 + expectedAfterMove2 + expectedQuit, appendable.toString());
  }

  @Test
  public void testPlayGameTwoSetsOfInputsOnlyOneValid() {
    Appendable appendable = new StringBuilder();
    Readable readable = new StringReader("4 2 4 4 4 5 4  q");
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireView view = new MarbleSolitaireTextView(model, appendable);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view, readable);

    controller.playGame();

    String expectedAfterMove = "\n    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O _ _ O O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 31";

    String expectedQuit = "\nGame quit!\n"
            + "State of game when quit:"
            + expectedAfterMove;

    assertEquals(expectedAfterMove + expectedQuit, appendable.toString());
  }

  @Test
  public void testPlayGameOneInput() {
    Appendable appendable = new StringBuilder();
    Readable readable = new StringReader("4 q");
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireView view = new MarbleSolitaireTextView(model, appendable);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view, readable);

    controller.playGame();

    String expectedAfterMove = "\n    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32";

    String expectedQuit = "Game quit!\n"
            + "State of game when quit:"
            + expectedAfterMove;

    assertEquals(expectedQuit, appendable.toString());
  }

  @Test
  public void testPlayGameQuitFirst() {
    Appendable appendable = new StringBuilder();
    Readable readable = new StringReader("q 4 2 4 4");
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireView view = new MarbleSolitaireTextView(model, appendable);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view, readable);

    controller.playGame();

    String expectedAfterMove = "\n    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32";

    String expectedQuit = "Game quit!\n"
            + "State of game when quit:"
            + expectedAfterMove;

    assertEquals(expectedQuit, appendable.toString());
  }

  @Test
  public void testPlayGameQuitSecond() {
    Appendable appendable = new StringBuilder();
    Readable readable = new StringReader("4 q 2 4 4");
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireView view = new MarbleSolitaireTextView(model, appendable);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view, readable);

    controller.playGame();

    String expectedAfterMove = "\n    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32";

    String expectedQuit = "Game quit!\n"
            + "State of game when quit:"
            + expectedAfterMove;

    assertEquals(expectedQuit, appendable.toString());
  }

  @Test
  public void testPlayGameQuitThird() {
    Appendable appendable = new StringBuilder();
    Readable readable = new StringReader("4 2 q 4 4");
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireView view = new MarbleSolitaireTextView(model, appendable);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view, readable);

    controller.playGame();

    String expectedAfterMove = "\n    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32";

    String expectedQuit = "Game quit!\n"
            + "State of game when quit:"
            + expectedAfterMove;

    assertEquals(expectedQuit, appendable.toString());
  }

  @Test
  public void testPlayGameQuitFourth() {
    Appendable appendable = new StringBuilder();
    Readable readable = new StringReader("4 2 4 q 4");
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireView view = new MarbleSolitaireTextView(model, appendable);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view, readable);

    controller.playGame();

    String expectedAfterMove = "\n    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 32";

    String expectedQuit = "Game quit!\n"
            + "State of game when quit:"
            + expectedAfterMove;

    assertEquals(expectedQuit, appendable.toString());
  }

  @Test
  public void testPlayGameQuitLast() {
    Appendable appendable = new StringBuilder();
    Readable readable = new StringReader("4 2 4 4 q");
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireView view = new MarbleSolitaireTextView(model, appendable);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view, readable);

    controller.playGame();

    String expectedAfterMove = "\n    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O _ _ O O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 31";

    String expectedQuit = "\nGame quit!\n"
            + "State of game when quit:"
            + expectedAfterMove;

    assertEquals(expectedAfterMove + expectedQuit, appendable.toString());
  }

  @Test
  public void testPlayGameLose() {
    System.out.println("\n\n***playGame - Lose");
    MarbleSolitaireModel model = new EnglishSolitaireModel(3, 3, 3);
    MarbleSolitaireView view = new MarbleSolitaireTextView(model);

    Readable myIn = new StringReader("4 6 4 4\n"
            + "4 3 4 5\n"
            + "4 1 4 3\n"
            + "2 4 4 4\n"
            + "5 4 3 4\n"
            + "7 4 5 4\n");
    MarbleSolitaireControllerImpl instance = new MarbleSolitaireControllerImpl(model, view, myIn);
    int oldscore = model.getScore();
    instance.playGame();
    int newscore = model.getScore();
    assertEquals(oldscore - 6, newscore);
  }

  @Test
  public void testPlayGameQuit() {
    System.out.println("\n\n***playGame with Quit");
    MarbleSolitaireModel model = new EnglishSolitaireModel(3, 3, 3);
    MarbleSolitaireView view = new MarbleSolitaireTextView(model);

    Readable myIn = new StringReader("4 6 4 4\n" + "Q");
    MarbleSolitaireControllerImpl instance = new MarbleSolitaireControllerImpl(model, view, myIn);
    int oldscore = model.getScore();
    instance.playGame();
    int newscore = model.getScore();
    assertEquals(oldscore - 1, newscore);
  }

  @Test
  public void testPlayGamequit() {
    System.out.println("\n\n***playGame with quit");
    MarbleSolitaireModel model = new EnglishSolitaireModel(3, 3, 3);
    MarbleSolitaireView view = new MarbleSolitaireTextView(model);

    Readable myIn = new StringReader("4 6 4 4\n" + "q");
    MarbleSolitaireControllerImpl instance = new MarbleSolitaireControllerImpl(model, view, myIn);
    int oldscore = model.getScore();
    instance.playGame();
    int newscore = model.getScore();
    assertEquals(oldscore - 1, newscore);
  }

  @Test
  public void testPlayGameBadInput() {
    System.out.println("\n\n***playGame - Bad Input");
    MarbleSolitaireModel model = new EnglishSolitaireModel(3, 3, 3);
    MarbleSolitaireView view = new MarbleSolitaireTextView(model);

    Readable myIn = new StringReader("4 6 4 4\n"
            + "4 a 3 -1 4 5.0 5\n" // bad input
            + "q");
    MarbleSolitaireControllerImpl instance = new MarbleSolitaireControllerImpl(model, view, myIn);
    int oldscore = model.getScore();
    instance.playGame();
    int newscore = model.getScore();
    assertEquals(oldscore - 2, newscore);
  }

  @Test
  public void testPlayGameBadMove() {
    System.out.println("\n\n***playGame - Bad Move");
    MarbleSolitaireModel model = new EnglishSolitaireModel(3, 3, 3);
    MarbleSolitaireView view = new MarbleSolitaireTextView(model);

    Readable myIn = new StringReader("4 6 4 4\n"
            + "1 3 1 5\n" // bad move
            + "4 3 4 5\n"
            + "4 1 4 3\n"
            + "2 4 4 4\n"
            + "5 4 3 4\n"
            + "7 4 5 4\n");
    MarbleSolitaireControllerImpl instance = new MarbleSolitaireControllerImpl(model, view, myIn);
    int oldscore = model.getScore();
    try {
      instance.playGame();
      int newscore = model.getScore();
      assertEquals(oldscore - 6, newscore);
    } catch (Exception e) {
      System.out.println("***" + e.getClass());
      assertEquals("class java.lang.IllegalArgumentException", e.getClass().toString());
    }
  }

  @Test
  public void testPlayGameSpacesBetweenInputs() {
    StringBuilder appendable = new StringBuilder();
    Readable readable = new StringReader("2\n"
            + "4\n"
            + " 4\n"
            + " 4\n q");
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireView view = new MarbleSolitaireTextView(model, appendable);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view, readable);

    controller.playGame();

    String expectedAfterMove = "\n    O O O\n"
            + "    O _ O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O\n"
            + "Score: 31";

    String expectedQuit = "\n"
            + "Game quit!\n"
            + "State of game when quit:"
            + expectedAfterMove;

    assertEquals(expectedAfterMove + expectedQuit, appendable.toString());
  }

  @Test
  public void testDisplayBoardWithArmThicknessFive() {
    StringBuilder appendable = new StringBuilder();
    Readable readable = new StringReader("7 5 7 7 q");
    MarbleSolitaireModel model = new EnglishSolitaireModel(5);
    MarbleSolitaireView view = new MarbleSolitaireTextView(model, appendable);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view, readable);

    controller.playGame();

    String expectedAfterMove = "\n        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O _ _ O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "Score: 103";

    String expectedQuit = "\n"
            + "Game quit!\n"
            + "State of game when quit:"
            + expectedAfterMove;

    assertEquals(expectedAfterMove + expectedQuit, appendable.toString());
  }

  @Test
  public void testPlayGameGameOver() {
    StringBuilder appendable = new StringBuilder();
    Readable readable = new StringReader("6 4 4 4\n"
            + "5 6 5 4\n"
            + "7 5 5 5\n"
            + "7 3 7 5\n"
            + "4 5 6 5\n"
            + "7 5 5 5\n"
            + "2 5 4 5\n"
            + "3 7 3 5\n"
            + "5 7 3 7\n"
            + "3 4 3 6\n"
            + "3 7 3 5\n"
            + "3 2 3 4\n"
            + "1 3 3 3\n"
            + "1 5 1 3\n"
            + "4 3 2 3\n"
            + "1 3 3 3\n"
            + "6 3 4 3\n"
            + "5 1 5 3\n"
            + "3 1 5 1\n"
            + "5 4 5 2\n"
            + "5 1 5 3\n"
            + "3 4 3 2\n"
            + "3 2 5 2\n"
            + "5 2 5 4\n"
            + "5 4 5 6\n"
            + "5 6 3 6\n"
            + "3 6 3 4\n"
            + "4 4 4 2\n"
            + "2 4 4 4\n"
            + "4 5 4 3\n"
            + "4 2 4 4 q\n");
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireView view = new MarbleSolitaireTextView(model, appendable);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view, readable);

    controller.playGame();

    String expectedAfterMove1 = "\n    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "    O _ O\n"
            + "    O O O\n"
            + "Score: 31\n";

    String expectedAfterMove2 = "\n    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "O O O O _ _ O\n"
            + "    O _ O\n"
            + "    O O O\n"
            + "Score: 30\n";

    String expectedAfterMove3 = "\n    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "O O O O O _ O\n"
            + "    O _ _\n"
            + "    O O _\n"
            + "Score: 29\n";

    String expectedAfterMove4 = "\n    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "O O O O O _ O\n"
            + "    O _ _\n"
            + "    _ _ O\n"
            + "Score: 28\n";

    String expectedAfterMove5 = "\n    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O O _ O O\n"
            + "O O O O _ _ O\n"
            + "    O _ O\n"
            + "    _ _ O\n"
            + "Score: 27\n";

    String expectedAfterMove6 = "\n    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O O _ O O\n"
            + "O O O O O _ O\n"
            + "    O _ _\n"
            + "    _ _ _\n"
            + "Score: 26\n";

    String expectedAfterMove7 = "\n    O O O\n"
            + "    O O _\n"
            + "O O O O _ O O\n"
            + "O O O O O O O\n"
            + "O O O O O _ O\n"
            + "    O _ _\n"
            + "    _ _ _\n"
            + "Score: 25\n";

    String expectedAfterMove8 = "\n    O O O\n"
            + "    O O _\n"
            + "O O O O O _ _\n"
            + "O O O O O O O\n"
            + "O O O O O _ O\n"
            + "    O _ _\n"
            + "    _ _ _\n"
            + "Score: 24\n";

    String expectedAfterMove9 = "\n    O O O\n"
            + "    O O _\n"
            + "O O O O O _ O\n"
            + "O O O O O O _\n"
            + "O O O O O _ _\n"
            + "    O _ _\n"
            + "    _ _ _\n"
            + "Score: 23\n";

    String expectedAfterMove10 = "\n    O O O\n"
            + "    O O _\n"
            + "O O O _ _ O O\n"
            + "O O O O O O _\n"
            + "O O O O O _ _\n"
            + "    O _ _\n"
            + "    _ _ _\n"
            + "Score: 22\n";

    String expectedAfterMove11 = "\n    O O O\n"
            + "    O O _\n"
            + "O O O _ O _ _\n"
            + "O O O O O O _\n"
            + "O O O O O _ _\n"
            + "    O _ _\n"
            + "    _ _ _\n"
            + "Score: 21\n";

    String expectedAfterMove12 = "\n    O O O\n"
            + "    O O _\n"
            + "O _ _ O O _ _\n"
            + "O O O O O O _\n"
            + "O O O O O _ _\n"
            + "    O _ _\n"
            + "    _ _ _\n"
            + "Score: 20\n";

    String expectedAfterMove13 = "\n    _ O O\n"
            + "    _ O _\n"
            + "O _ O O O _ _\n"
            + "O O O O O O _\n"
            + "O O O O O _ _\n"
            + "    O _ _\n"
            + "    _ _ _\n"
            + "Score: 19\n";

    String expectedAfterMove14 = "\n    O _ _\n"
            + "    _ O _\n"
            + "O _ O O O _ _\n"
            + "O O O O O O _\n"
            + "O O O O O _ _\n"
            + "    O _ _\n"
            + "    _ _ _\n"
            + "Score: 18\n";

    String expectedAfterMove15 = "\n    O _ _\n"
            + "    O O _\n"
            + "O _ _ O O _ _\n"
            + "O O _ O O O _\n"
            + "O O O O O _ _\n"
            + "    O _ _\n"
            + "    _ _ _\n"
            + "Score: 17\n";

    String expectedAfterMove16 = "\n    _ _ _\n"
            + "    _ O _\n"
            + "O _ O O O _ _\n"
            + "O O _ O O O _\n"
            + "O O O O O _ _\n"
            + "    O _ _\n"
            + "    _ _ _\n"
            + "Score: 16\n";

    String expectedAfterMove17 = "\n    _ _ _\n"
            + "    _ O _\n"
            + "O _ O O O _ _\n"
            + "O O O O O O _\n"
            + "O O _ O O _ _\n"
            + "    _ _ _\n"
            + "    _ _ _\n"
            + "Score: 15\n";

    String expectedAfterMove18 = "\n    _ _ _\n"
            + "    _ O _\n"
            + "O _ O O O _ _\n"
            + "O O O O O O _\n"
            + "_ _ O O O _ _\n"
            + "    _ _ _\n"
            + "    _ _ _\n"
            + "Score: 14\n";

    String expectedAfterMove19 = "\n    _ _ _\n"
            + "    _ O _\n"
            + "_ _ O O O _ _\n"
            + "_ O O O O O _\n"
            + "O _ O O O _ _\n"
            + "    _ _ _\n"
            + "    _ _ _\n"
            + "Score: 13\n";

    String expectedAfterMove20 = "\n    _ _ _\n"
            + "    _ O _\n"
            + "_ _ O O O _ _\n"
            + "_ O O O O O _\n"
            + "O O _ _ O _ _\n"
            + "    _ _ _\n"
            + "    _ _ _\n"
            + "Score: 12\n";

    String expectedAfterMove21 = "\n    _ _ _\n"
            + "    _ O _\n"
            + "_ _ O O O _ _\n"
            + "_ O O O O O _\n"
            + "_ _ O _ O _ _\n"
            + "    _ _ _\n"
            + "    _ _ _\n"
            + "Score: 11\n";

    String expectedAfterMove22 = "\n    _ _ _\n"
            + "    _ O _\n"
            + "_ O _ _ O _ _\n"
            + "_ O O O O O _\n"
            + "_ _ O _ O _ _\n"
            + "    _ _ _\n"
            + "    _ _ _\n"
            + "Score: 10\n";

    String expectedAfterMove23 = "\n    _ _ _\n"
            + "    _ O _\n"
            + "_ _ _ _ O _ _\n"
            + "_ _ O O O O _\n"
            + "_ O O _ O _ _\n"
            + "    _ _ _\n"
            + "    _ _ _\n"
            + "Score: 9\n";

    String expectedAfterMove24 = "\n    _ _ _\n"
            + "    _ O _\n"
            + "_ _ _ _ O _ _\n"
            + "_ _ O O O O _\n"
            + "_ _ _ O O _ _\n"
            + "    _ _ _\n"
            + "    _ _ _\n"
            + "Score: 8\n";

    String expectedAfterMove25 = "\n    _ _ _\n"
            + "    _ O _\n"
            + "_ _ _ _ O _ _\n"
            + "_ _ O O O O _\n"
            + "_ _ _ _ _ O _\n"
            + "    _ _ _\n"
            + "    _ _ _\n"
            + "Score: 7\n";

    String expectedAfterMove26 = "\n    _ _ _\n"
            + "    _ O _\n"
            + "_ _ _ _ O O _\n"
            + "_ _ O O O _ _\n"
            + "_ _ _ _ _ _ _\n"
            + "    _ _ _\n"
            + "    _ _ _\n"
            + "Score: 6\n";

    String expectedAfterMove27 = "\n    _ _ _\n"
            + "    _ O _\n"
            + "_ _ _ O _ _ _\n"
            + "_ _ O O O _ _\n"
            + "_ _ _ _ _ _ _\n"
            + "    _ _ _\n"
            + "    _ _ _\n"
            + "Score: 5\n";

    String expectedAfterMove28 = "\n    _ _ _\n"
            + "    _ O _\n"
            + "_ _ _ O _ _ _\n"
            + "_ O _ _ O _ _\n"
            + "_ _ _ _ _ _ _\n"
            + "    _ _ _\n"
            + "    _ _ _\n"
            + "Score: 4\n";

    String expectedAfterMove29 = "\n    _ _ _\n"
            + "    _ _ _\n"
            + "_ _ _ _ _ _ _\n"
            + "_ O _ O O _ _\n"
            + "_ _ _ _ _ _ _\n"
            + "    _ _ _\n"
            + "    _ _ _\n"
            + "Score: 3\n";

    String expectedAfterMove30 = "\n    _ _ _\n"
            + "    _ _ _\n"
            + "_ _ _ _ _ _ _\n"
            + "_ O O _ _ _ _\n"
            + "_ _ _ _ _ _ _\n"
            + "    _ _ _\n"
            + "    _ _ _\n"
            + "Score: 2\n";

    String expectedAfterMove31 = "\n    _ _ _\n"
            + "    _ _ _\n"
            + "_ _ _ _ _ _ _\n"
            + "_ _ _ O _ _ _\n"
            + "_ _ _ _ _ _ _\n"
            + "    _ _ _\n"
            + "    _ _ _\n"
            + "Score: 1";

    String expectedGameOver = "\n"
            + "Game over!"
            + expectedAfterMove31;

    assertEquals(expectedAfterMove1
            + expectedAfterMove2
            + expectedAfterMove3
            + expectedAfterMove4
            + expectedAfterMove5
            + expectedAfterMove6
            + expectedAfterMove7
            + expectedAfterMove8
            + expectedAfterMove9
            + expectedAfterMove10
            + expectedAfterMove11
            + expectedAfterMove12
            + expectedAfterMove13
            + expectedAfterMove14
            + expectedAfterMove15
            + expectedAfterMove16
            + expectedAfterMove17
            + expectedAfterMove18
            + expectedAfterMove19
            + expectedAfterMove20
            + expectedAfterMove21
            + expectedAfterMove22
            + expectedAfterMove23
            + expectedAfterMove24
            + expectedAfterMove25
            + expectedAfterMove26
            + expectedAfterMove27
            + expectedAfterMove28
            + expectedAfterMove29
            + expectedAfterMove30
            + expectedAfterMove31
            + expectedGameOver, appendable.toString());
  }

  @Test
  public void testPlayGameSolve() {
    System.out.println("\n\n***playGame solve");
    MarbleSolitaireModel model = new EnglishSolitaireModel(3, 3, 3);
    MarbleSolitaireView view = new MarbleSolitaireTextView(model);

    Readable readable = new StringReader("6 4 4 4\n"
            + "5 6 5 4\n"
            + "7 5 5 5\n"
            + "7 3 7 5\n"
            + "4 5 6 5\n"
            + "7 5 5 5\n"
            + "2 5 4 5\n"
            + "3 7 3 5\n"
            + "5 7 3 7\n"
            + "3 4 3 6\n"
            + "3 7 3 5\n"
            + "3 2 3 4\n"
            + "1 3 3 3\n"
            + "1 5 1 3\n"
            + "4 3 2 3\n"
            + "1 3 3 3\n"
            + "6 3 4 3\n"
            + "5 1 5 3\n"
            + "3 1 5 1\n"
            + "5 4 5 2\n"
            + "5 1 5 3\n"
            + "3 4 3 2\n"
            + "3 2 5 2\n"
            + "5 2 5 4\n"
            + "5 4 5 6\n"
            + "5 6 3 6\n"
            + "3 6 3 4\n"
            + "4 4 4 2\n"
            + "2 4 4 4\n"
            + "4 5 4 3\n"
            + "4 2 4 4 q\n");
    MarbleSolitaireControllerImpl instance =
            new MarbleSolitaireControllerImpl(model, view, readable);
    int oldscore = model.getScore();
    instance.playGame();
    int newscore = model.getScore();
    assertEquals(oldscore - 31, newscore);
  }
}