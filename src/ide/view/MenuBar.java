package ide.view;

import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import ide.controller.Controller;

import javax.swing.*;

public class MenuBar extends JMenuBar implements ActionListener {
	private JMenu fileMenu;
	private JMenu sourceMenu;
	private JMenuItem newMenuItem;
	private JMenuItem openMenuItem;
	private JMenuItem saveMenuItem;
	private JMenuItem quitMenuItem;
	private JMenuItem runMenuItem;
	private JMenuItem translateMenuItem;
	private Controller controller;
	
	public MenuBar(Controller controller) {
		this.controller = controller;
		
		fileMenu = new JMenu("File");
		sourceMenu = new JMenu("Source");
		
		add(fileMenu);
		add(sourceMenu);
		newMenuItem = createMenuItem(fileMenu, "New", this);
		newMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, Event.CTRL_MASK));
		openMenuItem = createMenuItem(fileMenu, "Open", this);
		openMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, Event.CTRL_MASK));
		saveMenuItem = createMenuItem(fileMenu, "Save", this);
		saveMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, Event.CTRL_MASK));
		quitMenuItem = createMenuItem(fileMenu, "Quit", this);
		quitMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, Event.CTRL_MASK));
		
		runMenuItem = createMenuItem(sourceMenu, "Run", this);
		runMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F11, 0));
		translateMenuItem = createMenuItem(sourceMenu, "Translate", this);
		translateMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F11, Event.CTRL_MASK));
	}
	
	private JMenuItem createMenuItem(JComponent parent, String text, ActionListener listener) {
		JMenuItem menuItem = new JMenuItem(text);
		menuItem.addActionListener(listener);
		parent.add(menuItem);
		return menuItem;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == newMenuItem)
			controller.newFile();
		else if (source == openMenuItem)
			controller.openFile();
		else if (source == saveMenuItem)
			controller.saveFile();
		else if (source == quitMenuItem)
			controller.quit();
		else if (source == runMenuItem)
			controller.runCode();
		else if (source == translateMenuItem)
			controller.translateCode();
	}
}
