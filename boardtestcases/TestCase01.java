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
        Test.enableDebug();
        Test.setWidth(16);
        Test.setHeight(35);
        
        boolean success = true;

        if (Test.debugStatus())
            System.out.println("Attempting to create board...");
        
        Board b = new Board(Test.getWidth(), Test.getHeight());

        // Testing for board creation.
        success &= (b != null);
        
        if (Test.debugStatus())
        {
            if (success)
            {
                System.out.println("Board creation successful.");
            }
            else
            {
                System.out.println("Error: could not create board");
                System.exit(0);
            }
        }

        // Testing for correct board dimensions.
        success &= (b.getWidth() == Test.getWidth() && (b.getHeight() == Test.getHeight()));
        
        if (Test.debugStatus())
        {
            if (success)
            {
                System.out.println(" Height: " + b.getHeight() + "\n Width: " + b.getWidth());
            }
            else
            {
                System.out.println("Error: incorrect board dimensions.");
                System.exit(0);
            }
        }

        if (Test.debugStatus())
        {
           if (success)
           {
            Test.printBoard(b);
           }
        }
    }
}
