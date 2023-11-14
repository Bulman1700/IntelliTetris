// JB

// TestCase02.java
// ===============
// Verifies that a piece can be placed on the board.
// Tests Board.commit() and Board.undo().

package boardtestcases;

import tetris.*;

public class TestCase02
{
  public static void printBoardStats(Board b)
  {
    if (b == null)
    {
      System.out.println("Error in board creattion.");
    }

    else
    {
      System.out.println("New Board..");
      System.out.println("  Height: " + b.getHeight());
      System.out.println("  Width: " + b.getWidth());
      Test.printBoard(b);
    }
  }

  public static boolean getCommitStatus(Board b)
  {
    return b.getCommitStatus();
  }

  public static boolean checkBoard(Board b)
  {
    for (int i = 0; i < b.getWidth(); i++)
    {
      for (int j = 0; j < b.getHeight(); j++)
      {
        if (b.getGrid(i, j))
        {
          return true;
        }
      }
    }
    return false;
  }

  public static void printDebugMsg(String s1, String s2, boolean success)
  {
    if (s2 == "")
    {
      System.out.println(s1);
      return;
    }
    else
    {
      if (success)
      {
        System.out.println(s1);
      }
      else
      {
        System.out.println(s2);
        System.exit(0);
      }
    }
  }

  public static void main(String [] args)
  {
    Test.enableDebug();
    Test.setWidth(5);
    Test.setHeight(5);

    int placeCount = 5;

    boolean success = true;

    // Build Board.
    Board b = new Board(Test.getWidth(), Test.getHeight());

    if (Test.debug())
      printBoardStats(b);

    if (Test.debug())
      printDebugMsg("Checking that initial board is committed..", "", success);

    success &= getCommitStatus(b);

    if (Test.debug())
    {
      printDebugMsg("PASS!", "Error: Initial board is not committed.", success);
    }

    if (Test.debug())
    {
      printDebugMsg("Placing piece on the board...", "", success);
    }

    b.place(TetrisConstants.gamePieces[0], 0, 0);

    if (Test.debug())
    {
      Test.printBoard(b);
      printDebugMsg("Checking commit status..", "", success);
    }

    success &= !getCommitStatus(b);

    if (Test.debug())
    {
      printDebugMsg("PASS!", "Error: Board is still committed.", success);
    }

    if (Test.debug())
    {
      printDebugMsg("Checking that piece is on the board...", "", success);
    }

    success &= checkBoard(b);

    if (Test.debug())
    {
      printDebugMsg("PASS!", "Error: Piece not found on board", success);
    }

    if (Test.debug())
    {
      printDebugMsg("Attemping to undo piece placement...", "", success);
    }

    b.undo();

    if (Test.debug())
    {
      printDebugMsg("Checking commit status...", "", success);
    }

    success &= getCommitStatus(b);

    if (Test.debug())
    {
      printDebugMsg("PASS!", "Error: Board not committed.", success);
    }

    if (Test.debug())
    {
      printDebugMsg("Checking that piece is removed from the board...", "", success);
    }

    success &= !checkBoard(b);

    if (Test.debug())
    {
      printDebugMsg("PASS!", "Error: Piece is still on the board", success);
      printDebugMsg("Placing piece on board...", "", success);
    }

    b.place(TetrisConstants.gamePieces[0], 0, 0);

    if (Test.debug())
    {
      printDebugMsg("Committing move...", "", success);
    }

    b.commit();

    success &= getCommitStatus(b);

    if (Test.debug())
    {
      printDebugMsg("PASS!", "Error: Board not committed.", success);
    }

    if (success)
      System.out.println("Hooray!");
    else
      System.out.println("fail");
  }
}
