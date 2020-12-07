package cit230.pkgfinal;

public class Red extends Piece {

    public Red(boolean rPiece, boolean bPiece) {
        super(rPiece, bPiece);
    }

    public boolean Red(boolean rPiece) {
        return true;
    }

    @Override
    public void placePiece() {
        if (this.rPiece) {
            System.out.print(Color.RED + " R " + Color.RESET);
        }
    }

    @Override
    public boolean direction(int sRow, int sCol, int dRow, int dCol) {
        if (this.rPiece) {
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
