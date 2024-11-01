/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package frontend;

import ChessCore.BoardFile;
import ChessCore.BoardRank;
import ChessCore.ClassicChessGame;
import ChessCore.GameStatus;
import ChessCore.Move;
import ChessCore.Square;
import Pieces.King;
import Pieces.Piece;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author elsam
 */
public class ChessBoard extends javax.swing.JPanel {

    private JButton[][] chessButtons;
    private Piece selectedPiece = null;
    private ClassicChessGame game = new ClassicChessGame();
    private Piece board[][];
    private Square fromSquare = null;

    public ChessBoard() {
        setLayout(new GridLayout(8, 8));

        addButtons();
        paintButtons();

    }

    public void addButtons() {
        chessButtons = new JButton[8][8];
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                chessButtons[row][col] = new JButton();
                chessButtons[row][col].setPreferredSize(new Dimension(75, 75));

                try {
                    setPieceIcon(row, col);
                } catch (IOException ex) {
                    Logger.getLogger(ChessBoard.class.getName()).log(Level.SEVERE, null, ex);
                }

                int curRow = row;
                int curCol = col;

                chessButtons[row][col].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            buttonClicked(curRow, curCol);
                        } catch (IOException ex) {
                            Logger.getLogger(ChessBoard.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });

                add(chessButtons[row][col]);
            }
        }
    }

    public void paintButtons() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if ((row + col) % 2 == 0) {
                    chessButtons[row][col].setBackground(Color.WHITE);
                } else {
                    chessButtons[row][col].setBackground(Color.BLACK);
                }
            }
        }
    }

    public void setPieceIcon(int row, int col) throws IOException {
        Piece pieceToSetIcon = game.getPieceAtSquare(new Square((BoardFile.values()[col]), (BoardRank.values()[row])));
        if (pieceToSetIcon != null) {

            if (pieceToSetIcon instanceof Pieces.Rook) {
                Image img = ImageIO.read(getClass().getResource("/ChessImages/" + pieceToSetIcon.getOwner() + "Rook.png")).getScaledInstance(75, 75, Image.SCALE_SMOOTH);
                chessButtons[row][col].setIcon(new ImageIcon(img));

            } else if (pieceToSetIcon instanceof Pieces.Knight) {
                Image img = ImageIO.read(getClass().getResource("/ChessImages/" + pieceToSetIcon.getOwner() + "Knight.png")).getScaledInstance(75, 75, Image.SCALE_SMOOTH);
                chessButtons[row][col].setIcon(new ImageIcon(img));
            } else if (pieceToSetIcon instanceof Pieces.Bishop) {
                Image img = ImageIO.read(getClass().getResource("/ChessImages/" + pieceToSetIcon.getOwner() + "Bishop.png")).getScaledInstance(75, 75, Image.SCALE_SMOOTH);
                chessButtons[row][col].setIcon(new ImageIcon(img));
            } else if (pieceToSetIcon instanceof Pieces.Queen) {
                Image img = ImageIO.read(getClass().getResource("/ChessImages/" + pieceToSetIcon.getOwner() + "Queen.png")).getScaledInstance(75, 75, Image.SCALE_SMOOTH);
                chessButtons[row][col].setIcon(new ImageIcon(img));
            } else if (pieceToSetIcon instanceof Pieces.King) {
                Image img = ImageIO.read(getClass().getResource("/ChessImages/" + pieceToSetIcon.getOwner() + "King.png")).getScaledInstance(75, 75, Image.SCALE_SMOOTH);
                chessButtons[row][col].setIcon(new ImageIcon(img));
            } else if (pieceToSetIcon instanceof Pieces.Pawn) {
                Image img = ImageIO.read(getClass().getResource("/ChessImages/" + pieceToSetIcon.getOwner() + "Pawn.png")).getScaledInstance(75, 75, Image.SCALE_SMOOTH);
                chessButtons[row][col].setIcon(new ImageIcon(img));
            }
        } else {
            chessButtons[row][col].setIcon(null);
        }

    }


    public void buttonClicked(int row, int col) throws IOException {
        paintButtons();
        if (selectedPiece != null) {
            if (chessButtons[row][col].getIcon() == null
                    || game.getPieceAtSquare(new Square((BoardFile.values()[col]), (BoardRank.values()[row]))).getOwner() != selectedPiece.getOwner()) {

                
                Square toSquare = new Square((BoardFile.values()[col]), (BoardRank.values()[row]));
                game.makeMove(new Move(fromSquare, toSquare)); 

                    if (game.getGameStatus() == GameStatus.WHITE_UNDER_CHECK || game.getGameStatus() == GameStatus.BLACK_UNDER_CHECK) {
                        for (BoardFile file : BoardFile.values()) {
                            for (BoardRank rank : BoardRank.values()) {
                                Piece p = game.getPieceAtSquare(new Square(file, rank));
                                if (p instanceof King && p.getOwner() != selectedPiece.getOwner()) {
                                    chessButtons[rank.getValue()][file.getValue()].setBackground(Color.RED);
                                }
                            }
                        }
                    } else if (game.getGameStatus() == GameStatus.WHITE_WON) {
                        JOptionPane.showMessageDialog(this, "White Won");
                    } else if (game.getGameStatus() == GameStatus.BLACK_WON) {
                        JOptionPane.showMessageDialog(this, "Black WON");
                    } else if (game.getGameStatus() == GameStatus.STALEMATE || game.getGameStatus() == GameStatus.INSUFFICIENT_MATERIAL) {
                        JOptionPane.showMessageDialog(this, "DRAW");
                    }

                

                for (int r = 0; r < 8; r++) {
                    for (int c = 0; c < 8; c++) {
                        setPieceIcon(r, c);
                    }
                }
                selectedPiece = null;

            }

        } else {
            selectedPiece = game.getPieceAtSquare(new Square((BoardFile.values()[col]), (BoardRank.values()[row])));
            fromSquare = new Square((BoardFile.values()[col]), (BoardRank.values()[row]));

            List<Square> validMoves = game.getAllValidMovesFromSquare(fromSquare);
            System.out.println(validMoves);

            for (var move : validMoves) {
                int c = move.getFile().getValue();
                int r = move.getRank().getValue();
                chessButtons[r][c].setBackground(Color.GRAY);
            }

        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        button1 = new java.awt.Button();

        button1.setLabel("button1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button button1;
    // End of variables declaration//GEN-END:variables
}
