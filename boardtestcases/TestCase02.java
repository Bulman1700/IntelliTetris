// JB

// TestCase02.java
// ===============
// Verifies that a piece can be placed on the board.
// Tests Board.commit() and Board.undo().

package boardtestcases;

import tetris.*;

public class TestCase02
{
  // Gets current state of the board.
  public static boolean getCommitStatus(Board b)
  {
    return b.getCommitStatus();
  }

// Linear search for piece in the board.
// Returns true if found, false otherwise.
  public static boolean checkBoard(Board b)
  {
    for (int i = 0; i < b.getWidth(); i++)
      for (int j = 0; j < b.getHeight(); j++)
        if (b.getGrid(i, j))
          return true;

    return false;
  }

  public static void main(String [] args)
  {
    Test.disableDebug();

    // Board parameters.
    Test.setWidth(5);
    Test.setHeight(5);

    // Number of test pieces.
    int placeCount = 5;

    boolean success = true;
    Board b = null;


    // Test for successful board creation.
    success &= (b = new Board(Test.getWidth(), Test.getHeight())) != null;

    if (Test.debug())
      Test.printDebugMsg("New Board: Width: " + Test.getWidth() + ", Height: " + Test.getHeight(), "Error in board creation.", success);

    if (Test.debug())
      Test.printDebugMsg("Checking that initial board is committed..", "", success);

    // Get the current state of the board.
    success &= getCommitStatus(b);

    if (Test.debug())
    {
      Test.printDebugMsg("PASS!", "Error: Initial board is not committed.", success);
    }

    if (Test.debug())
    {
      Test.printDebugMsg("Placing piece on the board...", "", success);
    }

    // Place a test piece on the board.
    b.place(TetrisConstants.gamePieces[0], 0, 0);

    if (Test.debug())
    {
      Test.printBoard(b);
      Test.printDebugMsg("Checking commit status..", "", success);
    }

    // Board changes to uncommitted state after a piece placement.
    success &= !getCommitStatus(b);

    if (Test.debug())
    {
      Test.printDebugMsg("PASS!", "Error: Board is still committed.", success);
    }

    if (Test.debug())
    {
      Test.printDebugMsg("Checking that piece is on the board...", "", success);
    }

    // Checking that piece is physically on the board.
    success &= checkBoard(b);

    if (Test.debug())
    {
      Test.printDebugMsg("PASS!", "Error: Piece not found on board", success);
    }

    if (Test.debug())
    {
      Test.printDebugMsg("Attemping to undo piece placement...", "", success);
    }

    // Undo piece placement.
    b.undo();

    if (Test.debug())
    {
      Test.printBoard(b);
      Test.printDebugMsg("Checking commit status...", "", success);
    }

    // Board is back to committed state after calling undo().
    success &= getCommitStatus(b);

    if (Test.debug())
    {
      Test.printDebugMsg("PASS!", "Error: Board not committed.", success);
    }

    if (Test.debug())
    {
      Test.printDebugMsg("Checking that piece is removed from the board...", "", success);
    }

    // Checking that piece is gone from the board.
    success &= !checkBoard(b);

    if (Test.debug())
    {
      Test.printDebugMsg("PASS!", "Error: Piece is still on the board", success);
      Test.printDebugMsg("Placing piece on board...", "", success);
    }

    // Place a new piece on the board.
    b.place(TetrisConstants.gamePieces[0], 0, 0);

    if (Test.debug())
    {
      Test.printBoard(b);
      Test.printDebugMsg("Committing move...", "", success);
    }

    // Commit the board. No longer possible to call undo().
    b.commit();

    // Checking that board has been committed after piece placement.
    success &= getCommitStatus(b);

    if (Test.debug())
    {
      Test.printDebugMsg("PASS!", "Error: Board not committed.", success);
    }

    if (success)
      System.out.println("Hooray!");
    else
      System.out.println("fail");
  }
}
