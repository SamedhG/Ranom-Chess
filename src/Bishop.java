import javalib.worldimages.Posn;
import javalib.worldimages.TextImage;
import javalib.worldimages.WorldImage;
import java.awt.*;
import java.util.ArrayList;

class Bishop extends Piece {

  public Bishop(boolean revealed, Posn loc, boolean white) {
    super(revealed, loc, white);
  }



  ArrayList<Posn> availableMoves(int w, int h, ArrayList<Piece> occupied) {
    ArrayList<Posn> results = new ArrayList<>();
    Utils u = new Utils();


    //check everything in the upLeft direction
    int curX = this.loc.x;
    int curY = this.loc.y;

    while ( curX >= 0 &&
            curY >= 0 &&
            !u.contains(new Posn(curX, curY), occupied)) {
      results.add(new Posn(curX, curY));
      curX -= 1;
      curY -= 1;
    }

    Posn p = new Posn(curX, curY);
    if(u.getPiece(p, occupied).revealed) {
      results.add(p);
    }

    //check everything in the upRight direction
    curX = this.loc.x;
    curY = this.loc.y;

    while ( curX < w &&
            curY >= 0 &&
            !u.contains(new Posn(curX, curY), occupied)) {
      results.add(new Posn(curX, curY));
      curX += 1;
      curY -= 1;
    }

    p = new Posn(curX, curY);
    if(u.getPiece(p, occupied).revealed) {
      results.add(p);
    }

    //check everything in the downRight direction
    curX = this.loc.x;
    curY = this.loc.y;

    while ( curX < w &&
            curY < h &&
            !u.contains(new Posn(curX, curY), occupied)) {
      results.add(new Posn(curX, curY));
      curX += 1;
      curY += 1;
    }

    p = new Posn(curX, curY);
    if(u.getPiece(p, occupied).revealed) {
      results.add(p);
    }


    //check everything in the downLeft direction
    curX = this.loc.x;
    curY = this.loc.y;

    while ( curX >= 0 &&
            curY < h &&
            !u.contains(new Posn(curX, curY), occupied)) {
      results.add(new Posn(curX, curY));
      curX -= 1;
      curY += 1;
    }

    p = new Posn(curX, curY);
    if(u.getPiece(p, occupied).revealed) {
      results.add(p);
    }


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
    
    return new TextImage("B", c);
  }
}