package cit230.pkgfinal;

public abstract class Piece {
    
    boolean rPiece;
    boolean bPiece;
    
    public Piece(boolean rPiece, boolean bPiece) {
        this.rPiece = rPiece;
        this.bPiece = bPiece;
    }

    public abstract void placePiece();

    public abstract boolean direction(int posX, int posY, int toPosX, int toPosY);
    
    public abstract int value();
}
