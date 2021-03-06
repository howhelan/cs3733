package boundaries;

import java.awt.Color;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;

import entities.Square;

/**
 * boundary class for a square in builder
 * @author Ethan Coeytaux
 * @author Hugh Whelan
 *
 */
public class BuilderSquareDisplay extends JPanel {
	private static final long serialVersionUID = 1L;

	public static final int pixelSize = 62;

	BuilderBoardDisplay parent;

	AbstractDisplay display;
	Square square;

	public BuilderSquareDisplay(BuilderBoardDisplay parent, Square square) {
		this.parent = parent;
		this.square = square;

		setup();
	}

	/**
	 * sets up display of Builder Square
	 */
	public void setup() {
		if (square.isInert()) {
			display = new InertDisplay(null);
			setBackground(Color.BLACK);
		} else if (square.isBucket()) {
			display = new BucketDisplay(null, false);
			setBackground(Color.LIGHT_GRAY);
		} else {
			display = new TileDisplay(null, square.getTile());
			setBackground(TileDisplay.getColor(square.getTile().getValue()));
		}

		this.removeAll();
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
						.addContainerGap()
						.addComponent(display, GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
						.addContainerGap())
				);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(display, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
						.addGap(12))
				);
		setLayout(groupLayout);
	}

	/**
	 * updates square to values on current board
	 */
	public Square changeSquare() {
		Square newSquare = parent.getNewSquare();
		square.copyValues(newSquare);
		setup();
		return newSquare;
		
	}
	
	/**
	 * refreshes square to update value
	 */
	public void changeSquare(Square newSquare) {
		square.copyValues(newSquare);
		setup();
	}
	

	public BuilderBoardDisplay getParentDisplay(){
		return this.parent;
	}
	
	public Square getCopySquare() {
		return( new Square(this.square.getTile(), this.square.isInert(), this.square.isInert(), this.square.getParentBoard(), this.square.getIIndex(), this.square.getJIndex()) );
	}
	
	/**
	 * returns square held in display
	 * @return
	 */
	public Square getSquare() {
		return square;
	}
}
