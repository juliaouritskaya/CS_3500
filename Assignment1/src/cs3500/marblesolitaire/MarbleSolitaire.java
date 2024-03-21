package cs3500.marblesolitaire;

import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.TriangleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;
import java.io.InputStreamReader;

public final class MarbleSolitaire {
  public static void main(String[] args){
    int type = 0;
    int size = 3;
    int sRow = 3;
    int sCol = 3;

    int nxtArg = 0;
    if(args.length > nxtArg){
      if(args[nxtArg].equalsIgnoreCase("english")){
        type = 1;
      } else if(args[nxtArg].equalsIgnoreCase("european")){
        type = 2;
      } else if(args[nxtArg].equalsIgnoreCase("triangular")){
        type = 3;
        size = 5;
        sRow = 1;
        sCol = 1;
      } else{
        //first parameter invalid
      }
    }else{
      //missing argument
    }

    nxtArg = 1;
    while(nxtArg < args.length){
      if(args.length > nxtArg + 1){
        if(args[nxtArg].equalsIgnoreCase("-size")){
          size = Integer.parseInt(args[nxtArg + 1]);
          nxtArg += 2;
        } else if(args[nxtArg].equalsIgnoreCase("-hole") && args.length > nxtArg + 2){
          sRow = Integer.parseInt(args[nxtArg + 1]);
          sCol = Integer.parseInt(args[nxtArg + 2]);
          nxtArg += 3;
        }else{
          //invalid optional parameters
        }
      }else{
        //not enough parameters
      }
    }

    MarbleSolitaireModel model;
    MarbleSolitaireView view;
    switch(type){
      case 1:
        model = new EnglishSolitaireModel(size, sRow, sCol);
        view = new MarbleSolitaireTextView(model);
        break;
      case 2:
        model = new EuropeanSolitaireModel(size, sRow, sCol);
        view = new MarbleSolitaireTextView(model);
        break;
      case 3:
        model = new TriangleSolitaireModel(size, sRow, sCol);
        view = new TriangleSolitaireTextView(model);
        break;
      default:
        model = new EnglishSolitaireModel();
        view = new MarbleSolitaireTextView(model);
        break;
      }

    System.out.println(view.toString());
    Readable myIn = new InputStreamReader(System.in);
    MarbleSolitaireControllerImpl instance = new MarbleSolitaireControllerImpl(model, view, myIn);
    instance.playGame();
  }
}