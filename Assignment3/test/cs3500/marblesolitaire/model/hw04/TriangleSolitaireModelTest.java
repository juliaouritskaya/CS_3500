package cs3500.marblesolitaire.model.hw04;
import org.junit.Assert;
import org.junit.Test;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState.SlotState;

import static org.junit.Assert.assertEquals;

public class TriangleSolitaireModelTest {
  @Test
  public void testConstructor1() {
    MarbleSolitaireModel s = new TriangleSolitaireModel();
    int expectedArmThickness = 5;
    assertEquals(expectedArmThickness, s.getBoardSize());
    assertEquals(SlotState.Empty, s.getSlotAt(0, 0));
  }

  @Test
  public void testConstructor2() {
    MarbleSolitaireModel s = new TriangleSolitaireModel(2, 2);
    int expectedArmThickness = 5;
    assertEquals(expectedArmThickness, s.getBoardSize());
    assertEquals(SlotState.Empty, s.getSlotAt(2, 2));
  }

  @Test
  public void testConstructor3() {
    MarbleSolitaireModel s = new TriangleSolitaireModel(7);
    int expectedArmThickness = 7;
    assertEquals(expectedArmThickness, s.getBoardSize());
    assertEquals(SlotState.Empty, s.getSlotAt(0, 0));
  }

