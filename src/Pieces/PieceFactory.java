package Pieces;

public interface PieceFactory {

    public Pawn createPawn();

    public Rook createRook();

    public Knight createKnight();

    public Bishop createbishop();

    public Queen createQueen();

    public King createKing();

}
