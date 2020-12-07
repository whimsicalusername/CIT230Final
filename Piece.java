package cit230.pkgfinal;

public abstract class Piece {
    
    boolean rPiece;
    boolean bPiece;
    
    public Piece(boolean rPiece, boolean bPiece) {
        this.rPiece = rPiece;
        this.bPiece = bPiece;
    }

    public abstract void placePiece();

    public abstract boolean direction(int sRow, int sCol, int dRow, int dCol);
    
    public abstract int value();
}
