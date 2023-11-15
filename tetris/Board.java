// Johnathon Bulman
// Board.java
// ==========

package tetris;

public class Board
{
  // Board dimensions.
  private int bWidth;
  private int bHeight;

  // 2D representation of the board.
  private boolean [][] grid;
  private boolean [][] backupGrid; // Backup.

  // State of the board.
  private boolean committed;

  // Stores how full each row and column is on the board.
  private int [] width;
  private int [] height;

  // Backups
  private int [] backupWidth;
  private int [] backupHeight;

  // Stores height of tallest column.
  private int maxHeight;
  private int backupMaxHeight;

  // Board constuctor. Initializes board and field variables.
  public Board(int bw, int bh)
  {
    // Setting grid parameters.
    this.bWidth = bw;
    this.bHeight = bh;

    this.maxHeight = 0;
    this.backupMaxHeight = 0;

    grid = new boolean[bWidth][bHeight];
    backupGrid = new boolean[bWidth][bHeight];

    width = new int[bHeight];
    height = new int[bWidth];
    backupWidth = new int[bHeight];
    backupHeight = new int[bWidth];

    committed = true;
  }

  // Getters.

  // Board width.
  public int getWidth()
  {
    return bWidth;
  }

  // Board height.
  public int getHeight()
  {
      return bHeight;
  }

  // Returns height at column 'x'.
  public int getColumnHeight(int x)
  {
      return height[x];
  }

  // Returns size of row 'x'.
  public int getRowSize(int y)
  {
      return width[y];
  }

  // Checks if the board is in the 'committed' state.
  public boolean getCommitStatus()
  {
      return committed;
  }

  // Check the boolean condition of an index in the grid.
  public boolean getGrid(int x, int y)
  {
      return grid[x][y];
  }

  // Returns maximum height of all columns.
  public int getMaxHeight()
  {
      return maxHeight;
  }

  // sets the maximum height.
  private void setMaxHeight(int y)
  {
      this.maxHeight = y;
  }

 // Finds the height of each column.
  private void setHeights()
  {
      for (int i = 0; i < bWidth; i++)
      {
          int height = getColumnHeight(i) - 1;

          if (height < 0 || getGrid(i, height))
              continue;

          int counter = 0;
          this.height[i] = 0;

          for (int j = 0; j < maxHeight; j++)
              if (getGrid(i, j))
                  this.height[i] = ++counter;
      }
  }

  // Places given piece on the grid by marking grid index as true.
  public int place(Piece piece, int x, int y)
  {
      // Committed should be true before placing a new piece to prevent
      // more than one piece being placed at a time between backups.
      if (!committed)
          throw new RuntimeException(BoardConsts.commitErrMsg);

      committed = false;
      backup();

      // Get coordinates of tetris piece.
      TPoint [] body = piece.getPiece();

      boolean rowFilled = false;

      // Keeps track of the highest y value and
      // compares it to the current maxheight.
      int maxY = 0;

      // Place piece on the grid.
      for (int i = 0; i < body.length; i++)
      {
          int xPos = x + body[i].x;
          int yPos = y + body[i].y;

          if (checkBounds(xPos, yPos))
              return BoardConsts.PLACE_OUT_BOUNDS;

          if (getGrid(xPos, yPos))
              return BoardConsts.PLACE_BAD;

          // Updates maxY.
          if (yPos > maxY)
              maxY = yPos;

          // Placement.
          grid[xPos][yPos] = true;

          if (getColumnHeight(xPos) < yPos + 1)
              height[xPos] = yPos + 1;

          rowFilled = isFilled(++width[yPos]);
      }

      // After confirming placement of entire piece, compares maxY
      // with the current maxHeight and updates if necessary.
      if (maxY > getMaxHeight())
          setMaxHeight((maxY + 1));

      return (rowFilled ? BoardConsts.PLACE_ROW_FILLED : BoardConsts.PLACE_OK);
  }

  // Returns true if piece is placed out of bounds.
  private boolean checkBounds(int x, int y)
  {
      return (x < 0 || y < 0 ||
              x >= bWidth || y >= bHeight);
  }

  // Clear all filled rows in the grid.
  public int clearRows()
  {
      if (committed)
      backup();

    committed = false;
    int numCleared = 0;

    // Bubble all the filled rows to the top of the grid.
    // Once all the filled rows are at the top, clear them out one by one
    // until there are no filled rows left.
    for (int i = 0, j = 0; i <= this.maxHeight; i++, j = i)
    {
      // Storing first filled row position with 'i'.
      if (isFilled(width[i]))
      {
        numCleared++; // Count filled row

        // Keep going until you reach a row that isnt filled.
        while (isFilled(width[++j]))
          ;

        // Swap the rows. 'j' is reset to 'i' at each iteration of the loop.
        swap(i , j);
      }

      // At this point, all filled rows are at the top of the grid.
      // Clear each filled row one by one until there are none left.
      if (j >= maxHeight)
           while (isFilled(width[j]))
              clear(j--);

    }



    // Fix any incorrect heights due to gaps in each column.
    setHeights();

    return numCleared;
  }

  // Checks if a row is filled.
  private boolean isFilled(int y)
  {
      return y >= bWidth;
  }


  // Swaps two rows in the grid.
  private void swap(int row_a, int row_b)
  {
      boolean [] temp = new boolean[bWidth];

      for (int i = 0; i < bWidth; i++)
      {
        temp[i] = this.grid[i][row_b];
        this.grid[i][row_b] = this.grid[i][row_a];
        this.grid[i][row_a] = temp[i];

      }

      // Swap width information.
      int tempWidth = width[row_a];
      width[row_a] = width[row_b];
      width[row_b] = tempWidth;
  }

  // Clear that sh!t out!
  private void clear(int row_j)
  {
      // Swap all values in row to false.
      for (int i = 0; i < bWidth; i++)
      {
        grid[i][row_j] = false;
        height[i]--;
      }

      // Row is now empty. Decrease max height and set index in array to empty.
      width[row_j] = 0;
      maxHeight--;
  }

  // Finds the height that a piece will fall before landing.
  public int dropHeight(Piece piece, int x)
  {
      int dropHeight = 0;
      int [] skirt = piece.getSkirt();

      for (int i = 0; i < skirt.length; i++)
      {
        int dist = getColumnHeight(x + i) - skirt[i];
        if (dist > dropHeight)
          dropHeight = dist;
      }

      return dropHeight;
  }

  // Resets to a previous save.
  public void undo()
  {
      if (this.committed == false)
      {
        int[] temp = backupWidth;
        backupWidth = width;
        width = temp;

        temp = backupHeight;
        backupHeight = height;
        height = temp;

        boolean[][] tempgrid = backupGrid;
        backupGrid = grid;
        grid = tempgrid;

        setMaxHeight(backupMaxHeight);
      }

      commit();
  }

  // Saves the board.
  public void commit()
  {
      committed = true;
  }

  // Makes a copy of the board before committing.
  private void backup()
  {
      // Copy over width, height, max height, and grid.
      System.arraycopy(width, 0, backupWidth, 0, width.length);
      System.arraycopy(height, 0, backupHeight, 0, height.length);

      for (int i = 0; i < grid.length; i++)
      System.arraycopy(grid[i], 0, backupGrid[i], 0, grid[i].length);

      backupMaxHeight = getMaxHeight();
  }
}
