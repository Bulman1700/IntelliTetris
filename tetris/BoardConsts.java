package tetris;

public class BoardConsts 
{
      // Return flags
  public static final int PLACE_OK = 0;
  public static final int PLACE_ROW_FILLED = 1;
  public static final int PLACE_OUT_BOUNDS = 2;
  public static final int PLACE_BAD = 3;

  // Error Messages
  public static final String commitErrMsg = "Error: Board not committed";
  public static final String heightErrMsg = "Error: Height is wrong in column ";
  public static final String widthErrMsg  = "Error: Width is wrong in row ";
  public static final String maxErrMsg    = "Error: Max Height is incorrect.";

  private BoardConsts()
  {
    
  }
}
