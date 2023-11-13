package boardtestcases;

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
  public static boolean debugStatus() { return Test.debug; }
  public static int getWidth() { return Test.width; }
  public static int getHeight() { return Test.height; }

  public static void testSuccess(boolean success)
  {
    if (!success)
    {
      System.out.println("Fail Whale :(");
      System.exit(0);
    }
  }

  private Test()
  {

  }
}
