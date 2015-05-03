package controllers;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.logging.Level;

import entities.AbstractLevel;
import entities.Board;
import entities.EliminationBoard;
import entities.Game;
import entities.Square;
import entities.SquareBuilderMove;
import entities.Model;
import boundaries.BoardDisplay;
import boundaries.BuilderSquareDisplay;
import boundaries.SquareDisplay;

/**
 * handles mouse events on a square
 * @author Hugh Whelan
 *
 */
public class MoveController implements MouseListener {
	SquareDisplay squareDisplay;
	Square square;
	Model model;
	
	
	public MoveController(SquareDisplay squareDisplay, Model model) {
		this.squareDisplay = squareDisplay;
		this.square = squareDisplay.getSquare();
		this.model = model;
	}

	/**
	 * try to select on mouse click
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(isValid()){
			this.square.select();
			this.squareDisplay.setup();
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		Board board = this.square.getParentBoard();
		BoardDisplay boardDisplay = squareDisplay.getParentBoardDisplay();
		
		this.model.getCurrentLevel().incrementMoves();
		
		
		if(board.validMove()){
			this.model.getCurrentLevel().increaseScore(this.square.getParentBoard().getMoveScore());
			
			if(model.getCurrentLevel().getLevelType() == Game.ELIMINATION_ID){
				board.eliminateMoved();
			}
			
			for( int i = 0; i < 9; i++){
				for( int j = 0; j < 9; j++){
					if (board.getSquare(i, j).isSelected()) board.getSquare(i, j).remove();
				}
			}
		}
		
		if(outOfMoves()) squareDisplay.getParentBoardDisplay().getParentLevelDisplay().gameOver();
		
		board.deselectAll();
		//this.levelScreen.getBoardDisplay().setupSquares();
		
		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 9; j++){
				boardDisplay.updateTile(i, j);
			}
		}
		squareDisplay.getParentBoardDisplay().getParentLevelDisplay().setup();
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if((e.getModifiers() == MouseEvent.BUTTON1_MASK) && this.square.getParentBoard().getNumberOfSelected() != 0 && isValid()){
			this.square.select();
			this.squareDisplay.setup();
		}
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * if there are none selected or the selected square is adjacent to a selected square, return true
	 * @param squareDisplay
	 * @param square
	 * @return boolean
	 */
	public boolean isValid(){
		//if (square.getParentBoard().getNumberOfSelected() >= 6) return false; //uncomment to prevent user from selected more than 6 tiles
		if (square.isInert() || square.isBucket()) return false;
		
		boolean downSelected;
		boolean upSelected;
		boolean leftSelected;
		boolean rightSelected;

		 
		if(this.square.getIIndex() <= 0 && this.square.getJIndex() <= 0){
			downSelected = this.square.getParentBoard().getSquare(this.square.getIIndex()+1, this.square.getJIndex()).isSelected();
			rightSelected = this.square.getParentBoard().getSquare(this.square.getIIndex(), this.square.getJIndex()+1).isSelected();
			
			return ((this.square.getParentBoard().getNumberOfSelected() == 0) || rightSelected || downSelected);
		}else if(this.square.getIIndex() >= 8 && this.square.getJIndex() >= 8){
			upSelected = this.square.getParentBoard().getSquare(this.square.getIIndex()-1, this.square.getJIndex()).isSelected();
			leftSelected = this.square.getParentBoard().getSquare(this.square.getIIndex(), this.square.getJIndex()-1).isSelected();
			
			return ((this.square.getParentBoard().getNumberOfSelected() == 0) || leftSelected || upSelected);
		}else if(this.square.getIIndex() <= 0 && this.square.getJIndex() >= 8){
			downSelected = this.square.getParentBoard().getSquare(this.square.getIIndex()+1, this.square.getJIndex()).isSelected();
			leftSelected = this.square.getParentBoard().getSquare(this.square.getIIndex(), this.square.getJIndex()-1).isSelected();
			
			return ((this.square.getParentBoard().getNumberOfSelected() == 0) || leftSelected || downSelected);
		
		}else if(this.square.getIIndex() >= 8 && this.square.getJIndex() <= 0){
			upSelected = this.square.getParentBoard().getSquare(this.square.getIIndex()-1, this.square.getJIndex()).isSelected();
			rightSelected = this.square.getParentBoard().getSquare(this.square.getIIndex(), this.square.getJIndex()+1).isSelected();
			
			return ((this.square.getParentBoard().getNumberOfSelected() == 0) || rightSelected || upSelected);
		
		}else if( this.square.getIIndex() <= 0){
			downSelected = this.square.getParentBoard().getSquare(this.square.getIIndex()+1, this.square.getJIndex()).isSelected();
			leftSelected = this.square.getParentBoard().getSquare(this.square.getIIndex(), this.square.getJIndex()-1).isSelected();
			rightSelected = this.square.getParentBoard().getSquare(this.square.getIIndex(), this.square.getJIndex()+1).isSelected();
			
			return ((this.square.getParentBoard().getNumberOfSelected() == 0) || rightSelected || leftSelected || downSelected);
		}else if(this.square.getJIndex() <= 0){
			downSelected = this.square.getParentBoard().getSquare(this.square.getIIndex()+1, this.square.getJIndex()).isSelected();
			upSelected = this.square.getParentBoard().getSquare(this.square.getIIndex()-1, this.square.getJIndex()).isSelected();
			rightSelected = this.square.getParentBoard().getSquare(this.square.getIIndex(), this.square.getJIndex()+1).isSelected();
			
			return ((this.square.getParentBoard().getNumberOfSelected() == 0) || rightSelected || downSelected || upSelected);
			
		}else if(this.square.getIIndex() >= 8){
			upSelected = this.square.getParentBoard().getSquare(this.square.getIIndex()-1, this.square.getJIndex()).isSelected();
			leftSelected = this.square.getParentBoard().getSquare(this.square.getIIndex(), this.square.getJIndex()-1).isSelected();
			rightSelected = this.square.getParentBoard().getSquare(this.square.getIIndex(), this.square.getJIndex()+1).isSelected();
			
			return ((this.square.getParentBoard().getNumberOfSelected() == 0) || rightSelected || leftSelected || upSelected);
		}else if(this.square.getJIndex() >= 8){
			downSelected = this.square.getParentBoard().getSquare(this.square.getIIndex()+1, this.square.getJIndex()).isSelected();
			upSelected = this.square.getParentBoard().getSquare(this.square.getIIndex()-1, this.square.getJIndex()).isSelected();
			leftSelected = this.square.getParentBoard().getSquare(this.square.getIIndex(), this.square.getJIndex()-1).isSelected();
			
			return ((this.square.getParentBoard().getNumberOfSelected() == 0) || leftSelected || downSelected || upSelected);
		}else{
			downSelected = this.square.getParentBoard().getSquare(this.square.getIIndex()+1, this.square.getJIndex()).isSelected();
			upSelected = this.square.getParentBoard().getSquare(this.square.getIIndex()-1, this.square.getJIndex()).isSelected();
			leftSelected = this.square.getParentBoard().getSquare(this.square.getIIndex(), this.square.getJIndex()-1).isSelected();
			rightSelected = this.square.getParentBoard().getSquare(this.square.getIIndex(), this.square.getJIndex()+1).isSelected();

			return ((this.square.getParentBoard().getNumberOfSelected() == 0) || rightSelected || leftSelected || downSelected || upSelected);
		}
	}
	
	public boolean outOfMoves(){
		if(model.getCurrentLevel().getInfo().getMovesTotal() - model.getCurrentLevel().getInfo().getMovesPlayed() <= 0) return true;
		else return false;
	}
}