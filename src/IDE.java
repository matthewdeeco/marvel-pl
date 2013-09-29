

import ide.view.View;

import javax.swing.SwingUtilities;

public class IDE {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {			
			@Override
			public void run() {
				View view = new View();
				view.show();
			}
		});
	}
}
