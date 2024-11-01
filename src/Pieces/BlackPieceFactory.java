package Pieces;

import ChessCore.Player;

public class BlackPieceFactory implements PieceFactory {

    @Override
    public Pawn createPawn() {
        return new Pawn(Player.BLACK);
    }

    @Override
    public Rook createRook() {
        return new Rook(Player.BLACK);
    }

    @Override
    public Knight createKnight() {
        return new Knight(Player.BLACK);
    }

    @Override
    public Bishop createbishop() {
        return new Bishop(Player.BLACK);
    }

    @Override
    public Queen createQueen() {
        return new Queen(Player.BLACK);
    }

    @Override
    public King createKing() {
        return new King(Player.BLACK);
    }

}
