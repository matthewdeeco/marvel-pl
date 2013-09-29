package ide.view;

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// JPanel version

/** @brief A simple Java Console for your application (Swing version).
 * 
 * @par Comments: Original located at
 * http://www.comweb.nl/java/Console/Console.html
 * 
 * @author RJHM van den Bergh , rvdb@comweb.nl
 * 
 * @history 02-07-2012 David MacDermot Marked: DWM 02-07-2012 Added KeyListener
 * to pipe text to STDIN. Added custom block style caret. Added various other
 * customizations.
 * @bug */

public class Console extends JPanel implements ActionListener, Runnable {
	private JTextArea textArea;
	private Thread reader;
	private Thread reader2;
	private boolean quit;

	private final PipedInputStream pin = new PipedInputStream();
	private final PipedInputStream pin2 = new PipedInputStream();
	private final PipedOutputStream pout3 = new PipedOutputStream(); // DWM 02-07-2012

	public Console() {
		textArea = new JTextArea();
		textArea.setBackground(new Color(20, 20, 20)); // DWM 02-07-2012
		textArea.setForeground(Color.WHITE); // DWM 02-07-2012
		textArea.setCaretColor(textArea.getForeground()); // DWM 02-07-2012
		textArea.setFont(new Font("monospaced", Font.PLAIN, 14)); // DWM 02-07-2012
		textArea.setLineWrap(true); // DWM 02-07-2012
		textArea.setWrapStyleWord(true); // DWM 02-07-2012
		textArea.setEditable(true); // DWM 02-07-2012
		JButton button = new JButton("Clear");

		setLayout(new BorderLayout());
		textArea.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		add(new JScrollPane(textArea), BorderLayout.CENTER);
		// add(button, BorderLayout.SOUTH);

		button.addActionListener(this);

		try {
			PipedOutputStream pout = new PipedOutputStream(this.pin);
			System.setOut(new PrintStream(pout, true));
		} catch (java.io.IOException io) {
			textArea.append("Couldn't redirect STDOUT to this console\n" + io.getMessage());
			textArea.setCaretPosition(textArea.getDocument().getLength()); // DWM
																			// 02-07-2012
		} catch (SecurityException se) {
			textArea.append("Couldn't redirect STDOUT to this console\n" + se.getMessage());
			textArea.setCaretPosition(textArea.getDocument().getLength()); // DWM
																			// 02-07-2012
		}

		try {
			PipedOutputStream pout2 = new PipedOutputStream(this.pin2);
			System.setErr(new PrintStream(pout2, true));
		} catch (java.io.IOException io) {
			textArea.append("Couldn't redirect STDERR to this console\n" + io.getMessage());
			textArea.setCaretPosition(textArea.getDocument().getLength()); // DWM
																			// 02-07-2012
		} catch (SecurityException se) {
			textArea.append("Couldn't redirect STDERR to this console\n" + se.getMessage());
			textArea.setCaretPosition(textArea.getDocument().getLength()); // DWM
																			// 02-07-2012
		}

		try {
			System.setIn(new PipedInputStream(this.pout3));
		} catch (java.io.IOException io) {
			textArea.append("Couldn't redirect STDIN to this console\n" + io.getMessage());
			textArea.setCaretPosition(textArea.getDocument().getLength()); // DWM
																			// 02-07-2012
		} catch (SecurityException se) {
			textArea.append("Couldn't redirect STDIN to this console\n" + se.getMessage());
			textArea.setCaretPosition(textArea.getDocument().getLength()); // DWM
																			// 02-07-2012
		}

		textArea.addKeyListener(new KeyListener() {

			public void keyPressed(KeyEvent e) {
			}

			public void keyReleased(KeyEvent e) {
			}

			public void keyTyped(KeyEvent e) {
				try {
					pout3.write(e.getKeyChar());
				} catch (IOException ex) {
				}
			}
		}); // DWM 02-07-2012

		quit = false; // signals the Threads that they should exit

		// Starting two separate threads to read from the PipedInputStreams
		reader = new Thread(this);
		reader.setDaemon(true);
		reader.start();

		reader2 = new Thread(this);
		reader2.setDaemon(true);
		reader2.start();
	}

	public synchronized void actionPerformed(ActionEvent evt) {
		this.clear();
	}

	public synchronized void run() {
		try {
			while (Thread.currentThread() == reader) {
				try {
					this.wait(100);
				} catch (InterruptedException ie) {
				}
				if (pin.available() != 0) {
					String input = this.readLine(pin);
					textArea.append(input);
					textArea.setCaretPosition(textArea.getDocument().getLength()); // DWM
																					// 02-07-2012
				}
				if (quit)
					return;
			}

			while (Thread.currentThread() == reader2) {
				try {
					this.wait(100);
				} catch (InterruptedException ie) {
				}
				if (pin2.available() != 0) {
					String input = this.readLine(pin2);
					textArea.append(input);
					textArea.setCaretPosition(textArea.getDocument().getLength()); // DWM
																					// 02-07-2012
				}
				if (quit)
					return;
			}
		} catch (Exception e) {
			textArea.append("\nConsole reports an Internal error.");
			textArea.append("The error is: " + e);
			textArea.setCaretPosition(textArea.getDocument().getLength()); // DWM
																			// 02-07-2012
		}

	}

	/** @brief Read a line of text from the input stream
	 * @param in The PipedInputStream to read
	 * @return String A line of text
	 * @throws IOException */
	private synchronized String readLine(PipedInputStream in) throws IOException {
		String input = "";
		do {
			int available = in.available();
			if (available == 0)
				break;
			byte b[] = new byte[available];
			in.read(b);
			input = input + new String(b, 0, b.length);
		} while (!input.endsWith("\n") && !input.endsWith("\r\n") && !quit);
		return input;
	}

	public void clear() { // DWM 02-07-2012
		textArea.setText("");
	}

	public Color getBackground() { // DWM 02-07-2012
		return Color.BLACK;
	}

	public void setBackground(Color bg) { // DWM 02-07-2012
		this.textArea.setBackground(bg);
	}

	public Color getForeground() { // DWM 02-07-2012
		return Color.WHITE;
	}

	public void setForeground(Color fg) { // DWM 02-07-2012
		this.textArea.setForeground(fg);
		this.textArea.setCaretColor(fg);
	}
}