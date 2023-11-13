package piecetestcases;

import tetris.*;

// Tests that the dimensions of each piece are correct.
public class TestCase03
{
    public static void main(String [] args)
    {
        Test.disableDebug();
        Test.setRotationCnt(100);
        Piece [] pieces = Piece.getPieces(); // Building Tetris Pieces.
        boolean success = true;

        for (int i = 0; i < TetrisConstants.gamePieces.length; i++)
            success &= (pieces[i].getWidth() == pieces[i].getSkirt().length);
        
        if (success)
            System.out.println("Hooray!");
    }
}
