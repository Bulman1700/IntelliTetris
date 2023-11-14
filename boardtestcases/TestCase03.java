// JB

// TestCase03.java
// ===============
// Tests various return values for Board.place().

package boardtestcases;

import tetris.*;

public class TestCase03
{
  // Temporarily place a piece on the board.
  public static int place(Piece p, Board b, int x, int y)
  {
    // Place the piece.
    int retval = b.place(TetrisConstants.gamePieces[0], x, y);
    // Undo piece placement.
    b.undo();

    // Return place() value.
    return retval;
  }

  // Permenantly place a piece on the board.
  public static int place_and_commit(Piece p, Board b, int x, int y)
  {
    // Place the piece.
    int retval = b.place(TetrisConstants.gamePieces[0], x, y);
    // Commit piece placement.
    b.commit();

    // Return place() value.
    return retval;
  }

  public static void main(String [] args)
  {
    Test.enableDebug();
    Test.setWidth(10);
    Test.setHeight(10);

    boolean success = true;
    int retval;

    // Build Board.
    Board b = new Board(Test.getWidth(), Test.getHeight());
    Piece p = TetrisConstants.gamePieces[0]; // Vertical Stick Piece.

    // Testing piece placement outside of board completely.
    if (Test.debug())
      Test.printDebugMsg("Placing Piece outside of bounds..", "", success);

    retval = place(p, b, b.getWidth() + 5, b.getHeight() + 5);
    success &= (retval == BoardConsts.PLACE_OUT_BOUNDS);

    if (Test.debug())
    {
      Test.printDebugMsg("Recognized that piece is out of bounds. PASS!", "Error: returned: " + retval, success);
      Test.printDebugMsg("Placing Piece outside of bounds..", "", success);
    }

    // Testing piece that is out of bounds vertically
    retval = place(p, b, b.getWidth() - 5, b.getHeight() - 1);
    success &= (retval == BoardConsts.PLACE_OUT_BOUNDS);

    if (Test.debug())
    {
      Test.printDebugMsg("Recognized that piece is out of bounds. PASS!", "Error: returned: " + retval, success);
      Test.printDebugMsg("Placing Piece outside of bounds..", "", success);
    }

    // Testing piece that is out of bounds horizontally
    retval = place(p, b, b.getWidth(), b.getHeight() - 5);
    success &= (retval == BoardConsts.PLACE_OUT_BOUNDS);

    if (Test.debug())
    {
      Test.printDebugMsg("Recognized that piece is out of bounds. PASS!", "Error: returned: " + retval, success);
      Test.printDebugMsg("Placing Piece correctly..", "", success);
    }

    // Testing piece that is placed correctly.
    retval = place_and_commit(p, b, b.getWidth() - 5, b.getHeight() - 5);
    success &= (retval == BoardConsts.PLACE_OK);

    if (Test.debug())
    {
      Test.printDebugMsg("Recognized that piece is placed correctly. PASS!", "Error: returned: " + retval, success);
      Test.printDebugMsg("Placing Piece on top of another piece..", "", success);
    }

    // Testing piece that is placed on top of another piece.
    retval = place(p, b, b.getWidth() - 5, b.getHeight() - 5);
    success &= (retval == BoardConsts.PLACE_BAD);

    if (Test.debug())
    {
      Test.printDebugMsg("Recognized that piece is on top of another piece. PASS!", "Error: returned: " + retval, success);
      Test.printDebugMsg("Placing Pieces until bottom row is filled..", "", success);
    }

    // Place pieces until bottom row of board is full.
    for (int i = 0; i < b.getWidth(); i++)
      retval = place_and_commit(p, b, i, 0);

    success &= (retval == BoardConsts.PLACE_ROW_FILLED);

    if (Test.debug())
      Test.printDebugMsg("Recognized that a row is full. PASS!", "Error: returned: " + retval, success);


    if (success)
      System.out.println("Hooray!");
    else
      System.out.println("failwhale");
  }
}
