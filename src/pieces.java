
import javalib.impworld.World;
import javalib.impworld.WorldScene;
import javalib.worldimages.*;

import java.awt.*;
import java.util.ArrayList;





abstract class Piece{
 boolean revealed = false;
 Posn loc;
 // represents who this piece belongs to
 boolean white;

  public Piece(boolean revealed, Posn loc, boolean white) {
    this.revealed = revealed;
    this.loc = loc;
    this.white = white;
  }


  void reveal() {
   this.revealed = true;
 }

  abstract ArrayList<Posn> availableMoves(int width, int height, ArrayList<Piece> occupied);
  abstract WorldImage drawMe();

}


class Queen extends Piece {

  public Queen(boolean revealed, Posn loc, boolean white) {
    super(revealed, loc, white);
  }

  ArrayList<Posn> availableMoves(int width, int height, ArrayList<Piece> occupied) {
    ArrayList<Posn> results = new ArrayList<>();

    // the biggest hack of all time !!!!!
    results.addAll(new Rook(true, this.loc, this.white).availableMoves(width, height, occupied));
    results.addAll(new Bishop(true, this.loc, this.white).availableMoves(width, height, occupied));

    return results;
  }


  WorldImage drawMe() {
    Color c = Color.WHITE;
    if(!white) {
      c = Color.BLACK;
    }
    if(!revealed) {
      return new TextImage("U", Color.LIGHT_GRAY);
    }
    return new TextImage("Q", c);
  }
}




class ChessGame extends World {
  int w = 8;
  int h = 8;
  ArrayList<Piece> occupiedSlots;
  boolean whiteTurn;

  public ChessGame(int w, int h, int numPieces) {
    this.w = w;
    this.h = h;
    if(numPieces % 2 != 0 || numPieces > (w * h)) {
      throw new IllegalArgumentException("Bad piece Count !!!!!!!!!");
    }

    occupiedSlots = new ArrayList<>();
    whiteTurn = true;


    // generate an arraylist of pieces
    Utils u = new Utils();
    int nump = numPieces;
    boolean player = false;
    
    while (nump > 0) {
      int x = (int)(Math.random() * w);
      int y = (int)(Math.random() * h);
      Posn p = new Posn(x,y);
      if(!u.contains(p, occupiedSlots)) {
        int type = (int)(Math.random() * 3);
        if(type == 0) {
          occupiedSlots.add(new Queen(false, p, player));
        }
        else if(type == 1) {
          occupiedSlots.add(new Rook(false, p, player));
        }
        else if(type == 2) {
          occupiedSlots.add(new Bishop(false, p, player));
        }
        player = !player;
        nump --;
      }
    }
    
  }


  public void onKeyEvent(String s) {
    System.out.println(occupiedSlots);
  }

  public WorldScene makeScene() {
    int s = 100;
    WorldScene ws = new WorldScene(w * s, h * s);


    WorldImage blackBox = new RectangleImage(s, s, OutlineMode.OUTLINE, Color.BLACK);
    WorldImage bg = new RectangleImage(w * s, h * s, OutlineMode.SOLID, Color.RED);

    ws.placeImageXY(bg, w * s/2, h * s/2);

    for (int i = 0; i < h; i++) {
      for (int j = 0; j < w; j++) {
        ws.placeImageXY(blackBox, j * s, i * s);
      }
    }

    for (Piece p: occupiedSlots) {
      ws.placeImageXY(p.drawMe(), p.loc.x * s,p.loc.y * s);
    }

    return ws;
  }
}
