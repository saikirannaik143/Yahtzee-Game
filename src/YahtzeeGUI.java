import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;

/**
 * The class handles all the swing GUI, and gets the data and game play from other classes to display.
 * 
 * @author i7709331 Bradley Page
 * 
 *
 */
public class YahtzeeGUI extends JFrame{

	private static final long serialVersionUID = 3255683022699487295L; //automatic fix for a warning
	private static JFrame frame;
	private JPanel panel, dicePanel, mainPanel, scoreDescPanel, scoreBtnPanel, helpPanel, settingsPanel;
	private JButton btnRoll;
	private static JButton[] btnDice = new JButton[5];

	private JLabel[] lblScoreDesc = new JLabel[20];
	private static JButton[] btnScore = new JButton[20];

	private JMenuBar menuBar;
	private JMenu menuBarOptions,menuBarHelp;
	private JMenuItem menuNewGame,menuSettings,menuExit,menuHelp;

	private JEditorPane helpText;

	private JRadioButton rdbGreen,rdbBlue,rdbRed,rdbOrange,rdbGold,rdbPurple;
	private ButtonGroup btnGroup;

	private Scoring scoring = new Scoring();

	private int rBackground = 100, gBackground = 150, bBackground = 12, rForeground = 255, gForeground = 255, bForeground = 255;

	private File helpFile;

	private static Yahtzee y = new Yahtzee();
	/**
	 * Returns the Yahtzee class so the same values are used throughout the classes.
	 * 
	 * @return the Yahtzee game class
	 */
	public static Yahtzee getGame(){
		return y;
	}

	
	/**
	 * Creates the Yahtzee GUI
	 */
	public YahtzeeGUI(){
		createWindow(355,620);
		addButtonRoll();
		addButtonDice();
		addMainPanel();
		addScoreDesc();
		addScore();

		frame.add(panel);
		frame.setVisible(true);
	}

	/**
	 * Creates the main window for the Yahztee game.
	 * 
	 * @param width The width of the window created in pixels
	 * @param height The height of the window created in pixels
	 */
	public void createWindow(int width, int height){
		frame = new JFrame();
		frame.setTitle("Yahtzee");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(width, height);
		frame.setResizable(false);
		frame.setJMenuBar(addMenuBar());
		panel = new JPanel(new BorderLayout());
		dicePanel = new JPanel();
		mainPanel = new JPanel();
		scoreDescPanel = new JPanel();
		scoreBtnPanel = new JPanel();

	}

	/**
	 * The returns and creates the JMenuBar so that it can be used by the method setJMenuBar()
	 * 
	 * @return 		the JMenuBar created in this method
	 */
	public JMenuBar addMenuBar(){
		menuBar = new JMenuBar();

		addMenu();

		return menuBar;
	}

	/**
	 * Adds menu headings at the top. also calls the method to create the menu items.
	 */
	public void addMenu(){
		menuBarOptions = new JMenu("Options");
		menuBarOptions.setMnemonic(KeyEvent.VK_O);
		menuBarOptions.getAccessibleContext().setAccessibleDescription("Game options");
		menuBarHelp = new JMenu("Help");
		menuBarHelp.setMnemonic(KeyEvent.VK_H);
		menuBarHelp.getAccessibleContext().setAccessibleDescription("Game Help");
		menuBar.add(menuBarOptions);
		menuBar.add(menuBarHelp);

		addOptionItems();

	}

	/**
	 * Creates all the menu items, sets keystrokes and adds the action listeners.
	 */
	public void addOptionItems(){
		menuNewGame = new JMenuItem("New Game");
		menuNewGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		menuNewGame.addActionListener(new MenuHandler());
		menuSettings = new JMenuItem("Settings");
		menuSettings.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		menuSettings.addActionListener(new MenuHandler());
		menuExit = new JMenuItem("Exit");
		menuExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
		menuExit.addActionListener(new MenuHandler());

		menuHelp = new JMenuItem("Help");
		menuHelp.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
		menuHelp.addActionListener(new MenuHandler());

		menuBarOptions.add(menuNewGame);
		menuBarOptions.add(menuSettings);
		menuBarOptions.add(menuExit);
		menuBarHelp.add(menuHelp);
	}

	/**
	 * Creates the main panel that holds the scores.
	 */
	public void addMainPanel(){
		mainPanel.setLayout(new BorderLayout());
		panel.add(mainPanel, BorderLayout.CENTER);
	}

