package core;

import com.codemercs.iow.IowKit;

/**
 * 
 * @author Pommesfee
 * @version 1.0
 * @since 1.0
 */
public class IOWarrior {
	
	public static void main(String[] args) {
		
		//Variablen
		boolean paused = false;
		Frame frame = null;
		LEDGame ledGame = null;
		LEDController ledCon = null;
		
		//IoWarrior Treiber laden
		long h = IowKit.openDevice();
		System.out.println("Product = "
				+ Long.toHexString(IowKit.getProductId(h)));
		System.out.println("Serial = " + IowKit.getSerialNumber(h));
		
		ledCon = new LEDController(h);
		ledGame = new LEDGame(ledCon);
		frame = new Frame(ledGame);
		
		ledGame.startGame();
		
		//Loop
		while (!ledGame.isCompleted()) {
			try {
				Thread.sleep(250);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	
		IowKit.closeDevice(h);
	}


}
