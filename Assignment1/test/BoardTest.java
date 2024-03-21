import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import cs3500.marblesolitaire.model.hw02.Board;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState.SlotState;

/**
 * This class represents the tests for the board class.
 */
public class BoardTest {
  @Test
  public void testBoardConstructor() {
    MarbleSolitaireModel s = new EnglishSolitaireModel();
    Board b = new Board(3, 3, 3);
    assertEquals(7, s.getBoardSize());
    int expectedArmThickness = 3;
    assertEquals(expectedArmThickness, s.getBoardSize() - 4);
  }

  @Test
  public void testCreateBoard1() {
    MarbleSolitaireModel s = new EnglishSolitaireModel();
    Board b = new Board(3, 3, 3);
    b.createBoard(3, 3);
    assertEquals(SlotState.Marble, s.getSlotAt(2, 0));
    assertEquals(SlotState.Marble, s.getSlotAt(0, 3));
    assertEquals(SlotState.Marble, s.getSlotAt(5, 4));
    assertEquals(SlotState.Marble, s.getSlotAt(3, 6));
    assertEquals(SlotState.Invalid, s.getSlotAt(0, 0));
    assertEquals(SlotState.Invalid, s.getSlotAt(1, 5));
    assertEquals(SlotState.Invalid, s.getSlotAt(6, 0));
    assertEquals(SlotState.Invalid, s.getSlotAt(5, 5));
    assertEquals(SlotState.Empty, s.getSlotAt(3, 3));
  }

  @Test
  public void testCreateBoard2() {
    MarbleSolitaireModel s = new EnglishSolitaireModel(5, 6, 10);
    Board b = new Board(5, 6, 10);
    b.createBoard(6, 10);
    assertEquals(SlotState.Marble, s.getSlotAt(2, 5));
    assertEquals(SlotState.Marble, s.getSlotAt(6, 0));
    assertEquals(SlotState.Marble, s.getSlotAt(6, 6));
    assertEquals(SlotState.Marble, s.getSlotAt(10, 4));
    assertEquals(SlotState.Invalid, s.getSlotAt(1, 2));
    assertEquals(SlotState.Invalid, s.getSlotAt(2, 10));
    assertEquals(SlotState.Invalid, s.getSlotAt(10, 2));
    assertEquals(SlotState.Invalid, s.getSlotAt(10, 9));
    assertEquals(SlotState.Empty, s.getSlotAt(6, 10));
  }

  @Test
  public void testInBounds() {
    Board b = new Board(3, 3, 3);
    b.createBoard(3, 3);
    assertTrue(b.inBounds(0, 3));
    assertFalse(b.inBounds(1, 1));
    assertTrue(b.inBounds(3, 3));
    assertFalse(b.inBounds(-3, 3));
    assertFalse(b.inBounds(5, -5));
    assertFalse(b.inBounds(9, 3));
    assertFalse(b.inBounds(0, 8));
  }

  @Test
  public void testCanMove() {
    Board b = new Board(5, 6, 10);
    b.createBoard(6, 10);
    assertTrue(b.canMove(4, 10, 6, 10));
    assertTrue(b.canMove(6, 8, 6, 10));
    assertFalse(b.canMove(12, 6, 6, 10));

    b.move(4, 10, 6, 10);
    assertTrue(b.canMove(7, 10, 5, 10));
  }

  @Test
  public void testMoveBoard() {
    MarbleSolitaireModel s = new EnglishSolitaireModel();
    Board b = new Board(3, 3, 3);
    b.createBoard(3, 3);
    assertEquals(SlotState.Empty, s.getSlotAt(3, 3));
    assertTrue(b.canMove(5, 3, 3, 3));
    s.move(5, 3, 3, 3);
    assertEquals(SlotState.Marble, s.getSlotAt(3, 3));
    assertEquals(SlotState.Empty, s.getSlotAt(5, 3));
    assertEquals(SlotState.Empty, s.getSlotAt(4, 3));

    s.move(2, 3, 4, 3);
    assertEquals(SlotState.Marble, s.getSlotAt(4, 3));
    assertEquals(SlotState.Empty, s.getSlotAt(3, 3));
    assertEquals(SlotState.Empty, s.getSlotAt(2, 3));

    s.move(0, 3, 2, 3);
    assertEquals(SlotState.Marble, s.getSlotAt(2, 3));
    assertEquals(SlotState.Empty, s.getSlotAt(0, 3));
    assertEquals(SlotState.Empty, s.getSlotAt(1, 3));

    s.move(3, 1, 3, 3);
    assertEquals(SlotState.Marble, s.getSlotAt(3, 3));
    assertEquals(SlotState.Empty, s.getSlotAt(3, 1));
    assertEquals(SlotState.Empty, s.getSlotAt(3, 2));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveBoardInvalid() {
    try {
      Board b = new Board(3, 3, 3);
      b.move(1, 1, 3, 1);
    } catch (IllegalArgumentException i) {
      String output = "Move is invalid.";
      Assert.assertEquals(output, i.getMessage());
      throw i;
    }
    Assert.fail();
  }

}