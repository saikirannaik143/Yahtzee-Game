import java.util.Random;

public class Dice {
	
	private int faceValue;
	private boolean holdValue = false;
	
	/**
	 * Selects a random value between 1-6, simulating a dice.
	 */
	public void roll(){
		Random roll = new Random();
		faceValue = roll.nextInt(6) + 1;
	}
	
	/**
	 * Sets the hold Value to true
	 */
	public void hold(){
		holdValue = true;
	}
	
	/** 
	 * @param holdValue whether the dice is held or not
	 */
	public void setHoldState(boolean holdValue){
		this.holdValue = holdValue;
		
	}
	
	/**
	 * 
	 * @param faceValue the value that the dice is to be set to
	 */
	public void setFaceValue(int faceValue){
		this.faceValue = faceValue;
	}
	
	/**
	 * gets the hold state of the dice, returning the boolean.
	 * 
	 * @return if the dice is held
	 */
	public boolean getHoldState(){
		return holdValue;
	}
	
	/**
	 * 
	 * @return the face value of the dice.
	 */
	public int getFaceValue(){
		return faceValue;
	}
	
}
