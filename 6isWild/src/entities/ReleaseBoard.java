package entities;

import java.util.ArrayList;

public class ReleaseBoard extends Board {
	public ReleaseBoard(Square[][] board, Blueprint bp, ArrayList<Index> selectedSquares) {
		super(board, bp.getValueFrequencies(), bp.getMultiplierFrequencies());
		// TODO Auto-generated constructor stub
	}

	int sixesLeft;
}
