// Test getWidth() and getHeight() methods

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

        success &= (b != null);
        success &= (b.getWidth() == Test.getWidth() && (b.getHeight() == Test.getHeight()));

        if (Test.debugStatus())
           if (success)
           {
                System.out.println("Board creation successful.\n Height: " + 
                                b.getHeight() + "\n Width: " + b.getWidth());
                b.printBoard();
           }
    }
}
