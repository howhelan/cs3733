package entities;

/**
 * this class contains the info that is needed for all levels
 * @author Hugh Whelan
 *
 */
public class Info {
	int movesPlayed;
	int movesTotal;
	int swaps;
	int shuffles;
	int removes;
	int score;
	int[] starRequirements; //index 0 = 1 star req, 1 -> 2 stars, 2 -> 3 stars
	int[] valueFrequencies;
	int[] multiplierFrequencies;
	
	public Info(int movesTotal, int swaps, int shuffles, int removes, int[] starRequirements, int[] valueFrequencies, int[] multiplierFrequencies){
		this.movesPlayed = 0;
		this.movesTotal = movesTotal;
		this.swaps = swaps;
		this.shuffles = shuffles;
		this.removes = removes;
		this.starRequirements = starRequirements;
		this.valueFrequencies = valueFrequencies;
		this.multiplierFrequencies = multiplierFrequencies;
		score = 0;
		
	}
	
	public int[] getStarRequirements(){ 
		return this.starRequirements;
	}
	
	public int getScore(){
		return this.score;
	}
	
	public int getMovesTotal(){
		return this.movesTotal;
	}
	
	public int getMovesPlayed(){
		return this.movesPlayed;
	}
	
	public void incrementMoves(){
		this.movesPlayed++;
	}
	
	public int getSwaps(){
		return this.swaps;
	}
	
	public void decrementSwaps(){
		swaps--;
	}
	
	public int getShuffles(){
		return this.shuffles;
	}
	
	public void decrementShuffles(){
		shuffles--;
	}
	
	public int getRemoves(){
		return this.removes;
	}
	
	public void decrementRemoves(){
		removes--;
	}
	
	public void increaseScore(int newScore){
		this.score += newScore;
	}

}
