import java.util.ArrayList;
import java.util.Collections;

public class Scoring {

	

	public void checkAces(){ // this checks the faceValues of the dice, to see if there are any 1's
		int total = 0;

		if (YahtzeeGUI.getGame().getScoreCanChange()[1]){
			for (int i = 0; i < YahtzeeGUI.getGame().getDiceLength(); i++){

				if (YahtzeeGUI.getBtnDiceValue(i) == 1){
					total += 1;					// adds 1 on to the total. This loop runs every roll, and so this needs to be re-added every roll even if dice held.
				}
			}
			YahtzeeGUI.setBtnScore(1, total); // sets the score
		}

	}	

	public void checkTwos(){
		// For explanations see setAces()
		int total = 0;

		for (int i = 0; i < YahtzeeGUI.getGame().getDiceLength(); i++){
			if (YahtzeeGUI.getBtnDiceValue(i) == 2){
				total += 2;
			}
		}
		YahtzeeGUI.setBtnScore(2, total);
	}	

	public void checkThrees(){
		// For explanations see setAces()
		int total = 0;

		for (int i = 0; i < YahtzeeGUI.getGame().getDiceLength(); i++){
			if (YahtzeeGUI.getBtnDiceValue(i) == 3){
				total += 3;
			}
		}
		YahtzeeGUI.setBtnScore(3, total);
	}	

	public void checkFours(){
		// For explanations see setAces()
		int total = 0;

		for (int i = 0; i < YahtzeeGUI.getGame().getDiceLength(); i++){
			if (YahtzeeGUI.getBtnDiceValue(i) == 4){
				total += 4;
			}
		}
		YahtzeeGUI.setBtnScore(4, total);
	}	

	public void checkFives(){
		// For explanations see setAces()
		int total = 0;

		for (int i = 0; i < YahtzeeGUI.getGame().getDiceLength(); i++){
			if (YahtzeeGUI.getBtnDiceValue(i) == 5){
				total += 5;
			} else {
				YahtzeeGUI.setBtnScore(5, 0);
			}
		}
		YahtzeeGUI.setBtnScore(5, total);
	}	

	public void checkSixes(){
		// For explanations see setAces()
		int total = 0;

		for (int i = 0; i < YahtzeeGUI.getGame().getDiceLength(); i++){
			if (YahtzeeGUI.getBtnDiceValue(i) == 6){
				total += 6;
			} else {
				YahtzeeGUI.setBtnScore(6, 0);
			}
		}
		YahtzeeGUI.setBtnScore(6, total);
	}
	public void check3Kind(){		
		ArrayList<Integer> dice = new ArrayList<Integer>();

		for (int i = 0; i < YahtzeeGUI.getGame().getDice().length ; i++ ){
			dice.add(YahtzeeGUI.getBtnDiceValue(i));
		}

		Collections.sort(dice);

		int dice0 = dice.get(0);
		int dice1 = dice.get(1);
		int dice2 = dice.get(2);
		int dice3 = dice.get(3);
		int dice4 = dice.get(4);

		int score = dice0 + dice1 + dice2 + dice3 + dice4;

		if ( (dice0 == dice1 && dice0 == dice2) || (dice1 == dice2 && dice1 == dice3) || (dice2 == dice3 && dice2 == dice4)){
			YahtzeeGUI.setBtnScore(11, score);
		} else {
			YahtzeeGUI.setBtnScore(11, 0);
		}
	}	

	public void check4Kind(){		
		ArrayList<Integer> dice = new ArrayList<Integer>();

		for (int i = 0; i < YahtzeeGUI.getGame().getDice().length ; i++ ){
			dice.add(YahtzeeGUI.getBtnDiceValue(i));
		}

		Collections.sort(dice);

		int dice0 = dice.get(0);
		int dice1 = dice.get(1);
		int dice2 = dice.get(2);
		int dice3 = dice.get(3);
		int dice4 = dice.get(4);

		int score = dice0 + dice1 + dice2 + dice3 + dice4;

		if ( (dice0 == dice1 && dice0 == dice2 && dice0 == dice3) || 
				(dice1 == dice2 && dice1 == dice3 && dice1 == dice4)
				){
			YahtzeeGUI.setBtnScore(12, score);
		} else {
			YahtzeeGUI.setBtnScore(12, 0);
		}
	}	

