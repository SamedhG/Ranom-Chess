import javalib.worldimages.Posn;

import java.util.ArrayList;

class Utils {

  // checks if a posn is contained in an array list of pieces
  boolean contains(Posn pos, ArrayList<Piece> arr) {
    for (Piece p : arr) {
      if(p.loc.equals(pos)){
        return true;
      }
    }
    return false;
  }

  Piece getPiece(Posn pos, ArrayList<Piece> arr) {
    for (Piece p : arr) {
      if(p.loc.equals(pos)){
        return p;
      }
    }
    throw new RuntimeException("fuck you");
  }


}
