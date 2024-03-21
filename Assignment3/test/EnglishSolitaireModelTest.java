import org.junit.Assert;
import org.junit.Test;

import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState.SlotState;

import static org.junit.Assert.assertEquals;

/**
 * This class represents the tests for the english solitaire model class.
 */
public class EnglishSolitaireModelTest {

  @Test
  public void testConstructor1() {
    MarbleSolitaireModel s = new EnglishSolitaireModel();
    int expectedArmThickness = 3;
    assertEquals(expectedArmThickness, s.getBoardSize() - 4);
    assertEquals(SlotState.Empty, s.getSlotAt(3, 3));
  }

  @Test
  public void testConstructor2() {
    MarbleSolitaireModel s = new EnglishSolitaireModel(6, 3);
    int expectedArmThickness = 3;
    assertEquals(expectedArmThickness, s.getBoardSize() - 4);
    assertEquals(SlotState.Empty, s.getSlotAt(6, 3));
  }

  @Test
  public void testConstructor3() {
    MarbleSolitaireModel s = new EnglishSolitaireModel(5);
    int expectedArmThickness = 5;
    assertEquals(expectedArmThickness, s.getBoardSize() - 8);
    assertEquals(SlotState.Empty, s.getSlotAt(6, 6));
  }

  @Test
  public void testConstructor4() {
    MarbleSolitaireModel s = new EnglishSolitaireModel(9, 2, 10);
    int expectedArmThickness = 9;
    assertEquals(expectedArmThickness, s.getBoardSize() - 16);
    assertEquals(SlotState.Empty, s.getSlotAt(2, 10));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor2InvalidEmptySlot() {
    try {
      MarbleSolitaireModel s = new EnglishSolitaireModel(0, 0);
    } catch (IllegalArgumentException i) {
      String output = "Invalid empty cell position (0,0)";
      Assert.assertEquals(output, i.getMessage());
      throw i;
    }
    Assert.fail();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor4NegativeArmThickness() {
    try {
      MarbleSolitaireModel s = new EnglishSolitaireModel(-1, 3, 3);
    } catch (IllegalArgumentException i) {
      String output = "Arm thickness must be a positive, odd number.";
      Assert.assertEquals(output, i.getMessage());
      throw i;
    }
    Assert.fail();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor4EvenArmThickness() {
    try {
      MarbleSolitaireModel s = new EnglishSolitaireModel(10, 3, 3);
    } catch (IllegalArgumentException i) {
      String output = "Arm thickness must be a positive, odd number.";
      Assert.assertEquals(output, i.getMessage());
      throw i;
    }
    Assert.fail();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor4OutOfBoundsEmptySlot() {
    try {
      MarbleSolitaireModel s = new EnglishSolitaireModel(3, 0, 0);
    } catch (IllegalArgumentException i) {
      String output = "Invalid empty cell position (0,0)";
      Assert.assertEquals(output, i.getMessage());
      throw i;
    }
    Assert.fail();
  }

  @Test
  public void testMoveUp() {
    MarbleSolitaireModel s = new EnglishSolitaireModel();
    assertEquals(SlotState.Empty, s.getSlotAt(3, 3));
    s.move(5, 3, 3, 3);
    assertEquals(SlotState.Marble, s.getSlotAt(3, 3));
    assertEquals(SlotState.Empty, s.getSlotAt(5, 3));
  }

  @Test
  public void testMoveDown() {
    MarbleSolitaireModel s = new EnglishSolitaireModel();
    assertEquals(SlotState.Empty, s.getSlotAt(3, 3));
    s.move(1, 3, 3, 3);
    assertEquals(SlotState.Marble, s.getSlotAt(3, 3));
    assertEquals(SlotState.Empty, s.getSlotAt(1, 3));
  }

  @Test
  public void testMoveLeft() {
    MarbleSolitaireModel s = new EnglishSolitaireModel();
    assertEquals(SlotState.Empty, s.getSlotAt(3, 3));
    s.move(3, 5, 3, 3);
    assertEquals(SlotState.Marble, s.getSlotAt(3, 3));
    assertEquals(SlotState.Empty, s.getSlotAt(3, 5));
  }

  @Test
  public void testMoveRight() {
    MarbleSolitaireModel s = new EnglishSolitaireModel();
    assertEquals(SlotState.Empty, s.getSlotAt(3, 3));
    s.move(3, 1, 3, 3);
    assertEquals(SlotState.Marble, s.getSlotAt(3, 3));
    assertEquals(SlotState.Empty, s.getSlotAt(3, 1));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveFromEmpty() {
    try {
      MarbleSolitaireModel s = new EnglishSolitaireModel();
      s.move(3, 3, 1, 3);
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
      MarbleSolitaireModel s = new EnglishSolitaireModel();
      s.move(1, 1, 3, 1);
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
      MarbleSolitaireModel s = new EnglishSolitaireModel();
      s.move(4, 3, 2, 3);
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
      MarbleSolitaireModel s = new EnglishSolitaireModel();
      s.move(0, 3, 0, 1);
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
      MarbleSolitaireModel s = new EnglishSolitaireModel();
      s.move(6, 3, 3, 3);
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
      MarbleSolitaireModel s = new EnglishSolitaireModel();
      s.move(-4, -3, -1, -3);
    } catch (IllegalArgumentException i) {
      String output = "Move is invalid.";
      Assert.assertEquals(output, i.getMessage());
      throw i;
    }
    Assert.fail();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveDiagonal() {
    try {
      MarbleSolitaireModel s = new EnglishSolitaireModel();
      s.move(5, 3, 3, 3);
      s.move(4, 5, 4, 3);
      s.move(3, 3, 5, 3);
      s.move(6, 2, 4, 4);
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
      MarbleSolitaireModel s = new EnglishSolitaireModel();
      s.move(1, 3, 3, 3);
      s.move(0, 3, 2, 3);
    } catch (IllegalArgumentException i) {
      String output = "Move is invalid.";
      Assert.assertEquals(output, i.getMessage());
      throw i;
    }
    Assert.fail();
  }

  @Test
  public void testGetSlotAt() {
    MarbleSolitaireModel s = new EnglishSolitaireModel();
    assertEquals(SlotState.Empty, s.getSlotAt(3, 3));
    assertEquals(SlotState.Marble, s.getSlotAt(4, 3));
    assertEquals(SlotState.Invalid, s.getSlotAt(0, 0));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetSlotAtInvalid() {
    try {
      MarbleSolitaireModel s = new EnglishSolitaireModel();
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
    MarbleSolitaireModel s = new EnglishSolitaireModel();
    assertEquals(false, s.isGameOver());
    s.move(3, 5, 3, 3);
    s.move(3, 2, 3, 4);
    s.move(5, 3, 3, 3);
    s.move(5, 2, 3, 2);
    s.move(4, 0, 4, 2);
    s.move(2, 0, 4, 0);
    s.move(2, 1, 4, 1);
    s.move(2, 3, 2, 1);
    s.move(3, 3, 3, 1);
    s.move(0, 2, 2, 2);
    s.move(0, 3, 2, 3);
    s.move(4, 1, 4, 3);
    s.move(2, 1, 4, 1);
    s.move(4, 4, 4, 2);
    s.move(6, 4, 4, 4);
    s.move(6, 2, 6, 4);
    s.move(4, 5, 4, 3);
    s.move(4, 2, 4, 4);
    s.move(3, 4, 5, 4);
    s.move(1, 4, 3, 4);
    s.move(4, 0, 4, 2);
    s.move(2, 2, 2, 4);
    s.move(3, 4, 1, 4);
    s.move(6, 4, 4, 4);
    s.move(2, 6, 2, 4);
    s.move(4, 6, 2, 6);
    s.move(1, 4, 3, 4);
    s.move(4, 4, 2, 4);
    assertEquals(true, s.isGameOver());
  }

  @Test
  public void testGetBoardSize() {
    MarbleSolitaireModel s = new EnglishSolitaireModel();
    assertEquals(7, s.getBoardSize());

    MarbleSolitaireModel s2 = new EnglishSolitaireModel(5);
    assertEquals(13, s2.getBoardSize());

    MarbleSolitaireModel s3 = new EnglishSolitaireModel(7);
    assertEquals(19, s3.getBoardSize());
  }

  @Test
  public void testGetScore() {
    MarbleSolitaireModel s = new EnglishSolitaireModel();
    assertEquals(32, s.getScore());

    s.move(3, 5, 3, 3);
    assertEquals(31, s.getScore());

    s.move(3, 2, 3, 4);
    s.move(5, 3, 3, 3);
    s.move(5, 2, 3, 2);
    s.move(4, 0, 4, 2);
    s.move(2, 0, 4, 0);
    s.move(2, 1, 4, 1);
    s.move(2, 3, 2, 1);
    s.move(3, 3, 3, 1);
    s.move(0, 2, 2, 2);
    s.move(0, 3, 2, 3);
    s.move(4, 1, 4, 3);
    s.move(2, 1, 4, 1);
    s.move(4, 4, 4, 2);
    s.move(6, 4, 4, 4);
    s.move(6, 2, 6, 4);
    s.move(4, 5, 4, 3);
    s.move(4, 2, 4, 4);
    s.move(3, 4, 5, 4);
    s.move(1, 4, 3, 4);
    s.move(4, 0, 4, 2);
    s.move(2, 2, 2, 4);
    s.move(3, 4, 1, 4);
    s.move(6, 4, 4, 4);
    s.move(2, 6, 2, 4);
    s.move(4, 6, 2, 6);
    s.move(1, 4, 3, 4);
    s.move(4, 4, 2, 4);

    assertEquals(4, s.getScore());
  }
}