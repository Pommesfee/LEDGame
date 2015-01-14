package core;

import com.codemercs.iow.IowKit;

public class LEDController {

	private long handle;
	public boolean greenActive = false;
	public boolean yellowActive = false;
	public boolean redActive = false;

	private int report[] = new int[3];

	private long result;

	public LEDController(long handle) {
		this.handle = handle;

		// ReportID = 0 fuer I/O Kommunikation
		report[0] = 0;
		// Port 0 LOW setzen
		report[1] = 0;
		// Port 1 HIGH setzen
		report[2] = 0;
		
		result = IowKit.write(handle, 0 /* Pipe 0 */, report);
		if (result != report.length)
			System.out.println("ERROR: IowKit.write()");
	}

	public void setGreenActive(boolean active) {

		// LED mit iow kit anschaltwen auschalten !!!

		if (isGreenActive() && active) {
			System.out.println("Green already active!");
		} else if (!isGreenActive() && active) {
			if (!isYellowActive() && !isRedActive()) {
				report[1] = 1;
				greenActive = true;
			}
			if (isYellowActive() && !isRedActive()) {
				report[1] = 3;
				greenActive = true;
			}
			if (!isYellowActive() && isRedActive()) {
				report[1] = 5;
				greenActive = true;
	 		}
			if (isYellowActive() && isRedActive()) {
				report[1] = 7;
				greenActive = true;
			}
		}
		
		////////////////////////
		
		if (!isGreenActive() && !active) {
			System.out.println("Green already inactive!");
		} else if (isGreenActive() && !active) {
			if (!isYellowActive() && !isRedActive()) {
				report[1] = 0;
				greenActive = false;
			}
			if (isYellowActive() && !isRedActive()) {
				report[1] = 2;
				greenActive = false;
			}
			if (!isYellowActive() && isRedActive()) {
				report[1] = 5;
				greenActive = false;
			}
			if (isYellowActive() && isRedActive()) {
				report[1] = 6;
				greenActive = false;
			}
		}
		
		result = IowKit.write(handle, 0 /* Pipe 0 */, report);
		if (result != report.length)
			System.out.println("ERROR: IowKit.write()");
	}

	
	public void setYellowActive(boolean active) {

		// LED mit iow kit anschaltwen auschalten !!!

		if (isYellowActive() && active) {
			System.out.println("Yellow already active!");
		} else if (!isYellowActive() && active) {
			if (!isGreenActive() && !isRedActive()) {
				report[1] = 2;
				yellowActive = true;
			}
			if (isGreenActive() && !isRedActive()) {
				report[1] = 3;
				yellowActive = true;
			}
			if(!isGreenActive() && isRedActive()) {
				report[1] = 6;
				yellowActive = true;
			}
			if (isGreenActive() && isRedActive()) {
				report[1] = 7;
				yellowActive = true;
			}
		}
		
		////////////////////////
		
		if (!isYellowActive() && !active) {
			System.out.println("Yellow already inactive!");
		} else if (isYellowActive() && !active) {
			if (!isGreenActive() && !isRedActive()) {
				report[1] = 0;
				yellowActive = false;
			}
			if (isGreenActive() && !isRedActive()) {
				report[1] = 1;
				yellowActive = false;
			}
			if (!isGreenActive() && isRedActive()) {
				report[1] = 4;
				yellowActive = false;
			}
			if (isGreenActive() && isRedActive()) {
				report[1] = 5;
				yellowActive = false;
			}
		}

		result = IowKit.write(handle, 0 /* Pipe 0 */, report);
		if (result != report.length)
			System.out.println("ERROR: IowKit.write()");

	}

	public void setRedActive(boolean active) {

		// LED mit iow kit anschaltwen auschalten !!!

		if (isRedActive() && active) {
			System.out.println("Red already active!");
		} else if (!isRedActive() && active) {
			if (!isGreenActive() && !isYellowActive()) {
				report[1] = 4;
				redActive = true;
			}
			if (isGreenActive() && !isYellowActive()) {
				report[1] = 5;
				redActive = true;
			}
			if (!isGreenActive() && isYellowActive()) {
				report[1] = 6;
				redActive = true;
			}
			if (isGreenActive() && isYellowActive()) {
				report[1] = 7;
				redActive = true;
			}
		}
		
		////////////////////////
		
		if (!isRedActive() && !active) {
			System.out.println("Red already inactive!");
		} else if (isRedActive() && !active) {
			if (!isGreenActive() && !isYellowActive()) {
				report[1] = 0;
				redActive = false;
			}
			if (isGreenActive() && !isYellowActive()) {
				report[1] = 1;
				redActive = false;
			}
			if (!isGreenActive() && isYellowActive()) {
				report[1] = 2;
				redActive = false;
			}
			if (isGreenActive() && isYellowActive()) {
				report[1] = 3;
				redActive = false;
			}
		}

		result = IowKit.write(handle, 0 /* Pipe 0 */, report);
		if (result != report.length)
			System.out.println("ERROR: IowKit.write()");

	}
	
	private boolean isGreenActive() {
		return greenActive;
	}

	private boolean isYellowActive() {
		return yellowActive;
	}

	private boolean isRedActive() {
		return redActive;
	}
}
