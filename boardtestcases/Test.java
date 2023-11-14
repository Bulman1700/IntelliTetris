// JB

// Test.java
// =========
// Holds all getters, setters, and static methods needed for any and all board testcase files.
// This class is not intended to be instantiated, nor run on its own.

package boardtestcases;

import tetris.Board;

public class Test
{
  // For debugging purposes.
  private static boolean debug = false;
  private static int width = -1;
  private static int height = -1;

  // Setters.
  public static void enableDebug() { Test.debug = true; }
  public static void disableDebug() { Test.debug = false; }

  public static void setWidth(int width) { Test.width = width; };
  public static void setHeight(int height) { Test.height = height; };

  // Getters.
  public static boolean debug() { return Test.debug; }
  public static int getWidth() { return Test.width; }
  public static int getHeight() { return Test.height; }

  // Standard Err Msg format.
  public static void printDebugMsg(String s1, String s2, boolean success)
  {
    if (!success)
    {
      System.out.println(s2);
      System.exit(0);
    }
    else if (s2 == "")
    {
      System.out.println(s1);
      return;
    }
    else
    {
      System.out.println(s1);
    }
  }

  // Simple print board method.
  public static void printBoard(Board b)
  {
    for (int i = b.getHeight() - 1; i >= 0; i--)
      for (int j = 0; j < b.getWidth(); j++)
        System.out.printf("%s%c", b.getGrid(j, i) ? "o " : ". ", j == b.getWidth() - 1 ? '\n' : ' ');

    System.out.println();
  }

  private Test()
  {
  }
}