	/**
	 * Creates the score labels and sets the sizes for each of them. this also adds them to the main panel.
	 */
	public void addScoreDesc(){
		lblScoreDesc[0] = new JLabel("UPPER SECTION  ");
		lblScoreDesc[1] = new JLabel("Aces  ");
		lblScoreDesc[2] = new JLabel("Twos  ");
		lblScoreDesc[3] = new JLabel("Threes  ");
		lblScoreDesc[4] = new JLabel("Fours  ");
		lblScoreDesc[5] = new JLabel("Fives  ");
		lblScoreDesc[6] = new JLabel("Sixes  ");
		lblScoreDesc[7] = new JLabel("TOTAL SCORE  ");
		lblScoreDesc[8] = new JLabel("BONUS  ");
		lblScoreDesc[9] = new JLabel("TOTAL UPPER  ");
		lblScoreDesc[10] = new JLabel("LOWER SECTION  ");
		lblScoreDesc[11] = new JLabel("3 of a Kind  ");
		lblScoreDesc[12] = new JLabel("4 of a Kind  ");
		lblScoreDesc[13] = new JLabel("Full House  ");
		lblScoreDesc[14] = new JLabel("Small Straight  ");
		lblScoreDesc[15] = new JLabel("Large Straight  ");
		lblScoreDesc[16] = new JLabel("Yahtzee!  ");
		lblScoreDesc[17] = new JLabel("Chance  ");
		lblScoreDesc[18] = new JLabel("TOTAL LOWER  ");
		lblScoreDesc[19] = new JLabel("GRAND TOTAL  ");

		mainPanel.add(scoreDescPanel, BorderLayout.WEST);
		scoreDescPanel.setLayout(new BoxLayout(scoreDescPanel,BoxLayout.Y_AXIS));
		scoreDescPanel.setBackground(new Color(rBackground,gBackground,bBackground));

		for(int i = 0; i < lblScoreDesc.length; i++){
			lblScoreDesc[i].setHorizontalAlignment(SwingConstants.RIGHT);
			lblScoreDesc[i].setPreferredSize(new Dimension(150,50));
			lblScoreDesc[i].setMaximumSize(new Dimension(150,50));
			lblScoreDesc[i].setForeground(new Color(rForeground,gForeground,bForeground));
			scoreDescPanel.add(lblScoreDesc[i]);
		}
	}

	/**
	 * Creates the score buttons and adds them to the panel. It adds the action listeners for each button as well.
	 */
	public void addScore(){

		mainPanel.add(scoreBtnPanel, BorderLayout.CENTER);
		scoreBtnPanel.setLayout(new BoxLayout(scoreBtnPanel,BoxLayout.Y_AXIS));
		scoreBtnPanel.setBackground(new Color(rBackground,gBackground,bBackground));
		y.iniScoreCanChange();

		for (int i = 0; i < btnScore.length; i++){

			btnScore[i] = new JButton("-");
			btnScore[i].addActionListener(new ScoreHandler());
			btnScore[i].setMaximumSize(new Dimension(200,50));
			scoreBtnPanel.add (btnScore[i]);
		}

		btnScore[0].setText("SCORE");
		btnScore[0].setBackground(null);
		btnScore[0].setForeground(Color.WHITE);

		btnScore[10].setText("SCORE");
		btnScore[10].setBackground(null);
		btnScore[10].setForeground(Color.WHITE);
	}

	/**
	 *  Creates the roll button and adds and action listener.
	 */
	public void addButtonRoll(){
		btnRoll = new JButton ("Roll the Dice");
		btnRoll.addActionListener(new RollHandler());

		dicePanel.setBackground(new Color(rBackground,gBackground,bBackground));
		dicePanel.add (btnRoll);
		panel.add(dicePanel, BorderLayout.SOUTH);
	}

	/**
	 *  Creates the dice buttons, setting the default value of "-" as well.
	 */
	public void addButtonDice(){

		for (int i = 0; i < btnDice.length; i++){
			btnDice[i] = new JButton("-");
			btnDice[i].addActionListener(new HoldHandler());
			btnDice[i].setPreferredSize(new Dimension(41,26));
			dicePanel.add (btnDice[i]);
		}
		panel.add(dicePanel, BorderLayout.SOUTH);
	}

	/**
	 *  Gets the array btnScore. Can be used to set various attributes using the JButton methods.
	 * 
	 * @return		The btnScore array. Add [i] onto the end to specify which position
	 */
	public static JButton[] getBtnScore(){
		return btnScore;
	}

