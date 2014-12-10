import java.awt.Color;
import javax.swing.UIManager;

public class Yahtzee {
	private Dice[] dice = new Dice[5];

	private int rollCount = 0;
	private boolean canRoll = true;

	private static boolean[] scoreCanChange = new boolean[20];		// this one is to change at an earlier time than scoreIsSet. i.e scoreCanChange can e.g. false while score is set can be false too
	private boolean[] scoreIsSet = new boolean[20];					// to check when scoring so no values are duplicated.
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
	 * Resets all total values and scores. Sets everything to it's initial state as if the program has just opened.
	 */
	public void restartGame(){
		rollCount = 0;
		canRoll = true;

		setScoreCount = 0;

		upperTotal = 0;
		lowerTotal = 0;
		upperGrandTotal = 0;
		upperBonus = 0;
		grandTotal = 0;
		hasUpperBonus = false;

		resetDice();
		iniScoreCanChange();
		iniScoreIsSet();
		
		resetAllScores();
	}

	/**
	 * @return The upper total value
	 */
	public int getUpperTotal(){
		return upperTotal;
	}

	/**
	 * @return The lower total value
	 */
	public int getLowerTotal(){
		return lowerTotal;
	}

	/**
	 * @return The upper bonus value
	 */
	public int getUpperBonus(){
		return upperBonus;
	}

	/**
	 * @return The grand total value
	 */
	public int getGrandTotal(){
		return grandTotal;
	}

	/**
	 * Resets the dice values, hold state and background colors.
	 */
	public void resetDice(){
		for (int i = 0; i < dice.length; i++){
			dice[i].setFaceValue(0);
			dice[i].setHoldState(false);
			YahtzeeGUI.getBtnDice()[i].setBackground(UIManager.getColor("Button.background"));
			YahtzeeGUI.getBtnDice()[i].setForeground(UIManager.getColor("Button.foreground"));
			YahtzeeGUI.getBtnDice()[i].setText("-");
		}
	}

	/**
	 * Initiates the scoreCanChange array for all the score values.
	 */
	public void iniScoreCanChange(){
		for(int i = 0 ; i < scoreCanChange.length; i++){
			scoreCanChange[i] = true;
		}

		scoreCanChange[0] = false;
		scoreCanChange[10] = false;
	}

	/**
	 * 
	 * @return whether the score can change. 
	 */
	public boolean[] getScoreCanChange(){
		return scoreCanChange;
	}

	/**
	 * Sets the state of a score button at the specified position
	 * 
	 * @param i the position of the array
	 * @param b the state of the score button
	 */
	public void setScoreCanChange(int i, boolean b){
		scoreCanChange[i] = b;
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

	/**
	 * Sets whether the score is set at the specified position.
	 * @param i the position of the button
	 * @param b the state of the score.
	 */
	public void setScoreIsSet(int i, boolean b){
		scoreIsSet[i] = b;
	}

	/**
	 * sets the score at the specified position.
	 * @param i the position of the score
	 * @param score the value of the score.
	 */
	public void setScore(int i, String score){

		int scoreInt = Integer.parseInt(score);
		YahtzeeGUI.setBtnScore(i, scoreInt);

		setScoreCanChange(i, false);
	}

	/**
	 * resets all score values to the default "-"
	 */
	public void resetScoreButtons(){
		for (int i = 1; i < 7 ; i++){

			if (scoreCanChange[i]){
				YahtzeeGUI.getBtnScore()[i].setText("-");
			}
		}
		for (int i = 11; i < 18 ; i++){

			if (scoreCanChange[i]){
				YahtzeeGUI.getBtnScore()[i].setText("-");
			}
		}
	}

	/**
	 * Updates the upper total with the values of the buttons where the score isn't set but the score can't change.
	 */
	public void updateUpperTotal(){
		for (int i = 1 ; i < 7 ; i++){ 
			if (!scoreCanChange[i] && !scoreIsSet[i]){ 
				int scoreAddition = 0;

				String buttonText = YahtzeeGUI.getBtnScore()[i].getText();
				scoreAddition = Integer.parseInt(buttonText); //gets the text in the button converts it to int

				upperTotal += scoreAddition; //adds that to the upper total.

				scoreIsSet[i] = true;
				setScoreCount++;

			}
		}

		YahtzeeGUI.setBtnScore(7, upperTotal);
		YahtzeeGUI.getBtnScore()[7].setForeground(Color.black);
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

	/**
	 *  updates the lower total from the values of the buttons that can't change and the score isn't set yet.
	 */
	public void updateLowerTotal(){
		for (int i = 11 ; i < 18 ; i++){
			if (!scoreCanChange[i] && !scoreIsSet[i]){
				int scoreAddition = 0;

				String buttonText = YahtzeeGUI.getBtnScore()[i].getText();

				scoreAddition = Integer.parseInt(buttonText);

				lowerTotal += scoreAddition;

				scoreIsSet[i] = true;
				setScoreCount++;

			}
		}

		YahtzeeGUI.setBtnScore(18, lowerTotal);
		YahtzeeGUI.getBtnScore()[18].setForeground(Color.black);
	}
	
	/**
	 *  Checks for bonus score and adds the bonus value if neccesary. Also sets hasUpperBonus to true if there is a bonus.
	 */
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
			YahtzeeGUI.getBtnScore()[i].setText("-");
		}
		
	}
	
	/** 
	 * @return The current rollcount
	 */
	public int getRollCount(){
		return rollCount;
	}

	/**
	 * Increases the roll count by one.
	 */
	public void increaseRollCount(){
		rollCount++;
	}
	
	/**
	 * Sets the roll count back to zero.
	 */
	public void resetRollCount(){
		rollCount = 0;
	}

	/**
	 * Checks whether the roll count is over 3 or not.
	 */
	public void checkRollCount(){
		if (rollCount >= 3){
			canRoll = false;
		} else {
			canRoll = true;
		}
	}

	/**
	 * 
	 * @return the roll state of the dice.
	 */
	public boolean getCanRoll(){
		return canRoll;
	}

	/**
	 * gets the dice for use of the methods in the Dice class
	 * 
	 * @param i Position of dice.
	 * @return the dice at the specified position
	 */
	public Dice getDice(int i){
		return dice[i];
	}

	/**
	 * Gets the dice object for use of methods in the dice class.
	 * 
	 * @return The dice object.
	 */
	public Dice[] getDice(){
		return dice;
	}

	/**
	 * gets the length of dice array for loops.
	 * 
	 * @return The length of the dice array
	 */
	public int getDiceLength(){
		return dice.length;
	}

	/**
	 * Checks how many goes have been and instantiates the endgame method if so.
	 */
	public void checkEndGame(){
		if (setScoreCount == 13){
			YahtzeeGUI.endGame();
		}
	}

	public static void main(String[] args) {
		new YahtzeeGUI();
	}

}
