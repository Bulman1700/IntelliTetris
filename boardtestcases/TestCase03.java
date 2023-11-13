package boardtestcases;

import tetris.*;

public class TestCase03
{
    public static void main(String [] args)
    {
        Test.setWidth(6);
        Test.setHeight(20);

        Board b = new Board(Test.getWidth(), Test.getHeight());
        Piece [] p = Piece.getPieces();

        b.place(p[TetrisConstants.SQUARE], 4, 0);
        b.commit();
        // b.printBoard();
        b.place(p[TetrisConstants.L1].fastRotation(), 1, 0);
        b.commit();
        // b.printBoard();
        b.place(p[TetrisConstants.S2].fastRotation(), 0, 0);
        b.commit();
        b.printBoard();
        b.clearRows();
        // b.printBoard();

    }   
}