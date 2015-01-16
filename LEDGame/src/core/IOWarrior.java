package core;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.codemercs.iow.IowKit;

/**
 * 
 * @author Pommesfee
 * @version 1.0
 * @since 1.0
 */
public class IOWarrior {
	
	public static void main(String[] args) {

		// Variablen
		boolean paused = false;
		LEDController ledCon = null;
		LEDGame ledGame = null;

		// IoWarrior Treiber laden
		long h = IowKit.openDevice();
		System.out.println("Product = "
				+ Long.toHexString(IowKit.getProductId(h)));
		System.out.println("Serial = " + IowKit.getSerialNumber(h));

		ledCon = new LEDController(h);
		ledGame = new LEDGame(ledCon);

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager
							.getSystemLookAndFeelClassName());
				} catch (Exception e) {
					e.printStackTrace();
				}
				Frame frame = new Frame(ledGame);
				frame.setVisible(true);
			}
		});
		ledGame.startGame();

		// Loop
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
