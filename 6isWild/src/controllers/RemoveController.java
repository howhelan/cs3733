package controllers;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import boundaries.BoardDisplay;
import boundaries.LevelDisplay;
import boundaries.SquareDisplay;
import entities.Game;
import entities.Model;
import entities.Square;

/**
 * Handles remove move
 * @author Ethan Coeytaux
 *
 */
public class RemoveController implements MouseListener {
	SquareDisplay squareDisplay;
	Square square;
	Model model;

	public RemoveController(SquareDisplay squareDisplay, Model model) {
		this.squareDisplay = squareDisplay;
		this.square = squareDisplay.getSquare();
		this.model = model;

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("remove");
		
		if (square.isInert() || square.isBucket()) return;
		
		BoardDisplay boardDisplay = squareDisplay.getParentBoardDisplay();
		LevelDisplay levelDisplay = boardDisplay.getParentLevelDisplay();
		
		square.getParentBoard().removeMove = false;
		
		square.remove();

		if (squareDisplay.getParentBoardDisplay().getParentLevelDisplay().getLevel().getLevelType() == Game.RELEASE_ID) square.getParentBoard().checkBoard();

		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 9; j++){
				boardDisplay.updateTile(i, j);
			}
		}

		levelDisplay.setup();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