	/**
	 * 	 Gets the array btnScore at the specified position.
	 * 
	 * @param i The position of the array wanted.
	 * @return	the score button that is selected.
	 */
	public JButton getBtnScore(int i) { 
		return btnScore[i];
	}

	/**
	 *  sets the value of the button specified to the score specified.
	 *  
	 * @param scorePosition the position of the button in the array
	 * @param score the score that needs to be shown.
	 */
	public static void setBtnScore(int scorePosition, int score) {

		if (!String.valueOf(btnScore[scorePosition]).equals("0") 
				&& y.getScoreCanChange()[scorePosition]){ // asks if btnScore[i] != 0 and if the score can change
			btnScore[scorePosition].setText(String.valueOf(score));
			btnScore[scorePosition].setForeground(Color.BLUE);

		}

	}

	/**
	 * 	 * 
	 * @return		the btnDice array. getBtnDice()[i] to get a specific position.
	 */
	public static JButton[] getBtnDice(){
		return btnDice;
	}

	/**
	 * Gets the value currently shown to the user - not the dice Facevalue.
	 * 
	 * @param i position of the dice
	 * @return		The value shown on the dice.
	 */
	public static int getBtnDiceValue(int i){
		return Integer.valueOf(btnDice[i].getText());
	}

	/**
	 *  Shows a pop-up alert for when a player has reached the maximum roll count
	 */
	public void createRollAlert(){
		JOptionPane.showMessageDialog(frame, "You need to pick a score before rolling again", "Pick a score", 1);

	}
	
	/**
	 *  Shows a pop-up for when the player has completed the game.
	 */
	public static void endGame() {
		JOptionPane.showMessageDialog(frame, "You have completed this game of Yahtzee. Your score is: " + y.getGrandTotal(), "Well done!", 1);
		restartDialog();
	}

