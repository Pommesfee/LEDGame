package core;

import java.util.ArrayList;
import java.util.Random;

public class LEDGame {

	private static final int GREEN = 1;
	private static final int YELLOW = 2;
	private static final int RED = 3;

	private ArrayList<Integer> gameStack;
	private ArrayList<Integer> eventStack;
	private int progress = 0;
	private int currentProgress = 0;

	private boolean completed;

	private LEDController ledCon;

	private Random r = new Random();

	private Thread thread = null;

	public LEDGame(LEDController ledCon) {
		this.ledCon = ledCon;

		gameStack = new ArrayList<Integer>();

		// Init gameStack
		for (int i = 0; i < 10; i++) {
			gameStack.add((r.nextInt(3) + 1));
			System.out.println(gameStack.get(i));
		}

		eventStack = new ArrayList<Integer>();

		if (gameStack.get(0) == GREEN) {
			ledCon.setGreenActive(true);
		} else if (gameStack.get(0) == YELLOW) {
			ledCon.setYellowActive(true);
		} else if(gameStack.get(0) == RED) {
			ledCon.setRedActive(true);
		}
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		ledCon.setAllActive(false);
	}

	public void notifyGreenPressed() {
		eventStack.add(GREEN);
		checkGame();
	}

	public void notifyYellowPressed() {
		eventStack.add(YELLOW);
		checkGame();
	}

	public void notifyRedPressed() {
		eventStack.add(RED);
		checkGame();
	}

	private void checkGame() {

		if (progress > gameStack.size()) {
			System.out.println("Game Completed");
			completed = true;
			return;
		}
		if (gameStack.get(progress) == eventStack.get(progress)) {
			System.out.println("SUCCESS!");
			progress += 1;
			Frame.setTxtPaneText("SUCCESS!");
		} else if (gameStack.get(progress) != eventStack.get(progress)) {
			System.out.println("FAILED!");
			Frame.setTxtPaneText("FAILED! GameOver");
		}
		
		//TODO Continue LED Enabling/Disabling after game start

	}

	public boolean isCompleted() {
		return completed;
	}

}
