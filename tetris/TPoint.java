package tetris;

public class TPoint
{
    public int x;
    public int y;

    // Makes a new tetris coordinate
    public TPoint(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    // Copies an existing tetris point.
    public TPoint(TPoint pt)
    {
        this.x = pt.x;
        this.y = pt.y;
    }

    @Override
    // equality override method
    public boolean equals(Object o)
    {
        // Standard checks
        if (this == o)
            return true;
        
        if (!(o instanceof TPoint))
            return false;
        
        // Checks that x and y match
        TPoint pt = (TPoint)o;

        return (this.x == pt.x && this.y == pt.y);    
    }

    @Override
    // Returns a string of the coordinate pair
    public String toString()
    {
        return "(" + x + "," + y + ")";
    }
}