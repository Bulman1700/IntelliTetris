package piecetestcases;

import tetris.*;


// Tests the rotation of each peice and verifies that they match the hard coded values in TetrisConstants.java.
// This will inherently test the equals() method in Piece.java as well.

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
    Test.disableDebug();
    Test.setRotationCnt(100);
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
      }

      for (int j = 0; j < Test.getRotationCnt(); j++)
      {
        testPiece = testPiece.fastRotation();

        // Conditions where rotated piece matches original piece.
        if ((i == 0 || i == 3 || i == 4) && (j % Test.getRotationCnt()) % 4 == 1)
        {
          success &= (Test.testEquality(TetrisConstants.gamePieces[i], testPiece) == true);
          
          if (Test.checkDebug())
          {
            if (!success)
              errMsg(i, j);
            else
              Test.printPiece(testPiece);
          }
        }

        else if (i == 5)
        {
          success &= (Test.testEquality(TetrisConstants.gamePieces[i], testPiece) == true);
          
          if (Test.checkDebug())
          {
            if (!success)
              errMsg(i, j);
            else
              Test.printPiece(testPiece);
          }
        }

        else if ((j % Test.getRotationCnt()) % 4 == 3)
        {
          success &= (Test.testEquality(TetrisConstants.gamePieces[i], testPiece) == true);
          
          if (Test.checkDebug())
          {
            if (!success)
              errMsg(i, j);
            else
              Test.printPiece(testPiece);
          }
        }

        else
        {
          success &= (Test.testEquality(TetrisConstants.gamePieces[i], testPiece) == false);
          
          if (Test.checkDebug())
          {
            if (!success)
              errMsg(i, j);
            else
              Test.printPiece(testPiece);
          }
        }  
      }

      if (Test.checkDebug())
        System.out.println("Pass!\n");
     }

    
    if (success)
      System.out.println("Hooray!");
    else
      System.out.println("Fail whale");
   }
}
