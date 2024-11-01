package Pieces;

import ChessCore.Player;

public class WhitePieceFactory implements PieceFactory{

    @Override
    public Pawn createPawn() {
        return new Pawn(Player.WHITE);
    }

    @Override
    public Rook createRook() {
        return new Rook(Player.WHITE);
    }

    @Override
    public Knight createKnight() {
        return new Knight(Player.WHITE);
    }

    @Override
    public Bishop createbishop() {
        return new Bishop(Player.WHITE);
    }

    @Override
    public Queen createQueen() {
        return new Queen(Player.WHITE);
    }

    @Override
    public King createKing() {
        return new King(Player.WHITE);
    }

}
