package core;

import com.codemercs.iow.IowKit;

/**
 * 
 * @author Christian Klook
 *
 */
public class IOWarrior {
	
	static boolean paused = false;
	static Frame frame;
	static LEDGame ledGame;
	static LEDController ledCon;
	
	public static void main(String[] args) {
		
		long h = IowKit.openDevice();
		System.out.println("Product = "
				+ Long.toHexString(IowKit.getProductId(h)));
		System.out.println("Serial = " + IowKit.getSerialNumber(h));
		
		ledCon = new LEDController(h);
		
		ledGame = new LEDGame(ledCon);
		frame = new Frame(ledGame);
		
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
