package entities;

import java.util.ArrayList;

public class EliminationBoard extends Board {
	public EliminationBoard(Square[][] board, Blueprint bp, ArrayList<Index> selectedSquares) {
		super(board, bp.getValueFrequencies(), bp.getMultiplierFrequencies());

		// TODO Auto-generated constructor stub
	}

	boolean[][] markedSquares;
}
