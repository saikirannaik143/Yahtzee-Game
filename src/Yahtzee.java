

public class Yahtzee {
	private Dice[] dice = new Dice[5];

	private int rollCount = 0;
	private boolean canRoll = true;

	private static boolean[] scoreCanChange = new boolean[20];		
	private int setScoreCount = 0;

	private int upperTotal = 0;
	private int lowerTotal = 0;
	private int upperGrandTotal = 0;
	private int upperBonus = 0;
	private int grandTotal = 0;
	private boolean hasUpperBonus = false;

	public Yahtzee(){
		for(int i = 0; i < dice.length; i++){
			dice[i] = new Dice();
		}
	}

	
	/**
	 * Initiates the ScoreIsSet array for each button.
	 * 
	 */
	public void iniScoreIsSet(){ 
		for(int i = 0 ; i < scoreIsSet.length; i++){
			scoreIsSet[i] = false;
		}
	}

	/**
	 * Checks if the score is set or not
	 * 
	 * @return if the score is set.
	 */
	public boolean[] getScoreIsSet(){
		return scoreIsSet;
	}

	public void setScoreIsSet(int i, boolean b){
		scoreIsSet[i] = b;
	}

	public void setScore(int i, String score){

		int scoreInt = Integer.parseInt(score);
		YahtzeeGUI.setBtnScore(i, scoreInt);

		setScoreCanChange(i, false);
	}

	
	/**
	 * updates the upper grand total, taking into account the bonus, if any.
	 */
	public void updateUpperGrandTotal(){
		int bonus = 0;
		if (hasUpperBonus) {
			bonus = 63;
		}
		
		upperGrandTotal = upperTotal+ bonus;

		YahtzeeGUI.setBtnScore(9, upperGrandTotal);
		YahtzeeGUI.getBtnScore()[9].setForeground(Color.black);
	}

	public void updateBonus(){
		if (upperTotal >= 63){
			YahtzeeGUI.setBtnScore(8, 35);
			YahtzeeGUI.getBtnScore()[8].setForeground(Color.black);
			hasUpperBonus = true;
		}
	}

	/**
	 * 	updates the grand total using the total variables.
	 */
	public void updateGrandTotal(){
		int bonus = 0;

		if (hasUpperBonus){
			bonus = 35;
		}

		grandTotal = lowerTotal + upperTotal + bonus;

		YahtzeeGUI.setBtnScore(19, grandTotal);
		YahtzeeGUI.getBtnScore()[19].setForeground(Color.black);
	}	

	/**
	 * Resets each score button to default "-"
	 */
	public void resetAllScores(){
		
		for (int i = 1; i <= 9 ; i++ ){
			YahtzeeGUI.getBtnScore()[i].setText("-");
		}
		for (int i = 11; i <= 19 ; i++ ){
	

}
