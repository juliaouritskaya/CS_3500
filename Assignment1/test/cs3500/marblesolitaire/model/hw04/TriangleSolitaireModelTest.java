package cs3500.marblesolitaire.model.hw04;

import org.junit.Test;

import java.io.StringReader;

import cs3500.marblesolitaire.controller.MarbleSolitaireController;
import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireView;
import cs3500.marblesolitaire.view.TriangleSolitaireTextView;

import static org.junit.Assert.assertEquals;

public class TriangleSolitaireModelTest {
  @Test
  public void winGameTriangle() throws Exception {
    Appendable a =  new StringBuilder();
    Readable r = new StringReader("3 1 1 1 5 1 3 1 4 3 2 1 2 1 4 1 4 1 4 3 " +
            "4 4 4 2 5 3 3 1 2 2 4 4 5 5 3 3 q");
    MarbleSolitaireModel model = new TriangleSolitaireModel();
    MarbleSolitaireView model2 = new TriangleSolitaireTextView(model, a) ;
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, model2, r);
    controller.playGame();
    assertEquals("\n" +
            "    O\n" +
            "   _ O\n" +
            "  _ O O\n" +
            " O O O O\n" +
            "O O O O O" +
            "\n" +
            "Score: 13\n"
            + "\n" +
            "    O\n" +
            "   _ O\n" +
            "  O O O\n" +
            " _ O O O\n" +
            "_ O O O O" +
            "\n" +
            "Score: 12\n"
            +"\n" +
            "    O\n" +
            "   O O\n" +
            "  O _ O\n" +
            " _ O _ O\n" +
            "_ O O O O" +
            "\n" +
            "Score: 11\n"
            + "\n" +
            "    O\n" +
            "   _ O\n" +
            "  _ _ O\n" +
            " O O _ O\n" +
            "_ O O O O" +
            "\n" +
            "Score: 10\n"
            +"\n" +
            "    O\n" +
            "   _ O\n" +
            "  _ _ O\n" +
            " _ _ O O\n" +
            "_ O O O O" +
            "\n" +
            "Score: 9\n"
            +
            "\n" +
            "    O\n" +
            "   _ O\n" +
            "  _ _ O\n" +
            " _ O _ _\n" +
            "_ O O O O" +
            "\n" +
            "Score: 8\n"
            +
            "\n" +
            "    O\n" +
            "   _ O\n" +
            "  O _ O\n" +
            " _ _ _ _\n" +
            "_ O _ O O" +
            "\n" +
            "Score: 7\n"
            +
            "\n" +
            "    O\n" +
            "   _ _\n" +
            "  O _ _\n" +
            " _ _ _ O\n" +
            "_ O _ O O" +
            "\n" +
            "Score: 6\n"
            +
            "\n" +
            "    O\n" +
            "   _ _\n" +
            "  O _ O\n" +
            " _ _ _ _\n" +
            "_ O _ O _" +
            "\n" +
            "Score: 5\n"
            +
            "Game over!\n"
            +
            "    O\n" +
            "   _ _\n" +
            "  O _ O\n" +
            " _ _ _ _\n" +
            "_ O _ O _" +
            "\n" +
            "Score: 5", a.toString());
  }

}