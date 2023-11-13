package piecetestcases;

import tetris.*;

// Verifies that Piece.java builds all tetris pieces and 'next' references are set and not null.
public class TestCase01
{

    public static void main(String [] args)
    {
        Test.enableDebug();
        Piece [] pieces = Piece.getPieces(); // Building Tetris Pieces
        boolean success = true;

        success &= ((pieces != null && pieces.length == TetrisConstants.gamePieces.length) == true);

        if (Test.checkDebug())
        {
            if (!success)
            {
                System.out.println("Error: getPieces() failed to build pieces");
                System.exit(1);
            }
        }

        // Test that all pieces are in the array and that their 'next' references are not null.
        for (int i = 0; i < TetrisConstants.gamePieces.length; i++)
            success &= (Test.testEquality(pieces[i], TetrisConstants.gamePieces[i]) && pieces[i].fastRotation() != null) == true;
        
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