  @Test
  public void testConstructor4() {
    MarbleSolitaireModel s = new TriangleSolitaireModel(9, 7, 4);
    int expectedArmThickness = 9;
    assertEquals(expectedArmThickness, s.getBoardSize());
    assertEquals(SlotState.Empty, s.getSlotAt(7, 4));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor2InvalidEmptySlot() {
    try {
      MarbleSolitaireModel s = new TriangleSolitaireModel(0, 10);
    } catch (IllegalArgumentException i) {
      String output = "Specified position (0, 10) is invalid.";
      Assert.assertEquals(output, i.getMessage());
      throw i;
    }
    Assert.fail();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor4NegativeArmThickness() {
    try {
      MarbleSolitaireModel s = new TriangleSolitaireModel(-1, 3, 3);
    } catch (IllegalArgumentException i) {
      String output = "Specified dimension is invalid, must be non-positive.";
      Assert.assertEquals(output, i.getMessage());
      throw i;
    }
    Assert.fail();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor4OutOfBoundsEmptySlot() {
    try {
      MarbleSolitaireModel s = new TriangleSolitaireModel(5, -1, 0);
    } catch (IllegalArgumentException i) {
      String output = "Specified position (-1, 0) is invalid.";
      Assert.assertEquals(output, i.getMessage());
      throw i;
    }
    Assert.fail();
  }

  @Test
  public void testMoveUpLeft() {
    MarbleSolitaireModel s = new TriangleSolitaireModel();
    assertEquals(SlotState.Empty, s.getSlotAt(0, 0));
    s.move(2, 2, 0, 0);
    assertEquals(SlotState.Marble, s.getSlotAt(0, 0));
    assertEquals(SlotState.Empty, s.getSlotAt(2, 2));
  }

  @Test
  public void testMoveUpRight() {
    MarbleSolitaireModel s = new TriangleSolitaireModel();
    assertEquals(SlotState.Empty, s.getSlotAt(0, 0));
    s.move(2, 0, 0, 0);
    assertEquals(SlotState.Marble, s.getSlotAt(0, 0));
    assertEquals(SlotState.Empty, s.getSlotAt(2, 0));
  }

  @Test
  public void testMoveDownLeft() {
    MarbleSolitaireModel s = new TriangleSolitaireModel(5, 2, 2);
    assertEquals(SlotState.Empty, s.getSlotAt(2, 2));
    s.move(0, 0, 2, 2);
    assertEquals(SlotState.Marble, s.getSlotAt(2, 2));
    assertEquals(SlotState.Empty, s.getSlotAt(0, 0));
  }

  @Test
  public void testMoveDownRight() {
    MarbleSolitaireModel s = new TriangleSolitaireModel(5, 2, 0);
    assertEquals(SlotState.Empty, s.getSlotAt(2, 0));
    s.move(0, 0, 2, 0);
    assertEquals(SlotState.Marble, s.getSlotAt(2, 0));
    assertEquals(SlotState.Empty, s.getSlotAt(0, 0));
  }

  @Test
  public void testMoveLeft() {
    MarbleSolitaireModel s = new TriangleSolitaireModel();
    assertEquals(SlotState.Empty, s.getSlotAt(0, 0));
    s.move(2, 0, 0, 0);
    assertEquals(SlotState.Marble, s.getSlotAt(0, 0));
    assertEquals(SlotState.Empty, s.getSlotAt(2, 0));

    s.move(2, 2, 2, 0);
    assertEquals(SlotState.Marble, s.getSlotAt(2, 0));
    assertEquals(SlotState.Empty, s.getSlotAt(2, 2));
  }

  @Test
  public void testMoveRight() {
    MarbleSolitaireModel s = new TriangleSolitaireModel();
    assertEquals(SlotState.Empty, s.getSlotAt(0, 0));
    s.move(2, 2, 0, 0);
    assertEquals(SlotState.Marble, s.getSlotAt(0, 0));
    assertEquals(SlotState.Empty, s.getSlotAt(2, 2));

    s.move(2, 0, 2, 2);
    assertEquals(SlotState.Marble, s.getSlotAt(2, 2));
    assertEquals(SlotState.Empty, s.getSlotAt(2, 0));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveFromEmpty() {
    try {
      MarbleSolitaireModel s = new TriangleSolitaireModel();
      s.move(0, 0, 2, 2);
    } catch (IllegalArgumentException i) {
      String output = "Move is invalid.";
      Assert.assertEquals(output, i.getMessage());
      throw i;
    }
    Assert.fail();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveFromInvalid() {
    try {
      MarbleSolitaireModel s = new TriangleSolitaireModel();
      s.move(-1, 1, 3, 1);
    } catch (IllegalArgumentException i) {
      String output = "Move is invalid.";
      Assert.assertEquals(output, i.getMessage());
      throw i;
    }
    Assert.fail();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveToMarble() {
    try {
      MarbleSolitaireModel s = new TriangleSolitaireModel();
      s.move(1, 1, 3, 3);
    } catch (IllegalArgumentException i) {
      String output = "Move is invalid.";
      Assert.assertEquals(output, i.getMessage());
      throw i;
    }
    Assert.fail();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveToInvalid() {
    try {
      MarbleSolitaireModel s = new TriangleSolitaireModel();
      s.move(0, 3, -3, 1);
    } catch (IllegalArgumentException i) {
      String output = "Move is invalid.";
      Assert.assertEquals(output, i.getMessage());
      throw i;
    }
    Assert.fail();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveTooMany() {
    try {
      MarbleSolitaireModel s = new TriangleSolitaireModel();
      s.move(2, 2, 0, 0);
      s.move(4, 4, 1, 1);
    } catch (IllegalArgumentException i) {
      String output = "Move is invalid.";
      Assert.assertEquals(output, i.getMessage());
      throw i;
    }
    Assert.fail();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveFromOutOfBounds() {
    try {
      MarbleSolitaireModel s = new TriangleSolitaireModel();
      s.move(-4, -3, -1, -3);
    } catch (IllegalArgumentException i) {
      String output = "Move is invalid.";
      Assert.assertEquals(output, i.getMessage());
      throw i;
    }
    Assert.fail();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveStraight() {
    try {
      MarbleSolitaireModel s = new TriangleSolitaireModel();
      s.move(2, 2, 0, 0);
      s.move(4, 4, 1, 1);
      s.move(4, 3, 2, 2);
    } catch (IllegalArgumentException i) {
      String output = "Move is invalid.";
      Assert.assertEquals(output, i.getMessage());
      throw i;
    }
    Assert.fail();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveOverEmptySlot() {
    try {
      MarbleSolitaireModel s = new TriangleSolitaireModel();
      s.move(2, 2, 0, 0);
      s.move(3, 3, 1, 1);
    } catch (IllegalArgumentException i) {
      String output = "Move is invalid.";
      Assert.assertEquals(output, i.getMessage());
      throw i;
    }
    Assert.fail();
  }

  @Test
  public void testGetSlotAt() {
    MarbleSolitaireModel s = new TriangleSolitaireModel();
    assertEquals(SlotState.Empty, s.getSlotAt(0, 0));
    assertEquals(SlotState.Marble, s.getSlotAt(4, 3));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetSlotAtInvalid() {
    try {
      MarbleSolitaireModel s = new TriangleSolitaireModel();
      s.getSlotAt(-1, 3);
      s.getSlotAt(0, 80);
    } catch (IllegalArgumentException i) {
      String output = "Beyond the dimensions of the board.";
      Assert.assertEquals(output, i.getMessage());
      throw i;
    }
    Assert.fail();
  }

  @Test
  public void testIsGameOver() {
    MarbleSolitaireModel s = new TriangleSolitaireModel();
    assertEquals(false, s.isGameOver());
    s.move(2, 2, 0, 0);
    s.move(2, 0, 2, 2);
    s.move(3, 3, 1, 1);
    s.move(0, 0, 2, 0);
    s.move(3, 0, 1, 0);
    s.move(3, 1, 3, 3);
    s.move(4, 4, 2, 2);
    s.move(2, 2, 0, 0);
    s.move(0, 0, 2, 0);
    s.move(4, 2, 4, 4);
    s.move(4, 0, 4, 2);
    assertEquals(true, s.isGameOver());
  }

  @Test
  public void testGetBoardSize() {
    MarbleSolitaireModel s = new TriangleSolitaireModel();
    assertEquals(5, s.getBoardSize());

    MarbleSolitaireModel s2 = new TriangleSolitaireModel(8);
    assertEquals(8, s2.getBoardSize());

    MarbleSolitaireModel s3 = new TriangleSolitaireModel(12);
    assertEquals(12, s3.getBoardSize());
  }

  @Test
  public void testGetScore() {
    MarbleSolitaireModel s = new TriangleSolitaireModel();
    assertEquals(14, s.getScore());

    s.move(2, 2, 0, 0);
    s.move(2, 0, 2, 2);
    s.move(3, 3, 1, 1);
    s.move(0, 0, 2, 0);
    s.move(3, 0, 1, 0);
    s.move(3, 1, 3, 3);
    s.move(4, 4, 2, 2);
    s.move(2, 2, 0, 0);
    s.move(0, 0, 2, 0);
    s.move(4, 2, 4, 4);
    s.move(4, 0, 4, 2);

    assertEquals(3, s.getScore());
  }
}