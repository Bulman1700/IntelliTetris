// Johnathon Bulman
// Piece.java
// ==========

package tetris;

import java.util.*;


public class Piece
{
    private TPoint [] piece; // Tetris coordinates.
    
    // Stores lowest 'y' coordinate for each 'x' index value.
    private int [] skirt;
    private int width;  // Piece width.
    private int height; // Piece height.
    private Piece next; // 'Next' rotation.

    // Stores all pieces' 'next' rotations. Used for early loading.
    private static Piece [] rotations;

    // Assumes no duplicate points.
    public Piece(TPoint[] pts)
    {
        // Allocating memory
        this.piece = new TPoint[pts.length];

        // Building tetris piece.
        for (int i = 0; i < piece.length; i++)
            piece[i] = new TPoint(pts[i]);
        
        this.height = this.width = 1;

        // Finding height and width from TPoints
        for (TPoint pt : piece)
        {
            if ((pt.x + 1) > width)
                width = pt.x + 1;
            if ((pt.y + 1) > height)
                height = pt.y + 1;
        }

        this.skirt = new int[width];

        Arrays.fill(skirt, height - 1); // Initialize

        // Getting skirt from TPoints
        for (TPoint pt : piece)
            if (skirt[pt.x] > pt.y)
                skirt[pt.x] = pt.y;
    }

    // Converts a string to TPoints and then calls the default constructor.
    public Piece(String pts)
    {
        this(parsePoints(pts));
    }

    public int getWidth()
    {
        return this.width;
    }

    public int getHeight()
    {
        return this.height;
    }

    public TPoint[] getPiece()
    {
        return this.piece;
    }

    public int[] getSkirt()
    {
        return this.skirt;
    }

     // Fast rotation.
      public Piece fastRotation()
     {
        return next;
     }

    // Returns a piece that is 90 degrees counter-clockwise rotated from the 
    // given piece.
    public Piece computeNextRotation()
    {
        TPoint [] rotate = new TPoint[piece.length];

        for (int i = 0; i < piece.length; i++)
            rotate[i] = new TPoint((height - 1) - piece[i].y, piece[i].x);
        
        return new Piece(rotate);
    }

    // Uses 'next' to store the next rotation in the sequence. Each rotated piece
    // has a 'next' pointer that stores its next rotation, until the piece returns 
    // to its root position.
    private static Piece makeFastRotation(Piece root)
    {
        Piece curr = root.computeNextRotation();
        root.next = curr;
        Piece next = curr.computeNextRotation();

        while (!next.equals(root))
        {
            curr.next = next;
            curr = next;
            next = curr.computeNextRotation();
        }
        
        return curr.next = root;
    }

    // Makes an array of tetris pieces and sets each piece's 'next' reference to it's next rotation.
    public static Piece[] getPieces()
    {
        if (Piece.rotations == null)
        {
            Piece.rotations = new Piece[TetrisConstants.gamePieces.length];

            for (int i = 0; i < Piece.rotations.length; i++)
                Piece.rotations[i] = makeFastRotation(TetrisConstants.gamePieces[i]);

        }

        return Piece.rotations;              
    }

    // Checks if two pieces are equal.
    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        
        if (!(o instanceof Piece))
            return false;
        
        Piece rhs = (Piece)o;

        // Check that all lists are same length
        List<TPoint> list1 = Arrays.asList(piece);
        List<TPoint> list2 = Arrays.asList(rhs.piece);

        return list1.containsAll(list2);
    }

    // Assumes string is non-null.
    private static TPoint [] parsePoints(String str)
    {
        List<TPoint> pts = new ArrayList<TPoint>();
        StringTokenizer tok = new StringTokenizer(str);

        while (tok.hasMoreTokens())
        {
            int x = Integer.parseInt(tok.nextToken());
            int y = Integer.parseInt(tok.nextToken());

            pts.add(new TPoint(x, y));
        }

        return pts.toArray(new TPoint[0]);
    }

    // Prints coordinates of tetris piece.
    public void printCoordinates()
    {
        TPoint[] piece = this.piece;

        for (TPoint i : piece)
            System.out.println(i);
        
        System.out.println();
    }

}