	/**
	 * The dialog for when a player has completed the game, and is asked to restart or close the game.
	 */
	public static void restartDialog(){
		String[] options = {"Restart", "Close Game"};

		int selection = JOptionPane.showOptionDialog(frame, "Would you like to restart the game?", "Restart", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

		if (selection == 0){
			y.restartGame();
		} else if (selection == 1){
			System.exit(0);
		}

	}

	/**
	 *  The dialog for when the player chooses to restart
	 */
	public void selectRestartDialog(){
		String[] options = {"Continue", "Restart"};

		int selection = JOptionPane.showOptionDialog(frame, "Would you like to restart the game?", "Restart", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

		if (selection == 0){

		} else if (selection == 1){
			y.restartGame();
		}
	}

	/**
	 * The dialog for when the player selects exit fromt the menu.
	 */
	public void selectExitDialog(){

		int selection = JOptionPane.showConfirmDialog(frame, "Would you like to exit the game?", "Exit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

		if (selection == JOptionPane.YES_OPTION){
			System.exit(0);
		}
	}

	/**
	 * Creates the window for the player help
	 */
	public void createHelpWindow(){
		frame = new JFrame();
		frame.setTitle("Yahtzee Help");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(500,500);
		frame.setResizable(false);
		panel = new JPanel(new BorderLayout());

		frame.add(panel);

		helpPanel = new JPanel();
		panel.add(helpPanel, BorderLayout.CENTER);

		helpPanel.setLayout(new BoxLayout(helpPanel,BoxLayout.Y_AXIS));
		helpPanel.setBackground(new Color(rBackground, gBackground, bBackground));

		writeHelp();

		frame.setVisible(true);
	}

	/**
	 * Writes the help into the help window from the help txt file
	 */
	public void writeHelp(){
		String fileHelp = "help.txt";
		helpFile = new File(fileHelp);

		helpText = new JEditorPane();
		helpText.setEditable(false);
		helpPanel.add(new JScrollPane(helpText), BorderLayout.CENTER);


		try {
			helpText.setPage(helpFile.toURI().toURL());
		} catch(Exception e) {
			JOptionPane.showMessageDialog(frame, "Could not write file to JEditorPane", "Error", 0);
			e.printStackTrace();
		}

	}

	/**
	 *  creates the settings window - shows colors and the exit button.
	 */
	public void createSettingsWindow(){
		frame = new JFrame();
		frame.setTitle("Settings");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(200,250);
		frame.setResizable(false);

		panel = new JPanel(new BorderLayout());
		frame.add(panel);

		settingsPanel = new JPanel();
		panel.add(settingsPanel, BorderLayout.CENTER);
		settingsPanel.setLayout(new BoxLayout(settingsPanel,BoxLayout.Y_AXIS));
		settingsPanel.setBackground(new Color(rBackground, gBackground, bBackground));

		//dbGreen,rdbBlue,rdbRed,rdbOrange,rdbGold,rdbPurple
		int w = 200;
		int h = 35;		
		rdbGreen = new JRadioButton("Green");
		settingsPanel.add(rdbGreen);
		rdbGreen.addActionListener(new ColorHandler());
		rdbGreen.setSelected(true);
		rdbGreen.setPreferredSize(new Dimension(w,h));
		rdbGreen.setBackground(new Color(100,150,12));
		rdbGreen.setForeground(Color.WHITE);

		rdbBlue = new JRadioButton("Blue");
		settingsPanel.add(rdbBlue);
		rdbBlue.addActionListener(new ColorHandler());
		rdbBlue.setPreferredSize(new Dimension(w,h));
		rdbBlue.setBackground(new Color(54,49,189));
		rdbBlue.setForeground(Color.WHITE);

		rdbRed = new JRadioButton("Red");
		settingsPanel.add(rdbRed);
		rdbRed.addActionListener(new ColorHandler());
		rdbRed.setPreferredSize(new Dimension(w,h));
		rdbRed.setBackground(new Color(199,8,8));
		rdbRed.setForeground(Color.WHITE);

		rdbOrange = new JRadioButton("Orange");
		settingsPanel.add(rdbOrange);
		rdbOrange.addActionListener(new ColorHandler());
		rdbOrange.setPreferredSize(new Dimension(w,h));
		rdbOrange.setBackground(new Color(242,111,17));
		rdbOrange.setForeground(Color.WHITE);

		rdbGold = new JRadioButton("Gold");
		settingsPanel.add(rdbGold);
		rdbGold.addActionListener(new ColorHandler());
		rdbGold.setPreferredSize(new Dimension(w,h));
		rdbGold.setBackground(new Color(242,190,17));
		rdbGold.setForeground(Color.BLACK);

		rdbPurple = new JRadioButton("Purple");
		settingsPanel.add(rdbPurple);
		rdbPurple.addActionListener(new ColorHandler());
		rdbPurple.setPreferredSize(new Dimension(w,h));
		rdbPurple.setBackground(new Color(104,12,138));
		rdbPurple.setForeground(Color.WHITE);

		btnGroup = new ButtonGroup();
		btnGroup.add(rdbGreen);
		btnGroup.add(rdbBlue);
		btnGroup.add(rdbRed);
		btnGroup.add(rdbOrange);
		btnGroup.add(rdbGold);
		btnGroup.add(rdbPurple);

		frame.setVisible(true);
	}

	/**
	 * uses the current colour variables for background and foreground and sets the background and text accordingly.
	 */
	public void setGameBackground(){

		scoreDescPanel.setBackground(new Color(rBackground,gBackground,bBackground));
		scoreBtnPanel.setBackground(new Color(rBackground,gBackground,bBackground));
		dicePanel.setBackground(new Color(rBackground,gBackground,bBackground));
		settingsPanel.setBackground(new Color(rBackground,gBackground,bBackground));

		for (int i = 0 ; i < lblScoreDesc.length; i++){
			lblScoreDesc[i].setForeground(new Color(rForeground,gForeground,bForeground));
		}
		
		btnScore[0].setForeground(new Color(rForeground,gForeground,bForeground));
		btnScore[10].setForeground(new Color(rForeground,gForeground,bForeground));

	}

	/**
	 * Tells the player to roll the dice before selecting a score.
	 */
	public void rollMessage(){
		JOptionPane.showMessageDialog(frame, "Please roll before selecting a Score", "Roll The Dice", 1);
	}

	/**
	 * Tells the player to toll the dice before holding the dice.
	 */
	public void holdMessage(){
		JOptionPane.showMessageDialog(frame, "Please roll before holding the dice", "Roll The Dice", 1);
	}

	/**
	 * 
	 * @author i7709331
	 *
	 */
	class ColorHandler implements ActionListener{
		public void actionPerformed(ActionEvent event){
			Object source = event.getSource();

			if (source == rdbGreen){
				rBackground = 100;
				gBackground = 150;
				bBackground = 12;
				rForeground = 255;
				gForeground = 255;
				bForeground = 255;
			} else if (source == rdbBlue){
				rBackground = 54;
				gBackground = 49;
				bBackground = 189;
				rForeground = 255;
				gForeground = 255;
				bForeground = 255;
			} else if (source == rdbRed){
				rBackground = 199;
				gBackground = 8;
				bBackground = 8;
				rForeground = 255;
				gForeground = 255;
				bForeground = 255;
			} else if (source == rdbOrange){
				rBackground = 242;
				gBackground = 111;
				bBackground = 17;
				rForeground = 255;
				gForeground = 255;
				bForeground = 255;
			} else if (source == rdbGold){
				rBackground = 242;
				gBackground = 190;
				bBackground = 17;
				rForeground = 0;
				gForeground = 0;
				bForeground = 0;
			} else if (source == rdbPurple){
				rBackground = 104;
				gBackground = 12;
				bBackground = 138;
				rForeground = 255;
				gForeground = 255;
				bForeground = 255;
			}

			setGameBackground();

		}
	}
	
	/**
	 * Finds the source when a menu button is pressed and chooses the correct action to perform.
	 * 
	 * @author i7709331
	 *
	 */
	class MenuHandler implements ActionListener{
		public void actionPerformed(ActionEvent event){
			Object source = event.getSource();

			if(source == menuNewGame){
				selectRestartDialog();
			} else if ( source == menuSettings){
				createSettingsWindow();
			} else if ( source == menuExit){
				selectExitDialog();
			} else if ( source == menuHelp){
				try {
					createHelpWindow();
				} catch (Exception e) {
					System.err.println("No help file found");
					e.printStackTrace();
				}
			}
		}
	}
	class ScoreHandler implements ActionListener {
		public void actionPerformed (ActionEvent event){

			Object source = event.getSource(); // this gets the source so I can get the value from it.

			if (y.getRollCount() == 0){
				rollMessage();
			} else {

				for(int i = 1; i < 7; i++){

					if(source == btnScore[i] && y.getScoreCanChange()[i] && !y.getScoreIsSet()[i]){
						y.setScore(i, ((AbstractButton) source).getText());
						btnScore[i].setForeground(new Color(100,150,12));

						y.updateUpperTotal();
						y.updateBonus();
						y.updateUpperGrandTotal();
						y.updateGrandTotal();
						y.resetRollCount();
						y.resetScoreButtons();
						y.resetDice();
						break;
					}
				}
				for(int i = 10; i < 18; i++){
					if(source == btnScore[i] && y.getScoreCanChange()[i] && !y.getScoreIsSet()[i]){
						y.setScore(i, ((AbstractButton) source).getText());
						btnScore[i].setForeground(new Color(100,150,12));

						y.updateLowerTotal();
						y.updateGrandTotal();
						y.resetRollCount();
						y.resetScoreButtons();
						y.resetDice();
						break;
					}
				}

				y.checkEndGame();
			}


		}
	}

	class RollHandler implements ActionListener {

		public void actionPerformed(ActionEvent event) {
			y.checkRollCount();

			if (y.getCanRoll() == false){
				createRollAlert();
			} else {
				y.increaseRollCount();


				for(int i = 0; i < y.getDiceLength(); i++){
					if (y.getDice(i).getHoldState() != true){
						y.getDice(i).roll();
						btnDice[i].setText(String.valueOf(y.getDice(i).getFaceValue()));
					}
				}

				scoring.checkAces();
				scoring.checkTwos();
				scoring.checkThrees();
				scoring.checkFours();
				scoring.checkFives();
				scoring.checkSixes();
				scoring.check3Kind();
				scoring.check4Kind();
				scoring.checkFullHouse();
				scoring.checkSmallStraight();
				scoring.checkLargeStraight();
				scoring.checkYahtzee();
				scoring.checkChance();
			}


		}
	}

	class HoldHandler implements ActionListener {

		public void actionPerformed(ActionEvent event) {


			if (y.getRollCount() == 0){
				holdMessage();
			} else{
				for (int i = 0; i < btnDice.length; i++){
					if (event.getSource()== btnDice[i]){ // Picks the pressed button after running through them all.
						if (y.getDice(i).getHoldState() == false){
							y.getDice(i).setHoldState(true);
							btnDice[i].setBackground(new Color(156,0,0));
							btnDice[i].setForeground(new Color(255,255,255));
						} else if (y.getDice(i).getHoldState() == true){
							y.getDice(i).setHoldState(false);
							btnDice[i].setBackground(UIManager.getColor("Button.background"));
							btnDice[i].setForeground(UIManager.getColor("Button.foreground"));
						}
					}
				}
			}
		}
	}

}