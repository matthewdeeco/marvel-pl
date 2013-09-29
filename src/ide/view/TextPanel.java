package ide.view;

import javax.swing.JPanel;

public abstract class TextPanel extends JPanel {
	public abstract String getText();
	public abstract void setText(String text);
	public abstract boolean isEmpty();
}
