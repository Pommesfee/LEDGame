package core;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;

/**
 * Class that handles the optical apperance of
 * the LED-Game.
 * 
 * @author Pommesfee
 * @version 1.0
 * @since 1.0
 */
public class Frame extends JFrame{

	private String title = "LED Game";
	
	private JPanel pnlBtn;
	
	private JButton btnGreen;
	private JButton btnYellow;
	private JButton btnRed;
	
	private static JTextPane txtPaneOutput;
	
	private String btnGreenTitle = "Green";
	private String btnYellowTitle = "Yellow";
	private String btnRedTitle = "Red";
	
	private LEDGame ledGame;
	
	public Frame(LEDGame ledGame) {
		
		this.ledGame = ledGame;
		
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setTitle(title);

		this.createWidgets();
		this.addWidgets();
		this.pack();

		this.setLocationRelativeTo(null);
		this.setMinimumSize(getContentPane().getPreferredSize());

		WindowListener winListener = new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int answer = JOptionPane.showConfirmDialog(Frame.this,
						"Wollen sie das Programm wirklich beenden ?",
						"Wirklich beenden ?", JOptionPane.YES_NO_OPTION,
						JOptionPane.INFORMATION_MESSAGE);
				if (answer == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		};
		this.addWindowListener(winListener);
		
		this.setVisible(true);
	}


	private void createWidgets() {
	
		// Panel Button
		if (pnlBtn == null) {
			pnlBtn = new JPanel();
		}
		
		// Text Panel Output
		if (txtPaneOutput == null) {
			txtPaneOutput = new JTextPane();
			txtPaneOutput.setText("Start game by pressing a button;");
			txtPaneOutput.setEditable(false);
		}
		
		// Button Green
		if (btnGreen == null) {
			btnGreen = new JButton();
			btnGreen.setPreferredSize(new Dimension(80, 25));
			btnGreen.setMaximumSize(new Dimension(80, 25));
			btnGreen.setMinimumSize(new Dimension(80, 25));
			btnGreen.setText(btnGreenTitle);
			btnGreen.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("Green pressed");
					ledGame.notifyGreenPressed();
				}
			});
		}
		
		// Button Yellow
		if (btnYellow == null) {
			btnYellow = new JButton();
			btnYellow.setPreferredSize(new Dimension(80, 25));
			btnYellow.setMaximumSize(new Dimension(80, 25));
			btnYellow.setMinimumSize(new Dimension(80, 25));
			btnYellow.setText(btnYellowTitle);
			btnYellow.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("Yellow pressed");
					ledGame.notifyYellowPressed();
				}
			});
		}
		
		// Button Red
		if (btnRed == null) {
			btnRed = new JButton();
			btnRed.setPreferredSize(new Dimension(80, 25));
			btnRed.setMaximumSize(new Dimension(80, 25));
			btnRed.setMinimumSize(new Dimension(80, 25));
			btnRed.setText(btnRedTitle);
			btnRed.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("Red pressed");
					ledGame.notifyRedPressed();
				}
			});
		}
			
	}

	private void addWidgets() {

		this.getContentPane().setLayout(new BorderLayout(5, 5));
		
		this.getContentPane().add(txtPaneOutput, BorderLayout.CENTER);
		
		pnlBtn.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		pnlBtn.add(btnGreen);
		pnlBtn.add(btnYellow);
		pnlBtn.add(btnRed);
		
		this.getContentPane().add(pnlBtn, BorderLayout.PAGE_END);
		
	}
	
	public static void setTxtPaneText(String text) {
		txtPaneOutput.setText(text);
	}
	
}
