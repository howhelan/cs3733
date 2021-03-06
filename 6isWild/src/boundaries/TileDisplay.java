package boundaries;

import java.awt.Color;
import java.awt.Font;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;

import entities.Model;
import entities.Tile;


/**
 * Boundary class, displays a Tile, which contains a value and a multiplier
 * @author Hugh Whelan
 *
 */
public class TileDisplay extends AbstractDisplay {
	private static final long serialVersionUID = 1L;

	Tile tile;

	public TileDisplay(Model model, Tile tile) {
		super(model);
		this.tile = tile;

		setup();
	}

	/**
	 * sets up tile display
	 */
	public void setup() {
		this.removeAll();
		
		this.setBackground(getColor(tile.getValue()));

		JLabel lblNewLabel = new JLabel("" + tile.getValue());
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 24));

		JLabel lblX = new JLabel("x" + tile.getMultiplier());
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(lblX)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(lblNewLabel)
						.addContainerGap(23, Short.MAX_VALUE))
				);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblX)
								.addGroup(groupLayout.createSequentialGroup()
										.addGap(14)
										.addComponent(lblNewLabel)))
										.addContainerGap(16, Short.MAX_VALUE))
				);
		setLayout(groupLayout);
	}

	/**
	 * sets this tile to given tile
	 * @param tile tile that is to be placed in this.tile
	 */
	public void changeTile(Tile tile) {
		this.tile = tile;
		setup();
	}
	
	public static Color getColor(int value) {
		switch (value) {
		case 1:
			return Color.red;
		case 2:
			return Color.orange;
		case 3:
			return Color.yellow;
		case 4:
			return Color.green;
		case 5:
			return Color.cyan;
		case 6:
			return Color.magenta;
		default:
				return Color.white;
		}
	}
}
