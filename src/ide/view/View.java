package ide.view;

import java.awt.*;
import javax.swing.*;
import ide.controller.Controller;

public class View {
	private static final Dimension WINDOW_SIZE = new Dimension(500, 400);
	private static final String DEFAULT_TITLE = "Marvel IDE";
	private JFrame frame;
	private DialogHandler dialogHandler;
	private TextPanel codeEditor;
	private Console console;
	private JTabbedPane tabbedPane;
	
	public View() {
		frame = new JFrame(DEFAULT_TITLE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(WINDOW_SIZE);
		dialogHandler = new DialogHandler(frame);
		
		frame.setContentPane(createMainPanel());
		Controller controller = new Controller();
		frame.setJMenuBar(new MenuBar(controller));
		controller.setView(this);
		controller.setDialogHandler(dialogHandler);
	}
	
	private JPanel createMainPanel() {
		JPanel mainPanel = new JPanel(new BorderLayout());
		tabbedPane = new JTabbedPane();
		codeEditor = new CodeEditorPanel();
		console = new Console();
		tabbedPane.addTab("Code Editor", codeEditor);
		tabbedPane.addTab("Console", console);
		mainPanel.add(tabbedPane, BorderLayout.CENTER);
		return mainPanel;
	}
	
	public void show() {
		frame.setVisible(true);
	}
	
	public String getText() {
		return codeEditor.getText();
	}
	
	public void setText(String text) {
		codeEditor.setText(text);
	}
	
	public void setTitle(String text) {
		frame.setTitle(text);
	}
	
	public void switchToConsole() {
		console.clear();
		tabbedPane.setSelectedIndex(1);
	}

	public void reset() {
		frame.setTitle(DEFAULT_TITLE);
		codeEditor.setText("");
	}
}