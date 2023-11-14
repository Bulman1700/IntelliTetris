// J.B.

// TestCase01.java
// ===============
// Builds tetris pieces and verifies that they are correct.
// Tests Piece.getPieces() and Piece.equals() methods.

package piecetestcases;

import tetris.*;

public class TestCase01
{
    public static void main(String [] args)
    {
        Test.enableDebug();
        Piece [] pieces = Piece.getPieces(); // Building Tetris Pieces
        boolean success = true;

        if (Test.checkDebug())
        {
            System.out.println("Building game pieces...");
        }

        // Testing that pieces array loaded correctly.
        success &= ((pieces != null && pieces.length == TetrisConstants.gamePieces.length) == true);

        if (Test.checkDebug())
        {
            if (!success)
            {
                System.out.println("Error: getPieces() failed to build pieces");
                System.exit(1);
            }
        }

        
        for (int i = 0; i < TetrisConstants.gamePieces.length; i++)
        {
            success &= (Test.testEquality(pieces[i], TetrisConstants.gamePieces[i])) == true;

            if (!success)
                break;
        }
        
        if (Test.checkDebug())
        {
            if (!success)
            {
                System.out.println("Error, game pieces are not correct.");
                System.exit(1);
            }
            else
                System.out.println("Pieces loaded successfully.");
        }

        if (success)
        System.out.println("Hooray!");
    else
        System.out.println("Fail whale :(");
    }
}
