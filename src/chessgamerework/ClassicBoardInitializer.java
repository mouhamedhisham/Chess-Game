package ChessCore;

import Pieces.Knight;
import Pieces.Rook;
import Pieces.Piece;
import Pieces.King;
import Pieces.Bishop;
import Pieces.BlackPieceFactory;
import Pieces.Pawn;
import Pieces.Queen;
import Pieces.WhitePieceFactory;

public final class ClassicBoardInitializer implements BoardInitializer {
    private static final BoardInitializer instance = new ClassicBoardInitializer();

    private ClassicBoardInitializer() {
    }

    public static BoardInitializer getInstance() {
        return instance;
    }

    @Override
    public Piece[][] initialize() {
        WhitePieceFactory whiteFactory = new WhitePieceFactory();
        BlackPieceFactory blackFactory = new BlackPieceFactory();
        Piece[][] initialState = {
            
            {whiteFactory.createRook(), whiteFactory.createKnight(),whiteFactory.createbishop(), whiteFactory.createQueen(), whiteFactory.createKing(), whiteFactory.createbishop(), whiteFactory.createKnight(), whiteFactory.createRook()},
            {whiteFactory.createPawn(),whiteFactory.createPawn(),whiteFactory.createPawn(),whiteFactory.createPawn(),whiteFactory.createPawn(),whiteFactory.createPawn(),whiteFactory.createPawn(),whiteFactory.createPawn()},
            {null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null},
            {blackFactory.createPawn(),blackFactory.createPawn(),blackFactory.createPawn(),blackFactory.createPawn(),blackFactory.createPawn(),blackFactory.createPawn(),blackFactory.createPawn(),blackFactory.createPawn(),},
            {blackFactory.createRook(), blackFactory.createKnight(),blackFactory.createbishop() , blackFactory.createQueen(), blackFactory.createKing(), blackFactory.createbishop(),blackFactory.createKnight(),blackFactory.createRook()}
        };
        return initialState;
    }
}
