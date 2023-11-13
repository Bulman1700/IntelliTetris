// Test placement of pieces on board

package boardtestcases;

import tetris.*;

public class TestCase02
{
    public static void main(String [] args)
    {
        Test.disableDebug();
        boolean success = true;
        Test.setWidth(16);
        Test.setHeight(35);

        Board b = new Board(Test.getWidth(), Test.getHeight());
    
        for (int i = 0; i < Test.getWidth(); i += 2)
        {
          b.place(TetrisConstants.gamePieces[5], i, 0);
          b.commit();
          
          if (Test.debugStatus())
          b.printBoard();

          b.place(TetrisConstants.gamePieces[1], i, 2);
          b.commit();

          if (Test.debugStatus())
          b.printBoard();

          b.place(TetrisConstants.gamePieces[6], i+1, 4);
          b.commit();

          if (Test.debugStatus())
          b.printBoard();
        }
    
    
        b.clearRows();
        b.commit();
    
        if (Test.debugStatus())
          b.printBoard();
    
        for (int i = 0; i < Test.getHeight(); i++)
          if (b.getRowSize(i) == b.getWidth())
            success = false;
    
        Test.testSuccess(success);
    
        for (int i = 0; i < Test.getWidth(); i += 4)
        {
          b.place(TetrisConstants.gamePieces[0].computeNextRotation(), i, 3);
          b.commit();
        }
    
    
        if (Test.debugStatus())
          b.printBoard();
    
        b.clearRows();
    
        if (Test.debugStatus())
          b.printBoard();
    
        for (int i = 0; i < Test.getHeight(); i++)
          if (b.getRowSize(i) == b.getWidth())
            success = false;
    
        Test.testSuccess(success);
    
        System.out.println("Pass!");
      }
    }

