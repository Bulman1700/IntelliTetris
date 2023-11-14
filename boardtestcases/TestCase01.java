// JB

// TestCase01.java
// ===============
// Tests board creation, getWidth(), and getHeight() methods.

package boardtestcases;

import tetris.*;

public class TestCase01
{
    public static void main(String [] args)
    {
        Test.disableDebug();
        Test.setWidth(16);
        Test.setHeight(35);

        boolean success = true;
        Board b = null;

        if (Test.debug())
          Test.printDebugMsg("Attempting to create board...", "", success);

        // Testing for board creation.
        success &= (b = new Board(Test.getWidth(), Test.getHeight())) != null;

        if (Test.debug())
        {
          Test.printDebugMsg("Board creation successful.", "Error: could not create board", success);
        }

        // Testing for correct board dimensions.
        success &= (b.getWidth() == Test.getWidth() && (b.getHeight() == Test.getHeight()));

        if (Test.debug())
        {
          Test.printDebugMsg(" Height: " + b.getHeight() + "\n Width: " + b.getWidth(), "Error: incorrect board dimensions.", success);
          Test.printBoard(b);
        }

        if (success)
          System.out.println("passed test case");
        else
          System.out.println("fail");
    }
}
