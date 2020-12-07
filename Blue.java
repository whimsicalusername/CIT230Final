package cit230.pkgfinal;

public class Blue extends Piece {

    public Blue(boolean rPiece, boolean bPiece) {
        super(rPiece, bPiece);
    }

    public boolean Blue(boolean bPiece) {
        return true;
    }

    @Override
    public void placePiece() {
        if (this.bPiece) {
            System.out.print(Color.BLUE + " B " + Color.RESET);
        }
    }

    @Override
    public boolean direction(int sRow, int sCol, int dRow, int dCol) {
        if (this.bPiece) {
            return ((sRow == dRow) && sCol == (dCol - 1));
        } else {
            return false;
        }
    }

    @Override
    public int value() {
        return 1;
    }
}
