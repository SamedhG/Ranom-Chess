import javalib.worldimages.Posn;
import javalib.worldimages.TextImage;
import javalib.worldimages.WorldImage;
import java.awt.*;
import java.util.ArrayList;


class Rook extends Piece {

  public Rook(boolean revealed, Posn loc, boolean white) {
    super(revealed, loc, white);
  }

  ArrayList<Posn> availableMoves(int w, int h, ArrayList<Piece> occupied) {
    ArrayList<Posn> results = new ArrayList<>();
    Utils u = new Utils();
    //check everything in the y direction downwards
    for (int i = this.loc.y; i < h; i++) {
      Posn p = new Posn(this.loc.x, i);
      if (u.contains(p, occupied)) {
        if(u.getPiece(p, occupied).revealed) {
          results.add(p);
        }
        break;
      }
      else {
        results.add(p);
      }
    }
    //check everything in the y direction upwards
    for (int i = this.loc.y; i >= 0; i--) {
      Posn p = new Posn(this.loc.x, i);
      if (u.contains(p, occupied)) {
        if(u.getPiece(p, occupied).revealed) {
          results.add(p);
        }
        break;
      }
      else {
        results.add(p);
      }
    }


    //check everything in the x direction rightwards
    for (int i = this.loc.x; i < w; i++) {
      Posn p = new Posn(i, this.loc.y);
      if (u.contains(p, occupied)) {
        if(u.getPiece(p, occupied).revealed) {
          results.add(p);
        }
        break;
      }
      else {
        results.add(p);
      }
    }
    //check everything in the x direction leftwards
    for (int i = this.loc.x; i >= 0 ; i--) {
      Posn p = new Posn(i, this.loc.y);
      if (u.contains(p, occupied)) {
        if(u.getPiece(p, occupied).revealed) {
          results.add(p);
        }
        break;
      }
      else {
        results.add(p);
      }
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

    return new TextImage("R", c);
  }

}