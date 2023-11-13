package piecetestcases;

import tetris.*;

public final class Test
{
    // For debugging purposes.
    private static boolean debug = true;

    // Piece rotation count.
    private static int rotationCnt = 3;

    // Setters.
    public static void enableDebug() { Test.debug = true; }
    public static void disableDebug() { Test.debug = false; }
    public static void setRotationCnt(int numRotations) { Test.rotationCnt = numRotations; }

    // Getters.
    public static boolean checkDebug() { return Test.debug; }
    public static int getRotationCnt() { return Test.rotationCnt; }

    private Test()
    {      
    }

    public static void printPiece(Piece piece)
    {
        piece.printCoordinates();
    }

    // Tests equals() method in Piece.java
    public static boolean testEquality(Piece a, Piece b)
    {
        return a.equals(b);
    } 
}
