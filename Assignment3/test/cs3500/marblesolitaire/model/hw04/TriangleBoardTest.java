package cs3500.marblesolitaire.model.hw04;

import org.junit.Assert;
import org.junit.Test;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState.SlotState;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TriangleBoardTest {
  @Test
  public void testTriangleBoardConstructor() {
    MarbleSolitaireModel s = new TriangleSolitaireModel();
    TriangleBoard b = new TriangleBoard(5, 2, 2);
    assertEquals(5, s.getBoardSize());
    int expectedArmThickness = 5;
    assertEquals(expectedArmThickness, s.getBoardSize());
  }

  @Test
  public void testCreateBoard2() {
    MarbleSolitaireModel s = new TriangleSolitaireModel(5, 2, 2);
    TriangleBoard b = new TriangleBoard(5, 2, 2);
    b.createBoard(2, 2);
    assertEquals(SlotState.Marble, s.getSlotAt(0, 0));
    assertEquals(SlotState.Marble, s.getSlotAt(4, 0));
    assertEquals(SlotState.Marble, s.getSlotAt(4, 4));
    assertEquals(SlotState.Empty, s.getSlotAt(2, 2));
  }

  @Test
  public void testInBounds() {
    TriangleBoard b = new TriangleBoard(5, 2, 2);
    b.createBoard(3, 3);
    assertFalse(b.inBounds(0, 3));
    assertTrue(b.inBounds(1, 1));
    assertTrue(b.inBounds(3, 3));
    assertTrue(b.inBounds(2, 2));
    assertFalse(b.inBounds(-3, 3));
    assertFalse(b.inBounds(5, -5));
    assertFalse(b.inBounds(9, 3));
    assertFalse(b.inBounds(0, 8));
  }

  @Test
  public void testCanMove() {
    TriangleBoard b = new TriangleBoard(5, 2, 2);
    b.createBoard(2, 2);
    assertTrue(b.canMove(0, 0, 2, 2));
    assertTrue(b.canMove(4, 4, 2, 2));
    assertTrue(b.canMove(2, 0, 2, 2));
    assertFalse(b.canMove(1, 1, 2, 2));

    b.move(0, 0, 2, 2);
    assertTrue(b.canMove(3, 3, 1, 1));
  }

  @Test
  public void testMoveBoard() {
    MarbleSolitaireModel s = new TriangleSolitaireModel(5, 2, 2);
    TriangleBoard b = new TriangleBoard(5, 2, 2);
    b.createBoard(2, 2);
    assertEquals(SlotState.Empty, s.getSlotAt(2, 2));
    assertTrue(b.canMove(0, 0, 2, 2));
    s.move(0, 0, 2, 2);
    assertEquals(SlotState.Marble, s.getSlotAt(2, 2));
    assertEquals(SlotState.Empty, s.getSlotAt(0, 0));
    assertEquals(SlotState.Empty, s.getSlotAt(1, 1));

    s.move(3, 3, 1, 1);
    assertEquals(SlotState.Marble, s.getSlotAt(1, 1));
    assertEquals(SlotState.Empty, s.getSlotAt(2, 2));
    assertEquals(SlotState.Empty, s.getSlotAt(3, 3));

    s.move(2, 0, 2, 2);
    assertEquals(SlotState.Marble, s.getSlotAt(2, 2));
    assertEquals(SlotState.Empty, s.getSlotAt(2, 0));
    assertEquals(SlotState.Empty, s.getSlotAt(2, 1));

    s.move(3, 1, 3, 3);
    assertEquals(SlotState.Marble, s.getSlotAt(3, 3));
    assertEquals(SlotState.Empty, s.getSlotAt(3, 1));
    assertEquals(SlotState.Empty, s.getSlotAt(3, 2));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveBoardInvalid() {
    try {
      TriangleBoard b = new TriangleBoard(5, 2, 2);
      b.move(1, 1, 3, 1);
    } catch (IllegalArgumentException i) {
      String output = "Move is invalid.";
      Assert.assertEquals(output, i.getMessage());
      throw i;
    }
    Assert.fail();
  }
}