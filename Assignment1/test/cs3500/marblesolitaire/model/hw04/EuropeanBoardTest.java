package cs3500.marblesolitaire.model.hw04;

import org.junit.Test;

import cs3500.marblesolitaire.model.hw02.Board;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState.SlotState;

import static org.junit.Assert.assertEquals;

/**
 * This class represents the tests for the european board class.
 */
public class EuropeanBoardTest {
  @Test
  public void testEuropeanBoardConstructor() {
    MarbleSolitaireModel s = new EuropeanSolitaireModel();
    EuropeanBoard b = new EuropeanBoard(3, 3, 3);
    assertEquals(7, s.getBoardSize());
    int expectedArmThickness = 3;
    assertEquals(expectedArmThickness, s.getBoardSize() - 4);

    assertEquals(SlotState.Empty, s.getSlotAt(3, 3));
  }

  @Test
  public void testCreateEuropeanBoard1() {
    MarbleSolitaireModel s = new EuropeanSolitaireModel();
    EuropeanBoard b = new EuropeanBoard(3, 3, 3);
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
  public void testInBounds() {
  }
}