	public void checkFullHouse(){		
		ArrayList<Integer> dice = new ArrayList<Integer>();

		for (int i = 0; i < YahtzeeGUI.getGame().getDice().length ; i++ ){
			dice.add(YahtzeeGUI.getBtnDiceValue(i));
		}

		Collections.sort(dice);

		int dice0 = dice.get(0);
		int dice1 = dice.get(1);
		int dice2 = dice.get(2);
		int dice3 = dice.get(3);
		int dice4 = dice.get(4);

		int score = 25;

		if ( ((dice0 == dice1 && dice0 == dice2) && (dice3 == dice4)) || 
				((dice0 == dice1) && (dice2 == dice3 && dice2 == dice4))
				){
			YahtzeeGUI.setBtnScore(13, score);
		} else {
			YahtzeeGUI.setBtnScore(13, 0);
		}
	}	

	public void checkSmallStraight(){		
		ArrayList<Integer> dice = new ArrayList<Integer>();

		int[] diceNew = new int[5]; // this is a new array to pass the Integers through and make into int.
		int score = 30; 

		for (int i = 0; i < YahtzeeGUI.getGame().getDice().length ; i++ ){
			dice.add(YahtzeeGUI.getBtnDiceValue(i));
		}

		Collections.sort(dice);

		for (int i = 0; i < YahtzeeGUI.getGame().getDice().length ; i++ ){ 
			diceNew[i] = dice.get(i); // Converts Integer into int, for maths purposes.
		}

		for (int i = 4; i > 0 ; i--){ //this checks if there are any duplicates. If there are, it makes the value (only for smallStraight) -1. -1 as to not make 12336 a small straight (it would appear as 01236)
			if (diceNew[i] == diceNew[i - 1]){
				diceNew[i - 1] = -1;
			}
		}

		if ( (
				diceNew[1] == diceNew[0] + 1 && // if the 4 first values are successive 
				diceNew[2] == diceNew[0] + 2 && 
				diceNew[3] == diceNew[0] + 3 
				) || (
						diceNew[2] == diceNew[1] + 1 && // if the 4 last values are successive 
						diceNew[3] == diceNew[1] + 2 &&
						diceNew[4] == diceNew[1] + 3 
						)){
			YahtzeeGUI.setBtnScore(14, score);
		} else {
			YahtzeeGUI.setBtnScore(14, 0);
		}
	}	

	public void checkLargeStraight(){		
		ArrayList<Integer> dice = new ArrayList<Integer>();
		int score = 40;

		for (int i = 0; i < YahtzeeGUI.getGame().getDice().length ; i++ ){
			dice.add(YahtzeeGUI.getBtnDiceValue(i));
		}

		Collections.sort(dice);

		int dice0 = dice.get(0);
		int dice1 = dice.get(1);
		int dice2 = dice.get(2);
		int dice3 = dice.get(3);
		int dice4 = dice.get(4);

		if ( 
				dice1 == dice0 + 1 && // if the 5 values are successive 
				dice2 == dice0 + 2 &&
				dice3 == dice0 + 3 &&
				dice4 == dice0 + 4 
				){
			YahtzeeGUI.setBtnScore(15, score);
		} else {
			YahtzeeGUI.setBtnScore(15, 0);
		}
	}	

	public void checkYahtzee(){

		int dice0 = YahtzeeGUI.getGame().getDice(0).getFaceValue();
		int dice1 = YahtzeeGUI.getGame().getDice(1).getFaceValue();
		int dice2 = YahtzeeGUI.getGame().getDice(2).getFaceValue();
		int dice3 = YahtzeeGUI.getGame().getDice(3).getFaceValue();
		int dice4 = YahtzeeGUI.getGame().getDice(4).getFaceValue();

		int score = 50;

		if ( dice1 == dice0 && dice2 == dice0 && dice3 == dice0 && dice4 == dice0 ){
			YahtzeeGUI.setBtnScore(16, score);
		} else {
			YahtzeeGUI.setBtnScore(16, 0);
		}
	}	

	public void checkChance(){

		int dice0 = YahtzeeGUI.getGame().getDice(0).getFaceValue();
		int dice1 = YahtzeeGUI.getGame().getDice(1).getFaceValue();
		int dice2 = YahtzeeGUI.getGame().getDice(2).getFaceValue();
		int dice3 = YahtzeeGUI.getGame().getDice(3).getFaceValue();
		int dice4 = YahtzeeGUI.getGame().getDice(4).getFaceValue();

		int score = dice0 + dice1 + dice2 + dice3 + dice4;

		YahtzeeGUI.setBtnScore(17, score);
	}

}
