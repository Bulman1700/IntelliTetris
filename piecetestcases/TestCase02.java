// J.B.

// TestCase02.java
// ===============
// Tests Piece.fastRotation() of each peice and verifies that they match the hard coded values in TetrisConstants.java.
// This will inherently test the equals() method in Piece.java as well. 

// A beast of a test case.

package piecetestcases;

import tetris.*;

public class TestCase02
{
  // States the piece and rotation number error orrcured at.
  public static void errMsg(int piece, int rotation)
  {
      System.out.println("Error: Wrong rotation on piece: " + piece + ", rotation: " + rotation);
      System.exit(1);
  }

  public static void main(String [] args)
  {
    Test.enableDebug();
    Test.setRotationCnt(5);
    Piece [] pieces = Piece.getPieces(); // Building Tetris Pieces.
    boolean success = true;

    // Looping through every piece in getPieces() and comparing them to hard coded 'gamePieces'.
    for (int i = 0; i < TetrisConstants.gamePieces.length; i++)
    {
      Piece testPiece = pieces[i];
      
      if (Test.checkDebug())
      {
        System.out.println("Piece[" + i + "]");
        Test.printPiece(testPiece);
        System.out.println();
      }

      // Loop through 'n' rotations of each peice and check which ones match the original piece.
      for (int j = 0; j < Test.getRotationCnt(); j++)
      {
        testPiece = testPiece.fastRotation(); // testPiece's 'next' reference.
        
        if (Test.checkDebug())
        {
          System.out.println("Rotating piece counter-clockwise...");
          Test.printPiece(testPiece);
        }

        // Conditions where rotated piece matches original piece.
        if ((i == 0 || i == 3 || i == 4) && ((j % Test.getRotationCnt()) % 4 == 1))
        {
          // Check if rotated piece matches piece[i]
          success &= (Test.testEquality(TetrisConstants.gamePieces[i], testPiece) == true);
          
          if (Test.checkDebug())
          {
            if (!success)
            {
              errMsg(i, j);
            }
            else
            {
              System.out.println("matches piece[" + i + "]");
            }
          }
        }

        // Conditions where rotated piece matches original piece.
        else if (i == 5)
        {
          // Check if rotated piece matches piece[i]
          success &= (Test.testEquality(TetrisConstants.gamePieces[i], testPiece) == true);
          
          if (Test.checkDebug())
          {
            if (!success)
            {
              errMsg(i, j);
            }
            else
            {
              System.out.println("matches piece[" + i + "]");
            }
          }
        }

        // Conditions where rotated piece matches original piece.
        else if ((j % Test.getRotationCnt()) % 4 == 3)
        {
          // Check if rotated piece matches piece[i]
          success &= (Test.testEquality(TetrisConstants.gamePieces[i], testPiece) == true);
          
          if (Test.checkDebug())
          {
            if (!success)
            {
              errMsg(i, j);
            }
            else
            {
              System.out.println("matches piece[" + i + "]");
            }
          }
        }

        // All other conditions where rotated piece does not match original piece.
        else
        {
          success &= (Test.testEquality(TetrisConstants.gamePieces[i], testPiece) == false);
          
          if (Test.checkDebug())
          {
            if (!success)
            {
              errMsg(i, j);
            }
          }
        }
        
        System.out.println();
      }

      // Print status of each original piece[i]
      if (Test.checkDebug())
      {
        System.out.println("Pass!\n");
      }
     }

    
    if (success)
      System.out.println("Hooray!");
    else
      System.out.println("Fail whale");
   }
}
