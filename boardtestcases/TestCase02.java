// JB

// TestCase02.java
// ===============
// Tests place() method on a small board and tests for return types.

package boardtestcases;

import tetris.*;

public class TestCase02
{
  public static final int EXCESS = 10;
  public static void main(String [] args)
  {
    Test.enableDebug();
    Test.setWidth(5);
    Test.setHeight(5);

    Board b = new Board(Test.getWidth(), Test.getHeight());

    if (Test.debugStatus())
    {
      System.out.println("New Board.");
      System.out.println("Width: " + Test.getWidth());
      System.out.println("Height: " + Test.getHeight());
      Test.printBoard(b);
    }

    // Make game pieces
    Piece [] p = Piece.getPieces();

    boolean success = true;
  }
}

