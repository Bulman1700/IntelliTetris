package tetris;

public final class TetrisConstants 
{
    // String constants for the standard 7 tetris pieces
	public static final String STICK_STR	= "0 0	0 1	 0 2  0 3";
	public static final String L1_STR		= "0 0	0 1	 0 2  1 0";
	public static final String L2_STR		= "0 0	1 0 1 1	 1 2";
	public static final String S1_STR		= "0 0	1 0	 1 1  2 1";
	public static final String S2_STR		= "0 1	1 1  1 0  2 0";
	public static final String SQUARE_STR	= "0 0  0 1  1 0  1 1";
	public static final String PYRAMID_STR	= "0 0  1 0  1 1  2 0";

	// Indexes for the standard 7 pieces in the pieces array
	public static final int STICK = 0;
	public static final int L1	  = 1;
	public static final int L2	  = 2;
	public static final int S1	  = 3;
	public static final int S2	  = 4;
	public static final int SQUARE	= 5;
	public static final int PYRAMID = 6;

 	// Game Pieces.
	public static final Piece [] gamePieces= new Piece[] {
		new Piece(STICK_STR),
		new Piece(L1_STR),
		new Piece(L2_STR),
		new Piece(S1_STR),
		new Piece(S2_STR),
		new Piece(SQUARE_STR),
		new Piece(PYRAMID_STR)
	};

    private TetrisConstants()
    {
        
    }


}
